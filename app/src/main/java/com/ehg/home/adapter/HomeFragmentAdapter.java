/*
 * Created by Emaar Hospitality Group on 9/8/18 11:25AM
 * Copyright(C)2018All rights reserved.
 * Last modified 9/8/18 11:25AM
 *
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,software
 * distributed under the License is distributed on an"AS IS"BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ehg.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ehg.R;

/**
 * This class initiates Home fragment list for Home Fragment class.
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> {

  private final Context context;
  private final LayoutInflater inflater;
  private String[] imageUrls = new String[] {
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_1.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_2.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_3.png",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_4.jpg",
      "http://yayandroid.com/data/github_library/parallax_listview/test_image_5.png",
  };

  /**
   * Parameterized constructor for HomeFragmentAdapter.
   */
  public HomeFragmentAdapter(Context context) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
  }

  /**
   * Called to inflate layout item and returns ViewHolder object.
   *
   * @param viewGroup viewGroup object
   * @param i integer position
   * @return returns ViewHolder object
   */
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(inflater.inflate(R.layout.item_home,
        viewGroup, false));
  }

  /**
   * Called to bind data values with viewHolder items.
   *
   * @param viewHolder viewHolder object
   * @param i integer position
   */
  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    viewHolder.recycleViewFeatureHotel.setHasFixedSize(true);
    viewHolder.recycleViewFeatureHotel
        .setLayoutManager(new LinearLayoutManager(this.context,
            LinearLayoutManager.HORIZONTAL, false));
    viewHolder.recycleViewFeatureHotel.setAdapter(
        new HomeFragmentHorizontalItemAdapter(context, imageUrls));

  }

  /**
   * Returns total number of items in adapter.
   *
   * @return itemCount
   */
  @Override
  public int getItemCount() {
    return imageUrls.length;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private final RecyclerView recycleViewFeatureHotel;
    private final TextView textViewFeatureTitle;
    private final TextView textViewShowAll;

    /**
     * ViewHolder constructor.
     *
     * @param itemView required for inflating all components from item layout
     */
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      recycleViewFeatureHotel = itemView.findViewById(R.id.recyclerview_item_home);
      textViewFeatureTitle = itemView.findViewById(R.id.textview_item_home_featuretitle);
      textViewShowAll = itemView.findViewById(R.id.textview_item_home_showall);
    }
  }
}
