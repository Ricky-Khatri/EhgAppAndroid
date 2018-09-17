/*
 *  Created by Emaar Hospitality Group on 14/9/18 6:04 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 14/9/18 6:01 PM
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

package com.ehg.splash.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.ehg.R;
import com.ehg.customview.CarouselLayout;
import com.ehg.splash.SplashActivity;
import com.ehg.splash.fragment.SplashImageFragment;
import java.util.Objects;

/**
 * This class initialise splash pager adapter.
 */
public class SplashPagerAdapter extends FragmentPagerAdapter implements
    ViewPager.OnPageChangeListener {

  public static final float BIG_SCALE = 1.0f;
  private static final float SMALL_SCALE = 0.7f;
  private static final float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
  private SplashActivity context;
  private FragmentManager fragmentManager;
  private float scale;


  /**
   * Parameterized constructor for SplashPagerAdapter.
   */
  public SplashPagerAdapter(SplashActivity context, FragmentManager fragmentManager) {
    super(fragmentManager);

    this.fragmentManager = fragmentManager;
    this.context = context;
  }

  /**
   * Return the Fragment associated with a specified position.
   * @param position - position of the fragment
   * @return         - return fragment
   */
  @Override
  public Fragment getItem(int position) {
    // make the first pager bigger than others
    try {
      if (position == SplashActivity.FIRST_PAGE) {
        scale = BIG_SCALE;
      } else {
        scale = SMALL_SCALE;
      }

      position = position % new SplashImageFragment().imageUrls.length;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return new SplashImageFragment().newInstance(context, position, scale);
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getCount() {
    return new SplashImageFragment().imageUrls.length;
  }

  /**
   *This method will be invoked when the current page is scrolled,
   *  either as part of a programmatically initiated smooth scroll or a user initiated touch scroll.
   *
   * @param position   - Position index of the first page currently being displayed.
   * @param positionOffset -  indicating the offset from the page at position.
   * @param positionOffsetPixels  - Value in pixels indicating the offset from position.
   */
  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    try {
      if (positionOffset >= 0f && positionOffset <= 1f) {
        CarouselLayout cur = getRootView(position);
        CarouselLayout next = getRootView(position + 1);

        cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
        next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method will be invoked when a new page becomes selected.
   * Animation is not necessarily complete.
   * @param position   - Position index of the new selected page
   */
  @Override
  public void onPageSelected(int position) {
  }

  /**
   *  This method called when the scroll state changes.
   * @param position - The new scroll state.
   */
  @Override
  public void onPageScrollStateChanged(int position) {
  }

  /**
   * This method is initialising the rootview .
   * @param position - Position index of the first page currently being displayed.
   * @return         - return Carousal layout object.
   */
  private CarouselLayout getRootView(int position) {
    return (CarouselLayout) Objects.requireNonNull(
        Objects.requireNonNull(fragmentManager.findFragmentByTag(this.getFragmentTag(position)))
            .getView()).findViewById(R.id.carousellayout_splashimagefragment);
  }

  /**
   * This method is using to fetch fragment tag.
   * @param position - position of the page which is showing currently.
   * @return         - returning fragment tag as string.
   */
  private String getFragmentTag(int position) {
    return "android:switcher:" + context.cardViewPager.getId() + ":" + position;
  }
}
