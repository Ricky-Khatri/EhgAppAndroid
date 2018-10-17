/*
 *  Created by Emaar Hospitality Group on 17/10/18 2:53 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 17/10/18 2:53 PM
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

import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.restaurant.pojo.Detail;
import com.ehg.booking.restaurant.pojo.RestaurantFetchAvailabilityPojo;
import com.ehg.home.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will show list of filters to be applied on Hotel list.
 */
public class HotelFilterActivity extends BaseActivity {

  private ChipGroup chipGroupBrand;
  private ChipGroup chipGroupProperty;
  private ChipGroup chipGroupClassification;
  private ChipGroup chipGroupFacilities;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hotel_filter);

    try {
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
    TextView textViewBrand = findViewById(R.id.textview_hotelfilter_brand);
    TextView textViewProperty = findViewById(R.id.textview_hotelfilter_property);
    TextView textViewClassification = findViewById(R.id.textview_hotelfilter_classification);
    TextView textViewFacilities = findViewById(R.id.textview_hotelfilter_facilities);

    initBrandFilter();
    initPropertyFilter();
    initClassificationFilter();
    initFacilitiesFilter();
  }

  /**
   * Called to init brand filter.
   */
  private void initBrandFilter() {
    /*chipGroupBrand = findViewById(R.id.chipgroup_hotelfilter_brand);
    //Create dynamic chips

    List<HotelFilterpojo>
    chipGroupBrand.removeAllViews();
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
            chip.setChipBackgroundColorResource(R.color.colorChipBackground);
            chip.setTextColor(getResources().getColor(R.color.colorGray));

            if (!TextUtils.isEmpty(selectedTimeSlot) && selectedTimeSlot
                .equalsIgnoreCase(timeSegmentList.get(index1).getReservationTime())) {
              chip.setChipBackgroundColorResource(R.color.colorGray);
              chip.setTextColor(getResources().getColor(R.color.white));
            } else {
              chip.setChipBackgroundColorResource(R.color.colorChipBackground);
              chip.setTextColor(getResources().getColor(R.color.colorGray));
            }

            final int finalIndex = index1;
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                  chip.setChipBackgroundColorResource(R.color.colorGray);
                  chip.setTextColor(getResources().getColor(R.color.white));
                  selectedTimeSlot = timeSegmentList.get(finalIndex).getReservationTime();
                } else {
                  chip.setChipBackgroundColorResource(R.color.colorChipBackground);
                  chip.setTextColor(getResources().getColor(R.color.colorGray));
                  selectedTimeSlot = "";
                }
                initBrandFilter();
              }
            });

            chip.setText(timeSegmentList.get(index1).getReservationTime());
            chipGroupBrand.addView(chip);
          }
        }
      }
    }*/
  }

  /**
   * Called to init property filter.
   */
  private void initPropertyFilter() {
    chipGroupProperty = findViewById(R.id.chipgroup_hotelfilter_property);
  }

  /**
   * Called to init classification filter.
   */
  private void initClassificationFilter() {
    chipGroupClassification = findViewById(R.id.chipgroup_hotelfilter_classification);
  }

  /**
   * Called to init facilities filter.
   */
  private void initFacilitiesFilter() {
    chipGroupFacilities = findViewById(R.id.chipgroup_hotelfilter_facilities);
  }
}
