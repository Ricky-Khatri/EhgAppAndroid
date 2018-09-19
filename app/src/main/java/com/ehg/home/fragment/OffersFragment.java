/*
 *  Created by Emaar Hospitality Group on 9/8/18 11:06 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 9/8/18 11:06 AM
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

package com.ehg.home.fragment;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.home.HomeActivity;
import com.ehg.home.adapter.OfferListAdapter;
import com.ehg.utilities.AppUtil;
import com.yayandroid.parallaxrecyclerview.ParallaxRecyclerView;
import java.util.Objects;

/**
 * This class shows list of offers related to multiple hotel properties.
 */
public class OffersFragment extends BaseFragment {

  private Context context;

  private OfferListAdapter offerListAdapter;

  /**
   * Called when fragment created.
   * @param savedInstanceState bundle object
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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
    View view = inflater.inflate(R.layout.fragment_offers,
        container, false);

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
          .updateToolbarTitle(getResources().getString(R.string.offers_title));
    }

    this.context = getActivity();
    initView(view);
  }

  /**
   * Method init's view component of this fragment.
   *
   * @param view view
   */
  private void initView(View view) {
    ParallaxRecyclerView recyclerViewOfferList = view
        .findViewById(R.id.parallax_recyclerView_offer_fragment_list);
    recyclerViewOfferList.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewOfferList.setHasFixedSize(true);

    DisplayMetrics displaymetrics = new DisplayMetrics();
    ((BaseActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    offerListAdapter = new OfferListAdapter(context,
        AppUtil.getDeviceHeight((BaseActivity) context));
    recyclerViewOfferList.setAdapter(offerListAdapter);
  }
}
