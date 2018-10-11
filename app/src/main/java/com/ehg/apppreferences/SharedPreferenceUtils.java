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

package com.ehg.apppreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class stores and returns preference data from application local memory.
 */
public class SharedPreferenceUtils {

  private static SharedPreferenceUtils sharedPreferenceUtils;
  protected Context context;
  private SharedPreferences sharedPreferences;
  private SharedPreferences.Editor sharedPreferencesEditor;

  //SharedPreference keys
  public static final String APP_LANGUAGE = "AppLanguage";
  public static final String FCM_TOKEN = "FcmToken";
  public static final String LOYALTY_MEMBER_ID = "LoyaltyMemberId";
  public static final String ACCOUNT_ID = "AccountId";

  /**
   * Parameterized constructor of this class.
   * @param context activity context
   */
  private SharedPreferenceUtils(Context context) {
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
  public static synchronized SharedPreferenceUtils getInstance(Context context) {

    if (sharedPreferenceUtils == null) {
      sharedPreferenceUtils = new SharedPreferenceUtils(context.getApplicationContext());
    }
    return sharedPreferenceUtils;
  }

  /**
   * Stores String value in preference.
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setValue(String key, String value) {
    sharedPreferencesEditor.putString(key, value);
    sharedPreferencesEditor.commit();
  }

  /**
   * Stores int value in preference.
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setValue(String key, int value) {
    sharedPreferencesEditor.putInt(key, value);
    sharedPreferencesEditor.commit();
  }

  /**
   * Stores Double value in String format in preference.
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setValue(String key, double value) {
    setValue(key, Double.toString(value));
  }

  /**
   * Stores long value in preference.
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setValue(String key, long value) {
    sharedPreferencesEditor.putLong(key, value);
    sharedPreferencesEditor.commit();
  }

  /**
   * Stores boolean value in preference.
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setValue(String key, boolean value) {
    sharedPreferencesEditor.putBoolean(key, value);
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

  /**
   * Retrieves int value from preference.
   *
   * @param key key of preference
   * @param defaultValue default value if no key found
   */
  public int getIntValue(String key, int defaultValue) {
    return sharedPreferences.getInt(key, defaultValue);
  }

  /**
   * Retrieves long value from preference.
   *
   * @param key key of preference
   * @param defaultValue default value if no key found
   */
  public long getLongValue(String key, long defaultValue) {
    return sharedPreferences.getLong(key, defaultValue);
  }

  /**
   * Retrieves boolean value from preference.
   *
   * @param keyFlag key of preference
   * @param defaultValue default value if no key found
   */
  public boolean getBoolanValue(String keyFlag, boolean defaultValue) {
    return sharedPreferences.getBoolean(keyFlag, defaultValue);
  }

  /**
   * Removes key from preference.
   *
   * @param key key of preference that is to be deleted
   */
  public void removeKey(String key) {
    if (sharedPreferencesEditor != null) {
      sharedPreferencesEditor.remove(key);
      sharedPreferencesEditor.commit();
    }
  }


  /**
   * Clears all the preferences stored.
   */
  public void clear() {
    sharedPreferencesEditor.clear().commit();
  }
}
