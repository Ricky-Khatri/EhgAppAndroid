/*
 *  Created by Emaar Hospitality Group on 10/8/18 11:27 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 10/8/18 11:27 AM
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

package com.ehg.firebaseanalytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Calendar;

/**
 * This class contains common methods for instantiating and logging firebase events.
 */

public class FirebaseAnalyticsUtil {

  public static FirebaseAnalytics mFirebaseAnalytics;

  /**
   * Firebase general item Events and Params.
   */

  //Events
  public static String VIEW_ITEM_EVENT = FirebaseAnalytics.Event.VIEW_ITEM;
  public static String DVC_EVENT = "DVC_Event";
  public static String SEARCH_EVENT = FirebaseAnalytics.Event.SEARCH;
  public static String EXIT_EVENT = "Exit_Event";

  //Params
  public static String ITEM_CATEGORY_PARAM = FirebaseAnalytics.Param.ITEM_CATEGORY;
  public static String ITEM_ID_PARAM = FirebaseAnalytics.Param.ITEM_ID;
  public static String ITEM_NAME_PARAM = FirebaseAnalytics.Param.ITEM_NAME;
  public static String CONTENT_TYPE_PARAM = FirebaseAnalytics.Param.CONTENT_TYPE;
  public static String PARENT_NAME_PARAM = "parent_name";
  public static String PARENT_TYPE_PARAM = "parent_type";
  public static String ITEM_OPERATION_PARAM = "item_operation";
  public static String ITEM_FEATURE_PARAM = "item_feature";
  public static String ITEM_VALUE_PARAM = "item_value";
  public static String ENTRY_TIME_PARAM = "entry_time";
  public static String FEATURE_PARAM = "feature";
  public static String EXIT_TIME_PARAM = "exit_time";
  public static String USER_ENGAGEMENT_PARAM = "user_engagement";
  public static String INACTIVE_TIME_PARAM = "inactive_time";
  public static String ITEM = "item";

  //Default params
  public static String CATEGORY_PARAM = "Category";
  public static String SUB_CATEGORY_PARAM = "Subcategory";
  public static long ENTRY_TIME = 0;

  /**
   * Firebase Spa module, item Events and Params.
   */
  //Events
  public static String SPA_EVENT = "Spa_Event";
  public static String SPA_SELECT_EVENT = FirebaseAnalytics.Event.SELECT_CONTENT;

  /**
   * Firebase Curtains module, item Events and Params.
   */
  //Params
  public static String OPEN_CURTAIN = "openCurtain";
  public static String CLOSE_CURTAIN = "closeCurtain";
  public static String STOP_CURTAIN = "stopCurtain";


  /**
   * Firebase Inroom Dinning module, item Events and Params.
   */
  //Events
  public static String IRD_EVENT = "IRD_Event";
  public static String ADD_TO_CART_EVENT = FirebaseAnalytics.Event.ADD_TO_CART;
  public static String REMOVE_FROM_CART_EVENT = FirebaseAnalytics.Event.REMOVE_FROM_CART;

  //Default variables
  public static String RESTAURENT_CONTENT_TYPE = "restaurants";
  public static String IRD_CONTENT_TYPE = "Ird";
  public static String IRD_ORDER_CARD = "irdOrderCard";
  public static String IRD_DELIVERY_SHCEDULED = "irdDeliverySchedule";
  public static String VIEW = "View";
  public static String ON_SET_TIME = "actionOnSetTime";
  public static String CHECKOUT = "Checkout";
  public static String ACTION_MAKE_RESERVATION = "actionOnMakeReserVation";
  public static String CONFIRM_RESERVATION = "Confirm Reservation";

  /**
   * Firebase Inroom Dinning module, item Events and Params.
   */
  //Events
  public static String ASSISTANCE_EVENT = "Assistance_Event";
  public static String AMINITIES_EVENT = "Amenities_Event";

  //Default
  public static String AMINITIES_CONTENT_TYPE = "ameinities";
  public static String ASSISTANCE_CONTENT_TYPE = "assistance";

  public static final String SCREEN_NAME = "Screen";
  public static final String EVENT_NAME = "Event";
  public static final String SCREEN_ACTION = "Action";
  public static final String EXTRA = "Extra";
  public static final String DATE_TIME = "DateTime";

  /**
   * This method returns Firebase analytics instance to requested activity.
   * @param context activity context
   * @return
   */
  public static FirebaseAnalytics getFirebaseInstance(Context context) {
    mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    return mFirebaseAnalytics;
  }

  /**
   * This method logs firebase events for requested activity.
   */
  @SuppressLint("InvalidAnalyticsName")
  public static void logFirebaseEvents(Context context,
      String itemId, String itemIdValue,
      String itemName, String itemNameValue,
      String contentType, String contentTypeValue,
      String itemCategory, String itemCategoryValue,
      String parentName, String parentNameValue,
      String parentType, String parentTypeValue,
      String itemOperation, String itemOperationValue,
      String itemFeature, String itemFeatureValue,
      String itemValueName, String itemValue,
      String eventName) {

    if (mFirebaseAnalytics == null) {
      mFirebaseAnalytics = getFirebaseInstance(context);
    }

    if (mFirebaseAnalytics != null) {

      ENTRY_TIME = System.currentTimeMillis();

      Bundle bundle = new Bundle();

      if (itemIdValue != null && !itemIdValue.equalsIgnoreCase("")) {
        bundle.putString(itemId, itemIdValue);
      }

      if (itemNameValue != null && !itemNameValue.equalsIgnoreCase("")) {
        bundle.putString(itemName, itemNameValue);
      }

      if (contentTypeValue != null && !contentTypeValue.equalsIgnoreCase("")) {
        bundle.putString(contentType, contentTypeValue);
      }

      if (itemCategoryValue != null && !itemCategoryValue.equalsIgnoreCase("")) {
        bundle.putString(itemCategory, itemCategoryValue);
      }

      if (parentNameValue != null && !parentNameValue.equalsIgnoreCase("")) {
        bundle.putString(parentName, parentNameValue);
      }

      if (parentTypeValue != null && !parentTypeValue.equalsIgnoreCase("")) {
        bundle.putString(parentType, parentTypeValue);
      }

      if (itemOperationValue != null && !itemOperationValue.equalsIgnoreCase("")) {
        bundle.putString(itemOperation, itemOperationValue);
      }

      if (itemFeatureValue != null && !itemFeatureValue.equalsIgnoreCase("")) {
        bundle.putString(itemFeature, itemFeatureValue);
      }

      if (itemValue != null && !itemValue.equalsIgnoreCase("")) {
        bundle.putString(itemValueName, itemValue);
      }

      mFirebaseAnalytics.logEvent(eventName, bundle);
    }
  }

  /**
   * This method logs firebase Exit feature events for requested activity.
   */
  @SuppressLint("InvalidAnalyticsName")
  public static void logFirebaseExitEvents(Context context, String feature,
      String enteryTime, String exitTime,
      String userEngagement, String inactiveTime) {

    if (mFirebaseAnalytics == null) {
      mFirebaseAnalytics = getFirebaseInstance(context);
    }

    if (mFirebaseAnalytics != null) {

      Bundle bundle = new Bundle();
      bundle.putString(FEATURE_PARAM, feature);
      bundle.putString(ENTRY_TIME_PARAM, enteryTime);
      bundle.putString(EXIT_TIME_PARAM, exitTime);
      bundle.putString(USER_ENGAGEMENT_PARAM, userEngagement);
      bundle.putString(INACTIVE_TIME_PARAM, inactiveTime);

      mFirebaseAnalytics.logEvent(EXIT_EVENT, bundle);
    }
  }

  /**
   * This method logs firebase events for requested activity.
   */
  public static void logEhgEvents(Context context,
      String screenName, String eventName, String screenAction, String extra) {

    if (mFirebaseAnalytics == null) {
      mFirebaseAnalytics = getFirebaseInstance(context);
    }

    if (mFirebaseAnalytics != null) {

      ENTRY_TIME = System.currentTimeMillis();

      Bundle bundle = new Bundle();

      if (!TextUtils.isEmpty(screenName)) {
        bundle.putString(SCREEN_NAME, screenName);
      }

      if (!TextUtils.isEmpty(eventName)) {
        bundle.putString(EVENT_NAME, eventName);
      }

      if (!TextUtils.isEmpty(screenAction)) {
        bundle.putString(SCREEN_ACTION, screenAction);
      }

      if (!TextUtils.isEmpty(extra)) {
        bundle.putString(EXTRA, extra);
      }

      String dateTime = Calendar.getInstance().getTime().toString();
      if (!TextUtils.isEmpty(dateTime)) {
        bundle.putString(DATE_TIME, dateTime);
      }
      mFirebaseAnalytics.logEvent(eventName, bundle);
    }
  }
}
