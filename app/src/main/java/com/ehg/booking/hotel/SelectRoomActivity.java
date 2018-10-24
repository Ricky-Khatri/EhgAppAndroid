/*
 *  Created by Emaar Hospitality Group on 18/10/18 5:51 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 18/10/18 5:51 PM
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
import com.ehg.booking.hotel.adapter.SelectRoomAdapter;
import com.ehg.booking.hotel.adapter.SelectRoomAdapter.OnRoomItemClicklistner;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

/**
 * This class allows to select hotel room.
 */
public class SelectRoomActivity extends BaseActivity
    implements OnRoomItemClicklistner, OnClickListener {

  private Context context;
  private RecyclerView recyclerViewRoomList;
  private TextView textViewHeaderTitle;
  private AppCompatImageView headerBackButton;
  private TextView textViewNext;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {

      setContentView(R.layout.activity_hotelroomselection);
      context = this;

      initView();
    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init activity view components.
   */
  private void initView() {

    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    headerBackButton = findViewById(R.id.imageview_header_back);
    textViewNext = findViewById(R.id.textview_hotelroomselection_next);
    recyclerViewRoomList = findViewById(R.id.recyclerview_hotelroomselection);
    recyclerViewRoomList.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewRoomList.setHasFixedSize(true);

    //Set Adapter
    SelectRoomAdapter selectRoomAdapter = new SelectRoomAdapter(context, this);
    recyclerViewRoomList.setAdapter(selectRoomAdapter);
    AppUtil.animateRecyclerView(context, recyclerViewRoomList,
        R.anim.layout_animation_from_bottom);

    //Set OnClickListener
    textViewNext.setOnClickListener(this);
    headerBackButton.setOnClickListener(this);
  }

  /**
   * Called when list view item clicked.
   *
   * @param position clicked item position
   * @param view clicked item
   */
  @Override
  public void onItemClick(int position, View view) {

    Intent intent = null;
    switch (view.getId()) {

      case R.id.sliderlayout_itemhotelroomselection_slider:

        intent = new Intent(context, HotelRoomdetailActivity.class);
        break;
      case R.id.linearlayout_itemhotelroomselection_allrates:

        intent = new Intent(context, AvailableRoomRatesActivity.class);
        break;
      default:
        break;
    }
    AppUtil.startActivityWithAnimation(this, intent, false);
  }

  /**
   * Called when activity item view clicked.
   *
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {

    Intent intent = null;
    switch (view.getId()) {
      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      case R.id.textview_hotelroomselection_next:
        intent = new Intent(context, EnhanceStayActivity.class);
        break;

      default:
        break;
    }
    AppUtil.startActivityWithAnimation(this, intent, false);
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
}
