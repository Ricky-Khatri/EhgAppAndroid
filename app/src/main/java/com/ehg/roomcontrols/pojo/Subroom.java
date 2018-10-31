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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Subroom {

  @SerializedName("ac_id")
  private String acId;
  @SerializedName("allow_decimal_temperature")
  private Boolean allowDecimalTemperature;
  @SerializedName("allow_eco_mode")
  private Boolean allowEcoMode;
  @SerializedName("curtain_items")
  private List<CurtainItem> curtainItems;
  @SerializedName("default_unit")
  private String defaultUnit;
  @SerializedName("fan_mode")
  private List<String> fanMode;
  @SerializedName("heating_cooling_mode")
  private List<Object> heatingCoolingMode;
  @SerializedName("light_items")
  private List<LightItem> lightItems;
  @SerializedName("mood_items")
  private List<MoodItem> moodItems;
  @Expose
  private List<Object> preset;
  @SerializedName("subroom_title")
  private String subroomTitle;
  @SerializedName("temperature_range_celsius")
  private TemperatureRangeCelsius temperatureRangeCelsius;
  @SerializedName("temperature_range_fahrenheit")
  private TemperatureRangeFahrenheit temperatureRangeFahrenheit;

  /**
   * Getter method.
   *
   * @return Gets the value of acId and returns acId.
   */
  public String getAcId() {
    return acId;
  }

  /**
   * Sets the acId. You can use getAcId() to get the value of acId.
   */
  public void setAcId(String acId) {
    this.acId = acId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of allowDecimalTemperature and returns allowDecimalTemperature.
   */
  public Boolean getAllowDecimalTemperature() {
    return allowDecimalTemperature;
  }

  /**
   * Sets the allowDecimalTemperature. You can use getAllowDecimalTemperature() to get the value of
   * allowDecimalTemperature.
   */
  public void setAllowDecimalTemperature(Boolean allowDecimalTemperature) {
    this.allowDecimalTemperature = allowDecimalTemperature;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of allowEcoMode and returns allowEcoMode.
   */
  public Boolean getAllowEcoMode() {
    return allowEcoMode;
  }

  /**
   * Sets the allowEcoMode. You can use getAllowEcoMode() to get the value of allowEcoMode.
   */
  public void setAllowEcoMode(Boolean allowEcoMode) {
    this.allowEcoMode = allowEcoMode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of curtainItems and returns curtainItems.
   */
  public List<CurtainItem> getCurtainItems() {
    return curtainItems;
  }

  /**
   * Sets the curtainItems. You can use getCurtainItems() to get the value of curtainItems.
   */
  public void setCurtainItems(List<CurtainItem> curtainItems) {
    this.curtainItems = curtainItems;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of defaultUnit and returns defaultUnit.
   */
  public String getDefaultUnit() {
    return defaultUnit;
  }

  /**
   * Sets the defaultUnit. You can use getDefaultUnit() to get the value of defaultUnit.
   */
  public void setDefaultUnit(String defaultUnit) {
    this.defaultUnit = defaultUnit;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of fanMode and returns fanMode.
   */
  public List<String> getFanMode() {
    return fanMode;
  }

  /**
   * Sets the fanMode. You can use getFanMode() to get the value of fanMode.
   */
  public void setFanMode(List<String> fanMode) {
    this.fanMode = fanMode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of heatingCoolingMode and returns heatingCoolingMode.
   */
  public List<Object> getHeatingCoolingMode() {
    return heatingCoolingMode;
  }

  /**
   * Sets the heatingCoolingMode. You can use getHeatingCoolingMode() to get the value of
   * heatingCoolingMode.
   */
  public void setHeatingCoolingMode(List<Object> heatingCoolingMode) {
    this.heatingCoolingMode = heatingCoolingMode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of lightItems and returns lightItems.
   */
  public List<LightItem> getLightItems() {
    return lightItems;
  }

  /**
   * Sets the lightItems. You can use getLightItems() to get the value of lightItems.
   */
  public void setLightItems(List<LightItem> lightItems) {
    this.lightItems = lightItems;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of moodItems and returns moodItems.
   */
  public List<MoodItem> getMoodItems() {
    return moodItems;
  }

  /**
   * Sets the moodItems. You can use getMoodItems() to get the value of moodItems.
   */
  public void setMoodItems(List<MoodItem> moodItems) {
    this.moodItems = moodItems;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of preset and returns preset.
   */
  public List<Object> getPreset() {
    return preset;
  }

  /**
   * Sets the preset. You can use getPreset() to get the value of preset.
   */
  public void setPreset(List<Object> preset) {
    this.preset = preset;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of subroomTitle and returns subroomTitle.
   */
  public String getSubroomTitle() {
    return subroomTitle;
  }

  /**
   * Sets the subroomTitle. You can use getSubroomTitle() to get the value of subroomTitle.
   */
  public void setSubroomTitle(String subroomTitle) {
    this.subroomTitle = subroomTitle;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of temperatureRangeCelsius and returns temperatureRangeCelsius.
   */
  public TemperatureRangeCelsius getTemperatureRangeCelsius() {
    return temperatureRangeCelsius;
  }

  /**
   * Sets the temperatureRangeCelsius. You can use getTemperatureRangeCelsius() to get the value of
   * temperatureRangeCelsius.
   */
  public void setTemperatureRangeCelsius(
      TemperatureRangeCelsius temperatureRangeCelsius) {
    this.temperatureRangeCelsius = temperatureRangeCelsius;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of temperatureRangeFahrenheit and returns temperatureRangeFahrenheit.
   */
  public TemperatureRangeFahrenheit getTemperatureRangeFahrenheit() {
    return temperatureRangeFahrenheit;
  }

  /**
   * Sets the temperatureRangeFahrenheit. You can use getTemperatureRangeFahrenheit() to get the value
   * of temperatureRangeFahrenheit.
   */
  public void setTemperatureRangeFahrenheit(
      TemperatureRangeFahrenheit temperatureRangeFahrenheit) {
    this.temperatureRangeFahrenheit = temperatureRangeFahrenheit;
  }
}
