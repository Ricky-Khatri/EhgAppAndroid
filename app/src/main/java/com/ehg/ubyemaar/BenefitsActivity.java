/*
 *  Created by Emaar Hospitality Group on 6/10/18 2:24 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 6/10/18 2:24 PM
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

package com.ehg.ubyemaar;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.ubyemaar.adapter.BenefitsAdapter;
import com.ehg.ubyemaar.adapter.BenefitsAdapter.OnItemClickListener;
import com.ehg.ubyemaar.adapter.UpointAdapter;
import com.ehg.ubyemaar.pojo.BenefitsPojo;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;

/**
 * This class shows tier benefits for U By Emaar members.
 */
public class BenefitsActivity extends BaseActivity implements OnItemClickListener {

  private AppCompatImageView imageViewBackArrow;
  private RecyclerView recyclerViewBenefitsList;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_benifites);

    try {
      initView();
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init view components of screen.
   */
  private void initView() {

    recyclerViewBenefitsList = findViewById(R.id.recyclerview_benefits_benefitslist);
    //Prepare data
    ArrayList<BenefitsPojo> benefitsList = new ArrayList<>();
    BenefitsPojo benefitsPojo = new BenefitsPojo();
    benefitsPojo.setTitle("DINE:");
    benefitsPojo.setDescription("10% Tier savings on your dining bill");
    benefitsList.add(benefitsPojo);
    benefitsPojo = new BenefitsPojo();
    benefitsPojo.setTitle("STAY:");
    benefitsPojo.setDescription("Book direct and enjoy 10% savings on hotel stays");
    benefitsList.add(benefitsPojo);

    LinearLayoutManager manager = new LinearLayoutManager(this,
        LinearLayoutManager.VERTICAL, false);
    recyclerViewBenefitsList.setLayoutManager(manager);
    recyclerViewBenefitsList.setHasFixedSize(true);
    BenefitsAdapter benefitsAdapter = new BenefitsAdapter(this, benefitsList, this);
    recyclerViewBenefitsList.setAdapter(benefitsAdapter);
    AppUtil.animateRecyclerView(this,
        recyclerViewBenefitsList, R.anim.layout_animation_from_bottom);

    TextView textViewheaderTitle = findViewById(R.id.textview_header_title);
    textViewheaderTitle.setText(getResources().getString(R.string.ubyemaar_benefitslabel));

    imageViewBackArrow = findViewById(R.id.imageview_header_back);
    //Set OnClickListener
    imageViewBackArrow.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        AppUtil.finishActivityWithAnimation(BenefitsActivity.this);
      }
    });
  }

  /**
   * Called when benefits list item clicked.
   * @param position clicked item position
   */
  @Override
  public void onItemClick(int position) {

  }

  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();
    if (imageViewBackArrow != null) {
      setBackArrowRtl(imageViewBackArrow);
    }
  }

  /**
   * Called to update back arrow rtl icons.
   *
   * @param appCompatImageView imageview object
   */
  @Override
  public void setBackArrowRtl(AppCompatImageView appCompatImageView) {
    super.setBackArrowRtl(appCompatImageView);
  }

  /**
   * OnKeyDown callback will be called when phone back key pressed.
   *
   * @param keyCode keycode
   * @param event event
   * @return return boolean value
   */
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      AppUtil.finishActivityWithAnimation(this);
    }
    return super.onKeyDown(keyCode, event);
  }
}
