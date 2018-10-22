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
import android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.andexert.calendarlistview.library.DatePickerController;
import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter.CalendarDay;
import com.andexert.calendarlistview.library.SimpleMonthAdapter.SelectedDays;
import com.diegocarloslima.fgelv.lib.FloatingGroupExpandableListView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;


/**
 * This class allows users to enter check-in,check-out and select date with location to book room.
 */
public class HotelBookingslotActivity extends BaseActivity implements
    DatePickerController, OnClickListener {

  private DayPickerView dayPickerView;
  private LinearLayout linearlayoutCheckIn;
  private LinearLayout linearlayoutCheckOut;
  private LinearLayout linearlayoutGuestRoom;
  private LinearLayout linearlayoutDestination;
  private TextView textViewtitle;
  private LinearLayout linearlayoutGuestRoomCount;
  private AppCompatImageView imageViewRoomMinus;
  private TextView textViewRoomcount;
  private AppCompatImageView imageViewRoomPlus;
  private AppCompatImageView imageViewAdultsMinus;
  private AppCompatImageView imageViewAdultsPlus;
  private TextView textViewAdultsCount;
  private AppCompatImageView imageViewChildMinus;
  private AppCompatImageView imageViewChildPlus;
  private TextView textViewChildCount;
  private AppCompatImageView imageViewInfantMinus;
  private AppCompatImageView imageViewInfantPlus;
  private TextView textViewInfantCount;
  private FloatingGroupExpandableListView expendableListViewDestination;

  private String[] parent = {"Dubai", "Egypt"};
  private String[] child = {"Dubai Marina", "Dera", "Downtown", "Bur Dubai",
      "Emirates Hills", "Al Alamein",
      "Matrouh"};

  private int[] parentImage = {R.drawable.placeholder, R.drawable.placeholder};

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
    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    if (getIntent() != null && getIntent().getStringExtra("title") != null) {
      textViewHeaderTitle.setText(getIntent().getStringExtra("title"));
    }
    dayPickerView = findViewById(R.id.daypickerview_hotelbookingslot_calandar);
    dayPickerView.setController(this);
    findViewById(R.id.imageview_header_back).setOnClickListener(this);

    linearlayoutCheckIn = findViewById(R.id.linearlayout_hotelbookingslot_checkin);
    linearlayoutCheckOut = findViewById(R.id.linearlayout_hotelbookingslot_checkout);
    linearlayoutGuestRoom = findViewById(R.id.linearlayout_hotelbookingslot_guestroom);
    linearlayoutDestination = findViewById(R.id.linearlayout_hotelbookingslot_destination);

    textViewtitle = findViewById(R.id.textview_hotelbookingslot_viewtitle);
    linearlayoutGuestRoomCount = findViewById(R.id.linearlayout_hotelbookingslot_guestroomnumber);
    imageViewRoomMinus = findViewById(R.id.imageview_hotelbookingslot_roomminus);
    imageViewRoomPlus = findViewById(R.id.imageview_hotelbookingslot_roomplus);
    textViewRoomcount = findViewById(R.id.imageview_hotelbookingslot_roomcount);

    imageViewAdultsMinus = findViewById(R.id.imageview_hotelbookingslot_adultminus);
    imageViewAdultsPlus = findViewById(R.id.imageview_hotelbookingslot_adultplus);
    textViewAdultsCount = findViewById(R.id.textview_hotelbookingslot_adultcount);

    imageViewChildMinus = findViewById(R.id.imageview_hotelbookingslot_childminus);
    imageViewChildPlus = findViewById(R.id.imageview_hotelbookingslot_childplus);
    textViewChildCount = findViewById(R.id.textview_hotelbookingslot_childcount);

    imageViewInfantMinus = findViewById(R.id.imageview_hotelbookingslot_infantminus);
    imageViewInfantPlus = findViewById(R.id.imageview_hotelbookingslot_infantplus);
    textViewInfantCount = findViewById(R.id.textview_hotelbookingslot_infantcount);
    expendableListViewDestination = findViewById(
        R.id.expandablelistview_hotelbookingslot_destination);

    linearlayoutCheckIn.setOnClickListener(this);
    linearlayoutCheckOut.setOnClickListener(this);
    linearlayoutGuestRoom.setOnClickListener(this);
  }

  /**
   * Called to get max calender years.
   * @return years
   */
  @Override
  public int getMaxYear() {
    return 2050;
  }

  /**
   * Called to get year, month, day from calender.
   * @param year selected year
   * @param month selected month
   * @param day selected day
   */
  @Override
  public void onDayOfMonthSelected(int year, int month, int day) {

  }

  /**
   * Called to get day range.
   * @param selectedDays selected day range
   */
  @Override
  public void onDateRangeSelected(SelectedDays<CalendarDay> selectedDays) {

  }

  /**
   * Called when view clicked on this screen.
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      case R.id.linearlayout_hotelbookingslot_guestroom:
        expendableListViewDestination.setVisibility(View.GONE);
        linearlayoutGuestRoomCount.setVisibility(View.VISIBLE);
        dayPickerView.setVisibility(View.GONE);
        break;

      case R.id.linearlayout_hotelbookingslot_checkin:
        expendableListViewDestination.setVisibility(View.GONE);
        linearlayoutGuestRoomCount.setVisibility(View.GONE);
        dayPickerView.setVisibility(View.VISIBLE);
        break;

      case R.id.linearlayout_hotelbookingslot_checkout:
        linearlayoutGuestRoomCount.setVisibility(View.GONE);
        dayPickerView.setVisibility(View.VISIBLE);
        expendableListViewDestination.setVisibility(View.GONE);
        break;

      case R.id.linearlayout_hotelbookingslot_destination:
        linearlayoutGuestRoomCount.setVisibility(View.GONE);
        dayPickerView.setVisibility(View.GONE);
        expendableListViewDestination.setVisibility(View.VISIBLE);
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
}
