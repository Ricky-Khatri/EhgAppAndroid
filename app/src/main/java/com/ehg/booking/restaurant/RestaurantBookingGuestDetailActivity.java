/*
 *  Created by Emaar Hospitality Group on 12/10/18 12:27 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 12/10/18 12:27 PM
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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.chip.ChipGroup;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.utilities.AppUtil;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * This class allows user to booking restaurant.
 */
public class RestaurantBookingGuestDetailActivity extends BaseActivity implements
    OnClickListener,
    OnEditorActionListener, ApiResponseListener {

  private static final String OPERATION = "MakeReservation";

  private Context context;
  private ChipGroup chipGroup;
  private EditText editTextFirstName;
  private EditText editTextLastName;
  private EditText editTextEmailAddress;
  private EditText editTextPhoneNumber;
  private EditText editTextSpecialRequest;
  private TextView textViewBookingRestaurent;

  private String[] tag = {"Oct2", "1:00AM", "5 Guests"};
  private TextView textViewDate;
  private TextView textViewTime;
  private TextView textViewGuestCount;

  private String restaurantId;
  private String dateStr;
  private String timeStr;
  private String numberOfPeople;
  private String expiresAt;
  private String reservationToken;


  /**
   * Called when fragment created.
   *
   * @param savedInstanceState bundle
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_restaurentbookingguestdetail);

    context = this;

    initView();
  }

  /**
   * Method init's view components of this screen.
   */
  private void initView() {
    try {
      editTextFirstName = findViewById(R.id.edittext_restaurantbookingguestdetail_firstname);
      editTextLastName = findViewById(R.id.edittext_restaurantbookingguestdetail_lastname);
      editTextEmailAddress = findViewById(R.id.edittext_restaurantbookingguestdetail_email);
      editTextPhoneNumber = findViewById(R.id.edittext_restaurantbookingguestdetail_phonenumber);
      editTextSpecialRequest = findViewById(R.id
          .edittext_restaurantbookingguestdetail_specialinstruction);
      textViewBookingRestaurent = findViewById(R.id.textview_restaurantbookingguestdetail_booking);
      textViewDate = findViewById(R.id.textview_restaurantbookingguestdetail_date);
      textViewTime = findViewById(R.id.textview_restaurantbookingguestdetail_time);
      textViewGuestCount = findViewById(R.id.textview_restaurantbookingguestdetail_numberofguest);

      restaurantId = getIntent().getStringExtra("restaurantId");
      dateStr = getIntent().getStringExtra("date");
      timeStr = getIntent().getStringExtra("time");
      numberOfPeople = getIntent().getStringExtra("numberOfPeople");
      expiresAt = getIntent().getStringExtra("expiresAt");
      reservationToken = getIntent().getStringExtra("reservationToken");

      String[] dateArray = dateStr.split("-");
      if (dateArray != null && dateArray.length > 0) {
        textViewDate.setText(dateArray[1] + " " + dateArray[2]);
      }
      textViewTime.setText(timeStr);
      textViewGuestCount.setText(numberOfPeople);

      textViewBookingRestaurent.setOnClickListener(this);

      //Set EditorCLickListener
      editTextFirstName.setOnEditorActionListener(this);
      editTextLastName.setOnEditorActionListener(this);
      editTextEmailAddress.setOnEditorActionListener(this);
      editTextPhoneNumber.setOnEditorActionListener(this);
      editTextSpecialRequest.setOnEditorActionListener(this);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
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

    switch (view.getId()) {

      case R.id.textview_restaurantbookingguestdetail_booking:
        validateSignUpFormFields();
        break;

      default:
        break;
    }
  }

  /**
   * Called when mobile phone keyboard keys clicked: enter/done/next keys.
   *
   * @param textView view currently focused
   * @param index index
   * @param keyEvent key event
   * @return returns boolean value
   */
  @Override
  public boolean onEditorAction(TextView textView, int index, KeyEvent keyEvent) {

    boolean cancel = false;

    if (index == EditorInfo.IME_ACTION_DONE) {

      validateSignUpFormFields();

    } else {

      switch (textView.getId()) {
        case R.id.edittext_restaurantbookingguestdetail_firstname:
          String firstName = editTextFirstName.getText().toString().trim();
          if (TextUtils.isEmpty(firstName)) {
            editTextFirstName.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;

          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_restaurantbookingguestdetail_lastname:
          String lastName = editTextLastName.getText().toString().trim();
          if (TextUtils.isEmpty(lastName)) {
            editTextLastName.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;
          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_restaurantbookingguestdetail_email:
          String email = editTextEmailAddress.getText().toString().trim();
          if (TextUtils.isEmpty(email)) {
            editTextEmailAddress.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;
          } else if (!AppUtil.isValidEmail(email)) {
            editTextEmailAddress.setError(getResources().getString(R.string.all_invalidemail));
            cancel = true;
          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_restaurantbookingguestdetail_phonenumber:
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
        /*case R.id.edittext_restaurantbookingguestdetail_specialinstruction:
          String password = editTextSpecialRequest.getText().toString().trim();
          if (TextUtils.isEmpty(password)) {
            editTextSpecialRequest.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;
          } else {
            cancel = false;
          }
          break;
*/
        default:
          break;
      }
    }
    return cancel;
  }

  /**
   * Method validates Restaurant booking form fields.
   */
  private void validateSignUpFormFields() {

    resetErrors();

    boolean cancel = false;
    View focusView = null;

    String firstName = editTextFirstName.getText().toString();
    String lastName = editTextLastName.getText().toString();
    String email = editTextEmailAddress.getText().toString();
    String mobile = editTextPhoneNumber.getText().toString();
    String specialRequest = editTextSpecialRequest.getText().toString();

    if (TextUtils.isEmpty(firstName)) {

      editTextFirstName.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextFirstName;
      cancel = true;

    } else if (TextUtils.isEmpty(lastName)) {

      editTextLastName.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextLastName;
      cancel = true;

    } else if (TextUtils.isEmpty(email)) {

      editTextEmailAddress.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextEmailAddress;
      cancel = true;

    } else if (!AppUtil.isValidEmail(email)) {

      editTextEmailAddress.setError(getResources().getString(R.string.all_invalidemail));
      focusView = editTextEmailAddress;
      cancel = true;

    } else if (TextUtils.isEmpty(mobile)) {

      editTextPhoneNumber.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextPhoneNumber;
      cancel = true;

    } else if (!AppUtil.isValidMobile(mobile)) {

      editTextPhoneNumber.setError(getResources().getString(R.string.all_invalidmobile));
      focusView = editTextPhoneNumber;
      cancel = true;

    }

    if (cancel) {

      focusView.requestFocus();

    } else {

      makeReservation(firstName, lastName, email, mobile, specialRequest);
    }
  }

  /**
   * Called when activity resumed.
   */
  @Override
  public void onResume() {
    super.onResume();
    resetErrors();
  }

  /**
   * Called to reset errors on form field.
   */
  private void resetErrors() {
    try {
      //Reset errors

      editTextFirstName.setError(null);
      editTextLastName.setError(null);
      editTextEmailAddress.setError(null);
      editTextPhoneNumber.setError(null);
      editTextSpecialRequest.setError(null);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to make reservation.
   * @param firstName
   * @param lastName
   * @param email
   * @param mobile
   * @param specialRequest
   */
  private void makeReservation(String firstName, String lastName, String email,
      String mobile, String specialRequest) {
    if (AppUtil.isNetworkAvailable(this)) {
      new HttpClientRequest().setApiResponseListner(this);
      JSONObject jsonObject = new JSONObject();
      JSONArray detailsArray = new JSONArray();
      JSONObject detailObject = new JSONObject();

      try {
        JSONObject deviceDetailObject = new JSONObject();
        deviceDetailObject.put("firstName", firstName);
        deviceDetailObject.put("lastName", lastName);
        deviceDetailObject.put("emailAddress", email);
        deviceDetailObject.put("phoneNumber", mobile);
        deviceDetailObject.put("phoneCountryCode", "971");//TODO : Need to make dynamic
        deviceDetailObject.put("specialRequest", specialRequest);
        deviceDetailObject.put("offerId", "0");// TODO : Make it dynamic
        deviceDetailObject.put("offerName", "Offer");
        deviceDetailObject.put("partySize", numberOfPeople);
        deviceDetailObject.put("reservationDate", dateStr);
        deviceDetailObject.put("reservationTime", timeStr
            .replace("AM", "").replace("PM", ""));
        deviceDetailObject.put("restaurantId", restaurantId);
        deviceDetailObject.put("reservationToken", reservationToken);

        if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
            .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {

          deviceDetailObject.put("loyaltyMemberId",
              SharedPreferenceUtils.getInstance(this)
                  .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""));
        }
        deviceDetailObject.put("deviceId", AppUtil.getDeviceId(this));

        detailObject.put("deviceDetails", deviceDetailObject);

        detailsArray.put(detailObject);

        jsonObject.put("details", detailsArray);
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

        Intent intent = new Intent(this, RestaurantBookingGuestDetailActivity.class);
        intent.putExtra("restaurantId", restaurantId);
        intent.putExtra("date", dateStr);
        //intent.putExtra("time", selectedTimeSlot);
        intent.putExtra("numberOfPeople", numberOfPeople);
        AppUtil.startActivityWithAnimation(this, intent, false);

      } else if (responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && !new JSONObject(responseVal).getBoolean("status")) {

        JSONObject dataObject = new JSONObject(responseVal).getJSONObject("data");
        JSONArray detailArray = dataObject.optJSONArray("detail");
        if (detailArray != null && detailArray.length() > 0) {
          JSONObject validationError = detailArray.optJSONObject(0)
              .optJSONArray("validationErrors").optJSONObject(0);

          AppUtil.showAlertDialog(this,
              validationError.getString("ErrorMessage"), false,
              getResources().getString(R.string.dialog_errortitle), true, null);
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
