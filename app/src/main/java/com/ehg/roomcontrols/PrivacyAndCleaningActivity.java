/*
 *  Created by Emaar Hospitality Group on 23/10/18 4:10 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 23/10/18 4:10 PM
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
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.roomcontrols.adapter.PrivacyAndCleaningAdapter;
import com.ehg.roomcontrols.adapter.PrivacyAndCleaningAdapter.OnPrivacyAndCleaningItemClickListener;
import com.ehg.roomcontrols.adapter.SceneAdapter;
import com.ehg.roomcontrols.pojo.LightPojo;
import com.ehg.roomcontrols.pojo.PrivacyAndCleaningPojo;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;

/**
 * This class shows privacy and cleaning items like: Makeup Room, Do not disturb.
 */
public class PrivacyAndCleaningActivity extends BaseActivity implements
    OnPrivacyAndCleaningItemClickListener {

  private RecyclerView recyclerViewPrivacyAndCleaningList;
  private ArrayList<PrivacyAndCleaningPojo> privacyAndCleaningList;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_privacy_and_cleaning);

    try {
      initView();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init activity view components.
   */
  private void initView() {
    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(R.string.privacyandcleaning_title);

    recyclerViewPrivacyAndCleaningList = findViewById(R.id.recyclerview_privacyandcleaning_list);
    recyclerViewPrivacyAndCleaningList.setLayoutManager(new LinearLayoutManager(this));

    //Prepare data
    privacyAndCleaningList = new ArrayList<>();
    PrivacyAndCleaningPojo privacyAndCleaningPojo = new PrivacyAndCleaningPojo();
    privacyAndCleaningPojo.setTitle("Make Up Room");
    privacyAndCleaningList.add(privacyAndCleaningPojo);
    privacyAndCleaningPojo = new PrivacyAndCleaningPojo();
    privacyAndCleaningPojo.setTitle("Do Not Disturb");
    privacyAndCleaningList.add(privacyAndCleaningPojo);

    //Set adapter
    PrivacyAndCleaningAdapter privacyAndCleaningAdapter = new PrivacyAndCleaningAdapter(this,
        AppUtil.getDeviceHeight(this), privacyAndCleaningList, this);
    recyclerViewPrivacyAndCleaningList.setAdapter(privacyAndCleaningAdapter);
    AppUtil.animateRecyclerView(this, recyclerViewPrivacyAndCleaningList,
        R.anim.layout_animation_from_bottom);

    //Set OnClickListener
    findViewById(R.id.imageview_header_back).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        AppUtil.finishActivityWithAnimation(PrivacyAndCleaningActivity.this);
      }
    });
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

  /**
   * Called when list item clicked.
   *
   * @param position item position
   */
  @Override
  public void onPrivacyAndCleaningItemClicked(int position) {

  }
}
