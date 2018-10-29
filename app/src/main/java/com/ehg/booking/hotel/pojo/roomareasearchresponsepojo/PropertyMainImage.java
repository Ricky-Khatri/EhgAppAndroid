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

/**
 * PropertyMainImage class.
 */
public class PropertyMainImage {

  @SerializedName("SortOrder")
  private int sortOrder;
  @SerializedName("Source")
  private String source;
  @SerializedName("Type")
  private String type;

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

  /**
   * Getter method.
   *
   * @return Gets the value of source and returns source.
   */
  public String getSource() {
    return source;
  }

  /**
   * Sets the source. You can use getSource() to get the value of source.
   */
  public void setSource(String source) {
    this.source = source;
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
