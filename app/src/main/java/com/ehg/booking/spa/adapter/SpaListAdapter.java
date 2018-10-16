/*
 *  Created by Emaar Hospitality Group on 15/10/18 8:09 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 15/10/18 8:09 PM
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

package com.ehg.booking.spa.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.booking.spa.SpaDetailActivity;
import com.ehg.utilities.AppUtil;

public class SpaListAdapter extends RecyclerView.Adapter<SpaListAdapter.ViewHolder> {

  private final LayoutInflater inflater;
  private Context context;

  private OnSpaItemClickListener onSpaItemClickListener;

  /**
   * Parameterized constructor for offer list adapter.
   */
  public SpaListAdapter(Context context, OnSpaItemClickListener onSpaItemClickListener) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.onSpaItemClickListener = onSpaItemClickListener;
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
    return new SpaListAdapter.ViewHolder(inflater.inflate(R.layout.item_spa,
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

    final String name = "THE SPA";
    String address = "Address Downtown, Downtown Dubai";
    String type = "45 Spa Treatments";

    viewHolder.textViewSpaName.setText(name);
    viewHolder.textViewSpaAddress.setText(address);
    viewHolder.textViewSpaType.setText(type);

    viewHolder.itemView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent intent = new Intent(context, SpaDetailActivity.class);
        intent.putExtra("title", name);
        AppUtil.startActivityWithAnimation((AppCompatActivity) context, intent, false);
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
    return 3;
  }

  /**
   * This is ViewHolder class.
   */
  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private final AppCompatImageView appCompatImageView;

    private final TextView textViewSpaName;
    private final TextView textViewSpaAddress;
    private final TextView textViewSpaType;

    private final Button buttonBook;

    /**
     * ViewHolder constructor.
     *
     * @param itemView required for inflating all components from item layout
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      textViewSpaName = itemView.findViewById(R.id.textview_itemspa_spaname);
      textViewSpaAddress = itemView.findViewById(R.id.textview_itemspa_address);
      textViewSpaType = itemView.findViewById(R.id.textview_itemspa_spatype);
      appCompatImageView = itemView.findViewById(R.id.appcompatimageview_itemspa_spaimage);
      buttonBook = itemView.findViewById(R.id.button_itemspa_book);

      buttonBook.setOnClickListener(this);

    }

    /**
     * Called when spa item clicked.
     * @param view clicked item view
     */
    @Override
    public void onClick(View view) {
      if (onSpaItemClickListener != null) {
        onSpaItemClickListener.onSpaItemClicked(getAdapterPosition());
      }
    }
  }

  /**
   * Interface.
   */
  public interface OnSpaItemClickListener {
    void onSpaItemClicked (int position);
  }
}
