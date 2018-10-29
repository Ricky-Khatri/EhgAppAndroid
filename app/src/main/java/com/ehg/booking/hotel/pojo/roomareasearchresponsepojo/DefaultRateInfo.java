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
 * DefaultRateInfo class.
 */
public class DefaultRateInfo {

  @SerializedName("RatePlanId")
  private String ratePlanId;
  @SerializedName("RateType")
  private String rateType;

  /**
   * Getter method.
   *
   * @return Gets the value of ratePlanId and returns ratePlanId.
   */
  public String getRatePlanId() {
    return ratePlanId;
  }

  /**
   * Sets the ratePlanId. You can use getRatePlanId() to get the value of ratePlanId.
   */
  public void setRatePlanId(String ratePlanId) {
    this.ratePlanId = ratePlanId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of rateType and returns rateType.
   */
  public String getRateType() {
    return rateType;
  }

  /**
   * Sets the rateType. You can use getRateType() to get the value of rateType.
   */
  public void setRateType(String rateType) {
    this.rateType = rateType;
  }
}
