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

package com.ehg.booking.hotel.pojo.fetchservicesresponsepojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * ServiceDetail class.
 */
public class ServiceDetail {

  @SerializedName("AutoSchedule")
  private Boolean autoSchedule;
  @SerializedName("Description")
  private String description;
  @SerializedName("IsMandatory")
  private Boolean isMandatory;
  @SerializedName("ServiceCategory")
  private String serviceCategory;
  @SerializedName("ServiceName")
  private String serviceName;
  @SerializedName("ServiceOptions")
  private List<ServiceOption> serviceOptions;
  @SerializedName("ServicePricingType")
  private String servicePricingType;
  @SerializedName("ServiceTypeId")
  private String serviceTypeId;
  @SerializedName("SortOrder")
  private int sortOrder;

  /**
   * Getter method.
   *
   * @return Gets the value of autoSchedule and returns autoSchedule.
   */
  public Boolean getAutoSchedule() {
    return autoSchedule;
  }

  /**
   * Sets the autoSchedule. You can use getAutoSchedule() to get the value of autoSchedule.
   */
  public void setAutoSchedule(Boolean autoSchedule) {
    this.autoSchedule = autoSchedule;
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
   * @return Gets the value of isMandatory and returns isMandatory.
   */
  public Boolean getMandatory() {
    return isMandatory;
  }

  /**
   * Sets the isMandatory. You can use getMandatory() to get the value of isMandatory.
   */
  public void setMandatory(Boolean mandatory) {
    isMandatory = mandatory;
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
  public List<ServiceOption> getServiceOptions() {
    return serviceOptions;
  }

  /**
   * Sets the serviceOptions. You can use getServiceOptions() to get the value of serviceOptions.
   */
  public void setServiceOptions(
      List<ServiceOption> serviceOptions) {
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
   * Sets the servicePricingType. You can use getServicePricingType() to get the value of servicePricingType.
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
