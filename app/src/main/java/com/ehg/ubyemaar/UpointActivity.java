/*
 *  Created by Emaar Hospitality Group on 21/9/18 4:26 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 21/9/18 4:23 PM
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

package com.ehg.ubyemaar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.ubyemaar.adapter.UpointAdapter;
import com.ehg.ubyemaar.pojo.UpointActivityPojo;
import com.ehg.utilities.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is using to show Upoint of the user.
 */

public class UpointActivity extends BaseActivity implements OnClickListener, ApiResponseListener {

  private static final String MEMBER_REDEMPTION_METHOD = "MemberRedemption";
  private Context context;
  private RecyclerView recycleViewUpoint;

  private AppCompatImageView imageviewBack;

  private TextView textViewHeaderTitle;
  private TextView textViewTransactions;

  private LinearLayout linearLayout;

  /**
   * Called when activity created first.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_upoint);
    context = this;

    initView();
  }

  /**
   * Init's view components of this screen.
   */
  private void initView() {

    textViewTransactions = findViewById(R.id.textview_upoint_transactions);
    linearLayout = findViewById(R.id.linearlayout_upointactivity);
    linearLayout.setVisibility(View.INVISIBLE);
    recycleViewUpoint = findViewById(R.id.recyclerview_upointactivity);
    imageviewBack = findViewById(R.id.imageview_header_back);
    imageviewBack.setOnClickListener(this);
    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(R.string.upointactivuty_title);

    LinearLayoutManager manager = new LinearLayoutManager(context,
        LinearLayoutManager.VERTICAL, false);
    recycleViewUpoint.setLayoutManager(manager);
    recycleViewUpoint.setHasFixedSize(true);

    if (!TextUtils.isEmpty(SharedPreferenceUtils.getInstance(context)
        .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""))) {
      getMemberRedemption();
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
   * Called when a view has been clicked.
   *
   * @param view - object of clicked view.
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation((AppCompatActivity) context);
        break;

      default:
        break;
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to get U Point activities..
   */
  private void getMemberRedemption() {
    if (AppUtil.isNetworkAvailable(context)) {
      new HttpClientRequest().setApiResponseListner(this);
      String url = WebServiceUtil.getUrl(WebServiceUtil.METHOD_MEMBER_REDEMPTION)
          + SharedPreferenceUtils.getInstance(context)
          .getStringValue(SharedPreferenceUtils.ACCOUNT_ID, "") + "/"
          + SharedPreferenceUtils.getInstance(context)
          .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "");

      //TODO: Need to comment it.
      url = WebServiceUtil.getUrl(WebServiceUtil.METHOD_MEMBER_REDEMPTION)
          + "00971566000000" + "/" + "3239";

      new HttpClientRequest(context, url,
          new RequestParams(), WebServiceUtil.CONTENT_TYPE,
          MEMBER_REDEMPTION_METHOD, true).httpGetRequest();
    } else {
      AppUtil.showAlertDialog((AppCompatActivity) context,
          context.getResources().getString(R.string.all_please_check_network_settings),
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
      if (requestMethod.equalsIgnoreCase(MEMBER_REDEMPTION_METHOD)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("Status")) {

        JSONObject jsonObject = new JSONObject(responseVal);
        JSONObject dataObject = jsonObject.getJSONObject("Data");
        JSONArray detailArray = dataObject.optJSONArray("Detail");

        List<UpointActivityPojo> upointActivityList = new ArrayList<>();

        if (detailArray != null && detailArray.length() > 0) {
          for (int index = 0; index < detailArray.length(); index++) {
            JSONArray responseDataArray = detailArray.optJSONObject(index)
                .optJSONArray("ResponseData");
            for (int index1 = 0; index1 < responseDataArray.length(); index1++) {
              JSONObject responseData = responseDataArray.optJSONObject(index1);
              UpointActivityPojo upointActivityPojo = new UpointActivityPojo();
              upointActivityPojo.setRedemptionId(responseData.getString("RedemptionId"));
              upointActivityPojo.setRedeemedDateTime(responseData.getString("RedeemedDateTime"));
              upointActivityPojo.setRedeemedAmount(responseData.getString("RedeemedAmount"));
              upointActivityPojo.setRedeemedPoint(responseData.getString("RedeemedPoint"));
              upointActivityPojo.setRedeemedLocation(responseData.getString("RedeemedLocation"));
              upointActivityList.add(upointActivityPojo);
            }
          }
        }

        if (upointActivityList != null && upointActivityList.size() > 0) {
          textViewTransactions.setText("Last " + upointActivityList.size() + " Transactions");
          //Set Adapter
          linearLayout.setVisibility(View.VISIBLE);
          recycleViewUpoint.setAdapter(new UpointAdapter(context, upointActivityList));
          AppUtil.animateRecyclerView(this, recycleViewUpoint,
              R.anim.layout_animation_from_bottom);
        }
      } else if (responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && !new JSONObject(responseVal).getBoolean("Status")) {

        linearLayout.setVisibility(View.INVISIBLE);
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
        linearLayout.setVisibility(View.INVISIBLE);
        AppUtil.showAlertDialog((AppCompatActivity) context,
            new JSONObject(responseVal).getString("message"), false,
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
    AppUtil.showAlertDialog((AppCompatActivity) context, errorMessage, false,
        getResources().getString(R.string.dialog_errortitle), true, null);
  }
}
