/*
 *  Created by Emaar Hospitality Group on 21/9/18 4:44 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 21/9/18 4:44 PM
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
import android.view.ViewGroup;
import android.widget.TextView;
import com.ehg.R;

/**
 * This class initiates Upoint list for Upoint activity class.
 */
public class UpointAdapter extends RecyclerView.Adapter<UpointAdapter.ViewHolder> {

  private final Context context;
  private final LayoutInflater inflater;
  private String[] description = new String[] {
      "Reel Cinemas-Website 16 Sep 2016, 01:44 PM",
      "Reel Cinemas-Website 07 Aug 2016, 01:44 PM",
      "Reel Cinemas-Website 10 Jul 2016, 01:44 PM",
      "Reel Cinemas-Website 11 Aug 2016, 01:44 PM",
      "Reel Cinemas-Website 09 Mar 2016, 01:44 PM",
  };
  private String[] upoints = new String[] {
      "+90",
      "-45",
      "+23",
      "+65",
      "+70",
  };
  private String[] value = new String[] {
      "AED 90",
      "AED-45",
      "AED 23",
      "AED 65",
      "AED 70",
  };

  /**
   * Parameterized constructor for UpointAdapter.
   */
  public UpointAdapter(Context context) {

    this.context = context;
    this.inflater = LayoutInflater.from(context);
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
    return new ViewHolder(inflater.inflate(R.layout.item_upoint,
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

    viewHolder.textViewDescription.setText(description[position]);
    viewHolder.textViewUpoint.setText(upoints[position]);
    viewHolder.textViewValue.setText(value[position]);

  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return description.length;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewDescription;
    private final TextView textViewUpoint;
    private final TextView textViewValue;

    /**
     * ViewHolder constructor.
     *
     * @param itemView required for inflating all components from item layout
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      textViewDescription = (TextView) itemView
          .findViewById(R.id.textview_upointadapter_description);
      textViewUpoint = (TextView) itemView.findViewById(R.id.textview_upointadapter_upoint);
      textViewValue = (TextView) itemView.findViewById(R.id.textview_upointadapter_value);
    }
  }
}
