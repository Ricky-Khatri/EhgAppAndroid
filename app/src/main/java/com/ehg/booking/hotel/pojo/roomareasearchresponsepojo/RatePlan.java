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
package com.ehg.booking.hotel.pojo.roomareasearchresponsepojo;

import com.google.gson.annotations.SerializedName;

/**
 * RatePlan class.
 */
public class RatePlan {

  @SerializedName("CancellationPolicy")
  private CancellationPolicy cancellationPolicy;
  @SerializedName("Confidential")
  private Boolean confidential;
  @SerializedName("Discount")
  private int discount;
  @SerializedName("ExternalCode")
  private String externalCode;
  @SerializedName("GuaranteePolicy")
  private GuaranteePolicy guaranteePolicy;
  @SerializedName("IsAvailable")
  private Boolean isAvailable;
  @SerializedName("Name")
  private String name;
  @SerializedName("Rate")
  private float rate;
  @SerializedName("RatePlanCode")
  private String ratePlanCode;
  @SerializedName("RateplanDesc")
  private String rateplanDesc;
  @SerializedName("SortOrder")
  private int sortOrder;

  /**
   * Getter method.
   *
   * @return Gets the value of cancellationPolicy and returns cancellationPolicy.
   */
  public CancellationPolicy getCancellationPolicy() {
    return cancellationPolicy;
  }

  /**
   * Sets the cancellationPolicy. You can use getCancellationPolicy() to get the value of
   * cancellationPolicy.
   */
  public void setCancellationPolicy(
      CancellationPolicy cancellationPolicy) {
    this.cancellationPolicy = cancellationPolicy;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of confidential and returns confidential.
   */
  public Boolean getConfidential() {
    return confidential;
  }

  /**
   * Sets the confidential. You can use getConfidential() to get the value of confidential.
   */
  public void setConfidential(Boolean confidential) {
    this.confidential = confidential;
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
   * @return Gets the value of externalCode and returns externalCode.
   */
  public String getExternalCode() {
    return externalCode;
  }

  /**
   * Sets the externalCode. You can use getExternalCode() to get the value of externalCode.
   */
  public void setExternalCode(String externalCode) {
    this.externalCode = externalCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of guaranteePolicy and returns guaranteePolicy.
   */
  public GuaranteePolicy getGuaranteePolicy() {
    return guaranteePolicy;
  }

  /**
   * Sets the guaranteePolicy. You can use getGuaranteePolicy() to get the value of guaranteePolicy.
   */
  public void setGuaranteePolicy(
      GuaranteePolicy guaranteePolicy) {
    this.guaranteePolicy = guaranteePolicy;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isAvailable and returns isAvailable.
   */
  public Boolean getAvailable() {
    return isAvailable;
  }

  /**
   * Sets the isAvailable. You can use getAvailable() to get the value of isAvailable.
   */
  public void setAvailable(Boolean available) {
    isAvailable = available;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of name and returns name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name. You can use getName() to get the value of name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of rate and returns rate.
   */
  public float getRate() {
    return rate;
  }

  /**
   * Sets the rate. You can use getRate() to get the value of rate.
   */
  public void setRate(float rate) {
    this.rate = rate;
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

  /**
   * Getter method.
   *
   * @return Gets the value of rateplanDesc and returns rateplanDesc.
   */
  public String getRateplanDesc() {
    return rateplanDesc;
  }

  /**
   * Sets the rateplanDesc. You can use getRateplanDesc() to get the value of rateplanDesc.
   */
  public void setRateplanDesc(String rateplanDesc) {
    this.rateplanDesc = rateplanDesc;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of sortOrder and returns sortOrder.
   */
  public int getSortOrder() {
    return sortOrder;
  }

  /**
   * Sets the sortOrder. You can use getSortOrder() to get the value of sortOrder.
   */
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
}
