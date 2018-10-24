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
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import com.andexert.calendarlistview.library.DatePickerController;
import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter.CalendarDay;
import com.andexert.calendarlistview.library.SimpleMonthAdapter.SelectedDays;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.customview.NumberPickerDialog;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.utilities.AppUtil;
import com.loopj.android.http.RequestParams;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class allows to select date, time and number of people for fetching available time slots.
 */
public class RestaurantBookingSlotActivity extends BaseActivity implements
    DatePickerController, OnClickListener, ApiResponseListener, OnValueChangeListener {

  private static final String FETCH_AVAILABILITY = "fetchAvailability";

  private DayPickerView dayPickerView;
  private Context context;
  private LinearLayout linearLayoutTime;
  private TextView textViewTime;
  private LinearLayout linearLayoutGuestCount;
  private static TextView textViewGuestCount;
  private TextView textViewNext;
  private int hour;
  private int minute;
  private TextView textViewAmPm;

  private String dateStr;
  private String timeStr;
  private static String numberOfPeopleStr;
  private String restaurantId;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restaurantbookingslot);

    try {
      context = this;
      initView();
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init ui components of this screen.
   */
  private void initView() {

    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText("Na3Na3");
    dayPickerView = findViewById(R.id.daypickerview_restaurantbookingslot_calandar);
    linearLayoutTime = findViewById(R.id.linearlayout_restaurentbookingslot_time);
    textViewTime = findViewById(R.id.textview_restaurentbookingslot_time);
    String[] currentTimes = Calendar.getInstance().getTime().toString().split(" ");
    if (currentTimes != null && currentTimes.length > 0) {
      int min = Integer.parseInt(currentTimes[3].split(":")[1]);
      if (min <= 55) {
        min = min + 5;
      } else if (min > 55) {
        min = (min - 55) + 5;
      }
      String minStr = min + "";
      if (min < 10) {
        minStr = "0" + min;
      }
      String currentTime = currentTimes[3].split(":")[0] + ":" + minStr;
      textViewTime.setText(currentTime);
    }
    linearLayoutGuestCount = findViewById(R.id.linearlayout_restaurentbookingslots_guestcount);
    textViewGuestCount = findViewById(R.id.textview_restaurentbookingslots_guestcount);
    textViewNext = findViewById(R.id.textview_restaurentbookingslot_next);
    textViewAmPm = findViewById(R.id.textview_restaurentbookingslot_ampm);

    //Set OnLClickListener
    textViewNext.setOnClickListener(this);
    linearLayoutTime.setOnClickListener(this);
    dayPickerView.setController(this);
    linearLayoutGuestCount.setOnClickListener(this);
    findViewById(R.id.imageview_header_back).setOnClickListener(this);

    Intent intent = getIntent();

    if (intent != null && intent.getStringExtra("restaurantId") != null) {
      restaurantId = getIntent().getStringExtra("restaurantId");
    }

    if (intent != null && intent.getStringExtra("key") == null) {
      SharedPreferenceUtils.getInstance(this)
          .setValue(SharedPreferenceUtils.RESTAURANT_CONFIRMATION_NUMBER, "");
    }
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    if (intent != null && intent.getStringExtra("key") == null) {
      SharedPreferenceUtils.getInstance(this)
          .setValue(SharedPreferenceUtils.RESTAURANT_CONFIRMATION_NUMBER, "");
    }
  }

  /**
   * Called to get max years.
   *
   * @return integer
   */
  @Override
  public int getMaxYear() {
    return 2050;
  }

  /**
   * Called to get day of month.
   *
   * @param year year
   * @param month month
   * @param day day
   */
  @Override
  public void onDayOfMonthSelected(int year, int month, int day) {
    month++;
    String dayString = day + "";
    String monthString = month + "";
    if (day < 10) {
      dayString = "0" + day;
    }
    if (month < 10) {
      monthString = "0" + month;
    }
    dateStr = year + "-" + monthString + "-" + dayString;
  }

  /**
   * Called to get date range selected.
   *
   * @param selectedDays selected days
   */
  @Override
  public void onDateRangeSelected(SelectedDays<CalendarDay> selectedDays) {

  }

  /**
   * Called when click event initialized on view component.
   *
   * @param view clicked view
   */
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

            /*Calendar datetime = Calendar.getInstance();
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);

            if (datetime.get(Calendar.AM_PM) == Calendar.AM) {
              am_pm = "AM";
            } else if (datetime.get(Calendar.AM_PM) == Calendar.PM) {
              am_pm = "PM";
            }

            String strHrsToShow =
                (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime.get(Calendar.HOUR) + "";

            if (Integer.parseInt(strHrsToShow) < 10) {
              strHrsToShow = "0" + strHrsToShow;
            }

            if (minutes < 10) {

              textViewTime.setText(strHrsToShow + ": 0" + minutes);
            } else {

              textViewTime.setText(strHrsToShow + ":" + minutes);
            }

            textViewAmPm.setText(am_pm);*/

            String hourStr = hourOfDay + "";
            String minuteStr = minutes + "";
            if (hourOfDay < 10) {
              hourStr = "0" + hourOfDay;
            }

            if (minutes < 10) {
              minuteStr = "0" + minutes;
            }

            textViewTime.setText(hourStr + ":" + minuteStr);
          }
        }, hour, minute, true);

        timePickerDialog.show();
        break;

      case R.id.linearlayout_restaurentbookingslots_guestcount:
        showNumberPicker();
        break;

      case R.id.textview_restaurentbookingslot_next:
        fetchRestaurantSlotAvailability();
        break;

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      default:
        break;
    }
  }

  /**
   * Called to init fetch slot availability api.
   */
  private void fetchRestaurantSlotAvailability() {
    if (TextUtils.isEmpty(dateStr)) {
      int date = Calendar.getInstance().get(Calendar.DATE);
      int month = Calendar.getInstance().get(Calendar.MONTH);
      month++;
      int year = Calendar.getInstance().get(Calendar.YEAR);

      String dateString = date + "";
      String monthString = month + "";
      if (date < 10) {
        dateString = "0" + date;
      }

      if (month < 10) {
        monthString = "0" + month;
      }

      dateStr = year + "-" + monthString + "-" + dateString;
    }
    if (TextUtils.isEmpty(numberOfPeopleStr)) {
      numberOfPeopleStr = "2";
    }
    restaurantId = "1";
    numberOfPeopleStr = textViewGuestCount.getText().toString();

    //TODO: Time validation
    timeStr = textViewTime.getText().toString();
    int hour = Integer.parseInt(timeStr.split(":")[0]);
    int minute = Integer.parseInt(timeStr.split(":")[1]);
    Calendar currentCalender = Calendar.getInstance();
    currentCalender
        .set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    currentCalender.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));

    Calendar selectedCalender = Calendar.getInstance();
    selectedCalender.set(Calendar.HOUR_OF_DAY, hour);
    selectedCalender.set(Calendar.MINUTE, minute);

    if (selectedCalender.equals(currentCalender)) {
      if (minute < 60) {
        minute = minute + 1;
      } else {
        minute = 0;
        hour = hour + 1;
      }
      timeStr = hour + ":" + minute;
    } else if (selectedCalender.before(currentCalender)) {
      AppUtil.showToast(this, getString(R.string.all_timevldationalert));
    } else {
      fetchAvailability(dateStr, timeStr,
          numberOfPeopleStr, restaurantId);
    }
  }

  /**
   * Called when number picker value changed.
   *
   * @param numberPicker numberPicker object
   * @param oldValue old value
   * @param newValue new value
   */
  @Override
  public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {

    numberOfPeopleStr = newValue + "";
    //textViewGuestCount.setText(numberOfPeopleStr);
  }

  /**
   * Called to show number picker.
   */
  public void showNumberPicker() {
    String numberOfPeople = textViewGuestCount.getText().toString().trim();
    if (TextUtils.isEmpty(numberOfPeople)) {
      numberOfPeople = "1";
    }
    AppUtil.numberPickerdefaultSelectedNumber = Integer.parseInt(numberOfPeople);

    NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
    numberPickerDialog.setValueChangeListener(this);
    numberPickerDialog.show(getSupportFragmentManager(), "Select number of people");
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

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to fetch restaurant slots availability.
   *
   * @param date date
   * @param time time
   * @param numberOfPeople number of people
   */
  private void fetchAvailability(String date, String time,
      String numberOfPeople, String restaurantId) {
    if (AppUtil.isNetworkAvailable(this)) {
      new HttpClientRequest().setApiResponseListner(this);

      String url = "";

      String loyaltyMemberId = SharedPreferenceUtils.getInstance(this).getStringValue(
          SharedPreferenceUtils.LOYALTY_MEMBER_ID, "");
      if (!TextUtils.isEmpty(loyaltyMemberId)) {

        url = WebServiceUtil.getUrl(WebServiceUtil.METHOD_GET_RESTAURANT_AVAILABILITY)
            + restaurantId
            + "?loyaltyMemberId="
            + loyaltyMemberId + "&tentativeDate=" + date + "&tentativeTime=" + time + "&partySize="
            + numberOfPeople;

      } else {

        url = WebServiceUtil.getUrl(WebServiceUtil.METHOD_GET_RESTAURANT_AVAILABILITY)
            + restaurantId
            + "?tentativeDate=" + date + "&tentativeTime=" + time + "&partySize="
            + numberOfPeople;
      }

      new HttpClientRequest(this, url,
          new RequestParams(), WebServiceUtil.CONTENT_TYPE,
          FETCH_AVAILABILITY, true).httpGetRequest();
    } else {
      AppUtil.showAlertDialog((AppCompatActivity) context,
          context.getResources().getString(R.string.all_please_check_network_settings),
          false, "", true, null);
    }
  }

  /**
   * Called when response received from api call.
   *
   * @param responseVal response
   * @param requestMethod request method name
   */
  @Override
  public void onSuccessResponse(String responseVal, String requestMethod) {

    try {
      if (requestMethod.equalsIgnoreCase(FETCH_AVAILABILITY)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("status")) {

        Intent intent = new Intent(this, FetchAvailabilityActivity.class);
        intent.putExtra("availableSlotes", responseVal);
        intent.putExtra("restaurantId", restaurantId);
        intent.putExtra("date", dateStr);
        intent.putExtra("time", timeStr);
        intent.putExtra("numberOfPeople", numberOfPeopleStr);
        AppUtil.startActivityWithAnimation(this, intent, false);

      } else if (responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && !new JSONObject(responseVal).getBoolean("status")) {

        JSONObject dataObject = new JSONObject(responseVal).getJSONObject("data");

        if (dataObject != null) {
          JSONArray detailArray = dataObject.optJSONArray("detail");
          if (detailArray != null && detailArray.length() > 0) {
            JSONObject validationError = detailArray.optJSONObject(0)
                .optJSONArray("validationErrors").optJSONObject(0);

            AppUtil.showAlertDialog(this,
                validationError.getString("ErrorMessage"), false,
                getResources().getString(R.string.dialog_errortitle), true, null);
          }
        } else {
          AppUtil.showAlertDialog(this,
              new JSONObject(responseVal).getString("message"), false,
              getResources().getString(R.string.dialog_errortitle), true, null);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called on failure api response.
   *
   * @param errorMessage error string
   */
  @Override
  public void onFailureResponse(String errorMessage) {
    AppUtil.showAlertDialog(this, errorMessage, false,
        getResources().getString(R.string.dialog_errortitle), true, null);
  }

  /**
   * Called when number picker action (Ok/Cancel) selected.
   */
  public static void onNumberPickerActionSelected(boolean isCanceled, int value) {
    if (!isCanceled) {
      numberOfPeopleStr = value + "";
      textViewGuestCount.setText(numberOfPeopleStr);
    }
  }
}
