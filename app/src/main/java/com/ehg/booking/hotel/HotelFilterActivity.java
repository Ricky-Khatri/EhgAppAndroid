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
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will show list of filters to be applied on Hotel list.
 */
public class HotelFilterActivity extends BaseActivity implements OnClickListener {

  private ChipGroup chipGroupBrand;
  private ChipGroup chipGroupProperty;
  private ChipGroup chipGroupClassification;
  private ChipGroup chipGroupFacilities;

  private String selectedBrand;
  private String selectedProperty;
  private String selectedClassification;
  private String selectedFacility;

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
    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(getResources().getString(R.string.all_filter));
    TextView textViewBrand = findViewById(R.id.textview_hotelfilter_brand);
    TextView textViewProperty = findViewById(R.id.textview_hotelfilter_property);
    TextView textViewClassification = findViewById(R.id.textview_hotelfilter_classification);
    TextView textViewFacilities = findViewById(R.id.textview_hotelfilter_facilities);

    initBrandFilter();
    initPropertyFilter();
    initClassificationFilter();
    initFacilitiesFilter();

    //Set OnClickListener
    findViewById(R.id.button_hotelfilter_searchforhotels).setOnClickListener(this);
    findViewById(R.id.imageview_header_back).setOnClickListener(this);
  }

  /**
   * Called to init brand filter.
   */
  private void initBrandFilter() {

    chipGroupBrand = findViewById(R.id.chipgroup_hotelfilter_brand);
    //Create dynamic chips
    final List<HotelFilterpojo> hotelFilterList = new ArrayList<>();
    HotelFilterpojo hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Address Hotels + Resorts");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Vida Hotels and Resorts");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Rove Hotels");
    hotelFilterList.add(hotelFilterpojo);
    chipGroupBrand.removeAllViews();
    if (hotelFilterList != null
        && hotelFilterList.size() > 0) {

      for (int index = 0; index < hotelFilterList.size(); index++) {
        final Chip chip = (Chip) getLayoutInflater()
            .inflate(R.layout.view_fetchavailabilitychiplayout,
                chipGroupBrand, false);

        chip.setPadding(10, 10, 10, 10);
        chip.setChipBackgroundColorResource(R.color.colorChipBackground);
        chip.setTextColor(getResources().getColor(R.color.colorGray));

        if (!TextUtils.isEmpty(selectedBrand) && selectedBrand
            .equalsIgnoreCase(hotelFilterList.get(index).getFilterTitle())) {
          chip.setChipBackgroundColorResource(R.color.colorGray);
          chip.setTextColor(getResources().getColor(R.color.white));
        } else {
          chip.setChipBackgroundColorResource(R.color.colorChipBackground);
          chip.setTextColor(getResources().getColor(R.color.colorGray));
        }

        final int finalIndex = index;
        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

            if (isChecked) {
              chip.setChipBackgroundColorResource(R.color.colorGray);
              chip.setTextColor(getResources().getColor(R.color.white));
              selectedBrand = hotelFilterList.get(finalIndex).getFilterTitle();
            } else {
              chip.setChipBackgroundColorResource(R.color.colorChipBackground);
              chip.setTextColor(getResources().getColor(R.color.colorGray));
              selectedBrand = "";
            }
            initBrandFilter();
          }
        });

        chip.setText(hotelFilterList.get(finalIndex).getFilterTitle());
        chipGroupBrand.addView(chip);
      }
    }
  }

  /**
   * Called to init property filter.
   */
  private void initPropertyFilter() {
    chipGroupProperty = findViewById(R.id.chipgroup_hotelfilter_property);
    //Create dynamic chips
    final List<HotelFilterpojo> hotelFilterList = new ArrayList<>();
    HotelFilterpojo hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Address Downtown");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Address Boulevard");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Address Skyview");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Vida Downtown");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Vida Harbour Point");
    hotelFilterList.add(hotelFilterpojo);
    chipGroupProperty.removeAllViews();
    if (hotelFilterList != null
        && hotelFilterList.size() > 0) {

      for (int index = 0; index < hotelFilterList.size(); index++) {
        final Chip chip = (Chip) getLayoutInflater()
            .inflate(R.layout.view_fetchavailabilitychiplayout,
                chipGroupProperty, false);

        chip.setPadding(10, 10, 10, 10);
        chip.setChipBackgroundColorResource(R.color.colorChipBackground);
        chip.setTextColor(getResources().getColor(R.color.colorGray));

        if (!TextUtils.isEmpty(selectedProperty) && selectedProperty
            .equalsIgnoreCase(hotelFilterList.get(index).getFilterTitle())) {
          chip.setChipBackgroundColorResource(R.color.colorGray);
          chip.setTextColor(getResources().getColor(R.color.white));
        } else {
          chip.setChipBackgroundColorResource(R.color.colorChipBackground);
          chip.setTextColor(getResources().getColor(R.color.colorGray));
        }

        final int finalIndex = index;
        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

            if (isChecked) {
              chip.setChipBackgroundColorResource(R.color.colorGray);
              chip.setTextColor(getResources().getColor(R.color.white));
              selectedProperty = hotelFilterList.get(finalIndex).getFilterTitle();
            } else {
              chip.setChipBackgroundColorResource(R.color.colorChipBackground);
              chip.setTextColor(getResources().getColor(R.color.colorGray));
              selectedProperty = "";
            }
            initPropertyFilter();
          }
        });

        chip.setText(hotelFilterList.get(finalIndex).getFilterTitle());
        chipGroupProperty.addView(chip);
      }
    }
  }

  /**
   * Called to init classification filter.
   */
  private void initClassificationFilter() {
    chipGroupClassification = findViewById(R.id.chipgroup_hotelfilter_classification);

    //Create dynamic chips
    final List<HotelFilterpojo> hotelFilterList = new ArrayList<>();
    HotelFilterpojo hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("5 Star");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("4 Star");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("3 Star");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("2 Star");
    hotelFilterList.add(hotelFilterpojo);
    chipGroupClassification.removeAllViews();
    if (hotelFilterList != null
        && hotelFilterList.size() > 0) {

      for (int index = 0; index < hotelFilterList.size(); index++) {
        final Chip chip = (Chip) getLayoutInflater()
            .inflate(R.layout.view_fetchavailabilitychiplayout,
                chipGroupClassification, false);

        chip.setPadding(10, 10, 10, 10);
        chip.setChipBackgroundColorResource(R.color.colorChipBackground);
        chip.setTextColor(getResources().getColor(R.color.colorGray));

        if (!TextUtils.isEmpty(selectedClassification) && selectedClassification
            .equalsIgnoreCase(hotelFilterList.get(index).getFilterTitle())) {
          chip.setChipBackgroundColorResource(R.color.colorGray);
          chip.setTextColor(getResources().getColor(R.color.white));
        } else {
          chip.setChipBackgroundColorResource(R.color.colorChipBackground);
          chip.setTextColor(getResources().getColor(R.color.colorGray));
        }

        final int finalIndex = index;
        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

            if (isChecked) {
              chip.setChipBackgroundColorResource(R.color.colorGray);
              chip.setTextColor(getResources().getColor(R.color.white));
              selectedClassification = hotelFilterList.get(finalIndex).getFilterTitle();
            } else {
              chip.setChipBackgroundColorResource(R.color.colorChipBackground);
              chip.setTextColor(getResources().getColor(R.color.colorGray));
              selectedClassification = "";
            }
            initClassificationFilter();
          }
        });

        chip.setText(hotelFilterList.get(finalIndex).getFilterTitle());
        chipGroupClassification.addView(chip);
      }
    }
  }

  /**
   * Called to init facilities filter.
   */
  private void initFacilitiesFilter() {
    chipGroupFacilities = findViewById(R.id.chipgroup_hotelfilter_facilities);

    //Create dynamic chips
    final List<HotelFilterpojo> hotelFilterList = new ArrayList<>();
    HotelFilterpojo hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Bar");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Free-Wifi");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Non Smoking Room");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Adjoining Rooms");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Restaurants");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("On The Beach");
    hotelFilterpojo = new HotelFilterpojo();
    hotelFilterpojo.setFilterTitle("Room Service");
    hotelFilterList.add(hotelFilterpojo);
    hotelFilterList.add(hotelFilterpojo);
    chipGroupFacilities.removeAllViews();
    if (hotelFilterList != null
        && hotelFilterList.size() > 0) {

      for (int index = 0; index < hotelFilterList.size(); index++) {
        final Chip chip = (Chip) getLayoutInflater()
            .inflate(R.layout.view_fetchavailabilitychiplayout,
                chipGroupFacilities, false);

        chip.setPadding(10, 10, 10, 10);
        chip.setChipBackgroundColorResource(R.color.colorChipBackground);
        chip.setTextColor(getResources().getColor(R.color.colorGray));

        if (!TextUtils.isEmpty(selectedFacility) && selectedFacility
            .equalsIgnoreCase(hotelFilterList.get(index).getFilterTitle())) {
          chip.setChipBackgroundColorResource(R.color.colorGray);
          chip.setTextColor(getResources().getColor(R.color.white));
        } else {
          chip.setChipBackgroundColorResource(R.color.colorChipBackground);
          chip.setTextColor(getResources().getColor(R.color.colorGray));
        }

        final int finalIndex = index;
        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

            if (isChecked) {
              chip.setChipBackgroundColorResource(R.color.colorGray);
              chip.setTextColor(getResources().getColor(R.color.white));
              selectedFacility = hotelFilterList.get(finalIndex).getFilterTitle();
            } else {
              chip.setChipBackgroundColorResource(R.color.colorChipBackground);
              chip.setTextColor(getResources().getColor(R.color.colorGray));
              selectedFacility = "";
            }
            initFacilitiesFilter();
          }
        });

        chip.setText(hotelFilterList.get(finalIndex).getFilterTitle());
        chipGroupFacilities.addView(chip);
      }
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
   * Called to set RTL back arrow.
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
   * Called when activity view component clicked.
   *
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      case R.id.button_hotelfilter_searchforhotels:
        AppUtil.finishActivityWithAnimation(this);
        break;

      default:
        break;
    }
  }
}
