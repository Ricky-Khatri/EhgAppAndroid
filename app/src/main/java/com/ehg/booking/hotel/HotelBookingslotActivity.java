/*
 *  Created by Emaar Hospitality Group on 17/10/18 6:00 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 17/10/18 6:00 PM
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
import android.view.View;
import android.view.View.OnClickListener;
import com.andexert.calendarlistview.library.DatePickerController;
import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter.CalendarDay;
import com.andexert.calendarlistview.library.SimpleMonthAdapter.SelectedDays;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;


/**
 * This class allows users to enter check-in,check-out and select date with location to book room.
 */
public class HotelBookingslotActivity extends BaseActivity implements
    DatePickerController, OnClickListener {

  private DayPickerView dayPickerView;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {

      setContentView(R.layout.activity_hotelbookingslot);
      initView();

    } catch (NullPointerException e) {

      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Called to init ui components of this screen.
   */
  private void initView() {

    dayPickerView = findViewById(R.id.daypickerview_hotelbookingslot_calandar);
    dayPickerView.setController(this);
    findViewById(R.id.imageview_header_back).setOnClickListener(this);
  }

  @Override
  public int getMaxYear() {
    return 2050;
  }

  @Override
  public void onDayOfMonthSelected(int year, int month, int day) {

  }

  @Override
  public void onDateRangeSelected(SelectedDays<CalendarDay> selectedDays) {

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
}
