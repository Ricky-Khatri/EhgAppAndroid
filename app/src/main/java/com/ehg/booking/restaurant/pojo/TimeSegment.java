/*
 *  Created by Emaar Hospitality Group on 27/9/18 11:37 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 27/9/18 11:37 AM
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

package com.ehg.booking.restaurant.pojo;

import com.google.gson.annotations.Expose;

/**
 * TimeSegment class for RestaurantFetchAvailabilityPojo.
 */
public class TimeSegment {

  @Expose
  private String reservationDate;
  @Expose
  private String reservationTime;

  /**
   * Getter method.
   *
   * @return Gets the value of reservationDate and returns reservationDate.
   */
  public String getReservationDate() {
    return reservationDate;
  }

  /**
   * Sets the reservationDate. You can use getReservationDate() to get the value of
   * reservationDate.
   */
  public void setReservationDate(String reservationDate) {
    this.reservationDate = reservationDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of reservationTime and returns reservationTime.
   */
  public String getReservationTime() {
    return reservationTime;
  }

  /**
   * Sets the reservationTime. You can use getReservationTime() to get the value of
   * reservationTime.
   */
  public void setReservationTime(String reservationTime) {
    this.reservationTime = reservationTime;
  }
}
