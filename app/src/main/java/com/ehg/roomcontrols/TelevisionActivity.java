
/*
 *  Created by Emaar Hospitality Group on 23/10/18 6:24 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 23/10/18 6:24 PM
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

package com.ehg.roomcontrols;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.roomcontrols.fragment.LightFragment;
import com.ehg.roomcontrols.fragment.TelevisionFragment;
import com.ehg.utilities.AppUtil;

/**
 * This class will show list of TV channels and allows user to operate channels on Tv at hotel room.
 */
public class TelevisionActivity extends BaseActivity {

  /**
   * Called when activity created.
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_television);

    initView();
  }

  /**
   * Init's view components of this screen.
   */
  private void initView() {

    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(getResources().getString(R.string.television_title));

    //Init tab layout
    TabLayout tabLayout = findViewById(R.id.tab_layout_all);
    final ViewPager viewPager = findViewById(R.id.viewpager_all_fragment_viewpager);

    //Create tabs
    tabLayout.addTab(tabLayout.newTab().setText("All"));
    tabLayout.addTab(tabLayout.newTab().setText("Entertainment"));
    tabLayout.addTab(tabLayout.newTab().setText("HD"));
    tabLayout.addTab(tabLayout.newTab().setText("News"));
    tabLayout.addTab(tabLayout.newTab().setText("Movie"));
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    //Set viewpager fragment adapter
    FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
    viewPager.setAdapter(fragmentAdapter);

    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab layoutTab) {
        viewPager.setCurrentItem(layoutTab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab layoutTab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab layoutTab) {

      }
    });

    //Set OnClickListener
    findViewById(R.id.imageview_header_back).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        AppUtil.finishActivityWithAnimation(TelevisionActivity.this);
      }
    });
  }

  /**
   * This class initializes viewpager fragment adapter.
   */
  public class FragmentAdapter extends FragmentStatePagerAdapter {

    /**
     * Constructor.
     *
     * @param fragmentManager para
     */
    public FragmentAdapter(FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
      return 5;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return new TelevisionFragment();
        case 1:
          return new TelevisionFragment();
        case 2:
          return new TelevisionFragment();
        case 3:
          return new TelevisionFragment();
        case 4:
          return new TelevisionFragment();
        default:
          return null;
      }
    }
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
   * Called to update back arrow rtl icons.
   *
   * @param appCompatImageView imageview object
   */
  @Override
  public void setBackArrowRtl(AppCompatImageView appCompatImageView) {
    super.setBackArrowRtl(appCompatImageView);
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
}
