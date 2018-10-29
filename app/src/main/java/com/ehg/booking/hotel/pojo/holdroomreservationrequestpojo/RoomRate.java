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
 * RoomRate.
 */
public class RoomRate {

  @SerializedName("MainImage")
  private MainImage mainImage;
  @SerializedName("NumberOfUnits")
  private int numberOfUnits;
  @SerializedName("PmsRoomExternalCode")
  private String pmsRoomExternalCode;
  @SerializedName("Rates")
  private List<Rate> rates;
  @SerializedName("RoomExternalCode")
  private String roomExternalCode;
  @SerializedName("RoomTypeCode")
  private String roomTypeCode;
  @SerializedName("RoomTypeName")
  private String roomTypeName;
  @SerializedName("TaxItems")
  private List<TaxItem> taxItems;

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
   * @return Gets the value of pmsRoomExternalCode and returns pmsRoomExternalCode.
   */
  public String getPmsRoomExternalCode() {
    return pmsRoomExternalCode;
  }

  /**
   * Sets the pmsRoomExternalCode. You can use getPmsRoomExternalCode() to get the value of
   * pmsRoomExternalCode.
   */
  public void setPmsRoomExternalCode(String pmsRoomExternalCode) {
    this.pmsRoomExternalCode = pmsRoomExternalCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of rates and returns rates.
   */
  public List<Rate> getRates() {
    return rates;
  }

  /**
   * Sets the rates. You can use getRates() to get the value of rates.
   */
  public void setRates(
      List<Rate> rates) {
    this.rates = rates;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of roomExternalCode and returns roomExternalCode.
   */
  public String getRoomExternalCode() {
    return roomExternalCode;
  }

  /**
   * Sets the roomExternalCode. You can use getRoomExternalCode() to get the value of
   * roomExternalCode.
   */
  public void setRoomExternalCode(String roomExternalCode) {
    this.roomExternalCode = roomExternalCode;
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
   * @return Gets the value of taxItems and returns taxItems.
   */
  public List<TaxItem> getTaxItems() {
    return taxItems;
  }

  /**
   * Sets the taxItems. You can use getTaxItems() to get the value of taxItems.
   */
  public void setTaxItems(
      List<TaxItem> taxItems) {
    this.taxItems = taxItems;
  }
}
