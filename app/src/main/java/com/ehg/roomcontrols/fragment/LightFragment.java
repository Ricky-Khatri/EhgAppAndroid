/*
 *  Created by Emaar Hospitality Group on 23/10/18 11:58 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 23/10/18 11:58 AM
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

package com.ehg.roomcontrols.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.roomcontrols.adapter.LightAdapter;
import com.ehg.roomcontrols.adapter.LightAdapter.OnLightItemClickListener;
import com.ehg.roomcontrols.adapter.SceneAdapter;
import com.ehg.roomcontrols.adapter.SceneAdapter.OnSceneItemClickListener;
import com.ehg.roomcontrols.pojo.LightPojo;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;

/**
 * This class shows available scenes and lights in hotel room.
 */
public class LightFragment extends Fragment implements OnLightItemClickListener,
    OnSceneItemClickListener {

  private Context context;

  private RecyclerView recyclerViewSceneList;
  private RecyclerView recyclerViewLightList;

  private ArrayList<LightPojo> sceneList;
  private ArrayList<LightPojo> lightList;

  /**
   * Called when fragment created.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * Called to inflate fragment view.
   *
   * @param inflater LayoutInflater
   * @param container ViewGroup
   * @param savedInstanceState Bundle
   * @return View
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_light, container, false);

    return view;
  }

  /**
   * Called to instantiate view components of fragment.
   *
   * @param view View
   * @param savedInstanceState Bundle
   */
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    this.context = getActivity();
    initView(view);
  }

  /**
   * Called to attach activity context to fragment.
   *
   * @param context activity context
   */
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  /**
   * Method init's view component of this fragment.
   *
   * @param view view
   */
  private void initView(View view) {

    try {

      initScenes(view);
      initLights(view);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called to init reservation category list.
   *
   * @param view view
   */
  private void initScenes(View view) {
    //Init scene list
    recyclerViewSceneList = view.findViewById(R.id.recyclerview_light_scenelist);
    recyclerViewSceneList.setLayoutManager(new GridLayoutManager(context, 2));

    //Prepare data
    sceneList = new ArrayList<>();
    LightPojo lightPojo = new LightPojo();
    lightPojo.setTitle("Sleep");
    sceneList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Cinema");
    sceneList.add(lightPojo);

    //Set adapter
    SceneAdapter sceneAdapter = new SceneAdapter(context,
        AppUtil.getDeviceHeight((BaseActivity) context), sceneList, this);
    recyclerViewSceneList.setAdapter(sceneAdapter);
    AppUtil.animateRecyclerView(context, recyclerViewSceneList,
        R.anim.layout_animation_from_bottom);
  }

  /**
   * Called to init reservation list.
   *
   * @param view view
   */
  private void initLights(View view) {
    //Init light lis
    recyclerViewLightList = view.findViewById(R.id.recyclerview_light_lightlist);
    recyclerViewLightList.setLayoutManager(new GridLayoutManager(context, 2));

    //Prepare data
    lightList = new ArrayList<>();
    LightPojo lightPojo = new LightPojo();
    lightPojo.setTitle("Bed Left");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Bed Right");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Sofa");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Mini Bar");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Bath");
    lightList.add(lightPojo);
    lightPojo = new LightPojo();
    lightPojo.setTitle("Pantry");
    lightList.add(lightPojo);

    //Set adapter
    LightAdapter lightAdapter = new LightAdapter(context,
        AppUtil.getDeviceHeight((BaseActivity) context), lightList, this);
    recyclerViewLightList.setAdapter(lightAdapter);
    AppUtil.animateRecyclerView(context, recyclerViewLightList,
        R.anim.layout_animation_from_bottom);
  }

  /**
   * Called when light item clicked.
   *
   * @param position item position
   */
  @Override
  public void onLightItemClicked(int position) {

  }

  /**
   * Called when scene item clicked.
   *
   * @param position item position
   */
  @Override
  public void onSceneItemClicked(int position) {

  }
}
