/*
 *  Created by Emaar Hospitality Group on 25/9/18 11:40 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 25/9/18 11:40 AM
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.ehg.R;
import com.ehg.ubyemaar.pojo.UserPreferencesPojo;
import java.util.ArrayList;

/**
 * This class held's user preferences list item.
 */
public class UserPreferencesAdapter extends
    RecyclerView.Adapter<UserPreferencesAdapter.ViewHolder> {

  private final Context context;
  private final LayoutInflater inflater;

  private ArrayList<UserPreferencesPojo> userPreferenceList;

  private OnUserPreferenceClickListener onUserPreferenceClickListener;

  /**
   * Parameterized constructor for UpointAdapter.
   */
  public UserPreferencesAdapter(Context context,
      ArrayList<UserPreferencesPojo> userPreferenceList) {

    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.userPreferenceList = userPreferenceList;
    onUserPreferenceClickListener = (OnUserPreferenceClickListener) context;
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
    return new ViewHolder(inflater.inflate(R.layout.item_userpreferences,
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

    UserPreferencesPojo userPreferencesPojo = userPreferenceList.get(position);

    if (userPreferencesPojo.isSelected()) {
      viewHolder.switchButton.setChecked(true);
    } else {
      viewHolder.switchButton.setChecked(false);
    }

    viewHolder.textViewDescription.setText(userPreferencesPojo.getPreferenceName());
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return userPreferenceList != null
        && userPreferenceList.size() > 0 ? userPreferenceList.size() : 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private final TextView textViewDescription;
    private final Switch switchButton;


    /**
     * ViewHolder constructor.
     *
     * @param itemView required for inflating all components from item layout
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      textViewDescription = itemView
          .findViewById(R.id.textview_userpreferences_preferencename);
      switchButton = itemView.findViewById(R.id.switch_userpreferences);
    }

    /**
     * Called when click event initiated by view component.
     *
     * @param view clicked view
     */
    @Override
    public void onClick(View view) {
      if (onUserPreferenceClickListener != null) {
        UserPreferencesPojo userPreferencesPojo = userPreferenceList.get(getAdapterPosition());
        if (userPreferencesPojo.isSelected()) {
          userPreferencesPojo.setSelected(false);
        } else {
          userPreferencesPojo.setSelected(true);
        }
        notifyDataSetChanged();
        onUserPreferenceClickListener.onPreferenceSelect(userPreferencesPojo, getAdapterPosition());
      }
    }
  }

  /**
   * Interface.
   */
  public interface OnUserPreferenceClickListener {

    void onPreferenceSelect(UserPreferencesPojo userPreferencesPojo, int position);
  }
}

