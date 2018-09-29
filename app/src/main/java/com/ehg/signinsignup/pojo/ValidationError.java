/*
 *  Created by Emaar Hospitality Group on 13/8/18 11:12 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 13/8/18 11:12 AM
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

package com.ehg.signinsignup.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * ValidationError class for UserProfilePojo.
 */
public class ValidationError {

  @SerializedName("errorMessage")
  private String errorMessage;
  @SerializedName("errorNumber")
  private Long errorNumber;

  /**
   * Getter method.
   *
   * @return Gets the value of errorMessage and returns errorMessage.
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Sets the errorMessage. You can use getErrorMessage() to get the value of errorMessage.
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of errorNumber and returns errorNumber.
   */
  public Long getErrorNumber() {
    return errorNumber;
  }

  /**
   * Sets the errorNumber. You can use getErrorNumber() to get the value of errorNumber.
   */
  public void setErrorNumber(Long errorNumber) {
    this.errorNumber = errorNumber;
  }
}
