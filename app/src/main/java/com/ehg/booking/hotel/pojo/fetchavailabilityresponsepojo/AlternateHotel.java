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
 * AlternateHotel class.
 */
public class AlternateHotel {

  @SerializedName("CityName")
  private int cityName;
  @SerializedName("CurrencyCode")
  private String currencyCode;
  @SerializedName("Distances")
  private List<Distance> distances;
  @SerializedName("HotelCode")
  private int hotelCode;
  @SerializedName("HotelName")
  private String hotelName;
  @SerializedName("HotelUrl")
  private String hotelUrl;
  @SerializedName("Media")
  private Media media;
  @SerializedName("Rate")
  private Rate rate;
  @SerializedName("Rating")
  private Rating rating;
  @SerializedName("ShortDescription")
  private String shortDescription;

  /**
   * Getter method.
   *
   * @return Gets the value of cityName and returns cityName.
   */
  public int getCityName() {
    return cityName;
  }

  /**
   * Sets the cityName. You can use getCityName() to get the value of cityName.
   */
  public void setCityName(int cityName) {
    this.cityName = cityName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of currencyCode and returns currencyCode.
   */
  public String getCurrencyCode() {
    return currencyCode;
  }

  /**
   * Sets the currencyCode. You can use getCurrencyCode() to get the value of currencyCode.
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of distances and returns distances.
   */
  public List<Distance> getDistances() {
    return distances;
  }

  /**
   * Sets the distances. You can use getDistances() to get the value of distances.
   */
  public void setDistances(
      List<Distance> distances) {
    this.distances = distances;
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
   * @return Gets the value of hotelUrl and returns hotelUrl.
   */
  public String getHotelUrl() {
    return hotelUrl;
  }

  /**
   * Sets the hotelUrl. You can use getHotelUrl() to get the value of hotelUrl.
   */
  public void setHotelUrl(String hotelUrl) {
    this.hotelUrl = hotelUrl;
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
   * @return Gets the value of rate and returns rate.
   */
  public Rate getRate() {
    return rate;
  }

  /**
   * Sets the rate. You can use getRate() to get the value of rate.
   */
  public void setRate(Rate rate) {
    this.rate = rate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of rating and returns rating.
   */
  public Rating getRating() {
    return rating;
  }

  /**
   * Sets the rating. You can use getRating() to get the value of rating.
   */
  public void setRating(Rating rating) {
    this.rating = rating;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of shortDescription and returns shortDescription.
   */
  public String getShortDescription() {
    return shortDescription;
  }

  /**
   * Sets the shortDescription. You can use getShortDescription() to get the value of
   * shortDescription.
   */
  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }
}
