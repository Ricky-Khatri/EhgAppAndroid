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

/**
 * Package class.
 */
public class Package {

  @SerializedName("Confidential")
  private Boolean confidential;
  @SerializedName("Discount")
  private int discount;
  @SerializedName("IsAvailable")
  private Boolean isAvailable;
  @SerializedName("PackageCatagory")
  private String packageCatagory;
  @SerializedName("PackageCode")
  private String packageCode;
  @SerializedName("PackageDesc")
  private String packageDesc;
  @SerializedName("PackageName")
  private String packageName;
  @SerializedName("Rate")
  private int rate;
  @SerializedName("SortOrder")
  private int sortOrder;

  /**
   * Getter method.
   *
   * @return Gets the value of confidential and returns confidential.
   */
  public Boolean getConfidential() {
    return confidential;
  }

  /**
   * Sets the confidential. You can use getConfidential() to get the value of confidential.
   */
  public void setConfidential(Boolean confidential) {
    this.confidential = confidential;
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
   * @return Gets the value of packageCatagory and returns packageCatagory.
   */
  public String getPackageCatagory() {
    return packageCatagory;
  }

  /**
   * Sets the packageCatagory. You can use getPackageCatagory() to get the value of packageCatagory.
   */
  public void setPackageCatagory(String packageCatagory) {
    this.packageCatagory = packageCatagory;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packageCode and returns packageCode.
   */
  public String getPackageCode() {
    return packageCode;
  }

  /**
   * Sets the packageCode. You can use getPackageCode() to get the value of packageCode.
   */
  public void setPackageCode(String packageCode) {
    this.packageCode = packageCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packageDesc and returns packageDesc.
   */
  public String getPackageDesc() {
    return packageDesc;
  }

  /**
   * Sets the packageDesc. You can use getPackageDesc() to get the value of packageDesc.
   */
  public void setPackageDesc(String packageDesc) {
    this.packageDesc = packageDesc;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packageName and returns packageName.
   */
  public String getPackageName() {
    return packageName;
  }

  /**
   * Sets the packageName. You can use getPackageName() to get the value of packageName.
   */
  public void setPackageName(String packageName) {
    this.packageName = packageName;
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
