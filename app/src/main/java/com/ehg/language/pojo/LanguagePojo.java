/*
 *  Created by Emaar Hospitality Group on 21/9/18 11:27 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 21/9/18 11:27 AM
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

package com.ehg.language.pojo;

/**
 * Pojo for Language.
 */
public class LanguagePojo {

  private String languageCode;
  private String languageName;

  private boolean isLanguageSelected;

  /**
   * Getter method.
   *
   * @return Gets the value of isLanguageSelected and returns isLanguageSelected.
   */
  public boolean isLanguageSelected() {
    return isLanguageSelected;
  }

  /**
   * Sets the isLanguageSelected. You can use getLanguageSelected() to get the value of
   * isLanguageSelected.
   */
  public void setLanguageSelected(boolean languageSelected) {
    isLanguageSelected = languageSelected;
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
   * @return Gets the value of languageName and returns languageName.
   */
  public String getLanguageName() {
    return languageName;
  }

  /**
   * Sets the languageName. You can use getLanguageName() to get the value of languageName.
   */
  public void setLanguageName(String languageName) {
    this.languageName = languageName;
  }
}
