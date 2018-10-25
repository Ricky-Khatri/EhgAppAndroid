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
 * Position class.
 */
public class Position {

  @SerializedName("Latitude")
  private String latitude;
  @SerializedName("Longitude")
  private String longitude;

  /**
   * Getter method.
   *
   * @return Gets the value of latitude and returns latitude.
   */
  public String getLatitude() {
    return latitude;
  }

  /**
   * Sets the latitude. You can use getLatitude() to get the value of latitude.
   */
  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of longitude and returns longitude.
   */
  public String getLongitude() {
    return longitude;
  }

  /**
   * Sets the longitude. You can use getLongitude() to get the value of longitude.
   */
  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }
}
