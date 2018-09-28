/*
 *  Created by Emaar Hospitality Group on 25/8/18 11:05 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 24/8/18 6:00 PM
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

package com.ehg.home;

import static android.graphics.drawable.ClipDrawable.HORIZONTAL;

import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.fragment.BookingFragment;
import com.ehg.home.BaseActivity.BroadCastMessageInterface;
import com.ehg.home.adapter.ShowMoreListAdapter;
import com.ehg.home.adapter.ShowMoreListAdapter.OnShowMoreListItemClickListener;
import com.ehg.home.fragment.BaseFragment;
import com.ehg.home.fragment.HomeFragment;
import com.ehg.home.navigation.FragmentNavigationController;
import com.ehg.home.navigation.FragmentNavigationController.TransactionType;
import com.ehg.maps.MapsActivity;
import com.ehg.offers.fragment.OffersFragment;
import com.ehg.reservations.fragment.ReservationsFragment;
import com.ehg.settings.SettingsActivity;
import com.ehg.socialmedia.SocialMediaActivity;
import com.ehg.ubyemaar.fragment.UbyEmaarFragment;
import com.ehg.utilities.AppPermissionCheckerUtil;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.FragmentHistoryUtil;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class contains contextual dynamic data and shows tabs at bottom.
 */

public class HomeActivity extends BaseActivity implements BaseFragment.FragmentNavigation,
    FragmentNavigationController.TransactionListener,
    FragmentNavigationController.RootFragmentListener,
    TabLayout.OnTabSelectedListener, View.OnClickListener,
    OnShowMoreListItemClickListener, BroadCastMessageInterface {


  private int[] mtabIconsSelected = {
      R.drawable.tab_home,
      R.drawable.tab_search,
      R.drawable.tab_share,
      R.drawable.tab_news,
      R.drawable.tab_profile,
  };

  private TabLayout bottomTabLayout;

  private String[] tabs = {"Home", "Book", "Reservations", "Offers", "U By Emaar"};

  private Toolbar toolbar;

  private FragmentHistoryUtil fragmentHistory;
  private FragmentNavigationController mnavController;

  private AppCompatImageView headerBackButton;

  private TextView headerTextView;
  private ImageView imageViewSettings;

  private Context context;

  private PopupWindow popupWindow;

  /**
   * Called when activity created.
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {

      setContentView(R.layout.activity_home);

      context = this;

      //Register broadcast message interface for HomeActivity
      setBroadCastMessageInterface(this);

      initView();

      //initToolbar();

      initTab();

      //registering listeners
      bottomTabLayout.addOnTabSelectedListener(this);
      headerBackButton.setOnClickListener(this);
      imageViewSettings.setOnClickListener(this);

      //Initialing "FragmentHistoryUtil" class.
      fragmentHistory = new FragmentHistoryUtil();

      //Initialing "FragmentNavigationController" with required parameter.
      mnavController = FragmentNavigationController
          .newBuilder(savedInstanceState, getSupportFragmentManager(),
              R.id.framelayout_home_fragment_container)
          .transactionListener(this)
          .rootFragmentListener(this, tabs.length)
          .build();

      switchTab(0);

      //Check for app location permissions
      if (AppPermissionCheckerUtil.checkAppPermission(this,
          new String[]{permission.ACCESS_FINE_LOCATION})) {
        //TODO: Need to implement
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception n) {
      n.printStackTrace();
    }
  }

  /**
   * initialising View component of the Home Activity.
   */
  private void initView() {
    bottomTabLayout = findViewById(R.id.bottom_tab_layout);
    headerBackButton = findViewById(R.id.imageview_header_back);
    headerTextView = findViewById(R.id.textview_header_title);
    imageViewSettings = findViewById(R.id.imageview_home_showmore);
    //toolbar = findViewById(R.id.toolbar);
    //headerBackButton.setVisibility(View.INVISIBLE);
    setBackArrowRtl(headerBackButton);
  }

  /**
   * Called to update back arrow rtl icons.
   *
   * @param appCompatImageView imageview object
   */
  @Override
  public void setBackArrowRtl(AppCompatImageView appCompatImageView) {
    super.setBackArrowRtl(appCompatImageView);
  }

  /**
   * Initialising toolbar.
   */
  private void initToolbar() {
    setSupportActionBar(toolbar);
  }

  /**
   * Initialising tablayout.
   */
  private void initTab() {
    if (bottomTabLayout != null) {
      for (int i = 0; i < tabs.length; i++) {
        bottomTabLayout.addTab(bottomTabLayout.newTab());
        Tab tab = bottomTabLayout.getTabAt(i);
        if (tab != null) {
          tab.setCustomView(getTabView(i));
        }
      }
    }
  }

  /**
   * This method will use to inflate a customview to the tablayout.
   *
   * @param position position of the tablayout
   * @return it will return view
   */
  private View getTabView(int position) {
    View view = LayoutInflater.from(HomeActivity.this)
        .inflate(R.layout.tab_item_bottom, null);
    ImageView icon = view.findViewById(R.id.tab_icon);
    icon.setImageDrawable(
        AppUtil.setDrawableSelector(HomeActivity.this,
            mtabIconsSelected[position], mtabIconsSelected[position]));
    return view;
  }

  /**
   * we are saving instance state of activity.
   *
   * @param outState state information
   */
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (mnavController != null) {
      mnavController.onSaveInstanceState(outState);
    }
  }

  /**
   * This method will use to save fragment into stack.
   *
   * @param fragment instance of fragment
   */
  @Override
  public void pushFragment(Fragment fragment) {

    if (mnavController != null) {
      mnavController.pushFragment(fragment);
    }
  }

  /**
   * Called to return root fragment object.
   *
   * @param index the index that the root of the stack Fragment needs to go
   */
  @Override
  public Fragment getRootFragment(int index) {

    switch (index) {

      case FragmentNavigationController.TAB1:
        return new HomeFragment();
      case FragmentNavigationController.TAB2:
        return new BookingFragment();
      case FragmentNavigationController.TAB3:
        return new ReservationsFragment();
      case FragmentNavigationController.TAB4:
        return new OffersFragment();
      case FragmentNavigationController.TAB5:
        return new UbyEmaarFragment();

      default:
    }
    throw new IllegalStateException("Need to send an index that we know");
  }

  /**
   * Method opens showMorePopupWindow and allows user to choose any option.
   */
  private void showMorePopUpWindow() {

    //Prepare show more options list
    ArrayList<String> showMoreOptionsList = new ArrayList<>();
    showMoreOptionsList.add("Settings");
    showMoreOptionsList.add("Share");
    showMoreOptionsList.add("Location");
    // We need to get the instance of the LayoutInflater

    LayoutInflater inflater = (LayoutInflater)
        HomeActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View layout = Objects.requireNonNull(inflater).inflate(R.layout.view_showmore,
        (ViewGroup) findViewById(R.id.liearlayout_id_showmore));

    popupWindow = new PopupWindow(layout, AppUtil.getDeviceWidth(this) / 3,
        LayoutParams.WRAP_CONTENT, true);

    RecyclerView recyclerViewShowMore = layout.findViewById(R.id.recyclerview_showmore_list);
    DividerItemDecoration itemDecor = new DividerItemDecoration(context, HORIZONTAL);
    recyclerViewShowMore.addItemDecoration(itemDecor);

    recyclerViewShowMore.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewShowMore.setHasFixedSize(true);
    recyclerViewShowMore.setAdapter(new ShowMoreListAdapter(context, this, showMoreOptionsList));

    popupWindow.setBackgroundDrawable(new BitmapDrawable());
    popupWindow.setOutsideTouchable(true);
    popupWindow.setAnimationStyle(R.style.popup_window_animation);

    //Get location of show more image on screen
    int[] location = new int[2];
    imageViewSettings.getLocationOnScreen(location);

    int screenHeight = AppUtil.getDeviceHeight(this);
    int yposition = screenHeight - location[1];

    popupWindow.showAtLocation(imageViewSettings, Gravity.END, 10,
        (yposition * 4) - 10);
  }

  /**
   * Show more list item listener callback.
   */
  @Override
  public void onShowMoreListItemClicked(String optionSelected) {

    //Dismiss popup window
    if (popupWindow != null) {
      popupWindow.dismiss();
    }

    //Open selected option
    Intent intent = null;

    switch (optionSelected) {

      case "Settings":
        intent = new Intent(context, SettingsActivity.class);
        break;

      case "Share":
        intent = new Intent(context, SocialMediaActivity.class);
        break;

      case "Location":
        //Check for app permissions
        if (AppPermissionCheckerUtil.checkAppPermission(this,
            new String[]{permission.ACCESS_FINE_LOCATION})) {

          //Check weather location service active,
          // if not then show location request dialog to turn it on.
          if (AppUtil.displayLocationSettingsRequest(this)) {
            intent = new Intent(context, MapsActivity.class);
          }
        }
        break;

      default:
        break;
    }

    if (intent != null) {
      AppUtil.startActivityWithAnimation(this, intent, false);
    }
  }

  /**
   * Called when tab transaction initiated.
   * @param fragment fragment object
   * @param index index
   */
  @Override
  public void onTabTransaction(Fragment fragment, int index) {

    // If we have a backstack, show the back button
    if (mnavController != null) {

      //  updateToolbar();
      updateHeader();

    }
  }

  /**
   * Method updates home screen header.
   */
  private void updateHeader() {

    /* if (!mnavController.isRootFragment()) {

      headerBackButton.setVisibility(View.VISIBLE);
    } else {

      headerBackButton.setVisibility(View.INVISIBLE);
    }*/
    headerBackButton.setVisibility(View.VISIBLE);
  }

  /**
   * Method updates toolbar.
   */
  private void updateToolbar() {
    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      Objects.requireNonNull(getSupportActionBar())
          .setDisplayHomeAsUpEnabled(!mnavController.isRootFragment());
    }
    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      Objects.requireNonNull(getSupportActionBar())
          .setDisplayShowHomeEnabled(!mnavController.isRootFragment());
    }
    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      Objects.requireNonNull(getSupportActionBar())
          .setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }
  }

  /**
   * Called when back stack pressed on fragment.
   * @param fragment object
   * @param transactionType transaction type
   */
  @Override
  public void onFragmentTransaction(Fragment fragment, TransactionType transactionType) {

    // If we have a backstack, show the back button
    if (mnavController != null) {

      //updateToolbar();

      updateHeader();
    }
  }

  /**
   * Called when activity started.
   */
  @Override
  protected void onStart() {
    super.onStart();
  }

  /**
   * Called when activity stopped.
   */
  @Override
  protected void onStop() {
    super.onStop();
  }

  /**
   * Called when tab switched.
   * @param position integer position
   */
  private void switchTab(int position) {
    mnavController.switchTab(position);
    /* updateToolbarTitle(position); */
  }

  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();
    setBackArrowRtl((AppCompatImageView) findViewById(R.id.imageview_header_back));
  }

  /**
   * Called when activity paused.
   */
  @Override
  protected void onPause() {
    super.onPause();
  }

  /**
   * Called when device back button pressed.
   */
  @Override
  public void onBackPressed() {

    if (!mnavController.isRootFragment()) {
      mnavController.popFragment();
    } else {

      if (fragmentHistory.isEmpty()) {
        super.onBackPressed();
      } else {

        super.onBackPressed();
        /* if (fragmentHistory.isEmpty()) {
        super.onBackPressed();
      } else {

        if (fragmentHistory.getStackSize() > 1) {

          int position = fragmentHistory.popPrevious();

          switchTab(position);

          updateTabSelection(position);

        } else {

          switchTab(0);

          updateTabSelection(0);

          fragmentHistory.emptyStack();
        }*/
      }
    }
  }

  /**
   * This method will use to update tab selection on Back button press.
   *
   * @param currentTab postion of selected tab
   */
  private void updateTabSelection(int currentTab) {

    for (int i = 0; i < tabs.length; i++) {
      Tab selectedTab = bottomTabLayout.getTabAt(i);
      if (currentTab != i) {
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
          Objects.requireNonNull(Objects.requireNonNull(selectedTab).getCustomView())
              .setSelected(false);
        }
      } else {
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
          Objects.requireNonNull(Objects.requireNonNull(selectedTab).getCustomView())
              .setSelected(true);
        }
      }
    }
  }

  /**
   * This Method will use to update title of the tool bar.
   *
   * @param title - toolbar title
   */
  public void updateToolbarTitle(String title) {

    if (!TextUtils.isEmpty(title)) {

      headerTextView.setText(title);
    }

    /*if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }*/
  }

  /**
   * Called when tab selected.
   *
   * @param tab tab
   */
  @Override
  public void onTabSelected(Tab tab) {

    fragmentHistory.push(tab.getPosition());

    switchTab(tab.getPosition());
  }

  /**
   * Called when tab unselected.
   *
   * @param tab tab
   */
  @Override
  public void onTabUnselected(Tab tab) {
  }

  /**
   * Called when tab reselected.
   *
   * @param tab tab
   */
  @Override
  public void onTabReselected(Tab tab) {

    mnavController.clearStack();

    switchTab(tab.getPosition());
  }


  /*@Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {

      case android.R.id.home:

        onBackPressed();
        return true;

      default:
    }
    return super.onOptionsItemSelected(item);
  }*/

  /**
   * Called when click event initiated.
   *
   * @param view clicked view reference
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      case R.id.imageview_home_showmore:
        //showMorePopUpWindow();
        Intent intent = new Intent(context, SettingsActivity.class);
        AppUtil.startActivityWithAnimation(this, intent, false);
        break;

      default:
        break;
    }
  }

  /**
   * OnKeyDown callback will be called when phone back key pressed.
   *
   * @param keyCode keycode
   * @param event event
   * @return return boolean value
   */
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      AppUtil.finishActivityWithAnimation(this);
    }
    return super.onKeyDown(keyCode, event);
  }

  /**
   * This will be called when location request dialog will be shown to turn on location services.
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    //TODO: Need to implement
    if (resultCode == -1) {
      Intent intent = new Intent(context, MapsActivity.class);
      startActivity(intent);
    } else {
      AppUtil.showToast(this, getResources().getString(R.string.all_permissionalert));
    }
  }

  /**
   * Callback method for BroadCastMessageInterface invoked from BaseActivity.
   *
   * @param message string data
   * @param flag boolean value
   */
  @Override
  public void onMessageReceived(String message, boolean flag) {
    if (flag) {
      //Check weather location service active,
      // if not then show location request dialog to turn it on.
      if (AppUtil.displayLocationSettingsRequest(this)) {
        Intent intent = new Intent(context, MapsActivity.class);
        AppUtil.startActivityWithAnimation(HomeActivity.this, intent, false);
      }
    } else {
      AppUtil.showToast(this, getResources().getString(R.string.all_permissionalert));
    }
  }
}
