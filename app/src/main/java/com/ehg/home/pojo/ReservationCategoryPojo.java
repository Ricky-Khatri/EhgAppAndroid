/*
 *  Created by Emaar Hospitality Group on 19/9/18 3:15 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 19/9/18 3:15 PM
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

package com.ehg.home.pojo;

/**
 * Pojo for Reservation horizontal category list.
 */
public class ReservationCategoryPojo {

  private String title;

  private boolean isSelected;

  /**
   * Getter method.
   *
   * @return Gets the value of name and returns title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the name. You can use getTitle() to get the value of title.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isSelected and returns isSelected.
   */
  public boolean isSelected() {
    return isSelected;
  }

  /**
   * Sets the isSelected. You can use getSelected() to get the value of isSelected.
   */
  public void setSelected(boolean selected) {
    isSelected = selected;
  }
}
