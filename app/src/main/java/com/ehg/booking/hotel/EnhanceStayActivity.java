/*
 *  Created by Emaar Hospitality Group on 20/10/18 7:57 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 20/10/18 7:57 PM
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

package com.ehg.booking.hotel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.hotel.adapter.EnhanceYourStayAdapter;
import com.ehg.booking.hotel.adapter.EnhanceYourStayAdapter.OnEnhanceYourStayItemClicklistner;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

/**
 * This class will list of show room enhancement items.
 */
public class EnhanceStayActivity extends BaseActivity implements
    OnClickListener, OnEnhanceYourStayItemClicklistner {

  private Context context;
  private TextView textViewHeaderTitle;
  private AppCompatImageView headerBackButton;
  private RecyclerView recyclerViewRoomList;
  private TextView textViewSaveAndResturn;
  private TextView textViewCancel;

  /**
   * Called when activity created.
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      setContentView(R.layout.activity_enhancestay);

      context = this;
      initView();
    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init view components of this activity.
   */
  private void initView() {

    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(R.string.enhancestay_title);
    headerBackButton = findViewById(R.id.imageview_header_back);
    textViewSaveAndResturn = findViewById(R.id.textview_enhancestay_saveenhancements);
    textViewCancel = findViewById(R.id.textview_enhancestay_cancel);
    recyclerViewRoomList = findViewById(R.id.recyclerview_enhancestay);
    recyclerViewRoomList.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewRoomList.setHasFixedSize(true);
    //Set adapter
    EnhanceYourStayAdapter selectRoomAdapter = new EnhanceYourStayAdapter(context, this);
    recyclerViewRoomList.setAdapter(selectRoomAdapter);
    AppUtil.animateRecyclerView(context, recyclerViewRoomList,
        R.anim.layout_animation_from_bottom);

    //Set OnClickListener
    headerBackButton.setOnClickListener(this);
    textViewSaveAndResturn.setOnClickListener(this);
    textViewCancel.setOnClickListener(this);
  }

  /**
   * Called when view item clicked on this activity.
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {

    Intent intent = null;
    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      case R.id.textview_enhancestay_skip:
        intent = new Intent(context, RoomBookingGuestDetailActivity.class);
        AppUtil.startActivityWithAnimation(this, intent, false);
        break;

      case R.id.textview_enhancestay_saveenhancements:
        AppUtil.finishActivityWithAnimation(this);
        break;

      case R.id.textview_enhancestay_cancel:
        AppUtil.finishActivityWithAnimation(this);
        break;

      default:
        break;
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
   * Called to set RTL back arrow.
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
   * @param position clicked item position
   * @param view clicked item view
   */
  @Override
  public void onItemClick(int position, View view) {

  }
}
