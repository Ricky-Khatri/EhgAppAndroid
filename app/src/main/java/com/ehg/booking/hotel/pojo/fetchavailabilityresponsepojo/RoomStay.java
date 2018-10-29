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
import java.util.List;

/**
 * RoomStay.
 */
public class RoomStay {

  @SerializedName("AllRoomTypes")
  private List<AllRoomType> allRoomTypes;
  @SerializedName("MandatoryServices")
  private List<MandatoryService> mandatoryServices;
  @SerializedName("PackageCategories")
  private List<PackageCategory> packageCategories;
  @SerializedName("PackageTypes")
  private List<PackageType> packageTypes;
  @SerializedName("RatePlans")
  private List<RatePlan> ratePlans;
  @SerializedName("RoomCategories")
  private List<RoomCategory> roomCategories;
  @SerializedName("RoomTypes")
  private List<RoomType> roomTypes;
  @SerializedName("TimeSpan")
  private TimeSpan timeSpan;

  /**
   * Getter method.
   *
   * @return Gets the value of allRoomTypes and returns allRoomTypes.
   */
  public List<AllRoomType> getAllRoomTypes() {
    return allRoomTypes;
  }

  /**
   * Sets the allRoomTypes. You can use getAllRoomTypes() to get the value of allRoomTypes.
   */
  public void setAllRoomTypes(
      List<AllRoomType> allRoomTypes) {
    this.allRoomTypes = allRoomTypes;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of mandatoryServices and returns mandatoryServices.
   */
  public List<MandatoryService> getMandatoryServices() {
    return mandatoryServices;
  }

  /**
   * Sets the mandatoryServices. You can use getMandatoryServices() to get the value of
   * mandatoryServices.
   */
  public void setMandatoryServices(
      List<MandatoryService> mandatoryServices) {
    this.mandatoryServices = mandatoryServices;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packageCategories and returns packageCategories.
   */
  public List<PackageCategory> getPackageCategories() {
    return packageCategories;
  }

  /**
   * Sets the packageCategories. You can use getPackageCategories() to get the value of
   * packageCategories.
   */
  public void setPackageCategories(
      List<PackageCategory> packageCategories) {
    this.packageCategories = packageCategories;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packageTypes and returns packageTypes.
   */
  public List<PackageType> getPackageTypes() {
    return packageTypes;
  }

  /**
   * Sets the packageTypes. You can use getPackageTypes() to get the value of packageTypes.
   */
  public void setPackageTypes(
      List<PackageType> packageTypes) {
    this.packageTypes = packageTypes;
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
   * @return Gets the value of roomCategories and returns roomCategories.
   */
  public List<RoomCategory> getRoomCategories() {
    return roomCategories;
  }

  /**
   * Sets the roomCategories. You can use getRoomCategories() to get the value of roomCategories.
   */
  public void setRoomCategories(
      List<RoomCategory> roomCategories) {
    this.roomCategories = roomCategories;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of roomTypes and returns roomTypes.
   */
  public List<RoomType> getRoomTypes() {
    return roomTypes;
  }

  /**
   * Sets the roomTypes. You can use getRoomTypes() to get the value of roomTypes.
   */
  public void setRoomTypes(
      List<RoomType> roomTypes) {
    this.roomTypes = roomTypes;
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