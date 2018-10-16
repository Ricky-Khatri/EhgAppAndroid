/*
 *  Created by Emaar Hospitality Group on 16/10/18 5:01 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 16/10/18 5:01 PM
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

package com.ehg.booking.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.hotel.adapter.HotelResortsAdapter;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

public class HotelListActivity extends BaseActivity implements OnClickListener {

  private Context context;
  private AppCompatImageView headerBackButton;
  private TextView textViewHeaderTitle;
  private RecyclerView recyclerViewHotelList;
  private String headerTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      setContentView(R.layout.activity_hotellist);
      context = this;

      initView();
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  private void initView() {

    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    headerBackButton = findViewById(R.id.imageview_header_back);
    recyclerViewHotelList = findViewById(R.id.recyclerview_hotellist_list);

    recyclerViewHotelList.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewHotelList.setHasFixedSize(true);

    HotelResortsAdapter hotelResortsAdapter = new HotelResortsAdapter(context);

    recyclerViewHotelList.setAdapter(hotelResortsAdapter);

    AppUtil.animateRecyclerView(context, recyclerViewHotelList,
        R.anim.layout_animation_from_bottom);

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {

      headerTitle = bundle.getString("title");
    }

    if (!TextUtils.isEmpty(headerTitle)) {

      textViewHeaderTitle.setText(headerTitle);
    }
    headerBackButton.setOnClickListener(this);

  }

  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();
    setBackArrowRtl(headerBackButton);
  }

  /**
   * Called to set RTL back arrow.
   *
   * @param appCompatImageView imageview object
   */
  @Override
  public void setBackArrowRtl(AppCompatImageView appCompatImageView) {
    super.setBackArrowRtl(appCompatImageView);
  }

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
