/*
 * Created by Emaar Hospitality Group on 8/8/18 11:41 AM
 * Copyright (C) 2018.  All rights reserved.
 * Last modified 8/8/18 11:41 AM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ehg.language.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.language.pojo.LanguagePojo;
import java.util.ArrayList;

/**
 * This class creates list items and feed to RecyclerView.
 */

public class SelectLanguageListAdapter extends
    RecyclerView.Adapter<SelectLanguageListAdapter.ViewHolder> {

  private ArrayList<LanguagePojo> languageList = null;

  private Context context;

  private OnItemClickListener onItemClickListener;

  /**
   * This is parametrized constructor of this adapter class.
   */
  public SelectLanguageListAdapter(Context context,
      ArrayList<LanguagePojo> languageList, OnItemClickListener onItemClickListener) {
    this.context = context;
    this.languageList = languageList;
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
        .inflate(R.layout.item_selectlanguage, viewGroup, false);

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

    LanguagePojo languagePojo = languageList.get(position);
    String languageName = languagePojo.getLanguageName();
    String languageCode = languagePojo.getLanguageCode();

    String selectedLanguageCode = SharedPreferenceUtils.getInstance(context)
        .getStringValue(SharedPreferenceUtils.APP_LANGUAGE, "");

    viewHolder.textViewLanguageName.setText(languageName);

    if (selectedLanguageCode.equalsIgnoreCase(languageCode)) {
      languagePojo.setLanguageSelected(true);
      viewHolder.checkbox.setChecked(true);
    } else {
      languagePojo.setLanguageSelected(false);
      viewHolder.checkbox.setChecked(false);
    }
  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return languageList != null ? languageList.size() : 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    // each data item is just a string in this case
    public TextView textViewLanguageName;

    public CheckBox checkbox;

    public View itemView;

    /**
     * Constructor of ViewHolder class.
     */
    public ViewHolder(View view) {
      super(view);
      itemView = view;
      textViewLanguageName = view.findViewById(R.id.textview_selectlanguage_language);
      checkbox = view.findViewById(R.id.checkbox_selectlanguage_languagechekbox);

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
        checkbox.setChecked(true);
        notifyDataSetChanged();
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
