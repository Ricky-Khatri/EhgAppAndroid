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

package com.ehg.booking.hotel.pojo.roomareasearchresponsepojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * HotelList class.
 */
public class HotelList {

  @SerializedName("Address")
  private Address address;
  @SerializedName("Amenity")
  private List<Amenity> amenity;
  @SerializedName("CurrentCurrency")
  private CurrentCurrency currentCurrency;
  @SerializedName("DefaultRateInfo")
  private DefaultRateInfo defaultRateInfo;
  @SerializedName("Description")
  private String description;
  @SerializedName("Discount")
  private int discount;
  @SerializedName("HotelCode")
  private int hotelCode;
  @SerializedName("HotelName")
  private String hotelName;
  @SerializedName("IsAvailable")
  private Boolean isAvailable;
  @SerializedName("Media")
  private Media media;
  @SerializedName("Packages")
  private List<Package> packages;
  @SerializedName("Position")
  private Position position;
  @SerializedName("Rate")
  private int rate;
  @SerializedName("RatePlans")
  private List<RatePlan> ratePlans;
  @SerializedName("SortOrder")
  private int sortOrder;
  @SerializedName("StarRating")
  private String starRating;

  /**
   * Getter method.
   *
   * @return Gets the value of address and returns address.
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Sets the address. You can use getAddress() to get the value of address.
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of amenity and returns amenity.
   */
  public List<Amenity> getAmenity() {
    return amenity;
  }

  /**
   * Sets the amenity. You can use getAmenity() to get the value of amenity.
   */
  public void setAmenity(
      List<Amenity> amenity) {
    this.amenity = amenity;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of currentCurrency and returns currentCurrency.
   */
  public CurrentCurrency getCurrentCurrency() {
    return currentCurrency;
  }

  /**
   * Sets the currentCurrency. You can use getCurrentCurrency() to get the value of currentCurrency.
   */
  public void setCurrentCurrency(
      CurrentCurrency currentCurrency) {
    this.currentCurrency = currentCurrency;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of defaultRateInfo and returns defaultRateInfo.
   */
  public DefaultRateInfo getDefaultRateInfo() {
    return defaultRateInfo;
  }

  /**
   * Sets the defaultRateInfo. You can use getDefaultRateInfo() to get the value of defaultRateInfo.
   */
  public void setDefaultRateInfo(
      DefaultRateInfo defaultRateInfo) {
    this.defaultRateInfo = defaultRateInfo;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of description and returns description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description. You can use getDescription() to get the value of description.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discount and returns discount.
   */
  public int getDiscount() {
    return discount;
  }

  /**
   * Sets the discount. You can use getDiscount() to get the value of discount.
   */
  public void setDiscount(int discount) {
    this.discount = discount;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of hotelCode and returns hotelCode.
   */
  public int getHotelCode() {
    return hotelCode;
  }

  /**
   * Sets the hotelCode. You can use getHotelCode() to get the value of hotelCode.
   */
  public void setHotelCode(int hotelCode) {
    this.hotelCode = hotelCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of hotelName and returns hotelName.
   */
  public String getHotelName() {
    return hotelName;
  }

  /**
   * Sets the hotelName. You can use getHotelName() to get the value of hotelName.
   */
  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isAvailable and returns isAvailable.
   */
  public Boolean getAvailable() {
    return isAvailable;
  }

  /**
   * Sets the isAvailable. You can use getAvailable() to get the value of isAvailable.
   */
  public void setAvailable(Boolean available) {
    isAvailable = available;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of media and returns media.
   */
  public Media getMedia() {
    return media;
  }

  /**
   * Sets the media. You can use getMedia() to get the value of media.
   */
  public void setMedia(Media media) {
    this.media = media;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packages and returns packages.
   */
  public List<Package> getPackages() {
    return packages;
  }

  /**
   * Sets the packages. You can use getPackages() to get the value of packages.
   */
  public void setPackages(
      List<Package> packages) {
    this.packages = packages;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of position and returns position.
   */
  public Position getPosition() {
    return position;
  }

  /**
   * Sets the position. You can use getPosition() to get the value of position.
   */
  public void setPosition(
      Position position) {
    this.position = position;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of rate and returns rate.
   */
  public int getRate() {
    return rate;
  }

  /**
   * Sets the rate. You can use getRate() to get the value of rate.
   */
  public void setRate(int rate) {
    this.rate = rate;
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

  /**
   * Getter method.
   *
   * @return Gets the value of starRating and returns starRating.
   */
  public String getStarRating() {
    return starRating;
  }

  /**
   * Sets the starRating. You can use getStarRating() to get the value of starRating.
   */
  public void setStarRating(String starRating) {
    this.starRating = starRating;
  }
}
