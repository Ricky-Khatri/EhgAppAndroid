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
 * Address
 */
public class Address {

  @SerializedName("Address1")
  private String address1;
  @SerializedName("Address2")
  private String address2;
  @SerializedName("City")
  private String city;
  @SerializedName("CountryCode")
  private String countryCode;
  @SerializedName("CountryName")
  private String countryName;
  @SerializedName("PostalCode")
  private String postalCode;
  @SerializedName("StateCode")
  private String stateCode;
  @SerializedName("StateName")
  private String stateName;

  /**
   * Getter method.
   *
   * @return Gets the value of address1 and returns address1.
   */
  public String getAddress1() {
    return address1;
  }

  /**
   * Sets the address1. You can use getAddress1() to get the value of address1.
   */
  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of address2 and returns address2.
   */
  public String getAddress2() {
    return address2;
  }

  /**
   * Sets the address2. You can use getAddress2() to get the value of address2.
   */
  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of city and returns city.
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city. You can use getCity() to get the value of city.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of countryCode and returns countryCode.
   */
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * Sets the countryCode. You can use getCountryCode() to get the value of countryCode.
   */
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of countryName and returns countryName.
   */
  public String getCountryName() {
    return countryName;
  }

  /**
   * Sets the countryName. You can use getCountryName() to get the value of countryName.
   */
  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of postalCode and returns postalCode.
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * Sets the postalCode. You can use getPostalCode() to get the value of postalCode.
   */
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of stateCode and returns stateCode.
   */
  public String getStateCode() {
    return stateCode;
  }

  /**
   * Sets the stateCode. You can use getStateCode() to get the value of stateCode.
   */
  public void setStateCode(String stateCode) {
    this.stateCode = stateCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of stateName and returns stateName.
   */
  public String getStateName() {
    return stateName;
  }

  /**
   * Sets the stateName. You can use getStateName() to get the value of stateName.
   */
  public void setStateName(String stateName) {
    this.stateName = stateName;
  }
}
