/*
 *  Created by Emaar Hospitality Group on 9/8/18 3:03 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 9/8/18 2:58 PM
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ehg.home.navigation;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.json.JSONArray;

/**
 * The class is used to manage navigation through multiple stacks of fragments, as well as
 * coordinate fragments that may appear on screen.
 */
@SuppressWarnings("RestrictedApi")
public class FragmentNavigationController {

  //Declare the constants  There is a maximum of 5 tabs,
  // this is per Material Design's Bottom Navigation's design spec.
  public static final int NO_TAB = -1;
  public static final int TAB1 = 0;
  public static final int TAB2 = 1;
  public static final int TAB3 = 2;
  public static final int TAB4 = 3;
  public static final int TAB5 = 4;
  public static final int TAB6 = 5;

  private static final int MAX_NUM_TABS = 5;

  // Extras used to store savedInstanceState
  private static final String EXTRA_TAG_COUNT = FragmentNavigationController.class.getName()
      + ":EXTRA_TAG_COUNT";
  private static final String EXTRA_SELECTED_TAB_INDEX =
      FragmentNavigationController.class.getName() + ":EXTRA_SELECTED_TAB_INDEX";
  private static final String EXTRA_CURRENT_FRAGMENT =
      FragmentNavigationController.class.getName() + ":EXTRA_CURRENT_FRAGMENT";
  private static final String EXTRA_FRAGMENT_STACK =
      FragmentNavigationController.class.getName() + ":EXTRA_FRAGMENT_STACK";

  @IdRes
  private final int mcontainerId;
  @NonNull
  private final List<Stack<Fragment>> mfragmentStacks;
  @NonNull
  private final FragmentManager mfragmentManager;
  private final FragmentNavigationTransactionOptions mdefaultTransactionOptions;
  @TabIndex
  private int mselectedTabIndex;
  private int mtagCount;
  @Nullable
  private Fragment mcurrentFrag;
  @Nullable
  private DialogFragment mcurrentDialogFrag;
  @Nullable
  private RootFragmentListener mrootFragmentListener;
  @Nullable
  private TransactionListener mtransactionListener;
  private boolean mexecutingTransaction;

  /**
   * Region Construction and setup
   * @param builder builder
   * @param savedInstanceState bundle object
   */
  private FragmentNavigationController(Builder builder, @Nullable Bundle savedInstanceState) {
    mfragmentManager = builder.mfragmentManager;
    mcontainerId = builder.mcontainerId;
    mfragmentStacks = new ArrayList<>(builder.mnumberOfTabs);
    mrootFragmentListener = builder.mrootFragmentListener;
    mtransactionListener = builder.mtransactionListener;
    mdefaultTransactionOptions = builder.mdefaultTransactionOptions;
    mselectedTabIndex = builder.mselectedTabIndex;

    //Attempt to restore from bundle, if not, initialize
    if (!restoreFromBundle(savedInstanceState, builder.mrootFragments)) {

      for (int i = 0; i < builder.mnumberOfTabs; i++) {
        Stack<Fragment> stack = new Stack<>();
        if (builder.mrootFragments != null) {
          stack.add(builder.mrootFragments.get(i));
        }
        mfragmentStacks.add(stack);
      }

      initialize(builder.mselectedTabIndex);
    }
  }

  /**
   * Returns builder object.
   * @param savedInstanceState bundle
   * @param fragmentManager fragmentManager
   * @param containerId containerId
   * @return builder
   */
  public static Builder newBuilder(@Nullable Bundle savedInstanceState,
      FragmentManager fragmentManager,
      int containerId) {
    return new Builder(savedInstanceState, fragmentManager, containerId);
  }

  //endregion

  //region Transactions

  /**
   * Function used to switch to the specified fragment stack.
   *
   * @param index The given index to switch to
   * @param transactionOptions Transaction options to be displayed
   * @throws IndexOutOfBoundsException Thrown if trying to switch to an index outside given range
   */
  public void switchTab(@TabIndex int index, @Nullable
      FragmentNavigationTransactionOptions transactionOptions)
      throws IndexOutOfBoundsException {
    //Check to make sure the tab is within range
    if (index >= mfragmentStacks.size()) {
      throw new IndexOutOfBoundsException("Can't switch to a tab that hasn't been initialized, "
          + "Index : " + index + ", current stack size : " + mfragmentStacks.size()
          + ". Make sure to create all of the tabs you need in the Constructor or "
          + "provide a way for them to be created via RootFragmentListener.");
    }
    if (mselectedTabIndex != index) {
      mselectedTabIndex = index;

      FragmentTransaction ft = createTransactionWithOptions(transactionOptions);
      ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

      detachCurrentFragment(ft);

      Fragment fragment = null;
      if (index == NO_TAB) {
        ft.commit();
      } else {
        //Attempt to reattach previous fragment
        fragment = reattachPreviousFragment(ft);
        if (fragment != null) {
          ft.commit();
        } else {
          fragment = getRootFragment(mselectedTabIndex);
          ft.add(mcontainerId, fragment, generateTag(fragment));
          ft.commit();
        }
      }

      executePendingTransactions();

      mcurrentFrag = fragment;
      if (mtransactionListener != null) {
        mtransactionListener.onTabTransaction(mcurrentFrag, mselectedTabIndex);
      }
    }
  }

  /**
   * Function used to switch to the specified fragment stack.
   *
   * @param index The given index to switch to
   * @throws IndexOutOfBoundsException Thrown if trying to switch to an index outside given range
   */
  public void switchTab(@TabIndex int index) throws IndexOutOfBoundsException {
    switchTab(index, null);
  }

  /**
   * Push a fragment onto the current stack.
   *
   * @param fragment The fragment that is to be pushed
   * @param transactionOptions Transaction options to be displayed
   */
  public void pushFragment(@Nullable Fragment fragment,
      @Nullable FragmentNavigationTransactionOptions transactionOptions) {
    if (fragment != null && mselectedTabIndex != NO_TAB) {
      FragmentTransaction fragmentTransaction = createTransactionWithOptions(transactionOptions);

      detachCurrentFragment(fragmentTransaction);
      fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
      fragmentTransaction.add(mcontainerId, fragment, generateTag(fragment));
      fragmentTransaction.commit();

      executePendingTransactions();

      mfragmentStacks.get(mselectedTabIndex).push(fragment);

      mcurrentFrag = fragment;
      if (mtransactionListener != null) {
        mtransactionListener.onFragmentTransaction(mcurrentFrag, TransactionType.PUSH);
      }
    }
  }

  /**
   * Push a fragment onto the current stack.
   *
   * @param fragment The fragment that is to be pushed
   */
  public void pushFragment(@Nullable Fragment fragment) {
    pushFragment(fragment, null);
  }

  /**
   * Pop the current fragment from the current tab.
   *
   * @param transactionOptions Transaction options to be displayed
   */
  public void popFragment(@Nullable FragmentNavigationTransactionOptions transactionOptions)
      throws UnsupportedOperationException {
    popFragments(1, transactionOptions);
  }

  /**
   * Pop the current fragment from the current tab.
   */
  public void popFragment() throws UnsupportedOperationException {
    popFragment(null);
  }

  /**
   * Pop the current stack until a given tag is found. If the tag is not found, the stack will
   * popFragment until it is at the root fragment.
   *
   * @param transactionOptions Transaction options to be displayed
   */
  public void popFragments(int popDepth,
      @Nullable FragmentNavigationTransactionOptions transactionOptions)
      throws UnsupportedOperationException {
    if (isRootFragment()) {
      throw new UnsupportedOperationException(
          "You can not popFragment the rootFragment. "
              + "If you need to change this fragment, use replaceFragment(fragment)");
    } else if (popDepth < 1) {
      throw new UnsupportedOperationException("popFragments parameter needs to be greater than 0");
    } else if (mselectedTabIndex == NO_TAB) {
      throw new UnsupportedOperationException("You can not pop fragments when no tab is selected");
    }

    //If our popDepth is big enough that it would just clear the stack, then call that.
    if (popDepth >= mfragmentStacks.get(mselectedTabIndex).size() - 1) {
      clearStack(transactionOptions);
      return;
    }

    Fragment fragment;
    FragmentTransaction ft = createTransactionWithOptions(transactionOptions);

    //Pop the number of the fragments on the stack and remove them from the FragmentManager
    for (int i = 0; i < popDepth; i++) {
      fragment = mfragmentManager
          .findFragmentByTag(mfragmentStacks.get(mselectedTabIndex).pop().getTag());
      if (fragment != null) {
        ft.remove(fragment);
      }
    }

    //Attempt to reattach previous fragment
    fragment = reattachPreviousFragment(ft);

    boolean bshouldPush = false;
    //If we can't reattach, either pull from the stack, or create a new root fragment
    if (fragment != null) {
      ft.commit();
    } else {
      if (!mfragmentStacks.get(mselectedTabIndex).isEmpty()) {
        fragment = mfragmentStacks.get(mselectedTabIndex).peek();
        ft.add(mcontainerId, fragment, fragment.getTag());
        ft.commit();
      } else {
        fragment = getRootFragment(mselectedTabIndex);
        ft.add(mcontainerId, fragment, generateTag(fragment));
        ft.commit();

        bshouldPush = true;
      }
    }

    executePendingTransactions();

    //Need to have this down here so that that tag has been
    // committed to the fragment before we add to the stack
    if (bshouldPush) {
      mfragmentStacks.get(mselectedTabIndex).push(fragment);
    }

    mcurrentFrag = fragment;
    if (mtransactionListener != null) {
      mtransactionListener.onFragmentTransaction(mcurrentFrag, TransactionType.POP);
    }
  }

  /**
   * Pop the current fragment from the current tab.
   */
  public void popFragments(int popDepth) throws UnsupportedOperationException {
    popFragments(popDepth, null);
  }

  /**
   * Clears the current tab's stack to get to just the bottom Fragment. This will reveal the root
   * fragment
   *
   * @param transactionOptions Transaction options to be displayed
   */
  public void clearStack(@Nullable FragmentNavigationTransactionOptions transactionOptions) {
    if (mselectedTabIndex == NO_TAB) {
      return;
    }

    //Grab Current stack
    Stack<Fragment> fragmentStack = mfragmentStacks.get(mselectedTabIndex);

    // Only need to start popping and reattach if the stack is greater than 1
    if (fragmentStack.size() > 1) {
      Fragment fragment;
      FragmentTransaction ft = createTransactionWithOptions(transactionOptions);

      //Pop all of the fragments on the stack and remove them from the FragmentManager
      while (fragmentStack.size() > 1) {
        fragment = mfragmentManager.findFragmentByTag(fragmentStack.pop().getTag());
        if (fragment != null) {
          ft.remove(fragment);
        }
      }

      //Attempt to reattach previous fragment
      fragment = reattachPreviousFragment(ft);

      boolean bshouldPush = false;
      //If we can't reattach, either pull from the stack, or create a new root fragment
      if (fragment != null) {
        ft.commit();
      } else {
        if (!fragmentStack.isEmpty()) {
          fragment = fragmentStack.peek();
          ft.add(mcontainerId, fragment, fragment.getTag());
          ft.commit();
        } else {
          fragment = getRootFragment(mselectedTabIndex);
          ft.add(mcontainerId, fragment, generateTag(fragment));
          ft.commit();

          bshouldPush = true;
        }
      }

      executePendingTransactions();

      if (bshouldPush) {
        mfragmentStacks.get(mselectedTabIndex).push(fragment);
      }

      //Update the stored version we have in the list
      mfragmentStacks.set(mselectedTabIndex, fragmentStack);

      mcurrentFrag = fragment;
      if (mtransactionListener != null) {
        mtransactionListener.onFragmentTransaction(mcurrentFrag, TransactionType.POP);
      }
    }
  }

  /**
   * Clears the current tab's stack to get to just the bottom Fragment. This will reveal the root
   * fragment.
   */
  public void clearStack() {
    clearStack(null);
  }

  /**
   * Replace the current fragment.
   *
   * @param fragment the fragment to be shown instead
   * @param transactionOptions Transaction options to be displayed
   */
  public void replaceFragment(@NonNull Fragment fragment,
      @Nullable FragmentNavigationTransactionOptions transactionOptions) {
    Fragment poppingFrag = getCurrentFrag();

    if (poppingFrag != null) {
      FragmentTransaction ft = createTransactionWithOptions(transactionOptions);

      //overly cautious fragment popFragment
      Stack<Fragment> fragmentStack = mfragmentStacks.get(mselectedTabIndex);
      if (!fragmentStack.isEmpty()) {
        fragmentStack.pop();
      }

      String tag = generateTag(fragment);
      ft.replace(mcontainerId, fragment, tag);

      //Commit our transactions
      ft.commit();

      executePendingTransactions();

      fragmentStack.push(fragment);
      mcurrentFrag = fragment;

      if (mtransactionListener != null) {
        mtransactionListener.onFragmentTransaction(mcurrentFrag, TransactionType.REPLACE);

      }
    }
  }

  /**
   * Replace the current fragment.
   *
   * @param fragment the fragment to be shown instead
   */
  public void replaceFragment(@NonNull Fragment fragment) {
    replaceFragment(fragment, null);
  }

  /**
   * Current DialogFragment being displayed. Null if none.
   *
   * @return it will return Dialog fragment
   */
  @Nullable
  @CheckResult
  public DialogFragment getCurrentDialogFrag() {
    if (mcurrentDialogFrag != null) {
      return mcurrentDialogFrag;
    } else {
      //Else try to find one in the FragmentManager
      FragmentManager fragmentManager;
      if (mcurrentFrag != null) {
        fragmentManager = mcurrentFrag.getChildFragmentManager();
      } else {
        fragmentManager = mfragmentManager;
      }
      if (fragmentManager.getFragments() != null) {
        for (Fragment fragment : fragmentManager.getFragments()) {
          if (fragment instanceof DialogFragment) {
            mcurrentDialogFrag = (DialogFragment) fragment;
            break;
          }
        }
      }
    }
    return mcurrentDialogFrag;
  }

  /**
   * Clear any DialogFragments that may be shown.
   */
  public void clearDialogFragment() {
    if (mcurrentDialogFrag != null) {
      mcurrentDialogFrag.dismiss();
      mcurrentDialogFrag = null;
    } else {
      // If we don't have the current dialog, try to find and dismiss it
      FragmentManager fragmentManager;
      if (mcurrentFrag != null) {
        fragmentManager = mcurrentFrag.getChildFragmentManager();
      } else {
        fragmentManager = mfragmentManager;
      }

      if (fragmentManager.getFragments() != null) {
        for (Fragment fragment : fragmentManager.getFragments()) {
          if (fragment instanceof DialogFragment) {
            ((DialogFragment) fragment).dismiss();
          }
        }
      }
    }
  }

  /**
   * Display a DialogFragment on the screen.
   *
   * @param dialogFragment The Fragment to be Displayed
   */
  public void showDialogFragment(@Nullable DialogFragment dialogFragment) {
    if (dialogFragment != null) {
      FragmentManager fragmentManager;
      if (mcurrentFrag != null) {
        fragmentManager = mcurrentFrag.getChildFragmentManager();
      } else {
        fragmentManager = mfragmentManager;
      }

      //Clear any current dialog fragments
      if (fragmentManager.getFragments() != null) {
        for (Fragment fragment : fragmentManager.getFragments()) {
          if (fragment instanceof DialogFragment) {
            ((DialogFragment) fragment).dismiss();
            mcurrentDialogFrag = null;
          }
        }
      }

      mcurrentDialogFrag = dialogFragment;
      try {
        dialogFragment.show(fragmentManager, dialogFragment.getClass().getName());
      } catch (IllegalStateException e) {
        // Activity was likely destroyed before we had a chance to show, nothing can be done here.
      }
    }
  }

  //endregion

  //region Private helper functions

  /**
   * Helper function to make sure that we are starting with a clean slate and to perform our first
   * fragment interaction.
   *
   * @param index the tab index to initialize to
   */
  private void initialize(@TabIndex int index) {
    mselectedTabIndex = index;
    if (mselectedTabIndex > mfragmentStacks.size()) {
      throw new IndexOutOfBoundsException("Starting index cannot "
          + "be larger than the number of stacks");
    }

    mselectedTabIndex = index;
    clearFragmentManager();
    clearDialogFragment();

    if (index == NO_TAB) {
      return;
    }

    FragmentTransaction ft = createTransactionWithOptions(null);

    Fragment fragment = getRootFragment(index);
    ft.add(mcontainerId, fragment, generateTag(fragment));
    ft.commit();

    executePendingTransactions();

    mcurrentFrag = fragment;
    if (mtransactionListener != null) {
      mtransactionListener.onTabTransaction(mcurrentFrag, mselectedTabIndex);
    }
  }

  /**
   * Helper function to get the root fragment for a given index. This is done by either passing them
   * in the constructor, or dynamically via NavListener.
   *
   * @param index The tab index to get this fragment from
   * @return The root fragment at this index
   * @throws IllegalStateException This will be thrown if we can't find a rootFragment for this
   * index. Either because you didn't provide it in the constructor, or because your
   * RootFragmentListener. getRootFragment(index) isn't returning a fragment for this index.
   */
  @NonNull
  @CheckResult
  private Fragment getRootFragment(int index) throws IllegalStateException {
    Fragment fragment = null;
    if (!mfragmentStacks.get(index).isEmpty()) {
      fragment = mfragmentStacks.get(index).peek();
    } else if (mrootFragmentListener != null) {
      fragment = mrootFragmentListener.getRootFragment(index);

      if (mselectedTabIndex != NO_TAB) {
        mfragmentStacks.get(mselectedTabIndex).push(fragment);
      }

    }
    if (fragment == null) {
      throw new IllegalStateException(
          "Either you haven't past in a fragment at this index"
              + " in your constructor, or you haven't "
              + "provided a way to create it while via your "
              + "RootFragmentListener.getRootFragment(index)");
    }

    return fragment;
  }

  /**
   * Will attempt to reattach a previous fragment in the FragmentManager, or return null if not able
   * to.
   *
   * @param ft current fragment transaction
   * @return Fragment if we were able to find and reattach it
   */
  @Nullable
  private Fragment reattachPreviousFragment(@NonNull FragmentTransaction ft) {
    Stack<Fragment> fragmentStack = mfragmentStacks.get(mselectedTabIndex);
    Fragment fragment = null;
    if (!fragmentStack.isEmpty()) {
      fragment = mfragmentManager.findFragmentByTag(fragmentStack.peek().getTag());
      if (fragment != null) {
        ft.attach(fragment);
      }
    }
    return fragment;
  }

  /**
   * Attempts to detach any current fragment if it exists, and if none is found, returns.
   *
   * @param ft the current transaction being performed
   */
  private void detachCurrentFragment(@NonNull FragmentTransaction ft) {
    Fragment oldFrag = getCurrentFrag();
    if (oldFrag != null) {
      ft.detach(oldFrag);
    }
  }

  /**
   * Helper function to attempt to get current fragment.
   *
   * @return Fragment the current frag to be returned
   */
  @Nullable
  @CheckResult
  public Fragment getCurrentFrag() {
    //Attempt to used stored current fragment
    if (mcurrentFrag != null) {
      return mcurrentFrag;
    } else if (mselectedTabIndex == NO_TAB) {
      return null;
    } else {
      //if not, try to pull it from the stack
      Stack<Fragment> fragmentStack = mfragmentStacks.get(mselectedTabIndex);
      if (!fragmentStack.isEmpty()) {
        mcurrentFrag = mfragmentManager.findFragmentByTag(mfragmentStacks
            .get(mselectedTabIndex).peek().getTag());
      }
    }
    return mcurrentFrag;
  }

  /**
   * Create a unique fragment tag so that we can grab the fragment later from the FragmentManger.
   *
   * @param fragment The fragment that we're creating a unique tag for
   * @return a unique tag using the fragment's class name
   */
  @NonNull
  @CheckResult
  private String generateTag(@NonNull Fragment fragment) {
    return fragment.getClass().getName() + ++mtagCount;
  }

  /**
   * This check is here to prevent recursive entries into executePendingTransactions.
   */
  private void executePendingTransactions() {
    if (!mexecutingTransaction) {
      mexecutingTransaction = true;
      mfragmentManager.executePendingTransactions();
      mexecutingTransaction = false;
    }
  }

  /**
   * Private helper function to clear out the fragment manager on initialization. All fragment
   * management should be done via FragNav.
   */
  private void clearFragmentManager() {
    if (mfragmentManager.getFragments() != null) {
      FragmentTransaction ft = createTransactionWithOptions(null);
      for (Fragment fragment : mfragmentManager.getFragments()) {
        if (fragment != null) {
          ft.remove(fragment);
        }
      }
      ft.commit();
      executePendingTransactions();
    }
  }

  /**
   * Setup a fragment transaction with the given option.
   *
   * @param transactionOptions The options that will be set for this transaction
   */
  @CheckResult
  private FragmentTransaction createTransactionWithOptions(
      @Nullable FragmentNavigationTransactionOptions transactionOptions) {
    FragmentTransaction ft = mfragmentManager.beginTransaction();
    if (transactionOptions == null) {
      transactionOptions = mdefaultTransactionOptions;
    }
    if (transactionOptions != null) {

      ft.setCustomAnimations(transactionOptions.enterAnimation, transactionOptions.exitAnimation,
          transactionOptions.popEnterAnimation, transactionOptions.popExitAnimation);
      ft.setTransitionStyle(transactionOptions.transitionStyle);

      ft.setTransition(transactionOptions.transition);

      if (transactionOptions.sharedElements != null) {
        for (Pair<View, String> sharedElement : transactionOptions.sharedElements) {
          ft.addSharedElement(sharedElement.first, sharedElement.second);
        }
      }

      if (transactionOptions.breadCrumbTitle != null) {
        ft.setBreadCrumbTitle(transactionOptions.breadCrumbTitle);
      }

      if (transactionOptions.breadCrumbShortTitle != null) {
        ft.setBreadCrumbShortTitle(transactionOptions.breadCrumbShortTitle);

      }
    }
    return ft;
  }

  //endregion

  //region Public helper functions

  /**
   * Get the number of fragment stacks.
   *
   * @return the number of fragment stacks
   */
  @CheckResult
  public int getSize() {
    return mfragmentStacks.size();
  }


  /**
   * Get a copy of the stack at a given index.
   *
   * @return requested stack
   */
  @SuppressWarnings("unchecked")
  @CheckResult
  @Nullable
  public Stack<Fragment> getStack(@TabIndex int index) {
    if (index == NO_TAB) {
      return null;
    }
    if (index >= mfragmentStacks.size()) {
      throw new IndexOutOfBoundsException("Can't get an index that's larger than we've setup");
    }
    return (Stack<Fragment>) mfragmentStacks.get(index).clone();
  }


  /**
   * Get a copy of the current stack that is being displayed.
   *
   * @return Current stack
   */
  @SuppressWarnings("unchecked")
  @CheckResult
  @Nullable
  public Stack<Fragment> getCurrentStack() {
    return getStack(mselectedTabIndex);
  }

  /**
   * Get the index of the current stack that is being displayed.
   *
   * @return Current stack index
   */
  @CheckResult
  @TabIndex
  public int getCurrentStackIndex() {
    return mselectedTabIndex;
  }


  /**
   * If true, you are at the bottom of the stack. (Consider using replaceFragment if you need to
   * change the root fragment for some reason) else you can popFragment as needed as your are not at
   * the root.
   *
   * @return boolean  value
   */
  @CheckResult
  public boolean isRootFragment() {
    Stack<Fragment> stack = getCurrentStack();

    return stack == null || stack.size() == 1;
  }

  //endregion

  //region SavedInstanceState

  /**
   * Call this in your Activity's onSaveInstanceState(Bundle outState) method to save the instance's
   * state.
   *
   * @param outState The Bundle to save state information to
   */
  public void onSaveInstanceState(@NonNull Bundle outState) {

    // Write tag count
    outState.putInt(EXTRA_TAG_COUNT, mtagCount);

    // Write select tab
    outState.putInt(EXTRA_SELECTED_TAB_INDEX, mselectedTabIndex);

    // Write current fragment
    if (mcurrentFrag != null) {
      outState.putString(EXTRA_CURRENT_FRAGMENT, mcurrentFrag.getTag());
    }

    // Write stacks
    try {
      final JSONArray stackArrays = new JSONArray();

      for (Stack<Fragment> stack : mfragmentStacks) {
        final JSONArray stackArray = new JSONArray();

        for (Fragment fragment : stack) {
          stackArray.put(fragment.getTag());
        }

        stackArrays.put(stackArray);
      }

      outState.putString(EXTRA_FRAGMENT_STACK, stackArrays.toString());
    } catch (Throwable t) {
      // Nothing we can do
    }
  }

  /**
   * Restores this instance to the state specified by the contents of savedInstanceState.
   *
   * @param savedInstanceState The bundle to restore from
   * @param rootFragments List of root fragments from which to initialize empty stacks. If null,
   * pull fragments from RootFragmentListener.
   * @return true if successful, false if not
   */
  private boolean restoreFromBundle(@Nullable Bundle savedInstanceState,
      @Nullable List<Fragment> rootFragments) {
    if (savedInstanceState == null) {
      return false;
    }

    // Restore tag count
    mtagCount = savedInstanceState.getInt(EXTRA_TAG_COUNT, 0);

    // Restore current fragment
    mcurrentFrag = mfragmentManager.findFragmentByTag(savedInstanceState
        .getString(EXTRA_CURRENT_FRAGMENT));

    // Restore fragment stacks
    try {
      final JSONArray stackArrays = new JSONArray(savedInstanceState
          .getString(EXTRA_FRAGMENT_STACK));

      for (int x = 0; x < stackArrays.length(); x++) {
        final JSONArray stackArray = stackArrays.getJSONArray(x);
        final Stack<Fragment> stack = new Stack<>();

        if (stackArray.length() == 1) {
          final String tag = stackArray.getString(0);
          final Fragment fragment;

          if (tag == null || "null".equalsIgnoreCase(tag)) {
            if (rootFragments != null) {
              fragment = rootFragments.get(x);
            } else {
              fragment = getRootFragment(x);
            }

          } else {
            fragment = mfragmentManager.findFragmentByTag(tag);
          }

          if (fragment != null) {
            stack.add(fragment);
          }
        } else {
          for (int y = 0; y < stackArray.length(); y++) {
            final String tag = stackArray.getString(y);

            if (tag != null && !"null".equalsIgnoreCase(tag)) {
              final Fragment fragment = mfragmentManager.findFragmentByTag(tag);

              if (fragment != null) {
                stack.add(fragment);
              }
            }
          }
        }

        mfragmentStacks.add(stack);
      }
      // Restore selected tab if we have one
      switch (savedInstanceState.getInt(EXTRA_SELECTED_TAB_INDEX)) {
        case TAB1:
          switchTab(TAB1);
          break;
        case TAB2:
          switchTab(TAB2);
          break;
        case TAB3:
          switchTab(TAB3);
          break;
        case TAB4:
          switchTab(TAB4);
          break;
        case TAB5:
          switchTab(TAB5);
          break;
        default:
          break;
      }

      //Successfully restored state
      return true;
    } catch (Throwable t) {
      return false;
    }
  }
  //endregion

  public enum TransactionType {
    PUSH,
    POP,
    REPLACE
  }

  //Declare the TabIndex annotation
  @IntDef({NO_TAB, TAB1, TAB2, TAB3, TAB4, TAB5})
  @Retention(RetentionPolicy.SOURCE)
  public @interface TabIndex {

  }

  // Declare Transit Styles
  @IntDef({FragmentTransaction.TRANSIT_NONE, FragmentTransaction.TRANSIT_FRAGMENT_OPEN,
      FragmentTransaction.TRANSIT_FRAGMENT_CLOSE, FragmentTransaction.TRANSIT_FRAGMENT_FADE})
  @Retention(RetentionPolicy.SOURCE)
  @interface Transit {

  }

  /**
   * RootFragmentListener interface.
   */
  public interface RootFragmentListener {

    /**
     * Dynamically create the Fragment that will go on the bottom of the stack.
     *
     * @param index the index that the root of the stack Fragment needs to go
     * @return the new Fragment
     */
    Fragment getRootFragment(int index);
  }

  /**
   * TransactionListener interface.
   */
  public interface TransactionListener {

    void onTabTransaction(Fragment fragment, int index);

    void onFragmentTransaction(Fragment fragment, TransactionType transactionType);
  }

  /**
   * Builder class.
   */
  public static final class Builder {

    private final int mcontainerId;
    private FragmentManager mfragmentManager;
    private RootFragmentListener mrootFragmentListener;
    @TabIndex
    private int mselectedTabIndex = TAB1;
    private TransactionListener mtransactionListener;
    private FragmentNavigationTransactionOptions mdefaultTransactionOptions;
    private int mnumberOfTabs = 0;
    private List<Fragment> mrootFragments;
    private Bundle msavedInstanceState;


    /**
     * Builder constructor.
     * @param savedInstanceState bundle
     * @param mfragmentManager fragmentManager
     * @param mcontainerId integer containerId
     */
    Builder(@Nullable Bundle savedInstanceState,
        FragmentManager mfragmentManager, int mcontainerId) {
      this.msavedInstanceState = savedInstanceState;
      this.mfragmentManager = mfragmentManager;
      this.mcontainerId = mcontainerId;
    }

    /**
     * The initial tab index to be used must be in range of rootFragments size.
     *
     * @param selectedTabIndex position
     */
    public Builder selectedTabIndex(@TabIndex int selectedTabIndex) {
      mselectedTabIndex = selectedTabIndex;
      if (mrootFragments != null && mselectedTabIndex > mnumberOfTabs) {
        throw new IndexOutOfBoundsException("Starting index cannot be"
            + " larger than the number of stacks");
      }
      return this;
    }

    /**
     * A single root fragment. This library can still be helpful. when managing a single stack of
     * fragments.
     *
     * @param rootFragment fragment object
     */
    public Builder rootFragment(Fragment rootFragment) {
      mrootFragments = new ArrayList<>(1);
      mrootFragments.add(rootFragment);
      mnumberOfTabs = 1;
      return rootFragments(mrootFragments);
    }

    /**
     * root Fragments are the root fragments that exist on any tab structure. If only one fragment
     * is sent in, fragnav will still manage transactions.
     *
     * @param rootFragments a list of root fragments.
     */
    public Builder rootFragments(@NonNull List<Fragment> rootFragments) {
      mrootFragments = rootFragments;
      mnumberOfTabs = rootFragments.size();
      if (mnumberOfTabs > MAX_NUM_TABS) {
        throw new IllegalArgumentException("Number of root fragments "
            + "cannot be greater than " + MAX_NUM_TABS);
      }
      return this;
    }

    /**
     * The default transaction options to be used unless otherwise defined.
     *
     * @param transactionOptions its return transactionOption of fragment navigation
     */
    public Builder defaultTransactionOptions(
        @NonNull FragmentNavigationTransactionOptions transactionOptions) {
      mdefaultTransactionOptions = transactionOptions;
      return this;
    }


    /**
     * This listner is initialised first time from the home class.
     *
     * @param rootFragmentListener a listener that allows for dynamically creating root fragments
     * @param numberOfTabs the number of tabs that will be switched between
     */
    public Builder rootFragmentListener(RootFragmentListener rootFragmentListener,
        int numberOfTabs) {
      mrootFragmentListener = rootFragmentListener;
      mnumberOfTabs = numberOfTabs;
      if (mnumberOfTabs > MAX_NUM_TABS) {
        throw new IllegalArgumentException("Number of tabs cannot be greater than " + MAX_NUM_TABS);
      }
      return this;
    }

    /**
     * A listener to be implemented (typically within the main activity). to fragment transactions
     * (including tab switches)
     *
     * @param val initialising listener with current class object
     */
    public Builder transactionListener(TransactionListener val) {
      mtransactionListener = val;
      return this;
    }

    /**
     * Returns FragmentNavigationController object.
     * @return FragmentNavigationController
     */
    public FragmentNavigationController build() {
      if (mrootFragmentListener == null && mrootFragments == null) {
        throw new IndexOutOfBoundsException("Either a root fragment(s) needs "
            + "to be set, or a fragment listener");
      }
      return new FragmentNavigationController(this, msavedInstanceState);
    }
  }
}
