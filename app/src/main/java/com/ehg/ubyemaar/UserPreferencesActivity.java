/*
 *  Created by Emaar Hospitality Group on 25/9/18 11:31 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 25/9/18 11:31 AM
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

package com.ehg.ubyemaar;

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
import com.ehg.ubyemaar.adapter.UserPreferencesAdapter;
import com.ehg.ubyemaar.adapter.UserPreferencesAdapter.OnUserPreferenceClickListener;
import com.ehg.ubyemaar.pojo.UserPreferencesPojo;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;

/**
 * This class allows to set user preferences. Like: Dine, Stay, Relax, Play etc.
 */
public class UserPreferencesActivity extends BaseActivity
    implements OnUserPreferenceClickListener {

  private ArrayList<UserPreferencesPojo> userPreferencesList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_preferences);

    initView();
  }

  /**
   * This method init's view component of this activity.
   */
  private void initView() {
    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(getResources().getString(R.string.userpreferences_preferences));

    //Prepare data
    userPreferencesList = new ArrayList<>();

    UserPreferencesPojo userPreferencesPojo = new UserPreferencesPojo();
    userPreferencesPojo.setPreferenceName("Dine");
    userPreferencesPojo.setPreferenceId("1");
    userPreferencesPojo.setSelected(true);
    userPreferencesList.add(userPreferencesPojo);
    userPreferencesPojo = new UserPreferencesPojo();
    userPreferencesPojo.setPreferenceName("Stay");
    userPreferencesPojo.setPreferenceId("1");
    userPreferencesPojo.setSelected(false);
    userPreferencesList.add(userPreferencesPojo);
    userPreferencesPojo = new UserPreferencesPojo();
    userPreferencesPojo.setPreferenceName("Relax");
    userPreferencesPojo.setPreferenceId("1");
    userPreferencesPojo.setSelected(false);
    userPreferencesList.add(userPreferencesPojo);
    userPreferencesPojo = new UserPreferencesPojo();
    userPreferencesPojo.setPreferenceName("Play");
    userPreferencesPojo.setPreferenceId("1");
    userPreferencesPojo.setSelected(false);
    userPreferencesList.add(userPreferencesPojo);

    //Init recycler view and set adapter
    RecyclerView recyclerViewUserPreferencesList =
        findViewById(R.id.recyclerview_userpreferences_list);
    LinearLayoutManager manager = new LinearLayoutManager(this,
        LinearLayoutManager.VERTICAL, false);
    recyclerViewUserPreferencesList.setLayoutManager(manager);
    recyclerViewUserPreferencesList.setHasFixedSize(true);
    UserPreferencesAdapter userPreferencesAdapter =
        new UserPreferencesAdapter(this, userPreferencesList);
    recyclerViewUserPreferencesList.setAdapter(userPreferencesAdapter);

    //Set OnClickListener
    AppCompatImageView appCompatImageViewBackArrow = findViewById(R.id.imageview_header_back);
    appCompatImageViewBackArrow.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        AppUtil.finishActivityWithAnimation(UserPreferencesActivity.this);
      }
    });

    setBackArrowRtl(appCompatImageViewBackArrow);
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
   * Called when user preference selected from list.
   *
   * @param userPreferencesPojo user preference object
   */
  @Override
  public void onPreferenceSelect(UserPreferencesPojo userPreferencesPojo, int position) {
    if (userPreferencesList != null && userPreferencesList.size() > 0
        && position > 0 && position < userPreferencesList.size()) {
      userPreferencesList.set(position, userPreferencesPojo);
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
