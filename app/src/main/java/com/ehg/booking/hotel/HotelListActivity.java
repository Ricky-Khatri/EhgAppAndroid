/*
 *  Created by Emaar Hospitality Group on 16/10/18 5:01 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 16/10/18 5:01 PM
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
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.hotel.adapter.HotelResortsAdapter;
import com.ehg.booking.hotel.adapter.HotelResortsAdapter.OnHotelItemClickListener;
import com.ehg.booking.hotel.adapter.SearchResultAdapter;
import com.ehg.booking.hotel.adapter.SearchResultAdapter.OnSearchResultItemClickListener;
import com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo.Detail;
import com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo.FetchRoomAvailabilityRequestPojo;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.FetchAvailabilityResponsePojo;
import com.ehg.booking.hotel.pojo.roomareasearchrequestpojo.GuestCount;
import com.ehg.booking.hotel.pojo.roomareasearchrequestpojo.RoomAreaSearchRequestPojo;
import com.ehg.booking.hotel.pojo.roomareasearchresponsepojo.HotelList;
import com.ehg.booking.hotel.pojo.roomareasearchresponsepojo.RoomAreaSearchResponsePojo;
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
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class will show  all the available hotel list.
 */
public class HotelListActivity extends BaseActivity implements
    OnClickListener, OnHotelItemClickListener, ApiResponseListener,
    OnSearchResultItemClickListener {

  private static final String FETCH_AVAILABILITY = "FetchAvailability";
  private Context context;
  private AppCompatImageView headerBackButton;
  private TextView textViewHeaderTitle;
  private RecyclerView recyclerViewHotelList;
  private String headerTitle;
  private TextView textViewClickHere;
  private LinearLayout linearlayoutSearch;

  private TextView textViewResults;

  private static final int REQUEST_CODE = 1;
  private AutoCompleteTextView textviewSearch;

  private AppCompatImageView appCompatImageViewSort;
  private RelativeLayout relativeLayoutResults;
  private RoomAreaSearchResponsePojo roomAreaSearchResponsePojo;

  private List<HotelList> hotelList;
  private HotelResortsAdapter hotelResortsAdapter;

  private String numberOfGuests;
  private String dates;
  private String numberOfRooms;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      setContentView(R.layout.activity_hotellist);
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

    appCompatImageViewSort = findViewById(R.id.appcompactimageview_hotellist_sort);
    relativeLayoutResults = findViewById(R.id.relativelayout_hotellist_results);
    relativeLayoutResults.setVisibility(View.GONE);
    textViewResults = findViewById(R.id.textView_hotellist_results);
    linearlayoutSearch = findViewById(R.id.linearlayout_hotellist_layoutsearch);
    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    headerBackButton = findViewById(R.id.imageview_header_back);
    textViewClickHere = findViewById(R.id.textView_hotellist_clickhere);

    //Set OnClickListener
    headerBackButton.setOnClickListener(this);
    findViewById(R.id.appcompactimageview_hotellist_filter).setOnClickListener(this);
    textViewClickHere.setOnClickListener(this);
    linearlayoutSearch.setOnClickListener(this);
    textviewSearch = findViewById(R.id.textview_hotellist_search);
    textviewSearch.setOnClickListener(this);
    appCompatImageViewSort.setOnClickListener(this);

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {

      headerTitle = bundle.getString("title") != null ? bundle.getString("title") : "";
      numberOfGuests =
          bundle.getString("numberOfGuests") != null ? bundle.getString("numberOfGuests") : "";
      dates = bundle.getString("dates") != null ? bundle.getString("dates") : "";
      numberOfRooms =
          bundle.getString("numberOfRooms") != null ? bundle.getString("numberOfRooms") : "";

      recyclerViewHotelList = findViewById(R.id.recyclerview_hotellist_list);
      recyclerViewHotelList.setLayoutManager(new LinearLayoutManager(context));
      recyclerViewHotelList.setHasFixedSize(true);

      if (!TextUtils.isEmpty(dates) && !TextUtils.isEmpty(numberOfGuests) && !TextUtils
          .isEmpty(numberOfRooms)) {
        textviewSearch.setText(dates + " | " + numberOfGuests + "," + numberOfRooms);
        //Set Search Adapter
        setSearchResultAdapter();
      } else {
        //Set Adapter
        hotelResortsAdapter = new HotelResortsAdapter(context, this, new ArrayList<HotelList>());
        recyclerViewHotelList.setAdapter(hotelResortsAdapter);
        AppUtil.animateRecyclerView(context, recyclerViewHotelList,
            R.anim.layout_animation_from_bottom);
      }

      if (!TextUtils.isEmpty(headerTitle)) {
        textViewHeaderTitle.setText(headerTitle);
      }
    }
  }

  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();
    setBackArrowRtl(headerBackButton);
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
   * Called when activity view item clicked.
   *
   * @param view clicked view item
   */
  @Override
  public void onClick(View view) {
    Intent intent = null;
    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation((AppCompatActivity) context);
        break;

      case R.id.appcompactimageview_hotellist_filter:
        intent = new Intent(this, HotelFilterActivity.class);
        AppUtil.startActivityWithAnimation(this, intent, false);
        break;

      case R.id.textView_hotellist_clickhere:
        intent = new Intent(this, HotelBookingPromoCodeActivity.class);
        AppUtil.startActivityWithAnimation(this, intent, false);
        break;

      case R.id.textview_hotellist_search:
        intent = new Intent(this, HotelBookingSlotActivity.class);
        intent.putExtra("key", "HotelListActivity");
        startActivityForResult(intent, REQUEST_CODE);
        break;

      case R.id.linearlayout_hotellist_layoutsearch:
        intent = new Intent(this, HotelBookingSlotActivity.class);
        intent.putExtra("key", "HotelListActivity");
        startActivityForResult(intent, REQUEST_CODE);
        break;

      case R.id.appcompactimageview_hotellist_sort:
        break;
      default:
        break;
    }
  }

  /**
   * Called when Hotel list item will click.
   *
   * @param position - clicked item position.
   * @param view - clicked view reference.
   * @param title - title
   */
  @Override
  public void onHotelItemClicked(int position, View view, String title) {

    Intent intent = null;
    String searchFieldText = textviewSearch.getText().toString();

    switch (view.getId()) {
      case R.id.button_itemhotelresort_book:
        if (searchFieldText
            .equalsIgnoreCase(getResources().getString(R.string.hotellist_searchhint))) {
          intent = new Intent(this, HotelBookingSlotActivity.class);
          intent.putExtra("title", title);
          intent.putExtra("key", "HotelListActivity");
          startActivityForResult(intent, REQUEST_CODE);
        } else {
          fetchRoomAvailability();
        }
        break;

      default:
        intent = new Intent(this, HotelDetailActivity.class);
        intent.putExtra("title", title);
        if (searchFieldText
            .equalsIgnoreCase(getResources().getString(R.string.hotellist_searchhint))) {
          intent.putExtra("key", "HotelList");
        } else {
          intent.putExtra("key", "SearchResultList");
        }
        AppUtil.startActivityWithAnimation(this, intent, false);
        break;
    }
  }

  /**
   * Called when Hotel search result item list item will click.
   *
   * @param position - clicked item position.
   * @param view - clicked view reference.
   * @param title - title
   */
  @Override
  public void onSearchResultItemClicked(int position, View view, String title) {
    Intent intent = null;

    switch (view.getId()) {
      case R.id.button_itemhotelresort_book:
        String searchFieldText = textviewSearch.getText().toString();
        if (searchFieldText
            .equalsIgnoreCase(getResources().getString(R.string.hotellist_searchhint))) {
          intent = new Intent(this, HotelBookingSlotActivity.class);
          intent.putExtra("title", title);
          intent.putExtra("key", "HotelListActivity");
          startActivityForResult(intent, REQUEST_CODE);
        } else {
          fetchRoomAvailability();
        }
        break;

      default:
        intent = new Intent(this, HotelDetailActivity.class);
        intent.putExtra("title", title);
        AppUtil.startActivityWithAnimation(this, intent, false);
        break;
    }
  }

  /**
   * Called when activity returned with result.
   *
   * @param requestCode requestCode
   * @param resultCode resultCode
   * @param data data
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (data != null && requestCode == REQUEST_CODE) {
      relativeLayoutResults.setVisibility(View.VISIBLE);
      numberOfGuests = data.getStringExtra("numberOfGuests");
      dates = data.getStringExtra("dates");
      numberOfRooms = data.getStringExtra("numberOfRooms");
      textviewSearch.setText(dates + " | " + numberOfGuests + "," + numberOfRooms);

      setSearchResultAdapter();
    }
  }

  /**
   * Called to set search result adapter.
   */
  private void setSearchResultAdapter() {
    roomAreaSearchResponsePojo = JsonParserUtil.getInstance(this)
        .getRoomAreaSearchResponsePojo();

    //When hotel area selected
    if (roomAreaSearchResponsePojo != null && roomAreaSearchResponsePojo.getData() != null
        && roomAreaSearchResponsePojo.getData().getDetail() != null
        && roomAreaSearchResponsePojo.getData().getDetail().size() > 0
        && roomAreaSearchResponsePojo.getData().getDetail().get(0).getResponseData() != null
        &&
        roomAreaSearchResponsePojo.getData().getDetail().get(0).getResponseData().getHotelList()
            != null
        &&
        roomAreaSearchResponsePojo.getData().getDetail().get(0).getResponseData().getHotelList()
            .size() > 0) {

      hotelList = roomAreaSearchResponsePojo.getData().getDetail().get(0).getResponseData()
          .getHotelList();

      //Show area search results
      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          if (hotelList != null && hotelList.size() > 0) {
            SearchResultAdapter searchResultAdapter = new SearchResultAdapter(context,
                HotelListActivity.this, hotelList);
            recyclerViewHotelList.setAdapter(searchResultAdapter);
            AppUtil.animateRecyclerView(context, recyclerViewHotelList,
                R.anim.layout_animation_from_bottom);
          }
        }
      });
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to fetch room availability.
   */
  private void fetchRoomAvailability() {
    try {
      if (AppUtil.isNetworkAvailable(context)) {
        new HttpClientRequest().setApiResponseListner(this);

        RoomAreaSearchRequestPojo roomAreaSearchRequestPojo = JsonParserUtil.getInstance(this)
            .getRoomAreaSearchRequestPojo();
        List<com.ehg.booking.hotel.pojo.roomareasearchrequestpojo.Detail>
            roomAreaDetailList = roomAreaSearchRequestPojo
            .getDetails();

        if (roomAreaDetailList != null && roomAreaDetailList.size() > 0) {

          com.ehg.booking.hotel.pojo.roomareasearchrequestpojo.Detail
              roomAreaDetail = roomAreaDetailList.get(0);
          Detail detail = new Detail();
          detail
              .setIbuId(1);//TODO: Make it dynamic
          detail.setCheckInDate(roomAreaDetail.getSearchCriteria().getTimeSpan().getStart());
          detail.setCheckOutDate(roomAreaDetail.getSearchCriteria().getTimeSpan().getEnd());
          detail.setTotalRooms(roomAreaDetail.getSearchCriteria().getNumberOfUnits());
          List<GuestCount> guestCountList = roomAreaDetail.getSearchCriteria().getGuestCounts();
          detail.setTotalAdults(guestCountList.get(0).getCount());
          List<Integer> childreAges = new ArrayList<>();
          //TODO: Make it dynamic
          childreAges.add(guestCountList.get(1).getCount());
          detail.setTotalChildren(guestCountList.get(1).getCount());
          //detail.setChildrenAges(childreAges);
          detail.setTotalInfants(guestCountList.get(2).getCount());
          detail.setCurrencyCode(roomAreaDetail.getCurrencyCode());
          detail.setLanguage(roomAreaDetail.getLanguageCode());

          if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
              .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {
            detail.setLoyaltyMemberId(Integer.parseInt(SharedPreferenceUtils.getInstance(this)
                .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")));
          }
          detail.setDeviceId(AppUtil.getDeviceId(this));

          FetchRoomAvailabilityRequestPojo fetchRoomAvailabilityRequestPojo =
              new FetchRoomAvailabilityRequestPojo();
          List<Detail> detailList = new ArrayList<>();
          detailList.add(detail);
          fetchRoomAvailabilityRequestPojo.setDetails(detailList);
          Gson gson = new Gson();
          String requestString = gson
              .toJson(fetchRoomAvailabilityRequestPojo, FetchRoomAvailabilityRequestPojo.class);

          StringEntity entity = null;
          try {
            entity = new StringEntity(requestString);
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }

          new HttpClientRequest(context,
              WebServiceUtil.getUrl(WebServiceUtil.METHOD_FETCH_ROOM_AVAILABILITY),
              entity, WebServiceUtil.CONTENT_TYPE,
              FETCH_AVAILABILITY, true).httpPostRequest();
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
      if (requestMethod.equalsIgnoreCase(FETCH_AVAILABILITY)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("Status")) {

        FetchAvailabilityResponsePojo fetchAvailabilityResponsePojo = new Gson()
            .fromJson(responseVal,
                new TypeToken<FetchAvailabilityResponsePojo>() {
                }.getType());

        JsonParserUtil.getInstance(this)
            .setFetchAvailabilityResponsePojo(fetchAvailabilityResponsePojo);

        Intent intent = new Intent(this, SelectRoomActivity.class);
        intent.putExtra("title", headerTitle);
        intent.putExtra("dates", dates);
        intent.putExtra("numberOfGuests", numberOfGuests);
        intent.putExtra("numberOfRooms", numberOfRooms);
        AppUtil.startActivityWithAnimation(this, intent, false);

      } else if (requestMethod.equalsIgnoreCase(FETCH_AVAILABILITY)
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
