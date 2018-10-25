/*
 *  Created by Emaar Hospitality Group on 25/10/18 11:50 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 25/10/18 11:50 AM
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

package com.ehg.booking.golf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.golf.adapter.GolfDetailGalleryAdapter;
import com.ehg.booking.golf.adapter.GolfDetailGalleryAdapter.OnItemClickListener;
import com.ehg.booking.restaurant.adapter.RestaurantDetailGalleryAdapter;
import com.ehg.customview.TextSliderView;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderLayout.Transformer;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.BaseSliderView.OnSliderClickListener;
import com.glide.slider.library.Tricks.ViewPagerEx.OnPageChangeListener;
import java.util.ArrayList;

public class GolfDetailActivity extends BaseActivity implements OnPageChangeListener,
    OnSliderClickListener, OnClickListener, OnItemClickListener {

  private Context context;
  private TextView textViewHeaderTitle;
  private AppCompatImageView headerBackButton;
  private SliderLayout sliderLayoutGolfDetails;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {

      setContentView(R.layout.activity_alldetail);
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
   * Init's view components of this screen.
   */
  private void initView() {
    textViewHeaderTitle = findViewById(R.id.textview_header_title);
    headerBackButton = findViewById(R.id.imageview_header_back);

    RecyclerView recyclerViewGolfGallery = findViewById(
        R.id.recyclerview_alldetail_gallery);
    GridLayoutManager manager = new GridLayoutManager(this, 3);
    recyclerViewGolfGallery.setLayoutManager(manager);
    recyclerViewGolfGallery.setHasFixedSize(true);
    GolfDetailGalleryAdapter restaurantDetailGalleryAdapter =
        new GolfDetailGalleryAdapter(this, this);
    recyclerViewGolfGallery.setAdapter(restaurantDetailGalleryAdapter);
    AppUtil.animateRecyclerView(this, recyclerViewGolfGallery,
        R.anim.layout_animation_from_right);

    headerBackButton.setOnClickListener(this);
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
   * Method init's auto scroll view pager component of this fragment.
   */
  private void initAutoScrollViewPager() {

    sliderLayoutGolfDetails = findViewById(R.id.sliderlayout_alldetail_slider);

    sliderLayoutGolfDetails.setPresetTransformer(Transformer.Default);

    sliderLayoutGolfDetails.getLayoutParams().height = AppUtil
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
      sliderLayoutGolfDetails.addSlider(sliderView);
    }

    sliderLayoutGolfDetails.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    sliderLayoutGolfDetails.setCustomAnimation(new DescriptionAnimation());
    sliderLayoutGolfDetails.setDuration(3000);
    sliderLayoutGolfDetails.addOnPageChangeListener(this);
  }

  /**
   * Will be called when fragment stopped or destroyed.
   */
  @Override
  public void onStop() {
    // To prevent a memory leak on rotation, make sure to call
    // stopAutoCycle() on the slider before activity or fragment is destroyed
    if (sliderLayoutGolfDetails != null) {
      sliderLayoutGolfDetails.stopAutoCycle();
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

  /**
   * Called when view item clicked on this activity.
   *
   * @param view clicked view
   */
  @Override
  public void onClick(View view) {

    Intent intent = null;

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation((AppCompatActivity) context);
        break;

      default:
        break;
    }
    AppUtil.startActivityWithAnimation(this, intent, false);

  }

  @Override
  public void onItemClick(int position) {

  }
}
