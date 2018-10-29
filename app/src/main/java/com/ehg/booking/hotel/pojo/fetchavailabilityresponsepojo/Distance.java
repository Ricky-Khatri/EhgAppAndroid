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
 * Distance class.
 */
public class Distance {

  @SerializedName("DistanceUnit")
  private String distanceUnit;
  @SerializedName("TotalDistance")
  private TotalDistance totalDistance;

  /**
   * Getter method.
   *
   * @return Gets the value of distanceUnit and returns distanceUnit.
   */
  public String getDistanceUnit() {
    return distanceUnit;
  }

  /**
   * Sets the distanceUnit. You can use getDistanceUnit() to get the value of distanceUnit.
   */
  public void setDistanceUnit(String distanceUnit) {
    this.distanceUnit = distanceUnit;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of totalDistance and returns totalDistance.
   */
  public TotalDistance getTotalDistance() {
    return totalDistance;
  }

  /**
   * Sets the totalDistance. You can use getTotalDistance() to get the value of totalDistance.
   */
  public void setTotalDistance(
      TotalDistance totalDistance) {
    this.totalDistance = totalDistance;
  }
}
