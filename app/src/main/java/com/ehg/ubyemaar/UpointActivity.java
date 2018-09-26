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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.ubyemaar.adapter.UpointAdapter;
import com.ehg.utilities.AppUtil;

/**
 * This class is using to show Upoint of the user.
 */

public class UpointActivity extends BaseActivity implements View.OnClickListener {

  private Context context;
  private RecyclerView recycleViewUpoint;

  private AppCompatImageView buttonBack;
  private TextView textViewHeaderTitle;

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

    recycleViewUpoint = findViewById(R.id.recyclerview_upointactivity);
    buttonBack = findViewById(R.id.imageview_header_back);
    buttonBack.setOnClickListener(this);
    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(R.string.upointactivuty_title);

    LinearLayoutManager manager = new LinearLayoutManager(context,
        LinearLayoutManager.VERTICAL, false);
    recycleViewUpoint.setLayoutManager(manager);
    recycleViewUpoint.setHasFixedSize(true);
    recycleViewUpoint.setAdapter(new UpointAdapter(context));
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
        AppUtil.finishActivityWithAnimation((AppCompatActivity) context);
        break;

      default:
        break;
    }
  }
}
