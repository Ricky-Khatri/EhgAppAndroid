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
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.downloadmanager.FileDownloadManager;
import com.ehg.downloadmanager.FileDownloadManager.FileDownloadManagerResponse;
import com.ehg.downloadmanager.FileDownloadUtil;
import com.ehg.home.BaseActivity;
import com.ehg.home.BaseActivity.BroadCastMessageInterface;
import com.ehg.home.HomeActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.signinsignup.SignInSignupActivity;
import com.ehg.splash.adapter.SplashPagerAdapter;
import com.ehg.splash.adapter.SplashPagerAdapter.SetOnPageChangeListener;
import com.ehg.utilities.AppPermissionCheckerUtil;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
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
    ApiResponseListener, FileDownloadManagerResponse, SetOnPageChangeListener {

  private static final String UPDATE_TOKEN_METHOD = "updateToken";
  private static final String FEATURE_SIGNUP = "signUp";

  public ViewPager cardViewPager;
  private Button buttonSignIn;

  public static int FIRST_PAGE = 1;

  private SplashPagerAdapter adapter;

  private TextView textViewBrandTitle;

  private String[] brandTitles = {"Book a Hotel", "Book a Restaurant",
      "Book a Spa", "Book a round of Golf", "Book an Experience"};

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    try {

      initView();

      setBroadCastMessageInterface(this);

      //Register file download manager and init file download process
      FileDownloadManager.setFileDownloadResponseListener(this);
      downloadLanguageJsonFile();

      /*
       *Checking permission for app.
       */
      AppPermissionCheckerUtil.checkAppPermission(SplashActivity.this,
          new String[]{permission.WRITE_EXTERNAL_STORAGE/*, permission.ACCESS_FINE_LOCATION*/});

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to initiate file download process.
   */
  private void downloadLanguageJsonFile() {
    if (AppUtil.isNetworkAvailable(this)) {
      new FileDownloadManager(this).new DownloadFile().execute(
          FileDownloadUtil.LANGUAGE_FOLDER_NAME,
          FileDownloadUtil.FILE_DOWNLOAD_BASE_URL + FileDownloadUtil.LANGUAGE_FILE_NAME,
          FileDownloadUtil.LANGUAGE_FILE_NAME);
    }
  }

  /**
   * Callback method called by fileDownloadResponseListener.
   *
   * @param isFileDownloaded boolean
   * @param fileName downloaded filename
   */
  @Override
  public void isFileDownloaded(boolean isFileDownloaded, String fileName) {
    if (isFileDownloaded) {
      String languageJson = new FileDownloadManager(this)
          .getJsonString(FileDownloadUtil.getRootFolderPath() + "/"
              + FileDownloadUtil.LANGUAGE_FOLDER_NAME + "/"
              + FileDownloadUtil.LANGUAGE_FILE_NAME);

      JsonParserUtil.getInstance(this).setStringValue(JsonParserUtil.LANGUAGE_JSON, languageJson);
    }
  }

  /**
   * initialising View component of the Splash Activity.
   */
  private void initView() {

    buttonSignIn = findViewById(R.id.button_splash_signin);
    textViewBrandTitle = findViewById(R.id.textview_splash_brandtitle);

    if (SharedPreferenceUtils.getInstance(this)
        .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")
        .equalsIgnoreCase("")) {
      buttonSignIn.setText(getResources().getString(R.string.splash_signin));
    } else {
      buttonSignIn.setText(getResources().getString(R.string.splash_next));
    }

    cardViewPager = findViewById(R.id.viewpager_splash_offers);

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
    textViewBrandTitle.setText(brandTitles[FIRST_PAGE]);
  }

  /**
   * Called when viewpager page changed or scrolled.
   * @param position current position of page
   */
  @Override
  public void onPageChange(int position) {
    if (brandTitles.length > 0 && position < brandTitles.length) {
      textViewBrandTitle.setText(brandTitles[position % brandTitles.length]);
    }
  }

  /**
   * Method to switch another activity based on stored preferences.
   */
  private void switchActivity() throws RuntimeException {

    Intent intent;
    if (SharedPreferenceUtils.getInstance(SplashActivity.this)
        .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")
        .equalsIgnoreCase("")) {
      intent = new Intent(SplashActivity.this, SignInSignupActivity.class);
    } else {
      intent = new Intent(SplashActivity.this, HomeActivity.class);
    }
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
      AppUtil.showToast(this, getResources().getString(R.string.all_permissionalert));
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
      JSONArray detailsArray = new JSONArray();
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
          UPDATE_TOKEN_METHOD, false).httpPostRequest();
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
