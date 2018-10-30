/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:41 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 20/9/18 5:59 PM
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

package com.ehg.reservations.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.home.HomeActivity;
import com.ehg.home.fragment.BaseFragment;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.networkrequest.WebServiceUtil;
import com.ehg.signinsignup.pojo.UserProfilePojo;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReservationsFragment extends BaseFragment implements ApiResponseListener {

  private static final String RESERVATIONS_METHOD = "Reservations";
  private Context context;

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

    View view = inflater.inflate(R.layout.fragment_reservations, container, false);

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
          .updateToolbarTitle(getResources().getString(R.string.reservations_title));
    }*/

    this.context = getActivity();
    initView(view);
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
   * Init's view components of this screen.
   *
   * @param view view
   */
  private void initView(View view) {

    TextView textViewHeaderTitle = view.findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(getResources().getString(R.string.reservations_title));
    view.findViewById(R.id.imageview_header_back).setVisibility(View.INVISIBLE);

    //Init tab layout
    TabLayout tabLayout = view.findViewById(R.id.tab_layout_all);
    final ViewPager viewPager = view.findViewById(R.id.viewpager_all_fragment_viewpager);

    //Create tabs
    tabLayout.addTab(tabLayout.newTab().setText(
        getResources().getString(R.string.reservation_upcomingtab)));
    tabLayout.addTab(tabLayout.newTab().setText(
        getResources().getString(R.string.reservation_pasttab)));
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    //Set viewpager fragment adapter
    FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager());
    viewPager.setAdapter(fragmentAdapter);

    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab layoutTab) {

        viewPager.setCurrentItem(layoutTab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab layoutTab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab layoutTab) {

      }
    });
  }

  /**
   * This class initializes viewpager fragment adapter.
   */
  public class FragmentAdapter extends FragmentStatePagerAdapter {

    /**
     * Constructor.
     *
     * @param fragmentManager para
     */
    public FragmentAdapter(FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
      return 2;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return new UpcomingReservationFragment();
        case 1:
          return new PastReservationFragment();
        default:
          return null;
      }
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Called to get list of all reservations.
   */
  private void getAllReservations() {
    if (AppUtil.isNetworkAvailable(context)) {
      new HttpClientRequest().setApiResponseListner(this);

      new HttpClientRequest(context,
          WebServiceUtil.getUrl(WebServiceUtil.METHOD_GET_ALL_RESERVATIONS)
              +  SharedPreferenceUtils.getInstance(context)
              .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, ""),
          new RequestParams(), WebServiceUtil.CONTENT_TYPE,
          RESERVATIONS_METHOD, true).httpGetRequest();
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
      if (requestMethod.equalsIgnoreCase(RESERVATIONS_METHOD)
          && responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && new JSONObject(responseVal).getBoolean("status")) {

      } else if (responseVal != null && !responseVal.equalsIgnoreCase("")
          && !responseVal.startsWith("<") && !new JSONObject(responseVal).getBoolean("status")) {

        JSONObject dataObject = new JSONObject(responseVal).getJSONObject("data");

        if (dataObject != null) {
          JSONArray detailArray = dataObject.optJSONArray("detail");
          if (detailArray != null && detailArray.length() > 0) {
            JSONObject validationError = detailArray.optJSONObject(0)
                .optJSONArray("validationErrors").optJSONObject(0);

            AppUtil.showAlertDialog((AppCompatActivity) context,
                validationError.getString("ErrorMessage"), false,
                getResources().getString(R.string.dialog_errortitle), true, null);
          }
        }
      } else {
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
