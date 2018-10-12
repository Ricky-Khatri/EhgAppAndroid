/*
 *  Created by Emaar Hospitality Group on 11/10/18 11:49 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 11/10/18 11:49 AM
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

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter.CalendarDay;
import com.andexert.calendarlistview.library.SimpleMonthAdapter.SelectedDays;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import java.util.Calendar;

public class RestaurantBookingSlotActivity extends BaseActivity implements
    com.andexert.calendarlistview.library.DatePickerController, View.OnClickListener {

  private DayPickerView dayPickerView;
  private Context context;
  private LinearLayout linearLayoutTime;
  private TextView textViewTime;
  private LinearLayout linearLayoutGuestCount;
  private TextView textViewGuestCount;
  private TextView textViewNext;
  private int hour;
  private int minute;
  private TextView textViewAmPm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_restaurantbookingslot);

    context = this;

    initView();
  }

  private void initView() {

    dayPickerView = findViewById(R.id.daypickerview_restaurantbookingslot_calandar);
    linearLayoutTime = findViewById(R.id.linearlayout_restaurentbookingslot_time);
    textViewTime = findViewById(R.id.textview_restaurentbookingslot_time);
    linearLayoutGuestCount = findViewById(R.id.linearlayout_restaurentbookingslots_guestcount);
    textViewGuestCount = findViewById(R.id.textview_restaurentbookingslots_guestcount);
    textViewNext = findViewById(R.id.textview_restaurentbookingslot_next);
    textViewAmPm = findViewById(R.id.textview_restaurentbookingslot_ampm);

    linearLayoutTime.setOnClickListener(this);
    dayPickerView.setController(this);
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

      case R.id.linearlayout_restaurentbookingslot_time:
        // Get Current Time
        final Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new OnTimeSetListener() {

          @SuppressLint("SetTextI18n")
          @Override
          public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {

            String amPm = "";
           /* hour   = hourOfDay;
            minute = minutes;*/

            Calendar datetime = Calendar.getInstance();
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);

            if (datetime.get(Calendar.AM_PM) == Calendar.AM) {
              amPm = "AM";
            } else if (datetime.get(Calendar.AM_PM) == Calendar.PM) {
              amPm = "PM";
            }

            String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime.get(Calendar.HOUR) + "";

            if (Integer.parseInt(strHrsToShow) < 10) {
              strHrsToShow = "0" + strHrsToShow;
            }

            if (minutes < 10) {

              textViewTime.setText(strHrsToShow + ": 0" + minutes);
            } else {

              textViewTime.setText(strHrsToShow + ":" + minutes);
            }

            textViewAmPm.setText(amPm);
          }
        }, hour, minute, false);

        timePickerDialog.show();

        break;

      case R.id.linearlayout_restaurentbookingslots_guestcount:

        //TODO : Number picker for guest count need to be implement after discussion.

        break;

      default:
    }
  }
}
