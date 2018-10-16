/*
 *  Created by Emaar Hospitality Group on 8/10/18 6:35 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 8/10/18 6:35 PM
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

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.hotel.adapter.BrandDetailAdapter;
import com.ehg.customview.TextSliderView;
import com.ehg.home.BaseActivity;
import com.ehg.home.adapter.HomeFragmentHorizontalItemAdapter.HorizontalItemClickListener;
import com.ehg.utilities.AppUtil;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderLayout.Transformer;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.BaseSliderView.OnSliderClickListener;
import com.glide.slider.library.Tricks.ViewPagerEx.OnPageChangeListener;
import java.util.ArrayList;

/**
 * Called to show brand details.
 */
public class BrandDetailActivity extends BaseActivity implements OnPageChangeListener,
    OnSliderClickListener, HorizontalItemClickListener {

  private SliderLayout sliderLayoutBrandDetails;
  private RecyclerView recyclerViewBrandList;
  private AppCompatImageView appCompatImageViewBackArrow;

  /**
   * Called when activity created.
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_brand_detail);

    try {

      initAutoScrollViewPager();

      initView();

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init view components of screen.
   */
  private void initView() {
    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    if (getIntent() != null && getIntent().getStringExtra("title") != null) {
      textViewHeaderTitle.setText(getIntent().getStringExtra("title"));
    }
    recyclerViewBrandList = findViewById(R.id.recyclerview_branddetail_list);
    //recyclerViewBrandList.setLayoutManager(new LinearLayoutManager(context));

    LinearLayoutManager manager = new LinearLayoutManager(this,
        LinearLayoutManager.VERTICAL, false);
    recyclerViewBrandList.setLayoutManager(manager);
    recyclerViewBrandList.setHasFixedSize(true);
    BrandDetailAdapter brandDetailAdapter = new BrandDetailAdapter(this, this);
    recyclerViewBrandList.setAdapter(brandDetailAdapter);
    AppUtil.animateRecyclerView(this, recyclerViewBrandList,
        R.anim.layout_animation_from_right);

    appCompatImageViewBackArrow = findViewById(R.id.imageview_header_back);

    //Set OnClickListener
    appCompatImageViewBackArrow.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        AppUtil.finishActivityWithAnimation(BrandDetailActivity.this);
      }
    });
  }

  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();
    setBackArrowRtl(appCompatImageViewBackArrow);
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
   * Method init's auto scroll view pager component of this fragment.
   */
  private void initAutoScrollViewPager() {

    sliderLayoutBrandDetails = findViewById(R.id.sliderlayout_branddetail_slider);

    sliderLayoutBrandDetails.setPresetTransformer(Transformer.Default);

    sliderLayoutBrandDetails.getLayoutParams().height = AppUtil
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
      sliderLayoutBrandDetails.addSlider(sliderView);
    }

    sliderLayoutBrandDetails.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    sliderLayoutBrandDetails.setCustomAnimation(new DescriptionAnimation());
    sliderLayoutBrandDetails.setDuration(3000);
    sliderLayoutBrandDetails.addOnPageChangeListener(this);
  }

  /**
   * Will be called when fragment stopped or destroyed.
   */
  @Override
  public void onStop() {
    // To prevent a memory leak on rotation, make sure to call
    // stopAutoCycle() on the slider before activity or fragment is destroyed
    if (sliderLayoutBrandDetails != null) {
      sliderLayoutBrandDetails.stopAutoCycle();
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
   * Called when horizontal recycler view item clicked.
   *
   * @param title title
   */
  @Override
  public void onHorizontalItemClicked(String title, String itemName) {

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
}
