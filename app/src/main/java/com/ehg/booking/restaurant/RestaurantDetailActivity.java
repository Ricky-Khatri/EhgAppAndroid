/*
 *  Created by Emaar Hospitality Group on 11/10/18 11:33 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 11/10/18 11:33 AM
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

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

/**
 * This class shows detail of a restaurant.
 */
public class RestaurantDetailActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restaurant_detail);

    initView();
  }

  /**
   * Init's view components of this screen.
   */
  private void initView() {

    //Set OnClickListener
    findViewById(R.id.imageview_header_back).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        AppUtil.finishActivityWithAnimation(RestaurantDetailActivity.this);
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
}
