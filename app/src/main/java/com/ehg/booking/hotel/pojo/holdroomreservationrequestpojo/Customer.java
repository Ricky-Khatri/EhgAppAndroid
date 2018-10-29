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
import java.util.List;

/**
 * Customer.
 */
public class Customer {

  @SerializedName("Address")
  private List<Address> address;
  @SerializedName("CompanyShortName")
  private String companyShortName;
  @SerializedName("Email")
  private String email;
  @SerializedName("GivenName")
  private String givenName;
  @SerializedName("NamePrefix")
  private String namePrefix;
  @SerializedName("SurName")
  private String surName;
  @SerializedName("Telephone")
  private List<Telephone> telephone;

  /**
   * Getter method.
   *
   * @return Gets the value of address and returns address.
   */
  public List<Address> getAddress() {
    return address;
  }

  /**
   * Sets the address. You can use getAddress() to get the value of address.
   */
  public void setAddress(
      List<Address> address) {
    this.address = address;
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
   * @return Gets the value of email and returns email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email. You can use getEmail() to get the value of email.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of givenName and returns givenName.
   */
  public String getGivenName() {
    return givenName;
  }

  /**
   * Sets the givenName. You can use getGivenName() to get the value of givenName.
   */
  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of namePrefix and returns namePrefix.
   */
  public String getNamePrefix() {
    return namePrefix;
  }

  /**
   * Sets the namePrefix. You can use getNamePrefix() to get the value of namePrefix.
   */
  public void setNamePrefix(String namePrefix) {
    this.namePrefix = namePrefix;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of surName and returns surName.
   */
  public String getSurName() {
    return surName;
  }

  /**
   * Sets the surName. You can use getSurName() to get the value of surName.
   */
  public void setSurName(String surName) {
    this.surName = surName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of telephone and returns telephone.
   */
  public List<Telephone> getTelephone() {
    return telephone;
  }

  /**
   * Sets the telephone. You can use getTelephone() to get the value of telephone.
   */
  public void setTelephone(
      List<Telephone> telephone) {
    this.telephone = telephone;
  }
}
