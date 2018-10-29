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
 * Total.
 */
public class Total {

  @SerializedName("AmountAfterTax")
  private int amountAfterTax;
  @SerializedName("AmountAfterTaxRoom")
  private int amountAfterTaxRoom;
  @SerializedName("AmountBeforeTax")
  private int amountBeforeTax;
  @SerializedName("AmountBeforeTaxRoom")
  private int amountBeforeTaxRoom;
  @SerializedName("AmountBeforeTaxServ")
  private int amountBeforeTaxServ;
  @SerializedName("Discount")
  private int discount;
  @SerializedName("DiscountIndicator")
  private Boolean discountIndicator;
  @SerializedName("DiscountIndicatorRoom")
  private Boolean discountIndicatorRoom;
  @SerializedName("DiscountIndicatorServ")
  private Boolean discountIndicatorServ;
  @SerializedName("DiscountRoom")
  private int discountRoom;
  @SerializedName("DiscountServ")
  private int discountServ;
  @SerializedName("GrossAmountBeforeTax")
  private int grossAmountBeforeTax;
  @SerializedName("GrossAmountBeforeTaxRoom")
  private int grossAmountBeforeTaxRoom;
  @SerializedName("GrossAmountBeforeTaxServ")
  private int grossAmountBeforeTaxServ;
  @SerializedName("TaxAmountTotal")
  private int taxAmountTotal;

  /**
   * Getter method.
   *
   * @return Gets the value of amountAfterTax and returns amountAfterTax.
   */
  public int getAmountAfterTax() {
    return amountAfterTax;
  }

  /**
   * Sets the amountAfterTax. You can use getAmountAfterTax() to get the value of amountAfterTax.
   */
  public void setAmountAfterTax(int amountAfterTax) {
    this.amountAfterTax = amountAfterTax;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of amountAfterTaxRoom and returns amountAfterTaxRoom.
   */
  public int getAmountAfterTaxRoom() {
    return amountAfterTaxRoom;
  }

  /**
   * Sets the amountAfterTaxRoom. You can use getAmountAfterTaxRoom() to get the value of
   * amountAfterTaxRoom.
   */
  public void setAmountAfterTaxRoom(int amountAfterTaxRoom) {
    this.amountAfterTaxRoom = amountAfterTaxRoom;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of amountBeforeTax and returns amountBeforeTax.
   */
  public int getAmountBeforeTax() {
    return amountBeforeTax;
  }

  /**
   * Sets the amountBeforeTax. You can use getAmountBeforeTax() to get the value of amountBeforeTax.
   */
  public void setAmountBeforeTax(int amountBeforeTax) {
    this.amountBeforeTax = amountBeforeTax;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of amountBeforeTaxRoom and returns amountBeforeTaxRoom.
   */
  public int getAmountBeforeTaxRoom() {
    return amountBeforeTaxRoom;
  }

  /**
   * Sets the amountBeforeTaxRoom. You can use getAmountBeforeTaxRoom() to get the value of
   * amountBeforeTaxRoom.
   */
  public void setAmountBeforeTaxRoom(int amountBeforeTaxRoom) {
    this.amountBeforeTaxRoom = amountBeforeTaxRoom;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of amountBeforeTaxServ and returns amountBeforeTaxServ.
   */
  public int getAmountBeforeTaxServ() {
    return amountBeforeTaxServ;
  }

  /**
   * Sets the amountBeforeTaxServ. You can use getAmountBeforeTaxServ() to get the value of
   * amountBeforeTaxServ.
   */
  public void setAmountBeforeTaxServ(int amountBeforeTaxServ) {
    this.amountBeforeTaxServ = amountBeforeTaxServ;
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
   * @return Gets the value of discountIndicator and returns discountIndicator.
   */
  public Boolean getDiscountIndicator() {
    return discountIndicator;
  }

  /**
   * Sets the discountIndicator. You can use getDiscountIndicator() to get the value of
   * discountIndicator.
   */
  public void setDiscountIndicator(Boolean discountIndicator) {
    this.discountIndicator = discountIndicator;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discountIndicatorRoom and returns discountIndicatorRoom.
   */
  public Boolean getDiscountIndicatorRoom() {
    return discountIndicatorRoom;
  }

  /**
   * Sets the discountIndicatorRoom. You can use getDiscountIndicatorRoom() to get the value of
   * discountIndicatorRoom.
   */
  public void setDiscountIndicatorRoom(Boolean discountIndicatorRoom) {
    this.discountIndicatorRoom = discountIndicatorRoom;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discountIndicatorServ and returns discountIndicatorServ.
   */
  public Boolean getDiscountIndicatorServ() {
    return discountIndicatorServ;
  }

  /**
   * Sets the discountIndicatorServ. You can use getDiscountIndicatorServ() to get the value of
   * discountIndicatorServ.
   */
  public void setDiscountIndicatorServ(Boolean discountIndicatorServ) {
    this.discountIndicatorServ = discountIndicatorServ;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discountRoom and returns discountRoom.
   */
  public int getDiscountRoom() {
    return discountRoom;
  }

  /**
   * Sets the discountRoom. You can use getDiscountRoom() to get the value of discountRoom.
   */
  public void setDiscountRoom(int discountRoom) {
    this.discountRoom = discountRoom;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discountServ and returns discountServ.
   */
  public int getDiscountServ() {
    return discountServ;
  }

  /**
   * Sets the discountServ. You can use getDiscountServ() to get the value of discountServ.
   */
  public void setDiscountServ(int discountServ) {
    this.discountServ = discountServ;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of grossAmountBeforeTax and returns grossAmountBeforeTax.
   */
  public int getGrossAmountBeforeTax() {
    return grossAmountBeforeTax;
  }

  /**
   * Sets the grossAmountBeforeTax. You can use getGrossAmountBeforeTax() to get the value of
   * grossAmountBeforeTax.
   */
  public void setGrossAmountBeforeTax(int grossAmountBeforeTax) {
    this.grossAmountBeforeTax = grossAmountBeforeTax;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of grossAmountBeforeTaxRoom and returns grossAmountBeforeTaxRoom.
   */
  public int getGrossAmountBeforeTaxRoom() {
    return grossAmountBeforeTaxRoom;
  }

  /**
   * Sets the grossAmountBeforeTaxRoom. You can use getGrossAmountBeforeTaxRoom() to get the value of
   * grossAmountBeforeTaxRoom.
   */
  public void setGrossAmountBeforeTaxRoom(int grossAmountBeforeTaxRoom) {
    this.grossAmountBeforeTaxRoom = grossAmountBeforeTaxRoom;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of grossAmountBeforeTaxServ and returns grossAmountBeforeTaxServ.
   */
  public int getGrossAmountBeforeTaxServ() {
    return grossAmountBeforeTaxServ;
  }

  /**
   * Sets the grossAmountBeforeTaxServ. You can use getGrossAmountBeforeTaxServ() to get the value of
   * grossAmountBeforeTaxServ.
   */
  public void setGrossAmountBeforeTaxServ(int grossAmountBeforeTaxServ) {
    this.grossAmountBeforeTaxServ = grossAmountBeforeTaxServ;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxAmountTotal and returns taxAmountTotal.
   */
  public int getTaxAmountTotal() {
    return taxAmountTotal;
  }

  /**
   * Sets the taxAmountTotal. You can use getTaxAmountTotal() to get the value of taxAmountTotal.
   */
  public void setTaxAmountTotal(int taxAmountTotal) {
    this.taxAmountTotal = taxAmountTotal;
  }
}
