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

package com.ehg.booking.hotel.pojo.roomareasearchpojo;

import com.google.gson.annotations.SerializedName;

/**
 * PriceRange class.
 */
public class PriceRange {

  @SerializedName("MaxRate")
  private int maxRate;
  @SerializedName("MinRate")
  private int minRate;

  /**
   * Getter method.
   *
   * @return Gets the value of maxRate and returns maxRate.
   */
  public int getMaxRate() {
    return maxRate;
  }

  /**
   * Sets the maxRate. You can use getMaxRate() to get the value of maxRate.
   */
  public void setMaxRate(int maxRate) {
    this.maxRate = maxRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of minRate and returns minRate.
   */
  public int getMinRate() {
    return minRate;
  }

  /**
   * Sets the minRate. You can use getMinRate() to get the value of minRate.
   */
  public void setMinRate(int minRate) {
    this.minRate = minRate;
  }
}
