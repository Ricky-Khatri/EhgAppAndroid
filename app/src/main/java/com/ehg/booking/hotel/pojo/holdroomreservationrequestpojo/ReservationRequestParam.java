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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * ReservationRequestParam.
 */
public class ReservationRequestParam {

  @SerializedName("PosSource")
  private PosSource posSource = new PosSource();
  @SerializedName("ResGlobalInfo")
  private ResGlobalInfo resGlobalInfo;
  @SerializedName("ResGuests")
  private List<ResGuest> resGuests;
  @SerializedName("RoomStays")
  private List<RoomStay> roomStays;
  @Expose
  private List<Service> services;

  /**
   * Getter method.
   *
   * @return Gets the value of posSource and returns posSource.
   */
  public PosSource getPosSource() {
    return posSource;
  }

  /**
   * Sets the posSource. You can use getPosSource() to get the value of posSource.
   */
  public void setPosSource(
      PosSource posSource) {
    this.posSource = posSource;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of resGlobalInfo and returns resGlobalInfo.
   */
  public ResGlobalInfo getResGlobalInfo() {
    return resGlobalInfo;
  }

  /**
   * Sets the resGlobalInfo. You can use getResGlobalInfo() to get the value of resGlobalInfo.
   */
  public void setResGlobalInfo(
      ResGlobalInfo resGlobalInfo) {
    this.resGlobalInfo = resGlobalInfo;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of resGuests and returns resGuests.
   */
  public List<ResGuest> getResGuests() {
    return resGuests;
  }

  /**
   * Sets the resGuests. You can use getResGuests() to get the value of resGuests.
   */
  public void setResGuests(
      List<ResGuest> resGuests) {
    this.resGuests = resGuests;
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

  /**
   * Getter method.
   *
   * @return Gets the value of services and returns services.
   */
  public List<Service> getServices() {
    return services;
  }

  /**
   * Sets the services. You can use getServices() to get the value of services.
   */
  public void setServices(
      List<Service> services) {
    this.services = services;
  }
}
