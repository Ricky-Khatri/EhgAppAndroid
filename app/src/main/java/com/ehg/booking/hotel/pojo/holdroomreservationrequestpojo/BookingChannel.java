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
 * BookingChannel.
 */
public class BookingChannel {

  @SerializedName("Code")
  private String code;
  @SerializedName("CompanyShortName")
  private String companyShortName;
  @SerializedName("Division")
  private String division;
  @SerializedName("Type")
  private String type;

  /**
   * Getter method.
   *
   * @return Gets the value of code and returns code.
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets the code. You can use getCode() to get the value of code.
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of companyShortName and returns companyShortName.
   */
  public String getCompanyShortName() {
    return companyShortName;
  }

  /**
   * Sets the companyShortName. You can use getCompanyShortName() to get the value of
   * companyShortName.
   */
  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of division and returns division.
   */
  public String getDivision() {
    return division;
  }

  /**
   * Sets the division. You can use getDivision() to get the value of division.
   */
  public void setDivision(String division) {
    this.division = division;
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
