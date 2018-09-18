/*
 *  Created by Emaar Hospitality Group on 14/9/18 6:03 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 14/9/18 6:01 PM
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
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.home.BaseActivity;
import com.ehg.home.BaseActivity.BroadCastMessageInterface;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.signinsignup.SignInSignupActivity;
import com.ehg.splash.adapter.SplashPagerAdapter;
import com.ehg.utilities.AppPermissionCheckerUtil;
import com.ehg.utilities.AppUtil;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is the landing class of the application. It loads other feature classes after 3 milliseconds
 * of delay.
 */
public class SplashActivity extends BaseActivity implements BroadCastMessageInterface,
    ApiResponseListener {

  public static final int SPLASH_TIME_OUT = 3000;
  private static final String UPDATE_TOKEN_METHOD = "updateToken";
  public ViewPager cardViewPager;
  private Button buttonSignIn;
  public static int FIRST_PAGE = 1;
  private SplashPagerAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    initView();
    setBroadCastMessageInterface(this);

    /*
     *Checking permission for app.
     */
    AppPermissionCheckerUtil.checkAppPermission(SplashActivity.this,
        new String[] {permission.WRITE_EXTERNAL_STORAGE, permission.ACCESS_FINE_LOCATION});
  }

  /**
   * initialising View component of the Splash Activity.
   */
  private void initView() {

    cardViewPager = findViewById(R.id.viewpager_splash_offers);
    buttonSignIn = findViewById(R.id.button_splash_signin);

    //set page margin between pages for viewpager
    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    int pageMargin = ((metrics.widthPixels / 4) * 2);
    cardViewPager.setPageMargin(-pageMargin);

    buttonSignIn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        switchActivity();

      }
    });

    adapter = new SplashPagerAdapter(this, getSupportFragmentManager());
    cardViewPager.setAdapter(adapter);
    adapter.notifyDataSetChanged();

    cardViewPager.addOnPageChangeListener(adapter);

    // Set current item to the middle page so we can fling to both
    // directions left and right
    cardViewPager.setCurrentItem(FIRST_PAGE);
    cardViewPager.setOffscreenPageLimit(3);
  }

  /**
   * Method to switch another activity based on stored preferences.
   */
  private void switchActivity() throws RuntimeException {

    Intent intent;
        /*if (SharedPreferenceUtils.getInstance(SplashActivity.this)
            .getStringValue(SharedPreferenceUtils.APP_LANGUAGE, "").equalsIgnoreCase("")) {
          intent = new Intent(SplashActivity.this, SignInSignupActivity.class);
        } else {
          intent = new Intent(SplashActivity.this, HomeActivity.class);
        }*/
    intent = new Intent(SplashActivity.this, SignInSignupActivity.class);
    AppUtil.startActivityWithAnimation(SplashActivity.this, intent, true);

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
      AppUtil.showToast(this, getResources().getString(R.string.all_permission_check_alert));
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to update FCM token on cloud.
   */
  private void updateToken() {
    if (AppUtil.isNetworkAvailable(this)) {
      new HttpClientRequest().setApiResponseListner(this);
      JSONObject jsonObject = new JSONObject();
      JSONArray detailesArray = new JSONArray();
      JSONObject detailObject = new JSONObject();

      try {

        detailObject.put("loyaltyMemberId", SharedPreferenceUtils.getInstance(this)
            .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""));

        JSONObject deviceDetailObject = new JSONObject();
        deviceDetailObject.put("deviceType", WebServiceUtil.DEVICE_TYPE);
        deviceDetailObject.put("deviceId", AppUtil.getDeviceId(this));
        deviceDetailObject.put("fcmToken",
            SharedPreferenceUtils.getInstance(this)
                .getStringValue(SharedPreferenceUtils.FCM_TOKEN, ""));

        detailObject.put("deviceDetails", deviceDetailObject);

        detailesArray.put(detailObject);

        jsonObject.put("details", detailesArray);
        jsonObject.put("operation", UPDATE_TOKEN_METHOD);
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

      new HttpClientRequest(this, WebServiceUtil.getUrl(WebServiceUtil.METHOD_UPDATE_TOKEN),
          entity, WebServiceUtil.CONTENT_TYPE,
          UPDATE_TOKEN_METHOD).httpPostRequest();
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
    if (requestMethod.equalsIgnoreCase(UPDATE_TOKEN_METHOD)) {

    }
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
