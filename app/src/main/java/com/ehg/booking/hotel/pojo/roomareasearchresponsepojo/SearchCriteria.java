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
 * SearchCriteria class.
 */
public class SearchCriteria {

  @SerializedName("Hotels")
  private List<Hotel> hotels;
  @SerializedName("PackageCategories")
  private List<String> packageCategories;
  @SerializedName("RatePlans")
  private List<RatePlan> ratePlans;
  @SerializedName("StarRatings")
  private List<String> starRatings;

  /**
   * Getter method.
   *
   * @return Gets the value of hotels and returns hotels.
   */
  public List<Hotel> getHotels() {
    return hotels;
  }

  /**
   * Sets the hotels. You can use getHotels() to get the value of hotels.
   */
  public void setHotels(
      List<Hotel> hotels) {
    this.hotels = hotels;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packageCategories and returns packageCategories.
   */
  public List<String> getPackageCategories() {
    return packageCategories;
  }

  /**
   * Sets the packageCategories. You can use getPackageCategories() to get the value of
   * packageCategories.
   */
  public void setPackageCategories(List<String> packageCategories) {
    this.packageCategories = packageCategories;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of ratePlans and returns ratePlans.
   */
  public List<RatePlan> getRatePlans() {
    return ratePlans;
  }

  /**
   * Sets the ratePlans. You can use getRatePlans() to get the value of ratePlans.
   */
  public void setRatePlans(
      List<RatePlan> ratePlans) {
    this.ratePlans = ratePlans;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of starRatings and returns starRatings.
   */
  public List<String> getStarRatings() {
    return starRatings;
  }

  /**
   * Sets the starRatings. You can use getStarRatings() to get the value of starRatings.
   */
  public void setStarRatings(List<String> starRatings) {
    this.starRatings = starRatings;
  }
}
