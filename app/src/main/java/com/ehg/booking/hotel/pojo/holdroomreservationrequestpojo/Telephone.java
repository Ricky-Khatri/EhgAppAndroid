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
 * Telephone.
 */
public class Telephone {

  @SerializedName("PhoneNumber")
  private String phoneNumber;
  @SerializedName("PhoneUseType")
  private String phoneUseType;

  /**
   * Getter method.
   *
   * @return Gets the value of phoneNumber and returns phoneNumber.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the phoneNumber. You can use getPhoneNumber() to get the value of phoneNumber.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of phoneUseType and returns phoneUseType.
   */
  public String getPhoneUseType() {
    return phoneUseType;
  }

  /**
   * Sets the phoneUseType. You can use getPhoneUseType() to get the value of phoneUseType.
   */
  public void setPhoneUseType(String phoneUseType) {
    this.phoneUseType = phoneUseType;
  }
}
