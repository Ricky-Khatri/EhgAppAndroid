/*
 *  Created by Emaar Hospitality Group on 6/10/18 2:40 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 6/10/18 2:40 PM
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

package com.ehg.ubyemaar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.ubyemaar.pojo.BenefitsPojo;
import java.util.ArrayList;

/**
 * This class shows benefits item list.
 */
public class BenefitsAdapter extends RecyclerView.Adapter<BenefitsAdapter.ViewHolder> {

  private ArrayList<BenefitsPojo> benefitsList = null;

  private Context context;

  private OnItemClickListener onItemClickListener;

  /**
   * This is parametrized constructor of this adapter class.
   */
  public BenefitsAdapter(Context context,
      ArrayList<BenefitsPojo> benefitsList, OnItemClickListener onItemClickListener) {
    this.context = context;
    this.benefitsList = benefitsList;
    this.onItemClickListener = onItemClickListener;
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
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_benefitslist, viewGroup, false);

    ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  /**
   * Called to bind data values with viewHolder items.
   *
   * @param viewHolder viewHolder object
   * @param position integer position
   */
  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

    BenefitsPojo benefitsPojo = benefitsList.get(position);

    viewHolder.textViewTitle.setText(benefitsPojo.getTitle());
    viewHolder.textViewDescription.setText(benefitsPojo.getDescription());
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return benefitsList != null ? benefitsList.size() : 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    // each data item is just a string in this case
    public TextView textViewTitle;
    public TextView textViewDescription;

    public View itemView;

    /**
     * Constructor of ViewHolder class.
     */
    public ViewHolder(View view) {
      super(view);
      itemView = view;
      textViewTitle = view.findViewById(R.id.textview_benefitstitle);
      textViewDescription= view.findViewById(R.id.textview_benefitsdescription);
      itemView.setOnClickListener(this);
    }

    /**
     * Called when view clicked.
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {

      //AppUtil.clickAnimation(view);

      if (onItemClickListener != null) {
        onItemClickListener.onItemClick(getAdapterPosition());
      }
    }
  }

  /**
   * OnItemClick interface for SelectLanguage Recycler view.
   */
  public interface OnItemClickListener {

    void onItemClick(int position);
  }
}

