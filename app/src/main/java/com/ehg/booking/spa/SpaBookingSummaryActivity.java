/*
 *  Created by Emaar Hospitality Group on 16/10/18 4:00 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 16/10/18 4:00 PM
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

package com.ehg.booking.spa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.home.HomeActivity;
import com.ehg.utilities.AppUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class shows Spa booking summary.
 */
public class SpaBookingSummaryActivity extends BaseActivity implements OnClickListener {

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_spabookingsummary);

    initView();
  }

  /**
   * Called to init view components of this activity.
   */
  private void initView() {

    try {
      TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
      textViewHeaderTitle.setText(getResources().getString(R.string.all_bookingsummary));
      findViewById(R.id.imageview_header_back).setOnClickListener(this);

      if (getIntent() != null && getIntent().getStringExtra("response") != null) {
        String response = getIntent().getStringExtra("response");
        JSONObject jsonObject = new JSONObject(response);
        JSONObject dataObject = jsonObject.getJSONObject("data");

        if (dataObject != null) {
          JSONArray detail = dataObject.optJSONArray("detail");

          for (int index = 0; index < detail.length(); index++) {
            JSONObject detailObject = detail.optJSONObject(index);

            TextView textViewGuestName = findViewById(
                R.id.textview_spabookingsummary_guestname);
            textViewGuestName.setText("Dear Mr. " + detailObject.getString("firstName") + " "
                + detailObject.getString("lastName"));
            TextView textViewSpaInquiry = findViewById(
                R.id.textview_spabookingsummary_inquiry);
            TextView textViewspaLable = findViewById(
                R.id.textview_spabookingsummary_spalabel);
            TextView textViewspaName = findViewById(
                R.id.textview_spabookingsummary_spa);
            textViewspaName.setText(detailObject.getString("spaName"));
            TextView textViewspatreatmentLabel = findViewById(
                R.id.textview_spabookingsummary_spatreatmentlabel);
            TextView textViewspaTreatment = findViewById(
                R.id.textview_spabookingsummary_spatreatment);
            textViewspaTreatment.setText(detailObject.getString("treatmentName"));
            TextView textViewNameLabel = findViewById(
                R.id.textview_spabookingsummary_namelabel);
            TextView textViewBookingName = findViewById(
                R.id.textview_spabookingsummary_bookingname);
            textViewBookingName.setText("Mr. " + detailObject.getString("firstName") + " "
                + detailObject.getString("lastName"));
            TextView textViewTelephoneLabel = findViewById(
                R.id.textview_spabookingsummary_telephonelabel);
            TextView textViewTelephoneNumber = findViewById(
                R.id.textview_spabookingsummary_telephone);
            textViewTelephoneNumber.setText(detailObject.getString("mobileNumber"));
            TextView textViewEmailLabel = findViewById(
                R.id.textview_spabookingsummary_emaillabel);
            TextView textViewEmail = findViewById(R.id.textview_spabookingsummary_email);
            textViewEmail.setText(detailObject.getString("emailAddress"));
            TextView textViewDate = findViewById(R.id.textview_spabookingsummary_date);
            textViewDate.setText("Date          :" + detailObject.getString("spaDate"));
            TextView textViewTime = findViewById(R.id.textview_spabookingsummary_time);
            textViewTime.setText("Time          :" + detailObject.getString("spaTime"));
            TextView textViewGuestLabel = findViewById(
                R.id.textview_spabookingsummary_guestlabel);
            TextView textViewGuestCount = findViewById(
                R.id.textview_spabookingsummary_guestcount);
            textViewGuestCount.setText(detailObject.getInt("totalPeople"));
            TextView textViewspaHighlightedName = findViewById(
                R.id.textview_spabookingsummary_spaname);
            textViewspaHighlightedName
                .setText("Lounge at Address Downtown");//TODO: Make dynamic
            TextView textViewspaAddress = findViewById(
                R.id.textview_spabookingsummary_spaaddress);
            textViewspaAddress.setText(detailObject
                .getString("Sheikh Mohammad bin Rashid Blvd - Dubai - United Arab Emirates"));
            TextView textViewLearnMoreAboutspa = findViewById(
                R.id.textview_spabookingsummary_aboutspa);
            TextView textViewDisclaimerLabel = findViewById(
                R.id.textview_spabookingsummary_disclaimerlabel);
            TextView textViewDisclaimer = findViewById(
                R.id.textview_spabookingsummary_disclaimer);
          }
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
      //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        AppUtil.startActivityWithAnimation(this, intent, true);
        break;

      default:
        break;
    }
  }
}
