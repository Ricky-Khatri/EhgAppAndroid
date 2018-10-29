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
 * TaxItem.
 */
public class TaxItem {

  @SerializedName("Currency")
  private String currency;
  @SerializedName("TaxAmt")
  private String taxAmt;
  @SerializedName("TaxChrgFreq")
  private String taxChrgFreq;
  @SerializedName("TaxDate")
  private String taxDate;
  @SerializedName("TaxDetType")
  private String taxDetType;
  @SerializedName("TaxName")
  private String taxName;
  @SerializedName("TaxRate")
  private String taxRate;
  @SerializedName("TaxSrc")
  private String taxSrc;

  /**
   * Getter method.
   *
   * @return Gets the value of currency and returns currency.
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets the currency. You can use getCurrency() to get the value of currency.
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxAmt and returns taxAmt.
   */
  public String getTaxAmt() {
    return taxAmt;
  }

  /**
   * Sets the taxAmt. You can use getTaxAmt() to get the value of taxAmt.
   */
  public void setTaxAmt(String taxAmt) {
    this.taxAmt = taxAmt;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxChrgFreq and returns taxChrgFreq.
   */
  public String getTaxChrgFreq() {
    return taxChrgFreq;
  }

  /**
   * Sets the taxChrgFreq. You can use getTaxChrgFreq() to get the value of taxChrgFreq.
   */
  public void setTaxChrgFreq(String taxChrgFreq) {
    this.taxChrgFreq = taxChrgFreq;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxDate and returns taxDate.
   */
  public String getTaxDate() {
    return taxDate;
  }

  /**
   * Sets the taxDate. You can use getTaxDate() to get the value of taxDate.
   */
  public void setTaxDate(String taxDate) {
    this.taxDate = taxDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxDetType and returns taxDetType.
   */
  public String getTaxDetType() {
    return taxDetType;
  }

  /**
   * Sets the taxDetType. You can use getTaxDetType() to get the value of taxDetType.
   */
  public void setTaxDetType(String taxDetType) {
    this.taxDetType = taxDetType;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxName and returns taxName.
   */
  public String getTaxName() {
    return taxName;
  }

  /**
   * Sets the taxName. You can use getTaxName() to get the value of taxName.
   */
  public void setTaxName(String taxName) {
    this.taxName = taxName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxRate and returns taxRate.
   */
  public String getTaxRate() {
    return taxRate;
  }

  /**
   * Sets the taxRate. You can use getTaxRate() to get the value of taxRate.
   */
  public void setTaxRate(String taxRate) {
    this.taxRate = taxRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxSrc and returns taxSrc.
   */
  public String getTaxSrc() {
    return taxSrc;
  }

  /**
   * Sets the taxSrc. You can use getTaxSrc() to get the value of taxSrc.
   */
  public void setTaxSrc(String taxSrc) {
    this.taxSrc = taxSrc;
  }
}
