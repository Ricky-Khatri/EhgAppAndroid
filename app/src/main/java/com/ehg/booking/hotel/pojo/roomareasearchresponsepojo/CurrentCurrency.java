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

/**
 * CurrentCurrency class.
 */
public class CurrentCurrency {

  @SerializedName("ChainCurrecnyExchangeRate")
  private int chainCurrecnyExchangeRate;
  @SerializedName("CurrencySymbol")
  private String currencySymbol;
  @SerializedName("ExchangeRate")
  private int exchangeRate;
  @SerializedName("IsDefaultCurrency")
  private Boolean isDefaultCurrency;

  /**
   * Getter method.
   *
   * @return Gets the value of chainCurrecnyExchangeRate and returns chainCurrecnyExchangeRate.
   */
  public int getChainCurrecnyExchangeRate() {
    return chainCurrecnyExchangeRate;
  }

  /**
   * Sets the chainCurrecnyExchangeRate. You can use getChainCurrecnyExchangeRate() to get the value
   * of chainCurrecnyExchangeRate.
   */
  public void setChainCurrecnyExchangeRate(int chainCurrecnyExchangeRate) {
    this.chainCurrecnyExchangeRate = chainCurrecnyExchangeRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of currencySymbol and returns currencySymbol.
   */
  public String getCurrencySymbol() {
    return currencySymbol;
  }

  /**
   * Sets the currencySymbol. You can use getCurrencySymbol() to get the value of currencySymbol.
   */
  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of exchangeRate and returns exchangeRate.
   */
  public int getExchangeRate() {
    return exchangeRate;
  }

  /**
   * Sets the exchangeRate. You can use getExchangeRate() to get the value of exchangeRate.
   */
  public void setExchangeRate(int exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isDefaultCurrency and returns isDefaultCurrency.
   */
  public Boolean getDefaultCurrency() {
    return isDefaultCurrency;
  }

  /**
   * Sets the isDefaultCurrency. You can use getDefaultCurrency() to get the value of
   * isDefaultCurrency.
   */
  public void setDefaultCurrency(Boolean defaultCurrency) {
    isDefaultCurrency = defaultCurrency;
  }
}
