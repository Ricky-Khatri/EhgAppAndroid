/*
 *  Created by Emaar Hospitality Group on 11/9/18 11:43 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 11/9/18 11:43 AM
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

package com.ehg.signinsignup.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.home.HomeActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.signinsignup.ForgotPasswordActivity;
import com.ehg.signinsignup.pojo.UserProfilePojo;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rilixtech.CountryCodePicker;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A login screen that offers login via all_email/password.
 */
public class SigninFragment extends Fragment implements OnClickListener, ApiResponseListener,
    OnEditorActionListener {

  private static final String USER_LOGIN_METHOD = "userLogin";

  private static final String TAG = "SigninFragment";

  private static final String OPERATION = "login";

  // UI references.
  private AutoCompleteTextView autoCompleteTextViewMobileNumber;
  private EditText editTextPassword;
  private Button buttonLogin;

  private Context context;

  private CountryCodePicker countryCodePicker;
  private String signinId;

  /**
   * Called when fragment created.
   *
   * @param savedInstanceState bundle
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * Called to inflate fragment layout.
   *
   * @param inflater layout inflater
   * @param container view group
   * @param savedInstanceState bundle
   * @return returns view object
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_signin, container, false);
    return view;
  }

  /**
   * Attach context to fragment.
   *
   * @param context activity context
   */
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  /**
   * Called to instantiate fragment view components.
   *
   * @param view view
   * @param savedInstanceState bundle
   */
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    context = getActivity();
    initView(view);
  }

  /**
   * Init's view components on this screen.
   *
   * @param view view object
   */
  private void initView(View view) {

    countryCodePicker = view.findViewById(R.id.countrycodepicker_signinfragment_countrycode);
    countryCodePicker.setCountryForPhoneCode(971);

    AppCompatImageView appCompatImageViewLogo = view.findViewById(R.id.imageview_signin_logo);
    appCompatImageViewLogo.getLayoutParams().height = AppUtil.getDeviceHeight(
        (AppCompatActivity) context) / 4;

    // Set up the login form.
    autoCompleteTextViewMobileNumber = view.findViewById(R.id.textview_signinfragment_mobile);

    TextView textViewUbyEmaarAccount = view.findViewById(R.id.text_view_u_by_emaar_account);
    textViewUbyEmaarAccount.setText(AppUtil.fromHtml(
        getResources().getString(R.string.all_signin_to_tap)
            + "<br><b>" + getResources().getString(R.string.all_u_by_emaar) + "</b> "
            + getResources().getString(R.string.all_account)), TextView.BufferType.SPANNABLE);

    editTextPassword = view.findViewById(R.id.edittext_signinfragment_password);
    editTextPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
          validateSigninFormField();
          return true;
        }
        return false;
      }
    });

    buttonLogin = view.findViewById(R.id.button_signin_login);
    TextView textViewForgotPassword = view.findViewById(R.id.text_view_forgot_password);
    TextView textViewContinueAsGuest = view.findViewById(R.id.textview_signup_continueasguest);

    //Set OnClickListener
    buttonLogin.setOnClickListener(this);
    textViewForgotPassword.setOnClickListener(this);
    textViewContinueAsGuest.setOnClickListener(this);

    //Set EditorActionListener
    autoCompleteTextViewMobileNumber.setOnEditorActionListener(this);
    editTextPassword.setOnEditorActionListener(this);
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

    boolean isToReturn = false;

    if (index == EditorInfo.IME_ACTION_DONE) {

      validateSigninFormField();

    } else {

      switch (textView.getId()) {
        case R.id.textview_signinfragment_mobile:
          // Store values at the time of the login attempt.
          String mobileNumber = autoCompleteTextViewMobileNumber.getText().toString().trim();
          // Check for a valid mobile number
          if (TextUtils.isEmpty(mobileNumber)) {
            autoCompleteTextViewMobileNumber.setError(getString(R.string.all_fieldrequired));
            isToReturn = true;
          } else if (!AppUtil.isValidMobile(mobileNumber)) {
            autoCompleteTextViewMobileNumber.setError(getString(R.string.all_invalidmobile));
            isToReturn = true;
          } else {
            isToReturn = false;
          }
          break;

        case R.id.edittext_signinfragment_password:
          String password = editTextPassword.getText().toString().trim();
          if (TextUtils.isEmpty(password)) {
            editTextPassword.setError(getString(R.string.all_fieldrequired));
            isToReturn = true;
          } else if (!isPasswordValid(password)) {
            editTextPassword.setError(getString(R.string.all_passwordlength));
            isToReturn = true;
          } else {
            isToReturn = false;
          }
          break;

        default:
          break;
      }
    }
    return isToReturn;
  }

  /**
   * Called when any view clicked.
   */
  @Override
  public void onClick(View view) {

    Intent intent;

    switch (view.getId()) {

      case R.id.button_signin_login:
        validateSigninFormField();
        break;

      case R.id.text_view_forgot_password:
        intent = new Intent(context, ForgotPasswordActivity.class);
        AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, false);
        break;

      case R.id.textview_signup_continueasguest:
        intent = new Intent(context, HomeActivity.class);
        AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, true);
        break;

      default:
        break;
    }
  }

  /**
   * Called when parent activity resumed.
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
      // Reset errors.
      autoCompleteTextViewMobileNumber.setError(null);
      editTextPassword.setError(null);
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * Attempts to sign in or register the account specified by the login form. If there are form
   * errors (invalid all_email, missing fields, etc.), the errors are presented and no actual login
   * attempt is made.
   */
  private void validateSigninFormField() {

    resetErrors();

    // Store values at the time of the login attempt.
    String mobileNumber = autoCompleteTextViewMobileNumber.getText().toString().trim();
    String password = editTextPassword.getText().toString().trim();

    boolean cancel = false;
    View focusView = null;

    // Check for a valid all_email address.
    if (TextUtils.isEmpty(mobileNumber)) {
      autoCompleteTextViewMobileNumber.setError(getString(R.string.all_fieldrequired));
      focusView = autoCompleteTextViewMobileNumber;
      cancel = true;

    } else if (!AppUtil.isValidMobile(mobileNumber)) {
      autoCompleteTextViewMobileNumber.setError(getString(R.string.all_invalidmobile));
      focusView = autoCompleteTextViewMobileNumber;
      cancel = true;

    } else if (TextUtils.isEmpty(password)) {
      editTextPassword.setError(getString(R.string.all_fieldrequired));
      focusView = editTextPassword;
      cancel = true;

    } else if (!isPasswordValid(password)) {
      editTextPassword.setError(getString(R.string.all_passwordlength));
      focusView = editTextPassword;
      cancel = true;
    }

    if (cancel) {
      // There was an error; don't attempt login and focus the first
      // form field with an error.
      focusView.requestFocus();
    } else {
      // Show a progress spinner, and kick off a background task to
      // perform the user login attempt.
      AppUtil.hideKeyboard(context, editTextPassword);

      userSignin(mobileNumber, password);
    }
  }

  /**
   * Checks if password is valid or not.
   */
  private boolean isPasswordValid(String password) {
    return password.length() > 4;
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Method allows to authenticate user at Emaar cloud.
   */
  private void userSignin(String mobileNumber, String password) {
    if (AppUtil.isNetworkAvailable(context)) {
      new HttpClientRequest().setApiResponseListner(this);
      JSONObject jsonObject = new JSONObject();
      JSONArray detailesArray = new JSONArray();
      JSONObject detailObject = new JSONObject();

      try {
        signinId = "00" + countryCodePicker.getSelectedCountryCode() + mobileNumber;
        detailObject.put("accountId", signinId);
        detailObject.put("password", password);

        JSONObject deviceDetailObject = new JSONObject();
        deviceDetailObject.put("deviceType", WebServiceUtil.DEVICE_TYPE);
        deviceDetailObject.put("deviceId", AppUtil.getDeviceId(context));
        deviceDetailObject.put("fcmToken",
            SharedPreferenceUtils.getInstance(context)
                .getStringValue(SharedPreferenceUtils.FCM_TOKEN, ""));

        detailObject.put("deviceDetails", deviceDetailObject);

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

      new HttpClientRequest(context, WebServiceUtil.getUrl(WebServiceUtil.METHOD_LOGIN),
          entity, WebServiceUtil.CONTENT_TYPE,
          USER_LOGIN_METHOD, true).httpPostRequest();
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
      if (requestMethod.equalsIgnoreCase(USER_LOGIN_METHOD)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("status")) {

        UserProfilePojo userProfilePojo = new Gson().fromJson(responseVal,
            new TypeToken<UserProfilePojo>() {
            }.getType());

        if (userProfilePojo != null && userProfilePojo.getStatus()) {

          JsonParserUtil.getInstance(context).saveUserProfilePojo(userProfilePojo);
          //Save loyaltyMEmberId
          SharedPreferenceUtils.getInstance(context)
              .setValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID,
                  userProfilePojo.getData().getDetail().get(0).getLoyaltyMemberId() + "");
          //Save mobile number as accoundId
          SharedPreferenceUtils.getInstance(context)
              .setValue(SharedPreferenceUtils.ACCOUNT_ID, signinId);

          Intent intent = new Intent(context, HomeActivity.class);
          AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, true);
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

