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

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.andexert.calendarlistview.library.DatePickerController;
import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter.CalendarDay;
import com.andexert.calendarlistview.library.SimpleMonthAdapter.SelectedDays;
import com.diegocarloslima.fgelv.lib.FloatingGroupExpandableListView;
import com.diegocarloslima.fgelv.lib.WrapperExpandableListAdapter;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.hotel.pojo.roomareasearchpojo.Detail;
import com.ehg.booking.hotel.pojo.roomareasearchpojo.GuestCount;
import com.ehg.booking.hotel.pojo.roomareasearchpojo.RoomAreaSearchRequestPojo;
import com.ehg.booking.hotel.pojo.roomareasearchpojo.SearchCriteria;
import com.ehg.booking.hotel.pojo.roomareasearchpojo.TimeSpan;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.search.adapter.SearchAdapter;
import com.ehg.search.pojo.SearchChildPojo;
import com.ehg.search.pojo.SearchGroupPojo;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
import com.google.gson.Gson;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * This class allows users to enter check-in,check-out and select date with location to book room.
 */
public class HotelBookingSlotActivity extends BaseActivity implements
    DatePickerController, OnClickListener, ApiResponseListener {

  private static final String SEARCH_ROOM_AREA = "SearchRoomArea";
  private DayPickerView dayPickerView;

  private LinearLayout linearlayoutCheckIn;
  private LinearLayout linearlayoutCheckOut;
  private LinearLayout linearlayoutGuestRoom;
  private LinearLayout linearlayoutDestination;
  private LinearLayout linearlayoutGuestRoomCount;

  private TextView textViewtitle;
  private TextView textViewRoomCount;
  private TextView textViewChildCount;
  private TextView textViewAdultsCount;
  private TextView textViewInfantCount;
  private TextView textViewChekinDate;
  private TextView textViewCheckoutDate;
  private TextView textViewGuestRooms;
  private TextView textViewDestination;
  private TextView textViewNext;

  private AppCompatImageView imageViewRoomMinus;
  private AppCompatImageView imageViewRoomPlus;
  private AppCompatImageView imageViewAdultsMinus;
  private AppCompatImageView imageViewAdultsPlus;
  private AppCompatImageView imageViewChildMinus;
  private AppCompatImageView imageViewChildPlus;
  private AppCompatImageView imageViewInfantMinus;
  private AppCompatImageView imageViewInfantPlus;

  private int numberOfRooms = 0;
  private int numberOfChild = 0;
  private int numberOfAdults = 0;
  private int numberOfInfants = 0;
  private int totalGuests = 0;

  private FloatingGroupExpandableListView expendableListViewDestination;

  private String[] parent = {"Dubai", "Egypt"};
  private String[] child = {"Dubai Marina", "Dera", "Downtown", "Bur Dubai",
      "Emirates Hills", "Al Alamein",
      "Matrouh"};

  private int[] parentImage = {R.drawable.placeholder, R.drawable.placeholder};
  private Context context;

  private String checkinDateStr;
  private String checkoutDateStr;
  private String startDateStr;
  private String endDateStr;

  private boolean isDateRangeSelected;

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
      context = this;
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
    textViewHeaderTitle.setText(getResources().getString(R.string.splash_book_hotel_title));
    if (getIntent() != null && getIntent().getStringExtra("title") != null) {
      textViewHeaderTitle.setText(getIntent().getStringExtra("title"));
    }

    textViewNext = findViewById(R.id.textview_hotelbookingslot_next);
    textViewChekinDate = findViewById(R.id.textviewhotelbookingslot_checkin);
    textViewCheckoutDate = findViewById(R.id.textviewhotelbookingslot_checkout);
    textViewGuestRooms = findViewById(R.id.textview_hotelbookingslot_rooms);
    textViewDestination = findViewById(R.id.textview_hotelbookingslot_destination);
    textViewRoomCount = findViewById(R.id.imageview_hotelbookingslot_roomcount);
    textViewAdultsCount = findViewById(R.id.textview_hotelbookingslot_adultcount);
    textViewInfantCount = findViewById(R.id.textview_hotelbookingslot_infantcount);
    textViewChildCount = findViewById(R.id.textview_hotelbookingslot_childcount);

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
    textViewRoomCount = findViewById(R.id.imageview_hotelbookingslot_roomcount);

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

    List<SearchGroupPojo> destinationList = setAdapter();

    SearchAdapter adapterSearch = new SearchAdapter(context, destinationList);
    final WrapperExpandableListAdapter wrapperAdapter = new WrapperExpandableListAdapter(
        adapterSearch);
    expendableListViewDestination.setAdapter(wrapperAdapter);

    for (int i = 0; i < wrapperAdapter.getGroupCount(); i++) {
      expendableListViewDestination.expandGroup(i);
    }

    expendableListViewDestination.setOnGroupClickListener(new OnGroupClickListener() {
      @Override
      public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition,
          long id) {

        parent.expandGroup(groupPosition);
        return true;
      }
    });

    linearlayoutCheckIn.setOnClickListener(this);
    linearlayoutCheckOut.setOnClickListener(this);
    linearlayoutGuestRoom.setOnClickListener(this);
    linearlayoutDestination.setOnClickListener(this);
    imageViewRoomMinus.setOnClickListener(this);
    imageViewRoomPlus.setOnClickListener(this);
    imageViewAdultsMinus.setOnClickListener(this);
    imageViewAdultsPlus.setOnClickListener(this);
    imageViewChildMinus.setOnClickListener(this);
    imageViewChildPlus.setOnClickListener(this);
    imageViewInfantMinus.setOnClickListener(this);
    imageViewInfantPlus.setOnClickListener(this);
    textViewNext.setOnClickListener(this);
  }

  /**
   * Called to get max calender years.
   *
   * @return years
   */
  @Override
  public int getMaxYear() {
    return 2050;
  }

  /**
   * Called to get year, month, day from calender.
   *
   * @param year selected year
   * @param month selected month
   * @param day selected day
   */
  @Override
  public void onDayOfMonthSelected(int year, int month, int day) {
    month++;
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, month);
    if (isDateRangeSelected) {
      checkinDateStr = "";
      checkoutDateStr = "";
      textViewChekinDate.setText("Check-in");
      textViewCheckoutDate.setText("Check-out");
    }
    if (TextUtils.isEmpty(checkinDateStr)) {
      checkinDateStr = day + "-" + calendar.getTime().toString().split(" ")[1];
      textViewChekinDate.setText(checkinDateStr);
      startDateStr = year + "-" + month + "-" + day;
      isDateRangeSelected = false;
    } else if (TextUtils.isEmpty(checkoutDateStr)) {
      checkoutDateStr = day + "-" + calendar.getTime().toString().split(" ")[1];
      textViewCheckoutDate.setText(checkoutDateStr);
      endDateStr = year + "-" + month + "-" + day;
    }
  }

  /**
   * Called to get day range.
   *
   * @param selectedDays selected day range
   */
  @Override
  public void onDateRangeSelected(SelectedDays<CalendarDay> selectedDays) {
    isDateRangeSelected = true;
  }

  /**
   * Called when view clicked on this screen.
   *
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.imageview_hotelbookingslot_adultminus:
        if (numberOfAdults > 0) {
          numberOfAdults--;
          textViewAdultsCount.setText(numberOfAdults + "");
        }
        break;

      case R.id.imageview_hotelbookingslot_adultplus:
        numberOfAdults++;
        textViewAdultsCount.setText(numberOfAdults + "");
        break;

      case R.id.imageview_hotelbookingslot_infantminus:
        if (numberOfInfants > 0) {
          numberOfInfants--;
          textViewInfantCount.setText(numberOfInfants + "");
        }
        break;

      case R.id.imageview_hotelbookingslot_infantplus:
        numberOfInfants++;
        textViewInfantCount.setText(numberOfInfants + "");
        break;

      case R.id.imageview_hotelbookingslot_roomminus:
        if (numberOfRooms > 0) {
          numberOfRooms--;
          textViewRoomCount.setText(numberOfRooms + "");
        }
        break;

      case R.id.imageview_hotelbookingslot_roomplus:
        numberOfRooms++;
        textViewRoomCount.setText(numberOfRooms + "");
        break;

      case R.id.imageview_hotelbookingslot_childminus:
        if (numberOfChild > 0) {
          numberOfChild--;
          textViewChildCount.setText(numberOfChild + "");
        }
        break;

      case R.id.imageview_hotelbookingslot_childplus:
        numberOfChild++;
        textViewChildCount.setText(numberOfChild + "");
        break;

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      case R.id.linearlayout_hotelbookingslot_guestroom:
        expendableListViewDestination.setVisibility(View.GONE);
        linearlayoutGuestRoomCount.setVisibility(View.VISIBLE);
        dayPickerView.setVisibility(View.GONE);
        break;

      case R.id.linearlayout_hotelbookingslot_checkin:
        checkinDateStr = "";
        expendableListViewDestination.setVisibility(View.GONE);
        linearlayoutGuestRoomCount.setVisibility(View.GONE);
        dayPickerView.setVisibility(View.VISIBLE);
        break;

      case R.id.linearlayout_hotelbookingslot_checkout:
        checkoutDateStr = "";
        linearlayoutGuestRoomCount.setVisibility(View.GONE);
        dayPickerView.setVisibility(View.VISIBLE);
        expendableListViewDestination.setVisibility(View.GONE);
        break;

      case R.id.linearlayout_hotelbookingslot_destination:
        linearlayoutGuestRoomCount.setVisibility(View.GONE);
        dayPickerView.setVisibility(View.GONE);
        expendableListViewDestination.setVisibility(View.VISIBLE);
        break;

      case R.id.textview_hotelbookingslot_next:
        totalGuests = numberOfAdults + numberOfChild + numberOfInfants;
        if (!TextUtils.isEmpty(checkinDateStr) && !TextUtils.isEmpty(checkoutDateStr)
            && totalGuests > 0 && numberOfRooms > 0) {
          /*Intent intent = new Intent();
          intent.putExtra("numberOfGuests", totalGuests + " guests");
          intent.putExtra("dates", checkinDateStr + " to " + checkoutDateStr);
          intent.putExtra("numberOfRooms", numberOfRooms + " rooms");
          setResult(Activity.RESULT_OK, intent);
          finish();*/
          searchRoomArea();
        } else {
          AppUtil.showToast(this, getString(R.string.all_hotelslotsfilteralert));
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

  private List<SearchGroupPojo> setAdapter() {

    List<SearchGroupPojo> groupList = new ArrayList<>();
    List<SearchChildPojo> childList;
    int k = 0;
    for (int i = 0; i < parent.length; i++) {

      childList = new ArrayList<>();
      SearchGroupPojo groupPojo = new SearchGroupPojo();

      groupPojo.setParentName(parent[i]);
      groupPojo.setImageName(parentImage[i]);

      if (childList.size() > 0) {
        childList.clear();
      }
      for (; k < child.length; k++) {

        SearchChildPojo childPojo = new SearchChildPojo();

        childPojo.setChildName(child[k]);

        childList.add(childPojo);

        if (child[k].equalsIgnoreCase("Emirates Hills")) {
          k++;
          break;
        }
      }
      groupPojo.setChildArray(childList);
      groupList.add(groupPojo);

    }
    return groupList;
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to search for available room area.
   */
  private void searchRoomArea() {
    if (AppUtil.isNetworkAvailable(context)) {
      new HttpClientRequest().setApiResponseListner(this);

      Detail detail = new Detail();
      detail.setChainCode("CYB");
      detail.setLanguageCode(SharedPreferenceUtils.getInstance(this)
          .getStringValue(SharedPreferenceUtils.APP_LANGUAGE, ""));
      detail.setMaxResponses(24);
      detail.setMoreDataEchoToken(0);
      SearchCriteria searchCriteria = new SearchCriteria();
      searchCriteria.setSearchResultSortOrder(2);
      searchCriteria.setNumberOfUnits(numberOfRooms);
      //TimeSpan
      TimeSpan timeSpan = new TimeSpan();
      timeSpan.setStart(startDateStr);
      timeSpan.setEnd(endDateStr);
      searchCriteria.setTimeSpan(timeSpan);
      //GuestCount
      List<GuestCount> guestCountList = new ArrayList<>();
      //Adults
      GuestCount guestCount = new GuestCount();
      guestCount.setAgeQualifyingCode("10");
      guestCount.setCount(numberOfAdults);
      guestCountList.add(guestCount);
      //Childs
      guestCount = new GuestCount();
      guestCount.setAgeQualifyingCode("8");
      guestCount.setCount(numberOfChild);
      guestCountList.add(guestCount);
      //Infants
      guestCount = new GuestCount();
      guestCount.setAgeQualifyingCode("7");
      guestCount.setCount(numberOfInfants);
      guestCountList.add(guestCount);

      searchCriteria.setGuestCounts(guestCountList);

      //TODO: Need to add ratings

      //HotelId
      //TODO: Need to make it dynamic
      List<String> hotelIds = new ArrayList<>();
      hotelIds.add("1");
      searchCriteria.setHotelIds(hotelIds);
      //TODO: Need to add position

      //Add searchCriteria to roomAreaSearchPojo
      detail.setSearchCriteria(searchCriteria);
      detail.setCurrencyCode("AED");//TODO:Make it dynamic
      detail.setDeviceId(AppUtil.getDeviceId(this));
      if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
          .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {
        detail.setLoyaltyMemberId(Integer.parseInt(SharedPreferenceUtils.getInstance(this)
            .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")));
      }
      List<Detail> detailList = new ArrayList<>();
      detailList.add(detail);
      RoomAreaSearchRequestPojo roomAreaSearchPojo = new RoomAreaSearchRequestPojo();
      roomAreaSearchPojo.setDetails(detailList);
      roomAreaSearchPojo.setFeature("roomReservation");
      roomAreaSearchPojo.setOperation("areaSearch");

      //Save RoomAreaSearchRequestPojo
      JsonParserUtil.getInstance(this).saveRoomAreaSearchRequestPojo(roomAreaSearchPojo);

      Gson gson = new Gson();
      String requestString = gson.toJson(roomAreaSearchPojo, RoomAreaSearchRequestPojo.class);

      StringEntity entity = null;
      try {
        entity = new StringEntity(requestString);
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      new HttpClientRequest(context, WebServiceUtil.getUrl(WebServiceUtil.METHOD_AREA_SEARCH),
          entity, WebServiceUtil.CONTENT_TYPE,
          SEARCH_ROOM_AREA, true).httpPostRequest();
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
      if (requestMethod.equalsIgnoreCase(SEARCH_ROOM_AREA)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("Status")) {

      } else if (requestMethod.equalsIgnoreCase(SEARCH_ROOM_AREA)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && !new JSONObject(responseVal).getBoolean("Status")) {

        JSONObject dataObject = new JSONObject(responseVal).getJSONObject("data");

        if (dataObject != null) {
          JSONArray detailArray = dataObject.optJSONArray("detail");
          if (detailArray != null && detailArray.length() > 0) {
            JSONObject validationError = detailArray.optJSONObject(0)
                .optJSONArray("validationErrors").optJSONObject(0);

            AppUtil.showAlertDialog((AppCompatActivity) context,
                validationError.getString("ErrorMessage"), false,
                getResources().getString(R.string.dialog_errortitle), true, null);
          }
        }
      } else {
        AppUtil.showAlertDialog(this,
            new JSONObject(responseVal).getString("message"), false,
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
