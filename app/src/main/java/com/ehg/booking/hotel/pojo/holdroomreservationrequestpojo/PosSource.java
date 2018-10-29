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
 * PosSource.
 */
public class PosSource {

  @SerializedName("BookingChannel")
  private BookingChannel bookingChannel;
  @SerializedName("CompanyName")
  private CompanyName companyName;
  @SerializedName("RequestorIds")
  private List<RequestorId> requestorIds;

  /**
   * Getter method.
   *
   * @return Gets the value of bookingChannel and returns bookingChannel.
   */
  public BookingChannel getBookingChannel() {
    return bookingChannel;
  }

  /**
   * Sets the bookingChannel. You can use getBookingChannel() to get the value of bookingChannel.
   */
  public void setBookingChannel(
      BookingChannel bookingChannel) {
    this.bookingChannel = bookingChannel;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of companyName and returns companyName.
   */
  public CompanyName getCompanyName() {
    return companyName;
  }

  /**
   * Sets the companyName. You can use getCompanyName() to get the value of companyName.
   */
  public void setCompanyName(
      CompanyName companyName) {
    this.companyName = companyName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of requestorIds and returns requestorIds.
   */
  public List<RequestorId> getRequestorIds() {
    return requestorIds;
  }

  /**
   * Sets the requestorIds. You can use getRequestorIds() to get the value of requestorIds.
   */
  public void setRequestorIds(
      List<RequestorId> requestorIds) {
    this.requestorIds = requestorIds;
  }
}
