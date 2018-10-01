/*
 *  Created by Emaar Hospitality Group on 29/9/18 5:54 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 29/9/18 5:54 PM
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

package com.ehg.search.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.search.pojo.SearchGroupPojo;
import java.util.List;
import java.util.Objects;

/**
 * This class search list search activity class.
 */
public class SearchActivityAdapter extends BaseExpandableListAdapter {

  private Context context;

  private List<SearchGroupPojo> listGroup;

  /**
   * Parameterized constructor for SearchActivityAdapter.
   */
  public SearchActivityAdapter(Context context, List<SearchGroupPojo> listGroup) {

    this.context = context;

    this.listGroup = listGroup;
  }

  /**
   * Gets the number of groups.
   *
   * @return - size of groups
   */
  @Override
  public int getGroupCount() {
    return this.listGroup.size();
  }

  /**
   * Gets the number of children in a specified group.
   *
   * @param groupPosition  - the position of the group for which the children
   *            count should be returned
   *
   * @return - the children count in the specified group
   */
  @Override
  public int getChildrenCount(int groupPosition) {
    int size = 0;
    if (this.listGroup.get(groupPosition).getChildArray() != null) {
      size = this.listGroup.get(groupPosition).getChildArray().size();
    }
    return size;
  }

  /**
   * Gets the data associated with the given group.
   *
   * @param groupPosition the position of the group
   * @return - the data child for the specified group
   */
  @Override
  public Object getGroup(int groupPosition) {
    return this.listGroup.get(groupPosition);
  }

  /**
   * Gets the data associated with the given child within the given group.
   *
   * @param groupPosition the position of the group that the child resides in
   * @param childPosition the position of the child with respect to other
   *            children in the group
   * @return the data of the child
   */
  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return this.listGroup.get(groupPosition).getChildArray().get(childPosition);
  }

  /**
   * Gets the ID for the group at the given position. This group ID must be
   * unique across groups. The combined ID (see
   * {@link #getCombinedGroupId(long)}) must be unique across ALL items
   * (groups and all children).
   *
   * @param groupPosition the position of the group for which the ID is wanted
   * @return the ID associated with the group
   */
  @Override
  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  /**
   * Gets the ID for the given child within the given group. This ID must be
   * unique across all children within the group. The combined ID (see
   * {@link #getCombinedChildId(long, long)}) must be unique across ALL items
   * (groups and all children).
   *
   * @param groupPosition the position of the group that contains the child
   * @param childPosition the position of the child within the group for which
   *            the ID is wanted
   * @return the ID associated with the child
   */
  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  /**
   * Indicates whether the child and group IDs are stable across changes to the
   * underlying data.
   *
   * @return whether or not the same ID always refers to the same object
   * @see Adapter#hasStableIds()
   */
  @Override
  public boolean hasStableIds() {
    return false;
  }

  /**
   * Gets a View that displays the given group. This View is only for the
   * group--the Views for the group's children will be fetched using
   * {@link #getChildView(int, int, boolean, View, ViewGroup)}.
   *
   * @param groupPosition the position of the group for which the View is
   *            returned
   * @param isExpanded whether the group is expanded or collapsed
   * @param convertView the old view to reuse, if possible. You should check
   *            that this view is non-null and of an appropriate type before
   *            using. If it is not possible to convert this view to display
   *            the correct data, this method can create a new view. It is not
   *            guaranteed that the convertView will have been previously
   *            created by
   *            {@link #getGroupView(int, boolean, View, ViewGroup)}.
   * @param parent the parent that this view will eventually be attached to
   * @return the View corresponding to the group at the specified position
   */
  @Override
  public View getGroupView(int groupPosition, boolean isExpanded,
      View convertView, ViewGroup parent) {

    if (convertView == null) {
      LayoutInflater layoutInflater = (LayoutInflater)
          context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = Objects.requireNonNull(layoutInflater)
          .inflate(R.layout.item_searchparent, parent, false);
    }

    TextView textViewParent = convertView.findViewById(R.id.textview_itemsearchparent_title);
    AppCompatImageView iconImageView = convertView.findViewById(R.id.imageview_itemsearch);

    textViewParent.setText(this.listGroup.get(groupPosition).getParentName());
    iconImageView.setImageResource(this.listGroup.get(groupPosition).getImageName());

    return convertView;
  }


  /**
   * Gets a View that displays the data for the given child within the given
   * group.
   *
   * @param groupPosition the position of the group that contains the child
   * @param childPosition the position of the child (for which the View is
   * returned) within the group
   * @param isLastChild Whether the child is the last child within the group
   * @param convertView the old view to reuse, if possible. You should check
   * that this view is non-null and of an appropriate type before
   * using. If it is not possible to convert this view to display
   * the correct data, this method can create a new view. It is not
   * guaranteed that the convertView will have been previously
   * created by
   * {@link #getChildView(int, int, boolean, View, ViewGroup)}.
   * @param parent the parent that this view will eventually be attached to
   * @return the View corresponding to the child at the specified position
   */
  @Override
  public View getChildView(int groupPosition, int childPosition,
      boolean isLastChild, View convertView,
      ViewGroup parent) {

    final ViewHolder holder;
    View view = convertView;

    if (view == null) {
      LayoutInflater layoutInflater = (LayoutInflater)
          context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = Objects.requireNonNull(layoutInflater)
          .inflate(R.layout.item_searchchild, parent, false);
      holder = new ViewHolder();

      holder.textViewChild = view.findViewById(R.id.textview_itemsearchchild_title);

      view.setTag(holder);

    } else {
      holder = (ViewHolder) view.getTag();
    }

    holder.textViewChild.setText(this.listGroup.get(groupPosition)
        .getChildArray().get(childPosition).getChildName());

    return view;
  }

  /**
   * Whether the child at the specified position is selectable.
   *
   * @param groupPosition the position of the group that contains the child
   * @param childPosition the position of the child within the group
   * @return whether the child is selectable.
   */
  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }

  /**
   * ViewHolder Class.
   */
  public class ViewHolder {

    TextView textViewChild;

  }
}
