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
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is used to enter guest detail.
 */
public class RoomBookingGuestDetailActivity extends BaseActivity implements
    OnClickListener, OnEditorActionListener {

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

        intent = new Intent(context, RoomPaymentActivity.class);
        //validateSignUpFormFields();
        break;

      default:
        break;
    }
    AppUtil.startActivityWithAnimation(this, intent, false);
  }


}
