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
 * RatePlan.
 */
public class RatePlan {

  @SerializedName("Available")
  private Boolean available;
  @SerializedName("CancellationPolicy")
  private CancellationPolicy cancellationPolicy;
  @SerializedName("Confidential")
  private Boolean confidential;
  @SerializedName("Default")
  private Boolean defaultRate;
  @SerializedName("DiscountRate")
  private float discountRate;
  @SerializedName("GuaranteePolicy")
  private GuaranteePolicy guaranteePolicy;
  @SerializedName("LeadRate")
  private float leadRate;
  @SerializedName("MerchandisedPromoData")
  private MerchandisedPromoData merchandisedPromoData;
  @SerializedName("RatePlanCode")
  private String ratePlanCode;
  @SerializedName("RatePlanDescription")
  private String ratePlanDescription;
  @SerializedName("RatePlanName")
  private String ratePlanName;
  @SerializedName("RatePlanType")
  private String ratePlanType;
  @SerializedName("SortOrder")
  private int sortOrder;

  /**
   * Getter method.
   *
   * @return Gets the value of available and returns available.
   */
  public Boolean getAvailable() {
    return available;
  }

  /**
   * Sets the available. You can use getAvailable() to get the value of available.
   */
  public void setAvailable(Boolean available) {
    this.available = available;
  }

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
   * @return Gets the value of defaultRate and returns defaultRate.
   */
  public Boolean getDefaultRate() {
    return defaultRate;
  }

  /**
   * Sets the defaultRate. You can use getDefaultRate() to get the value of defaultRate.
   */
  public void setDefaultRate(Boolean defaultRate) {
    this.defaultRate = defaultRate;
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
   * @return Gets the value of leadRate and returns leadRate.
   */
  public float getLeadRate() {
    return leadRate;
  }

  /**
   * Sets the leadRate. You can use getLeadRate() to get the value of leadRate.
   */
  public void setLeadRate(int leadRate) {
    this.leadRate = leadRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of merchandisedPromoData and returns merchandisedPromoData.
   */
  public MerchandisedPromoData getMerchandisedPromoData() {
    return merchandisedPromoData;
  }

  /**
   * Sets the merchandisedPromoData. You can use getMerchandisedPromoData() to get the value of
   * merchandisedPromoData.
   */
  public void setMerchandisedPromoData(
      MerchandisedPromoData merchandisedPromoData) {
    this.merchandisedPromoData = merchandisedPromoData;
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
   * @return Gets the value of ratePlanDescription and returns ratePlanDescription.
   */
  public String getRatePlanDescription() {
    return ratePlanDescription;
  }

  /**
   * Sets the ratePlanDescription. You can use getRatePlanDescription() to get the value of
   * ratePlanDescription.
   */
  public void setRatePlanDescription(String ratePlanDescription) {
    this.ratePlanDescription = ratePlanDescription;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of ratePlanName and returns ratePlanName.
   */
  public String getRatePlanName() {
    return ratePlanName;
  }

  /**
   * Sets the ratePlanName. You can use getRatePlanName() to get the value of ratePlanName.
   */
  public void setRatePlanName(String ratePlanName) {
    this.ratePlanName = ratePlanName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of ratePlanType and returns ratePlanType.
   */
  public String getRatePlanType() {
    return ratePlanType;
  }

  /**
   * Sets the ratePlanType. You can use getRatePlanType() to get the value of ratePlanType.
   */
  public void setRatePlanType(String ratePlanType) {
    this.ratePlanType = ratePlanType;
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
