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
 * CancellationDeadLine class.
 */
public class CancellationDeadline {

  @SerializedName("CheckInDate")
  private String checkInDate;
  @SerializedName("DeadLineDate")
  private String deadLineDate;
  @SerializedName("DeadLineDurationHours")
  private int deadLineDurationHours;
  @SerializedName("DeadLineHour")
  private int deadLineHour;

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
   * @return Gets the value of deadLineDate and returns deadLineDate.
   */
  public String getDeadLineDate() {
    return deadLineDate;
  }

  /**
   * Sets the deadLineDate. You can use getDeadLineDate() to get the value of deadLineDate.
   */
  public void setDeadLineDate(String deadLineDate) {
    this.deadLineDate = deadLineDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of deadLineDurationHours and returns deadLineDurationHours.
   */
  public int getDeadLineDurationHours() {
    return deadLineDurationHours;
  }

  /**
   * Sets the deadLineDurationHours. You can use getDeadLineDurationHours() to get the value of
   * deadLineDurationHours.
   */
  public void setDeadLineDurationHours(int deadLineDurationHours) {
    this.deadLineDurationHours = deadLineDurationHours;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of deadLineHour and returns deadLineHour.
   */
  public int getDeadLineHour() {
    return deadLineHour;
  }

  /**
   * Sets the deadLineHour. You can use getDeadLineHour() to get the value of deadLineHour.
   */
  public void setDeadLineHour(int deadLineHour) {
    this.deadLineHour = deadLineHour;
  }
}
