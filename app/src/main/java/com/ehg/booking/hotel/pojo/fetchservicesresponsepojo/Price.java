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

/**
 * Price.
 */
public class Price {

  @SerializedName("AmountAfterTax")
  private float amountAfterTax;
  @SerializedName("AmountBeforeTax")
  private float amountBeforeTax;
  @SerializedName("BaseAmountBeforeTax")
  private float baseAmountBeforeTax;
  @SerializedName("PriceType")
  private String priceType;
  @SerializedName("TaxAmount")
  private float taxAmount;

  /**
   * Getter method.
   *
   * @return Gets the value of amountAfterTax and returns amountAfterTax.
   */
  public float getAmountAfterTax() {
    return amountAfterTax;
  }

  /**
   * Sets the amountAfterTax. You can use getAmountAfterTax() to get the value of amountAfterTax.
   */
  public void setAmountAfterTax(float amountAfterTax) {
    this.amountAfterTax = amountAfterTax;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of amountBeforeTax and returns amountBeforeTax.
   */
  public float getAmountBeforeTax() {
    return amountBeforeTax;
  }

  /**
   * Sets the amountBeforeTax. You can use getAmountBeforeTax() to get the value of amountBeforeTax.
   */
  public void setAmountBeforeTax(float amountBeforeTax) {
    this.amountBeforeTax = amountBeforeTax;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of baseAmountBeforeTax and returns baseAmountBeforeTax.
   */
  public float getBaseAmountBeforeTax() {
    return baseAmountBeforeTax;
  }

  /**
   * Sets the baseAmountBeforeTax. You can use getBaseAmountBeforeTax() to get the value of
   * baseAmountBeforeTax.
   */
  public void setBaseAmountBeforeTax(float baseAmountBeforeTax) {
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
  public float getTaxAmount() {
    return taxAmount;
  }

  /**
   * Sets the taxAmount. You can use getTaxAmount() to get the value of taxAmount.
   */
  public void setTaxAmount(float taxAmount) {
    this.taxAmount = taxAmount;
  }
}
