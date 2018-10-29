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
import java.util.List;

/**
 * Detail.
 */
public class Detail {

  @SerializedName("DeviceId")
  private String deviceId;
  @SerializedName("IbuId")
  private int ibuId;
  @SerializedName("LanguageCode")
  private String languageCode;
  @SerializedName("LoyaltyMemberId")
  private int loyaltyMemberId;
  @SerializedName("ReservationRequestParams")
  private List<ReservationRequestParam> reservationRequestParams;

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
   * @return Gets the value of reservationRequestParams and returns reservationRequestParams.
   */
  public List<ReservationRequestParam> getReservationRequestParams() {
    return reservationRequestParams;
  }

  /**
   * Sets the reservationRequestParams. You can use getReservationRequestParams() to get the value of
   * reservationRequestParams.
   */
  public void setReservationRequestParams(
      List<ReservationRequestParam> reservationRequestParams) {
    this.reservationRequestParams = reservationRequestParams;
  }
}
