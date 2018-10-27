/*
 *  Created by Emaar Hospitality Group on 22/10/18 11:31 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 22/10/18 11:31 AM
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo.Detail;
import com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo.FetchRoomAvailabilityRequestPojo;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.FetchAvailabilityResponsePojo;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is used to enter guest detail.
 */
public class RoomBookingGuestDetailActivity extends BaseActivity implements
    OnClickListener, OnEditorActionListener, ApiResponseListener {

  private static final String HOLD_RESERVATION = "HoldReservation";

  private Context context;
  private AppCompatImageView imageViewBack;
  private TextView textViewHeaderTitle;
  private Spinner spinnerGuestTitle;
  private EditText editTextFirstName;
  private EditText editTextLastName;
  private EditText editTextEmail;
  private EditText editTextPhoneNumber;
  private EditText editTextAddress;
  private EditText editTextAddressNewline;
  private EditText editTextCity;
  private EditText editTextFrequentlyAsked;
  private EditText editTextSpecialRequest;
  private Spinner spinnerCountry;
  private TextView textViewAmount;
  private TextView textViewNext;
  private String guestTitle;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      setContentView(R.layout.activity_roombookingguestdetail);
      context = this;
      initView();
    } catch (NullPointerException e) {

      e.printStackTrace();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /**
   * Called to init view components of this activity.
   */
  private void initView() {

    imageViewBack = findViewById(R.id.imageview_header_back);
    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    spinnerGuestTitle = findViewById(R.id.spinner_roombookingguestdetail_title);
    editTextFirstName = findViewById(R.id.edittext_roombookingguestdetail_firstname);
    editTextLastName = findViewById(R.id.edittext_roombookingguestdetail_lastname);
    editTextEmail = findViewById(R.id.edittext_roombookingguestdetail_email);
    editTextPhoneNumber = findViewById(R.id.edittext_roombookingguestdetail_phonenumber);
    editTextAddress = findViewById(R.id.edittext_roombookingguestdetail_address);
    editTextAddressNewline = findViewById(R.id.edittext_roombookingguestdetail_addressnewline);
    editTextCity = findViewById(R.id.edittext_roombookingguestdetail_city);
    editTextFrequentlyAsked = findViewById(R.id.edittext_roombookingguestdetail_frequentguestid);
    editTextSpecialRequest = findViewById(R.id.edittext_roombookingguestdetail_specialrequest);
    spinnerCountry = findViewById(R.id.spinner_roombookingguestdetail_country);
    textViewAmount = findViewById(R.id.textview_roombookingguestdetail_amount);
    textViewNext = findViewById(R.id.textview_roombookingguestdetail_next);

    findViewById(R.id.imageview_header_back).setOnClickListener(this);

    textViewNext.setOnClickListener(this);
    showGuestTitle();
  }

  /**
   * Called when mobile phone keyboard keys clicked: enter/done/next keys.
   *
   * @param textView view currently focused
   * @param index index
   * @param event key event
   * @return returns boolean value
   */
  @Override
  public boolean onEditorAction(TextView textView, int index, KeyEvent event) {
    boolean cancel = false;

    if (index == EditorInfo.IME_ACTION_DONE) {

      validateSignUpFormFields();

    } else {

      switch (textView.getId()) {
        case R.id.edittext_roombookingguestdetail_firstname:
          String firstName = editTextFirstName.getText().toString().trim();
          if (TextUtils.isEmpty(firstName)) {
            editTextFirstName.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;

          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_roombookingguestdetail_lastname:
          String lastName = editTextLastName.getText().toString().trim();
          if (TextUtils.isEmpty(lastName)) {
            editTextLastName.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;
          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_roombookingguestdetail_email:
          String email = editTextEmail.getText().toString().trim();
          if (TextUtils.isEmpty(email)) {
            editTextEmail.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;
          } else if (!AppUtil.isValidEmail(email)) {
            editTextEmail.setError(getResources().getString(R.string.all_invalidemail));
            cancel = true;
          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_roombookingguestdetail_phonenumber:
          String mobile = editTextPhoneNumber.getText().toString().trim();
          if (TextUtils.isEmpty(mobile)) {
            editTextPhoneNumber.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;
          } else if (!AppUtil.isValidMobile(mobile)) {
            editTextPhoneNumber.setError(getResources().getString(R.string.all_invalidmobile));
            cancel = true;
          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_roombookingguestdetail_address:
          String address = editTextAddress.getText().toString().trim();
          if (TextUtils.isEmpty(address)) {
            editTextAddress.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;
          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_roombookingguestdetail_city:
          String city = editTextCity.getText().toString().trim();
          if (TextUtils.isEmpty(city)) {
            editTextCity.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;
          } else {
            cancel = false;
          }
          break;

        default:
          break;
      }
    }
    return cancel;
  }

  /**
   * Method validates Spa booking form fields.
   */
  private void validateSignUpFormFields() {

    resetErrors();

    boolean cancel = false;
    View focusView = null;

    String firstName = editTextFirstName.getText().toString();
    String lastName = editTextLastName.getText().toString();
    String email = editTextEmail.getText().toString();
    String mobile = editTextPhoneNumber.getText().toString();
    String address = editTextAddress.getText().toString();
    String city = editTextCity.getText().toString();
    //String prefferedDateTime = textViewPrefferedDateTime.getText().toString();

    if (guestTitle.equalsIgnoreCase("Please Select Title")) {

      Toast.makeText(context, "Please select Title", Toast.LENGTH_SHORT).show();
      /*cancel = true;
      focusView = textViewPrefferedDateTime;*/
    } else if (TextUtils.isEmpty(firstName)) {

      editTextFirstName.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextFirstName;
      cancel = true;

    } else if (TextUtils.isEmpty(lastName)) {

      editTextLastName.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextLastName;
      cancel = true;

    } else if (TextUtils.isEmpty(email)) {

      editTextEmail.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextEmail;
      cancel = true;

    } else if (!AppUtil.isValidEmail(email)) {

      editTextEmail.setError(getResources().getString(R.string.all_invalidemail));
      focusView = editTextEmail;
      cancel = true;

    } else if (TextUtils.isEmpty(mobile)) {

      editTextPhoneNumber.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextPhoneNumber;
      cancel = true;

    } else if (!AppUtil.isValidMobile(mobile)) {

      editTextPhoneNumber.setError(getResources().getString(R.string.all_invalidmobile));
      focusView = editTextPhoneNumber;
      cancel = true;
    } else if (TextUtils.isEmpty(address)) {

      editTextAddress.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextAddress;
      cancel = true;

    } else if (TextUtils.isEmpty(city)) {

      editTextCity.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextCity;
      cancel = true;
    }

    if (cancel) {
      Objects.requireNonNull(focusView).requestFocus();
    } else {
      holdRoomReservation();
    }
  }

  /**
   * this method is using to show guest title.
   */
  private void showGuestTitle() {

    // Spinner Drop down elements
    List<String> userTitlelist = new ArrayList<String>();
    userTitlelist.add("Please Select Title");
    userTitlelist.add("Mr.");
    userTitlelist.add("Ms.");

    // Creating adapter for spinner
    ArrayAdapter<String> guestTitleAdapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, userTitlelist);

    // Drop down layout style - list view with radio button
    guestTitleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    spinnerGuestTitle.setAdapter(guestTitleAdapter);

    spinnerGuestTitle.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        guestTitle = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();
    resetErrors();
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

  /**
   * Called to reset errors on form field.
   */
  private void resetErrors() {
    try {
      //Reset errors

      editTextFirstName.setError(null);
      editTextLastName.setError(null);
      editTextEmail.setError(null);
      editTextPhoneNumber.setError(null);
      editTextAddress.setError(null);
      editTextCity.setError(null);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called on view clicked.
   *
   * @param view view
   */
  @Override
  public void onClick(View view) {

    Intent intent = null;
    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation((AppCompatActivity) context);
        break;

      case R.id.textview_roombookingguestdetail_next:
        validateSignUpFormFields();
        break;

      default:
        break;
    }
    AppUtil.startActivityWithAnimation(this, intent, false);
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to hold multiple room reservation.
   */
  private void holdRoomReservation() {
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
          detail.setIbuId(2);//TODO: Make it dynamic
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
              WebServiceUtil.getUrl(WebServiceUtil.METHOD_HOLD_RESERVATION_MULTI_ROOM),
              entity, WebServiceUtil.CONTENT_TYPE,
              HOLD_RESERVATION, true).httpPostRequest();
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
      if (requestMethod.equalsIgnoreCase(HOLD_RESERVATION)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("Status")) {

        FetchAvailabilityResponsePojo fetchAvailabilityResponsePojo = new Gson()
            .fromJson(responseVal,
                new TypeToken<FetchAvailabilityResponsePojo>() {
                }.getType());

        JsonParserUtil.getInstance(this)
            .setFetchAvailabilityResponsePojo(fetchAvailabilityResponsePojo);


      } else if (requestMethod.equalsIgnoreCase(HOLD_RESERVATION)
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
