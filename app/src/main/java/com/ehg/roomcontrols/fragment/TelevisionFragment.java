/*
 *  Created by Emaar Hospitality Group on 23/10/18 6:39 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 23/10/18 6:39 PM
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

package com.ehg.roomcontrols.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.home.fragment.BaseFragment;
import com.ehg.reservations.adapter.ReservationCategoryAdapter;
import com.ehg.reservations.pojo.ReservationCategoryPojo;
import com.ehg.roomcontrols.adapter.LightAdapter;
import com.ehg.roomcontrols.adapter.LightAdapter.OnLightItemClickListener;
import com.ehg.roomcontrols.pojo.LightPojo;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;

public class TelevisionFragment extends BaseFragment implements OnLightItemClickListener {

  private Context context;

  private ArrayList<LightPojo> lightList;

  private RecyclerView recyclerViewLanguageList;
  private RecyclerView recyclerViewChannelList;

  private ArrayList<ReservationCategoryPojo> reservationCategoryList;

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

    View view = inflater.inflate(R.layout.fragment_television, container, false);

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
   * Method init's view component of this fragment.
   *
   * @param view view
   */
  private void initView(View view) {

    try {

      initLanguageList(view);
      initChannelList(view);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init language list.
   *
   * @param view view
   */
  private void initLanguageList(View view) {
    //Init horizontal reservation category recycler view
    recyclerViewLanguageList = view
        .findViewById(R.id.recyclerview_television_languagelist);
    recyclerViewLanguageList.setLayoutManager(
        new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

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
    ReservationCategoryAdapter reservationCategoryAdapter = new ReservationCategoryAdapter(context,
        AppUtil.getDeviceHeight((BaseActivity) context), reservationCategoryList);
    recyclerViewLanguageList.setAdapter(reservationCategoryAdapter);
    AppUtil.animateRecyclerView(context, recyclerViewLanguageList,
        R.anim.layout_animation_from_right);
  }

  /**
   * Called to init reservation list.
   *
   * @param view view
   */
  private void initChannelList(View view) {
    //Init light lis
    recyclerViewChannelList = view.findViewById(R.id.recyclerview_television_channellist);
    recyclerViewChannelList.setLayoutManager(new GridLayoutManager(context, 3));

    //Prepare data
    lightList = new ArrayList<>();
    LightPojo lightPojo = new LightPojo();
    lightPojo.setTitle("MTV HD");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Travel HD");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Discovery");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Star World");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Colors");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Viva");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Star World");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("MTV HD");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Travel HD");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Discovery");
    lightList.add(lightPojo);

    //Set adapter
    LightAdapter lightAdapter = new LightAdapter(context,
        AppUtil.getDeviceHeight((BaseActivity) context), lightList, this);
    recyclerViewChannelList.setAdapter(lightAdapter);
    AppUtil.animateRecyclerView(context, recyclerViewChannelList,
        R.anim.layout_animation_from_bottom);
  }

  @Override
  public void onLightItemClicked(int position) {

  }
}
