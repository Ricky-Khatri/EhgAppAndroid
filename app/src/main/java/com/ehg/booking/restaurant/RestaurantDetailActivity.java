/*
 *  Created by Emaar Hospitality Group on 11/10/18 11:33 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 11/10/18 11:33 AM
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

package com.ehg.booking.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.restaurant.adapter.RestaurantDetailGalleryAdapter;
import com.ehg.booking.restaurant.adapter.RestaurantDetailGalleryAdapter.OnItemClickListener;
import com.ehg.customview.TextSliderView;
import com.ehg.home.BaseActivity;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.utilities.AppUtil;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderLayout.Transformer;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.BaseSliderView.OnSliderClickListener;
import java.util.ArrayList;

/**
 * This class shows detail of a restaurant.
 */
public class RestaurantDetailActivity extends BaseActivity implements OnItemClickListener,
    OnSliderClickListener, OnClickListener {

  /**
   * Called when activity created.
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      setContentView(R.layout.activity_restaurant_detail);

      initAutoScrollViewPager();

      initView();
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Init's view components of this screen.
   */
  private void initView() {

    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    if (getIntent() != null && getIntent().getStringExtra("title") != null) {
      textViewHeaderTitle.setText(getIntent().getStringExtra("title"));
    }
    RecyclerView recyclerViewRestaurantGallery = findViewById(
        R.id.recyclerview_restaurantdetail_gallery);
    GridLayoutManager manager = new GridLayoutManager(this, 3);
    recyclerViewRestaurantGallery.setLayoutManager(manager);
    recyclerViewRestaurantGallery.setHasFixedSize(true);
    RestaurantDetailGalleryAdapter restaurantDetailGalleryAdapter =
        new RestaurantDetailGalleryAdapter(this, this);
    recyclerViewRestaurantGallery.setAdapter(restaurantDetailGalleryAdapter);
    AppUtil.animateRecyclerView(this, recyclerViewRestaurantGallery,
        R.anim.layout_animation_from_right);

    Button buttonBookNow = findViewById(R.id.button_restaurantdetail_booknow);
    Button buttonCallUs = findViewById(R.id.button_restaurantdetail_callus);

    //Set OnClickListener
    findViewById(R.id.imageview_header_back).setOnClickListener(this);
    buttonBookNow.setOnClickListener(this);
    buttonCallUs.setOnClickListener(this);
  }

  /**
   * Method init's auto scroll view pager component of this activity.
   */
  private void initAutoScrollViewPager() {

    SliderLayout sliderLayoutRestaurantDetail = findViewById(
        R.id.sliderlayout_restaurantdetail_slider);

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
   * Called to update back arrow rtl icons.
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
   * Called when gallery item clicked.
   *
   * @param position position of cliked item
   */
  @Override
  public void onItemClick(int position) {

  }

  /**
   * Called when slider item clicked.
   *
   * @param baseSliderView clicked view
   */
  @Override
  public void onSliderClick(BaseSliderView baseSliderView) {

  }

  /**
   * Called when
   */
  @Override
  public void onClick(View view) {

    Intent intent = null;

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(RestaurantDetailActivity.this);
        break;

      case R.id.button_restaurantdetail_booknow:
        intent = new Intent(this, RestaurantBookingSlotActivity.class);
        break;

      case R.id.button_restaurantdetail_callus:

        break;

      default:

        break;
    }
    AppUtil.startActivityWithAnimation(this, intent, false);
  }
}
