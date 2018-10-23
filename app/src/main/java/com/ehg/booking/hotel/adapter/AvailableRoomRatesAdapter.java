/*
 *  Created by Emaar Hospitality Group on 23/10/18 5:38 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 23/10/18 5:38 PM
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
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.ehg.R;
import com.ehg.customview.TextViewSpannable;

public class AvailableRoomRatesAdapter extends RecyclerView.Adapter<AvailableRoomRatesAdapter.ViewHolder> {


  private final Context context;
  private final OnRoomRatesItemClicklistner onRoomRatesListner;

  public AvailableRoomRatesAdapter(Context context, OnRoomRatesItemClicklistner roomRatesItemClicklistner) {

    this.context = context;

    onRoomRatesListner = roomRatesItemClicklistner;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_availableroomrates, viewGroup, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

  }

  @Override
  public int getItemCount() {
    return 5;
  }

  /**
   * OnItemClick interface for Select item Recycler view.
   */
  public interface OnRoomRatesItemClicklistner {

    void onItemClick(int position, View view);
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private final TextView textViewRateTitle;
    private final TextView textViewroomDetail;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      textViewRateTitle = itemView.findViewById(R.id.textview_itemavailableroomrates_ratestitle);
      textViewroomDetail = itemView.findViewById(R.id.textview_itemavailableroomrates_detail);
    }

    @Override
    public void onClick(View view) {

      if (onRoomRatesListner != null) {
        onRoomRatesListner.onItemClick(getAdapterPosition(), view);
        notifyDataSetChanged();
      }
    }
  }

}
