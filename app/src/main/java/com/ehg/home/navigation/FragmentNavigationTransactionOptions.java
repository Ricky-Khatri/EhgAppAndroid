/*
 *  Created by Emaar Hospitality Group on 9/8/18 3:01 PM
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

import android.support.annotation.AnimRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * This is fragment navigation class responsible for fragment transactions.
 */

public class FragmentNavigationTransactionOptions {

  List<Pair<View, String>> sharedElements;
  @FragmentNavigationController.Transit
  int transition = FragmentTransaction.TRANSIT_NONE;
  @AnimRes
  int enterAnimation = 0;
  @AnimRes
  int exitAnimation = 0;
  @AnimRes
  int popEnterAnimation = 0;
  @AnimRes
  int popExitAnimation = 0;
  @StyleRes
  int transitionStyle = 0;
  String breadCrumbTitle;
  String breadCrumbShortTitle;

  /**
   * Constructor.
   *
   * @param builder builder
   */
  private FragmentNavigationTransactionOptions(Builder builder) {
    sharedElements = builder.sharedElements;
    transition = builder.transition;
    enterAnimation = builder.enterAnimation;
    exitAnimation = builder.exitAnimation;
    transitionStyle = builder.transitionStyle;
    popEnterAnimation = builder.popEnterAnimation;
    popExitAnimation = builder.popExitAnimation;
    breadCrumbTitle = builder.breadCrumbTitle;
    breadCrumbShortTitle = builder.breadCrumbShortTitle;
  }

  /**
   * Returns Builder class object
   */
  public static Builder newBuilder() {
    return new Builder();
  }


  public static final class Builder {

    private List<Pair<View, String>> sharedElements;
    private int transition;
    private int enterAnimation;
    private int exitAnimation;
    private int transitionStyle;
    private int popEnterAnimation;
    private int popExitAnimation;
    private String breadCrumbTitle;
    private String breadCrumbShortTitle;

    private Builder() {
    }

    public Builder addSharedElement(Pair<View, String> val) {
      if (sharedElements == null) {
        sharedElements = new ArrayList<>(3);
      }
      sharedElements.add(val);
      return this;
    }

    public Builder sharedElements(List<Pair<View, String>> val) {
      sharedElements = val;
      return this;
    }

    public Builder transition(@FragmentNavigationController.Transit int val) {
      transition = val;
      return this;
    }

    public Builder customAnimations(@AnimRes int enterAnimation, @AnimRes int exitAnimation) {
      this.enterAnimation = enterAnimation;
      this.exitAnimation = exitAnimation;
      return this;
    }

    public Builder customAnimations(@AnimRes int enterAnimation, @AnimRes int exitAnimation,
        @AnimRes int popEnterAnimation, @AnimRes int popExitAnimation) {
      this.popEnterAnimation = popEnterAnimation;
      this.popExitAnimation = popExitAnimation;
      return customAnimations(enterAnimation, exitAnimation);
    }


    public Builder transitionStyle(@StyleRes int val) {
      transitionStyle = val;
      return this;
    }

    public Builder breadCrumbTitle(String val) {
      breadCrumbTitle = val;
      return this;
    }

    public Builder breadCrumbShortTitle(String val) {
      breadCrumbShortTitle = val;
      return this;
    }

    public FragmentNavigationTransactionOptions build() {
      return new FragmentNavigationTransactionOptions(this);
    }
  }
}

