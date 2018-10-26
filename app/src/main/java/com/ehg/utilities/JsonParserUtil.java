/*
 *  Created by Emaar Hospitality Group on 18/9/18 5:51 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 18/9/18 5:51 PM
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

package com.ehg.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.FetchAvailabilityResponsePojo;
import com.ehg.booking.hotel.pojo.roomareasearchrequestpojo.RoomAreaSearchRequestPojo;
import com.ehg.booking.hotel.pojo.roomareasearchresponsepojo.RoomAreaSearchResponsePojo;
import com.ehg.signinsignup.pojo.UserProfilePojo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * This class allows to locally store response pojo objects in preferences.
 */
public class JsonParserUtil {

  private static JsonParserUtil jsonParserUtil;
  protected Context context;
  private SharedPreferences sharedPreferences;
  private SharedPreferences.Editor sharedPreferencesEditor;

  //Constants
  private static final String USER_PROFILE_POJO = "UserProfilePojo";
  private static final String ROOM_AREA_SEARCH_REQUEST_POJO = "RoomAreaSearchRequestPojo";
  private static final String ROOM_AREA_SEARCH_RESPONSE_POJO = "RoomAreaSearchRseponsePojo";
  private static final String FETCH_AVAILABILITY_RESPONSE_POJO = "FetchAvailabilityRseponsePojo";
  public static final String LANGUAGE_JSON = "LanguageJson";

  /**
   * Parameterized constructor of this class.
   *
   * @param context activity context
   */
  private JsonParserUtil(Context context) {
    this.context = context;
    sharedPreferences = context.getSharedPreferences("EhgPreferences", Context.MODE_PRIVATE);
    sharedPreferencesEditor = sharedPreferences.edit();
  }

  /**
   * Creates single instance of SharedPreferenceUtils.
   *
   * @param context context of Activity or Service
   * @return Returns instance of SharedPreferenceUtils
   */
  public static synchronized JsonParserUtil getInstance(Context context) {

    if (jsonParserUtil == null) {
      jsonParserUtil = new JsonParserUtil(context.getApplicationContext());
    }
    return jsonParserUtil;
  }

  // Setter and Getter methods for storing pojo class objects.

  /**
   * Called to save UserProfile pojo.
   *
   * @param userProfilePojo object
   */
  public void setUserProfilePojo(UserProfilePojo userProfilePojo) {

    Gson gson = new Gson();

    String json = gson.toJson(userProfilePojo);

    sharedPreferencesEditor.putString(USER_PROFILE_POJO, json);
    sharedPreferencesEditor.commit();
  }

  /**
   * Returns UserProfilePojo object.
   *
   * @return object
   */
  public UserProfilePojo getUserProfilePojo() {
    Gson gson = new Gson();
    UserProfilePojo userProfilePojo;

    String jsonPreferences = sharedPreferences.getString(USER_PROFILE_POJO, "");

    Type type = new TypeToken<UserProfilePojo>() {
    }.getType();
    userProfilePojo = gson.fromJson(jsonPreferences, type);

    if (userProfilePojo == null) {
      return new UserProfilePojo();
    }
    return userProfilePojo;
  }

  /**
   * Called to save RoomAreaSearchRequestPojo.
   *
   * @param roomAreaSearchRequestPojo object
   */
  public void setRoomAreaSearchRequestPojo(RoomAreaSearchRequestPojo roomAreaSearchRequestPojo) {

    Gson gson = new Gson();

    String json = gson.toJson(roomAreaSearchRequestPojo);

    sharedPreferencesEditor.putString(ROOM_AREA_SEARCH_REQUEST_POJO, json);
    sharedPreferencesEditor.commit();
  }

  /**
   * Returns RoomAreaSearchRequestPojo.
   *
   * @return object
   */
  public RoomAreaSearchRequestPojo getRoomAreaSearchRequestPojo() {
    Gson gson = new Gson();
    RoomAreaSearchRequestPojo roomAreaSearchRequestPojo;

    String jsonPreferences = sharedPreferences.getString(ROOM_AREA_SEARCH_REQUEST_POJO, "");

    Type type = new TypeToken<RoomAreaSearchRequestPojo>() {
    }.getType();
    roomAreaSearchRequestPojo = gson.fromJson(jsonPreferences, type);

    if (roomAreaSearchRequestPojo == null) {
      return new RoomAreaSearchRequestPojo();
    }
    return roomAreaSearchRequestPojo;
  }

  /**
   * Called to save RoomAreaSearchRequestPojo.
   *
   * @param roomAreaSearchResponsePojo object
   */
  public void setRoomAreaSearchResponsePojo(RoomAreaSearchResponsePojo roomAreaSearchResponsePojo) {

    Gson gson = new Gson();

    String json = gson.toJson(roomAreaSearchResponsePojo);

    sharedPreferencesEditor.putString(ROOM_AREA_SEARCH_RESPONSE_POJO, json);
    sharedPreferencesEditor.commit();
  }

  /**
   * Returns RoomAreaSearchResponsePojo.
   *
   * @return object
   */
  public RoomAreaSearchResponsePojo getRoomAreaSearchResponsePojo() {
    Gson gson = new Gson();
    RoomAreaSearchResponsePojo roomAreaSearchResponsePojo;

    String jsonPreferences = sharedPreferences.getString(ROOM_AREA_SEARCH_RESPONSE_POJO, "");

    Type type = new TypeToken<RoomAreaSearchResponsePojo>() {
    }.getType();
    roomAreaSearchResponsePojo = gson.fromJson(jsonPreferences, type);

    if (roomAreaSearchResponsePojo == null) {
      return new RoomAreaSearchResponsePojo();
    }
    return roomAreaSearchResponsePojo;
  }

  /**
   * Called to save RoomAreaSearchResponsePojo.
   *
   * @param fetchAvailabilityResponsePojo object
   */
  public void setFetchAvailabilityResponsePojo(
      FetchAvailabilityResponsePojo fetchAvailabilityResponsePojo) {

    Gson gson = new Gson();

    String json = gson.toJson(fetchAvailabilityResponsePojo);

    sharedPreferencesEditor.putString(FETCH_AVAILABILITY_RESPONSE_POJO, json);
    sharedPreferencesEditor.commit();
  }

  /**
   * Returns FetchAvailabilityResponsePojo.
   *
   * @return object
   */
  public FetchAvailabilityResponsePojo getFetchAvailabilityResponsePojo() {
    Gson gson = new Gson();
    FetchAvailabilityResponsePojo fetchAvailabilityResponsePojo;

    String jsonPreferences = sharedPreferences.getString(FETCH_AVAILABILITY_RESPONSE_POJO, "");

    Type type = new TypeToken<FetchAvailabilityResponsePojo>() {
    }.getType();
    fetchAvailabilityResponsePojo = gson.fromJson(jsonPreferences, type);

    if (fetchAvailabilityResponsePojo == null) {
      return new FetchAvailabilityResponsePojo();
    }
    return fetchAvailabilityResponsePojo;
  }
  //****************************************************************************

  /**
   * Stores String value in preference.
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setStringValue(String key, String value) {
    sharedPreferencesEditor.putString(key, value);
    sharedPreferencesEditor.commit();
  }

  /**
   * Retrieves String value from preference.
   *
   * @param key key of preference
   * @param defaultValue default value if no key found
   */
  public String getStringValue(String key, String defaultValue) {
    return sharedPreferences.getString(key, defaultValue);
  }
}
