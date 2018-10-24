/*
 *  Created by Emaar Hospitality Group on 23/10/18 4:23 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 23/10/18 4:23 PM
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.hotel.adapter.AvailableRoomRatesAdapter;
import com.ehg.booking.hotel.adapter.AvailableRoomRatesAdapter.OnRoomRatesItemClicklistner;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

/**
 * Called to show available room rates.
 */
public class AvailableRoomRatesActivity extends BaseActivity implements OnClickListener, OnRoomRatesItemClicklistner {

  private Context context;
  private TextView textViewHeaderTitle;
  private AppCompatImageView headerBackButton;
  private RecyclerView recyclerViewPriceList;
  private String headerTitle;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      setContentView(R.layout.activity_availableroomrates);

      context = this;
      initView();

    } catch (NullPointerException e) {

      e.printStackTrace();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /**
   * Called to init view components of screen.
   */
  private void initView() {
    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    headerBackButton = findViewById(R.id.imageview_header_back);
    //Set Adapter
    recyclerViewPriceList = findViewById(R.id.recyclerview_availableroomrates_list);
    recyclerViewPriceList.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewPriceList.setHasFixedSize(true);
    AvailableRoomRatesAdapter hotelResortsAdapter = new AvailableRoomRatesAdapter(context, this);
    recyclerViewPriceList.setAdapter(hotelResortsAdapter);
    AppUtil.animateRecyclerView(context, recyclerViewPriceList,
        R.anim.layout_animation_from_bottom);

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {

      headerTitle = bundle.getString("title");
    }
    if (!TextUtils.isEmpty(headerTitle)) {

      textViewHeaderTitle.setText(headerTitle);
    }
    //Set OnClickListener
    headerBackButton.setOnClickListener(this);
  }


  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();
    setBackArrowRtl(headerBackButton);
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
   * Called when view item clicked on this activity.
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {

    Intent intent = null;

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation((AppCompatActivity) context);
        break;

      default:
        break;
    }
    AppUtil.startActivityWithAnimation(this, intent, false);

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
