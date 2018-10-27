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
 * TotalPrice.
 */
public class TotalPrice {

  @SerializedName("AmountAfterTax")
  private String amountAfterTax;
  @SerializedName("AmountBeforeTax")
  private String amountBeforeTax;
  @SerializedName("BaseAmountBeforeTax")
  private String baseAmountBeforeTax;
  @SerializedName("PriceType")
  private String priceType;
  @SerializedName("TaxAmount")
  private String taxAmount;

  /**
   * Getter method.
   *
   * @return Gets the value of amountAfterTax and returns amountAfterTax.
   */
  public String getAmountAfterTax() {
    return amountAfterTax;
  }

  /**
   * Sets the amountAfterTax. You can use getAmountAfterTax() to get the value of amountAfterTax.
   */
  public void setAmountAfterTax(String amountAfterTax) {
    this.amountAfterTax = amountAfterTax;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of amountBeforeTax and returns amountBeforeTax.
   */
  public String getAmountBeforeTax() {
    return amountBeforeTax;
  }

  /**
   * Sets the amountBeforeTax. You can use getAmountBeforeTax() to get the value of amountBeforeTax.
   */
  public void setAmountBeforeTax(String amountBeforeTax) {
    this.amountBeforeTax = amountBeforeTax;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of baseAmountBeforeTax and returns baseAmountBeforeTax.
   */
  public String getBaseAmountBeforeTax() {
    return baseAmountBeforeTax;
  }

  /**
   * Sets the baseAmountBeforeTax. You can use getBaseAmountBeforeTax() to get the value of
   * baseAmountBeforeTax.
   */
  public void setBaseAmountBeforeTax(String baseAmountBeforeTax) {
    this.baseAmountBeforeTax = baseAmountBeforeTax;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of priceType and returns priceType.
   */
  public String getPriceType() {
    return priceType;
  }

  /**
   * Sets the priceType. You can use getPriceType() to get the value of priceType.
   */
  public void setPriceType(String priceType) {
    this.priceType = priceType;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxAmount and returns taxAmount.
   */
  public String getTaxAmount() {
    return taxAmount;
  }

  /**
   * Sets the taxAmount. You can use getTaxAmount() to get the value of taxAmount.
   */
  public void setTaxAmount(String taxAmount) {
    this.taxAmount = taxAmount;
  }
}
