/*
 *  Created by Emaar Hospitality Group on 8/8/18 6:23 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 8/8/18 6:22 PM
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

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.webview.WebviewActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains common utility methods required for the app.
 */
public class AppUtil {

  public static final String PRIVACY_POLICY_URL = "https://www.emaar.com/en/privacy-policy/index.aspx";
  public static final String TERMS_AND_CONDITIONS_URL = "https://www.emaar.com/en/terms-and-conditions/index.aspx";
  public static final String SUPPORT_URL = "https://www.emaar.com/en/faq/";

  private static final String TAG = AppUtil.class.getName();

  //private static ProgressDialog progressDialog;

  private static Dialog dialogLoadingIndicator;

  public static int numberPickerDefaultSelectedNumber = 2;

  /**
   * Method returns device screen width in pixel.
   */
  public static int getDeviceWidth(AppCompatActivity context) {
    if (context != null) {
      DisplayMetrics displaymetrics = new DisplayMetrics();
      context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
      return displaymetrics.widthPixels;
    } else {
      return 0;
    }
  }

  /**
   * Called to get card type.
   *
   * @param cardNumber card number
   * @return cardType
   */
  public static String getCardType(String cardNumber) {
    String cardType = "";
    cardNumber = cardNumber.replace("-","");
    if (cardNumber.matches("^4[0-9]{6,}$")) {
      cardType = "VI";//VISA
    } else if (cardNumber.matches("^5[1-5][0-9]{5,}$")) {
      cardType = "MC";//MASTER CARD
    } else if (cardNumber.matches("^3[47][0-9]{5,}$")) {
      cardType = "AE"; //AMERICAN EXPRESS
    } else if (cardNumber.matches("^3(?:0[0-5]|[68][0-9])[0-9]{4,}$")) {
      cardType = "DC";//DINNERS CLUB
    } else if (cardNumber.matches("^6(?:011|5[0-9]{2})[0-9]{3,}$")) {
      cardType = "DI";//DISCOVER
    } else if (cardNumber.matches("^(?:2131|1800|35[0-9]{3})[0-9]{3,}$")) {
      cardType = "JCB";//JCB
    }
    return cardType;
  }

  /**
   * Called to animate recycler view items.
   *
   * @param context activity context
   * @param recyclerView recyclerview object
   * @param resourceId animation resource id
   */
  public static void animateRecyclerView(Context context, RecyclerView recyclerView,
      int resourceId) {
    try {
      LayoutAnimationController animation = null;
      //Check if current language is arabic then change RTL animation
      if (SharedPreferenceUtils.getInstance(context).getStringValue(
          SharedPreferenceUtils.APP_LANGUAGE, "").equalsIgnoreCase("ar")) {
        if (resourceId == R.anim.layout_animation_from_right) {
          resourceId = R.anim.layout_animation_from_left;
        }
        animation = AnimationUtils.loadLayoutAnimation(context, resourceId);
      } else {
        animation = AnimationUtils.loadLayoutAnimation(context, resourceId);
      }
      recyclerView.setLayoutAnimation(animation);
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to get decimal formatted rate to 2 decimal digits.
   */
  public static String getFormatedPriceRate(String priceRate) {
    DecimalFormat decimalFormat = new DecimalFormat("#.00");
    return priceRate;
  }

  /**
   * Called to get string from html string content.
   *
   * @param html html string
   * @return returns spanned content
   */
  public static Spanned fromHtml(String html) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
    } else {
      return Html.fromHtml(html);
    }
  }

  /**
   * Called to show progress indicator.
   *
   * @param appCompatActivity activity context
   */
  public static void showLoadingIndicator(AppCompatActivity appCompatActivity) {

    try {
      // We need to get the instance of the LayoutInflater
      dialogLoadingIndicator = new Dialog(appCompatActivity);
      dialogLoadingIndicator.requestWindowFeature(Window.FEATURE_NO_TITLE);
      dialogLoadingIndicator.setContentView(R.layout.view_loadingindicator);
      dialogLoadingIndicator.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

      ProgressBar progressBar = dialogLoadingIndicator
          .findViewById(R.id.progressbar_loadingindicator);
      progressBar.getIndeterminateDrawable().setColorFilter(
          appCompatActivity.getResources().getColor(R.color.white),
          android.graphics.PorterDuff.Mode.MULTIPLY);

      dialogLoadingIndicator.show();

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (RuntimeException rte) {
      rte.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    /*if (appCompatActivity != null) {
      progressDialog = new ProgressDialog(appCompatActivity, R.style.AppCompatAlertDialogStyle);
      progressDialog.setMessage(appCompatActivity.getResources().getString(R.string.all_loading));
      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      progressDialog.show();
    }*/
  }

  /**
   * Called to dismiss progress indicator.
   *
   * @param appCompatActivity activity context
   */
  public static void dismissLoadingIndicator(AppCompatActivity appCompatActivity) {
    /*if (appCompatActivity != null && progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }*/
    if (appCompatActivity != null && dialogLoadingIndicator != null && dialogLoadingIndicator
        .isShowing()) {
      dialogLoadingIndicator.dismiss();
    }
  }

  /**
   * This method shows click animation on the view passed.
   */
  public static void clickAnimation(View view) {
    if (view != null) {
      Animation animation = AnimationUtils.loadAnimation(view.getContext(),
          R.anim.click_animation);

      BounceInterpolator bounceInterpolator = new BounceInterpolator(0.2, 20);
      animation.setInterpolator(bounceInterpolator);

      view.startAnimation(animation);
    }
  }

  /**
   * This class allows to create Bounce Interpolator animation.
   */
  public static class BounceInterpolator implements android.view.animation.Interpolator {

    private double amplitude = 1;
    private double frequency = 10;

    /**
     * BounceInterpolator constructor.
     *
     * @param amplitude amplitude
     * @param frequency frequency
     */
    BounceInterpolator(double amplitude, double frequency) {
      this.amplitude = amplitude;
      this.frequency = frequency;
    }

    /**
     * Returns interpolation value.
     *
     * @param time float time para
     * @return returns float value
     */
    public float getInterpolation(float time) {
      return (float) (-1 * Math.pow(Math.E, -time / amplitude)
          * Math.cos(frequency * time) + 1);
    }
  }
  //*********************************************************************

  /**
   * Checks if passed email string is valid or not.
   */
  public static boolean isValidEmail(String email) {
    return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
  }

  /**
   * Checks if passed phone number is  valid or not.
   */
  public static boolean isValidMobile(String phone) {
    return /*android.util.Patterns.PHONE.matcher(phone).matches()*/
        /*phone.length() > 0 && phone.length() == 10*/
        PhoneNumberUtils.isGlobalPhoneNumber(phone) && phone.length() >= 5;
  }

  /**
   * Checks if password is valid or not.
   */
  public static boolean isPasswordValid(String password) {
    Pattern pattern;
    Matcher matcher;
    final String passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
    pattern = Pattern.compile(passwordPattern);
    matcher = pattern.matcher(password);
    return password.length() > 8 && matcher.matches();
  }

  /**
   * Method returns device screen height in pixel.
   */
  public static int getDeviceHeight(AppCompatActivity context) {
    if (context != null) {
      DisplayMetrics displaymetrics = new DisplayMetrics();
      context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
      return displaymetrics.heightPixels;
    } else {
      return 0;
    }
  }

  /**
   * Method show toast message.
   */
  public static void showToast(Context context, String message) {
    if (context != null) {
      Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER, 0, 0);
      toast.show();
    }
  }

  /**
   * Method starts another activity with slide bottom to up animation.
   *
   * @param appCompatActivity context
   * @param intent intent value
   */
  public static void startActivityWithAnimation(AppCompatActivity appCompatActivity,
      Intent intent, boolean finishCurrentActivity) {

    if (appCompatActivity != null && intent != null) {

      appCompatActivity.startActivity(intent);

      //Check if current language is arabic then change RTL animation
      if (SharedPreferenceUtils.getInstance(appCompatActivity).getStringValue(
          SharedPreferenceUtils.APP_LANGUAGE, "").equalsIgnoreCase("ar")) {
        appCompatActivity.overridePendingTransition(R.anim.slide_in_right_animation,
            R.anim.slide_out_left_animation);
      } else {
        appCompatActivity.overridePendingTransition(R.anim.slide_in_left_animation,
            R.anim.slide_out_right_animation);
      }

      if (finishCurrentActivity) {
        appCompatActivity.finish();
      }
    }
  }

  /**
   * Finishes activity with animation.
   *
   * @param appCompatActivity activity context.
   */
  public static void finishActivityWithAnimation(AppCompatActivity appCompatActivity) {
    if (appCompatActivity != null) {

      appCompatActivity.finish();

      //Check if current language is arabic then change RTL animation
      if (SharedPreferenceUtils.getInstance(appCompatActivity).getStringValue(
          SharedPreferenceUtils.APP_LANGUAGE, "").equalsIgnoreCase("ar")) {
        appCompatActivity.overridePendingTransition(R.anim.slide_in_left_animation,
            R.anim.slide_out_right_animation);
      } else {
        appCompatActivity.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
      }
    }
  }

  /**
   * Check network availability.
   */
  public static boolean isNetworkAvailable(Context context) {
    if (context != null) {
      ConnectivityManager connectivityManager
          = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
      return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    } else {
      return false;
    }
  }

  /**
   * Method gets device id.
   *
   * @param context context
   * @return returns string value
   */
  public static String getDeviceId(Context context) {
    return Settings.Secure.getString(context.getContentResolver(),
        Settings.Secure.ANDROID_ID);
  }

  /**
   * Method returns app version name.
   *
   * @param context context
   * @return returns string value
   */
  public static String getVersionName(Context context) {

    PackageInfo packageInfo = null;
    try {
      packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    } catch (NullPointerException n) {
      n.printStackTrace();
    }
    assert packageInfo != null;
    return packageInfo.versionName;
  }

  /**
   * Method for selecting drawable for bottom tabs.
   *
   * @param context context
   * @param normal int type
   * @param selected selection type
   */
  public static Drawable setDrawableSelector(Context context, int normal, int selected) {

    Drawable stateNormal = ContextCompat.getDrawable(context, normal);

    Drawable statePressed = ContextCompat.getDrawable(context, selected);

    assert statePressed != null;
    Bitmap stateNormalBitmap = ((BitmapDrawable) statePressed).getBitmap();

    // Setting alpha directly just didn't work, so we draw a new bitmap!
    Bitmap disabledBitmap = Bitmap.createBitmap(
        statePressed.getIntrinsicWidth(),
        statePressed.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(disabledBitmap);

    Paint paint = new Paint();
    paint.setAlpha(126);
    canvas.drawBitmap(stateNormalBitmap, 0, 0, paint);

    BitmapDrawable stateNormalDrawable = new BitmapDrawable(context.getResources(), disabledBitmap);

    StateListDrawable drawable = new StateListDrawable();

    drawable.addState(new int[]{android.R.attr.state_selected},
        statePressed);
    drawable.addState(new int[]{android.R.attr.state_enabled},
        stateNormalDrawable);

    return drawable;
  }

  /**
   * Hides keyboard.
   *
   * @param context context of requested activity.
   * @param view view passed from ui.
   */
  public static void hideKeyboard(Context context, View view) {
    if (context != null && view != null) {
      InputMethodManager imm = (InputMethodManager) context
          .getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  /**
   * This method will use to show alert dialog.
   *
   * @param appCompatActivity calling class object
   * @param alertMessage message which will show as an alert
   * @param isRedirect if it is true than we will navigate to other activity, or if false than we
   *        will stay on same activity and perform required action.
   * @param alertTitle message will show on a dialog title
   * @param isCancelable Whether the dialog should be isCancelable when touched outside the window
   */
  public static void showAlertDialog(final AppCompatActivity appCompatActivity,
      String alertMessage, final boolean isRedirect, String alertTitle, boolean isCancelable,
      final Intent intent) {

    if (appCompatActivity != null) {
      try {
        // We need to get the instance of the LayoutInflater
        final Dialog dialog = new Dialog(appCompatActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(isCancelable);
        dialog.setContentView(R.layout.view_alertdialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogAnimation;

        Button buttonCancel = dialog.findViewById(R.id.button_alertdialog_cancel);
        buttonCancel.setVisibility(View.GONE);
        TextView textViewTitle = dialog.findViewById(R.id.textview_alertdialog_title);
        TextView textViewAlertMessage = dialog.findViewById(R.id.textview_alertdialog_message);

        if (TextUtils.isEmpty(alertTitle)) {
          dialog.findViewById(R.id.liearlayout_alertdialog_title).setVisibility(View.GONE);
        } else {
          dialog.findViewById(R.id.liearlayout_alertdialog_title).setVisibility(View.VISIBLE);
          textViewTitle.setText(alertTitle);
        }

        textViewAlertMessage.setText(alertMessage);

        Button buttonOk = dialog.findViewById(R.id.button_alertdialog_ok);
        buttonOk.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
            if (isRedirect) {

              if (intent != null) {
                startActivityWithAnimation(appCompatActivity, intent, isRedirect);
              } else {
                finishActivityWithAnimation(appCompatActivity);
              }
            }
            dialog.dismiss();
          }
        });

        dialog.show();

      } catch (NullPointerException n) {
        n.printStackTrace();
      } catch (RuntimeException rte) {
        rte.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * This method will show location settings request dialog. To turn on gps settings to enable usage
   * of location in app.
   *
   * @param context context of activity.
   */
  public static boolean displayLocationSettingsRequest(final AppCompatActivity context) {

    boolean isSuccess = false;

    if (context != null) {

      LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
      boolean isGpsEnabled = false;
      boolean isNetworkEnabled = false;

      try {
        isGpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      try {
        isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      // If gps and network services are not enabled then show location request dialog.

      if (!isGpsEnabled && !isNetworkEnabled) {

        isSuccess = false;

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
            .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
            .checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
          @Override
          public void onResult(LocationSettingsResult result) {
            final Status status = result.getStatus();
            switch (status.getStatusCode()) {
              case LocationSettingsStatusCodes.SUCCESS:
                Log.i(TAG, "All location settings are satisfied.");
                break;
              case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                Log.i(TAG, "Location settings are not satisfied. "
                    + "Show the user a dialog to upgrade location settings ");

                try {
                  // Show the dialog by calling startResolutionForResult(), and check the result
                  // in onActivityResult().
                  status.startResolutionForResult(context, 1);
                } catch (IntentSender.SendIntentException e) {
                  Log.i(TAG, "PendingIntent unable to execute request.");
                }
                break;
              case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                Log.i(TAG, "Location settings are inadequate, "
                    + "and cannot be fixed here. Dialog not created.");
                break;

              default:
                break;
            }
          }
        });
      } else {
        isSuccess = true;
      }
    }

    return isSuccess;
  }

  /**
   * Called to load a web page (url) in webview.
   *
   * @param context activity context
   * @param title webview activity title
   * @param url url to load in webview
   * @param isRedirect has to finish current activity or not
   */
  public static void loadWebView(AppCompatActivity context, String title, String url,
      boolean isRedirect) {

    if (context != null) {
      if (isNetworkAvailable(context)) {
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.putExtra("title", title);
        if (!url.equalsIgnoreCase("")) {
          intent.putExtra("url", url);
        }
        AppUtil.startActivityWithAnimation(context, intent, isRedirect);
      } else {
        showAlertDialog(context,
            context.getResources().getString(R.string.all_please_check_network_settings),
            false, "", true, null);
      }
    }
  }

  /**
   * Sets status bar color of app.
   *
   * @param context activity context
   * @param color color int resource to be set
   */
  public static void setStatusBarColor(AppCompatActivity context, int color) {

    try {
      if (context != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        context.getWindow().setStatusBarColor(
            darkenColor(
                ContextCompat.getColor(context, color)));
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns color darken.
   *
   * @param color int color
   * @return returns darken int color
   */
  private static int darkenColor(int color) {
    float[] hsv = new float[3];
    Color.colorToHSV(color, hsv);
    hsv[2] *= 0.8f;
    return Color.HSVToColor(hsv);
  }
}
