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
 * Rate.
 */
public class Rate {

  @SerializedName("AmountBeforeTax")
  private String amountBeforeTax;
  @SerializedName("Discount")
  private String discount;
  @SerializedName("DiscountIndicator")
  private String discountIndicator;
  @SerializedName("EffectiveDate")
  private String effectiveDate;
  @SerializedName("GrossAmountBeforeTax")
  private String grossAmountBeforeTax;
  @SerializedName("TaxAmount")
  private String taxAmount;
  @SerializedName("TaxItems")
  private String taxItems;

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
   * @return Gets the value of discount and returns discount.
   */
  public String getDiscount() {
    return discount;
  }

  /**
   * Sets the discount. You can use getDiscount() to get the value of discount.
   */
  public void setDiscount(String discount) {
    this.discount = discount;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discountIndicator and returns discountIndicator.
   */
  public String getDiscountIndicator() {
    return discountIndicator;
  }

  /**
   * Sets the discountIndicator. You can use getDiscountIndicator() to get the value of
   * discountIndicator.
   */
  public void setDiscountIndicator(String discountIndicator) {
    this.discountIndicator = discountIndicator;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of effectiveDate and returns effectiveDate.
   */
  public String getEffectiveDate() {
    return effectiveDate;
  }

  /**
   * Sets the effectiveDate. You can use getEffectiveDate() to get the value of effectiveDate.
   */
  public void setEffectiveDate(String effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of grossAmountBeforeTax and returns grossAmountBeforeTax.
   */
  public String getGrossAmountBeforeTax() {
    return grossAmountBeforeTax;
  }

  /**
   * Sets the grossAmountBeforeTax. You can use getGrossAmountBeforeTax() to get the value of
   * grossAmountBeforeTax.
   */
  public void setGrossAmountBeforeTax(String grossAmountBeforeTax) {
    this.grossAmountBeforeTax = grossAmountBeforeTax;
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

  /**
   * Getter method.
   *
   * @return Gets the value of taxItems and returns taxItems.
   */
  public String getTaxItems() {
    return taxItems;
  }

  /**
   * Sets the taxItems. You can use getTaxItems() to get the value of taxItems.
   */
  public void setTaxItems(String taxItems) {
    this.taxItems = taxItems;
  }
}
