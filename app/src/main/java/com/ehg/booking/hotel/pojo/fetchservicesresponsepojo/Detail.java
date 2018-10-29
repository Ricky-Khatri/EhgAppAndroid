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
 * Detail class.
 */
public class Detail {

  @SerializedName("ResponseCode")
  private int responseCode;
  @SerializedName("ResponseData")
  private ResponseData responseData;
  @SerializedName("ValidationErrors")
  private List<ValidationError> validationErrors;

  /**
   * Getter method.
   *
   * @return Gets the value of responseCode and returns responseCode.
   */
  public int getResponseCode() {
    return responseCode;
  }

  /**
   * Sets the responseCode. You can use getResponseCode() to get the value of responseCode.
   */
  public void setResponseCode(int responseCode) {
    this.responseCode = responseCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of responseData and returns responseData.
   */
  public ResponseData getResponseData() {
    return responseData;
  }

  /**
   * Sets the responseData. You can use getResponseData() to get the value of responseData.
   */
  public void setResponseData(ResponseData responseData) {
    this.responseData = responseData;
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
   * Sets the validationErrors. You can use getValidationErrors() to get the value of validationErrors.
   */
  public void setValidationErrors(
      List<ValidationError> validationErrors) {
    this.validationErrors = validationErrors;
  }
}
