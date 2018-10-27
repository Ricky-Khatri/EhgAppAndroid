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
import java.util.List;

/**
 * DailyRate.
 */
public class DailyRate {

  @SerializedName("AdultQuantity")
  private float adultQuantity;
  @SerializedName("AdultRate")
  private float adultRate;
  @SerializedName("AdultUnitPrice")
  private float adultUnitPrice;
  @SerializedName("ChildQuantity")
  private float childQuantity;
  @SerializedName("ChildRate")
  private float childRate;
  @SerializedName("ChildUnitPrice")
  private float childUnitPrice;
  @SerializedName("DiscountRate")
  private float discountRate;
  @SerializedName("Price")
  private List<Price> price;
  @SerializedName("Quantity")
  private float quantity;
  @SerializedName("ServiceDate")
  private String serviceDate;
  @SerializedName("ServiceOptionRefId")
  private String serviceOptionRefId;
  @SerializedName("TaxRate")
  private Double taxRate;

  /**
   * Getter method.
   *
   * @return Gets the value of adultQuantity and returns adultQuantity.
   */
  public float getAdultQuantity() {
    return adultQuantity;
  }

  /**
   * Sets the adultQuantity. You can use getAdultQuantity() to get the value of adultQuantity.
   */
  public void setAdultQuantity(float adultQuantity) {
    this.adultQuantity = adultQuantity;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of adultRate and returns adultRate.
   */
  public float getAdultRate() {
    return adultRate;
  }

  /**
   * Sets the adultRate. You can use getAdultRate() to get the value of adultRate.
   */
  public void setAdultRate(float adultRate) {
    this.adultRate = adultRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of adultUnitPrice and returns adultUnitPrice.
   */
  public float getAdultUnitPrice() {
    return adultUnitPrice;
  }

  /**
   * Sets the adultUnitPrice. You can use getAdultUnitPrice() to get the value of adultUnitPrice.
   */
  public void setAdultUnitPrice(float adultUnitPrice) {
    this.adultUnitPrice = adultUnitPrice;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of childQuantity and returns childQuantity.
   */
  public float getChildQuantity() {
    return childQuantity;
  }

  /**
   * Sets the childQuantity. You can use getChildQuantity() to get the value of childQuantity.
   */
  public void setChildQuantity(float childQuantity) {
    this.childQuantity = childQuantity;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of childRate and returns childRate.
   */
  public float getChildRate() {
    return childRate;
  }

  /**
   * Sets the childRate. You can use getChildRate() to get the value of childRate.
   */
  public void setChildRate(float childRate) {
    this.childRate = childRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of childUnitPrice and returns childUnitPrice.
   */
  public float getChildUnitPrice() {
    return childUnitPrice;
  }

  /**
   * Sets the childUnitPrice. You can use getChildUnitPrice() to get the value of childUnitPrice.
   */
  public void setChildUnitPrice(float childUnitPrice) {
    this.childUnitPrice = childUnitPrice;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discountRate and returns discountRate.
   */
  public float getDiscountRate() {
    return discountRate;
  }

  /**
   * Sets the discountRate. You can use getDiscountRate() to get the value of discountRate.
   */
  public void setDiscountRate(float discountRate) {
    this.discountRate = discountRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of price and returns price.
   */
  public List<Price> getPrice() {
    return price;
  }

  /**
   * Sets the price. You can use getPrice() to get the value of price.
   */
  public void setPrice(
      List<Price> price) {
    this.price = price;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of quantity and returns quantity.
   */
  public float getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity. You can use getQuantity() to get the value of quantity.
   */
  public void setQuantity(float quantity) {
    this.quantity = quantity;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of serviceDate and returns serviceDate.
   */
  public String getServiceDate() {
    return serviceDate;
  }

  /**
   * Sets the serviceDate. You can use getServiceDate() to get the value of serviceDate.
   */
  public void setServiceDate(String serviceDate) {
    this.serviceDate = serviceDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of serviceOptionRefId and returns serviceOptionRefId.
   */
  public String getServiceOptionRefId() {
    return serviceOptionRefId;
  }

  /**
   * Sets the serviceOptionRefId. You can use getServiceOptionRefId() to get the value of
   * serviceOptionRefId.
   */
  public void setServiceOptionRefId(String serviceOptionRefId) {
    this.serviceOptionRefId = serviceOptionRefId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxRate and returns taxRate.
   */
  public Double getTaxRate() {
    return taxRate;
  }

  /**
   * Sets the taxRate. You can use getTaxRate() to get the value of taxRate.
   */
  public void setTaxRate(Double taxRate) {
    this.taxRate = taxRate;
  }
}
