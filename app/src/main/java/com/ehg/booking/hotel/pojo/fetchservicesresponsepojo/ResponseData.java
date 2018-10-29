/*
 *  Created by Emaar Hospitality Group on 17/10/18 6:00 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 17/10/18 6:00 PM
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

package com.ehg.booking.hotel.pojo.fetchservicesresponsepojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * ResponseData.
 */
public class ResponseData {

  @SerializedName("ServiceDetails")
  private List<ServiceDetail> serviceDetails;

  /**
   * Getter method.
   *
   * @return Gets the value of serviceDetails and returns serviceDetails.
   */
  public List<ServiceDetail> getServiceDetails() {
    return serviceDetails;
  }

  /**
   * Sets the serviceDetails. You can use getServiceDetails() to get the value of serviceDetails.
   */
  public void setServiceDetails(
      List<ServiceDetail> serviceDetails) {
    this.serviceDetails = serviceDetails;
  }
}
