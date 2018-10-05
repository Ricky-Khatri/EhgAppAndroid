/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:42 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 21/9/18 3:41 PM
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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.home.fragment.BaseFragment;
import com.ehg.reservations.adapter.ReservationAdapter;
import com.ehg.reservations.adapter.ReservationCategoryAdapter;
import com.ehg.reservations.pojo.ReservationCategoryPojo;
import com.ehg.reservations.pojo.ReservationPojo;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;

/**
 * This class shows upcoming reservations list.
 */
public class UpcomingReservationFragment  extends BaseFragment {

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

    View view = inflater.inflate(R.layout.fragment_upcoming_reservation, container, false);

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

      initReservationCategoryList(view);
      initReservationList(view);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init reservation category list.
   * @param view view
   */
  private void initReservationCategoryList(View view) {
    //Init horizontal reservation category recycler view
    RecyclerView recyclerViewReservationCategory = view
        .findViewById(R.id.recyclerview_upcomingreservation_reservationcategory);
    recyclerViewReservationCategory.setLayoutManager(
        new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    recyclerViewReservationCategory.setHasFixedSize(true);

    //Prepare data
    reservationCategoryList = new ArrayList<>();
    ReservationCategoryPojo reservationCategoryPojo = new ReservationCategoryPojo();
    reservationCategoryPojo.setSelected(true);
    reservationCategoryPojo.setTitle("All");
    reservationCategoryList.add(reservationCategoryPojo);
    reservationCategoryPojo = new ReservationCategoryPojo();
    reservationCategoryPojo.setSelected(false);
    reservationCategoryPojo.setTitle("Hotel");
    reservationCategoryList.add(reservationCategoryPojo);
    reservationCategoryPojo = new ReservationCategoryPojo();
    reservationCategoryPojo.setSelected(false);
    reservationCategoryPojo.setTitle("Restaurant");
    reservationCategoryList.add(reservationCategoryPojo);
    reservationCategoryPojo = new ReservationCategoryPojo();
    reservationCategoryPojo.setSelected(false);
    reservationCategoryPojo.setTitle("Spa");
    reservationCategoryList.add(reservationCategoryPojo);
    reservationCategoryPojo = new ReservationCategoryPojo();
    reservationCategoryPojo.setSelected(false);
    reservationCategoryPojo.setTitle("Golf");
    reservationCategoryList.add(reservationCategoryPojo);

    //Set adapter
    DisplayMetrics displaymetrics = new DisplayMetrics();
    ((BaseActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    ReservationCategoryAdapter reservationCategoryAdapter = new ReservationCategoryAdapter(context,
        AppUtil.getDeviceHeight((BaseActivity) context),reservationCategoryList);
    recyclerViewReservationCategory.setAdapter(reservationCategoryAdapter);
    AppUtil.animateRecyclerView(context,recyclerViewReservationCategory,
        R.anim.layout_animation_from_right);
  }

  /**
   * Called to init reservation list.
   * @param view view
   */
  private void initReservationList(View view) {
    //Init vertical reservation recycler view
    RecyclerView recyclerViewUpcomingReservationList = view
        .findViewById(R.id.recyclerview_upcomingreservation_reservation);
    recyclerViewUpcomingReservationList.setLayoutManager(
        new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    recyclerViewUpcomingReservationList.setHasFixedSize(true);

    //Prepare data
    reservationList = new ArrayList<>();
    ReservationPojo reservationPojo = new ReservationPojo();
    reservationPojo.setTitle("Address Downtown");
    reservationPojo.setAddress("Downtown, Dubai");
    reservationPojo.setCheckinDate("Check-in Date : 17, Oct 2018");
    reservationPojo.setCheckoutDate("Check-out Date : 17, Oct 2018");
    reservationPojo.setAdults("Adults : 2");
    reservationPojo.setChilds("Children : 2");
    reservationPojo.setType("Hotel");
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
    reservationPojo.setType("Restaurant");
    reservationList.add(reservationPojo);
    reservationPojo = new ReservationPojo();
    reservationPojo.setTitle("The Spa");
    reservationPojo.setAddress("Address downtown");
    reservationPojo.setCheckinDate("Date : 1, Oct 2018");
    reservationPojo.setCheckoutDate("12:15 PM");
    reservationPojo.setAdults("Guests : 2");
    reservationPojo.setChilds("");
    reservationPojo.setTimeAvailable(true);
    reservationPojo.setType("Spa");
    reservationList.add(reservationPojo);
    reservationPojo = new ReservationPojo();
    reservationPojo.setTitle("Golf");
    reservationPojo.setAddress("Dubai");
    reservationPojo.setCheckinDate("Date : 23, Oct 2018");
    reservationPojo.setCheckoutDate("12:15 PM");
    reservationPojo.setAdults("Guests : 2");
    reservationPojo.setChilds("");
    reservationPojo.setTimeAvailable(true);
    reservationPojo.setType("Golf");
    reservationList.add(reservationPojo);

    //Set adapter
    DisplayMetrics displaymetrics = new DisplayMetrics();
    ((BaseActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    ReservationAdapter reservationAdapter = new ReservationAdapter(context,
        AppUtil.getDeviceHeight((BaseActivity) context),reservationList);
    recyclerViewUpcomingReservationList.setAdapter(reservationAdapter);
    AppUtil.animateRecyclerView(context,recyclerViewUpcomingReservationList,
        R.anim.layout_animation_from_bottom);
  }
}

