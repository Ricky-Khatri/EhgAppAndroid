/*
 *  Created by Emaar Hospitality Group on 29/9/18 2:34 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 29/9/18 2:34 PM
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

package com.ehg.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.EditText;
import com.diegocarloslima.fgelv.lib.FloatingGroupExpandableListView;
import com.diegocarloslima.fgelv.lib.WrapperExpandableListAdapter;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.search.adapter.SearchAdapter;
import com.ehg.search.pojo.SearchChildPojo;
import com.ehg.search.pojo.SearchGroupPojo;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * This class allows to search items like: Hotels, Restaurents, Spa etc.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

  private String[] parent = {"Nearby", "Trending"};
  private String[] child = {"Hotels", "Restaurent", "Spa Treatment", "Round of Golf",
      "AddressDowntown | Hotel", "AddressDubai Marina | Hotel",
      "Aquaba Pool Bar | Restaurent", "Namieli | Restaurent",
      "Vida Downtown | Hotel", "Linkx | Restaurent", "Na3Na3 | Restaurent"};

  private int[] parentImage = {R.drawable.placeholder, R.drawable.placeholder};

  private Context context;
  private FloatingGroupExpandableListView exapandableList;
  private AppCompatImageView imageViewClose;
  private EditText editTextSearch;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState - bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_search);

    context = this;

    initView();
  }

  /**
   * Init's view components of this activity.
   */
  private void initView() {

    exapandableList = findViewById(R.id.expandablelistview_search);
    imageViewClose = findViewById(R.id.appcompatimageview_search_close);
    editTextSearch = findViewById(R.id.edittext_search);

    imageViewClose.setOnClickListener(this);

    List<SearchGroupPojo> srch = setAdapter();

    SearchAdapter adapterSearch = new SearchAdapter(context, srch);
    WrapperExpandableListAdapter wrapperAdapter = new WrapperExpandableListAdapter(adapterSearch);
    exapandableList.setAdapter(wrapperAdapter);

    for (int i = 0; i < wrapperAdapter.getGroupCount(); i++) {
      exapandableList.expandGroup(i);
    }

  }

  private List<SearchGroupPojo> setAdapter() {

    List<SearchGroupPojo> groupList = new ArrayList<>();
    List<SearchChildPojo> childList;
    int k = 0;
    for (int i = 0; i < parent.length; i++) {

      childList = new ArrayList<>();
      SearchGroupPojo groupPojo = new SearchGroupPojo();

      groupPojo.setParentName(parent[i]);
      groupPojo.setImageName(parentImage[i]);

      if (childList.size() > 0) {
        childList.clear();
      }
      for (; k < child.length; k++) {

        SearchChildPojo childPojo = new SearchChildPojo();

        childPojo.setChildName(child[k]);

        childList.add(childPojo);

        if (child[k].equalsIgnoreCase("Round of Golf")) {
          k++;
          break;
        }


      }
      groupPojo.setChildArray(childList);
      groupList.add(groupPojo);

    }

    return groupList;
  }

  /**
   * This method Called when a view has been clicked.
   *
   * @param view - clicked view object
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {

      case R.id.appcompatimageview_search_close:

        AppUtil.finishActivityWithAnimation(this);
        break;

      default:

    }
  }
}
