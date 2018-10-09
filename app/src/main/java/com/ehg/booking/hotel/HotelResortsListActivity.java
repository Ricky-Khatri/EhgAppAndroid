/*
 *  Created by Emaar Hospitality Group on 6/10/18 2:54 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 6/10/18 2:54 PM
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
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.hotel.adapter.HotelResortsAdapter;
import com.ehg.booking.hotel.adapter.HotelResortAdapter;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

/**
 * This class allows to show Hotel & Resorts List.
 */
public class HotelResortsListActivity extends BaseActivity implements View.OnClickListener {

  private Context context;
  private RecyclerView recyclerViewHotelList;
  private TextView textViewHotelCount;
  private AppCompatImageView imageViewHotelFilter;
  private AppCompatImageView imageViewHotelSort;
  private TextView textViewHeaderTitle;
  private String headerTitle = "";
  private AppCompatImageView headerBackButton;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState - bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_hotelresorts);
    context = this;

    initView();
  }

  /**
   * Init's view components of this activity.
   */
  private void initView() {

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {

      headerTitle = bundle.getString("title");
    }
    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    recyclerViewHotelList = findViewById(R.id.recyclerview_hotelresorts_list);
    textViewHotelCount = findViewById(R.id.textview_hotelresorts_count);
    imageViewHotelFilter = findViewById(R.id.appcompactimageview_hotelresorts_filter);
    imageViewHotelSort = findViewById(R.id.appcompactimageview_hotelresorts_sort);
    headerBackButton = findViewById(R.id.imageview_header_back);

    headerBackButton.setOnClickListener(this);

    recyclerViewHotelList.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewHotelList.setHasFixedSize(true);

    HotelResortsAdapter hotelResortsAdapter = new HotelResortsAdapter(context);

    recyclerViewHotelList.setAdapter(hotelResortsAdapter);

    AppUtil.animateRecyclerView(context, recyclerViewHotelList,
        R.anim.layout_animation_from_bottom);

    if (!TextUtils.isEmpty(headerTitle)) {

      textViewHeaderTitle.setText(headerTitle);
    }
  }

  /**
   * This method Called when a view has been clicked.
   *
   * @param view - clicked view object.
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
}
