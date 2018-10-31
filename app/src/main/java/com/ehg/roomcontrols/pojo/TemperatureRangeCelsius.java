/*
 *  Created by Emaar Hospitality Group on 10/8/18 5:47 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 10/8/18 5:47 PM
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
package com.ehg.roomcontrols.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * TemperatureRangeCelsius.
 */
public class TemperatureRangeCelsius {

  @SerializedName("maximum_temperature")
  private String maximumTemperature;
  @SerializedName("minimum_temperature")
  private String minimumTemperature;

  public String getMaximumTemperature() {
    return maximumTemperature;
  }

  public void setMaximumTemperature(String maximumTemperature) {
    this.maximumTemperature = maximumTemperature;
  }

  public String getMinimumTemperature() {
    return minimumTemperature;
  }

  public void setMinimumTemperature(String minimumTemperature) {
    this.minimumTemperature = minimumTemperature;
  }

}
