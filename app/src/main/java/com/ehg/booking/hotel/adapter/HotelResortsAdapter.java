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
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.ehg.booking.hotel.HotelBookingslotActivity;
import com.ehg.booking.hotel.HotelDetailActivity;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * This class initiates Hotel Resort list for Hotel Resort class.
 */
public class HotelResortsAdapter extends RecyclerView.Adapter<HotelResortsAdapter.ViewHolder> {

  private final LayoutInflater inflater;
  private final OnHotelItemClickListener onHotelItemClickListener;

  private Context context;

  private List<HotelDetail> hotelDetailList;

  /**
   * Parameterized constructor for HotelResortsAdapter.
   */
  public HotelResortsAdapter(Context context, OnHotelItemClickListener onHotelItemClickListener) {

    this.inflater = LayoutInflater.from(context);
    this.onHotelItemClickListener = onHotelItemClickListener;
    this.context = context;
    hotelDetailList = new ArrayList<>();
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
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

    String title = "";
    String address = "";
    switch (position) {
      case 0:
        title = "ADDRESS DOWNTOWN";
        address = "Downtown Dubai";
        break;

      case 1:
        title = "VIDA HARBOUR POINT";
        address = "Downtown Dubai";
        break;

      case 2:
        title = "ROVE DOWNTOWN";
        address = "Zabeel Street";
        break;

      default:
        break;
    }

    HotelDetail hotelDetail = new HotelDetail();
    hotelDetail.setTitle(title);
    hotelDetail.setAddress(address);
    hotelDetailList.add(hotelDetail);

    viewHolder.textViewHotelname.setText(title);
    viewHolder.textViewHotellocation.setText(address);

    //TODO: Temporary solution, will be removed after content api implementation
    viewHolder.itemView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (hotelDetailList != null && hotelDetailList.size() > 0) {
          Intent intent = new Intent(context, HotelDetailActivity.class);
          intent.putExtra("title", hotelDetailList.get(position).title);
          AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, false);
        }
      }
    });

    //TODO: Temporary solution, will be removed after content api implementation
    viewHolder.buttonBook.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (hotelDetailList != null && hotelDetailList.size() > 0) {
          Intent intent = new Intent(context, HotelBookingslotActivity.class);
          intent.putExtra("title", hotelDetailList.get(position).title);
          AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, false);
        }
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
    return 3;
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

      /*linearlayoutImageView.setOnClickListener(this);
      buttonBook.setOnClickListener(this);
      itemView.setOnClickListener(this);*/
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

  public class HotelDetail {

    private String title;
    private String address;

    /**
     * Getter method.
     *
     * @return Gets the value of title and returns title.
     */
    public String getTitle() {
      return title;
    }

    /**
     * Sets the title. You can use getTitle() to get the value of title.
     */
    public void setTitle(String title) {
      this.title = title;
    }

    /**
     * Getter method.
     *
     * @return Gets the value of address and returns address.
     */
    public String getAddress() {
      return address;
    }

    /**
     * Sets the address. You can use getAddress() to get the value of address.
     */
    public void setAddress(String address) {
      this.address = address;
    }
  }
}