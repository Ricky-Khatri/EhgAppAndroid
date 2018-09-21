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

package com.ehg.book.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.book.pojo.BookPojo;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

  private Context context;
  private LayoutInflater inflater;

  private int itemHeight;

  private ArrayList<BookPojo> bookList;

  /**
   * Parameterized constructor for offer list adapter.
   */
  public BookAdapter(Context context, int itemHeight, ArrayList<BookPojo> bookList) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.itemHeight = itemHeight / 4;
    this.bookList = bookList;
  }

  /**
   * Called to inflate layout item and returns ViewHolder object.
   * @param viewGroup viewGroup object
   * @param position integer position
   * @return returns ViewHolder object
   */
  @Override
  public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
    return new BookAdapter.ViewHolder(inflater.inflate(R.layout.item_book_list,
        viewGroup, false));
  }

  /**
   * Called to bind data values with viewHolder items.
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(final BookAdapter.ViewHolder viewHolder, int position) {

    /*Glide.with(context).load(bookList.get(position).getImageUrl())
        .into(viewHolder.appCompatImageView);*/

    viewHolder.textViewTitle.setText(bookList.get(position).getTitle());
  }

  /**
   * Returns total number of items in adapter.
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

      textViewTitle = view.findViewById(R.id.textview_itembooklist_title);
      relativeLayout = view.findViewById(R.id.relativelayout_itembooklist_list);
      appCompatImageView = view.findViewById(R.id.imageview_itembooklist_image);

      //Set dynamic height of item layout
      relativeLayout.getLayoutParams().height = itemHeight;
    }
  }
}
