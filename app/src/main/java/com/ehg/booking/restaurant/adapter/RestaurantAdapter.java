/*
 *  Created by Emaar Hospitality Group on 16/10/18 11:10 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 16/10/18 11:10 AM
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

package com.ehg.booking.restaurant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ehg.R;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

  private final LayoutInflater inflater;

  private Context context;

  private OnRestaurantItemClickListener onRestaurantClickListener;

  /**
   * Parameterized constructor for offer list adapter.
   */
  public RestaurantAdapter(Context context, OnRestaurantItemClickListener onRestaurantClickListener) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.onRestaurantClickListener = onRestaurantClickListener;
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
    return new ViewHolder(inflater.inflate(R.layout.item_restaurant,
        viewGroup, false));
  }

  /**
   * Called to bind data values with viewHolder items.
   *
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

    String name = "";
    String address = "";
    String type = "";

    if (position == 0) {
      name = "Na3Na3";
      address = "Address Boulevard";
      type = "Middle Eastern";
    }

    if (position == 1) {
      name = "Grand Cafe Boulevard";
      address = "Address Boulevard";
      type = "Middle Eastern";
    }

    if (position == 2) {
      name = "Na3Na3";
      address = "Address Boulevard";
      type = "Middle Eastern";
    }

    viewHolder.textViewrestaurantName.setText(name);
    viewHolder.textViewrestaurantAddress.setText(address);
    viewHolder.textViewrestaurantType.setText(type);
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return 3;
  }

  /**
   * This is ViewHolder class.
   */
  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private final AppCompatImageView appCompatImageView;

    private final TextView textViewrestaurantName;
    private final TextView textViewrestaurantAddress;
    private final TextView textViewrestaurantType;

    private final Button buttonBook;

    /**
     * ViewHolder constructor.
     *
     * @param itemView required for inflating all components from item layout
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      textViewrestaurantName = itemView.findViewById(R.id.textview_itemrestaurant_restaurantname);
      textViewrestaurantAddress = itemView.findViewById(R.id.textview_itemrestaurant_address);
      textViewrestaurantType = itemView.findViewById(R.id.textview_itemrestaurant_restauranttype);
      appCompatImageView = itemView.findViewById(R.id.appcompatimageview_itemrestaurant_restaurantimage);
      buttonBook = itemView.findViewById(R.id.button_itemrestaurant_book);

      buttonBook.setOnClickListener(this);
    }

    /**
     * Called when restaurant list item clicked.
     * @param view clicked item view
     */
    @Override
    public void onClick(View view) {
      if(onRestaurantClickListener != null) {
        onRestaurantClickListener.onRestaurantItemClicked(getAdapterPosition());
      }
    }
  }

  /**
   * Interface.
   */
  public interface OnRestaurantItemClickListener {
    void onRestaurantItemClicked(int position);
  }
}
