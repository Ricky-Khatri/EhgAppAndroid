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
import com.ehg.booking.spa.adapter.SpaListAdapter;
import com.ehg.booking.spa.adapter.SpaListAdapter.OnSpaItemClickListener;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

/**
 * This class shows list of Spa at Emaar properties.
 */
public class SpaActivity extends BaseActivity implements OnClickListener, OnSpaItemClickListener {

  private AppCompatImageView imageViewHeaderBack;
  private RecyclerView recyclerViewSpa;
  private Context context;
  private String headerName;
  private TextView textViewHeader;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_spa);

    try {
      context = this;

      Bundle bundle = getIntent().getExtras();

      if (bundle != null) {

        headerName = bundle.getString("title");
      }
      initView();

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init view components.
   */
  private void initView() {

    recyclerViewSpa = findViewById(R.id.recyclerview_spa);
    imageViewHeaderBack = findViewById(R.id.imageview_header_back);
    textViewHeader = findViewById(R.id.textview_header_title);
    textViewHeader.setText(headerName);
    recyclerViewSpa.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewSpa.setHasFixedSize(true);

    imageViewHeaderBack.setOnClickListener(this);

    SpaListAdapter spaListAdapter = new SpaListAdapter(context, this);
    recyclerViewSpa.setAdapter(spaListAdapter);

    AppUtil.animateRecyclerView(context, recyclerViewSpa,
        R.anim.layout_animation_from_bottom);
  }

  /**
   * Called when activity item clicked.
   *
   * @param view clicked view
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
   * Called when Spa list item clicked.
   *
   * @param position clicked item position
   * @param view clicked item view
   */
  @Override
  public void onSpaItemClicked(int position, View view) {

    Intent intent = null;

    switch (view.getId()) {
      case R.id.button_itemspa_book:
        intent = new Intent(this, SpaRequestEnquiryActivity.class);
        break;

      default:
        intent = new Intent(this, SpaDetailActivity.class);
        break;
    }

    AppUtil.startActivityWithAnimation(this, intent, false);
  }
}
