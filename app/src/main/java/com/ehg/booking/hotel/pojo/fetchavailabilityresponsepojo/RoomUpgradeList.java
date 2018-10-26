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
 * RoomUpgradeList.
 */
public class RoomUpgradeList {

  @SerializedName("OfferType")
  private String offerType;
  @SerializedName("RoomTypeCode")
  private String roomTypeCode;
  @SerializedName("SortOrder")
  private int sortOrder;

  /**
   * Getter method.
   *
   * @return Gets the value of offerType and returns offerType.
   */
  public String getOfferType() {
    return offerType;
  }

  /**
   * Sets the offerType. You can use getOfferType() to get the value of offerType.
   */
  public void setOfferType(String offerType) {
    this.offerType = offerType;
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
   * @return Gets the value of sortOrder and returns sortOrder.
   */
  public int getSortOrder() {
    return sortOrder;
  }

  /**
   * Sets the sortOrder. You can use getSortOrder() to get the value of sortOrder.
   */
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
}
