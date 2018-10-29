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
 * AltPayResParamLst.
 */
public class AltPayResParamLst {

  @SerializedName("ParamKey")
  private String paramKey;
  @SerializedName("ParamValue")
  private String paramValue;

  /**
   * Getter method.
   *
   * @return Gets the value of paramKey and returns paramKey.
   */
  public String getParamKey() {
    return paramKey;
  }

  /**
   * Sets the paramKey. You can use getParamKey() to get the value of paramKey.
   */
  public void setParamKey(String paramKey) {
    this.paramKey = paramKey;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of paramValue and returns paramValue.
   */
  public String getParamValue() {
    return paramValue;
  }

  /**
   * Sets the paramValue. You can use getParamValue() to get the value of paramValue.
   */
  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }
}
