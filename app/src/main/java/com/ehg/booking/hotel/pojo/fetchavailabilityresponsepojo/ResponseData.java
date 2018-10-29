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
 * ResponseData.
 */
public class ResponseData {

  @SerializedName("AlternateHotels")
  private List<AlternateHotel> alternateHotels;
  @SerializedName("CurrencyCode")
  private String currencyCode;
  @SerializedName("HasMandatoryServices")
  private Boolean hasMandatoryServices;
  @SerializedName("IbuId")
  private int ibuId;
  @SerializedName("ImageBaseUrl")
  private String imageBaseUrl;
  @SerializedName("IsPeStrikeThroughEnabled")
  private Boolean isPeStrikeThroughEnabled;
  @SerializedName("LanguageCode")
  private String languageCode;
  @SerializedName("PromotionApplied")
  private Boolean promotionApplied;
  @SerializedName("RoomStays")
  private List<RoomStay> roomStays;

  /**
   * Getter method.
   *
   * @return Gets the value of alternateHotels and returns alternateHotels.
   */
  public List<AlternateHotel> getAlternateHotels() {
    return alternateHotels;
  }

  /**
   * Sets the alternateHotels. You can use getAlternateHotels() to get the value of alternateHotels.
   */
  public void setAlternateHotels(
      List<AlternateHotel> alternateHotels) {
    this.alternateHotels = alternateHotels;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of currencyCode and returns currencyCode.
   */
  public String getCurrencyCode() {
    return currencyCode;
  }

  /**
   * Sets the currencyCode. You can use getCurrencyCode() to get the value of currencyCode.
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of hasMandatoryServices and returns hasMandatoryServices.
   */
  public Boolean getHasMandatoryServices() {
    return hasMandatoryServices;
  }

  /**
   * Sets the hasMandatoryServices. You can use getHasMandatoryServices() to get the value of
   * hasMandatoryServices.
   */
  public void setHasMandatoryServices(Boolean hasMandatoryServices) {
    this.hasMandatoryServices = hasMandatoryServices;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of ibuId and returns ibuId.
   */
  public int getIbuId() {
    return ibuId;
  }

  /**
   * Sets the ibuId. You can use getIbuId() to get the value of ibuId.
   */
  public void setIbuId(int ibuId) {
    this.ibuId = ibuId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of imageBaseUrl and returns imageBaseUrl.
   */
  public String getImageBaseUrl() {
    return imageBaseUrl;
  }

  /**
   * Sets the imageBaseUrl. You can use getImageBaseUrl() to get the value of imageBaseUrl.
   */
  public void setImageBaseUrl(String imageBaseUrl) {
    this.imageBaseUrl = imageBaseUrl;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isPeStrikeThroughEnabled and returns isPeStrikeThroughEnabled.
   */
  public Boolean getPeStrikeThroughEnabled() {
    return isPeStrikeThroughEnabled;
  }

  /**
   * Sets the isPeStrikeThroughEnabled. You can use getPeStrikeThroughEnabled() to get the value of
   * isPeStrikeThroughEnabled.
   */
  public void setPeStrikeThroughEnabled(Boolean peStrikeThroughEnabled) {
    isPeStrikeThroughEnabled = peStrikeThroughEnabled;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of languageCode and returns languageCode.
   */
  public String getLanguageCode() {
    return languageCode;
  }

  /**
   * Sets the languageCode. You can use getLanguageCode() to get the value of languageCode.
   */
  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of promotionApplied and returns promotionApplied.
   */
  public Boolean getPromotionApplied() {
    return promotionApplied;
  }

  /**
   * Sets the promotionApplied. You can use getPromotionApplied() to get the value of
   * promotionApplied.
   */
  public void setPromotionApplied(Boolean promotionApplied) {
    this.promotionApplied = promotionApplied;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of roomStays and returns roomStays.
   */
  public List<RoomStay> getRoomStays() {
    return roomStays;
  }

  /**
   * Sets the roomStays. You can use getRoomStays() to get the value of roomStays.
   */
  public void setRoomStays(
      List<RoomStay> roomStays) {
    this.roomStays = roomStays;
  }
}
