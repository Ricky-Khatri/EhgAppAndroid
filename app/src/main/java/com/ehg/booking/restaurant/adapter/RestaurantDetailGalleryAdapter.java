/*
 *  Created by Emaar Hospitality Group on 11/10/18 4:08 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 11/10/18 4:08 PM
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
import com.ehg.R;

/**
 * This class init's restaurant gallery on RestaurantDetailScreen.
 */
public class RestaurantDetailGalleryAdapter extends
    RecyclerView.Adapter<RestaurantDetailGalleryAdapter.ViewHolder> {

  private Context context;

  private String[] images = {"", "", "", "", "", "", "", "", "", "", "", ""};

  private RestaurantDetailGalleryAdapter.OnItemClickListener onItemClickListener;

  /**
   * This is parametrized constructor of this adapter class.
   */
  public RestaurantDetailGalleryAdapter(Context context,
      RestaurantDetailGalleryAdapter.OnItemClickListener onItemClickListener) {
    this.context = context;
    this.onItemClickListener = onItemClickListener;
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
  public RestaurantDetailGalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
      int position) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_restaurantdetail_gallery, viewGroup, false);

    RestaurantDetailGalleryAdapter.ViewHolder viewHolder = new RestaurantDetailGalleryAdapter.ViewHolder(
        view);
    return viewHolder;
  }

  /**
   * Called to bind data values with viewHolder items.
   *
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(@NonNull RestaurantDetailGalleryAdapter.ViewHolder viewHolder,
      int position) {

    viewHolder.imageViewRestaurantImage.setImageResource(R.drawable.placeholder);
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return images != null ? images.length : 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    // each data item is just a string in this case
    public AppCompatImageView imageViewRestaurantImage;

    public View itemView;

    /**
     * Constructor of ViewHolder class.
     */
    public ViewHolder(View view) {
      super(view);
      itemView = view;
      imageViewRestaurantImage = view.findViewById(R.id.imageview_itemrestaurantdetailgallery_restaurantimage);

      itemView.setOnClickListener(this);
    }

    /**
     * Called when view clicked.
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {

      //AppUtil.clickAnimation(view);

      if (onItemClickListener != null) {
        onItemClickListener.onItemClick(getAdapterPosition());
        notifyDataSetChanged();
      }
    }
  }

  /**
   * OnItemClick interface for SelectLanguage Recycler view.
   */
  public interface OnItemClickListener {

    void onItemClick(int position);
  }
}

