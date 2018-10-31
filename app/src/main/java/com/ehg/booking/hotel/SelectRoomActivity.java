/*
 *  Created by Emaar Hospitality Group on 18/10/18 5:51 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 18/10/18 5:51 PM
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
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.hotel.adapter.SelectRoomAdapter;
import com.ehg.booking.hotel.adapter.SelectRoomAdapter.OnRoomItemClicklistner;
import com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo.FetchRoomAvailabilityRequestPojo;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.AverageRate;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.FetchAvailabilityResponsePojo;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.RoomStay;
import com.ehg.booking.hotel.pojo.fetchservicesrequestpojo.FetchRoomServicesPojo;
import com.ehg.booking.hotel.pojo.fetchservicesresponsepojo.FetchServicesResponsePojo;
import com.ehg.booking.hotel.pojo.fetchservicesresponsepojo.ServiceDetail;
import com.ehg.booking.hotel.pojo.roomareasearchrequestpojo.Detail;
import com.ehg.booking.hotel.pojo.roomareasearchrequestpojo.GuestCount;
import com.ehg.booking.hotel.pojo.roomareasearchrequestpojo.RoomAreaSearchRequestPojo;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class allows to select hotel room.
 */
public class SelectRoomActivity extends BaseActivity
    implements OnRoomItemClicklistner, OnClickListener, ApiResponseListener {

  private static final String FETCH_ROOM_SERVICES = "FetchRoomServices";
  private Context context;
  private RecyclerView recyclerViewRoomList;
  private TextView textViewHeaderTitle;
  private AppCompatImageView headerBackButton;
  private TextView textViewNext;

  private int numberOfRooms = 0;

  private TextView textViewSelectRoomTitle;
  private TextView textViewDatesLabel;
  private TextView textViewNumberOfGuestsLabel;
  private TextView textViewRoomCountLabel;
  private TextView textViewAmount;

  private AverageRate averageRate;
  private SelectRoomAdapter selectRoomAdapter;
  private int selectedRatePosition = 0;
  private int roomNumber = 1;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      setContentView(R.layout.activity_hotelroomselection);
      context = this;

      initView();
    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init activity view components.
   */
  private void initView() {

    textViewAmount = findViewById(R.id.textview_hotelroomselection_amount);
    textViewDatesLabel = findViewById(R.id.textview_hotelroomselection_date);
    textViewNumberOfGuestsLabel = findViewById(R.id.textview_hotelroomselection_guestcount);
    textViewRoomCountLabel = findViewById(R.id.textview_hotelroomselection_roomcount);

    if (getIntent() != null && getIntent().getStringExtra("dates") != null) {
      textViewDatesLabel.setText(getIntent().getStringExtra("dates"));
    }
    if (getIntent() != null && getIntent().getStringExtra("numberOfGuests") != null) {
      textViewNumberOfGuestsLabel.setText(getIntent().getStringExtra("numberOfGuests"));
    }
    if (getIntent() != null && getIntent().getStringExtra("numberOfRooms") != null) {
      textViewRoomCountLabel.setText(getIntent().getStringExtra("numberOfRooms"));
    }
    numberOfRooms = SharedPreferenceUtils.getInstance(this)
        .getIntValue(SharedPreferenceUtils.SELECTED_ROOM_COUNT, 0);

    textViewSelectRoomTitle = findViewById(R.id.textView_hotelroomselection_selectroom);
    textViewSelectRoomTitle.setText("Select the Room (" + roomNumber + ")");
    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    if (getIntent() != null && getIntent().getStringExtra("title") != null) {
      textViewHeaderTitle.setText(getIntent().getStringExtra("title"));
    }
    headerBackButton = findViewById(R.id.imageview_header_back);
    textViewNext = findViewById(R.id.textview_hotelroomselection_next);
    recyclerViewRoomList = findViewById(R.id.recyclerview_hotelroomselection);
    recyclerViewRoomList.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewRoomList.setHasFixedSize(true);

    //Set OnClickListener
    textViewNext.setOnClickListener(this);
    headerBackButton.setOnClickListener(this);

    //Set Adapter
    FetchAvailabilityResponsePojo fetchAvailabilityResponsePojo = JsonParserUtil.getInstance(this)
        .getFetchAvailabilityResponsePojo();
    if (fetchAvailabilityResponsePojo.getData() != null
        && fetchAvailabilityResponsePojo.getData().getDetail() != null
        && fetchAvailabilityResponsePojo.getData().getDetail().size() > 0
        && fetchAvailabilityResponsePojo.getData().getDetail().get(0).getResponseData() != null
        && fetchAvailabilityResponsePojo.getData()
        .getDetail().get(0).getResponseData().getRoomStays() != null
        && fetchAvailabilityResponsePojo.getData().getDetail()
        .get(0).getResponseData().getRoomStays()
        .size() > 0) {
      List<RoomStay> roomStayList = fetchAvailabilityResponsePojo
          .getData().getDetail().get(0)
          .getResponseData().getRoomStays();

      String baseUrl = fetchAvailabilityResponsePojo
          .getData().getDetail().get(0)
          .getResponseData().getImageBaseUrl();
      selectRoomAdapter = new SelectRoomAdapter(context, this, roomStayList,
          baseUrl);
      recyclerViewRoomList.setAdapter(selectRoomAdapter);
      AppUtil.animateRecyclerView(context, recyclerViewRoomList,
          R.anim.layout_animation_from_bottom);

      if (averageRate == null) {
        averageRate = fetchAvailabilityResponsePojo.getData().getDetail().get(0)
            .getResponseData().getRoomStays().get(0)
            .getRoomTypes().get(0).getAverageRates().get(0);

        textViewAmount.setText(SharedPreferenceUtils.getInstance(this)
            .getStringValue(SharedPreferenceUtils.APP_CURRENCY, "AED")
            + " " + averageRate.getRate());

      }
    }
  }

  /**
   * Called when list view item clicked.
   *
   * @param position clicked item position
   * @param view clicked item
   */
  @Override
  public void onItemClick(int position, View view, List<AverageRate> averageRateList) {

    Intent intent = null;
    switch (view.getId()) {

      case R.id.textview_itemhoteroomselection_selection:
        fetchRoomServices();
        break;
      case R.id.sliderlayout_itemhotelroomselection_slider:
        intent = new Intent(context, HotelRoomDetailActivity.class);
        AppUtil.startActivityWithAnimation(this, intent, false);
        break;
      case R.id.linearlayout_itemhotelroomselection_allrates:
        selectedRatePosition = position;
        if (averageRateList != null && averageRateList.size() > 0) {
          Gson gson = new Gson();
          String averageListString = gson.toJson(averageRateList);
          intent = new Intent(context, AvailableRoomRatesActivity.class);
          intent.putExtra("averageListString", averageListString);
          startActivityForResult(intent, 1);
        }
        break;
      default:
        break;
    }
  }

  /**
   * Called when activity returns a result.
   *
   * @param requestCode requestCode
   * @param resultCode resultCode
   * @param data data
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (data != null && data.getStringExtra("averageRate") != null && requestCode == 1) {
      Type type = new TypeToken<AverageRate>() {
      }.getType();
      averageRate = new Gson()
          .fromJson(data.getStringExtra("averageRate"), type);
      textViewAmount.setText(SharedPreferenceUtils.getInstance(this)
          .getStringValue(SharedPreferenceUtils.APP_CURRENCY, "AED") + " "
          + averageRate.getRate());
      if (selectRoomAdapter != null) {
        selectRoomAdapter.updateSelectedRate(selectedRatePosition, averageRate.getRate());
      }
    }
  }

  /**
   * Called when activity item view clicked.
   *
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {

    try {
      Intent intent = null;
      switch (view.getId()) {
        case R.id.imageview_header_back:
          AppUtil.finishActivityWithAnimation(this);
          break;

        case R.id.textview_hotelroomselection_next:
          if (roomNumber < numberOfRooms) {
            roomNumber++;
            textViewSelectRoomTitle.setText("Select the Room (" + roomNumber + ")");
          } else if (roomNumber >= numberOfRooms) {
            if (averageRate == null) {
              FetchAvailabilityResponsePojo fetchAvailabilityResponsePojo = JsonParserUtil
                  .getInstance(this)
                  .getFetchAvailabilityResponsePojo();
              averageRate = fetchAvailabilityResponsePojo.getData().getDetail().get(0)
                  .getResponseData().getRoomStays().get(0)
                  .getRoomTypes().get(0).getAverageRates().get(0);
            }
            intent = new Intent(context, RoomBookingGuestDetailActivity.class);
            intent.putExtra("averageRate", new Gson().toJson(averageRate));
            intent.putExtra("title", textViewHeaderTitle.getText().toString());
            AppUtil.startActivityWithAnimation(this, intent, false);
          }
          break;

        default:
          break;
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
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
   * Get a diff between two dates.
   *
   * @param oldDate the old date
   * @param newDate the new date
   * @return the diff value, in the days
   */
  public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
    try {
      return Math.abs(TimeUnit.DAYS.convert(format.parse(newDate).getTime()
          - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS));
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to fetch room services.
   */
  private void fetchRoomServices() {
    try {
      if (AppUtil.isNetworkAvailable(context)) {
        new HttpClientRequest().setApiResponseListner(this);

        FetchRoomAvailabilityRequestPojo fetchRoomAvailabilityRequestPojo = JsonParserUtil
            .getInstance(this)
            .getFetchAvailabilityRequestPojo();
        List<com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo.Detail>
            fetchAvailablityDetailList = fetchRoomAvailabilityRequestPojo.getDetails();

        if (fetchAvailablityDetailList != null && fetchAvailablityDetailList.size() > 0) {

          com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo.Detail
              fetchAvailabilityDetail = fetchAvailablityDetailList.get(0);

          com.ehg.booking.hotel.pojo.fetchservicesrequestpojo.Detail detail =
              new com.ehg.booking.hotel.pojo.fetchservicesrequestpojo.Detail();

          //TODO: Integer.parseInt(roomAreaDetail.getSearchCriteria().getHotelIds().get(0))
          detail.setIbuId(2);

          detail.setCheckInDate(fetchAvailabilityDetail.getCheckInDate());
          detail.setCheckOutDate(fetchAvailabilityDetail.getCheckOutDate());
          detail.setTotalRooms(fetchAvailabilityDetail.getTotalRooms());
          List<GuestCount> guestCountList = new ArrayList<>();
          GuestCount guestCount = new GuestCount();
          guestCount.setAgeQualifyingCode("10");
          guestCount.setCount(fetchAvailabilityDetail.getTotalAdults());
          guestCountList.add(guestCount);
          guestCount = new GuestCount();
          guestCount.setAgeQualifyingCode("8");
          guestCount.setCount(fetchAvailabilityDetail.getTotalChildren());
          guestCountList.add(guestCount);
          guestCount = new GuestCount();
          guestCount.setAgeQualifyingCode("7");
          guestCount.setCount(fetchAvailabilityDetail.getTotalInfants());
          guestCountList.add(guestCount);
          detail.setTotalAdults(guestCountList.get(0).getCount());

          List<Integer> childreAges = new ArrayList<>();
          //TODO: Make it dynamic
          childreAges.add(guestCountList.get(1).getCount());
          detail.setTotalChildren(guestCountList.get(1).getCount());
          detail.setChildrenAges(childreAges);
          detail.setTotalInfants(guestCountList.get(2).getCount());

          detail.setRatePlanPackageRequired(true);
          detail.setRoomTypeCode("");//TODO: Pass actual value

          try {
            if (averageRate != null) {
              String ratePlanCode = averageRate.getRatePlanCode();
              detail.setRatePlanCode(ratePlanCode);
              detail.setRatePlanType(
                  JsonParserUtil.getRoomRatePlanType(averageRate.getRatePlanType()));
            } else {
              FetchAvailabilityResponsePojo fetchAvailabilityResponsePojo = JsonParserUtil
                  .getInstance(this)
                  .getFetchAvailabilityResponsePojo();
              String ratePlanCode = fetchAvailabilityResponsePojo.getData().getDetail().get(0)
                  .getResponseData().getRoomStays().get(0)
                  .getRoomTypes().get(0).getAverageRates().get(0).getRatePlanCode();

              detail.setRatePlanCode(ratePlanCode);
              detail.setRatePlanType(JsonParserUtil.getRoomRatePlanType(
                  fetchAvailabilityResponsePojo.getData().getDetail().get(0)
                      .getResponseData().getRoomStays().get(0)
                      .getRoomTypes().get(0).getAverageRates().get(0).getRatePlanType()));
            }
          } catch (NullPointerException n) {
            n.printStackTrace();
            detail.setRatePlanCode("2248004");
            detail.setRatePlanType(JsonParserUtil.getRoomRatePlanType("Regular"));
          }

          //Calculate stay duration
          int dayDifference = (int) getDateDiff(
              new SimpleDateFormat("yyyy-MM-dd"),
              detail.getCheckOutDate(), detail.getCheckInDate());
          detail.setDurationOfStay(dayDifference);

          detail.setCurrencyCode(SharedPreferenceUtils.getInstance(this)
              .getStringValue(SharedPreferenceUtils.APP_CURRENCY, "AED"));
          detail.setLanguage(SharedPreferenceUtils.getInstance(this)
              .getStringValue(SharedPreferenceUtils.APP_LANGUAGE, "en"));

          if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
              .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {
            detail.setLoyaltyMemberId(Integer.parseInt(SharedPreferenceUtils.getInstance(this)
                .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")));
          }
          detail.setDeviceId(AppUtil.getDeviceId(this));

          FetchRoomServicesPojo fetchRoomServicesPojo =
              new FetchRoomServicesPojo();
          List<com.ehg.booking.hotel.pojo.fetchservicesrequestpojo.Detail> detailList =
              new ArrayList<>();
          detailList.add(detail);
          fetchRoomServicesPojo.setFeature("roomReservation");
          fetchRoomServicesPojo.setOperation("fetchServices");
          fetchRoomServicesPojo.setDetails(detailList);

          Gson gson = new Gson();
          String requestString = gson
              .toJson(fetchRoomServicesPojo, FetchRoomServicesPojo.class);

          StringEntity entity = null;
          try {
            entity = new StringEntity(requestString);
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }

          new HttpClientRequest(context,
              WebServiceUtil.getUrl(WebServiceUtil.METHOD_FETCH_ROOM_SERVICES),
              entity, WebServiceUtil.CONTENT_TYPE,
              FETCH_ROOM_SERVICES, true).httpPostRequest();
        }
      } else {
        AppUtil.showAlertDialog((AppCompatActivity) context,
            context.getResources().getString(R.string.all_please_check_network_settings),
            false, "", true, null);
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (IndexOutOfBoundsException iob) {
      iob.printStackTrace();
    } catch (NumberFormatException iob) {
      iob.printStackTrace();
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
      if (requestMethod.equalsIgnoreCase(FETCH_ROOM_SERVICES)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("Status")) {

        FetchServicesResponsePojo fetchServicesResponsePojo = new Gson()
            .fromJson(responseVal,
                new TypeToken<FetchServicesResponsePojo>() {
                }.getType());

        if (fetchServicesResponsePojo.getData() != null
            && fetchServicesResponsePojo.getData().getDetail() != null
            && fetchServicesResponsePojo.getData().getDetail().size() > 0
            && fetchServicesResponsePojo.getData().getDetail().get(0).getResponseData() != null
            && fetchServicesResponsePojo.getData().getDetail().get(0).getResponseData()
            .getServiceDetails() != null
            && fetchServicesResponsePojo.getData().getDetail().get(0).getResponseData()
            .getServiceDetails().size() > 0) {

          List<ServiceDetail> serviceDetailList = fetchServicesResponsePojo.getData().getDetail()
              .get(0).getResponseData().getServiceDetails();

          //Show enhance stay layout
          if (selectRoomAdapter != null) {
            selectRoomAdapter.showEnhanceStayLayout(selectedRatePosition, serviceDetailList);
          }
        }

      } else if (requestMethod.equalsIgnoreCase(FETCH_ROOM_SERVICES)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && !new JSONObject(responseVal).getBoolean("Status")) {

        JSONObject dataObject = new JSONObject(responseVal).getJSONObject("Data");

        if (dataObject != null) {
          JSONArray detailArray = dataObject.optJSONArray("Detail");
          if (detailArray != null && detailArray.length() > 0) {
            JSONObject validationError = detailArray.optJSONObject(0)
                .optJSONArray("ValidationErrors").optJSONObject(0);

            AppUtil.showAlertDialog((AppCompatActivity) context,
                validationError.getString("ErrorMessage"), false,
                getResources().getString(R.string.dialog_errortitle), true, null);
          }
        }
      } else {
        AppUtil.showAlertDialog(this,
            new JSONObject(responseVal).getString("Message"), false,
            getResources().getString(R.string.dialog_errortitle), true, null);
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
