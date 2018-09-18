/*
 * Created by Emaar Hospitality Group on 8/8/18 11:41 AM
 * Copyright (C) 2018.  All rights reserved.
 * Last modified 8/8/18 11:41 AM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ehg.networkrequest;

import android.content.Context;
import android.text.TextUtils;
import com.ehg.R;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.FirebasePerformance.HttpMethod;
import com.google.firebase.perf.metrics.HttpMetric;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * This class makes HttpRequest for calling API or making internet calls.
 */

public class HttpClientRequest {

  private ApiResponseListener apiResponseListner;

  private static final int TIME_OUT = 600000;

  private Context context;

  private StringEntity entity;
  private String contentType;
  private String url;
  private String requestMethod;
  private String errorMessage;

  private RequestParams params;

  private AsyncHttpClient asyncHttpClient;

  //Fire-base api monitor members
  private HttpMetric httpMetric;

  /**
   * Default constructor for HttpRequest class.
   */
  public HttpClientRequest() {
  }

  /**
   * This constructor will use to initialized the parameter of this class for Api calling.
   *
   * @param context - context of calling class.
   * @param url - base url
   * @param stringEntity - data entity
   * @param contentType - contentType of api call
   * @param methodClass - calling class method
   */
  public HttpClientRequest(Context context, String url, StringEntity stringEntity,
      String contentType, String methodClass) {

    asyncHttpClient = new AsyncHttpClient(true, 80, 443);
    asyncHttpClient.setConnectTimeout(TIME_OUT);
    this.url = url;
    this.context = context;
    entity = stringEntity;

    this.contentType = contentType;

    requestMethod = methodClass;
    errorMessage = context.getResources().getString(R.string.api_error_message);
  }

  /**
   * This constructor will use to initialized the parameter of this class for Api calling.
   *
   * @param context - context of calling class
   * @param url - base url
   * @param requestParams - data as a parameter
   * @param contentType - contentType of api call
   * @param methodClass - calling class method
   */

  public HttpClientRequest(Context context, String url, RequestParams requestParams,
      String contentType, String methodClass) {

    asyncHttpClient = new AsyncHttpClient(true, 80, 443);
    asyncHttpClient.setConnectTimeout(TIME_OUT);
    this.url = url;
    this.context = context;
    params = requestParams;

    this.contentType = contentType;

    requestMethod = methodClass;
    errorMessage = context.getResources().getString(R.string.api_error_message);
  }

  /**
   * This method will use to call POST type request to the server.
   */
  public void httpPostRequest() {

    try {

      startFirebaseMonitorTrace(HttpMethod.POST);

      asyncHttpClient.addHeader("Access-Token", "dummy"); //TODO: Need to pass access-token

      asyncHttpClient.post(context, url, entity, contentType, new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

          try {

            stopFirebaseMonitorTrace();

            String result = new String(responseBody);

            if (TextUtils.isEmpty(result)) {
              apiResponseListner.onFailureResponse(errorMessage);
            } else {
              apiResponseListner.onSuccessResponse(result, requestMethod);
            }

          } catch (NullPointerException e) {
            apiResponseListner.onFailureResponse(errorMessage);
            e.printStackTrace();
          } catch (Exception e) {
            apiResponseListner.onFailureResponse(errorMessage);
            e.printStackTrace();
          }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers,
            byte[] responseBody, Throwable error) {

          try {

            stopFirebaseMonitorTrace();

            String result = new String(responseBody);

            if (TextUtils.isEmpty(result)) {
              apiResponseListner.onFailureResponse(errorMessage);
            } else {
              apiResponseListner.onFailureResponse(result);
            }

          } catch (NullPointerException e) {
            apiResponseListner.onFailureResponse(errorMessage);
          } catch (Exception e) {
            apiResponseListner.onFailureResponse(errorMessage);
          }
        }
      });

    } catch (NullPointerException e) {
      apiResponseListner.onFailureResponse(errorMessage);
    } catch (Exception e) {
      apiResponseListner.onFailureResponse(errorMessage);
    }
  }

  /**
   * This method will use to call GET type request to the server.
   */
  public void httpGetRequest() {

    try {

      startFirebaseMonitorTrace(HttpMethod.GET);

      asyncHttpClient.addHeader("Access-Token", "dummy");//TODO: Need to update value
      asyncHttpClient.addHeader("Content-Type", contentType);

      asyncHttpClient.get(context, url, params, new TextHttpResponseHandler() {
        @Override
        public void onFailure(int statusCode, Header[] headers,
            String result, Throwable throwable) {

          try {

            stopFirebaseMonitorTrace();

            if (TextUtils.isEmpty(result)) {
              apiResponseListner.onFailureResponse(errorMessage);
            } else {
              apiResponseListner.onFailureResponse(result);
            }

          } catch (NullPointerException e) {
            apiResponseListner.onFailureResponse(errorMessage);
          } catch (Exception e) {
            apiResponseListner.onFailureResponse(errorMessage);
          }
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String result) {
          try {

            stopFirebaseMonitorTrace();

            if (TextUtils.isEmpty(result)) {
              apiResponseListner.onFailureResponse(errorMessage);
            } else {
              apiResponseListner.onSuccessResponse(result, requestMethod);
            }
          } catch (NullPointerException e) {
            apiResponseListner.onFailureResponse(errorMessage);
          } catch (Exception e) {
            apiResponseListner.onFailureResponse(errorMessage);
          }
        }
      });

    } catch (NullPointerException e) {
      apiResponseListner.onFailureResponse(errorMessage);
    } catch (Exception e) {
      apiResponseListner.onFailureResponse(errorMessage);
    }
  }

  /*private void fetchAccessTokenPost(final String callingMethod) {

    try {
      //String tokenUrl = new SharedData(context).getAccessTokenApi();

      Log.e("running--acessToken-", "");

      JSONObject jsonObject = new JSONObject();

      *//* try {
        jsonObject.put("client_id", new SharedData(context).getClientId());
        jsonObject.put("client_secret", new SharedData(context).getClientSecret());

        try {
          StringEntity entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException ignored) {

          ignored.fillInStackTrace();
        }

      } catch (JSONException e) {
        e.printStackTrace();
      }*//*

   *//*  asyncHttpClient.post(context, tokenUrl, entity, mcontentType
    , new AsyncHttpResponseHandler() {

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

          String result = new String(responseBody);

          if (TextUtils.isEmpty(result)) {
            result = "";
          }

          String accesToken = "";
          try {

            JSONObject jsonObject1 = new JSONObject(result);

            JSONObject jsonObject2 = jsonObject1.getJSONObject("data");

            accesToken = jsonObject2.getString("access_token");

          } catch (JSONException e) {
            e.printStackTrace();
          }

          if (!TextUtils.isEmpty(result)) {

            new SharedData(context).setAccessToken(accesToken);

          }

          if (!TextUtils.isEmpty(callingMethod)) {

            if (callingMethod.equalsIgnoreCase("GET")) {

              makeHttpGetRequest();

            } else if (callingMethod.equalsIgnoreCase("POST")) {

              makeHttpPostRequest();

            }
            //sendRequestRespons.getResponce_refreshToken(callingMethod);
          }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers,
            byte[] responseBody, Throwable error) {

          Log.e("Access_token-onFailure", Arrays.toString(responseBody));
        }
      });*//*

    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }*/

  //********************* Fire base performance monitor ********************************

  /**
   * Method starts Firebase api performance monitor.
   *
   * @param methodType methodType (GET/ POST)
   */
  private void startFirebaseMonitorTrace(String methodType) {
    httpMetric =
        FirebasePerformance.getInstance().newHttpMetric(url,
            methodType);
    httpMetric.start();
  }

  /**
   * Method stops Firebase api performance monitor.
   */
  private void stopFirebaseMonitorTrace() {
    if (httpMetric != null) {
      httpMetric.stop();
    }
  }

  //************************************************************************************

  //-------ApiResponse--Interface-----//

  public void setApiResponseListner(ApiResponseListener apiResponseListner) {

    this.apiResponseListner = apiResponseListner;
  }

  public interface ApiResponseListener {

    void onSuccessResponse(String responseVal, String requestMethod);

    void onFailureResponse(String errorMessage);
  }
}
