/*
 *  Created by Emaar Hospitality Group on 24/9/18 11:39 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 24/9/18 11:39 AM
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

package com.ehg.offers.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.home.fragment.BaseFragment;
import com.ehg.reservations.adapter.ReservationAdapter;
import com.ehg.reservations.pojo.ReservationCategoryPojo;
import com.ehg.reservations.pojo.ReservationPojo;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;

/***
 * This class will show list of guest specific offers.
 */
public class ForYouOffersFragment extends BaseFragment implements OnClickListener {

  private Context context;

  private ArrayList<ReservationCategoryPojo> reservationCategoryList;
  private ArrayList<ReservationPojo> reservationList;

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

    View view = inflater.inflate(R.layout.fragment_foryouoffers, container, false);

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

    this.context = getActivity();
    initView(view);
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
   * Method init's view component of this fragment.
   *
   * @param view view
   */
  private void initView(View view) {

    try {

      initForYouOfferList(view);

      LinearLayout linearLayoutSort = view.findViewById(R.id.linearlayout_foryouoffers_sort);
      linearLayoutSort.getLayoutParams().width
          = (AppUtil.getDeviceWidth((AppCompatActivity) context) / 2) - 30;
      LinearLayout linearLayoutFilter = view.findViewById(R.id.linearlayout_foryouoffers_filter);
      linearLayoutFilter.getLayoutParams().width
          = (AppUtil.getDeviceWidth((AppCompatActivity) context) / 2) - 30;

      //Set OnClickedListener
      linearLayoutSort.setOnClickListener(this);
      linearLayoutFilter.setOnClickListener(this);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called when click event initiated.
   *
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {
    switch (view.getId()) {

      case R.id.linearlayout_foryouoffers_sort:

        break;

      case R.id.linearlayout_foryouoffers_filter:

        break;

      default:
        break;
    }
  }

  /**
   * Called to init guest specific (For you) offer list.
   * @param view view
   */
  private void initForYouOfferList(View view) {
    //Init vertical reservation recycler view
    RecyclerView recyclerViewReservation = view
        .findViewById(R.id.recyclerview_foryouoffers_foryouofferlist);
    recyclerViewReservation.setLayoutManager(
        new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    recyclerViewReservation.setHasFixedSize(true);

    //Prepare data
    reservationList = new ArrayList<>();
    ReservationPojo reservationPojo = new ReservationPojo();
    reservationPojo.setTitle("Address Downtown");
    reservationPojo.setAddress("Downtown, Dubai");
    reservationPojo.setCheckinDate("Check-in : 17, Oct 2018");
    reservationPojo.setCheckoutDate("Check-out : 17, Oct 2018");
    reservationPojo.setAdults("Adults : 2");
    reservationPojo.setChilds("Children : 2");
    reservationPojo.setTimeAvailable(false);
    reservationList.add(reservationPojo);
    reservationPojo = new ReservationPojo();
    reservationPojo.setTitle("Lounge");
    reservationPojo.setAddress("Address downtown");
    reservationPojo.setCheckinDate("Date : 21, Oct 2018");
    reservationPojo.setCheckoutDate("12:15 PM");
    reservationPojo.setAdults("Guests : 4");
    reservationPojo.setChilds("");
    reservationPojo.setTimeAvailable(true);
    reservationList.add(reservationPojo);
    reservationPojo = new ReservationPojo();
    reservationPojo.setTitle("The Spa");
    reservationPojo.setAddress("Address downtown");
    reservationPojo.setCheckinDate("Date : 1, Oct 2018");
    reservationPojo.setCheckoutDate("12:15 PM");
    reservationPojo.setAdults("Guests : 2");
    reservationPojo.setChilds("");
    reservationPojo.setTimeAvailable(true);
    reservationList.add(reservationPojo);
    reservationPojo = new ReservationPojo();
    reservationPojo.setTitle("Golf");
    reservationPojo.setAddress("Dubai");
    reservationPojo.setCheckinDate("Date : 23, Oct 2018");
    reservationPojo.setCheckoutDate("12:15 PM");
    reservationPojo.setAdults("Guests : 2");
    reservationPojo.setChilds("");
    reservationPojo.setTimeAvailable(true);
    reservationList.add(reservationPojo);
    reservationPojo = new ReservationPojo();
    reservationPojo.setTitle("Lounge");
    reservationPojo.setAddress("Address downtown");
    reservationPojo.setCheckinDate("Date : 21, Oct 2018");
    reservationPojo.setCheckoutDate("12:15 PM");
    reservationPojo.setAdults("Guests : 4");
    reservationPojo.setChilds("");
    reservationPojo.setTimeAvailable(true);
    reservationList.add(reservationPojo);

    //Set adapter
    DisplayMetrics displaymetrics = new DisplayMetrics();
    ((BaseActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    ReservationAdapter reservationAdapter = new ReservationAdapter(context,
        AppUtil.getDeviceHeight((BaseActivity) context),reservationList);
    recyclerViewReservation.setAdapter(reservationAdapter);
  }
}