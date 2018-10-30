/*
 *  Created by Emaar Hospitality Group on 29/9/18 7:48 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 29/9/18 7:48 PM
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

package com.ehg.splash;

import android.Manifest.permission;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.firebaseanalytics.FirebaseAnalyticsUtil;
import com.ehg.home.BaseActivity;
import com.ehg.home.BaseActivity.BroadCastMessageInterface;
import com.ehg.home.HomeActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.utilities.AppPermissionCheckerUtil;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.LanguageUtil;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is launching activity.
 */
public class LaunchActivity extends BaseActivity implements BroadCastMessageInterface,
    ApiResponseListener {

  public static final int SPLASH_TIME_OUT = 3000;
  private static final String UPDATE_TOKEN_METHOD = "updateToken";
  private static final String FEATURE_SIGNUP = "signUp";

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launch);

    try {

      //updateToken();

      setBroadCastMessageInterface(this);

      //Get phone default language and configure in app
      LanguageUtil.setDeviceLanguage(this);

      /*
       *Checking permission for app.
       */
      if (AppPermissionCheckerUtil.checkAppPermission(this,
          new String[]{permission.WRITE_EXTERNAL_STORAGE})) {

        switchActivity();
      }

      /*FirebaseAnalyticsUtil.logEhgEvents(this,LaunchActivity.class.getName(),
          "View","AppLaunch","App launched");*/
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method to switch another activity based on stored preferences.
   */
  private void switchActivity() throws RuntimeException {

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent;

        if (TextUtils.isEmpty(SharedPreferenceUtils.getInstance(LaunchActivity.this)
            .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {
          intent = new Intent(LaunchActivity.this, SplashActivity.class);
        } else {
          intent = new Intent(LaunchActivity.this, HomeActivity.class);
        }
        AppUtil.startActivityWithAnimation(LaunchActivity.this, intent, true);

      }
    }, SPLASH_TIME_OUT);
  }

  /**
   * Callback method for BroadCastMessageInterface invoked from BaseActivity.
   *
   * @param message string data
   * @param flag boolean value
   */
  @Override
  public void onMessageReceived(String message, boolean flag) {

    if (!flag) {
      AppUtil.showToast(this, getResources().getString(R.string.all_permissionalert));
    }
    switchActivity();
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to update FCM token on cloud.
   */
  private void updateToken() {
    if (AppUtil.isNetworkAvailable(this)) {
      new HttpClientRequest().setApiResponseListner(this);
      JSONObject jsonObject = new JSONObject();
      JSONArray detailsArray = new JSONArray();
      JSONObject detailObject = new JSONObject();

      try {

        if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(this)
            .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {

          detailObject
              .put("loyaltyMemberId", Integer.parseInt(SharedPreferenceUtils.getInstance(this)
                  .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")));
        }
        JSONObject deviceDetailObject = new JSONObject();
        deviceDetailObject.put("deviceType", WebServiceUtil.DEVICE_TYPE);
        deviceDetailObject.put("deviceId", AppUtil.getDeviceId(this));
        deviceDetailObject.put("fcmToken",
            SharedPreferenceUtils.getInstance(this)
                .getStringValue(SharedPreferenceUtils.FCM_TOKEN, ""));

        detailObject.put("deviceDetails", deviceDetailObject);

        detailsArray.put(detailObject);

        jsonObject.put("details", detailsArray);
        jsonObject.put("operation", UPDATE_TOKEN_METHOD);
        jsonObject.put("feature", FEATURE_SIGNUP);

      } catch (JSONException e) {
        e.printStackTrace();
      }

      StringEntity entity = null;
      try {
        entity = new StringEntity(jsonObject.toString());
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      new HttpClientRequest(this, WebServiceUtil.getUrl(WebServiceUtil.METHOD_UPDATE_TOKEN),
          entity, WebServiceUtil.CONTENT_TYPE,
          UPDATE_TOKEN_METHOD, false).httpPutRequest();
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
    /*if (requestMethod.equalsIgnoreCase(UPDATE_TOKEN_METHOD)) {

    }*/
  }

  /**
   * Called on failure api response.
   *
   * @param errorMessage error string
   */
  @Override
  public void onFailureResponse(String errorMessage) {
  }
}
