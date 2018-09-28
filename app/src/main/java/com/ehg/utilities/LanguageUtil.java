/*
 *  Created by Emaar Hospitality Group on 24/8/18 11:32 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 24/8/18 11:32 AM
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
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import com.ehg.apppreferences.SharedPreferenceUtils;
import java.util.Locale;

/**
 * This class allows to set language locale in app.
 */
public class LanguageUtil {

  /**
   * Sets locale of app (Change application language).
   *
   * @param context context
   * @param languageCode language code.
   */
  public static void setLocale(Context context, String languageCode) {
    try {
      Locale locale = new Locale(languageCode);
      Resources resources = context.getResources();
      DisplayMetrics displayMetrics = resources.getDisplayMetrics();
      Configuration configuration = resources.getConfiguration();
      configuration.locale = locale;

      if (Build.VERSION.SDK_INT >= 17) {
        configuration.setLayoutDirection(locale);
      }

      resources.updateConfiguration(configuration, displayMetrics);

      //Store selected app language in preference
      SharedPreferenceUtils.getInstance(context).setValue(SharedPreferenceUtils.APP_LANGUAGE,
          languageCode);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (RuntimeException rte) {
      rte.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
