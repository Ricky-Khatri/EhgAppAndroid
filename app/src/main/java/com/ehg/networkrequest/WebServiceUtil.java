/*
 *  Created by Emaar Hospitality Group on 10/8/18 5:47 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 10/8/18 5:47 PM
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

package com.ehg.networkrequest;

/**
 * This class contains all web service constant variables and method names.
 */

public class WebServiceUtil {

  //API base url
  //public static final String BASE_URL = "http://192.168.3.28:8080/digivalet-ehg-gateway/";

  //Dev environment
  //public static final String BASE_URL = "https://his-dev.digivalet.com/dv_app_ehggateway/";
  //public static final String BASE_URL = "https://172.25.0.72/dv_app_ehggateway/";

  //Local ip for reservations
  public static final String BASE_URL = "http://192.168.3.66:8080/digivalet-ehg-gateway";

  public static final String DEVICE_TYPE = "Android";

  //API Content type
  public static final String CONTENT_TYPE = "application/json";

  //Signup method
  public static final String METHOD_SIGN_UP = "signUp/quickEnrollment/v1";

  //Login method
  public static final String METHOD_LOGIN = "signUp/login/v1";

  //Logout method
  public static final String METHOD_LOGOUT = "signUp/logout/v1";

  //UpdateToken method
  public static final String METHOD_UPDATE_TOKEN = "updateToken";

  //ResetPassword method
  public static final String METHOD_RESET_PASSWORD = "signUp/resetPassword/v1/";

  //GetMemberDetails method
  public static final String METHOD_GET_MEMBER_DETAIL = "signUp/memberDetails/v1/";

  //FetchAvailability api for restaurant reservations method
  public static final String METHOD_GET_RESTAURANT_AVAILABILITY = "/restaurantReservation/fetchAvailability/v1/";

  //LockReservation method
  public static final String METHOD_LOCK_RESERVATION = "/restaurantReservation/lockReservation/v1";

  //MakeReservation method
  public static final String METHOD_MAKE_RESERVATION = "/restaurantReservation/makeReservation/v1";

  //CancelReservation method
  public static final String METHOD_CANCEL_RESERVATION = "/restaurantReservation/cancelReservation/v1/";



  //Api feature constants
  public static final String FEATURE_SIGN_UP = "signUp";
  public static final String FEATURE_DINNING = "Dining";

  /**
   * Returns complete api url.
   * @param methodName name of api method
   * @return complete api url
   */
  public static String getUrl(String methodName) {
    return BASE_URL + methodName;
  }
}
