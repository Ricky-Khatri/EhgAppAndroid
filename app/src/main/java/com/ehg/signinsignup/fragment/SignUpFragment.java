/*
 *  Created by Emaar Hospitality Group on 11/9/18 11:44 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 11/9/18 11:44 AM
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
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.HomeActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.utilities.AppUtil;
import com.rilixtech.CountryCodePicker;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class allows new user to sign up in app as Emaar member.
 */

public class SignUpFragment extends Fragment implements OnClickListener, ApiResponseListener {

  private static final String USER_SIGNUP_METHOD = "userSignup";

  private static final String OPERATION = "quickEnrolment";

  private EditText edittextFirstName;
  private EditText edittextLastName;
  private EditText edittextEmail;
  private EditText edittextMobile;
  private EditText edittextPassword;

  private Context context;
  private CountryCodePicker countryCodePicker;

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
   * Called to inflate fragment layout.
   *
   * @param inflater layout inflater
   * @param container view group
   * @param savedInstanceState bundle
   * @return returns view object
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_sign_up,
        container, false);

    return view;
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
   * Method init's view components of this screen.
   *
   * @param view view object
   */
  private void initView(View view) {

    countryCodePicker = view.findViewById(R.id.countrycodepicker_signup_countrycode);
    countryCodePicker.setCountryForPhoneCode(971);
    
    AppCompatImageView appCompatImageViewLogo = view.findViewById(R.id.imageview_sign_up_logo);
    appCompatImageViewLogo.getLayoutParams().height = AppUtil.getDeviceHeight(
        (AppCompatActivity) context) / 4;

    edittextFirstName = view.findViewById(R.id.edittext_sign_up_first_name);
    edittextLastName = view.findViewById(R.id.edittext_sign_up_last_name);
    edittextEmail = view.findViewById(R.id.edittext_sign_up_email);
    edittextMobile = view.findViewById(R.id.edittext_sign_up_mobile);
    edittextPassword = view.findViewById(R.id.edittext_sign_up_password);

    TextView textViewUbyEmaarAccount = view.findViewById(R.id.text_view_u_by_emaar_account);
    textViewUbyEmaarAccount.setText(Html.fromHtml(
        getResources().getString(R.string.signupfragment_sign_up_and_create)
            + "<b> "
            + getResources().getString(R.string.all_u_by_emaar) + "</b> "
            + getResources().getString(R.string.all_account)),TextView.BufferType.SPANNABLE);

    TextView textViewContinueAsGuest = view.findViewById(R.id.text_view_continue_as_guest);

    TextView textViewWhatIsUbyEmaar = view.findViewById(R.id.text_view_what_is_u_by_emaar_account);

    Button buttonSignUp = view.findViewById(R.id.button_sign_up);

    buttonSignUp.setOnClickListener(this);
    textViewContinueAsGuest.setOnClickListener(this);
    textViewWhatIsUbyEmaar.setOnClickListener(this);
  }

  /**
   * Called on view clicked.
   *
   * @param view view
   */
  @Override
  public void onClick(View view) {
    switch (view.getId()) {

      case R.id.button_sign_up:
        validateSignUpFormFields();
        //userSignup();
        break;

      case R.id.text_view_continue_as_guest:
        Intent intent = new Intent(context, HomeActivity.class);
        AppUtil.startActivityWithAnimation((AppCompatActivity)context, intent, true);
        break;

      case R.id.text_view_what_is_u_by_emaar_account:
        break;

      default:
        break;
    }
  }

  /**
   * Method validates sign up form fields.
   */
  private void validateSignUpFormFields() {
    edittextFirstName.setError(null);
    edittextLastName.setError(null);
    edittextEmail.setError(null);
    edittextMobile.setError(null);
    edittextPassword.setError(null);

    boolean cancel = false;
    View focusView = null;

    String firstName = edittextFirstName.getText().toString();
    String lastName = edittextLastName.getText().toString();
    String email = edittextEmail.getText().toString();
    String mobile = edittextMobile.getText().toString();
    String password = edittextPassword.getText().toString();

    if (TextUtils.isEmpty(firstName)) {

      edittextFirstName.setError(getResources().getString(R.string.all_error_field_required));
      focusView = edittextFirstName;
      cancel = true;

    } else if (TextUtils.isEmpty(lastName)) {

      edittextLastName.setError(getResources().getString(R.string.all_error_field_required));
      focusView = edittextLastName;
      cancel = true;

    } else if (TextUtils.isEmpty(email)) {

      edittextEmail.setError(getResources().getString(R.string.all_error_field_required));
      focusView = edittextEmail;
      cancel = true;

    } else if (!AppUtil.isEmailValid(email)) {

      edittextEmail.setError(getResources().getString(R.string.all_error_invalid_email));
      focusView = edittextEmail;
      cancel = true;

    } else if (TextUtils.isEmpty(mobile)) {

      edittextMobile.setError(getResources().getString(R.string.all_error_field_required));
      focusView = edittextMobile;
      cancel = true;

    } else if (!AppUtil.isMobileValid(mobile)) {

      edittextMobile.setError(getResources().getString(R.string.all_error_invalid_mobile));
      focusView = edittextMobile;
      cancel = true;

    } else if (TextUtils.isEmpty(password)) {

      edittextPassword.setError(getResources().getString(R.string.all_error_field_required));
      focusView = edittextPassword;
      cancel = true;

    } else if (!isPasswordValid(password)) {

      edittextPassword.setError(getResources().getString(R.string.all_error_invalid_password));
      focusView = edittextPassword;
      cancel = true;

    }

    if (cancel) {

      focusView.requestFocus();

    } else {

      Intent intent = new Intent(context, HomeActivity.class);
      AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, true);
      //userSignup();
    }
  }

  /**
   * Checks if password is valid or not.
   */
  private boolean isPasswordValid(String password) {
    //TODO: Replace this with your own logic
    return password.length() != 10;
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Method registers user at Emaar cloud.
   */
  private void userSignup(String emailId, String mobileNumber, String firstName, String lastName) {
    if (AppUtil.isNetworkAvailable(context)) {
      new HttpClientRequest().setApiResponseListner(this);
      JSONObject jsonObject = new JSONObject();
      JSONArray detailesArray = new JSONArray();
      JSONObject detailObject = new JSONObject();

      try {
        detailObject.put("emailId", emailId);
        detailObject.put("mobileNumber", mobileNumber);
        detailObject.put("lastName", lastName);
        detailObject.put("firstName", firstName);

        detailesArray.put(detailObject);

        jsonObject.put("details", detailesArray);
        jsonObject.put("operation", OPERATION);
        jsonObject.put("feature", WebServiceUtil.METHOD_SIGN_UP);

      } catch (JSONException e) {
        e.printStackTrace();
      }

      StringEntity entity = null;
      try {
        entity = new StringEntity(jsonObject.toString());
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      new HttpClientRequest(context, WebServiceUtil.getUrl(WebServiceUtil.METHOD_SIGN_UP),
          entity, WebServiceUtil.CONTENT_TYPE,
          USER_SIGNUP_METHOD).httpPostRequest();
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
    if (requestMethod.equalsIgnoreCase(USER_SIGNUP_METHOD)) {
      /*Intent intent = new Intent(this, SigninFragment.class);
      startActivity(intent);
      finish();*/
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
        getResources().getString(R.string.alert_dialog_title_error), true);
  }
}
