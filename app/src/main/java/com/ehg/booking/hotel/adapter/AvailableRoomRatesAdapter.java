/*
 *  Created by Emaar Hospitality Group on 23/10/18 5:38 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 23/10/18 5:38 PM
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
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo.AverageRate;
import java.util.HashMap;
import java.util.List;

/**
 * This class is showing room rates.
 */
public class AvailableRoomRatesAdapter extends
    RecyclerView.Adapter<AvailableRoomRatesAdapter.ViewHolder> {

  private final Context context;
  private final OnRoomRatesItemClicklistner onRoomRatesListner;

  private List<AverageRate> averageRateList;

  private HashMap<String, Boolean> roomRateSelectionHashMap;

  /**
   * This is parametrized constructor of this adapter class.
   */
  public AvailableRoomRatesAdapter(Context context,
      OnRoomRatesItemClicklistner roomRatesItemClicklistner,
      List<AverageRate> averageRateList) {

    this.context = context;
    onRoomRatesListner = roomRatesItemClicklistner;
    this.averageRateList = averageRateList;
    roomRateSelectionHashMap = new HashMap<>();
    if (averageRateList != null && averageRateList.size() > 0) {
      roomRateSelectionHashMap.put(averageRateList.get(0).getRatePlanCode(), true);
    }
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
        .inflate(R.layout.item_availableroomrates, viewGroup, false);

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

    final AverageRate averageRate = averageRateList.get(position);
    if (roomRateSelectionHashMap.containsKey(averageRate.getRatePlanCode())
        && roomRateSelectionHashMap.get(averageRate.getRatePlanCode())) {
      viewHolder.textViewRateSelection
          .setBackgroundResource(R.drawable.all_square_border_background_dark);
      viewHolder.textViewRateSelection.setTextColor(context.getResources().getColor(R.color.white));
      viewHolder.textViewRateSelection.setText("SELECTED RATE");
    } else {
      viewHolder.textViewRateSelection.setBackgroundResource(R.drawable.all_background_border);
      viewHolder.textViewRateSelection
          .setTextColor(context.getResources().getColor(R.color.colorGray));
      viewHolder.textViewRateSelection.setText("SELECT RATE");
    }
    viewHolder.textViewPrice.setText(SharedPreferenceUtils.getInstance(context)
        .getStringValue(SharedPreferenceUtils.APP_CURRENCY, "AED") +
        " "+ averageRate.getRate());//TODO:Make it dynamic

    //SetOnCLickListener
    viewHolder.textViewRateSelection.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (roomRateSelectionHashMap.containsKey(averageRate.getRatePlanCode())
            && roomRateSelectionHashMap.get(averageRate.getRatePlanCode())) {
          roomRateSelectionHashMap.put(averageRate.getRatePlanCode(), false);
        } else {
          roomRateSelectionHashMap.put(averageRate.getRatePlanCode(), true);
          if (onRoomRatesListner != null) {
            onRoomRatesListner.onItemClick(position, view, averageRate);
          }
        }
        notifyDataSetChanged();
      }
    });
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return averageRateList != null && averageRateList.size() > 0 ? averageRateList.size() : 0;
  }

  /**
   * OnItemClick interface for Select item Recycler view.
   */
  public interface OnRoomRatesItemClicklistner {

    void onItemClick(int position, View view, AverageRate averageRate);
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private final TextView textViewRateTitle;
    private final TextView textViewRateDetail;
    private final TextView textViewPrice;
    private final TextView textViewRateSelection;

    /**
     * Constructor of ViewHolder class.
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      textViewRateTitle = itemView.findViewById(R.id.textview_itemavailableroomrates_ratestitle);
      textViewRateDetail = itemView.findViewById(R.id.textview_itemavailableroomrates_detail);
      textViewPrice = itemView.findViewById(R.id.textview_itemavailableroomrates_price);
      textViewRateSelection = itemView.findViewById(R.id.textview_itemavailableroomrates_select);
    }

    /**
     * Called when view clicked.
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {
      /*if (onRoomRatesListner != null) {
        onRoomRatesListner.onItemClick(getAdapterPosition(), view);
        notifyDataSetChanged();
      }*/
    }
  }
}
