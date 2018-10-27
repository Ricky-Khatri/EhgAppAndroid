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
 * Service.
 */
public class Service {

  @SerializedName("Description")
  private String description;
  @SerializedName("ServiceCategory")
  private String serviceCategory;
  @SerializedName("ServiceName")
  private String serviceName;
  @SerializedName("ServiceOptions")
  private ServiceOptions serviceOptions;
  @SerializedName("ServicePricingType")
  private String servicePricingType;
  @SerializedName("ServiceTypeId")
  private String serviceTypeId;
  @SerializedName("SortOrder")
  private String sortOrder;
  @SerializedName("TotalPrice")
  private TotalPrice totalPrice;

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
   * @return Gets the value of serviceCategory and returns serviceCategory.
   */
  public String getServiceCategory() {
    return serviceCategory;
  }

  /**
   * Sets the serviceCategory. You can use getServiceCategory() to get the value of serviceCategory.
   */
  public void setServiceCategory(String serviceCategory) {
    this.serviceCategory = serviceCategory;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of serviceName and returns serviceName.
   */
  public String getServiceName() {
    return serviceName;
  }

  /**
   * Sets the serviceName. You can use getServiceName() to get the value of serviceName.
   */
  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of serviceOptions and returns serviceOptions.
   */
  public ServiceOptions getServiceOptions() {
    return serviceOptions;
  }

  /**
   * Sets the serviceOptions. You can use getServiceOptions() to get the value of serviceOptions.
   */
  public void setServiceOptions(
      ServiceOptions serviceOptions) {
    this.serviceOptions = serviceOptions;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of servicePricingType and returns servicePricingType.
   */
  public String getServicePricingType() {
    return servicePricingType;
  }

  /**
   * Sets the servicePricingType. You can use getServicePricingType() to get the value of
   * servicePricingType.
   */
  public void setServicePricingType(String servicePricingType) {
    this.servicePricingType = servicePricingType;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of serviceTypeId and returns serviceTypeId.
   */
  public String getServiceTypeId() {
    return serviceTypeId;
  }

  /**
   * Sets the serviceTypeId. You can use getServiceTypeId() to get the value of serviceTypeId.
   */
  public void setServiceTypeId(String serviceTypeId) {
    this.serviceTypeId = serviceTypeId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of sortOrder and returns sortOrder.
   */
  public String getSortOrder() {
    return sortOrder;
  }

  /**
   * Sets the sortOrder. You can use getSortOrder() to get the value of sortOrder.
   */
  public void setSortOrder(String sortOrder) {
    this.sortOrder = sortOrder;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of totalPrice and returns totalPrice.
   */
  public TotalPrice getTotalPrice() {
    return totalPrice;
  }

  /**
   * Sets the totalPrice. You can use getTotalPrice() to get the value of totalPrice.
   */
  public void setTotalPrice(
      TotalPrice totalPrice) {
    this.totalPrice = totalPrice;
  }
}
