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
 * ResponseCategory.
 */
public class RoomCategory {

  @SerializedName("CategoryCode")
  private String categoryCode;
  @SerializedName("CategoryName")
  private String categoryName;
  @SerializedName("SortOrder")
  private int sortOrder;

  /**
   * Getter method.
   *
   * @return Gets the value of categoryCode and returns categoryCode.
   */
  public String getCategoryCode() {
    return categoryCode;
  }

  /**
   * Sets the categoryCode. You can use getCategoryCode() to get the value of categoryCode.
   */
  public void setCategoryCode(String categoryCode) {
    this.categoryCode = categoryCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of categoryName and returns categoryName.
   */
  public String getCategoryName() {
    return categoryName;
  }

  /**
   * Sets the categoryName. You can use getCategoryName() to get the value of categoryName.
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of sortOrder and returns sortOrder.
   */
  public int getSortOrder() {
    return sortOrder;
  }

  /**
   * Sets the sortOrder. You can use getSortOrder() to get the value of sortOrder.
   */
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
}
