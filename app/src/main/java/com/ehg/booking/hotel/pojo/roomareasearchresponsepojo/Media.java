/*
 *  Created by Emaar Hospitality Group on 25/10/18 2:50 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 25/10/18 2:49 PM
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

package com.ehg.booking.hotel.pojo.roomareasearchresponsepojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Media class.
 */
public class Media {

  @SerializedName("PropertyDetailImages")
  private List<PropertyDetailImage> propertyDetailImages;
  @SerializedName("PropertyMainImages")
  private List<PropertyMainImage> propertyMainImages;
  @SerializedName("PropertyMainImgTag")
  private PropertyMainImgTag propertyMainImgTag;

  /**
   * Getter method.
   *
   * @return Gets the value of propertyDetailImages and returns propertyDetailImages.
   */
  public List<PropertyDetailImage> getPropertyDetailImages() {
    return propertyDetailImages;
  }

  /**
   * Sets the propertyDetailImages. You can use getPropertyDetailImages() to get the value of
   * propertyDetailImages.
   */
  public void setPropertyDetailImages(
      List<PropertyDetailImage> propertyDetailImages) {
    this.propertyDetailImages = propertyDetailImages;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of propertyMainImages and returns propertyMainImages.
   */
  public List<PropertyMainImage> getPropertyMainImages() {
    return propertyMainImages;
  }

  /**
   * Sets the propertyMainImages. You can use getPropertyMainImages() to get the value of
   * propertyMainImages.
   */
  public void setPropertyMainImages(
      List<PropertyMainImage> propertyMainImages) {
    this.propertyMainImages = propertyMainImages;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of propertyMainImgTag and returns propertyMainImgTag.
   */
  public PropertyMainImgTag getPropertyMainImgTag() {
    return propertyMainImgTag;
  }

  /**
   * Sets the propertyMainImgTag. You can use getPropertyMainImgTag() to get the value of
   * propertyMainImgTag.
   */
  public void setPropertyMainImgTag(
      PropertyMainImgTag propertyMainImgTag) {
    this.propertyMainImgTag = propertyMainImgTag;
  }
}
