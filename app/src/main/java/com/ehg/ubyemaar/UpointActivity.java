/*
 *  Created by Emaar Hospitality Group on 21/9/18 4:26 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 21/9/18 4:23 PM
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

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.ubyemaar.adapter.UpointAdapter;

/**
 * This class is using to show Upoint of the user.
 */

public class UpointActivity extends BaseActivity implements View.OnClickListener {

  Context context;
  private RecyclerView upointRecycleView;

  private AppCompatImageView headerBackButton;
  private TextView headertextView;

  /**
   * Called when activity created first.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_upoint);
    context = this;

    initView();
  }

  /**
   * Init's view components of this screen.
   */
  private void initView() {

    upointRecycleView = findViewById(R.id.recyclerview_upointactivity);
    headerBackButton = findViewById(R.id.imageview_header_back);
    headertextView = findViewById(R.id.textview_header_title);

    headertextView.setText(R.string.upointactivuty_title);

    LinearLayoutManager manager = new LinearLayoutManager(context,
        LinearLayoutManager.VERTICAL, false);
    upointRecycleView.setLayoutManager(manager);
    upointRecycleView.setHasFixedSize(true);
    upointRecycleView.setAdapter(new UpointAdapter(context));

  }

  /**
   * Called when a view has been clicked.
   *
   * @param view - object of clicked view.
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.imageview_header_back:
        finish();
        break;

      default:
    }
  }
}
