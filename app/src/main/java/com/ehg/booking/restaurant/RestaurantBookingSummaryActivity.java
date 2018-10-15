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

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class shows Restaurant booking summary.
 */
public class RestaurantBookingSummaryActivity extends BaseActivity implements OnClickListener {

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
      String response = getIntent().getStringExtra("response");
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
          textViewBookingConfirmationNumber.setText(detailObject.getString("confirmationNumber"));
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
          Button buttonModifyBooking = findViewById(
              R.id.button_restaurantbookingsummary_modifybooking);
          TextView textViewCancelBooking = findViewById(
              R.id.textview_restaurantbookingsummary_cancelbooking);
        }
      }

      findViewById(R.id.imageview_header_back).setOnClickListener(this);
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
      AppUtil.finishActivityWithAnimation(this);
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

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      default:
        break;
    }
  }

}
