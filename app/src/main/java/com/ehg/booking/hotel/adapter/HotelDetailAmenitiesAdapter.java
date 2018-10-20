/*
 *  Created by Emaar Hospitality Group on 17/10/18 4:12 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 17/10/18 4:12 PM
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
import com.ehg.R;

public class HotelDetailAmenitiesAdapter extends RecyclerView.Adapter<HotelDetailAmenitiesAdapter.ViewHolder> {

  private final HotelDetailAmenitiesAdapter.OnAmenitiesItemClickedListener onItemClickListener;
  private Context context;

  private String[] images = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

  /**
   * This is parametrized constructor of this adapter class.
   */
  public HotelDetailAmenitiesAdapter(Context context,
      HotelDetailAmenitiesAdapter.OnAmenitiesItemClickedListener itemClickListener) {
    this.context = context;
    onItemClickListener = itemClickListener;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_hoteldetailamenities, viewGroup, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

  }

  @Override
  public int getItemCount() {
    return images != null ? images.length : 0;
  }


  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private final AppCompatImageView imageViewAmenities;

    /**
     * Constructor of ViewHolder class.
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      imageViewAmenities = itemView.findViewById(R.id.appcompactimageview_itemhoteldetailamenities_amenitiesimage);

      itemView.setOnClickListener(this);
    }

    /**
     * Called when view clicked.
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {

      if (onItemClickListener != null) {
        onItemClickListener.onItemClick(getAdapterPosition());
        notifyDataSetChanged();
      }
    }
  }

  /**
   * OnItemClick interface for Select item Recycler view.
   */
  public interface OnAmenitiesItemClickedListener {

    void onItemClick(int position);
  }

}
