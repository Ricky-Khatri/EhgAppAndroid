/*
 *  Created by Emaar Hospitality Group on 27/9/18 11:37 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 27/9/18 11:37 AM
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

package com.ehg.booking.restaurant.pojo;

import com.google.gson.annotations.Expose;
import java.util.List;

/**
 * Detail class for RestaurantFetchAvailabilityPojo.
 */
public class Detail {

  @Expose
  private Long partySize;
  @Expose
  private Long responseCode;
  @Expose
  private Long restaurantId;
  @Expose
  private List<TimeSegment> timeSegments;
  @Expose
  private List<ValidationError> validationErrors;

  /**
   * Getter method.
   *
   * @return Gets the value of partySize and returns partySize.
   */
  public Long getPartySize() {
    return partySize;
  }

  /**
   * Sets the partySize. You can use getPartySize() to get the value of partySize.
   */
  public void setPartySize(Long partySize) {
    this.partySize = partySize;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of responseCode and returns responseCode.
   */
  public Long getResponseCode() {
    return responseCode;
  }

  /**
   * Sets the responseCode. You can use getResponseCode() to get the value of responseCode.
   */
  public void setResponseCode(Long responseCode) {
    this.responseCode = responseCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of restaurantId and returns restaurantId.
   */
  public Long getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the restaurantId. You can use getRestaurantId() to get the value of restaurantId.
   */
  public void setRestaurantId(Long restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of timeSegments and returns timeSegments.
   */
  public List<TimeSegment> getTimeSegments() {
    return timeSegments;
  }

  /**
   * Sets the timeSegments. You can use getTimeSegments() to get the value of timeSegments.
   */
  public void setTimeSegments(
      List<TimeSegment> timeSegments) {
    this.timeSegments = timeSegments;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of validationErrors and returns validationErrors.
   */
  public List<ValidationError> getValidationErrors() {
    return validationErrors;
  }

  /**
   * Sets the validationErrors. You can use getValidationErrors() to get the value of
   * validationErrors.
   */
  public void setValidationErrors(
      List<ValidationError> validationErrors) {
    this.validationErrors = validationErrors;
  }
}
