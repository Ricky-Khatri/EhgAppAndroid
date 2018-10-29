/*
 *  Created by Emaar Hospitality Group on 22/10/18 6:08 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 22/10/18 6:08 PM
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

package com.ehg.booking.hotel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo.Detail;
import com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo.FetchRoomAvailabilityRequestPojo;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.AverageRate;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.FetchAvailabilityResponsePojo;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Customer;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.GuaranteesAccepted;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.HoldRoomReservationRequestPojo;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.PaymentCard;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Profile;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Rate;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.RatePlan;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.ResGlobalInfo;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.ResGuest;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.ReservationRequestParam;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.RoomRate;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.RoomStay;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Telephone;
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.TimeSpan;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Called when user want to payment against booking room.
 */
public class RoomPaymentActivity extends BaseActivity implements OnClickListener,
    ApiResponseListener {

  private static final String ROOM_RESERVATION = "RoomReservation";
  private TextView textViewHeaderTitle;
  private AppCompatImageView headerBackButton;
  private Context context;

  private EditText editTextNameOnCard;
  private EditText editTextCardNumber;
  private EditText editTextExpiry;

  private HoldRoomReservationRequestPojo holdRoomReservationRequestPojo;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      setContentView(R.layout.activity_roompayment);

      context = this;
      initView();

    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init view components of this activity.
   */
  private void initView() {

    editTextNameOnCard = findViewById(R.id.edittext_roompayment_nameoncard);
    editTextNameOnCard = findViewById(R.id.edittext_roompayment_cardnumber);
    editTextNameOnCard = findViewById(R.id.edittextroompayment_expirydate);

    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    headerBackButton = findViewById(R.id.imageview_header_back);

    Intent intent = getIntent();
    if (intent != null && intent.getStringExtra("holdReservationRequest") != null) {
      Type type = new TypeToken<AverageRate>() {
      }.getType();
      holdRoomReservationRequestPojo = new Gson()
          .fromJson(intent.getStringExtra("holdReservationRequest"), type);
    }
    //Set OnClickListener
    TextView textViewBook = findViewById(R.id.textview_roompayment_book);
    textViewBook.setOnClickListener(this);
    headerBackButton.setOnClickListener(this);
  }


  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();
    // resetErrors();
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
   * Called when view item clicked on this activity.
   *
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      case R.id.textview_roompayment_book:
        validateFormFields();
        break;

      default:
        break;
    }
  }

  /**
   * Method validates form fields.
   */
  private void validateFormFields() {

    editTextNameOnCard.setError(null);
    editTextCardNumber.setError(null);
    editTextExpiry.setError(null);

    boolean cancel = false;
    View focusView = null;

    String nameOnCard = editTextNameOnCard.getText().toString();
    String cardNumber = editTextCardNumber.getText().toString().trim();
    String expiryDate = editTextExpiry.getText().toString().trim();

    if (TextUtils.isEmpty(nameOnCard)) {

      editTextNameOnCard.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextNameOnCard;
      cancel = true;

    } else if (TextUtils.isEmpty(cardNumber)) {

      editTextCardNumber.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextCardNumber;
      cancel = true;

    } else if (TextUtils.isEmpty(expiryDate)) {

      editTextExpiry.setError(getResources().getString(R.string.all_fieldrequired));
      focusView = editTextExpiry;
      cancel = true;

    }

    if (cancel) {
      Objects.requireNonNull(focusView).requestFocus();
    } else {
      roomReservation(nameOnCard, cardNumber, expiryDate);
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called for room reservation.
   */
  private void roomReservation(String nameOnCard, String cardNumber, String expiryDate) {
    try {
      if (AppUtil.isNetworkAvailable(context)) {
        new HttpClientRequest().setApiResponseListner(this);

        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber(cardNumber);
        paymentCard.setCardHolderName(nameOnCard);
        paymentCard.setExpireDate(expiryDate);
        GuaranteesAccepted guaranteesAccepted = new GuaranteesAccepted();
        guaranteesAccepted.setPaymentCard(paymentCard);
        List<GuaranteesAccepted> guaranteesAcceptedList = new ArrayList<>();
        guaranteesAcceptedList.add(guaranteesAccepted);
        List<com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Detail> detailList
            = holdRoomReservationRequestPojo.getDetails();

        if (detailList != null && detailList.size() > 0) {
          com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Detail detail
              = detailList.get(0);
          List<ReservationRequestParam> reservationRequestParamList = detail
              .getReservationRequestParams();

          if (reservationRequestParamList != null && reservationRequestParamList.size() > 0) {
            ReservationRequestParam reservationRequestParam = reservationRequestParamList.get(0);
            ResGlobalInfo resGlobalInfo = reservationRequestParam.getResGlobalInfo();
            resGlobalInfo.setGuaranteesAccepted(guaranteesAcceptedList);
            reservationRequestParam.setResGlobalInfo(resGlobalInfo);
            detail.setReservationRequestParams(reservationRequestParamList);
            detailList.set(0, detail);
            holdRoomReservationRequestPojo.setDetails(detailList);
          }
        }

        Gson gson = new Gson();
        String requestString = gson
            .toJson(holdRoomReservationRequestPojo, HoldRoomReservationRequestPojo.class);

        StringEntity entity = null;
        try {
          entity = new StringEntity(requestString);
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        }

        new HttpClientRequest(context,
            WebServiceUtil.getUrl(WebServiceUtil.METHOD_RESERVATION_MULTI_ROOM),
            entity, WebServiceUtil.CONTENT_TYPE,
            ROOM_RESERVATION, true).httpPostRequest();
      } else {
        AppUtil.showAlertDialog((AppCompatActivity) context,
            context.getResources().getString(R.string.all_please_check_network_settings),
            false, "", true, null);
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (IndexOutOfBoundsException iob) {
      iob.printStackTrace();
    } catch (NumberFormatException iob) {
      iob.printStackTrace();
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
      if (requestMethod.equalsIgnoreCase(ROOM_RESERVATION)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("Status")) {

      } else if (requestMethod.equalsIgnoreCase(ROOM_RESERVATION)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && !new JSONObject(responseVal).getBoolean("Status")) {

        JSONObject dataObject = new JSONObject(responseVal).getJSONObject("Data");

        if (dataObject != null) {
          JSONArray detailArray = dataObject.optJSONArray("Detail");
          if (detailArray != null && detailArray.length() > 0) {
            JSONObject validationError = detailArray.optJSONObject(0)
                .optJSONArray("ValidationErrors").optJSONObject(0);

            AppUtil.showAlertDialog((AppCompatActivity) context,
                validationError.getString("ErrorMessage"), false,
                getResources().getString(R.string.dialog_errortitle), true, null);
          }
        }
      } else {
        AppUtil.showAlertDialog(this,
            new JSONObject(responseVal).getString("Message"), false,
            getResources().getString(R.string.dialog_errortitle), true, null);
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
