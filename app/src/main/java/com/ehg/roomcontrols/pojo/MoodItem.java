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

/**
 * MoodItem.
 */
public class MoodItem {

  @SerializedName("default_image_url")
  private String defaultImageUrl;
  @SerializedName("display_name")
  private String displayName;
  @Expose
  private String id;
  @SerializedName("is_sleep")
  private Boolean isSleep;
  @Expose
  private int order;
  @SerializedName("selected_image_url")
  private Object selectedImageUrl;
  @SerializedName("stop_feature")
  private Boolean stopFeature;

  /**
   * Getter method.
   *
   * @return Gets the value of defaultImageUrl and returns defaultImageUrl.
   */
  public String getDefaultImageUrl() {
    return defaultImageUrl;
  }

  /**
   * Sets the defaultImageUrl. You can use getDefaultImageUrl() to get the value of
   * defaultImageUrl.
   */
  public void setDefaultImageUrl(String defaultImageUrl) {
    this.defaultImageUrl = defaultImageUrl;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of displayName and returns displayName.
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Sets the displayName. You can use getDisplayName() to get the value of displayName.
   */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of id and returns id.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id. You can use getId() to get the value of id.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isSleep and returns isSleep.
   */
  public Boolean getSleep() {
    return isSleep;
  }

  /**
   * Sets the isSleep. You can use getSleep() to get the value of isSleep.
   */
  public void setSleep(Boolean sleep) {
    isSleep = sleep;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of order and returns order.
   */
  public int getOrder() {
    return order;
  }

  /**
   * Sets the order. You can use getOrder() to get the value of order.
   */
  public void setOrder(int order) {
    this.order = order;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of selectedImageUrl and returns selectedImageUrl.
   */
  public Object getSelectedImageUrl() {
    return selectedImageUrl;
  }

  /**
   * Sets the selectedImageUrl. You can use getSelectedImageUrl() to get the value of
   * selectedImageUrl.
   */
  public void setSelectedImageUrl(Object selectedImageUrl) {
    this.selectedImageUrl = selectedImageUrl;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of stopFeature and returns stopFeature.
   */
  public Boolean getStopFeature() {
    return stopFeature;
  }

  /**
   * Sets the stopFeature. You can use getStopFeature() to get the value of stopFeature.
   */
  public void setStopFeature(Boolean stopFeature) {
    this.stopFeature = stopFeature;
  }
}
