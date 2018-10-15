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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.restaurant.pojo.Detail;
import com.ehg.booking.restaurant.pojo.RestaurantFetchAvailabilityPojo;
import com.ehg.booking.restaurant.pojo.TimeSegment;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.utilities.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class shows list of available time slots.
 */
public class FetchAvailabilityActivity extends BaseActivity implements OnClickListener,
    ApiResponseListener {

  private static final String OPERATION = "LockReservation";

  private List<TimeSegment> timeSegmentList;

  private String selectedTimeSlot;
  private String restaurantId;
  private String dateStr;
  private String timeStr;
  private String numberOfPeople;

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

      restaurantId = getIntent().getStringExtra("restaurantId");
      dateStr = getIntent().getStringExtra("date");
      timeStr = getIntent().getStringExtra("time");
      numberOfPeople = getIntent().getStringExtra("numberOfPeople");

      TextView textViewDate = findViewById(R.id.textview_fetchavailablity_date);
      TextView textViewTime = findViewById(R.id.textview_fetchavailablity_time);
      TextView textViewNumberOfPeople = findViewById(R.id.textview_fetchavailablity_numberofpeople);

      String[] dateArray = dateStr.split("-");
      if (dateArray != null && dateArray.length > 0) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]));
        textViewDate.setText(calendar.getTime().toString().split(" ")[1] + " " + dateArray[2]);
      }
      textViewTime.setText(timeStr);
      textViewNumberOfPeople.setText(numberOfPeople + " Guests");

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
                final int finalIndex = index1;
                chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    if (isChecked) {
                      chip.setChipBackgroundColorResource(R.color.colorGray);
                      chip.setTextColor(getResources().getColor(R.color.white));
                      selectedTimeSlot = timeSegmentList.get(finalIndex).getReservationTime();
                    } else {
                      chip.setChipBackgroundColorResource(R.color.colorDarkerGray);
                      chip.setTextColor(getResources().getColor(R.color.colorGray));
                      selectedTimeSlot = "";
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

      //Set OnClickListener
      findViewById(R.id.imageview_header_back).setOnClickListener(this);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
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
          lockReservation();
        } else {
          AppUtil.showToast(this, getString(R.string.fetchavailability_notimeslotsavailable));
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

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to lock reservation for selected time slot.
   */
  private void lockReservation() {
    if (AppUtil.isNetworkAvailable(this)) {
      new HttpClientRequest().setApiResponseListner(this);
      JSONObject jsonObject = new JSONObject();
      JSONArray detailesArray = new JSONArray();
      JSONObject detailObject = new JSONObject();

      try {
        JSONObject deviceDetailObject = new JSONObject();
        deviceDetailObject.put("partySize", numberOfPeople);
        deviceDetailObject.put("reservationDate", dateStr);
        deviceDetailObject.put("reservationTime", selectedTimeSlot
            .replace("AM", "").replace("PM", ""));
        deviceDetailObject.put("restaurantId", Integer.parseInt(restaurantId));

        if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
            .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {

          deviceDetailObject.put("loyaltyMemberId",
              Integer.parseInt(SharedPreferenceUtils.getInstance(this)
                  .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")));
        }
        deviceDetailObject.put("deviceId", AppUtil.getDeviceId(this));

        //detailObject.put("deviceDetails", deviceDetailObject);

        detailesArray.put(deviceDetailObject);

        jsonObject.put("details", detailesArray);
        jsonObject.put("operation", OPERATION);
        jsonObject.put("feature", WebServiceUtil.FEATURE_DINNING);

      } catch (JSONException e) {
        e.printStackTrace();
      }

      StringEntity entity = null;
      try {
        entity = new StringEntity(jsonObject.toString());
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      new HttpClientRequest(this, WebServiceUtil.getUrl(WebServiceUtil.METHOD_LOCK_RESERVATION),
          entity, WebServiceUtil.CONTENT_TYPE,
          OPERATION, true).httpPostRequest();
    } else {
      AppUtil.showAlertDialog(this,
          getResources().getString(R.string.all_please_check_network_settings),
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
      if (requestMethod.equalsIgnoreCase(OPERATION)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("status")) {

        String expiresAt = "";
        String reservationToken = "";

        JSONObject jsonObject = new JSONObject(responseVal);
        JSONObject dataOject = jsonObject.getJSONObject("data");
        JSONArray details = dataOject.optJSONArray("detail");

        if (details != null && details.length() > 0) {
          JSONObject detailObject = details.optJSONObject(0);
          expiresAt = detailObject.getString("expiresAt");
          reservationToken = detailObject.getString("reservationToken");
        }

        Intent intent = new Intent(this, RestaurantBookingGuestDetailActivity.class);
        intent.putExtra("restaurantId", restaurantId);
        intent.putExtra("expiresAt", expiresAt);
        intent.putExtra("reservationToken", reservationToken);
        intent.putExtra("date", dateStr);
        intent.putExtra("time", selectedTimeSlot);
        intent.putExtra("numberOfPeople", numberOfPeople);
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
}
