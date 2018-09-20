/*
 *  Created by Emaar Hospitality Group on 13/8/18 11:12 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 13/8/18 11:12 AM
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

package com.ehg.settings;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

/**
 * This class allows users to do local app settings.
 */
public class SettingsActivity extends BaseActivity implements OnClickListener {

  /**
   * Called when activity created first.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_settings);

    initView();
  }

  /**
   * Init's view components of this screen.
   */
  private void initView() {

    TextView textViewTitle = findViewById(R.id.textview_header_title);
    textViewTitle.setText(getResources().getString(R.string.settings_title));

    findViewById(R.id.imageview_header_back).setOnClickListener(this);
  }

  /**
   * Called when user click on any view attached OnClickListener.
   *
   * @param view clicked view object
   */
  @Override
  public void onClick(View view) {
    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;
    }
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
