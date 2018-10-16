/*
 *  Created by Emaar Hospitality Group on 27/9/18 11:37 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 27/9/18 11:37 AM
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

package com.ehg.booking.restaurant;

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
import com.ehg.booking.restaurant.adapter.RestaurantAdapter;
import com.ehg.booking.restaurant.adapter.RestaurantAdapter.OnRestaurantItemClickListener;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

/**
 * This class shows list of Restaurants at Emaar properties.
 */
public class RestaurantActivity extends BaseActivity implements OnClickListener,
    OnRestaurantItemClickListener {

  private RecyclerView recyclerViewRestaurantList;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restaurant);

    initView();
  }

  /**
   * Init's view components of this screen.
   */
  private void initView() {

    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    if (getIntent() != null && getIntent().getStringExtra("title") != null) {
      textViewHeaderTitle.setText(getIntent().getStringExtra("title"));
    }
    //Set OnClickListener
    findViewById(R.id.imageview_header_back).setOnClickListener(this);
    //Init recycler view
    recyclerViewRestaurantList = findViewById(R.id.recyclerview_restaurant);
    recyclerViewRestaurantList.setLayoutManager(new LinearLayoutManager(this));
    recyclerViewRestaurantList.setHasFixedSize(true);
    //Set adapter
    RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this, this);
    recyclerViewRestaurantList.setAdapter(restaurantAdapter);

    AppUtil.animateRecyclerView(this, recyclerViewRestaurantList,
        R.anim.layout_animation_from_bottom);
  }

  /**
   * Called when activity item clicked.
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.imageview_header_back:

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
   * Called when restaurant list item clicked.
   *
   * @param position clicked item position from list
   */
  @Override
  public void onRestaurantItemClicked(int position) {
    Intent intent = new Intent(this, RestaurantBookingSlotActivity.class);
    AppUtil.startActivityWithAnimation(this, intent, false);
  }
}
