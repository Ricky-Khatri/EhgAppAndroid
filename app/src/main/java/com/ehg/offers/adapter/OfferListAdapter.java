/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:40 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 31/8/18 3:32 PM
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

package com.ehg.offers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ehg.R;
import com.yayandroid.parallaxrecyclerview.ParallaxViewHolder;

/**
 * This class initiates offer list for OfferListActivity class.
 */
public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.ViewHolder> {

  private Context context;
  private LayoutInflater inflater;

  private int itemHeight;

  private String[] imageUrls = new String[]{
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_1.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_2.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_3.png",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_4.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_5.png",
  };

  /**
   * Parameterized constructor for offer list adapter.
   */
  public OfferListAdapter(Context context, int itemHeight) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.itemHeight = itemHeight / 4;
    if (this.itemHeight == 0 || this.itemHeight < 100) {
      this.itemHeight = 100;
    }
  }

  /**
   * Called to inflate layout item and returns ViewHolder object.
   * @param viewGroup viewGroup object
   * @param position integer position
   * @return returns ViewHolder object
   */
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
    return new ViewHolder(inflater.inflate(R.layout.item_offer_list,
        viewGroup, false));
  }

  /**
   * Called to bind data values with viewHolder items.
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(final ViewHolder viewHolder, int position) {

    Glide.with(context).load(imageUrls[position % imageUrls.length])
        .into(viewHolder.getBackgroundImage());

    viewHolder.getTextView().setText("Row " + position);
  }

  /**
   * Returns total number of items in adapter.
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return imageUrls.length;
  }

  /**
   * This is ViewHolder class.
   */
  public class ViewHolder extends ParallaxViewHolder {

    private final TextView textViewTitle;

    private final RelativeLayout relativeLayout;


    /**
     * ViewHolder constructor.
     *
     * @param view required for inflating all components from item layout
     */
    public ViewHolder(View view) {
      super(view);

      textViewTitle = view.findViewById(R.id.textview_offer_list_title);
      relativeLayout = view.findViewById(R.id.relativelayout_offer_list);

      //Set dynamic height of item layout
      relativeLayout.getLayoutParams().height = itemHeight;
    }

    /**
     * Returns parallax image view id.
     * @return integer resource id
     */
    @Override
    public int getParallaxImageId() {
      return R.id.parallax_imageview_offer_list_offer_image;
    }

    /**
     * Returns text view object.
     * @return textview
     */
    public TextView getTextView() {
      return textViewTitle;
    }
  }
}
