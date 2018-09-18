/*
 *  Created by Emaar Hospitality Group on 25/8/18 11:04 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 24/8/18 6:04 PM
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

package com.ehg.settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.utilities.AppUtil;
import com.loopj.android.http.RequestParams;
import com.rilixtech.CountryCodePicker;

/**
 * This class allows to get access of forgotten user password.
 */
public class ForgotPasswordActivity extends BaseActivity implements OnClickListener,
    ApiResponseListener {

  private AutoCompleteTextView textViewMobileNumber;
  private AutoCompleteTextView textViewEmail;

  private static final String FORGOT_PASSWORD_METHOD = "forgotPassword";
  private CountryCodePicker countryCodePicker;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forgot_password);

    initView();
  }

  /**
   * Method init's view components.
   */
  private void initView() {

    countryCodePicker = findViewById(R.id.countrycodepicker_forgot_password_countrycode);
    countryCodePicker.setCountryForPhoneCode(971);

    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(R.string.all_forgotpassword);

    textViewMobileNumber = findViewById(R.id.textview_forgot_password_mobile_number);
    textViewEmail = findViewById(R.id.textview_forgot_password_email);
    Button buttonSubmit = findViewById(R.id.button_forgot_password_reset_password);
    buttonSubmit.setOnClickListener(this);

    findViewById(R.id.imageview_header_back).setOnClickListener(this);
  }

  /**
   * Called when any view clicked.
   *
   * @param view view
   */
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.button_forgot_password_reset_password:
        validateUsername();
        break;

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      default:
        break;
    }
  }

  /**
   * Method checks for valid email or phone number. And on success submit it to Emaar cloud by
   * calling forgotPassword api.
   */
  private void validateUsername() {

    textViewMobileNumber.setError(null);
    textViewEmail.setError(null);

    String mobileNumber = textViewMobileNumber.getText().toString();
    String email = textViewEmail.getText().toString();

    // Check for a valid username
    if (TextUtils.isEmpty(mobileNumber) && TextUtils.isEmpty(email)) {
      textViewMobileNumber.setError(getString(R.string.all_please_enter_email_or_mobile_number));
      textViewMobileNumber.requestFocus();

    } else {

      if (!TextUtils.isEmpty(mobileNumber)) {
        if (!AppUtil.isValidMobile(mobileNumber)) {
          textViewMobileNumber.setError(getString(R.string.all_error_invalid_mobile));
          textViewMobileNumber.requestFocus();
        } else {
          //TODO: Need to uncomment forgotPassword()
          //forgotPassword();
          finish();
        }
      } else if (!TextUtils.isEmpty(email)) {
        if (!AppUtil.isEmailValid(email)) {
          textViewEmail.setError(getString(R.string.all_error_invalid_email));
          textViewEmail.requestFocus();
        } else {
          //TODO: Need to uncomment forgotPassword()
          //forgotPassword();
          finish();
        }
      }
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Method calls forgotPassword api.
   */
  private void forgotPassword() {
    if (AppUtil.isNetworkAvailable(this)) {
      new HttpClientRequest().setApiResponseListner(this);

      new HttpClientRequest(this, WebServiceUtil.getUrl(WebServiceUtil.METHOD_RESET_PASSWORD)
          + SharedPreferenceUtils.getInstance(this)
          .getStringValue(SharedPreferenceUtils.ACCOUNT_ID, ""),
          new RequestParams(), WebServiceUtil.CONTENT_TYPE,
          FORGOT_PASSWORD_METHOD).httpGetRequest();
    }
  }

  /**
   * Called on successful api response.
   *
   * @param responseVal response string
   * @param requestMethod requested method name
   */
  @Override
  public void onSuccessResponse(String responseVal, String requestMethod) {
    if (FORGOT_PASSWORD_METHOD.equalsIgnoreCase(requestMethod)) {
      //TODO: Parse api response
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
        getResources().getString(R.string.alert_dialog_title_error), true);
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
}
