/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:39 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 19/9/18 2:01 PM
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

package com.ehg.booking.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.pojo.BookingPojo;
import com.ehg.booking.spa.SpaActivity;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

  private Context context;
  private LayoutInflater inflater;

  private int itemHeight;

  private ArrayList<BookingPojo> bookList;

  /**
   * Parameterized constructor for offer list adapter.
   */
  public BookingAdapter(Context context, int itemHeight, ArrayList<BookingPojo> bookList) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.itemHeight = itemHeight / 4 - 20;
    this.bookList = bookList;
  }

  /**
   * Called to inflate layout item and returns ViewHolder object.
   *
   * @param viewGroup viewGroup object
   * @param position integer position
   * @return returns ViewHolder object
   */
  @Override
  public BookingAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
    return new BookingAdapter.ViewHolder(inflater.inflate(R.layout.item_booking_list,
        viewGroup, false));
  }

  /**
   * Called to bind data values with viewHolder items.
   *
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(final BookingAdapter.ViewHolder viewHolder, final int position) {

    /*Glide.with(context).load(bookList.get(position).getImageUrl())
        .into(viewHolder.appCompatImageView);*/

    viewHolder.textViewTitle.setText(bookList.get(position).getTitle());

    viewHolder.relativeLayout.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        switch (position) {

          case 0:

            break;
          case 1:

            break;

          case 2:

            Intent intent = new Intent(context, SpaActivity.class);
            intent.putExtra("spaTreatment", bookList.get(position).getTitle());
            AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, false);

            break;

          case 3:

            break;

          case 4:

            break;
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
    return bookList != null && bookList.size() > 0 ? bookList.size() : 0;
  }

  /**
   * This is ViewHolder class.
   */
  public class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewTitle;

    private final AppCompatImageView appCompatImageView;

    private final RelativeLayout relativeLayout;

    /**
     * ViewHolder constructor.
     *
     * @param view required for inflating all components from item layout
     */
    public ViewHolder(View view) {
      super(view);

      textViewTitle = view.findViewById(R.id.textview_itembookinglist_title);
      relativeLayout = view.findViewById(R.id.relativelayout_itembookinglist_list);
      appCompatImageView = view.findViewById(R.id.imageview_itembookinglist_image);

      //Set dynamic height of item layout
      relativeLayout.getLayoutParams().height = itemHeight;
    }
  }
}
