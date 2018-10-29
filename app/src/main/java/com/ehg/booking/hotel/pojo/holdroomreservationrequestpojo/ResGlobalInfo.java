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
 * ResGlobalInfo.
 */
public class ResGlobalInfo {

  @SerializedName("ArrivalTime")
  private String arrivalTime;
  @SerializedName("Comments")
  private List<Comment> comments;
  @SerializedName("GuaranteesAccepted")
  private List<GuaranteesAccepted> guaranteesAccepted;
  @SerializedName("GuestCounts")
  private List<GuestCount> guestCounts;
  @SerializedName("Rooms")
  private String rooms;
  @SerializedName("TimeSpan")
  private TimeSpan timeSpan;

  /**
   * Getter method.
   *
   * @return Gets the value of arrivalTime and returns arrivalTime.
   */
  public String getArrivalTime() {
    return arrivalTime;
  }

  /**
   * Sets the arrivalTime. You can use getArrivalTime() to get the value of arrivalTime.
   */
  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of comments and returns comments.
   */
  public List<Comment> getComments() {
    return comments;
  }

  /**
   * Sets the comments. You can use getComments() to get the value of comments.
   */
  public void setComments(
      List<Comment> comments) {
    this.comments = comments;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of guaranteesAccepted and returns guaranteesAccepted.
   */
  public List<GuaranteesAccepted> getGuaranteesAccepted() {
    return guaranteesAccepted;
  }

  /**
   * Sets the guaranteesAccepted. You can use getGuaranteesAccepted() to get the value of
   * guaranteesAccepted.
   */
  public void setGuaranteesAccepted(
      List<GuaranteesAccepted> guaranteesAccepted) {
    this.guaranteesAccepted = guaranteesAccepted;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of guestCounts and returns guestCounts.
   */
  public List<GuestCount> getGuestCounts() {
    return guestCounts;
  }

  /**
   * Sets the guestCounts. You can use getGuestCounts() to get the value of guestCounts.
   */
  public void setGuestCounts(
      List<GuestCount> guestCounts) {
    this.guestCounts = guestCounts;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of rooms and returns rooms.
   */
  public String getRooms() {
    return rooms;
  }

  /**
   * Sets the rooms. You can use getRooms() to get the value of rooms.
   */
  public void setRooms(String rooms) {
    this.rooms = rooms;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of timeSpan and returns timeSpan.
   */
  public TimeSpan getTimeSpan() {
    return timeSpan;
  }

  /**
   * Sets the timeSpan. You can use getTimeSpan() to get the value of timeSpan.
   */
  public void setTimeSpan(
      TimeSpan timeSpan) {
    this.timeSpan = timeSpan;
  }
}
