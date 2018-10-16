/*
 *  Created by Emaar Hospitality Group on 12/9/18 7:03 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 12/9/18 6:33 PM
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

package com.ehg.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ehg.R;
import com.ehg.utilities.AppUtil;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * This class initiates Home fragment horizontal item list for Home Fragment class.
 */
public class HomeFragmentHorizontalItemAdapter extends
    RecyclerView.Adapter<HomeFragmentHorizontalItemAdapter.ViewHolder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final String[] imageUrls;

  private String title;

  private int heightWidthFactor;

  private HorizontalItemClickListener horizontalItemClickListener;
  private String itemName;

  /**
   * Parameterized constructor for HomeFragmentHorizontalItemAdapter.
   */
  public HomeFragmentHorizontalItemAdapter(Context context, String[] ary, String title,
      HorizontalItemClickListener horizontalItemClickListener) {

    this.context = context;
    this.inflater = LayoutInflater.from(context);
    imageUrls = ary;
    this.title = title;
    this.horizontalItemClickListener = horizontalItemClickListener;

    if (title.equalsIgnoreCase("HOTELS AND RESORTS")) {
      heightWidthFactor = AppUtil.getDeviceHeight((AppCompatActivity) context) / 4 - 20;
    } else {
      heightWidthFactor = AppUtil.getDeviceHeight((AppCompatActivity) context) / 4 - 70;
    }
  }

  /**
   * Called to inflate layout item and returns ViewHolder object.
   *
   * @param viewGroup viewGroup object
   * @param i integer position
   * @return returns ViewHolder object
   */
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(inflater.inflate(R.layout.item_home_horizontalitem,
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

    /*Glide.with(context).load(imageUrls[position % imageUrls.length])
        .into(viewHolder.roundedImageView);*/

    if (title.equalsIgnoreCase("RESTAURANTS")) {

      if(position == 0) {
        itemName = "3IN1";
      }
      if(position == 1) {
        itemName = "AL BAYT";
      }
      if(position == 2) {
        itemName = "ASADO";
      }
      viewHolder.textViewBrandName.setText(itemName);
    }

    if (title.equalsIgnoreCase("EMAAR LEISURE GROUP")) {

      if(position == 0) {
        itemName = "ARABIAN RANCHES GOLF CLUB";
      }
      if(position == 1) {
        itemName = "DUBAI POLO & EQUESTRIAN CLUB";
      }
      if(position == 2) {
        itemName = "DUBAI MARINA YACHT CLUB";
      }
      viewHolder.textViewBrandName.setText(itemName);
    }
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return imageUrls != null && imageUrls.length > 0 ? imageUrls.length : 0;
  }

  /**
   * ViewHolder class.
   */
  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

    private final RoundedImageView roundedImageView;
    private final TextView textViewBrandName;

    /**
     * ViewHolder constructor.
     *
     * @param itemView required for inflating all components from item layout
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      roundedImageView = itemView
          .findViewById(R.id.roundedimageview_itemhome_horizontalitem_brandimage);
      textViewBrandName = itemView.findViewById(R.id.textview_itemhome_horizontalitem_brandname);

      roundedImageView.getLayoutParams().height = heightWidthFactor;
      roundedImageView.getLayoutParams().width = heightWidthFactor;
      textViewBrandName.getLayoutParams().width = heightWidthFactor;

      itemView.setOnClickListener(this);
    }

    /**
     * Called when horizontal item clicked.
     * @param view clicked view
     */
    @Override
    public void onClick(View view) {
      if(horizontalItemClickListener != null) {
        horizontalItemClickListener.onHorizontalItemClicked(title,itemName);
      }
    }
  }

  /**
   * Horizontal item click listener.
   */
  public interface HorizontalItemClickListener {
    void onHorizontalItemClicked(String title, String itemName);
  }
}
