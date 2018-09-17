/*
 *  Created by Emaar Hospitality Group on 16/8/18 1:43 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 16/8/18 1:41 PM
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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ehg.R;
import java.util.ArrayList;
import java.util.List;

/**
 * This adapter class initiates show more popup window list for HomeActivity.
 */

public class ShowMoreListAdapter extends
    RecyclerView.Adapter<ShowMoreListAdapter.ShowMoreViewHolder> {

  private OnShowMoreListItemClickListener onShowMoreListItemClickListener;

  private Context context;
  private LayoutInflater inflater;

  private List<String> showMoreOptionsList = new ArrayList<>();

  /**
   * ShowMoreListAdapter constructor.
   *
   * @param context context
   */
  public ShowMoreListAdapter(Context context,
      OnShowMoreListItemClickListener onShowMoreListItemClickListener,
      ArrayList<String> showMoreOptionsList) {

    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.onShowMoreListItemClickListener = onShowMoreListItemClickListener;
    this.showMoreOptionsList = showMoreOptionsList;
  }

  /**
   * Called to inflate layout item and returns ViewHolder object.
   * @param viewGroup viewGroup object
   * @param position integer position
   * @return returns ViewHolder object
   */
  @NonNull
  @Override
  public ShowMoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
    return new ShowMoreViewHolder(inflater.inflate(R.layout.item_showmore_list,
        viewGroup, false));
  }

  /**
   * Called to bind data values with viewHolder items.
   * @param showMoreViewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(@NonNull ShowMoreViewHolder showMoreViewHolder, final int position) {

    showMoreViewHolder.textViewList.setText(showMoreOptionsList.get(position));
  }

  /**
   * Returns total number of items in adapter.
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return showMoreOptionsList != null
        && showMoreOptionsList.size() > 0 ? showMoreOptionsList.size() : 0;
  }

  /**
   * This is ViewHolder class.
   */
  class ShowMoreViewHolder extends ViewHolder implements  OnClickListener {

    private final TextView textViewList;

    /**
     * ShowMoreViewHolder constructor.
     *
     * @param itemView itemview
     */
    ShowMoreViewHolder(@NonNull View itemView) {
      super(itemView);

      textViewList = itemView.findViewById(R.id.textview_showmore_list);

      itemView.setOnClickListener(this);
    }

    /**
     * Called when any view clicked.
     * @param view clicked view object
     */
    @Override
    public void onClick(View view) {
      if (onShowMoreListItemClickListener != null) {
        String optionSelected = showMoreOptionsList.get(getAdapterPosition());
        onShowMoreListItemClickListener.onShowMoreListItemClicked(optionSelected);
      }
    }
  }

  /**
   * Show more list item click listener.
   */
  public interface OnShowMoreListItemClickListener {
    void onShowMoreListItemClicked(String optionSelected);
  }
}
