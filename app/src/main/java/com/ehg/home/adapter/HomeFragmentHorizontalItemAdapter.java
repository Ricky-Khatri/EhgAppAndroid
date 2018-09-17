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

  /**
   * Parameterized constructor for HomeFragmentHorizontalItemAdapter.
   */
  public HomeFragmentHorizontalItemAdapter(Context context, String[] ary) {

    this.context = context;
    this.inflater = LayoutInflater.from(context);
    imageUrls = ary;

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

    Glide.with(context).load(imageUrls[position % imageUrls.length])
        .into(viewHolder.roundedImageView);

  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return imageUrls.length;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

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
    }
  }
}
