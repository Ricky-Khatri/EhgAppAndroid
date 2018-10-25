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

package com.ehg.booking.hotel.pojo.fetchavailabilityrequestpojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * FetchRoomAvailabilityRequestPojo class.
 */
public class FetchRoomAvailabilityRequestPojo {

  @SerializedName("Details")
  private List<Detail> details;
  @SerializedName("Feature")
  private String feature;
  @SerializedName("Operation")
  private String operation;

  /**
   * Getter method.
   *
   * @return Gets the value of details and returns details.
   */
  public List<Detail> getDetails() {
    return details;
  }

  /**
   * Sets the details. You can use getDetails() to get the value of details.
   */
  public void setDetails(
      List<Detail> details) {
    this.details = details;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of feature and returns feature.
   */
  public String getFeature() {
    return feature;
  }

  /**
   * Sets the feature. You can use getFeature() to get the value of feature.
   */
  public void setFeature(String feature) {
    this.feature = feature;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of operation and returns operation.
   */
  public String getOperation() {
    return operation;
  }

  /**
   * Sets the operation. You can use getOperation() to get the value of operation.
   */
  public void setOperation(String operation) {
    this.operation = operation;
  }
}
