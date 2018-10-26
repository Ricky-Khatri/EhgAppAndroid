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
import java.util.List;

/**
 * AverageRate class.
 */
public class AverageRate {

  @SerializedName("Available")
  private Boolean available;
  @SerializedName("Discount")
  private float discount;
  @SerializedName("DisplayUrgencyMessage")
  private Boolean displayUrgencyMessage;
  @SerializedName("GeoPricingPromotion")
  private Boolean geoPricingPromotion;
  @SerializedName("MerchandisedPromoData")
  private MerchandisedPromoData merchandisedPromoData;
  @SerializedName("PromotionPercentageDiscount")
  private float promotionPercentageDiscount;
  @SerializedName("QuantityRemaining")
  private String quantityRemaining;
  @SerializedName("Rate")
  private float rate;
  @SerializedName("RatePlanCode")
  private String ratePlanCode;
  @SerializedName("RatePlanType")
  private String ratePlanType;
  @SerializedName("RoomTypeCode")
  private String roomTypeCode;
  @SerializedName("RoomUpgradeList")
  private List<RoomUpgradeList> roomUpgradeList;
  @SerializedName("ShowPromotionBanner")
  private Boolean showPromotionBanner;
  @SerializedName("XnightsDiscount")
  private float xnightsDiscount;
  @SerializedName("XnightsDiscountType")
  private String xnightsDiscountType;

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
   * @return Gets the value of discount and returns discount.
   */
  public float getDiscount() {
    return discount;
  }

  /**
   * Sets the discount. You can use getDiscount() to get the value of discount.
   */
  public void setDiscount(float discount) {
    this.discount = discount;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of displayUrgencyMessage and returns displayUrgencyMessage.
   */
  public Boolean getDisplayUrgencyMessage() {
    return displayUrgencyMessage;
  }

  /**
   * Sets the displayUrgencyMessage. You can use getDisplayUrgencyMessage() to get the value of
   * displayUrgencyMessage.
   */
  public void setDisplayUrgencyMessage(Boolean displayUrgencyMessage) {
    this.displayUrgencyMessage = displayUrgencyMessage;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of geoPricingPromotion and returns geoPricingPromotion.
   */
  public Boolean getGeoPricingPromotion() {
    return geoPricingPromotion;
  }

  /**
   * Sets the geoPricingPromotion. You can use getGeoPricingPromotion() to get the value of
   * geoPricingPromotion.
   */
  public void setGeoPricingPromotion(Boolean geoPricingPromotion) {
    this.geoPricingPromotion = geoPricingPromotion;
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
   * @return Gets the value of promotionPercentageDiscount and returns promotionPercentageDiscount.
   */
  public float getPromotionPercentageDiscount() {
    return promotionPercentageDiscount;
  }

  /**
   * Sets the promotionPercentageDiscount. You can use getPromotionPercentageDiscount() to get the
   * value of promotionPercentageDiscount.
   */
  public void setPromotionPercentageDiscount(float promotionPercentageDiscount) {
    this.promotionPercentageDiscount = promotionPercentageDiscount;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of quantityRemaining and returns quantityRemaining.
   */
  public String getQuantityRemaining() {
    return quantityRemaining;
  }

  /**
   * Sets the quantityRemaining. You can use getQuantityRemaining() to get the value of
   * quantityRemaining.
   */
  public void setQuantityRemaining(String quantityRemaining) {
    this.quantityRemaining = quantityRemaining;
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
   * @return Gets the value of roomTypeCode and returns roomTypeCode.
   */
  public String getRoomTypeCode() {
    return roomTypeCode;
  }

  /**
   * Sets the roomTypeCode. You can use getRoomTypeCode() to get the value of roomTypeCode.
   */
  public void setRoomTypeCode(String roomTypeCode) {
    this.roomTypeCode = roomTypeCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of roomUpgradeList and returns roomUpgradeList.
   */
  public List<RoomUpgradeList> getRoomUpgradeList() {
    return roomUpgradeList;
  }

  /**
   * Sets the roomUpgradeList. You can use getRoomUpgradeList() to get the value of roomUpgradeList.
   */
  public void setRoomUpgradeList(
      List<RoomUpgradeList> roomUpgradeList) {
    this.roomUpgradeList = roomUpgradeList;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of showPromotionBanner and returns showPromotionBanner.
   */
  public Boolean getShowPromotionBanner() {
    return showPromotionBanner;
  }

  /**
   * Sets the showPromotionBanner. You can use getShowPromotionBanner() to get the value of
   * showPromotionBanner.
   */
  public void setShowPromotionBanner(Boolean showPromotionBanner) {
    this.showPromotionBanner = showPromotionBanner;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of xnightsDiscount and returns xnightsDiscount.
   */
  public float getXnightsDiscount() {
    return xnightsDiscount;
  }

  /**
   * Sets the xnightsDiscount. You can use getXnightsDiscount() to get the value of xnightsDiscount.
   */
  public void setXnightsDiscount(float xnightsDiscount) {
    this.xnightsDiscount = xnightsDiscount;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of xnightsDiscountType and returns xnightsDiscountType.
   */
  public String getXnightsDiscountType() {
    return xnightsDiscountType;
  }

  /**
   * Sets the xnightsDiscountType. You can use getXnightsDiscountType() to get the value of
   * xnightsDiscountType.
   */
  public void setXnightsDiscountType(String xnightsDiscountType) {
    this.xnightsDiscountType = xnightsDiscountType;
  }
}
