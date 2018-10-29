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
 * Address.
 */
public class Address {

  @SerializedName("AddressLine1")
  private String addressLine1;
  @SerializedName("AddressLine2")
  private String addressLine2;
  @SerializedName("CityName")
  private String cityName;
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
  @SerializedName("UseType")
  private String useType;

  /**
   * Getter method.
   *
   * @return Gets the value of addressLine1 and returns addressLine1.
   */
  public String getAddressLine1() {
    return addressLine1;
  }

  /**
   * Sets the addressLine1. You can use getAddressLine1() to get the value of addressLine1.
   */
  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of addressLine2 and returns addressLine2.
   */
  public String getAddressLine2() {
    return addressLine2;
  }

  /**
   * Sets the addressLine2. You can use getAddressLine2() to get the value of addressLine2.
   */
  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of cityName and returns cityName.
   */
  public String getCityName() {
    return cityName;
  }

  /**
   * Sets the cityName. You can use getCityName() to get the value of cityName.
   */
  public void setCityName(String cityName) {
    this.cityName = cityName;
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

  /**
   * Getter method.
   *
   * @return Gets the value of useType and returns useType.
   */
  public String getUseType() {
    return useType;
  }

  /**
   * Sets the useType. You can use getUseType() to get the value of useType.
   */
  public void setUseType(String useType) {
    this.useType = useType;
  }
}
