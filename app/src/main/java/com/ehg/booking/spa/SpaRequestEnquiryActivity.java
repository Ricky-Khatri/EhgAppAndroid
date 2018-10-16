/*
 *  Created by Emaar Hospitality Group on 16/10/18 11:09 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 16/10/18 11:09 AM
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

package com.ehg.booking.spa;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TimePicker;
import android.widget.Toast;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.restaurant.RestaurantBookingSummaryActivity;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.signinsignup.pojo.Data;
import com.ehg.signinsignup.pojo.Detail;
import com.ehg.signinsignup.pojo.UserProfilePojo;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
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
 * This class allows user to submit enquiry for Spa .
 */
public class SpaRequestEnquiryActivity extends BaseActivity implements
    OnClickListener, OnEditorActionListener, ApiResponseListener {

  private static final String OPERATION = "spa";

  private static AutoCompleteTextView textViewPrefferedDateTime;
  private Context context;
  private AppCompatImageView imageViewBack;
  private TextView textViewHeaderTitle;
  private Spinner spinnerGuestTitle;
  private EditText editTextFirstName;
  private EditText editTextLastName;
  private EditText editTextEmail;
  private EditText editTextPhoneNumber;
  private Spinner spinnerNumberOfpeople;
  private TextView textViewBook;
  private String numberOfGuest = "2";
  private String guestTitle;


  /**
   * Called when Activity created.
   *
   * @param savedInstanceState bundle
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_sparequestenquiry);

    try {

      context = this;
      initView();

    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method init's view components of this screen.
   */
  private void initView() {

    imageViewBack = findViewById(R.id.imageview_header_back);
    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    spinnerGuestTitle = findViewById(R.id.spinner_sparequestenquiry_title);
    editTextFirstName = findViewById(R.id.edittext_sparequestenquiry_firstname);
    editTextLastName = findViewById(R.id.edittext_sparequestenquiry_lastname);
    editTextEmail = findViewById(R.id.edittext_sparequestenquiry_email);
    editTextPhoneNumber = findViewById(R.id.edittext_sparequestenquiry_phonenumber);

    UserProfilePojo userProfilePojo = JsonParserUtil.getInstance(this).getUserProfilePojo();
    if (userProfilePojo.getData() != null && userProfilePojo.getData().getDetail() != null &&
        userProfilePojo.getData().getDetail().size() > 0) {
      Detail detail = userProfilePojo.getData().getDetail().get(0);

      editTextFirstName.setText(detail.getFirstName());
      editTextLastName.setText(detail.getLastName());
      editTextEmail.setText(detail.getEmailId());
      editTextPhoneNumber.setText(detail.getMobileNumber());
    }

    spinnerNumberOfpeople = findViewById(R.id.spinner_sparequestenquiry_numberofpeople);
    textViewPrefferedDateTime = findViewById(R.id.textview_sparequestenquiry_preferreddatetime);
    textViewBook = findViewById(R.id.textview_sparequestenquiry_booking);

    textViewPrefferedDateTime.setOnClickListener(this);
    textViewBook.setOnClickListener(this);

    showGuestTitle();
    showNumberOfGuest();
  }

  /**
   * This method is using to show number of guest.
   */
  private void showNumberOfGuest() {

    // Spinner Drop down elements
    List<String> guestList = new ArrayList<String>();
    guestList.add("1");
    guestList.add("2");
    guestList.add("3");
    guestList.add("4");
    guestList.add("5");
    guestList.add("6");
    guestList.add("7");
    guestList.add("8");
    guestList.add("9");
    guestList.add("10");

    // Creating adapter for spinner
    ArrayAdapter<String> numberOfGuestAdapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, guestList);

    // Drop down layout style - list view with radio button
    numberOfGuestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    spinnerNumberOfpeople.setAdapter(numberOfGuestAdapter);

    spinnerNumberOfpeople.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        numberOfGuest = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  /**
   * this method is using to show guest title.
   */
  private void showGuestTitle() {

    // Spinner Drop down elements
    List<String> userTitlelist = new ArrayList<String>();
    userTitlelist.add("Please Select Title");
    userTitlelist.add("Mr.");
    userTitlelist.add("Mrs.");

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
   * This method is using to show datepicker dialog.
   */
  private void showDatePickerDialog() {
    DialogFragment newFragment = new DatePickerFragment();
    newFragment.show(getSupportFragmentManager(), "datePicker");
  }

  /**
   * Called on view clicked.
   *
   * @param view view
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.textview_sparequestenquiry_preferreddatetime:

        showTimePickerDialog();
        showDatePickerDialog();

        break;
      case R.id.textview_sparequestenquiry_booking:

        validateSignUpFormFields();

        break;

      default:

        break;
    }
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
    String prefferedDateTime = textViewPrefferedDateTime.getText().toString();

    if (guestTitle.equalsIgnoreCase("Please Select Title")) {

      Toast.makeText(context, "Please select Title", Toast.LENGTH_SHORT).show();
      cancel = true;
      focusView = textViewPrefferedDateTime;
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
    } else if (TextUtils.isEmpty(prefferedDateTime)) {

      textViewPrefferedDateTime.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = textViewPrefferedDateTime;
      cancel = true;

    }

    if (cancel) {

      Objects.requireNonNull(focusView).requestFocus();

    } else {

      saveSpaTransactionRequest();
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

      validateSignUpFormFields();

    } else {

      switch (textView.getId()) {
        case R.id.edittext_sparequestenquiry_firstname:
          String firstName = editTextFirstName.getText().toString().trim();
          if (TextUtils.isEmpty(firstName)) {
            editTextFirstName.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;

          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_sparequestenquiry_lastname:
          String lastName = editTextLastName.getText().toString().trim();
          if (TextUtils.isEmpty(lastName)) {
            editTextLastName.setError(getResources().getString(R.string.all_fieldrequired));
            cancel = true;
          } else {
            cancel = false;
          }
          break;

        case R.id.edittext_sparequestenquiry_email:
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

        case R.id.edittext_sparequestenquiry_phonenumber:
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

        default:
          break;
      }
    }
    return cancel;
  }

  /**
   * this method is calling time picker class.
   */
  public void showTimePickerDialog() {
    DialogFragment dialogFragment = new TimePickerFragment();
    dialogFragment.show(getSupportFragmentManager(), "timePicker");
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
      textViewPrefferedDateTime.setError(null);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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
      pickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

      // Create a new instance of DatePickerDialog and return it
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
      // Do something with the date chosen by the user
      textViewPrefferedDateTime.setText(day + "/" + (month + 1) + "/" + year);
      textViewPrefferedDateTime.setError(null);
    }
  }

  /**
   * this classs is showing time picker dialog.
   */
  public static class TimePickerFragment extends DialogFragment implements
      TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      // Use the current time as the default values for the picker
      final Calendar c = Calendar.getInstance();
      int hour = c.get(Calendar.HOUR_OF_DAY);
      int minute = c.get(Calendar.MINUTE);

      // Create a new instance of TimePickerDialog and return it
      return new TimePickerDialog(getActivity(), this, hour, minute,
          DateFormat.is24HourFormat(getActivity()));
    }

    /**
     * Called when the user is done setting a new time and the dialog has closed.
     *
     * @param view the view associated with this listener
     * @param hourOfDay the hour that was set
     * @param minute the minute that was set
     */
    @SuppressLint("SetTextI18n")
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      // Do something with the time chosen by the user
      textViewPrefferedDateTime.setText(textViewPrefferedDateTime.getText()
          + " -" + hourOfDay + ":" + minute);
      textViewPrefferedDateTime.setError(null);
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to make Spa inquiry.
   */
  private void saveSpaTransactionRequest() {
    if (AppUtil.isNetworkAvailable(this)) {
      new HttpClientRequest().setApiResponseListner(this);
      JSONObject jsonObject = new JSONObject();
      JSONArray detailsArray = new JSONArray();
      JSONObject detailObject = new JSONObject();

      try {
        //Date
        Calendar calendar = Calendar.getInstance();
        int date = Calendar.getInstance().get(Calendar.DATE);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        month++;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String spaDate = year + "-" + month + "-" + date;

        //time
        String[] times = calendar.getTime().toString().split(" ");
        String spaTime = "10:00";
        if (times != null && times.length > 0) {
          spaTime = times[3].split(":")[1];
        }

        JSONObject deviceDetailObject = new JSONObject();
        deviceDetailObject.put("ibuId", 1);//TODO: make it dynamic
        deviceDetailObject.put("spaName", "The Spa"); //TODO: make it dynamic
        deviceDetailObject.put("treatmentName", "Balinese Massage");//TODO: make it dynamic
        deviceDetailObject.put("spaDate", spaDate);
        deviceDetailObject.put("spaTime", spaTime);
        deviceDetailObject.put("totalPeople", Integer.parseInt(numberOfGuest));
        deviceDetailObject.put("firstName", editTextFirstName.getText().toString());
        deviceDetailObject.put("lastName", editTextLastName.getText().toString());
        deviceDetailObject.put("emailAddress", editTextEmail.getText().toString());
        deviceDetailObject.put("mobileNumber", editTextPhoneNumber.getText().toString());

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
        jsonObject.put("feature", WebServiceUtil.FEATURE_SAVE_SPA_TRANSACTION);
      } catch (JSONException e) {
        e.printStackTrace();
      }

      StringEntity entity = null;
      try {
        entity = new StringEntity(jsonObject.toString());
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      new HttpClientRequest(this, WebServiceUtil.getUrl(WebServiceUtil.METHOD_SPA_INQUIRY),
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

        Intent intent = new Intent(this, SpaBookingSummaryActivity.class);
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
