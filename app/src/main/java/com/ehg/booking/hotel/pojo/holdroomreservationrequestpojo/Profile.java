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

/**
 * Profile.
 */
public class Profile {

  @SerializedName("Customer")
  private Customer customer;
  @SerializedName("FrequestGuestId")
  private String frequestGuestId;
  @SerializedName("Rph")
  private String rph;
  @SerializedName("ShareAllMarketInd")
  private String shareAllMarketInd;

  /**
   * Getter method.
   *
   * @return Gets the value of customer and returns customer.
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Sets the customer. You can use getCustomer() to get the value of customer.
   */
  public void setCustomer(
      Customer customer) {
    this.customer = customer;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of frequestGuestId and returns frequestGuestId.
   */
  public String getFrequestGuestId() {
    return frequestGuestId;
  }

  /**
   * Sets the frequestGuestId. You can use getFrequestGuestId() to get the value of frequestGuestId.
   */
  public void setFrequestGuestId(String frequestGuestId) {
    this.frequestGuestId = frequestGuestId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of rph and returns rph.
   */
  public String getRph() {
    return rph;
  }

  /**
   * Sets the rph. You can use getRph() to get the value of rph.
   */
  public void setRph(String rph) {
    this.rph = rph;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of shareAllMarketInd and returns shareAllMarketInd.
   */
  public String getShareAllMarketInd() {
    return shareAllMarketInd;
  }

  /**
   * Sets the shareAllMarketInd. You can use getShareAllMarketInd() to get the value of
   * shareAllMarketInd.
   */
  public void setShareAllMarketInd(String shareAllMarketInd) {
    this.shareAllMarketInd = shareAllMarketInd;
  }
}
