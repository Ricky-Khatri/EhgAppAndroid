/*
 *  Created by Emaar Hospitality Group on 30/10/18 7:16 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 30/10/18 7:16 PM
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

package com.ehg.ubyemaar.pojo;

/**
 * UpointActivityPojo.
 */
public class UpointActivityPojo {

  private String redemptionId;
  private String redeemedDateTime;
  private String redeemedAmount;
  private String redeemedPoint;
  private String redeemedLocation;

  /**
   * Getter method.
   *
   * @return Gets the value of redemptionId and returns redemptionId.
   */
  public String getRedemptionId() {
    return redemptionId;
  }

  /**
   * Sets the redemptionId. You can use getRedemptionId() to get the value of redemptionId.
   */
  public void setRedemptionId(String redemptionId) {
    this.redemptionId = redemptionId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of redeemedDateTime and returns redeemedDateTime.
   */
  public String getRedeemedDateTime() {
    return redeemedDateTime;
  }

  /**
   * Sets the redeemedDateTime. You can use getRedeemedDateTime() to get the value of
   * redeemedDateTime.
   */
  public void setRedeemedDateTime(String redeemedDateTime) {
    this.redeemedDateTime = redeemedDateTime;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of redeemedAmount and returns redeemedAmount.
   */
  public String getRedeemedAmount() {
    return redeemedAmount;
  }

  /**
   * Sets the redeemedAmount. You can use getRedeemedAmount() to get the value of redeemedAmount.
   */
  public void setRedeemedAmount(String redeemedAmount) {
    this.redeemedAmount = redeemedAmount;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of redeemedPoint and returns redeemedPoint.
   */
  public String getRedeemedPoint() {
    return redeemedPoint;
  }

  /**
   * Sets the redeemedPoint. You can use getRedeemedPoint() to get the value of redeemedPoint.
   */
  public void setRedeemedPoint(String redeemedPoint) {
    this.redeemedPoint = redeemedPoint;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of redeemedLocation and returns redeemedLocation.
   */
  public String getRedeemedLocation() {
    return redeemedLocation;
  }

  /**
   * Sets the redeemedLocation. You can use getRedeemedLocation() to get the value of
   * redeemedLocation.
   */
  public void setRedeemedLocation(String redeemedLocation) {
    this.redeemedLocation = redeemedLocation;
  }
}
