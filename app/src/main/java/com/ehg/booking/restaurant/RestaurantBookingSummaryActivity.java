/*
 *  Created by Emaar Hospitality Group on 15/10/18 3:19 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 15/10/18 3:19 PM
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

package com.ehg.booking.restaurant;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.home.BaseActivity;
import com.ehg.home.HomeActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.utilities.AppUtil;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class shows Restaurant booking summary.
 */
public class RestaurantBookingSummaryActivity extends BaseActivity implements OnClickListener,
    ApiResponseListener {

  private static final String CANCEL_BOOKING = "cancelBooking";

  private String confirmationNumber;
  private String restaurantId;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restaurant_booking_summary);

    initView();
  }

  /**
   * Called to init view components of this activity.
   */
  private void initView() {
    try {
      TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
      textViewHeaderTitle.setText(R.string.all_bookingsummary);
      Button buttonModifyBooking = findViewById(R.id.button_restaurantbookingsummary_modifybooking);
      buttonModifyBooking.setOnClickListener(this);
      TextView textViewCancelBooking = findViewById(
          R.id.textview_restaurantbookingsummary_cancelbooking);
      textViewCancelBooking.setOnClickListener(this);
      findViewById(R.id.imageview_header_back).setOnClickListener(this);

      String response = getIntent().getStringExtra("response");
      restaurantId = getIntent().getStringExtra("restaurantId");
      JSONObject jsonObject = new JSONObject(response);
      JSONObject dataObject = jsonObject.getJSONObject("data");

      if (dataObject != null) {
        JSONArray detail = dataObject.optJSONArray("detail");

        for (int index = 0; index < detail.length(); index++) {
          JSONObject detailObject = detail.optJSONObject(index);

          TextView textViewGuestName = findViewById(
              R.id.textview_restaurantbookingsummery_guestname);
          textViewGuestName.setText("Dear Mr. " + detailObject.getString("firstName") + " "
              + detailObject.getString("lastName"));
          TextView textViewYourTableReservation = findViewById(
              R.id.textview_restaurantbookingsummary_yourtablereservation);
          TextView textViewBookingConfirmLabel = findViewById(
              R.id.textview_restaurantbookingsummary_bookingconfirmationlabel);
          TextView textViewBookingConfirmationNumber = findViewById(
              R.id.textview_restaurantbookingsummary_bookingconfirmation);
          confirmationNumber = detailObject.getString("confirmationNumber");
          textViewBookingConfirmationNumber.setText(confirmationNumber);
          TextView textViewRestaurantLable = findViewById(
              R.id.textview_restaurantbookingsummary_restaurantlabel);
          TextView textViewRestaurantName = findViewById(
              R.id.textview_restaurantbookingsummary_restaurant);
          textViewRestaurantName.setText("Lounge at Address Downtown");//TODO: Make dynamic
          TextView textViewNameLabel = findViewById(
              R.id.textview_restaurantbookingsummary_namelabel);
          TextView textViewBookingName = findViewById(
              R.id.textview_restaurantbookingsummary_bookingname);
          textViewBookingName.setText("Mr. " + detailObject.getString("firstName") + " "
              + detailObject.getString("lastName"));
          TextView textViewTelephoneLabel = findViewById(
              R.id.textview_restaurantbookingsummary_telephonelabel);
          TextView textViewTelephoneNumber = findViewById(
              R.id.textview_restaurantbookingsummary_telephone);
          textViewTelephoneNumber.setText(detailObject.getString("phoneNumber"));
          TextView textViewEmailLabel = findViewById(
              R.id.textview_restaurantbookingsummary_emaillabel);
          TextView textViewEmail = findViewById(R.id.textview_restaurantbookingsummary_email);
          textViewEmail.setText(detailObject.getString("emailAddress"));
          TextView textViewDate = findViewById(R.id.textview_restaurantbookingsummary_date);
          textViewDate.setText("Date          :" + detailObject.getString("reservationDate"));
          TextView textViewTime = findViewById(R.id.textview_restaurantbookingsummary_time);
          textViewTime.setText("Time          :" + detailObject.getString("reservationTime"));
          TextView textViewGuestLabel = findViewById(
              R.id.textview_restaurantbookingsummary_guestlabel);
          TextView textViewGuestCount = findViewById(
              R.id.textview_restaurantbookingsummary_guestcount);
          textViewGuestCount.setText(detailObject.getInt("partySize"));
          TextView textViewRestaurantHighlightedName = findViewById(
              R.id.textview_restaurantbookingsummary_restaurantname);
          textViewRestaurantHighlightedName
              .setText("Lounge at Address Downtown");//TODO: Make dynamic
          TextView textViewRestaurantAddress = findViewById(
              R.id.textview_restaurantbookingsummary_restaurantaddress);
          textViewRestaurantAddress.setText(detailObject
              .getString("Sheikh Mohammad bin Rashid Blvd - Dubai - United Arab Emirates"));
          TextView textViewLearnMoreAboutRestaurant = findViewById(
              R.id.textview_restaurantbookingsummary_aboutrestaurant);
          TextView textViewDisclaimerLabel = findViewById(
              R.id.textview_restaurantbookingsummary_disclaimerlabel);
          TextView textViewDisclaimer = findViewById(
              R.id.textview_restaurantbookingsummary_disclaimer);
        }
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();
    setBackArrowRtl((AppCompatImageView) findViewById(R.id.imageview_header_back));
  }

  /**
   * Called to update back arrow rtl icons.
   *
   * @param appCompatImageView imageview object
   */
  @Override
  public void setBackArrowRtl(AppCompatImageView appCompatImageView) {
    super.setBackArrowRtl(appCompatImageView);
  }

  /**
   * OnKeyDown callback will be called when phone back key pressed.
   *
   * @param keyCode keycode
   * @param event event
   * @return return boolean value
   */
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      Intent intent = new Intent(this, HomeActivity.class);
      intent.putExtra("tab", "2");
      //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
      AppUtil.startActivityWithAnimation(this, intent, true);
    }
    return super.onKeyDown(keyCode, event);
  }

  /**
   * Called on view clicked.
   *
   * @param view view
   */
  @Override
  public void onClick(View view) {
    Intent intent = null;
    switch (view.getId()) {

      case R.id.imageview_header_back:
        intent = new Intent(this, HomeActivity.class);
        intent.putExtra("tab", "2");
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        AppUtil.startActivityWithAnimation(this, intent, true);
        break;

      case R.id.button_restaurantbookingsummary_modifybooking:
        intent = new Intent(this, RestaurantBookingSlotActivity.class);
        intent.putExtra("restaurantId", restaurantId);
        intent.putExtra("key", "modifyReservation");
        SharedPreferenceUtils.getInstance(this)
            .setValue(SharedPreferenceUtils.RESTAURANT_CONFIRMATION_NUMBER, confirmationNumber);
        AppUtil.startActivityWithAnimation(this, intent, true);
        break;

      case R.id.textview_restaurantbookingsummary_cancelbooking:
        showCancelReservationDialog(this);
        break;

      default:
        break;
    }
  }

  /**
   * This method will use to show Cancel Reservation dialog and allows user to cancel reservation.
   *
   * @param appCompatActivity calling class object
   */
  private void showCancelReservationDialog(final AppCompatActivity appCompatActivity) {

    try {
      // We need to get the instance of the LayoutInflater
      final Dialog dialog = new Dialog(appCompatActivity);
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      dialog.setCancelable(true);
      dialog.setContentView(R.layout.view_alertdialog);
      dialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogAnimation;

      TextView textViewTitle = dialog.findViewById(R.id.textview_alertdialog_title);
      TextView textViewAlertMessage = dialog.findViewById(R.id.textview_alertdialog_message);
      textViewTitle.setText(getResources().getString(R.string.all_confirmtitle));

      textViewAlertMessage.setText(R.string.all_cancelreservationmessage);

      Button buttonCancel = dialog.findViewById(R.id.button_alertdialog_cancel);
      buttonCancel.setText(getResources().getString(R.string.all_no));
      buttonCancel.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          dialog.dismiss();
        }
      });

      Button buttonOk = dialog.findViewById(R.id.button_alertdialog_ok);
      buttonOk.setText(getResources().getString(R.string.all_yes));

      buttonOk.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {

          dialog.dismiss();
          cancelBooking();
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

  /**
   * Called to cancel restaurant booking.
   */
  private void cancelBooking() {
    if (AppUtil.isNetworkAvailable(this)) {
      new HttpClientRequest().setApiResponseListner(this);

      String url = "";

      String loyaltyMemberId = SharedPreferenceUtils.getInstance(this).getStringValue(
          SharedPreferenceUtils.LOYALTY_MEMBER_ID, "");
      if (!TextUtils.isEmpty(loyaltyMemberId)) {

        url = WebServiceUtil.getUrl(WebServiceUtil.METHOD_CANCEL_RESERVATION)
            + confirmationNumber + "/" + restaurantId + "?loyaltyMemberId=" + loyaltyMemberId
            + "&deviceId=" + AppUtil.getDeviceId(this);

      } else {

        url = WebServiceUtil.getUrl(WebServiceUtil.METHOD_CANCEL_RESERVATION)
            + confirmationNumber + "/" + restaurantId + "?deviceId=" + AppUtil.getDeviceId(this);
      }

      new HttpClientRequest(this, url,
          new RequestParams(), WebServiceUtil.CONTENT_TYPE,
          CANCEL_BOOKING, true).httpPutRequest();
    } else {
      AppUtil.showAlertDialog(this,
          getResources().getString(R.string.all_please_check_network_settings),
          false, "", true, null);
    }
  }

  /**
   * Called when response received from api call.
   *
   * @param responseVal response
   * @param requestMethod request method name
   */
  @Override
  public void onSuccessResponse(String responseVal, String requestMethod) {

    try {
      if (requestMethod.equalsIgnoreCase(CANCEL_BOOKING)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("status")) {

        SharedPreferenceUtils.getInstance(this)
            .setValue(SharedPreferenceUtils.RESTAURANT_CONFIRMATION_NUMBER, "");

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("tab", "2");
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        AppUtil.showAlertDialog(this,
            new JSONObject(responseVal).getString("message"), true,
            getResources().getString(R.string.dialog_alerttitle), false, intent);

      } else if (responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && !new JSONObject(responseVal).getBoolean("status")) {

        JSONObject dataObject = new JSONObject(responseVal).getJSONObject("data");

        if (dataObject != null) {
          JSONArray detailArray = dataObject.optJSONArray("detail");
          if (detailArray != null && detailArray.length() > 0) {
            JSONObject validationError = detailArray.optJSONObject(0)
                .optJSONArray("validationErrors").optJSONObject(0);

            AppUtil.showAlertDialog(this,
                validationError.getString("ErrorMessage"), false,
                getResources().getString(R.string.dialog_errortitle), true, null);
          }
        } else {
          AppUtil.showAlertDialog(this,
              new JSONObject(responseVal).getString("message"), false,
              getResources().getString(R.string.dialog_errortitle), true, null);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called on failure api response.
   *
   * @param errorMessage error string
   */
  @Override
  public void onFailureResponse(String errorMessage) {
    AppUtil.showAlertDialog(this, errorMessage, false,
        getResources().getString(R.string.dialog_errortitle), true, null);
  }
}
