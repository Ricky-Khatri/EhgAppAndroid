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
 * LightItem.
 */
public class LightItem {

  @SerializedName("default_image_url")
  private String defaultImageUrl;
  @SerializedName("display_name")
  private String displayName;
  @Expose
  private String id;
  @SerializedName("is_chrome")
  private Boolean isChrome;
  @SerializedName("is_dimmer")
  private Boolean isDimmer;
  @SerializedName("is_spectrume")
  private Boolean isSpectrume;
  @Expose
  private Long order;
  @SerializedName("selected_image_url")
  private String selectedImageUrl;

  /**
   * Getter method.
   *
   * @return Gets the value of defaultImageUrl and returns defaultImageUrl.
   */
  public String getDefaultImageUrl() {
    return defaultImageUrl;
  }

  /**
   * Sets the defaultImageUrl. You can use getDefaultImageUrl() to get the value of defaultImageUrl.
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
   * @return Gets the value of isChrome and returns isChrome.
   */
  public Boolean getChrome() {
    return isChrome;
  }

  /**
   * Sets the isChrome. You can use getChrome() to get the value of isChrome.
   */
  public void setChrome(Boolean chrome) {
    isChrome = chrome;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isDimmer and returns isDimmer.
   */
  public Boolean getDimmer() {
    return isDimmer;
  }

  /**
   * Sets the isDimmer. You can use getDimmer() to get the value of isDimmer.
   */
  public void setDimmer(Boolean dimmer) {
    isDimmer = dimmer;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isSpectrume and returns isSpectrume.
   */
  public Boolean getSpectrume() {
    return isSpectrume;
  }

  /**
   * Sets the isSpectrume. You can use getSpectrume() to get the value of isSpectrume.
   */
  public void setSpectrume(Boolean spectrume) {
    isSpectrume = spectrume;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of order and returns order.
   */
  public Long getOrder() {
    return order;
  }

  /**
   * Sets the order. You can use getOrder() to get the value of order.
   */
  public void setOrder(Long order) {
    this.order = order;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of selectedImageUrl and returns selectedImageUrl.
   */
  public String getSelectedImageUrl() {
    return selectedImageUrl;
  }

  /**
   * Sets the selectedImageUrl. You can use getSelectedImageUrl() to get the value of
   * selectedImageUrl.
   */
  public void setSelectedImageUrl(String selectedImageUrl) {
    this.selectedImageUrl = selectedImageUrl;
  }
}
