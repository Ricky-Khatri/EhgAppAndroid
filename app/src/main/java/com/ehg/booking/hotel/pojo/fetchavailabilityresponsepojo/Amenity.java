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
 * Amenity class.
 */
public class Amenity {

  @SerializedName("Id")
  private int id;
  @SerializedName("Image")
  private Image image;
  @SerializedName("Name")
  private String name;
  @SerializedName("Premium")
  private Boolean premium;
  @SerializedName("Sort")
  private int sort;

  /**
   * Getter method.
   *
   * @return Gets the value of id and returns id.
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the id. You can use getId() to get the value of id.
   */
  public void setId(int id) {
    this.id = id;
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
   * @return Gets the value of name and returns name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name. You can use getName() to get the value of name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of premium and returns premium.
   */
  public Boolean getPremium() {
    return premium;
  }

  /**
   * Sets the premium. You can use getPremium() to get the value of premium.
   */
  public void setPremium(Boolean premium) {
    this.premium = premium;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of sort and returns sort.
   */
  public int getSort() {
    return sort;
  }

  /**
   * Sets the sort. You can use getSort() to get the value of sort.
   */
  public void setSort(int sort) {
    this.sort = sort;
  }
}
