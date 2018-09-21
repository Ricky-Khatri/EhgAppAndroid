/*
 *  Created by Emaar Hospitality Group on 12/9/18 6:43 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 12/9/18 6:33 PM
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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.customview.TextSliderView;
import com.ehg.home.BaseActivity;
import com.ehg.home.HomeActivity;
import com.ehg.home.adapter.HomeFragmentAdapter;
import com.ehg.home.adapter.HomeRoomControlAdapter;
import com.ehg.offers.adapter.OfferListAdapter;
import com.ehg.utilities.AppUtil;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderLayout.Transformer;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.BaseSliderView.OnSliderClickListener;
import com.glide.slider.library.Tricks.ViewPagerEx.OnPageChangeListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class shows contextual contents for hotel brands and. Allows customers to explore hotel
 * properties.
 */

public class HomeFragment extends BaseFragment implements OnSliderClickListener,
    OnPageChangeListener {

  private SliderLayout sliderLayoutHomeOffers;

  private Context context;

  private OfferListAdapter offerListAdapter;
  private RecyclerView recyclerViewHotelList;
  private LinearLayout linearLayoutRoomControls;
  private RecyclerView recyclerViewRoomControls;
  private TextView textViewClientTitle;
  private TextView textViewGuestInformation;
  private TextView textViewRoomInformation;
  private HomeFragmentAdapter homeFragmentAdapter;

  private String[] imageUrl = new String[]{
      "LIGHTS",
      "AC CONTROL",
      "TELEVISION",
      "PRIVACY & CLEANING",
  };

  /**
   * Called when fragment created.
   *
   * @param savedInstanceState bundle
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * Attach context to fragment.
   *
   * @param context activity context
   */
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  /**
   * Called to inflate fragment layout.
   *
   * @param inflater layout inflater
   * @param container view group
   * @param savedInstanceState bundle
   * @return returns view object
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    // Inflate the layout for this fragment

    return inflater.inflate(R.layout.fragment_home,
        container, false);
  }

  /**
   * Called to instantiate fragment view components.
   *
   * @param view view
   * @param savedInstanceState bundle
   */
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      ((HomeActivity) Objects.requireNonNull(getActivity()))
          .updateToolbarTitle(context.getResources().getString(R.string.home_title));
    }

    try {

      context = getActivity();

      initAutoScrollViewPager(view);

      initView(view);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (RuntimeException rte) {
      rte.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method init's view component of this fragment.
   *
   * @param view view
   */
  private void initView(View view) {

    linearLayoutRoomControls = view.findViewById(R.id.linearlayout_homefragment_roomcontrols);
    recyclerViewRoomControls = view.findViewById(R.id.recyclerview_homefragment_roomcontrol);
    textViewClientTitle = view.findViewById(R.id.textview_homefragment_clientname_title);
    textViewGuestInformation = view.findViewById(R.id.textview_homefragment_guest_information);
    textViewRoomInformation = view.findViewById(R.id.textview_homefragment_roomcontrol_roomdetail);
    recyclerViewHotelList = view.findViewById(R.id.recyclerview_home_fragment);
    //recyclerViewHotelList.setLayoutManager(new LinearLayoutManager(context));

    LinearLayoutManager manager = new LinearLayoutManager(getActivity(),
        LinearLayoutManager.VERTICAL, false);
    recyclerViewHotelList.setLayoutManager(manager);
    recyclerViewHotelList.setHasFixedSize(true);
    homeFragmentAdapter = new HomeFragmentAdapter(context);
    recyclerViewHotelList.setAdapter(homeFragmentAdapter);

    //Room controls Recyclerview
    recyclerViewRoomControls.setLayoutManager(new LinearLayoutManager(this.context,
        LinearLayoutManager.HORIZONTAL, false));
    recyclerViewRoomControls.setAdapter(new HomeRoomControlAdapter(context, imageUrl));

    /* ParallaxRecyclerView recyclerViewOfferList = view
        .findViewById(R.id.parallax_recyclerView_home_fragment_list);
    recyclerViewOfferList.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewOfferList.setHasFixedSize(true);

    DisplayMetrics displaymetrics = new DisplayMetrics();
    ((BaseActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    offerListAdapter = new OfferListAdapter(context,
        AppUtil.getDeviceHeight((BaseActivity) context));
    recyclerViewOfferList.setAdapter(offerListAdapter);*/
  }

  /**
   * Method init's auto scroll view pager component of this fragment.
   *
   * @param view view
   */
  private void initAutoScrollViewPager(View view) {

    sliderLayoutHomeOffers = view.findViewById(R.id.sliderlayout_home_fragment);

    sliderLayoutHomeOffers.setPresetTransformer(Transformer.Default);

    sliderLayoutHomeOffers.getLayoutParams().height = AppUtil
        .getDeviceHeight((BaseActivity) context) / 3;

    ArrayList<String> listUrl = new ArrayList<>();
    ArrayList<String> listName = new ArrayList<>();

    listUrl.add("http://yayandroid.com/data/github_library/parallax_listview/test_image_1.jpg");
    listName.add("Address Downtown");

    listUrl.add("http://yayandroid.com/data/github_library/parallax_listview/test_image_2.jpg");
    listName.add("Address Boulevard");

    listUrl.add(
        "http://yayandroid.com/data/github_library/parallax_listview/test_image_4.jpg");
    listName.add("Rov");

    listUrl.add("http://yayandroid.com/data/github_library/parallax_listview/test_image_3.png");
    listName.add("Vida");

    listUrl.add("http://yayandroid.com/data/github_library/parallax_listview/test_image_5.png");
    listName.add("Address");

    //Add image abd text data to sliderLayout
    for (int index = 0; index < listUrl.size(); index++) {
      TextSliderView sliderView = new TextSliderView(context);
      // if you want show image only / without description text use DefaultSliderView instead

      // initialize SliderLayout
      sliderView
          .empty(R.drawable.emaar_logo)
          .image(listUrl.get(index))
          .description(listName.get(index))
          .setOnSliderClickListener(this);

      //add your extra information
      sliderView.bundle(new Bundle());
      sliderView.getBundle().putString("extra", listName.get(index));
      sliderLayoutHomeOffers.addSlider(sliderView);
    }

    sliderLayoutHomeOffers.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    sliderLayoutHomeOffers.setCustomAnimation(new DescriptionAnimation());
    sliderLayoutHomeOffers.setDuration(3000);
    sliderLayoutHomeOffers.addOnPageChangeListener(this);
  }

  /**
   * Will be called when fragment stopped or destroyed.
   */
  @Override
  public void onStop() {
    // To prevent a memory leak on rotation, make sure to call
    // stopAutoCycle() on the slider before activity or fragment is destroyed
    if (sliderLayoutHomeOffers != null) {
      sliderLayoutHomeOffers.stopAutoCycle();
    }
    super.onStop();
  }

  /**
   * SliderClickListener methods.
   *
   * @param baseSliderView sliderview
   */
  @Override
  public void onSliderClick(BaseSliderView baseSliderView) {

  }

  /**
   * Will be called on page scrolled.
   *
   * @param index page index
   * @param value value
   * @param index1 index1
   */
  @Override
  public void onPageScrolled(int index, float value, int index1) {

  }

  /**
   * Will be called on page selected.
   *
   * @param index page index
   */
  @Override
  public void onPageSelected(int index) {

  }

  /**
   * Will be called on page scroll state changed.
   *
   * @param index page index
   */
  @Override
  public void onPageScrollStateChanged(int index) {

  }
}
