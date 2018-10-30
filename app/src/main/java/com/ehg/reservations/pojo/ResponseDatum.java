/*
 *  Created by Emaar Hospitality Group on 18/10/18 5:51 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 18/10/18 5:51 PM
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

package com.ehg.reservations.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * ResponseDatum class.
 */
public class ResponseDatum {

  @SerializedName("ConfimationNumber")
  private String confimationNumber;
  @SerializedName("EndDate")
  private String endDate;
  @SerializedName("EntityAddress")
  private String entityAddress;
  @SerializedName("EntityName")
  private String entityName;
  @SerializedName("Guests")
  private int guests;
  @SerializedName("ReservationType")
  private int reservationType;
  @SerializedName("StartDate")
  private String startDate;
  @SerializedName("StartTime")
  private String startTime;

  /**
   * Getter method.
   *
   * @return Gets the value of confimationNumber and returns confimationNumber.
   */
  public String getConfimationNumber() {
    return confimationNumber;
  }

  /**
   * Sets the confimationNumber. You can use getConfimationNumber() to get the value of
   * confimationNumber.
   */
  public void setConfimationNumber(String confimationNumber) {
    this.confimationNumber = confimationNumber;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of endDate and returns endDate.
   */
  public String getEndDate() {
    return endDate;
  }

  /**
   * Sets the endDate. You can use getEndDate() to get the value of endDate.
   */
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of entityAddress and returns entityAddress.
   */
  public String getEntityAddress() {
    return entityAddress;
  }

  /**
   * Sets the entityAddress. You can use getEntityAddress() to get the value of entityAddress.
   */
  public void setEntityAddress(String entityAddress) {
    this.entityAddress = entityAddress;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of entityName and returns entityName.
   */
  public String getEntityName() {
    return entityName;
  }

  /**
   * Sets the entityName. You can use getEntityName() to get the value of entityName.
   */
  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of guests and returns guests.
   */
  public int getGuests() {
    return guests;
  }

  /**
   * Sets the guests. You can use getGuests() to get the value of guests.
   */
  public void setGuests(int guests) {
    this.guests = guests;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of reservationType and returns reservationType.
   */
  public int getReservationType() {
    return reservationType;
  }

  /**
   * Sets the reservationType. You can use getReservationType() to get the value of reservationType.
   */
  public void setReservationType(int reservationType) {
    this.reservationType = reservationType;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of startDate and returns startDate.
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * Sets the startDate. You can use getStartDate() to get the value of startDate.
   */
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of startTime and returns startTime.
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * Sets the startTime. You can use getStartTime() to get the value of startTime.
   */
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }
}
