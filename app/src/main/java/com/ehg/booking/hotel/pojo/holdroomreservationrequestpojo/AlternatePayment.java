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
import java.util.List;

/**
 * AlternatePayment.
 */
public class AlternatePayment {

  @SerializedName("AltPayInitXml")
  private String altPayInitXml;
  @SerializedName("AltPayRedirectUrl")
  private String altPayRedirectUrl;
  @SerializedName("AltPayResParamLst")
  private List<AltPayResParamLst> altPayResParamLst;
  @SerializedName("HtmlForm")
  private String htmlForm;
  @SerializedName("Type")
  private String type;
  @SerializedName("UiBgColor")
  private String uiBgColor;
  @SerializedName("UiFontType")
  private String uiFontType;
  @SerializedName("UiTextColor")
  private String uiTextColor;

  /**
   * Getter method.
   *
   * @return Gets the value of altPayInitXml and returns altPayInitXml.
   */
  public String getAltPayInitXml() {
    return altPayInitXml;
  }

  /**
   * Sets the altPayInitXml. You can use getAltPayInitXml() to get the value of altPayInitXml.
   */
  public void setAltPayInitXml(String altPayInitXml) {
    this.altPayInitXml = altPayInitXml;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of altPayRedirectUrl and returns altPayRedirectUrl.
   */
  public String getAltPayRedirectUrl() {
    return altPayRedirectUrl;
  }

  /**
   * Sets the altPayRedirectUrl. You can use getAltPayRedirectUrl() to get the value of
   * altPayRedirectUrl.
   */
  public void setAltPayRedirectUrl(String altPayRedirectUrl) {
    this.altPayRedirectUrl = altPayRedirectUrl;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of altPayResParamLst and returns altPayResParamLst.
   */
  public List<AltPayResParamLst> getAltPayResParamLst() {
    return altPayResParamLst;
  }

  /**
   * Sets the altPayResParamLst. You can use getAltPayResParamLst() to get the value of
   * altPayResParamLst.
   */
  public void setAltPayResParamLst(
      List<AltPayResParamLst> altPayResParamLst) {
    this.altPayResParamLst = altPayResParamLst;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of htmlForm and returns htmlForm.
   */
  public String getHtmlForm() {
    return htmlForm;
  }

  /**
   * Sets the htmlForm. You can use getHtmlForm() to get the value of htmlForm.
   */
  public void setHtmlForm(String htmlForm) {
    this.htmlForm = htmlForm;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of type and returns type.
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type. You can use getType() to get the value of type.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of uiBgColor and returns uiBgColor.
   */
  public String getUiBgColor() {
    return uiBgColor;
  }

  /**
   * Sets the uiBgColor. You can use getUiBgColor() to get the value of uiBgColor.
   */
  public void setUiBgColor(String uiBgColor) {
    this.uiBgColor = uiBgColor;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of uiFontType and returns uiFontType.
   */
  public String getUiFontType() {
    return uiFontType;
  }

  /**
   * Sets the uiFontType. You can use getUiFontType() to get the value of uiFontType.
   */
  public void setUiFontType(String uiFontType) {
    this.uiFontType = uiFontType;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of uiTextColor and returns uiTextColor.
   */
  public String getUiTextColor() {
    return uiTextColor;
  }

  /**
   * Sets the uiTextColor. You can use getUiTextColor() to get the value of uiTextColor.
   */
  public void setUiTextColor(String uiTextColor) {
    this.uiTextColor = uiTextColor;
  }
}
