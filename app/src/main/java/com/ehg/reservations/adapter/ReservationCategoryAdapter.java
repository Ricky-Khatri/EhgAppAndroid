/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:41 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 19/9/18 4:42 PM
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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.reservations.pojo.ReservationCategoryPojo;
import java.util.ArrayList;

/**
 * This class init's horizontal reservation category adapter list. Like: All, Hotel, Restaurant,
 * Spa, Gold.
 */
public class ReservationCategoryAdapter extends
    RecyclerView.Adapter<ReservationCategoryAdapter.ViewHolder> {

  private Context context;

  private LayoutInflater inflater;

  private OnReservationCategoryClickListener onReservationCategoryClickListener;

  private int itemHeight;

  private ArrayList<ReservationCategoryPojo> reservationCategoryList;

  /**
   * Parameterized constructor for offer list adapter.
   */
  public ReservationCategoryAdapter(Context context,
      int itemHeight, ArrayList<ReservationCategoryPojo> reservationCategoryList) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.itemHeight = itemHeight / 4;
    this.reservationCategoryList = reservationCategoryList;
  }

  /**
   * Called to inflate layout item and returns ViewHolder object.
   *
   * @param viewGroup viewGroup object
   * @param position integer position
   * @return returns ViewHolder object
   */
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
    return new ViewHolder(inflater.inflate(R.layout.item_reservationcategory_list,
        viewGroup, false));
  }

  /**
   * Called to bind data values with viewHolder items.
   *
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

    ReservationCategoryPojo reservationCategoryPojo = reservationCategoryList.get(position);

    if (reservationCategoryPojo.isSelected()) {
      viewHolder.relativeLayout.setBackgroundResource(
          R.drawable.reservation_category_rounded_bg_selected);
      viewHolder.textViewTitle.setTextColor(context.getResources().getColor(R.color.white));
    } else {
      viewHolder.textViewTitle.setTextColor(context.getResources().getColor(R.color.colorGray));
      viewHolder.relativeLayout.setBackgroundResource(
          R.drawable.reservation_category_rounded_bg_unselected);
    }
    viewHolder.textViewTitle.setText(reservationCategoryList.get(position).getTitle());

    //Set OnClickListener
    viewHolder.relativeLayout.setOnClickListener(new OnClickListener() {
      /**
       * Called when click event initiated.
       *
       * @param view view
       */
      @Override
      public void onClick(View view) {
        if (onReservationCategoryClickListener != null) {
          onReservationCategoryClickListener.onCategoryClicked();
        }
        updateTabSelection(reservationCategoryList.get(position));
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
    return reservationCategoryList != null
        && reservationCategoryList.size() > 0 ? reservationCategoryList.size() : 0;
  }

  /**
   * This is ViewHolder class.
   */
  public class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewTitle;

    private final RelativeLayout relativeLayout;

    /**
     * ViewHolder constructor.
     *
     * @param view required for inflating all components from item layout
     */
    public ViewHolder(View view) {
      super(view);

      textViewTitle = view.findViewById(R.id.textview_itemreservationcategory_categoryname);
      relativeLayout = view.findViewById(R.id.relativelayout_itemreservationcategory);
    }
  }

  /**
   * Reservation category click listener.
   */
  public interface OnReservationCategoryClickListener {

    void onCategoryClicked();
  }

  /**
   * Called to update list tab selection.
   * @param reservationCategoryPojo pojo
   */
  private void updateTabSelection(ReservationCategoryPojo reservationCategoryPojo) {

    if (reservationCategoryList != null && reservationCategoryList.size() > 0) {

      for (int i = 0; i < reservationCategoryList.size(); i++) {
        ReservationCategoryPojo pojo = reservationCategoryList.get(i);

        if (pojo.getTitle().equalsIgnoreCase(reservationCategoryPojo.getTitle())) {
          pojo.setSelected(true);
        } else {
          pojo.setSelected(false);
        }
        reservationCategoryList.set(i, pojo);
      }
      //Refresh category list
      notifyDataSetChanged();
    }
  }
}
