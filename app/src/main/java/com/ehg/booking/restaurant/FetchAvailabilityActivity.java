/*
 *  Created by Emaar Hospitality Group on 12/10/18 12:52 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 12/10/18 12:52 PM
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

package com.ehg.booking.restaurant;

import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import com.ehg.R;
import com.ehg.booking.restaurant.pojo.Detail;
import com.ehg.booking.restaurant.pojo.RestaurantFetchAvailabilityPojo;
import com.ehg.booking.restaurant.pojo.TimeSegment;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * This class shows list of available time slots.
 */
public class FetchAvailabilityActivity extends BaseActivity implements OnClickListener {

  private List<TimeSegment> timeSegmentList;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_fetch_availability);

    initView();
  }

  /**
   * Called to init's view components of this screen.
   */
  private void initView() {

    try {

      ChipGroup chipGroup = findViewById(R.id.chipgroup_fetchavailability_timeslots);

      Button buttonNext = findViewById(R.id.button_fetchavailablity_next);
      buttonNext.setOnClickListener(this);
      //Create dynamic chips
      if (getIntent() != null && getIntent().getStringExtra("availableSlotes") != null) {
        RestaurantFetchAvailabilityPojo restaurantFetchAvailabilityPojo = new Gson().fromJson(
            getIntent().getStringExtra("availableSlotes"),
            new TypeToken<RestaurantFetchAvailabilityPojo>() {
            }.getType());

        if (restaurantFetchAvailabilityPojo != null
            && restaurantFetchAvailabilityPojo.getData() != null) {
          List<Detail> detailList = restaurantFetchAvailabilityPojo.getData().getDetail();
          if (detailList != null && detailList.size() > 0) {
            for (int index = 0; index < detailList.size(); index++) {

              Detail detail = detailList.get(index);
              timeSegmentList = detail.getTimeSegments();
              for (int index1 = 0; index1 < timeSegmentList.size(); index1++) {

                final Chip chip = (Chip) getLayoutInflater()
                    .inflate(R.layout.view_fetchavailabilitychiplayout,
                        chipGroup, false);

                chip.setPadding(10, 10, 10, 10);
                chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    if (isChecked) {
                      chip.setChipBackgroundColorResource(R.color.colorGray);
                      chip.setTextColor(getResources().getColor(R.color.white));
                    } else {
                      chip.setChipBackgroundColorResource(R.color.colorDarkerGray);
                      chip.setTextColor(getResources().getColor(R.color.colorGray));
                    }
                  }
                });

                chip.setText(timeSegmentList.get(index1).getReservationTime());
                chipGroup.addView(chip);
              }
            }
          }
        }
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called when click event initialized on view component.
   *
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      case R.id.button_fetchavailablity_next:
        if (timeSegmentList != null && timeSegmentList.size() > 0) {

        } else {
          AppUtil.showToast(this,getString(R.string.fetchavailability_notimeslotsavailable));
        }
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
}
