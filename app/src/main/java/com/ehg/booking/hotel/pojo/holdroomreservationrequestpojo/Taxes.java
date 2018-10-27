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

package com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo;

import com.google.gson.annotations.SerializedName;

/**
 * Taxes.
 */
public class Taxes {

  @SerializedName("ResortFee")
  private int resortFee;
  @SerializedName("ServiceCharge")
  private int serviceCharge;

  /**
   * Getter method.
   *
   * @return Gets the value of resortFee and returns resortFee.
   */
  public int getResortFee() {
    return resortFee;
  }

  /**
   * Sets the resortFee. You can use getResortFee() to get the value of resortFee.
   */
  public void setResortFee(int resortFee) {
    this.resortFee = resortFee;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of serviceCharge and returns serviceCharge.
   */
  public int getServiceCharge() {
    return serviceCharge;
  }

  /**
   * Sets the serviceCharge. You can use getServiceCharge() to get the value of serviceCharge.
   */
  public void setServiceCharge(int serviceCharge) {
    this.serviceCharge = serviceCharge;
  }
}
