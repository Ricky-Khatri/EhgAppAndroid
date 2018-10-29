/*
 *  Created by Emaar Hospitality Group on 20/10/18 3:40 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 20/10/18 3:40 PM
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.hotel.adapter.EnhanceStayAdapter.OnEnhanceStayItemClicklistner;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.AverageRate;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.Media;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.RoomStay;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.RoomType;
import com.ehg.booking.hotel.pojo.fetchservicesresponsepojo.ServiceDetail;
import com.ehg.customview.TextSliderView;
import com.ehg.utilities.AppUtil;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderLayout.Transformer;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.BaseSliderView.OnSliderClickListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class to initiate the room selection fo a hotel.
 */
public class SelectRoomAdapter extends RecyclerView.Adapter<SelectRoomAdapter.ViewHolder> implements
    OnSliderClickListener, OnEnhanceStayItemClicklistner {

  private final Context context;
  private final OnRoomItemClicklistner onRoomItemClicklistner;

  private List<RoomStay> roomStayList;
  private List<RoomType> roomTypeList;

  private String baseUrl;

  private HashMap<String, Boolean> roomSelectionHashMap;
  private float selectedRate = 0.0f;
  private int selectedRatePosition = 0;
  private List<ServiceDetail> serviceDetails;

  /**
   * This is parametrized constructor of this adapter class.
   */
  public SelectRoomAdapter(Context context, OnRoomItemClicklistner itemClicklistner,
      List<RoomStay> roomStayList, String baseUrl) {
    this.context = context;
    onRoomItemClicklistner = itemClicklistner;
    this.roomStayList = roomStayList;
    roomTypeList = roomStayList.get(0).getRoomTypes();
    this.baseUrl = baseUrl;
    roomSelectionHashMap = new HashMap<>();
    serviceDetails = new ArrayList<>();
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
        .inflate(R.layout.item_hotelroomselection, viewGroup, false);

    return new ViewHolder(view);
  }

  /**
   * Called to bind data values with viewHolder items.
   *
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

    try {

      if (roomTypeList != null && roomTypeList.size() > 0) {

        final RoomType roomType = roomTypeList.get(position);
        if (roomType.getAverageRates() != null) {
          viewHolder.textViewAllRates.setText(context.getResources()
              .getString(R.string.itemhotelroonselection_rates) + "(" + roomType.getAverageRates()
              .size() + ")");
        } else {
          viewHolder.textViewAllRates.setText(context.getResources()
              .getString(R.string.itemhotelroonselection_rates) + "(0)");
        }
        viewHolder.textViewRoomType.setText(roomType.getRoomTypeName());
        viewHolder.textViewMaxOccupancy
            .setText(roomType.getRoomFeatures().get(0).getQuantity() + "");

        if (selectedRatePosition == position && selectedRate != 0.0) {
          roomType.getAverageRates().get(0).setRate(selectedRate);
        }

        viewHolder.textviewPrice
            .setText(SharedPreferenceUtils.getInstance(context)
                .getStringValue(SharedPreferenceUtils.APP_CURRENCY, "AED")
                + " " + AppUtil
                .getFormatedPriceRate(roomType.getAverageRates().get(0).getRate() + "")
                + "");

        List<Media> mediaList = roomType.getMedias();
        initAutoScrollViewPager(viewHolder, mediaList);

        if (roomSelectionHashMap.containsKey(roomType.getRoomTypeCode())
            && roomSelectionHashMap.get(roomType.getRoomTypeCode())) {
          viewHolder.textViewSelection
              .setBackgroundResource(R.drawable.all_square_border_background_dark);
          viewHolder.textViewSelection.setTextColor(context.getResources().getColor(R.color.white));
          viewHolder.textViewSelection.setText("SELECTED");
        } else {
          viewHolder.textViewSelection.setBackgroundResource(R.drawable.all_background_border);
          viewHolder.textViewSelection
              .setTextColor(context.getResources().getColor(R.color.colorBlack));
          viewHolder.textViewSelection.setText("SELECT");
        }

        //Set EnhanceStay Adapter
        if (serviceDetails != null && serviceDetails.size() > 0
            && selectedRatePosition == position) {
          viewHolder.linearlayoutEnhanceStay.setVisibility(View.VISIBLE);
          viewHolder.recyclerViewEnhanceStay
              .setLayoutManager(
                  new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
          viewHolder.recyclerViewEnhanceStay.setHasFixedSize(true);
          EnhanceStayAdapter selectRoomAdapter = new EnhanceStayAdapter(context, this,
              serviceDetails);
          viewHolder.recyclerViewEnhanceStay.setAdapter(selectRoomAdapter);
        } /*else {
          viewHolder.linearlayoutEnhanceStay.setVisibility(View.GONE);
        }*/

        //SetOnCLickListener
        viewHolder.textViewSelection.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
            if (roomSelectionHashMap.containsKey(roomType.getRoomTypeCode())
                && roomSelectionHashMap.get(roomType.getRoomTypeCode())) {
              //viewHolder.linearlayoutEnhanceStay.setVisibility(View.GONE);
              roomSelectionHashMap.put(roomType.getRoomTypeCode(), false);
            } else {
              //viewHolder.linearlayoutEnhanceStay.setVisibility(View.VISIBLE);
              roomSelectionHashMap.put(roomType.getRoomTypeCode(), true);
              if (onRoomItemClicklistner != null) {
                onRoomItemClicklistner.onItemClick(position, view, null);
              }
            }
          }
        });
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (IndexOutOfBoundsException i) {
      i.printStackTrace();
    }
  }

  /**
   * Called to show enhance stay layout for selected room type.
   *
   * @param position positon
   * @param serviceDetails serviceDetails list
   */
  public void showEnhanceStayLayout(int position, List<ServiceDetail> serviceDetails) {
    this.serviceDetails = serviceDetails;
    this.selectedRatePosition = position;
    notifyDataSetChanged();
  }

  /**
   * Called to update selected rate.
   */
  public void updateSelectedRate(int position, float selectedRate) {
    this.selectedRate = selectedRate;
    this.selectedRatePosition = position;
    notifyDataSetChanged();
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return roomTypeList != null && roomTypeList.size() > 0 ? roomTypeList.size() : 0;
  }

  @Override
  public void onSliderClick(BaseSliderView baseSliderView) {

  }

  /**
   * Method init's auto scroll view pager component of this activity.
   */
  private void initAutoScrollViewPager(ViewHolder viewHolder, List<Media> mediaList) {

    viewHolder.sliderLayoutImageView.setPresetTransformer(Transformer.Default);

    viewHolder.sliderLayoutImageView.getLayoutParams().height = AppUtil
        .getDeviceHeight((AppCompatActivity) context) / 3 - 50;

    //Add image abd text data to sliderLayout
    for (int index = 0; index < mediaList.size(); index++) {
      TextSliderView sliderView = new TextSliderView(this.context);
      // if you want show image only / without description text use DefaultSliderView instead

      // initialize SliderLayout
      sliderView
          .empty(R.drawable.placeholder)
          .image(baseUrl + mediaList.get(index).getSource())
          /*.description(listName.get(index))*/
          .setOnSliderClickListener(this);

      //add your extra information
      sliderView.bundle(new Bundle());
      viewHolder.sliderLayoutImageView.addSlider(sliderView);
    }

    viewHolder.sliderLayoutImageView
        .setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    viewHolder.sliderLayoutImageView.setCustomAnimation(new DescriptionAnimation());
    viewHolder.sliderLayoutImageView.setDuration(3000);
    //sliderLayoutRestaurantDetail.addOnPageChangeListener(this);
  }

  @Override
  public void onItemClick(int position, View view) {

  }

  /**
   * OnItemClick interface for Select item Recycler view.
   */
  public interface OnRoomItemClicklistner {

    void onItemClick(int position, View view,
        List<AverageRate> averageRateList);
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private final SliderLayout sliderLayoutImageView;
    private final LinearLayout linearLayoutSlider;
    private final LinearLayout linearlayoutViewAllRates;
    private final LinearLayout linearlayoutEnhanceStay;
    private final RecyclerView recyclerViewEnhanceStay;
    private final TextView textViewSelection;

    private final TextView textViewRoomType;
    private final TextView textViewMaxOccupancy;
    private final TextView textViewCityView;
    private final TextView textViewSize;
    private final TextView textViewAllRates;
    private final TextView textViewSelectedRoomCount;
    private final TextView textviewPrice;

    /**
     * Constructor of ViewHolder class.
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      textviewPrice = itemView.findViewById(R.id.textview_itemhoteroomselection_price);
      textViewRoomType = itemView.findViewById(R.id.textview_itemhotelroomselection_roomtype);
      textViewMaxOccupancy = itemView.findViewById(R.id.textview_itemhoteroomselection_occupancy);
      textViewCityView = itemView.findViewById(R.id.textView_itemhoteroomselection_view);
      textViewSize = itemView.findViewById(R.id.textview_itemhoteroomselection_size);
      textViewAllRates = itemView.findViewById(R.id.textView_itemhoteroomselection_allrates);
      textViewSelectedRoomCount = itemView
          .findViewById(R.id.textview_itemhoteroomselection_selectioncount);

      sliderLayoutImageView = itemView
          .findViewById(R.id.sliderlayout_itemhotelroomselection_slider);
      linearLayoutSlider = itemView.findViewById(R.id.linearlayout_itemhotelroomselection);
      linearlayoutViewAllRates = itemView
          .findViewById(R.id.linearlayout_itemhotelroomselection_allrates);
      linearlayoutEnhanceStay = itemView
          .findViewById(R.id.linearlayout_itemhotelroomselection_enhancestay);
      recyclerViewEnhanceStay = itemView.findViewById(R.id.recyclerview_itemhotelroomselection);
      textViewSelection = itemView.findViewById(R.id.textview_itemhoteroomselection_selection);

      //textViewSelection.setOnClickListener(this);
      linearlayoutViewAllRates.setOnClickListener(this);
      sliderLayoutImageView.setOnClickListener(this);
      linearLayoutSlider.setOnClickListener(this);
      itemView.setOnClickListener(this);
    }

    /**
     * Called when view clicked.
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {
      if (onRoomItemClicklistner != null) {
        List<AverageRate> averageRateList = roomStayList.get(getAdapterPosition())
            .getRoomTypes().get(getAdapterPosition()).getAverageRates();
        onRoomItemClicklistner.onItemClick(getAdapterPosition(), view, averageRateList);
        notifyDataSetChanged();
      }
    }
  }
}
