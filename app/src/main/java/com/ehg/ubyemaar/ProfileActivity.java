/*
 *  Created by Emaar Hospitality Group on 30/10/18 2:37 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 30/10/18 2:37 PM
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

package com.ehg.ubyemaar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TimePicker;
import android.widget.Toast;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.hotel.HotelListActivity;
import com.ehg.booking.spa.SpaRequestEnquiryActivity;
import com.ehg.booking.spa.SpaRequestEnquiryActivity.DatePickerFragment;
import com.ehg.home.BaseActivity;
import com.ehg.home.HomeActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.signinsignup.pojo.Detail;
import com.ehg.signinsignup.pojo.UserProfilePojo;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This Class is using to show user profile and can edit profile also.
 */
public class ProfileActivity extends BaseActivity
    implements OnClickListener, OnEditorActionListener,
    ApiResponseListener {

  private static final String UPDATE_MEMBER = "updateMember";
  private static final String OPERATION = "updateMember";
  private Context context;
  private TextView textViewMembership;
  private RadioGroup radioGroupGender;
  private RadioButton radioButtonMale;
  private RadioButton radioButtonFemale;
  private Spinner spinnerSalutation;
  private EditText editTextFirstName;
  private EditText editTextLastName;
  private static TextView textViewBirthDay;
  private Spinner spinnerNationality;
  private EditText editTextPhoneNumber;
  private EditText editTextEmailAddress;
  private Spinner spinnerCountry;
  private Spinner spinnerCity;
  private EditText editTextAddress;
  private LinearLayout linearLayoutedit;
  private String country;
  private String guestTitle;
  private String userNationality;
  private String userCity;
  private AppCompatImageView imageViewBack;
  private TextView textViewHeaderTitle;
  private Button buttonSave;
  private String birthDay;
  private String userGender;
  private LinearLayout linearLayoutBirthDay;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      setContentView(R.layout.activity_profile);
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
    textViewHeaderTitle.setText(getResources().getString(R.string.profile_title));
    linearLayoutBirthDay = findViewById(R.id.linearlayout_profile_birthday);
    linearLayoutedit = findViewById(R.id.linearlayout_profile_edit);
    textViewMembership = findViewById(R.id.textview_profile_membershipnumber);
    radioGroupGender = findViewById(R.id.radiogroup_profile);
    radioButtonMale = findViewById(R.id.radiobutton_profile_male);
    radioButtonFemale = findViewById(R.id.radiobutton_profile_female);
    spinnerSalutation = findViewById(R.id.spinner_profile_salutation);
    editTextFirstName = findViewById(R.id.edittext_profile_firstname);
    editTextLastName = findViewById(R.id.edittext_profile_lastname);
    textViewBirthDay = findViewById(R.id.textView_profile_birthday);
    spinnerNationality = findViewById(R.id.spinner_profile_nationality);
    editTextPhoneNumber = findViewById(R.id.edittext_profile_mobilenumber);
    editTextEmailAddress = findViewById(R.id.edittext_profile_email);
    spinnerCountry = findViewById(R.id.spinner_profile_country);
    spinnerCity = findViewById(R.id.spinner_profile_city);
    editTextAddress = findViewById(R.id.edittext_profile_address);
    buttonSave = findViewById(R.id.button_profile_saveinformation);

    radioGroupGender.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {

        // This will get the radiobutton that has changed in its check state
        RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
        // This puts the value (true/false) into the variable
        boolean isChecked = checkedRadioButton.isChecked();
        // If the radiobutton that has changed in check state is now checked...
        if (isChecked) {
          // Changes the textview's text to "Checked: example radiobutton text"
          userGender = checkedRadioButton.getText().toString();
        }
      }
    });

    radioButtonMale.setEnabled(false);
    radioButtonFemale.setEnabled(false);
    spinnerSalutation.setEnabled(false);
    editTextFirstName.setEnabled(false);
    editTextLastName.setEnabled(false);
    textViewBirthDay.setEnabled(false);
    spinnerNationality.setEnabled(false);
    editTextPhoneNumber.setEnabled(false);
    editTextEmailAddress.setEnabled(false);
    spinnerCountry.setEnabled(false);
    spinnerCity.setEnabled(false);
    editTextAddress.setEnabled(false);

    linearLayoutBirthDay.setOnClickListener(this);
    linearLayoutedit.setOnClickListener(this);
    imageViewBack.setOnClickListener(this);
    buttonSave.setOnClickListener(this);
    textViewBirthDay.setOnClickListener(this);

    //initialising list.
    showCountryList("", true);
    showGuestTitle("", true);
    showCityList("", true);
    showNationalityList("", true);

    //If guest is logged in then pre fill form details
    if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
        .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {
      UserProfilePojo userProfilePojo = JsonParserUtil.getInstance(this).getUserProfilePojo();
      if (userProfilePojo.getData() != null && userProfilePojo.getData().getDetail() != null
          && userProfilePojo.getData().getDetail().size() > 0) {

        Detail detail = userProfilePojo.getData().getDetail().get(0);
        editTextFirstName.setText(detail.getFirstName());
        editTextLastName.setText(detail.getLastName());
        editTextEmailAddress.setText(detail.getEmailId());
        String mobileNumber = detail.getMobileNumber();

        if (mobileNumber.length() > 10) {
          mobileNumber = mobileNumber.substring(mobileNumber.length() - 10);
        } else {
          // whatever is appropriate in this case
          throw new IllegalArgumentException("word has less than 10 characters!");
        }
        editTextPhoneNumber.setText(mobileNumber);
        textViewMembership
            .setText(String.format("%s %s", getResources()
                    .getString(R.string.profile_membershipnumber),
                SharedPreferenceUtils.getInstance(context)
                    .getStringValue(SharedPreferenceUtils.ACCOUNT_ID, "")));
        textViewBirthDay.setText(detail.getBirthDate());
        editTextAddress.setText(detail.getAddressLine1());

        if (detail.getGender().equalsIgnoreCase("Male")) {

          //radioButtonMale.setSelected(true);
          radioGroupGender.check(R.id.radiobutton_profile_male);
        } else {
          //radioButtonFemale.setSelected(true);
          radioGroupGender.check(R.id.radiobutton_profile_female);
        }

        if (!TextUtils.isEmpty(detail.getCountry())) {
          showCountryList(detail.getCountry(), false);
        }

        if (!TextUtils.isEmpty(detail.getSuffix())) {
          showGuestTitle(detail.getSuffix(), false);
        }
        if (!TextUtils.isEmpty(detail.getCity())) {
          showCityList(detail.getCity(), false);
        }
        if (!TextUtils.isEmpty(detail.getRegion())) {
          showNationalityList(detail.getRegion(), false);
        }
      }
    }
  }

  /**
   * this method is using to show guest title.
   */
  private void showGuestTitle(String title, boolean isSelectionEnable) {

    // Spinner Drop down elements
    List<String> userTitlelist = new ArrayList<String>();

    if (isSelectionEnable) {

      userTitlelist.add("Mr.");
      userTitlelist.add("Ms.");
    } else {
      userTitlelist.add(title);
    }
    // Creating adapter for spinner
    ArrayAdapter<String> guestTitleAdapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, userTitlelist);

    // Drop down layout style - list view with radio button
    guestTitleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerSalutation.setAdapter(guestTitleAdapter);
    spinnerSalutation.setOnItemSelectedListener(new OnItemSelectedListener() {
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
   * this method is using to show city list.
   */
  private void showCityList(String city, boolean isSelectionEnable) {

    // Spinner Drop down elements
    List<String> countryList = new ArrayList<>();

    if (isSelectionEnable) {

      countryList.add("UAE");
      countryList.add("INDIA");

    } else {
      countryList.add(city);
    }

    // Creating adapter for spinner
    ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this,
        android.R.layout.simple_spinner_item, countryList);

    // Drop down layout style - list view with radio button
    countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    spinnerCity.setAdapter(countryAdapter);

    spinnerCity.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        userCity = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  /**
   * this method is using to show nationality list.
   */
  private void showNationalityList(String nationality, boolean isSelectionEnable) {

    // Spinner Drop down elements
    List<String> countryList = new ArrayList<>();

    if (isSelectionEnable) {

      countryList.add("UAE");
      countryList.add("INDIA");

    } else {
      countryList.add(nationality);
    }

    // Creating adapter for spinner
    ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this,
        android.R.layout.simple_spinner_item, countryList);

    // Drop down layout style - list view with radio button
    countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    spinnerNationality.setAdapter(countryAdapter);

    spinnerNationality.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        userNationality = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  /**
   * this method is using to show country list.
   */
  private void showCountryList(String countryName, boolean isSelectionEnable) {

    // Spinner Drop down elements
    List<String> countryList = new ArrayList<>();

    if (isSelectionEnable) {

      countryList.add("UAE");
      countryList.add("INDIA");

    } else {
      countryList.add(countryName);
    }

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

  @Override
  public void onClick(View view) {
    switch (view.getId()) {

      case R.id.textView_profile_birthday:
        showDatePickerDialog();
        break;

      case R.id.linearlayout_profile_birthday:
        showDatePickerDialog();
        break;

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation((AppCompatActivity) context);
        break;

      case R.id.linearlayout_profile_edit:
        radioButtonMale.setEnabled(true);
        radioButtonFemale.setEnabled(true);
        editTextEmailAddress.setEnabled(true);
        spinnerSalutation.setEnabled(true);
        editTextFirstName.setEnabled(true);
        editTextLastName.setEnabled(true);
        textViewBirthDay.setEnabled(true);
        spinnerNationality.setEnabled(true);
        editTextPhoneNumber.setEnabled(true);
        spinnerCountry.setEnabled(true);
        spinnerCity.setEnabled(true);
        editTextAddress.setEnabled(true);
        buttonSave.setVisibility(View.VISIBLE);
        break;

      case R.id.button_profile_saveinformation:
        validateFormFields();
        break;
      default:
        break;
    }
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
      editTextAddress.setError(null);
      //editTextCity.setError(null);
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
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
    String email = editTextEmailAddress.getText().toString();
    String mobile = editTextPhoneNumber.getText().toString();
    String address = editTextAddress.getText().toString();
    birthDay = textViewBirthDay.getText().toString();
    //birthDay = "04-24-1995";
    if (TextUtils.isEmpty(guestTitle)
        && guestTitle.equalsIgnoreCase("Please Select Title")) {

      focusView = spinnerSalutation;
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

    } else if (TextUtils.isEmpty(birthDay)) {
      focusView = textViewBirthDay;
      AppUtil.showToast(this, "Please select birth day");
      cancel = true;
    } else if (TextUtils.isEmpty(userNationality)) {
      focusView = spinnerNationality;
      AppUtil.showToast(this, "Please select nationality");
      cancel = true;
    } else if (TextUtils.isEmpty(mobile)) {

      editTextPhoneNumber.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextPhoneNumber;
      cancel = true;

    } else if (!AppUtil.isValidMobile(mobile)) {

      editTextPhoneNumber.setError(getResources().getString(R.string.all_invalidmobile));
      focusView = editTextPhoneNumber;
      cancel = true;
    } else if (TextUtils.isEmpty(email)) {

      editTextEmailAddress.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextEmailAddress;
      cancel = true;

    } else if (!AppUtil.isValidEmail(email)) {

      editTextEmailAddress.setError(getResources().getString(R.string.all_invalidemail));
      focusView = editTextEmailAddress;
      cancel = true;

    } /*else if (TextUtils.isEmpty(country)) {
      focusView = spinnerCountry;
      AppUtil.showToast(this, "Please select country");
      cancel = true;

    } else if (TextUtils.isEmpty(userCity)) {
      focusView = spinnerCity;
      AppUtil.showToast(this, "Please select city");
      cancel = true;
    } else if (TextUtils.isEmpty(address)) {

      editTextAddress.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextAddress;
      cancel = true;

    }*/

    if (cancel) {
      Objects.requireNonNull(focusView).requestFocus();
    } else {
      //holdRoomReservation(address, city);
      updateMemberDetail(lastName, firstName, userGender, birthDay, address,
          userCity, country, mobile, "", guestTitle);
    }
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

        case R.id.edittext_profile_firstname:
          String firstName = editTextFirstName.getText().toString().trim();
          if (TextUtils.isEmpty(firstName)) {
            editTextFirstName.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;

          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_profile_lastname:
          String lastName = editTextLastName.getText().toString().trim();
          if (TextUtils.isEmpty(lastName)) {
            editTextLastName.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;
          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_profile_mobilenumber:
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

        case R.id.edittext_profile_email:
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

        case R.id.edittext_profile_address:
          String address = editTextAddress.getText().toString().trim();
          if (TextUtils.isEmpty(address)) {
            editTextAddress.setError(getResources().getString(R.string.all_fieldrequired));
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
   * This method is using to show datepicker dialog.
   */
  private void showDatePickerDialog() {
    DialogFragment newFragment = new DatePickerFragment();
    newFragment.show(getSupportFragmentManager(), "datePicker");
  }

  /**
   * Method allows to authenticate user at Emaar cloud.
   */
  private void updateMemberDetail(String lastName, String firstName, String gender,
      String birthDate, String address,
      String city, String country, String mobileNumber, String password, String guestTitle) {
    if (AppUtil.isNetworkAvailable(context)) {
      new HttpClientRequest().setApiResponseListner(this);
      JSONObject jsonObject = new JSONObject();
      JSONArray detailesArray = new JSONArray();
      JSONObject detailObject = new JSONObject();

      try {
        detailObject.put("lastName", lastName);
        detailObject.put("firstName", firstName);
        //detailObject.put("password", password);

        detailObject.put("gender", gender);
        detailObject.put("suffix", guestTitle);
        detailObject.put("birthDate", birthDate);
        detailObject.put("addressLine1", address);
        detailObject.put("addressLine2", address);
        detailObject.put("city", city);
        detailObject.put("country", country);
        //detailObject.put("postalCode", "");

        detailesArray.put(detailObject);

        jsonObject.put("details", detailesArray);
        jsonObject.put("operation", OPERATION);
        jsonObject.put("feature", WebServiceUtil.FEATURE_SIGN_UP);

      } catch (JSONException e) {
        e.printStackTrace();
      }

      StringEntity entity = null;
      try {
        entity = new StringEntity(jsonObject.toString());
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      new HttpClientRequest(context, WebServiceUtil.getUrl(WebServiceUtil.METHOD_UPDATE_MEMBER)
          + SharedPreferenceUtils.getInstance(context)
          .getStringValue(SharedPreferenceUtils.ACCOUNT_ID, ""),
          entity, WebServiceUtil.CONTENT_TYPE,
          UPDATE_MEMBER, true).httpPutRequest();
    } else {
      AppUtil.showAlertDialog((AppCompatActivity) context,
          context.getResources().getString(R.string.all_please_check_network_settings),
          false, "", true, null);
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * This class is using to show Datepicker dialog.
   */
  public static class DatePickerFragment extends DialogFragment implements
      DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      // Use the current date as the default date in the picker
      final Calendar calendar = Calendar.getInstance();
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH);
      int day = calendar.get(Calendar.DAY_OF_MONTH);

      DatePickerDialog pickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()),
          this, year, month, day);

      // Add 3 days to Calendar
      calendar.add(Calendar.DATE, 6);

      // Subtract 6 days from Calendar updated date
      calendar.add(Calendar.DATE, -6);

      // Set the Calendar new date as minimum date of date picker
      //pickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

      return pickerDialog;
    }

    /**
     * this method is using to set date from clalander.
     *
     * @param view the picker associated with the dialog
     * @param year the selected year
     * @param month the selected month (0-11 for compatibility with {@link Calendar#MONTH})
     * @param day th selected day of the month (1-31, depending on month)
     */
    @SuppressLint("SetTextI18n")
    public void onDateSet(DatePicker view, int year, int month, int day) {
      month = month + 1;

      String convertDay = "";
      String convertMonth = "";
      if (day < 10) {
        convertDay = 0 + String.valueOf(day);
        //day = Integer.parseInt(convertDay);
      } else {
        convertDay = String.valueOf(day);
      }

      if (month < 10) {
        convertMonth = 0 + String.valueOf(month);
        //month = Integer.parseInt(convertMonth);
      } else {
        convertMonth = String.valueOf(month);
      }

      textViewBirthDay.setText(convertMonth + "-" + convertDay + "-" + year);
      textViewBirthDay.setError(null);
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
      if (requestMethod.equalsIgnoreCase(UPDATE_MEMBER)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("status")) {

        UserProfilePojo userProfilePojo = new Gson().fromJson(responseVal,
            new TypeToken<UserProfilePojo>() {
            }.getType());

        if (userProfilePojo != null && userProfilePojo.getStatus()) {

          JsonParserUtil.getInstance(context).setUserProfilePojo(userProfilePojo);
          //Save loyaltyMEmberId
          SharedPreferenceUtils.getInstance(context)
              .setValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID,
                  userProfilePojo.getData().getDetail().get(0).getLoyaltyMemberId() + "");

          Intent intent = new Intent(context, HomeActivity.class);
          intent.putExtra("tab","4");
          AppUtil.showAlertDialog((AppCompatActivity) context,
              userProfilePojo.getMessage(), true,
              getResources().getString(R.string.dialog_alerttitle), false, intent);
        }
      } else if (responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && !new JSONObject(responseVal).getBoolean("status")) {

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
        AppUtil.showAlertDialog((AppCompatActivity) context,
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
    AppUtil.showAlertDialog((AppCompatActivity) context, errorMessage, false,
        getResources().getString(R.string.dialog_errortitle), true, null);
  }

}
