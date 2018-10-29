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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.UniqueId;
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

  private String uniqueId;
  private String cardNumber;

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
    editTextCardNumber = findViewById(R.id.edittext_roompayment_cardnumber);
    editTextExpiry = findViewById(R.id.edittextroompayment_expirydate);

    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    if (getIntent() != null && getIntent().getStringExtra("title") != null) {
      textViewHeaderTitle.setText(getIntent().getStringExtra("title"));
    }
    headerBackButton = findViewById(R.id.imageview_header_back);

    Intent intent = getIntent();
    if (intent != null && intent.getStringExtra("holdReservationRequest") != null) {
      Type type = new TypeToken<HoldRoomReservationRequestPojo>() {
      }.getType();
      holdRoomReservationRequestPojo = new Gson()
          .fromJson(intent.getStringExtra("holdReservationRequest"), type);
    }

    if (intent != null && intent.getStringExtra("uniqueId") != null) {
      uniqueId = intent.getStringExtra("uniqueId");
    }

    TextView textViewName = findViewById(R.id.textview_roompayment_guestname);
    TextView textViewTelepohone = findViewById(R.id.textview_roompayment_guesttelephone);
    TextView textViewEmail = findViewById(R.id.textView_roompayment_guestemail);
    TextView textViewAddress = findViewById(R.id.textview_roompayment_guestaddress);
    TextView textViewChekingDate = findViewById(R.id.textview_roompayment_checkin);
    TextView textViewChekoutDate = findViewById(R.id.textview_roompayment_checkout);
    TextView textViewNumberOfPeople = findViewById(R.id.textview_roompayment_numberofpepople);
    TextView textViewRoomType = findViewById(R.id.textview_roompayment_roomtype);
    TextView textViewAddOn = findViewById(R.id.textview_roompayment_addons);

    List<com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo.Detail>
        detailList = holdRoomReservationRequestPojo.getDetails();

    if (detailList != null && detailList.size() > 0
        && detailList.get(0).getReservationRequestParams() != null
        && detailList.get(0).getReservationRequestParams().size() > 0) {

      ReservationRequestParam reservationRequestParam = detailList.get(0)
          .getReservationRequestParams().get(0);

      List<ResGuest> resGuestList = reservationRequestParam.getResGuests();
      if (resGuestList != null && resGuestList.size() > 0) {
        ResGuest resGuest = resGuestList.get(0);

        //Update UniqueId in pojo
        UniqueId uniqueIdObject = new UniqueId();
        uniqueIdObject.setId(uniqueId);
        uniqueIdObject.setIdContext("Reservation");
        resGuest.setUniqueId(uniqueIdObject);
        resGuestList.set(0, resGuest);
        reservationRequestParam.setResGuests(resGuestList);
        List<ReservationRequestParam> reservationRequestParamList = detailList.get(0)
            .getReservationRequestParams();
        reservationRequestParamList.set(0, reservationRequestParam);
        detailList.get(0).setReservationRequestParams(reservationRequestParamList);
        holdRoomReservationRequestPojo.setDetails(detailList);
        ///////////////////////////

        Profile profile = resGuest.getProfile();
        if (profile != null && profile.getCustomer() != null) {
          Customer customer = profile.getCustomer();
          textViewName.setText(customer.getGivenName());
          textViewTelepohone.setText(customer.getTelephone() != null
              && customer.getTelephone().size() > 0
              ? customer.getTelephone().get(0).getPhoneNumber() + "" : "");
          textViewEmail.setText(customer.getEmail());
          textViewAddress.setText(customer.getAddress() != null
              && customer.getAddress().size() > 0 ? customer.getAddress().get(0).getAddressLine1()
              + " " + customer.getAddress().get(0).getCityName() : "");

          FetchRoomAvailabilityRequestPojo fetchRoomAvailabilityRequestPojo
              = JsonParserUtil.getInstance(this).getFetchAvailabilityRequestPojo();
          List<Detail> fetchAvailabilityDetailList = fetchRoomAvailabilityRequestPojo.getDetails();
          if (fetchAvailabilityDetailList != null && fetchAvailabilityDetailList.size() > 0) {
            textViewChekingDate.setText(fetchAvailabilityDetailList.get(0).getCheckInDate());
            textViewChekoutDate.setText(fetchAvailabilityDetailList.get(0).getCheckOutDate());
            String numberOfAdluts = "";
            String numberOfChildren = "";
            if (fetchAvailabilityDetailList.get(0).getTotalAdults() > 0) {
              numberOfAdluts = fetchAvailabilityDetailList.get(0).getTotalAdults() + " Adults";
            }
            if (fetchAvailabilityDetailList.get(0).getTotalChildren() > 0) {
              numberOfChildren =
                  fetchAvailabilityDetailList.get(0).getTotalChildren() + " Children";
            }
            String numberOfInfants = "";
            if (fetchAvailabilityDetailList.get(0).getTotalInfants() > 0) {
              numberOfAdluts = fetchAvailabilityDetailList.get(0).getTotalInfants() + " Infants";
            }
            textViewNumberOfPeople.setText(numberOfAdluts + numberOfChildren + numberOfInfants);
          }
        }
      }
    }

    //Set OnClickListener
    TextView textViewBook = findViewById(R.id.textview_roompayment_book);
    textViewBook.setOnClickListener(this);
    headerBackButton.setOnClickListener(this);

    //Set TextChangeListener for card number field.
    editTextCardNumber.addTextChangedListener(new TextWatcher() {
      private static final char space = '-';

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        // Remove all spacing char
        int pos = 0;
        while (true) {
          if (pos >= s.length()) {
            break;
          }
          if (space == s.charAt(pos) && (((pos + 1) % 5) != 0 || pos + 1 == s.length())) {
            s.delete(pos, pos + 1);
          } else {
            pos++;
          }
        }

        // Insert char where needed.
        pos = 4;
        while (true) {
          if (pos >= s.length()) {
            break;
          }
          final char c = s.charAt(pos);
          // Only if its a digit where there should be a space we insert a space
          if ("0123456789".indexOf(c) >= 0) {
            s.insert(pos, "" + space);
          }
          pos += 5;
        }
      }
    });

    //Set TextChangeListener for expiry date
    editTextExpiry.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable editable) {

        if (editable.length() > 0 && (editable.length() % 3) == 0) {
          final char c = editable.charAt(editable.length() - 1);
          if ('/' == c) {
            editable.delete(editable.length() - 1, editable.length());
          }
        }
        if (editable.length() > 0 && (editable.length() % 3) == 0) {
          char c = editable.charAt(editable.length() - 1);
          if (Character.isDigit(c)
              && TextUtils.split(editable.toString(), String.valueOf("/")).length <= 2) {
            editable.insert(editable.length() - 1, String.valueOf("/"));
          }
        }
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
       /* if (before == 1 && count == 2 && s.charAt(s.length() - 1) != '/') {
          editTextExpiry.setText(editTextExpiry.getText().toString() + "/");
        }
        if (editTextExpiry.getText().toString().toCharArray().length < 3) {
          editTextExpiry.setText(editTextExpiry.getText().toString().replace("/", ""));
        }*/
      }
    });
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
    cardNumber = editTextCardNumber.getText().toString().trim();
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

        String cardType = AppUtil.getCardType(cardNumber);

        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber(cardNumber.trim());
        paymentCard.setCardHolderName(nameOnCard);
        paymentCard.setExpireDate(expiryDate);
        paymentCard.setCardType(cardType);
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
