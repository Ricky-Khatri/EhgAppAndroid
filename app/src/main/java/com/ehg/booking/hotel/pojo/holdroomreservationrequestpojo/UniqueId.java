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
 * UniqueId.
 */
public class UniqueId {

  @SerializedName("Id")
  private String id;
  @SerializedName("IdContext")
  private String idContext;

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

  /**
   * Getter method.
   *
   * @return Gets the value of idContext and returns idContext.
   */
  public String getIdContext() {
    return idContext;
  }

  /**
   * Sets the idContext. You can use getIdContext() to get the value of idContext.
   */
  public void setIdContext(String idContext) {
    this.idContext = idContext;
  }
}
