/*
 *  Created by Emaar Hospitality Group on 20/10/18 7:57 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 20/10/18 7:57 PM
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.hotel.adapter.EnhanceStayAdapter;
import com.ehg.booking.hotel.adapter.EnhanceStayAdapter.OnEnhanceStayItemClicklistner;
import com.ehg.booking.hotel.adapter.SelectRoomAdapter;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

public class EnhanceStayActivity extends BaseActivity implements OnClickListener, OnEnhanceStayItemClicklistner {

  private Context context;
  private TextView textViewHeaderTitle;
  private AppCompatImageView headerBackButton;
  private RecyclerView recyclerViewRoomList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {

      setContentView(R.layout.activity_enhancestay);

      context = this;
      initView();

    } catch (NullPointerException e) {

      e.printStackTrace();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  private void initView() {

    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    headerBackButton = findViewById(R.id.imageview_header_back);
    recyclerViewRoomList = findViewById(R.id.recyclerview_enhancestay);
    recyclerViewRoomList.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewRoomList.setHasFixedSize(true);

    EnhanceStayAdapter selectRoomAdapter = new EnhanceStayAdapter(context, this);
    recyclerViewRoomList.setAdapter(selectRoomAdapter);

    AppUtil.animateRecyclerView(context, recyclerViewRoomList,
        R.anim.layout_animation_from_bottom);

    headerBackButton.setOnClickListener(this);

  }

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
  public void onItemClick(int position, View view) {

  }
}
