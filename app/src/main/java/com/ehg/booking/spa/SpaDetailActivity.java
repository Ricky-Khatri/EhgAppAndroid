/*
 *  Created by Emaar Hospitality Group on 16/10/18 6:09 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 16/10/18 6:09 PM
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

package com.ehg.booking.spa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.spa.adapter.SpaDetailHorizontalItemAdapter;
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
 * This class shows details of selected Spa.
 */
public class SpaDetailActivity extends BaseActivity implements OnSliderClickListener,
    OnClickListener {

  private Context context;
  private AppCompatImageView backButton;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      setContentView(R.layout.activity_spadetail);

      context = this;
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
    } else {
      textViewHeaderTitle.setText("The Spa");
    }

    Button buttonRequestInquiry = findViewById(R.id.button_spadetail_requestenquiry);
    buttonRequestInquiry.setOnClickListener(this);
    backButton = findViewById(R.id.imageview_header_back);
    backButton.setOnClickListener(this);
    RecyclerView recyclerViewSpaList = findViewById(
        R.id.recyclerview_spadetail_gallery);
    recyclerViewSpaList.setHasFixedSize(true);
    recyclerViewSpaList.setLayoutManager(new LinearLayoutManager(this.context,
        LinearLayoutManager.HORIZONTAL, false));

    SpaDetailHorizontalItemAdapter adapter = new SpaDetailHorizontalItemAdapter(context);

    recyclerViewSpaList.setAdapter(adapter);
    /*RestaurantDetailGalleryAdapter restaurantDetailGalleryAdapter =
        new RestaurantDetailGalleryAdapter(this, this);
    recyclerViewRestaurantGallery.setAdapter(restaurantDetailGalleryAdapter);
    AppUtil.animateRecyclerView(this, recyclerViewRestaurantGallery,
        R.anim.layout_animation_from_right);*/
    AppUtil.animateRecyclerView(this, recyclerViewSpaList,
        R.anim.layout_animation_from_right);

    Button buttonBookNow = findViewById(R.id.button_spadetail_requestenquiry);
    Button buttonCallUs = findViewById(R.id.button_spadetail_callus);

    //Set OnClickListener
    findViewById(R.id.imageview_header_back).setOnClickListener(this);
    buttonBookNow.setOnClickListener(this);
    buttonCallUs.setOnClickListener(this);
  }

  private void initAutoScrollViewPager() {

    SliderLayout sliderLayoutRestaurantDetail = findViewById(
        R.id.sliderlayout_spadetail_slider);

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
   * Called when activity item clicked.
   *
   * @param view clicked item view
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation((AppCompatActivity) context);
        break;

      case R.id.button_spadetail_requestenquiry:
        Intent intent = new Intent(this, SpaRequestEnquiryActivity.class);
        AppUtil.startActivityWithAnimation(this, intent, false);
        break;
      default:
        break;
    }
  }

  /**
   * Called when slider item clicked.
   *
   * @param baseSliderView clicked view
   */
  @Override
  public void onSliderClick(BaseSliderView baseSliderView) {

  }
}
