/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:45 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 21/9/18 3:39 PM
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

package com.ehg.ubyemaar.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.home.HomeActivity;
import com.ehg.home.fragment.BaseFragment;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.signinsignup.pojo.Detail;
import com.ehg.signinsignup.pojo.UserProfilePojo;
import com.ehg.ubyemaar.BenefitsActivity;
import com.ehg.ubyemaar.UpointActivity;
import com.ehg.ubyemaar.UserPreferencesActivity;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
import com.ehg.utilities.LanguageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import java.util.Objects;

public class UbyEmaarFragment extends BaseFragment implements ApiResponseListener, OnClickListener {

  private static final String GET_MEMBER_DETAIL_METHOD = "getMemberDetailMethod";
  private Context context;

  private TextView textViewProfile;
  private TextView textViewUPoint;
  private TextView textViewPreference;
  private TextView textViewbenifits;
  private TextView textViewMemberShipType;
  private TextView textViewMemberShip;
  private TextView textViewTotalPoints;
  private TextView textViewTotalPointsLabel;
  private TextView textViewAmount;
  private TextView textViewAmountLabel;
  private TextView textViewCurrency;
  private TextView textViewUserName;

  /**
   * Called when fragment created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * Called to inflate fragment view.
   *
   * @param inflater LayoutInflater
   * @param container ViewGroup
   * @param savedInstanceState Bundle
   * @return View
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_ubyemaar, container, false);

    ButterKnife.bind(this, view);

    return view;
  }

  /**
   * Called to instantiate view components of fragment.
   *
   * @param view View
   * @param savedInstanceState Bundle
   */
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    /*if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      ((HomeActivity) Objects.requireNonNull(getActivity()))
          .updateToolbarTitle(getResources().getString(R.string.u_by_emaar_title));
    }*/

    this.context = getActivity();
    initView(view);

    //Call getMemberDetail api
    if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(context).getStringValue(
        SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {
      getMemberDetails(SharedPreferenceUtils.getInstance(context).getStringValue(
          SharedPreferenceUtils.ACCOUNT_ID, ""));
    }
  }

  /**
   * Called to attach activity context to fragment.
   *
   * @param context activity context
   */
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  /**
   * Called to init view components of this fragment.
   */
  private void initView(View view) {
    TextView textViewHeaderTitle = view.findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(getResources().getString(R.string.offers_title));
    view.findViewById(R.id.imageview_header_back).setVisibility(View.INVISIBLE);
    textViewProfile = view.findViewById(R.id.textview_ubyemaar_profile);
    textViewUPoint = view.findViewById(R.id.textview_ubyemaar_upointactivity);
    textViewPreference = view.findViewById(R.id.textview_ubyemaar_preferences);
    textViewbenifits = view.findViewById(R.id.textview_ubyemaar_benefits);

    textViewMemberShipType = view.findViewById(R.id.textview_ubyemaar_membershiptype);
    textViewMemberShip = view.findViewById(R.id.textview_ubyemaar_membership);
    textViewTotalPoints = view.findViewById(R.id.textview_ubyemaar_totaluppointcount);
    textViewTotalPointsLabel = view.findViewById(R.id.textview_ubyemaar_totaluppointlabel);
    textViewAmount = view.findViewById(R.id.textview_ubyemaar_amount);
    textViewAmountLabel = view.findViewById(R.id.textview_ubyemaar_amountlabel);
    textViewCurrency = view.findViewById(R.id.textview_ubyemaar_currency);
    textViewUserName = view.findViewById(R.id.textview_ubyemaar_username);

    /*view.findViewById(R.id.linearlayout_ubyemaar_profile).setOnClickListener(this);
    view.findViewById(R.id.linearlayout_ubyemaar_upointactivity).setOnClickListener(this);
    view.findViewById(R.id.linearlayout_ubyemaar_preferences).setOnClickListener(this);
    view.findViewById(R.id.linearlayout_ubyemaar_benefits).setOnClickListener(this);*/
  }

  /**
   * Called when activity resumed.
   */
  @Override
  public void onResume() {
    super.onResume();

    try {
      if (context != null) {
        textViewProfile.setText(LanguageUtil.getLanguageTitleFromKey((AppCompatActivity) context,
            context.getResources().getString(R.string.ubyemaar_profilelabels)));
        textViewUPoint.setText(LanguageUtil.getLanguageTitleFromKey((AppCompatActivity) context,
            context.getResources().getString(R.string.ubyemaar_upointlabel)));
        textViewPreference.setText(LanguageUtil.getLanguageTitleFromKey((AppCompatActivity) context,
            context.getResources().getString(R.string.ubyemaar_preferenceslabel)));
        textViewbenifits.setText(LanguageUtil.getLanguageTitleFromKey((AppCompatActivity) context,
            context.getResources().getString(R.string.ubyemaar_benefitslabel)));

        textViewTotalPointsLabel.setText(LanguageUtil
            .getLanguageTitleFromKey((AppCompatActivity) context,
                context.getResources().getString(R.string.ubyemaar_totalupointslabel)));
        textViewAmountLabel.setText(LanguageUtil
            .getLanguageTitleFromKey((AppCompatActivity) context,
                context.getResources().getString(R.string.ubyemaar_availabletoredeemlabel)));
        textViewCurrency.setText(LanguageUtil
            .getLanguageTitleFromKey((AppCompatActivity) context,
                context.getResources().getString(R.string.ubyemaar_aedcurrency)));

        UserProfilePojo userProfilePojo = JsonParserUtil.getInstance(context).getUserProfilePojo();
        if (userProfilePojo != null && userProfilePojo.getData().getDetail().size() > 0) {
          Detail detail = userProfilePojo.getData().getDetail().get(0);
          textViewUserName
              .setText("Hello Mr. " + detail.getFirstName() + " " + detail.getLastName());
          textViewMemberShipType.setText(detail.getTierLevel());
          textViewTotalPoints.setText(detail.getCurrentPoints() + "");
          textViewAmount.setText(detail.getCurrentBalance() + "");
        }
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called when click event initiated by any component.
   *
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {

    Intent intent;

    switch (view.getId()) {

      case R.id.linearlayout_ubyemaar_profile:
        AppUtil.showAlertDialog((AppCompatActivity) context, "Function not implemented.",
            false, "", true, null);
        break;

      case R.id.linearlayout_ubyemaar_upointactivity:
        intent = new Intent(context, UpointActivity.class);
        AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, false);
        break;

      case R.id.linearlayout_ubyemaar_preferences:
        intent = new Intent(context, UserPreferencesActivity.class);
        AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, false);
        break;

      case R.id.linearlayout_ubyemaar_benefits:
        intent = new Intent(context, BenefitsActivity.class);
        AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, false);
        break;

      default:
        break;
    }
  }

  /**
   * Method calls getMemberDetail api.
   */
  private void getMemberDetails(String accountId) {

    if (AppUtil.isNetworkAvailable(context)) {

      new HttpClientRequest().setApiResponseListner(this);

      new HttpClientRequest(context, WebServiceUtil.getUrl(WebServiceUtil.METHOD_GET_MEMBER_DETAIL)
          + accountId,
          new RequestParams(), WebServiceUtil.CONTENT_TYPE,
          GET_MEMBER_DETAIL_METHOD, true).httpGetRequest();
    } else {
      AppUtil.showAlertDialog((AppCompatActivity) context,
          context.getResources().getString(R.string.all_please_check_network_settings),
          false, "", true, null);
    }
  }

  /**
   * Called on successful api response.
   *
   * @param responseVal response string
   * @param requestMethod requested method name
   */
  @Override
  public void onSuccessResponse(String responseVal, String requestMethod) {
    if (GET_MEMBER_DETAIL_METHOD.equalsIgnoreCase(requestMethod)) {

      UserProfilePojo userProfilePojo = new Gson().fromJson(responseVal,
          new TypeToken<UserProfilePojo>() {
          }.getType());

      if (userProfilePojo != null && userProfilePojo.getStatus()) {

        JsonParserUtil.getInstance(context).saveUserProfilePojo(userProfilePojo);

        Detail detail = userProfilePojo.getData().getDetail().get(0);
        textViewUserName.setText("Hello Mr. " + detail.getFirstName() + " " + detail.getLastName());
        textViewMemberShipType.setText(detail.getTierLevel());
        textViewTotalPoints.setText(detail.getCurrentPoints() + "");
        textViewAmount.setText(detail.getCurrentBalance() + "");
      }
    }
  }

  /**
   * Called on failure api response.
   *
   * @param errorMessage error string
   */
  @Override
  public void onFailureResponse(String errorMessage) {
    AppUtil.showAlertDialog((AppCompatActivity) context, errorMessage, false,
        getResources().getString(R.string.dialog_errortitle), true, null);
  }
}
