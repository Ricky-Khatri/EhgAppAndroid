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

package com.ehg.booking.hotel.pojo.roomareasearchpojo;

import com.google.gson.annotations.SerializedName;

/**
 * RequestorId class.
 */
public class RequestorId {

  @SerializedName("CodeType")
  private String codeType;
  @SerializedName("Id")
  private String id;

  /**
   * Getter method.
   *
   * @return Gets the value of codeType and returns codeType.
   */
  public String getCodeType() {
    return codeType;
  }

  /**
   * Sets the codeType. You can use getCodeType() to get the value of codeType.
   */
  public void setCodeType(String codeType) {
    this.codeType = codeType;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of id and returns id.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id. You can use getId() to get the value of id.
   */
  public void setId(String id) {
    this.id = id;
  }
}
