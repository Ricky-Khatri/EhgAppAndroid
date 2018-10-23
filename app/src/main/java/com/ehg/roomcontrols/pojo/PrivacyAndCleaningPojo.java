/*
 *  Created by Emaar Hospitality Group on 23/10/18 4:18 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 23/10/18 4:18 PM
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

/**
 * PrivacyAndCleaningPjo class.
 */
public class PrivacyAndCleaningPojo {

  private int id;

  private String title;
  private String iconUrl;

  /**
   * Getter method.
   *
   * @return Gets the value of id and returns id.
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the id. You can use getId() to get the value of id.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of title and returns title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title. You can use getTitle() to get the value of title.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of iconUrl and returns iconUrl.
   */
  public String getIconUrl() {
    return iconUrl;
  }

  /**
   * Sets the iconUrl. You can use getIconUrl() to get the value of iconUrl.
   */
  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }
}
