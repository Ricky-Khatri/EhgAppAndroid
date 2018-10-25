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

package com.ehg.booking.hotel.pojo.roomareasearchpojo;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchCriteria class.
 */
public class SearchCriteria {

  @SerializedName("AmenityIds")
  private List<String> amenityIds;
  @SerializedName("DestinationSearch")
  private DestinationSearch destinationSearch = new DestinationSearch();
  @SerializedName("GuestCounts")
  private List<GuestCount> guestCounts;
  @SerializedName("HotelIds")
  private List<String> hotelIds;
  @SerializedName("NumberOfUnits")
  private int numberOfUnits;
  @SerializedName("Position")
  private Position position = new Position();
  @SerializedName("PriceRange")
  private PriceRange priceRange = new PriceRange();
  @SerializedName("Radius")
  private Radius radius = new Radius();
  @SerializedName("RatePlanCategory")
  private List<String> ratePlanCategory = new ArrayList<>();
  @SerializedName("RatePlanCodes")
  private List<String> ratePlanCodes = new ArrayList<>();
  @SerializedName("SearchResultSortOrder")
  private int searchResultSortOrder;
  @SerializedName("StarRatings")
  private List<String> starRatings = new ArrayList<>();
  @SerializedName("TimeSpan")
  private TimeSpan timeSpan;

  /**
   * Getter method.
   *
   * @return Gets the value of amenityIds and returns amenityIds.
   */
  public List<String> getAmenityIds() {
    return amenityIds;
  }

  /**
   * Sets the amenityIds. You can use getAmenityIds() to get the value of amenityIds.
   */
  public void setAmenityIds(List<String> amenityIds) {
    this.amenityIds = amenityIds;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of destinationSearch and returns destinationSearch.
   */
  public DestinationSearch getDestinationSearch() {
    return destinationSearch;
  }

  /**
   * Sets the destinationSearch. You can use getDestinationSearch() to get the value of
   * destinationSearch.
   */
  public void setDestinationSearch(
      DestinationSearch destinationSearch) {
    this.destinationSearch = destinationSearch;
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
   * @return Gets the value of hotelIds and returns hotelIds.
   */
  public List<String> getHotelIds() {
    return hotelIds;
  }

  /**
   * Sets the hotelIds. You can use getHotelIds() to get the value of hotelIds.
   */
  public void setHotelIds(List<String> hotelIds) {
    this.hotelIds = hotelIds;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of numberOfUnits and returns numberOfUnits.
   */
  public int getNumberOfUnits() {
    return numberOfUnits;
  }

  /**
   * Sets the numberOfUnits. You can use getNumberOfUnits() to get the value of numberOfUnits.
   */
  public void setNumberOfUnits(int numberOfUnits) {
    this.numberOfUnits = numberOfUnits;
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
  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of priceRange and returns priceRange.
   */
  public PriceRange getPriceRange() {
    return priceRange;
  }

  /**
   * Sets the priceRange. You can use getPriceRange() to get the value of priceRange.
   */
  public void setPriceRange(PriceRange priceRange) {
    this.priceRange = priceRange;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of radius and returns radius.
   */
  public Radius getRadius() {
    return radius;
  }

  /**
   * Sets the radius. You can use getRadius() to get the value of radius.
   */
  public void setRadius(Radius radius) {
    this.radius = radius;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of ratePlanCategory and returns ratePlanCategory.
   */
  public List<String> getRatePlanCategory() {
    return ratePlanCategory;
  }

  /**
   * Sets the ratePlanCategory. You can use getRatePlanCategory() to get the value of
   * ratePlanCategory.
   */
  public void setRatePlanCategory(List<String> ratePlanCategory) {
    this.ratePlanCategory = ratePlanCategory;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of ratePlanCodes and returns ratePlanCodes.
   */
  public List<String> getRatePlanCodes() {
    return ratePlanCodes;
  }

  /**
   * Sets the ratePlanCodes. You can use getRatePlanCodes() to get the value of ratePlanCodes.
   */
  public void setRatePlanCodes(List<String> ratePlanCodes) {
    this.ratePlanCodes = ratePlanCodes;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of searchResultSortOrder and returns searchResultSortOrder.
   */
  public int getSearchResultSortOrder() {
    return searchResultSortOrder;
  }

  /**
   * Sets the searchResultSortOrder. You can use getSearchResultSortOrder() to get the value of
   * searchResultSortOrder.
   */
  public void setSearchResultSortOrder(int searchResultSortOrder) {
    this.searchResultSortOrder = searchResultSortOrder;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of starRatings and returns starRatings.
   */
  public List<String> getStarRatings() {
    return starRatings;
  }

  /**
   * Sets the starRatings. You can use getStarRatings() to get the value of starRatings.
   */
  public void setStarRatings(List<String> starRatings) {
    this.starRatings = starRatings;
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
  public void setTimeSpan(TimeSpan timeSpan) {
    this.timeSpan = timeSpan;
  }
}
