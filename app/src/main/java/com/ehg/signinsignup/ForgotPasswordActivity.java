/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:46 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 21/9/18 3:21 PM
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

package com.ehg.signinsignup;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ehg.R;
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
    ApiResponseListener, OnEditorActionListener {

  private AutoCompleteTextView textViewMobileNumber;
  private AutoCompleteTextView textViewall_email;

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
    textViewall_email = findViewById(R.id.textview_forgot_password_all_email);
    Button buttonSubmit = findViewById(R.id.button_forgot_password_reset_password);

    //Set OnclickListener
    buttonSubmit.setOnClickListener(this);
    findViewById(R.id.imageview_header_back).setOnClickListener(this);

    //Set OnEditorActionListener
    textViewMobileNumber.setOnEditorActionListener(this);
    textViewall_email.setOnEditorActionListener(this);
  }

  /**
   * Called when phone keyboard action clicked.
   * @param textView focused view
   * @param index index
   * @param keyEvent key event
   * @return boolean
   */
  @Override
  public boolean onEditorAction(TextView textView, int index, KeyEvent keyEvent) {
    validateUsername();
    return false;
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
   * Method checks for valid all_email or phone number. And on success submit it to Emaar cloud by
   * calling forgotPassword api.
   */
  private void validateUsername() {

    textViewMobileNumber.setError(null);
    textViewall_email.setError(null);

    String mobileNumber = textViewMobileNumber.getText().toString();
    String all_email = textViewall_email.getText().toString();

    // Check for a valid username
    if (TextUtils.isEmpty(mobileNumber) && TextUtils.isEmpty(all_email)) {
      textViewMobileNumber.setError(getString(R.string.all_erroremailormobile));
      textViewMobileNumber.requestFocus();

    } else if (!TextUtils.isEmpty(mobileNumber) && !TextUtils.isEmpty(all_email)) {
      textViewMobileNumber.setError(getString(R.string.all_erroremailormobile));
      textViewMobileNumber.requestFocus();

    } else {

      if (!TextUtils.isEmpty(mobileNumber)) {
        if (!AppUtil.isValidMobile(mobileNumber)) {
          textViewMobileNumber.setError(getString(R.string.all_invalidmobile));
          textViewMobileNumber.requestFocus();
        } else {
          forgotPassword(mobileNumber);
        }
      } else if (!TextUtils.isEmpty(all_email)) {
        if (!AppUtil.isall_emailValid(all_email)) {
          textViewall_email.setError(getString(R.string.all_invalidemail));
          textViewall_email.requestFocus();
        } else {
          forgotPassword(all_email);
        }
      }
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Method calls forgotPassword api.
   */
  private void forgotPassword(String accountId) {

    if (AppUtil.isNetworkAvailable(this)) {

      new HttpClientRequest().setApiResponseListner(this);

      new HttpClientRequest(this, WebServiceUtil.getUrl(WebServiceUtil.METHOD_RESET_PASSWORD)
          + accountId,
          new RequestParams(), WebServiceUtil.CONTENT_TYPE,
          FORGOT_PASSWORD_METHOD,true).httpGetRequest();
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
        getResources().getString(R.string.dialog_errortitle), true);
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
