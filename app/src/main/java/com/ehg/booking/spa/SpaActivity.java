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

package com.ehg.booking.spa;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.spa.adapter.SpaListAdapter;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

public class SpaActivity extends BaseActivity implements View.OnClickListener {

  private AppCompatImageView imageViewHeaderBack;
  private RecyclerView recyclerViewSpa;
  private Context context;
  private String headerName;
  private TextView textViewHeader;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_spa);

    context = this;

    Bundle bundle = getIntent().getExtras();

    if (bundle != null) {

      headerName = bundle.getString("spaTreatment");
    }

    initView();

  }

  private void initView() {

    recyclerViewSpa = (RecyclerView) findViewById(R.id.recyclerview_spa);
    imageViewHeaderBack = (AppCompatImageView) findViewById(R.id.imageview_header_back);
    textViewHeader = (TextView) findViewById(R.id.textview_header_title);

    textViewHeader.setText(headerName);
    recyclerViewSpa.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewSpa.setHasFixedSize(true);

    imageViewHeaderBack.setOnClickListener(this);

    SpaListAdapter spaListAdapter = new SpaListAdapter(context);
    recyclerViewSpa.setAdapter(spaListAdapter);

    AppUtil.animateRecyclerView(context, recyclerViewSpa,
        R.anim.layout_animation_from_bottom);
  }

  @Override
  public void onClick(View v) {

    switch (v.getId()) {

      case R.id.imageview_header_back:

        AppUtil.finishActivityWithAnimation(this);
        break;

      default:
        break;
    }
  }
}
