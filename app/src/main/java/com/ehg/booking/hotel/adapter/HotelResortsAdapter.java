/*
 *  Created by Emaar Hospitality Group on 8/10/18 4:03 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 8/10/18 4:03 PM
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
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.ehg.R;

/**
 * This class initiates Hotel Resort list for Hotel Resort class.
 */
public class HotelResortsAdapter extends RecyclerView.Adapter<HotelResortsAdapter.ViewHolder> {

  private final LayoutInflater inflater;
  private final OnHotelItemClickListener onHotelItemClickListener;

  /**
   * Parameterized constructor for HotelResortsAdapter.
   */
  public HotelResortsAdapter(Context context, OnHotelItemClickListener onHotelItemClickListener) {

    this.inflater = LayoutInflater.from(context);
    this.onHotelItemClickListener = onHotelItemClickListener;
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
    return new HotelResortsAdapter.ViewHolder(inflater.inflate(R.layout.item_hotelresorts,
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

  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return 10;
  }

  /**
   * Interface.
   */
  public interface OnHotelItemClickListener {

    void onHotelItemClicked(int position, View view);
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private final AppCompatImageView imageViewHotel;
    private final TextView textViewHotelname;
    private final TextView textViewHotellocation;
    private final RatingBar ratingBarHotel;
    private final Button buttonBook;
    private final LinearLayout linearlayoutImageView;

    /**
     * ViewHolder constructor.
     *
     * @param itemView required for inflating all components from item layout
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      imageViewHotel = itemView.findViewById(R.id.appcompatimageview_itemhotelresort_hotelimage);
      textViewHotelname = itemView.findViewById(R.id.textview_itemhotelresort_hotelname);
      textViewHotellocation = itemView.findViewById(R.id.textview_itemhotelresort_hotellocation);
      ratingBarHotel = itemView.findViewById(R.id.raitingbar_itemhotelresorts_raiting);
      buttonBook = itemView.findViewById(R.id.button_itemhotelresort_book);
      linearlayoutImageView = itemView.findViewById(R.id.linearlayout_itemhotelresorts);

      linearlayoutImageView.setOnClickListener(this);
      buttonBook.setOnClickListener(this);
      //itemView.setOnClickListener(this);
    }

    /**
     * Called when spa item clicked.
     *
     * @param view clicked item view
     */
    @Override
    public void onClick(View view) {
      if (onHotelItemClickListener != null) {
        onHotelItemClickListener.onHotelItemClicked(getAdapterPosition(), view);
      }
    }
  }

}