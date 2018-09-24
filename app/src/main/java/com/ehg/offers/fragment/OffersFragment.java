/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:40 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 19/9/18 1:38 PM
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
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.home.HomeActivity;
import com.ehg.home.fragment.BaseFragment;
import com.ehg.offers.adapter.OfferListAdapter;
import com.ehg.reservations.fragment.PastReservationFragment;
import com.ehg.reservations.fragment.UpcomingReservationFragment;
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
   * Init's view components of this screen.
   * @param view view
   */
  private void initView(View view) {

    //Init tab layout
    TabLayout tabLayout = view.findViewById(R.id.tab_layout_all);
    final ViewPager viewPager = view.findViewById(R.id.viewpager_all_fragment_viewpager);

    //Create tabs
    tabLayout.addTab(tabLayout.newTab().setText(R.string.offers_latest));
    tabLayout.addTab(tabLayout.newTab().setText(R.string.offers_foryou));
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    //Set viewpager fragment adapter
    FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager());
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
          return new LatestOffersFragment();
        case 1:
          return new ForYouOffersFragment();
        default:
          return null;
      }
    }
  }
}
