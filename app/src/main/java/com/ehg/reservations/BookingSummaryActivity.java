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
import android.view.KeyEvent;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;
import org.json.JSONArray;
import org.json.JSONObject;

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
    try {
      String response = getIntent().getStringExtra("response");
      JSONObject jsonObject =new JSONObject(response);
      JSONObject dataObject = jsonObject.getJSONObject("data");

      if( dataObject != null) {
        JSONArray detail = dataObject.optJSONArray("detail");

        for (int index = 0; index < detail.length(); index++) {
          JSONObject detailObject = detail.optJSONObject(index);

        }
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
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
