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
 * GuestCount.
 */
public class GuestCount {

  @SerializedName("Age")
  private String age;
  @SerializedName("AgeQualifyingCode")
  private String ageQualifyingCode;
  @SerializedName("Count")
  private String count;

  /**
   * Getter method.
   *
   * @return Gets the value of age and returns age.
   */
  public String getAge() {
    return age;
  }

  /**
   * Sets the age. You can use getAge() to get the value of age.
   */
  public void setAge(String age) {
    this.age = age;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of ageQualifyingCode and returns ageQualifyingCode.
   */
  public String getAgeQualifyingCode() {
    return ageQualifyingCode;
  }

  /**
   * Sets the ageQualifyingCode. You can use getAgeQualifyingCode() to get the value of
   * ageQualifyingCode.
   */
  public void setAgeQualifyingCode(String ageQualifyingCode) {
    this.ageQualifyingCode = ageQualifyingCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of count and returns count.
   */
  public String getCount() {
    return count;
  }

  /**
   * Sets the count. You can use getCount() to get the value of count.
   */
  public void setCount(String count) {
    this.count = count;
  }
}
