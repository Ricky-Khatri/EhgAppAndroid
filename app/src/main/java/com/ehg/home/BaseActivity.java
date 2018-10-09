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

package com.ehg.home;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.exceptionhandler.ExceptionHandlerUtil;
import com.ehg.firebaseanalytics.FirebaseAnalyticsUtil;
import com.ehg.utilities.AppPermissionCheckerUtil;
import com.ehg.utilities.AppUtil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

/**
 * This is parent Activity class for all activities in application.
 */
public class BaseActivity extends AppCompatActivity {

  private BroadCastMessageInterface broadCastMessageInterface;

  /**
   * Interface to broadcast message to it's child activities.
   */
  public interface BroadCastMessageInterface {

    /**
     * Override method for child activity.
     */
    void onMessageReceived(String message, boolean flag);
  }

  /**
   * Instantiate BroadCastMessageInterface object.
   */
  public void setBroadCastMessageInterface(BroadCastMessageInterface broadCastInterface) {
    if (broadCastInterface != null) {
      broadCastMessageInterface = broadCastInterface;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {

      //Handle uncaught exception
      handleUncaughtException();

      //Set status bar color throughout the app.
      AppUtil.setStatusBarColor(this, R.color.white);

      /**
       * Instantiate Fire base analytics.
       */
      FirebaseAnalyticsUtil.getFirebaseInstance(this);

      /**
       * Get notification token and store locally.
       */
      FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this,
          new OnSuccessListener<InstanceIdResult>() {
            /**
             * Called when FCM token generated successfully.
             * @param instanceIdResult InstanceIdResult
             */
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
              String token = instanceIdResult.getToken();
              SharedPreferenceUtils.getInstance(BaseActivity.this)
                  .setValue(SharedPreferenceUtils.FCM_TOKEN, token);
            }
          });

    } catch (NullPointerException npe) {
      npe.printStackTrace();
    } catch (RuntimeException rte) {
      rte.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to update back arrow navigation image with layout direction.
   * @param appCompatImageView imageview object
   */
  public void setBackArrowRtl(AppCompatImageView appCompatImageView) {
    try {
      if (appCompatImageView != null) {
        if (SharedPreferenceUtils.getInstance(this).getStringValue(
            SharedPreferenceUtils.APP_LANGUAGE, "").equalsIgnoreCase("ar")) {
          appCompatImageView.setImageResource(R.drawable.ic_forward_arrow);
        } else {
          appCompatImageView.setImageResource(R.drawable.ic_back_arrow);
        }
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method handles uncaught exception. And allows user to report application bug.
   */
  public void handleUncaughtException() {
    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread thread, Throwable e) {

        ExceptionHandlerUtil exceptionHandlerUtil = new ExceptionHandlerUtil(BaseActivity.this);
        exceptionHandlerUtil.uncaughtException(thread, e);
      }
    });
  }

  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();

    try {
      if (SharedPreferenceUtils.getInstance(this).getStringValue(
          SharedPreferenceUtils.APP_LANGUAGE, "").equalsIgnoreCase("ar")) {
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
      } else {
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
      }
    } catch (NullPointerException npe) {
      npe.printStackTrace();
    } catch (RuntimeException rte) {
      rte.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method will be called on requestPermissionDialog callback.
   *
   * @param requestCode requestCode
   * @param permissions permission array
   * @param grantResults request grant array
   */
  @Override
  public void onRequestPermissionsResult(int requestCode,
      String[] permissions, int[] grantResults) {
    switch (requestCode) {
      case AppPermissionCheckerUtil.REQUEST_CODE: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          // permission was granted, yay! Do the
          // contacts-related task you need to do.
          if (broadCastMessageInterface != null) {
            broadCastMessageInterface.onMessageReceived(permissions.toString(), true);
          }
        } else {
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
          if (broadCastMessageInterface != null) {
            broadCastMessageInterface.onMessageReceived(permissions.toString(), false);
          }
        }
        return;
      }
      default:
        break;
    }
  }
}
