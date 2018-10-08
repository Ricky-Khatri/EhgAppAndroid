/*
 *  Created by Emaar Hospitality Group on 14/9/18 6:02 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 14/9/18 6:01 PM
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


package com.ehg.splash.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.ehg.R;
import com.ehg.customview.CarouselLayout;
import com.ehg.splash.SplashActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.Objects;

/**
 * This class is using to show Carousal type of image on splash screen.
 */
public class SplashImageFragment extends Fragment {

  private static final String POSITON = "position";
  private static final String SCALE = "scale";
  private static final String DRAWABLE_RESOURE = "resource";
  private int screenWidth;
  private int screenHeight;
  public String[] imageUrls = new String[] {
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_1.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_2.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_3.png",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_4.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_5.png",
  };

  private SplashActivity context;


  public SplashImageFragment() {

  }

  /**
   * This is a fragment constructor which is using to initiate a Fragment class.
   * @param context -reference of SplashActivity
   * @param pos     -position of image
   * @param scale   -size of image
   * @return - it will return fragment class object
   */
  public Fragment newInstance(SplashActivity context, int pos, float scale) {
    Bundle b = new Bundle();
    b.putInt(POSITON, pos);
    b.putFloat(SCALE, scale);

    this.context = context;

    return Fragment.instantiate(context, SplashImageFragment.class.getName(), b);
  }


  /**
   * Called when fragment created we will initialize essential component here.
   *
   * @param savedInstanceState bundle
   */
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWidthAndHeight();
  }

  /**
   * Called to inflate fragment layout.
   * @param inflater layout inflater
   * @param container view group
   * @param savedInstanceState bundle
   * @return returns view object
   *
   */
  @SuppressLint("SetTextI18n")
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    if (container == null) {
      return null;
    }

    final int position = Objects.requireNonNull(this.getArguments()).getInt(POSITON);
    float scale = this.getArguments().getFloat(SCALE);

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth / 2,
        screenHeight / 2);
    LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.item_splashpager,
        container, false);

    //TextView textView = (TextView) linearLayout.findViewById(R.id.text);
    CarouselLayout root = linearLayout.findViewById(R.id.carousellayout_splashimagefragment);
    RoundedImageView imageView = linearLayout.findViewById(
        R.id.roundedimageview_splashimagefragment);

    //textView.setText("Carousel item: " + postion);
    imageView.setLayoutParams(layoutParams);

    try {

      switch (position) {

        case 0:
          Glide.with(getActivity()).load(R.drawable.one)
              .into(imageView);
          break;

        case 1:
          Glide.with(getActivity()).load(R.drawable.two)
              .into(imageView);
          break;

        case 2:
          Glide.with(getActivity()).load(R.drawable.three)
              .into(imageView);
          break;

        case 3:
          Glide.with(getActivity()).load(R.drawable.four)
              .into(imageView);
          break;

        case 4:
          Glide.with(getActivity()).load(R.drawable.five)
              .into(imageView);
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    //imageView.setImageResource(imageArray[postion]);

    //handling click event
   /* imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getActivity(), ImageDetailsActivity.class);
        intent.putExtra(DRAWABLE_RESOURE, imageArray[postion]);
        startActivity(intent);
      }
    });*/

    root.setScaleBoth(scale);

    return linearLayout;
  }

  /**
   * This method is using to calculate the device screen height and width.
   */
  private void getWidthAndHeight() {
    DisplayMetrics displaymetrics = new DisplayMetrics();
    Objects.requireNonNull(getActivity()).getWindowManager()
        .getDefaultDisplay().getMetrics(displaymetrics);
    screenHeight = displaymetrics.heightPixels;
    screenWidth = displaymetrics.widthPixels;
  }

}
