/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:41 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 19/9/18 7:17 PM
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

package com.ehg.reservations.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.reservations.pojo.ReservationPojo;
import java.util.ArrayList;

/**
 * This class initiates reservation list.
 */
public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

  private Context context;
  private LayoutInflater inflater;

  private int itemHeight;

  private ArrayList<ReservationPojo> reservationList;

  /**
   * Parameterized constructor for offer list adapter.
   */
  public ReservationAdapter(Context context, int itemHeight,
      ArrayList<ReservationPojo> reservationList) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.itemHeight = itemHeight / 4 - 50;
    this.reservationList = reservationList;
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
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
    return new ViewHolder(inflater.inflate(R.layout.item_reservation_list,
        viewGroup, false));
  }

  /**
   * Called to bind data values with viewHolder items.
   *
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

    /*Glide.with(context).load(bookList.get(position).getImageUrl())
        .into(viewHolder.appCompatImageViewThumb);*/

    ReservationPojo reservationPojo = reservationList.get(position);

    if (reservationPojo.isTimeAvailable()) {
      viewHolder.linearLayoutTime.setVisibility(View.VISIBLE);
      viewHolder.linearLayoutCheckoutDate.setVisibility(View.GONE);
      viewHolder.textViewTime.setText(reservationPojo.getCheckoutDate());
    } else {
      viewHolder.linearLayoutTime.setVisibility(View.GONE);
      viewHolder.linearLayoutCheckoutDate.setVisibility(View.VISIBLE);
      viewHolder.textViewCheckoutDate.setText(reservationPojo.getCheckoutDate());
    }

    viewHolder.textViewTitle.setText(reservationPojo.getTitle());
    viewHolder.textViewAddress.setText(reservationPojo.getAddress());
    viewHolder.textViewCheckinDate.setText(reservationPojo.getCheckinDate());
    viewHolder.textViewAdults.setText(reservationPojo.getAdults());
    viewHolder.textViewChilds.setText(reservationPojo.getChilds());
    viewHolder.textViewReservationType.setText(reservationPojo.getType());
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return reservationList != null && reservationList.size() > 0 ? reservationList.size() : 0;
  }

  /**
   * This is ViewHolder class.
   */
  public class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewTitle;
    private final TextView textViewAddress;
    private final TextView textViewCheckinDate;
    private final TextView textViewCheckoutDate;
    private final TextView textViewAdults;
    private final TextView textViewChilds;
    private final TextView textViewTime;
    private final TextView textViewReservationType;

    private final AppCompatImageView appCompatImageViewThumb;

    private LinearLayout linearLayoutCheckoutDate;
    private LinearLayout linearLayoutTime;

    /**
     * ViewHolder constructor.
     *
     * @param view required for inflating all components from item layout
     */
    public ViewHolder(View view) {
      super(view);

      textViewTitle = view.findViewById(R.id.textview_itemreservationlist_title);
      textViewAddress = view.findViewById(R.id.textview_itemreservationlist_address);
      textViewCheckinDate = view.findViewById(R.id.textview_itemreservationlist_checkindate);
      textViewCheckoutDate = view.findViewById(R.id.textview_itemreservationlist_checkoutdate);
      textViewAdults = view.findViewById(R.id.textview_itemreservationlist_adults);
      textViewChilds = view.findViewById(R.id.textview_itemreservationlist_childs);
      textViewTime = view.findViewById(R.id.textview_itemreservationlist_time);
      textViewReservationType = view.findViewById(R.id.textview_itemreservationlist_reservationtype);
      appCompatImageViewThumb = view.findViewById(R.id.imageview_itembookinglist_image);
      linearLayoutCheckoutDate = view
          .findViewById(R.id.linearlayout_itemreservationlist_checkoutdate);
      linearLayoutTime = view.findViewById(R.id.linearlayout_itemreservationlist_time);
    }
  }
}


