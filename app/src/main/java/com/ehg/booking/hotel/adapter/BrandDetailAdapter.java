/*
 *  Created by Emaar Hospitality Group on 8/10/18 6:58 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 8/10/18 6:58 PM
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.adapter.HomeFragmentHorizontalItemAdapter;
import com.ehg.home.adapter.HomeFragmentHorizontalItemAdapter.HorizontalItemClickListener;
import com.ehg.utilities.LanguageUtil;

public class BrandDetailAdapter extends RecyclerView.Adapter<BrandDetailAdapter.ViewHolder> {

  private HorizontalItemClickListener horizontalItemClickListener;

  private final Context context;
  private final LayoutInflater inflater;
  private String[] imageUrls = new String[]{
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_1.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_2.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_3.png",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_4.jpg"
      /*"http://yayandroid.com/data/github_library/parallax_listview/test_image_5.png",*/
  };

  /**
   * Parameterized constructor for HomeFragmentAdapter.
   */
  public BrandDetailAdapter(Context context, HorizontalItemClickListener horizontalItemClickListener) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.horizontalItemClickListener = horizontalItemClickListener;
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
  public BrandDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
    return new BrandDetailAdapter.ViewHolder(inflater.inflate(R.layout.item_home,
        viewGroup, false));
  }

  /**
   * Called to bind data values with viewHolder items.
   *
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(@NonNull BrandDetailAdapter.ViewHolder viewHolder, int position) {

    viewHolder.recycleViewFeatureHotel.setHasFixedSize(true);
    viewHolder.recycleViewFeatureHotel
        .setLayoutManager(new LinearLayoutManager(this.context,
            LinearLayoutManager.HORIZONTAL, false));

    String title = "";
    switch (position) {
      case 0:
        title = "HOTELS";
        break;

      case 1:
        title = "RESTAURANTS";
        break;

      case 2:
        title = "SPA";
        break;

      case 3:
        title = "MEET";
        break;

      default:
        break;
    }

    viewHolder.textViewFeatureTitle.setText(LanguageUtil.getLanguageTitleFromKey(
        (AppCompatActivity) context, title));

    //Set horizontal recycler view adapter
    viewHolder.recycleViewFeatureHotel.setAdapter(
        new HomeFragmentHorizontalItemAdapter(context, imageUrls, title,horizontalItemClickListener));
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

    private final RecyclerView recycleViewFeatureHotel;
    private final TextView textViewFeatureTitle;
    private final TextView textViewShowAll;

    /**
     * ViewHolder constructor.
     *
     * @param itemView required for inflating all components from item layout
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      recycleViewFeatureHotel = itemView.findViewById(R.id.recyclerview_item_home);
      textViewFeatureTitle = itemView.findViewById(R.id.textview_item_home_featuretitle);
      textViewShowAll = itemView.findViewById(R.id.textview_item_home_showall);
    }
  }
}

