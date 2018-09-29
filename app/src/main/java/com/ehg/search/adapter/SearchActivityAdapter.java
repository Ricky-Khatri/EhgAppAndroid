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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class SearchActivityAdapter extends BaseExpandableListAdapter {

  Context context;

  public SearchActivityAdapter(Context context) {

    this.context = context;
  }

  @Override
  public int getGroupCount() {
    return 0;
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return 0;
  }

  @Override
  public Object getGroup(int groupPosition) {
    return null;
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return null;
  }

  @Override
  public long getGroupId(int groupPosition) {
    return 0;
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return 0;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    return null;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
      ViewGroup parent) {
    return null;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }
}
