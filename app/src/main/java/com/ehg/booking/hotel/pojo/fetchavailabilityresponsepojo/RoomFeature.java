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

package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.SerializedName;

/**
 * RoomFeature.
 */
public class RoomFeature {

  @SerializedName("AmenityName")
  private String amenityName;
  @SerializedName("Image")
  private Image image;
  @SerializedName("Quantity")
  private int quantity;
  @SerializedName("Type")
  private String type;

  /**
   * Getter method.
   *
   * @return Gets the value of amenityName and returns amenityName.
   */
  public String getAmenityName() {
    return amenityName;
  }

  /**
   * Sets the amenityName. You can use getAmenityName() to get the value of amenityName.
   */
  public void setAmenityName(String amenityName) {
    this.amenityName = amenityName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of image and returns image.
   */
  public Image getImage() {
    return image;
  }

  /**
   * Sets the image. You can use getImage() to get the value of image.
   */
  public void setImage(Image image) {
    this.image = image;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of quantity and returns quantity.
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity. You can use getQuantity() to get the value of quantity.
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of type and returns type.
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type. You can use getType() to get the value of type.
   */
  public void setType(String type) {
    this.type = type;
  }
}
