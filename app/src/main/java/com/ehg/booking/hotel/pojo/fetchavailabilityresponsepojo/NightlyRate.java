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
 * NightlyRates.
 */
public class NightlyRate {

  @SerializedName("AmtBeforeTax")
  private int amtBeforeTax;
  @SerializedName("AmtTotal")
  private int amtTotal;
  @SerializedName("Date")
  private String date;
  @SerializedName("Discount")
  private int discount;
  @SerializedName("DiscountType")
  private String discountType;
  @SerializedName("RatePlanCode")
  private String ratePlanCode;

  /**
   * Getter method.
   *
   * @return Gets the value of amtBeforeTax and returns amtBeforeTax.
   */
  public int getAmtBeforeTax() {
    return amtBeforeTax;
  }

  /**
   * Sets the amtBeforeTax. You can use getAmtBeforeTax() to get the value of amtBeforeTax.
   */
  public void setAmtBeforeTax(int amtBeforeTax) {
    this.amtBeforeTax = amtBeforeTax;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of amtTotal and returns amtTotal.
   */
  public int getAmtTotal() {
    return amtTotal;
  }

  /**
   * Sets the amtTotal. You can use getAmtTotal() to get the value of amtTotal.
   */
  public void setAmtTotal(int amtTotal) {
    this.amtTotal = amtTotal;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of date and returns date.
   */
  public String getDate() {
    return date;
  }

  /**
   * Sets the date. You can use getDate() to get the value of date.
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discount and returns discount.
   */
  public int getDiscount() {
    return discount;
  }

  /**
   * Sets the discount. You can use getDiscount() to get the value of discount.
   */
  public void setDiscount(int discount) {
    this.discount = discount;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discountType and returns discountType.
   */
  public String getDiscountType() {
    return discountType;
  }

  /**
   * Sets the discountType. You can use getDiscountType() to get the value of discountType.
   */
  public void setDiscountType(String discountType) {
    this.discountType = discountType;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of ratePlanCode and returns ratePlanCode.
   */
  public String getRatePlanCode() {
    return ratePlanCode;
  }

  /**
   * Sets the ratePlanCode. You can use getRatePlanCode() to get the value of ratePlanCode.
   */
  public void setRatePlanCode(String ratePlanCode) {
    this.ratePlanCode = ratePlanCode;
  }
}
