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
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.ehg.R;
import com.ehg.home.HomeActivity;
import com.ehg.home.fragment.BaseFragment;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.utilities.AppUtil;
import com.loopj.android.http.RequestParams;
import java.util.Objects;

public class UbyEmaarFragment extends BaseFragment implements ApiResponseListener {

  private static final String GET_MEMBER_DETAIL_METHOD = "getMemberDetailMethod";
  private Context context;

  /**
   * Called when fragment created.
   * @param savedInstanceState bundle object
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * Called to inflate fragment view.
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

    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      ((HomeActivity) Objects.requireNonNull(getActivity()))
          .updateToolbarTitle(getResources().getString(R.string.u_by_emaar_title));
    }

    return view;
  }

  /**
   * Called to instantiate view components of fragment.
   * @param view View
   * @param savedInstanceState Bundle
   */
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      ((HomeActivity) Objects.requireNonNull(getActivity()))
          .updateToolbarTitle(getResources().getString(R.string.u_by_emaar_title));
    }

    this.context = getActivity();

    //Call getMemberDetail api
    //getMemberDetails("08989732840");
  }

  /**
   * Called to attach activity context to fragment.
   * @param context activity context
   */
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
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
          GET_MEMBER_DETAIL_METHOD,true).httpGetRequest();
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
      //TODO: Parse api response
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
        getResources().getString(R.string.dialog_errortitle), true,null);
  }
}
