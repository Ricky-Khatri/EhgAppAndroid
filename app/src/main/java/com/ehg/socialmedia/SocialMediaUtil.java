/*
 *  Created by Emaar Hospitality Group on 16/8/18 12:08 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 16/8/18 12:08 PM
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

package com.ehg.socialmedia;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import com.ehg.R;
import com.ehg.utilities.AppUtil;
import java.io.FileNotFoundException;

/**
 * This class allows to share users activity on social media.
 */

public class SocialMediaUtil {

  //Tweeter constants
  public static final String TWITTER_URL = "https://twitter.com/intent/tweet?text=";
  public static final String TWITTER_PACKAGE_NAME = "com.twitter.android";

  //Facebook constants
  public static final String FACEBOOK_URL = "https://www.facebook.com/";
  public static final String FACEBOOK_PACKAGE_NAME = "com.facebook.katana";

  //Instagram constants
  public static final String INSTAGRAM_URL = "https://www.instagram.com";
  public static final String INSTAGRAM_PACKAGE_NAME = "com.instagram.android";

  /**
   * This method shares user post on tweeter.
   *
   * @param appCompatActivity context
   * @param message user message
   * @param urlOptional url is optional parameter
   */
  public static void sharePostOnTwitter(AppCompatActivity appCompatActivity, String message,
      String urlOptional) {

    if (AppUtil.isNetworkAvailable(appCompatActivity)) {

      if (appCompatActivity != null) {

        String tweetUrl = TWITTER_URL + message;

        if (urlOptional != null && !urlOptional.equalsIgnoreCase("") && urlOptional.length() > 0) {

          tweetUrl = tweetUrl + "&url=" + urlOptional;
        }

        // If Twitter app installed then share via app else share via browser.
        if (isAppInstalled(appCompatActivity, TWITTER_PACKAGE_NAME)) {

          Intent shareIntent = new Intent(Intent.ACTION_SEND);
          shareIntent.setPackage(TWITTER_PACKAGE_NAME);
          shareIntent.setType("text/*");
          shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
          appCompatActivity.startActivity(shareIntent);

        } else {

          Uri uri = Uri.parse(tweetUrl);
          appCompatActivity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
      }
    } else {
      AppUtil.showToast(appCompatActivity,
          appCompatActivity.getResources().getString(R.string.all_please_check_network_settings));
    }
  }


  /**
   * This method shares user post on facebook.
   *
   * @param appCompatActivity context
   * @param message user message
   */
  public static void sharePostOnFacebook(AppCompatActivity appCompatActivity, String message) {

    if (AppUtil.isNetworkAvailable(appCompatActivity)) {

      if (appCompatActivity != null) {

        Intent shareIntent;

        // If Facebook app installed then share via app else share via browser.
        if (isAppInstalled(appCompatActivity, FACEBOOK_PACKAGE_NAME)) {

          shareIntent = new Intent(Intent.ACTION_SEND);
          shareIntent.setPackage(FACEBOOK_PACKAGE_NAME);
          shareIntent.setType("text/plain");
          shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);

        } else {

          String sharerUrl = FACEBOOK_URL + message;
          shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        appCompatActivity.startActivity(shareIntent);
      }

    } else {
      AppUtil.showToast(appCompatActivity,
          appCompatActivity.getResources().getString(R.string.all_please_check_network_settings));
    }
  }

  /**
   * This method shares user post on Instagram.
   *
   * @param appCompatActivity context
   * @param imagePath imagePath to be shared
   * @param title post title
   * @param message post message
   */
  public static void sharePostOnInstagram(AppCompatActivity appCompatActivity, String imagePath,
      String title, String message) {

    if (AppUtil.isNetworkAvailable(appCompatActivity)) {

      if (appCompatActivity != null) {

        Intent shareIntent;

        // If Facebook app installed then share via app else share via browser.
        if (isAppInstalled(appCompatActivity, INSTAGRAM_PACKAGE_NAME)) {

          shareIntent = new Intent();
          shareIntent.setAction(Intent.ACTION_SEND);
          shareIntent.setPackage(INSTAGRAM_PACKAGE_NAME);
          try {
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(
                MediaStore.Images.Media.insertImage(appCompatActivity.getContentResolver(),
                    imagePath, title, message)));

          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          shareIntent.setType("image/jpeg");

        } else {

          String sharerUrl = INSTAGRAM_URL;
          shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        appCompatActivity.startActivity(shareIntent);
      }
    } else {
      AppUtil.showToast(appCompatActivity,
          appCompatActivity.getResources().getString(R.string.all_please_check_network_settings));
    }
  }

  /**
   * Returns true if requested app installed in phone else return false.
   *
   * @param appCompatActivity context
   * @param packageName package name of requested app
   */
  public static boolean isAppInstalled(AppCompatActivity appCompatActivity, String packageName) {

    boolean isInstalled = false;

    if (appCompatActivity != null) {

      PackageManager packageManager = appCompatActivity.getPackageManager();
      try {

        ApplicationInfo info = packageManager.getApplicationInfo(packageName, 0);

        isInstalled = true;

      } catch (NameNotFoundException e) {
        // APP NOT INSTALLED
        isInstalled = false;
      }
    }
    return isInstalled;
  }
}
