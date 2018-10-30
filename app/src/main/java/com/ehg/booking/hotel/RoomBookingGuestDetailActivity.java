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
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.AverageRate;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.FetchAvailabilityResponsePojo;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Address;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Customer;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.HoldRoomReservationRequestPojo;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Profile;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Rate;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.RatePlan;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.ResGlobalInfo;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.ResGuest;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.ReservationRequestParam;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.RoomRate;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.RoomStay;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Telephone;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.TimeSpan;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.signinsignup.pojo.UserProfilePojo;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
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
  private EditText editTextFrequentGuestId;
  private EditText editTextSpecialRequest;
  private Spinner spinnerCountry;
  private TextView textViewAmount;
  private TextView textViewNext;
  private String guestTitle;
  private AverageRate averageRate;
  private String country;
  private String requestString;
  private String uniqueId;

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

    Intent intent = getIntent();
    if (intent != null && intent.getStringExtra("averageRate") != null) {
      Type type = new TypeToken<AverageRate>() {
      }.getType();
      averageRate = new Gson()
          .fromJson(intent.getStringExtra("averageRate"), type);
    }
    imageViewBack = findViewById(R.id.imageview_header_back);
    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    if (getIntent() != null && getIntent().getStringExtra("title") != null) {
      textViewHeaderTitle.setText(getIntent().getStringExtra("title"));
    }
    spinnerGuestTitle = findViewById(R.id.spinner_roombookingguestdetail_title);
    editTextFirstName = findViewById(R.id.edittext_roombookingguestdetail_firstname);
    editTextLastName = findViewById(R.id.edittext_roombookingguestdetail_lastname);
    editTextEmail = findViewById(R.id.edittext_roombookingguestdetail_email);
    editTextPhoneNumber = findViewById(R.id.edittext_roombookingguestdetail_phonenumber);
    editTextAddress = findViewById(R.id.edittext_roombookingguestdetail_address);
    editTextAddressNewline = findViewById(R.id.edittext_roombookingguestdetail_addressnewline);
    editTextCity = findViewById(R.id.edittext_roombookingguestdetail_city);
    editTextFrequentGuestId = findViewById(R.id.edittext_roombookingguestdetail_frequentguestid);

    //If guest is logged in then pre fill form details
    if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
        .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {
      UserProfilePojo userProfilePojo = JsonParserUtil.getInstance(this).getUserProfilePojo();
      if (userProfilePojo.getData() != null && userProfilePojo.getData().getDetail() != null
          && userProfilePojo.getData().getDetail().size() > 0) {

        com.ehg.signinsignup.pojo.Detail detail = userProfilePojo.getData().getDetail().get(0);
        String mobileNumber = detail.getMobileNumber();
        if (mobileNumber.length() == 10) {
        } else if (mobileNumber.length() > 10) {
          mobileNumber = mobileNumber.substring(mobileNumber.length() - 10);
        } else {
          // whatever is appropriate in this case
          throw new IllegalArgumentException("word has less than 10 characters!");
        }
        editTextFirstName.setText(detail.getFirstName());
        editTextLastName.setText(detail.getLastName());
        editTextEmail.setText(detail.getEmailId());
        editTextPhoneNumber.setText(mobileNumber);
        editTextFrequentGuestId.setText(SharedPreferenceUtils.getInstance(this)
            .getStringValue(SharedPreferenceUtils.ACCOUNT_ID, ""));
      }
    }

    editTextSpecialRequest = findViewById(R.id.edittext_roombookingguestdetail_specialrequest);
    //Set OnEditorActionListener
    editTextSpecialRequest.setOnEditorActionListener(new OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int index, KeyEvent keyEvent) {
        if (index == EditorInfo.IME_ACTION_DONE) {
          validateFormFields();
        }
        return false;
      }
    });
    spinnerCountry = findViewById(R.id.spinner_roombookingguestdetail_country);
    textViewAmount = findViewById(R.id.textview_roombookingguestdetail_amount);
    if (averageRate != null) {
      textViewAmount.setText(SharedPreferenceUtils.getInstance(this)
          .getStringValue(SharedPreferenceUtils.APP_CURRENCY, "AED")
          + " " + averageRate.getRate());
    }
    textViewNext = findViewById(R.id.textview_roombookingguestdetail_next);

    findViewById(R.id.imageview_header_back).setOnClickListener(this);

    textViewNext.setOnClickListener(this);
    showGuestTitle();
    showCountryList();
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

      validateFormFields();

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
   * Method validates form fields.
   */
  private void validateFormFields() {

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

    if (TextUtils.isEmpty(guestTitle)
        && guestTitle.equalsIgnoreCase("Please Select Title")) {
      focusView = spinnerGuestTitle;
      Toast.makeText(context, "Please select Title", Toast.LENGTH_SHORT).show();
      cancel = true;

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

    } else if (TextUtils.isEmpty(country)) {

      AppUtil.showToast(this, "Please select country");
      cancel = true;

    } else if (TextUtils.isEmpty(city)) {

      editTextCity.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextCity;
      cancel = true;
    }

    if (cancel) {
      Objects.requireNonNull(focusView).requestFocus();
    } else {
      holdRoomReservation(address, city);
    }
  }

  /**
   * this method is using to show guest title.
   */
  private void showGuestTitle() {

    // Spinner Drop down elements
    List<String> userTitlelist = new ArrayList<String>();
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
   * this method is using to show country list.
   */
  private void showCountryList() {

    // Spinner Drop down elements
    List<String> countryList = new ArrayList<>();
    countryList.add("UAE");
    countryList.add("INDIA");

    // Creating adapter for spinner
    ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this,
        android.R.layout.simple_spinner_item, countryList);

    // Drop down layout style - list view with radio button
    countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    spinnerCountry.setAdapter(countryAdapter);

    spinnerCountry.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        country = parent.getItemAtPosition(position).toString();
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

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation((AppCompatActivity) context);
        break;

      case R.id.textview_roombookingguestdetail_next:
        validateFormFields();
        break;

      default:
        break;
    }
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
   * Called to hold multiple room reservation.
   */
  private void holdRoomReservation(String address, String city) {
    try {
      if (AppUtil.isNetworkAvailable(context)) {
        new HttpClientRequest().setApiResponseListner(this);

        FetchRoomAvailabilityRequestPojo fetchRoomAvailabilityRequestPojo = JsonParserUtil
            .getInstance(this)
            .getFetchAvailabilityRequestPojo();
        List<com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo.Detail>
            fetchAvailablityDetailList = fetchRoomAvailabilityRequestPojo.getDetails();

        if (fetchAvailablityDetailList != null && fetchAvailablityDetailList.size() > 0) {

          com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Detail
              detail = new com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Detail();
          detail.setLanguageCode(SharedPreferenceUtils.getInstance(this)
              .getStringValue(SharedPreferenceUtils.APP_LANGUAGE, "en"));
          detail.setDeviceId(AppUtil.getDeviceId(this));
          detail.setIbuId(2);//TODO:Make it dynamic
          if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
              .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {
            detail.setLoyaltyMemberId(Integer.parseInt(SharedPreferenceUtils.getInstance(this)
                .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")));
          }

          ReservationRequestParam reservationRequestParam = new ReservationRequestParam();
          //reservationRequestParam.setPosSource(null);//TODO:

          Customer customer = new Customer();

          String firstName = editTextFirstName.getText().toString().trim();
          String lastName = editTextLastName.getText().toString().trim();
          String email = editTextEmail.getText().toString().trim();

          customer.setGivenName(firstName);
          customer.setEmail(email);
          customer.setCompanyShortName(firstName);
          customer.setNamePrefix(guestTitle);
          customer.setSurName(lastName);
          Address address1 = new Address();
          address1.setAddressLine1(address);
          address1.setCityName(city);
          List<Address> addressList = new ArrayList<>();
          addressList.add(address1);
          customer.setAddress(addressList);
          //telephone
          List<Telephone> telephoneList = new ArrayList<>();
          Telephone telephone = new Telephone();
          String phone = editTextPhoneNumber.getText().toString().trim();
          telephone.setPhoneNumber(phone);
          telephone.setPhoneUseType("Mobile");
          telephoneList.add(telephone);
          customer.setTelephone(telephoneList);

          ResGuest resGuest = new ResGuest();
          Profile profile = new Profile();
          if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
              .getStringValue(SharedPreferenceUtils.ACCOUNT_ID, ""))) {
            profile.setFrequestGuestId(editTextFrequentGuestId.getText().toString().trim());
          }
          profile.setCustomer(customer);
          resGuest.setProfile(profile);
          List<ResGuest> resGuestList = new ArrayList<>();
          resGuestList.add(resGuest);
          reservationRequestParam.setResGuests(resGuestList);

          Detail fetchAvailabilityDetail = fetchAvailablityDetailList.get(0);

          //resGlobalInfo.setRooms();

          List<com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.GuestCount>
              guestCountList = new ArrayList<>();
          com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.GuestCount
              guestCount = new com.ehg.booking.hotel.pojo
              .holdroomreservationrequestpojo.GuestCount();
          guestCount.setAgeQualifyingCode("10");
          guestCount.setCount(fetchAvailabilityDetail.getTotalAdults() + "");
          guestCountList.add(guestCount);
          guestCount = new com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.GuestCount();
          guestCount.setAgeQualifyingCode("8");
          guestCount.setCount(fetchAvailabilityDetail.getTotalChildren() + "");
          guestCountList.add(guestCount);
          guestCount = new com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.GuestCount();
          guestCount.setAgeQualifyingCode("7");
          guestCount.setCount(fetchAvailabilityDetail.getTotalInfants() + "");
          guestCountList.add(guestCount);
          ResGlobalInfo resGlobalInfo = new ResGlobalInfo();
          resGlobalInfo.setGuestCounts(guestCountList);

          //TimeSpan
          //Calculate stay duration
          int dayDifference = (int) getDateDiff(
              new SimpleDateFormat("yyyy-MM-dd"),
              fetchAvailabilityDetail.getCheckInDate(), fetchAvailabilityDetail.getCheckInDate());
          TimeSpan timeSpan = new TimeSpan();
          timeSpan.setDuration(dayDifference + "");
          timeSpan.setStart(fetchAvailabilityDetail.getCheckInDate());
          timeSpan.setEnd(fetchAvailabilityDetail.getCheckOutDate());
          resGlobalInfo.setTimeSpan(timeSpan);

          List<RatePlan> ratePlanList = new ArrayList<>();
          RatePlan ratePlan = new RatePlan();
          ratePlan.setRatePlanCode(averageRate.getRatePlanCode());
          ratePlan.setRatePlanType(averageRate.getRatePlanType());
          ratePlanList.add(ratePlan);
          List<RoomRate> roomRateList = new ArrayList<>();
          RoomRate roomRate = new RoomRate();
          List<Rate> rateList = new ArrayList<>();
          Rate rate = new Rate();
          rate.setAmountBeforeTax(averageRate.getRate() + "");
          roomRate.setRoomTypeCode(averageRate.getRoomTypeCode());
          roomRate.setRates(rateList);
          roomRate.setNumberOfUnits(fetchAvailabilityDetail.getTotalRooms());
          roomRateList.add(roomRate);
          List<RoomStay> roomStayList = new ArrayList<>();
          RoomStay roomStay = new RoomStay();
          roomStay.setRoomRates(roomRateList);
          roomStay.setRatePlans(ratePlanList);
          roomStayList.add(roomStay);
          reservationRequestParam.setRoomStays(roomStayList);
          reservationRequestParam.setResGlobalInfo(resGlobalInfo);
          List<ReservationRequestParam> reservationRequestParamList = new ArrayList<>();
          reservationRequestParamList.add(reservationRequestParam);
          detail.setReservationRequestParams(reservationRequestParamList);
          List<com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Detail>
              detailList = new ArrayList<>();
          detailList.add(detail);
          HoldRoomReservationRequestPojo holdRoomReservationRequestPojo =
              new HoldRoomReservationRequestPojo();
          holdRoomReservationRequestPojo.setFeature("roomReservation");
          holdRoomReservationRequestPojo.setOperation("RoomReservation");
          holdRoomReservationRequestPojo.setDetails(detailList);

          Gson gson = new Gson();
          requestString = gson
              .toJson(holdRoomReservationRequestPojo, HoldRoomReservationRequestPojo.class);

          //Temporary request
          HoldRoomReservationRequestPojo tempHoldRoomReservationRequestPojo
              = holdRoomReservationRequestPojo;

          reservationRequestParamList = new ArrayList<>();
          reservationRequestParam.setResGuests(null);
          reservationRequestParamList.add(reservationRequestParam);
          detail.setReservationRequestParams(reservationRequestParamList);
          detailList = new ArrayList<>();
          detailList.add(detail);
          tempHoldRoomReservationRequestPojo.setDetails(detailList);

          String holdReservationRequest = gson
              .toJson(holdRoomReservationRequestPojo, HoldRoomReservationRequestPojo.class);

          StringEntity entity = null;
          try {
            entity = new StringEntity(holdReservationRequest);
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

        JSONObject jsonObject = new JSONObject(responseVal);
        JSONObject dataObject = jsonObject.getJSONObject("Data");
        JSONArray detailArray = dataObject.optJSONArray("Detail");
        if (detailArray != null && detailArray.length() > 0) {
          for (int index = 0; index < detailArray.length(); index++) {
            JSONObject detailObject = detailArray.getJSONObject(index);
            JSONObject responseDataObject = detailObject.optJSONObject("ResponseData");
            JSONArray reservationResponsesArray = responseDataObject
                .optJSONArray("ReservationResponses");
            if (reservationResponsesArray != null && reservationResponsesArray.length() > 0) {
              uniqueId = reservationResponsesArray.getJSONObject(0).getString("UniqueId");
            }
            if (!TextUtils.isEmpty(uniqueId)) {
              Intent intent = new Intent(this, RoomPaymentActivity.class);
              intent.putExtra("holdReservationRequest", requestString);
              intent.putExtra("title", textViewHeaderTitle.getText().toString());
              intent.putExtra("uniqueId", uniqueId);
              AppUtil.startActivityWithAnimation(this, intent, false);
              break;
            }
          }
        }
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
