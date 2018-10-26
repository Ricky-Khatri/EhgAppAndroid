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
 * RoomType.
 */
public class RoomType {

  @SerializedName("Amenities")
  private List<Amenity> amenities;
  @SerializedName("Available")
  private Boolean available;
  @SerializedName("AverageRates")
  private List<AverageRate> averageRates;
  @SerializedName("CategoryCode")
  private String categoryCode;
  @SerializedName("Description")
  private String description;
  @SerializedName("DisplayUrgencyMessage")
  private Boolean displayUrgencyMessage;
  @SerializedName("MainImage")
  private MainImage mainImage;
  @SerializedName("Medias")
  private List<Media> medias;
  @SerializedName("NightlyRates")
  private List<NightlyRate> nightlyRates;
  @SerializedName("QuantityRemaining")
  private String quantityRemaining;
  @SerializedName("RoomFeatures")
  private List<RoomFeature> roomFeatures;
  @SerializedName("RoomTypeCode")
  private String roomTypeCode;
  @SerializedName("RoomTypeName")
  private String roomTypeName;
  @SerializedName("RoomUpgradeOptions")
  private List<RoomUpgradeOption> roomUpgradeOptions;
  @SerializedName("SortOrder")
  private int sortOrder;

  /**
   * Getter method.
   *
   * @return Gets the value of amenities and returns amenities.
   */
  public List<Amenity> getAmenities() {
    return amenities;
  }

  /**
   * Sets the amenities. You can use getAmenities() to get the value of amenities.
   */
  public void setAmenities(
      List<Amenity> amenities) {
    this.amenities = amenities;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of available and returns available.
   */
  public Boolean getAvailable() {
    return available;
  }

  /**
   * Sets the available. You can use getAvailable() to get the value of available.
   */
  public void setAvailable(Boolean available) {
    this.available = available;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of averageRates and returns averageRates.
   */
  public List<AverageRate> getAverageRates() {
    return averageRates;
  }

  /**
   * Sets the averageRates. You can use getAverageRates() to get the value of averageRates.
   */
  public void setAverageRates(
      List<AverageRate> averageRates) {
    this.averageRates = averageRates;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of categoryCode and returns categoryCode.
   */
  public String getCategoryCode() {
    return categoryCode;
  }

  /**
   * Sets the categoryCode. You can use getCategoryCode() to get the value of categoryCode.
   */
  public void setCategoryCode(String categoryCode) {
    this.categoryCode = categoryCode;
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
   * @return Gets the value of displayUrgencyMessage and returns displayUrgencyMessage.
   */
  public Boolean getDisplayUrgencyMessage() {
    return displayUrgencyMessage;
  }

  /**
   * Sets the displayUrgencyMessage. You can use getDisplayUrgencyMessage() to get the value of
   * displayUrgencyMessage.
   */
  public void setDisplayUrgencyMessage(Boolean displayUrgencyMessage) {
    this.displayUrgencyMessage = displayUrgencyMessage;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of mainImage and returns mainImage.
   */
  public MainImage getMainImage() {
    return mainImage;
  }

  /**
   * Sets the mainImage. You can use getMainImage() to get the value of mainImage.
   */
  public void setMainImage(
      MainImage mainImage) {
    this.mainImage = mainImage;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of medias and returns medias.
   */
  public List<Media> getMedias() {
    return medias;
  }

  /**
   * Sets the medias. You can use getMedias() to get the value of medias.
   */
  public void setMedias(
      List<Media> medias) {
    this.medias = medias;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of nightlyRates and returns nightlyRates.
   */
  public List<NightlyRate> getNightlyRates() {
    return nightlyRates;
  }

  /**
   * Sets the nightlyRates. You can use getNightlyRates() to get the value of nightlyRates.
   */
  public void setNightlyRates(
      List<NightlyRate> nightlyRates) {
    this.nightlyRates = nightlyRates;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of quantityRemaining and returns quantityRemaining.
   */
  public String getQuantityRemaining() {
    return quantityRemaining;
  }

  /**
   * Sets the quantityRemaining. You can use getQuantityRemaining() to get the value of
   * quantityRemaining.
   */
  public void setQuantityRemaining(String quantityRemaining) {
    this.quantityRemaining = quantityRemaining;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of roomFeatures and returns roomFeatures.
   */
  public List<RoomFeature> getRoomFeatures() {
    return roomFeatures;
  }

  /**
   * Sets the roomFeatures. You can use getRoomFeatures() to get the value of roomFeatures.
   */
  public void setRoomFeatures(
      List<RoomFeature> roomFeatures) {
    this.roomFeatures = roomFeatures;
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
   * @return Gets the value of roomTypeName and returns roomTypeName.
   */
  public String getRoomTypeName() {
    return roomTypeName;
  }

  /**
   * Sets the roomTypeName. You can use getRoomTypeName() to get the value of roomTypeName.
   */
  public void setRoomTypeName(String roomTypeName) {
    this.roomTypeName = roomTypeName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of roomUpgradeOptions and returns roomUpgradeOptions.
   */
  public List<RoomUpgradeOption> getRoomUpgradeOptions() {
    return roomUpgradeOptions;
  }

  /**
   * Sets the roomUpgradeOptions. You can use getRoomUpgradeOptions() to get the value of
   * roomUpgradeOptions.
   */
  public void setRoomUpgradeOptions(
      List<RoomUpgradeOption> roomUpgradeOptions) {
    this.roomUpgradeOptions = roomUpgradeOptions;
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
