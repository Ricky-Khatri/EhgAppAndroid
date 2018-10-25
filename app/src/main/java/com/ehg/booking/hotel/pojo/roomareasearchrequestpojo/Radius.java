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

package com.ehg.booking.hotel.pojo.roomareasearchrequestpojo;

import com.google.gson.annotations.SerializedName;

/**
 * Redius class.
 */
public class Radius {

  @SerializedName("Distance")
  private String distance;
  @SerializedName("UnitOfMeasureCode")
  private int unitOfMeasureCode;

  /**
   * Getter method.
   *
   * @return Gets the value of distance and returns distance.
   */
  public String getDistance() {
    return distance;
  }

  /**
   * Sets the distance. You can use getDistance() to get the value of distance.
   */
  public void setDistance(String distance) {
    this.distance = distance;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of unitOfMeasureCode and returns unitOfMeasureCode.
   */
  public int getUnitOfMeasureCode() {
    return unitOfMeasureCode;
  }

  /**
   * Sets the unitOfMeasureCode. You can use getUnitOfMeasureCode() to get the value of
   * unitOfMeasureCode.
   */
  public void setUnitOfMeasureCode(int unitOfMeasureCode) {
    this.unitOfMeasureCode = unitOfMeasureCode;
  }
}
