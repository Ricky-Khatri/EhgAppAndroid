/*
 *  Created by Emaar Hospitality Group on 24/9/18 12:21 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 24/9/18 12:21 PM
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

package com.ehg.reservations;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import com.ehg.R;
import com.ehg.home.BaseActivity;

/**
 * This class will show booking summary of rooms, restaurants, spa, golf etc.
 */
public class BookingSummaryActivity extends BaseActivity {

  private Context context;

  /**
   * Called when activity created.
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_bookingsummary);

    context = this;

    initView();
  }

  /**
   * Called to init view components of this activity.
   */
  private void initView() {
    setBackArrowRtl((AppCompatImageView) findViewById(R.id.imageview_header_back));
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
}
