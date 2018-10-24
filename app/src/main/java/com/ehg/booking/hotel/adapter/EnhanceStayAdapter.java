/*
 *  Created by Emaar Hospitality Group on 20/10/18 8:42 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 20/10/18 8:42 PM
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

package com.ehg.booking.hotel.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.ehg.R;
import com.ehg.customview.TextSliderView;
import com.ehg.utilities.AppUtil;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderLayout.Transformer;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.BaseSliderView.OnSliderClickListener;
import java.util.ArrayList;

/**
 * This class is initiating the list of hotel and showing hotel list.
 */
public class EnhanceStayAdapter extends RecyclerView
    .Adapter<EnhanceStayAdapter.ViewHolder> implements
    OnSliderClickListener {

  private final Context context;
  private final OnEnhanceStayItemClicklistner onItemListner;

  /**
   * This is parametrized constructor of this adapter class.
   */
  public EnhanceStayAdapter(Context context,
      OnEnhanceStayItemClicklistner itemClicklistner) {

    this.context = context;
    onItemListner = itemClicklistner;
  }

  /**
   * Called to inflate layout item and returns ViewHolder object.
   *
   * @param viewGroup viewGroup object
   * @param position integer position
   * @return returns ViewHolder object
   */
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_enhancestay, viewGroup, false);

    return new ViewHolder(view);
  }

  /**
   * Called to bind data values with viewHolder items.
   *
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

    initAutoScrollViewPager(viewHolder);
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return 5;
  }

  @Override
  public void onSliderClick(BaseSliderView baseSliderView) {

  }

  /**
   * Method init's auto scroll view pager component of this activity.
   */
  private void initAutoScrollViewPager(ViewHolder viewHolder) {

    viewHolder.sliderLayoutImageView.setPresetTransformer(Transformer.Default);

    viewHolder.sliderLayoutImageView.getLayoutParams().height = AppUtil
        .getDeviceHeight((AppCompatActivity) context) / 3 - 50;

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
      TextSliderView sliderView = new TextSliderView(this.context);
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
      viewHolder.sliderLayoutImageView.addSlider(sliderView);
    }

    viewHolder.sliderLayoutImageView
        .setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    viewHolder.sliderLayoutImageView.setCustomAnimation(new DescriptionAnimation());
    viewHolder.sliderLayoutImageView.setDuration(3000);
    //sliderLayoutRestaurantDetail.addOnPageChangeListener(this);
  }

  /**
   * OnItemClick interface for Select item Recycler view.
   */
  public interface OnEnhanceStayItemClicklistner {

    void onItemClick(int position, View view);
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private final SliderLayout sliderLayoutImageView;
    private final LinearLayout linearLayoutSlider;

    /**
     * Constructor of ViewHolder class.
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      sliderLayoutImageView = itemView.findViewById(R.id.sliderlayout_itemenhancestay_slider);
      linearLayoutSlider = itemView.findViewById(R.id.linearlayout_itemenhancestay);
    }

    /**
     * Called when view clicked.
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {

      if (onItemListner != null) {
        onItemListner.onItemClick(getAdapterPosition(), view);
        notifyDataSetChanged();
      }
    }
  }
}
