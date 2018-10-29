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

package com.ehg.booking.hotel.pojo.roomareasearchresponsepojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * ResponseData class.
 */
public class ResponseData {

  @SerializedName("ChainCode")
  private String chainCode;
  @SerializedName("HotelList")
  private List<HotelList> hotelList;
  @SerializedName("LanguageCode")
  private String languageCode;
  @SerializedName("MoreDataEchoToken")
  private Long moreDataEchoToken;
  @SerializedName("SearchCriteria")
  private SearchCriteria searchCriteria;

  /**
   * Getter method.
   *
   * @return Gets the value of chainCode and returns chainCode.
   */
  public String getChainCode() {
    return chainCode;
  }

  /**
   * Sets the chainCode. You can use getChainCode() to get the value of chainCode.
   */
  public void setChainCode(String chainCode) {
    this.chainCode = chainCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of hotelList and returns hotelList.
   */
  public List<HotelList> getHotelList() {
    return hotelList;
  }

  /**
   * Sets the hotelList. You can use getHotelList() to get the value of hotelList.
   */
  public void setHotelList(
      List<HotelList> hotelList) {
    this.hotelList = hotelList;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of languageCode and returns languageCode.
   */
  public String getLanguageCode() {
    return languageCode;
  }

  /**
   * Sets the languageCode. You can use getLanguageCode() to get the value of languageCode.
   */
  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of moreDataEchoToken and returns moreDataEchoToken.
   */
  public Long getMoreDataEchoToken() {
    return moreDataEchoToken;
  }

  /**
   * Sets the moreDataEchoToken. You can use getMoreDataEchoToken() to get the value of
   * moreDataEchoToken.
   */
  public void setMoreDataEchoToken(Long moreDataEchoToken) {
    this.moreDataEchoToken = moreDataEchoToken;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of searchCriteria and returns searchCriteria.
   */
  public SearchCriteria getSearchCriteria() {
    return searchCriteria;
  }

  /**
   * Sets the searchCriteria. You can use getSearchCriteria() to get the value of searchCriteria.
   */
  public void setSearchCriteria(
      SearchCriteria searchCriteria) {
    this.searchCriteria = searchCriteria;
  }
}
