/*
 *  Created by Emaar Hospitality Group on 14/8/18 6:35 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 14/8/18 6:35 PM
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

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * This class checks specific permission from requested activity. And perform proper actions based
 * on permission attributes.
 */

public class AppPermissionCheckerUtil {

  public static final int REQUEST_CODE = 100;

  /**
   * Checks for requested permissions and allows user to choose (Allow / Deny) permissions.
   *
   * @param appCompatActivity context
   * @param permission permissions array
   * @return returns boolean value based on condition.
   */
  public static boolean checkAppPermission(AppCompatActivity appCompatActivity,
      String[] permission) {

    boolean isPermissionGranted = false;

    if (Build.VERSION.SDK_INT >= VERSION_CODES.M) {

      for (String permissionStr : permission) {

        // If permission denied then ask for permission
        if (ContextCompat.checkSelfPermission(appCompatActivity, permissionStr)
            == PackageManager.PERMISSION_DENIED) {

          ActivityCompat.requestPermissions(appCompatActivity,
              permission,
              REQUEST_CODE);

          break;

        } else if (ContextCompat.checkSelfPermission(appCompatActivity, permissionStr)
            != PackageManager.PERMISSION_GRANTED) {

          // If permission not granted then ask for permission
          ActivityCompat.requestPermissions(appCompatActivity,
              permission,
              REQUEST_CODE);

          break;

        } else {
          // Permission has already been granted
          isPermissionGranted = true;
        }
      }
    } else {
      isPermissionGranted = true;
    }

    return isPermissionGranted;
  }
}
