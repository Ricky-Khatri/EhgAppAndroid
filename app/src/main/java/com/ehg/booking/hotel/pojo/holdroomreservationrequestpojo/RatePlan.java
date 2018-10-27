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
 * RatePlan.
 */
public class RatePlan {

  @SerializedName("IsConfidential")
  private Boolean isConfidential;
  @SerializedName("IsHideDailyRate")
  private Boolean isHideDailyRate;
  @SerializedName("IsHideRateFromAttendee")
  private Boolean isHideRateFromAttendee;
  @SerializedName("MainImage")
  private MainImage mainImage;
  @SerializedName("MaxStay")
  private int maxStay;
  @SerializedName("PmsRateExternalCode")
  private String pmsRateExternalCode;
  @SerializedName("RateExternalCode")
  private String rateExternalCode;
  @SerializedName("RatePlanCode")
  private String ratePlanCode;
  @SerializedName("RatePlanName")
  private String ratePlanName;
  @SerializedName("RatePlanType")
  private String ratePlanType;

  /**
   * Getter method.
   *
   * @return Gets the value of isConfidential and returns isConfidential.
   */
  public Boolean getConfidential() {
    return isConfidential;
  }

  /**
   * Sets the isConfidential. You can use getConfidential() to get the value of isConfidential.
   */
  public void setConfidential(Boolean confidential) {
    isConfidential = confidential;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isHideDailyRate and returns isHideDailyRate.
   */
  public Boolean getHideDailyRate() {
    return isHideDailyRate;
  }

  /**
   * Sets the isHideDailyRate. You can use getHideDailyRate() to get the value of isHideDailyRate.
   */
  public void setHideDailyRate(Boolean hideDailyRate) {
    isHideDailyRate = hideDailyRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isHideRateFromAttendee and returns isHideRateFromAttendee.
   */
  public Boolean getHideRateFromAttendee() {
    return isHideRateFromAttendee;
  }

  /**
   * Sets the isHideRateFromAttendee. You can use getHideRateFromAttendee() to get the value of
   * isHideRateFromAttendee.
   */
  public void setHideRateFromAttendee(Boolean hideRateFromAttendee) {
    isHideRateFromAttendee = hideRateFromAttendee;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of mainImage and returns mainImage.
   */
  public MainImage getMainImage() {
    return mainImage;
  }

  /**
   * Sets the mainImage. You can use getMainImage() to get the value of mainImage.
   */
  public void setMainImage(
      MainImage mainImage) {
    this.mainImage = mainImage;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of maxStay and returns maxStay.
   */
  public int getMaxStay() {
    return maxStay;
  }

  /**
   * Sets the maxStay. You can use getMaxStay() to get the value of maxStay.
   */
  public void setMaxStay(int maxStay) {
    this.maxStay = maxStay;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of pmsRateExternalCode and returns pmsRateExternalCode.
   */
  public String getPmsRateExternalCode() {
    return pmsRateExternalCode;
  }

  /**
   * Sets the pmsRateExternalCode. You can use getPmsRateExternalCode() to get the value of
   * pmsRateExternalCode.
   */
  public void setPmsRateExternalCode(String pmsRateExternalCode) {
    this.pmsRateExternalCode = pmsRateExternalCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of rateExternalCode and returns rateExternalCode.
   */
  public String getRateExternalCode() {
    return rateExternalCode;
  }

  /**
   * Sets the rateExternalCode. You can use getRateExternalCode() to get the value of
   * rateExternalCode.
   */
  public void setRateExternalCode(String rateExternalCode) {
    this.rateExternalCode = rateExternalCode;
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
}
