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

package com.ehg.booking.hotel.pojo.fetchservicesrequestpojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Detail class.
 */
public class Detail {

  @SerializedName("CheckInDate")
  private String checkInDate;
  @SerializedName("CheckOutDate")
  private String checkOutDate;
  @SerializedName("ChildrenAges")
  private List<Integer> childrenAges;
  @SerializedName("CurrencyCode")
  private String currencyCode;
  @SerializedName("DeviceId")
  private String deviceId;
  @SerializedName("DurationOfStay")
  private int durationOfStay;
  @SerializedName("IbuId")
  private int ibuId;
  @SerializedName("Language")
  private String language;
  @SerializedName("LoyaltyMemberId")
  private int loyaltyMemberId;
  @SerializedName("RatePlanCode")
  private String ratePlanCode;
  @SerializedName("RatePlanPackageRequired")
  private Boolean ratePlanPackageRequired;
  @SerializedName("RateplanType")
  private int ratePlanType;
  @SerializedName("RoomTypeCode")
  private String roomTypeCode;
  @SerializedName("TotalAdults")
  private int totalAdults;
  @SerializedName("TotalChildren")
  private int totalChildren;
  @SerializedName("TotalInfants")
  private int totalInfants;
  @SerializedName("TotalRooms")
  private int totalRooms;

  /**
   * Getter method.
   *
   * @return Gets the value of checkInDate and returns checkInDate.
   */
  public String getCheckInDate() {
    return checkInDate;
  }

  /**
   * Sets the checkInDate. You can use getCheckInDate() to get the value of checkInDate.
   */
  public void setCheckInDate(String checkInDate) {
    this.checkInDate = checkInDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of checkOutDate and returns checkOutDate.
   */
  public String getCheckOutDate() {
    return checkOutDate;
  }

  /**
   * Sets the checkOutDate. You can use getCheckOutDate() to get the value of checkOutDate.
   */
  public void setCheckOutDate(String checkOutDate) {
    this.checkOutDate = checkOutDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of childrenAges and returns childrenAges.
   */
  public List<Integer> getChildrenAges() {
    return childrenAges;
  }

  /**
   * Sets the childrenAges. You can use getChildrenAges() to get the value of childrenAges.
   */
  public void setChildrenAges(List<Integer> childrenAges) {
    this.childrenAges = childrenAges;
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
   * @return Gets the value of deviceId and returns deviceId.
   */
  public String getDeviceId() {
    return deviceId;
  }

  /**
   * Sets the deviceId. You can use getDeviceId() to get the value of deviceId.
   */
  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of durationOfStay and returns durationOfStay.
   */
  public int getDurationOfStay() {
    return durationOfStay;
  }

  /**
   * Sets the durationOfStay. You can use getDurationOfStay() to get the value of durationOfStay.
   */
  public void setDurationOfStay(int durationOfStay) {
    this.durationOfStay = durationOfStay;
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
   * @return Gets the value of language and returns language.
   */
  public String getLanguage() {
    return language;
  }

  /**
   * Sets the language. You can use getLanguage() to get the value of language.
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of loyaltyMemberId and returns loyaltyMemberId.
   */
  public int getLoyaltyMemberId() {
    return loyaltyMemberId;
  }

  /**
   * Sets the loyaltyMemberId. You can use getLoyaltyMemberId() to get the value of loyaltyMemberId.
   */
  public void setLoyaltyMemberId(int loyaltyMemberId) {
    this.loyaltyMemberId = loyaltyMemberId;
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
   * @return Gets the value of ratePlanPackageRequired and returns ratePlanPackageRequired.
   */
  public Boolean getRatePlanPackageRequired() {
    return ratePlanPackageRequired;
  }

  /**
   * Sets the ratePlanPackageRequired. You can use getRatePlanPackageRequired() to get the value of
   * ratePlanPackageRequired.
   */
  public void setRatePlanPackageRequired(Boolean ratePlanPackageRequired) {
    this.ratePlanPackageRequired = ratePlanPackageRequired;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of rateplanType and returns rateplanType.
   */
  public int getRateplanType() {
    return ratePlanType;
  }

  /**
   * Sets the rateplanType. You can use getRateplanType() to get the value of rateplanType.
   */
  public void setRatePlanType(int ratePlanType) {
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
   * @return Gets the value of totalAdults and returns totalAdults.
   */
  public int getTotalAdults() {
    return totalAdults;
  }

  /**
   * Sets the totalAdults. You can use getTotalAdults() to get the value of totalAdults.
   */
  public void setTotalAdults(int totalAdults) {
    this.totalAdults = totalAdults;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of totalChildren and returns totalChildren.
   */
  public int getTotalChildren() {
    return totalChildren;
  }

  /**
   * Sets the totalChildren. You can use getTotalChildren() to get the value of totalChildren.
   */
  public void setTotalChildren(int totalChildren) {
    this.totalChildren = totalChildren;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of totalInfants and returns totalInfants.
   */
  public int getTotalInfants() {
    return totalInfants;
  }

  /**
   * Sets the totalInfants. You can use getTotalInfants() to get the value of totalInfants.
   */
  public void setTotalInfants(int totalInfants) {
    this.totalInfants = totalInfants;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of totalRooms and returns totalRooms.
   */
  public int getTotalRooms() {
    return totalRooms;
  }

  /**
   * Sets the totalRooms. You can use getTotalRooms() to get the value of totalRooms.
   */
  public void setTotalRooms(int totalRooms) {
    this.totalRooms = totalRooms;
  }
}
