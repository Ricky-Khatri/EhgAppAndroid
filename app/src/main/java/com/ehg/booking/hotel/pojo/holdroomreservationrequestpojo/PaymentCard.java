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
 * PaymentCard.
 */
public class PaymentCard {

  @SerializedName("CardCode")
  private String cardCode;
  @SerializedName("CardHolderInfoRequired")
  private String cardHolderInfoRequired;
  @SerializedName("CardHolderName")
  private String cardHolderName;
  @SerializedName("CardNumber")
  private String cardNumber;
  @SerializedName("CardType")
  private String cardType;
  @SerializedName("ExpireDate")
  private String expireDate;
  @SerializedName("IssueNum")
  private String issueNum;
  @SerializedName("SeriesCode")
  private String seriesCode;
  @SerializedName("StartDate")
  private String startDate;

  /**
   * Getter method.
   *
   * @return Gets the value of cardCode and returns cardCode.
   */
  public String getCardCode() {
    return cardCode;
  }

  /**
   * Sets the cardCode. You can use getCardCode() to get the value of cardCode.
   */
  public void setCardCode(String cardCode) {
    this.cardCode = cardCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of cardHolderInfoRequired and returns cardHolderInfoRequired.
   */
  public String getCardHolderInfoRequired() {
    return cardHolderInfoRequired;
  }

  /**
   * Sets the cardHolderInfoRequired. You can use getCardHolderInfoRequired() to get the value of
   * cardHolderInfoRequired.
   */
  public void setCardHolderInfoRequired(String cardHolderInfoRequired) {
    this.cardHolderInfoRequired = cardHolderInfoRequired;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of cardHolderName and returns cardHolderName.
   */
  public String getCardHolderName() {
    return cardHolderName;
  }

  /**
   * Sets the cardHolderName. You can use getCardHolderName() to get the value of cardHolderName.
   */
  public void setCardHolderName(String cardHolderName) {
    this.cardHolderName = cardHolderName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of cardNumber and returns cardNumber.
   */
  public String getCardNumber() {
    return cardNumber;
  }

  /**
   * Sets the cardNumber. You can use getCardNumber() to get the value of cardNumber.
   */
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of cardType and returns cardType.
   */
  public String getCardType() {
    return cardType;
  }

  /**
   * Sets the cardType. You can use getCardType() to get the value of cardType.
   */
  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of expireDate and returns expireDate.
   */
  public String getExpireDate() {
    return expireDate;
  }

  /**
   * Sets the expireDate. You can use getExpireDate() to get the value of expireDate.
   */
  public void setExpireDate(String expireDate) {
    this.expireDate = expireDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of issueNum and returns issueNum.
   */
  public String getIssueNum() {
    return issueNum;
  }

  /**
   * Sets the issueNum. You can use getIssueNum() to get the value of issueNum.
   */
  public void setIssueNum(String issueNum) {
    this.issueNum = issueNum;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of seriesCode and returns seriesCode.
   */
  public String getSeriesCode() {
    return seriesCode;
  }

  /**
   * Sets the seriesCode. You can use getSeriesCode() to get the value of seriesCode.
   */
  public void setSeriesCode(String seriesCode) {
    this.seriesCode = seriesCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of startDate and returns startDate.
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * Sets the startDate. You can use getStartDate() to get the value of startDate.
   */
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
}
