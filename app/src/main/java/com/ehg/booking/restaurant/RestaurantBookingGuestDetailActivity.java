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
import android.support.v7.widget.AppCompatImageView;
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
import com.ehg.reservations.BookingSummaryActivity;
import com.ehg.utilities.AppUtil;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
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
      TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
      textViewHeaderTitle.setText("Na3Na3");
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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]));
        textViewDate.setText(calendar.getTime().toString().split(" ")[1] + " " + dateArray[2]);
      }
      textViewTime.setText(timeStr);
      textViewGuestCount.setText(numberOfPeople + " Guests");

      textViewBookingRestaurent.setOnClickListener(this);

      //Set EditorCLickListener
      editTextFirstName.setOnEditorActionListener(this);
      editTextLastName.setOnEditorActionListener(this);
      editTextEmailAddress.setOnEditorActionListener(this);
      editTextPhoneNumber.setOnEditorActionListener(this);
      editTextSpecialRequest.setOnEditorActionListener(this);
      findViewById(R.id.imageview_header_back).setOnClickListener(this);

      //Set OnEditorActionListener
      editTextSpecialRequest.setOnEditorActionListener(new OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
          if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            validateSignUpFormFields();
          }
          return false;
        }
      });

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

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
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
   *
   * @param firstName first name
   * @param lastName last name
   * @param email email
   * @param mobile mobile
   * @param specialRequest special request
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
        deviceDetailObject.put("phoneCountryCode", "AU");//TODO : Need to make dynamic
        deviceDetailObject.put("specialRequest", specialRequest);
        deviceDetailObject.put("offerId", "0");// TODO : Make it dynamic
        deviceDetailObject.put("offerName", "Offer");
        deviceDetailObject.put("partySize", numberOfPeople);
        deviceDetailObject.put("reservationDate", dateStr);
        deviceDetailObject.put("reservationTime", timeStr
            .replace("AM", "").replace("PM", ""));
        deviceDetailObject.put("restaurantId", Integer.parseInt(restaurantId));
        deviceDetailObject.put("reservationToken", reservationToken);

        if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
            .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {

          deviceDetailObject.put("loyaltyMemberId",
              Integer.parseInt(SharedPreferenceUtils.getInstance(this)
                  .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")));
        }
        deviceDetailObject.put("deviceId", AppUtil.getDeviceId(this));

        //detailObject.put("deviceDetails", deviceDetailObject);

        detailsArray.put(deviceDetailObject);

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

      new HttpClientRequest(this, WebServiceUtil.getUrl(WebServiceUtil.METHOD_MAKE_RESERVATION),
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

        Intent intent = new Intent(this, RestaurantBookingSummaryActivity.class);
        intent.putExtra("restaurantId", restaurantId);
        intent.putExtra("response", responseVal);
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
