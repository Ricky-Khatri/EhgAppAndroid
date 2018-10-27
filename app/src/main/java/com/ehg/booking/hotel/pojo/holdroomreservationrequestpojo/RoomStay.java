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
 * RoomStay.
 */
public class RoomStay {

  @SerializedName("DepositPayments")
  private DepositPayments depositPayments;
  @SerializedName("DiscountCode")
  private String discountCode;
  @SerializedName("RatePlans")
  private List<RatePlan> ratePlans;
  @SerializedName("RoomRates")
  private List<RoomRate> roomRates;
  @SerializedName("Taxes")
  private Taxes taxes;
  @SerializedName("Total")
  private Total total;

  /**
   * Getter method.
   *
   * @return Gets the value of depositPayments and returns depositPayments.
   */
  public DepositPayments getDepositPayments() {
    return depositPayments;
  }

  /**
   * Sets the depositPayments. You can use getDepositPayments() to get the value of depositPayments.
   */
  public void setDepositPayments(
      DepositPayments depositPayments) {
    this.depositPayments = depositPayments;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discountCode and returns discountCode.
   */
  public String getDiscountCode() {
    return discountCode;
  }

  /**
   * Sets the discountCode. You can use getDiscountCode() to get the value of discountCode.
   */
  public void setDiscountCode(String discountCode) {
    this.discountCode = discountCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of ratePlans and returns ratePlans.
   */
  public List<RatePlan> getRatePlans() {
    return ratePlans;
  }

  /**
   * Sets the ratePlans. You can use getRatePlans() to get the value of ratePlans.
   */
  public void setRatePlans(
      List<RatePlan> ratePlans) {
    this.ratePlans = ratePlans;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of roomRates and returns roomRates.
   */
  public List<RoomRate> getRoomRates() {
    return roomRates;
  }

  /**
   * Sets the roomRates. You can use getRoomRates() to get the value of roomRates.
   */
  public void setRoomRates(
      List<RoomRate> roomRates) {
    this.roomRates = roomRates;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxes and returns taxes.
   */
  public Taxes getTaxes() {
    return taxes;
  }

  /**
   * Sets the taxes. You can use getTaxes() to get the value of taxes.
   */
  public void setTaxes(Taxes taxes) {
    this.taxes = taxes;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of total and returns total.
   */
  public Total getTotal() {
    return total;
  }

  /**
   * Sets the total. You can use getTotal() to get the value of total.
   */
  public void setTotal(Total total) {
    this.total = total;
  }
}
