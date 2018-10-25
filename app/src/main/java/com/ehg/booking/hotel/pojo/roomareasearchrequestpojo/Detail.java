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

package com.ehg.booking.hotel.pojo.roomareasearchrequestpojo;

import com.google.gson.annotations.SerializedName;

/**
 * Detail class.
 */
public class Detail {

  @SerializedName("ChainCode")
  private String chainCode;
  @SerializedName("CurrencyCode")
  private String currencyCode;
  @SerializedName("DeviceId")
  private String deviceId;
  @SerializedName("LanguageCode")
  private String languageCode;
  @SerializedName("LoyaltyMemberId")
  private int loyaltyMemberId;
  @SerializedName("MaxResponses")
  private int maxResponses;
  @SerializedName("MoreDataEchoToken")
  private int moreDataEchoToken;
  @SerializedName("PosSource")
  private PosSource posSource = null;
  @SerializedName("SearchCriteria")
  private SearchCriteria searchCriteria;

  /**
   * Getter method.
   *
   * @return Gets the value of chainCode and returns chainCode.
   */
  public String getChainCode() {
    return chainCode;
  }

  /**
   * Sets the chainCode. You can use getChainCode() to get the value of chainCode.
   */
  public void setChainCode(String chainCode) {
    this.chainCode = chainCode;
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
   * @return Gets the value of deviceId and returns deviceId.
   */
  public String getDeviceId() {
    return deviceId;
  }

  /**
   * Sets the deviceId. You can use getDeviceId() to get the value of deviceId.
   */
  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of languageCode and returns languageCode.
   */
  public String getLanguageCode() {
    return languageCode;
  }

  /**
   * Sets the languageCode. You can use getLanguageCode() to get the value of languageCode.
   */
  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of loyaltyMemberId and returns loyaltyMemberId.
   */
  public int getLoyaltyMemberId() {
    return loyaltyMemberId;
  }

  /**
   * Sets the loyaltyMemberId. You can use getLoyaltyMemberId() to get the value of loyaltyMemberId.
   */
  public void setLoyaltyMemberId(int loyaltyMemberId) {
    this.loyaltyMemberId = loyaltyMemberId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of maxResponses and returns maxResponses.
   */
  public int getMaxResponses() {
    return maxResponses;
  }

  /**
   * Sets the maxResponses. You can use getMaxResponses() to get the value of maxResponses.
   */
  public void setMaxResponses(int maxResponses) {
    this.maxResponses = maxResponses;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of moreDataEchoToken and returns moreDataEchoToken.
   */
  public int getMoreDataEchoToken() {
    return moreDataEchoToken;
  }

  /**
   * Sets the moreDataEchoToken. You can use getMoreDataEchoToken() to get the value of
   * moreDataEchoToken.
   */
  public void setMoreDataEchoToken(int moreDataEchoToken) {
    this.moreDataEchoToken = moreDataEchoToken;
  }

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
  public void setPosSource(PosSource posSource) {
    this.posSource = posSource;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of searchCriteria and returns searchCriteria.
   */
  public SearchCriteria getSearchCriteria() {
    return searchCriteria;
  }

  /**
   * Sets the searchCriteria. You can use getSearchCriteria() to get the value of searchCriteria.
   */
  public void setSearchCriteria(
      SearchCriteria searchCriteria) {
    this.searchCriteria = searchCriteria;
  }
}
