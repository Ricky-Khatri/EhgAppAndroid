/*
 *  Created by Emaar Hospitality Group on 26/10/18 1:04 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 26/10/18 1:04 PM
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
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.booking.hotel.pojo.roomareasearchresponsepojo.HotelList;
import com.ehg.booking.hotel.pojo.roomareasearchresponsepojo.Media;
import com.ehg.booking.hotel.pojo.roomareasearchresponsepojo.PropertyMainImage;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

  private final LayoutInflater inflater;
  private final OnSearchResultItemClickListener onSearchResultItemClickListener;

  private Context context;

  private List<HotelList> hotelList;

  /**
   * Parameterized constructor for HotelResortsAdapter.
   */
  public SearchResultAdapter(Context context,
      OnSearchResultItemClickListener onHotelItemClickListener,
      List<HotelList> hotelList) {

    this.inflater = LayoutInflater.from(context);
    this.onSearchResultItemClickListener = onHotelItemClickListener;
    this.context = context;
    this.hotelList = hotelList;
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
    return new ViewHolder(inflater.inflate(R.layout.item_hotelresorts,
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

    HotelList hotel = hotelList.get(position);
    viewHolder.textViewHotelname.setText(hotel.getHotelName());
    viewHolder.textViewHotellocation.setText(hotel.getAddress().getAddress1());
    viewHolder.textViewPrice
        .setText(
            Html.fromHtml("Price starting at\n<font><b>"
                + SharedPreferenceUtils.getInstance(context)
                .getStringValue(SharedPreferenceUtils.APP_CURRENCY, "AED") +
                " " + hotel.getRate() + "</b></font>"));

    Media media = hotel.getMedia() != null ? hotel.getMedia() : new Media();
    List<PropertyMainImage> propertyMainImageList = media.getPropertyMainImages();
    if (propertyMainImageList != null && propertyMainImageList.size() > 0) {
      Glide.with(context).load(propertyMainImageList.get(0).getSource())
          .placeholder(R.drawable.placeholder)
          .into(viewHolder.imageViewHotel);
    }
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return hotelList != null && hotelList.size() > 0 ? hotelList.size() : 3;
  }

  /**
   * OnItemClick interface for Select item Recycler view.
   */
  public interface OnSearchResultItemClickListener {

    void onSearchResultItemClicked(int position, View view, String title);
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private final AppCompatImageView imageViewHotel;
    private final TextView textViewHotelname;
    private final TextView textViewHotellocation;
    private final RatingBar ratingBarHotel;
    private final Button buttonBook;
    private final LinearLayout linearlayoutImageView;
    private final TextView textViewPrice;

    /**
     * ViewHolder constructor.
     *
     * @param itemView required for inflating all components from item layout
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      textViewPrice = itemView.findViewById(R.id.textview_itemhotelresorts_price);
      imageViewHotel = itemView.findViewById(R.id.appcompatimageview_itemhotelresort_hotelimage);
      textViewHotelname = itemView.findViewById(R.id.textview_itemhotelresort_hotelname);
      textViewHotellocation = itemView.findViewById(R.id.textview_itemhotelresort_hotellocation);
      ratingBarHotel = itemView.findViewById(R.id.raitingbar_itemhotelresorts_raiting);
      buttonBook = itemView.findViewById(R.id.button_itemhotelresort_book);
      linearlayoutImageView = itemView.findViewById(R.id.linearlayout_itemhotelresorts);

      buttonBook.setOnClickListener(this);
      itemView.setOnClickListener(this);
    }

    /**
     * Called when spa item clicked.
     *
     * @param view clicked item view
     */
    @Override
    public void onClick(View view) {
      if (onSearchResultItemClickListener != null && hotelList != null
          && hotelList.size() > 0) {
        onSearchResultItemClickListener.onSearchResultItemClicked(getAdapterPosition(), view,
            hotelList.get(getAdapterPosition()).getHotelName());
      }
    }
  }
}
