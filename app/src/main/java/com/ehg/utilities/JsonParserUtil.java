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

  /**
   * Parameterized constructor of this class.
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

  //*********************** Sign-in Response Pojo *****************************
  /**
   * Called to save UserProfile pojo.
   * @param userProfilePojo object
   */
  public void saveUserProfilePojo(UserProfilePojo userProfilePojo) {

    Gson gson = new Gson();

    String json = gson.toJson(userProfilePojo);

    sharedPreferencesEditor.putString(USER_PROFILE_POJO, json);
    sharedPreferencesEditor.commit();
  }

  /**
   * Returns UserProfilePojo object.
   * @return object
   */
  public UserProfilePojo getUserProfilePojo() {
    Gson gson = new Gson();
    UserProfilePojo userProfilePojo;

    String jsonPreferences = sharedPreferences.getString(USER_PROFILE_POJO, "");

    Type type = new TypeToken<UserProfilePojo>() {}.getType();
    userProfilePojo = gson.fromJson(jsonPreferences, type);

    if (userProfilePojo == null) {
      return new UserProfilePojo();
    }
    return userProfilePojo;
  }
  //****************************************************************************
}