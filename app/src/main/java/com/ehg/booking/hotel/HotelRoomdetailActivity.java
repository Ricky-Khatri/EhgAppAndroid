/*
 *  Created by Emaar Hospitality Group on 20/10/18 5:39 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 20/10/18 5:39 PM
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

package com.ehg.booking.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.hotel.adapter.HotelDetailAmenitiesAdapter;
import com.ehg.booking.hotel.adapter.HotelDetailAmenitiesAdapter.OnAmenitiesItemClickedListener;
import com.ehg.customview.TextSliderView;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderLayout.Transformer;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.BaseSliderView.OnSliderClickListener;
import java.util.ArrayList;

/**
 * This class will show room detail o the hotel.
 */
public class HotelRoomdetailActivity extends BaseActivity implements
    OnClickListener, OnSliderClickListener,
    OnAmenitiesItemClickedListener {

  private TextView textViewHeaderTitle;
  private AppCompatImageView headerBackButton;
  private Context context;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {

      setContentView(R.layout.activity_roomdetail);
      context = this;

      initAutoScrollViewPager();

      initView();

    } catch (NullPointerException e) {

      e.printStackTrace();

    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /**
   * Called to init view components of this activity.
   */
  private void initView() {

    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    headerBackButton = findViewById(R.id.imageview_header_back);

    RecyclerView recyclerViewRoomAmenities = findViewById(
        R.id.recyclerview_roomdetail_amenities);
    GridLayoutManager managerGridlayout = new GridLayoutManager(this, 5);
    recyclerViewRoomAmenities.setLayoutManager(managerGridlayout);
    recyclerViewRoomAmenities.setHasFixedSize(true);

    HotelDetailAmenitiesAdapter hotelDetailAmenitiesAdapter = new
        HotelDetailAmenitiesAdapter(context, this);

    recyclerViewRoomAmenities.setAdapter(hotelDetailAmenitiesAdapter);
    AppUtil.animateRecyclerView(this, recyclerViewRoomAmenities,
        R.anim.layout_animation_from_right);

    headerBackButton.setOnClickListener(this);
  }

  /**
   * Method init's auto scroll view pager component of this activity.
   */
  private void initAutoScrollViewPager() {

    SliderLayout sliderLayoutRestaurantDetail = findViewById(
        R.id.sliderlayout_roomdetail_slider);

    sliderLayoutRestaurantDetail.setPresetTransformer(Transformer.Default);

    sliderLayoutRestaurantDetail.getLayoutParams().height = AppUtil
        .getDeviceHeight(this) / 3 - 50;

    ArrayList<String> listUrl = new ArrayList<>();
    ArrayList<String> listName = new ArrayList<>();

    listUrl.add("http://yayandroid.com/data/github_library/parallax_listview/test_image_1.jpg");
    listName.add("Address Downtown");

    listUrl.add("http://yayandroid.com/data/github_library/parallax_listview/test_image_2.jpg");
    listName.add("Address Boulevard");

    listUrl.add("http://yayandroid.com/data/github_library/parallax_listview/test_image_4.jpg");
    listName.add("Rov");

    listUrl.add("http://yayandroid.com/data/github_library/parallax_listview/test_image_3.png");
    listName.add("Vida");

    listUrl.add("http://yayandroid.com/data/github_library/parallax_listview/test_image_5.png");
    listName.add("Address");

    //Add image abd text data to sliderLayout
    for (int index = 0; index < listUrl.size(); index++) {
      TextSliderView sliderView = new TextSliderView(this);
      // if you want show image only / without description text use DefaultSliderView instead

      // initialize SliderLayout
      sliderView
          .empty(R.drawable.placeholder)
          .image(listUrl.get(index))
          .description(listName.get(index))
          .setOnSliderClickListener(this);

      //add your extra information
      sliderView.bundle(new Bundle());
      sliderView.getBundle().putString("extra", listName.get(index));
      sliderLayoutRestaurantDetail.addSlider(sliderView);
    }

    sliderLayoutRestaurantDetail.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    sliderLayoutRestaurantDetail.setCustomAnimation(new DescriptionAnimation());
    sliderLayoutRestaurantDetail.setDuration(3000);
    //sliderLayoutRestaurantDetail.addOnPageChangeListener(this);
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
   * Called to set RTL back arrow.
   *
   * @param appCompatImageView imageview object
   */
  @Override
  public void setBackArrowRtl(AppCompatImageView appCompatImageView) {
    super.setBackArrowRtl(appCompatImageView);
  }

  /**
   * OnKeyDown callback will be called when phone back key pressed.
   *
   * @param keyCode keycode
   * @param event event
   * @return return boolean value
   */
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      AppUtil.finishActivityWithAnimation(this);
    }
    return super.onKeyDown(keyCode, event);
  }

  /**
   * Called when activity view item clicked.
   * @param view clicked view item
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      default:
        break;
    }
  }

  /**
   * Called when slider item clicked.
   * @param baseSliderView clicked view item
   */
  @Override
  public void onSliderClick(BaseSliderView baseSliderView) {

  }

  /**
   * Called when list view item clicked.
   * @param position clicked item position
   */
  @Override
  public void onItemClick(int position) {

  }
}
