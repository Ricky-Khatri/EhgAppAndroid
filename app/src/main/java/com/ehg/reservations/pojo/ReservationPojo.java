/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:43 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 19/9/18 6:28 PM
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

package com.ehg.reservations.pojo;

/**
 * Pojo for reservation fragment.
 */
public class ReservationPojo {

  private String title;
  private String imageUrl;
  private String address;
  private String checkinDate;
  private String checkoutDate;
  private String adults;
  private String childs;

  private boolean isTimeAvailable;

  /**
   * Getter method.
   *
   * @return Gets the value of isTimeAvailable and returns isTimeAvailable.
   */
  public boolean isTimeAvailable() {
    return isTimeAvailable;
  }

  /**
   * Sets the isTimeAvailable. You can use getTimeAvailable() to get the value of isTimeAvailable.
   */
  public void setTimeAvailable(boolean timeAvailable) {
    isTimeAvailable = timeAvailable;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of title and returns title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title. You can use getTitle() to get the value of title.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of imageUrl and returns imageUrl.
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Sets the imageUrl. You can use getImageUrl() to get the value of imageUrl.
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of address and returns address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the address. You can use getAddress() to get the value of address.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of checkinDate and returns checkinDate.
   */
  public String getCheckinDate() {
    return checkinDate;
  }

  /**
   * Sets the checkinDate. You can use getCheckinDate() to get the value of checkinDate.
   */
  public void setCheckinDate(String checkinDate) {
    this.checkinDate = checkinDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of checkoutDate and returns checkoutDate.
   */
  public String getCheckoutDate() {
    return checkoutDate;
  }

  /**
   * Sets the checkoutDate. You can use getCheckoutDate() to get the value of checkoutDate.
   */
  public void setCheckoutDate(String checkoutDate) {
    this.checkoutDate = checkoutDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of adults and returns adults.
   */
  public String getAdults() {
    return adults;
  }

  /**
   * Sets the adults. You can use getAdults() to get the value of adults.
   */
  public void setAdults(String adults) {
    this.adults = adults;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of childs and returns childs.
   */
  public String getChilds() {
    return childs;
  }

  /**
   * Sets the childs. You can use getChilds() to get the value of childs.
   */
  public void setChilds(String childs) {
    this.childs = childs;
  }
}
