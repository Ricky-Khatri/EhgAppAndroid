/*
 *  Created by Emaar Hospitality Group on 10/8/18 5:47 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 10/8/18 5:47 PM
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

package com.ehg.roomcontrols.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Data.
 */
public class Data {

  @Expose
  private Ac ac;
  @SerializedName("call_butler")
  private CallButler callButler;
  @Expose
  private Curtains curtains;
  @Expose
  private Dnd dnd;
  @Expose
  private Door door;
  @Expose
  private Lights lights;
  @SerializedName("lights_panoramic")
  private LightsPanoramic lightsPanoramic;
  @Expose
  private Mur mur;
  @Expose
  private List<Tv> tv;

  /**
   * Getter method.
   *
   * @return Gets the value of ac and returns ac.
   */
  public Ac getAc() {
    return ac;
  }

  /**
   * Sets the ac. You can use getAc() to get the value of ac.
   */
  public void setAc(Ac ac) {
    this.ac = ac;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of callButler and returns callButler.
   */
  public CallButler getCallButler() {
    return callButler;
  }

  /**
   * Sets the callButler. You can use getCallButler() to get the value of callButler.
   */
  public void setCallButler(CallButler callButler) {
    this.callButler = callButler;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of curtains and returns curtains.
   */
  public Curtains getCurtains() {
    return curtains;
  }

  /**
   * Sets the curtains. You can use getCurtains() to get the value of curtains.
   */
  public void setCurtains(Curtains curtains) {
    this.curtains = curtains;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of dnd and returns dnd.
   */
  public Dnd getDnd() {
    return dnd;
  }

  /**
   * Sets the dnd. You can use getDnd() to get the value of dnd.
   */
  public void setDnd(Dnd dnd) {
    this.dnd = dnd;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of door and returns door.
   */
  public Door getDoor() {
    return door;
  }

  /**
   * Sets the door. You can use getDoor() to get the value of door.
   */
  public void setDoor(Door door) {
    this.door = door;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of lights and returns lights.
   */
  public Lights getLights() {
    return lights;
  }

  /**
   * Sets the lights. You can use getLights() to get the value of lights.
   */
  public void setLights(Lights lights) {
    this.lights = lights;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of lightsPanoramic and returns lightsPanoramic.
   */
  public LightsPanoramic getLightsPanoramic() {
    return lightsPanoramic;
  }

  /**
   * Sets the lightsPanoramic. You can use getLightsPanoramic() to get the value of lightsPanoramic.
   */
  public void setLightsPanoramic(LightsPanoramic lightsPanoramic) {
    this.lightsPanoramic = lightsPanoramic;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of mur and returns mur.
   */
  public Mur getMur() {
    return mur;
  }

  /**
   * Sets the mur. You can use getMur() to get the value of mur.
   */
  public void setMur(Mur mur) {
    this.mur = mur;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of tv and returns tv.
   */
  public List<Tv> getTv() {
    return tv;
  }

  /**
   * Sets the tv. You can use getTv() to get the value of tv.
   */
  public void setTv(List<Tv> tv) {
    this.tv = tv;
  }
}
