/*
 *  Created by Emaar Hospitality Group on 17/10/18 3:13 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 17/10/18 3:13 PM
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

package com.ehg.booking.hotel;

/**
 * HotelFilterPojo class.
 */
public class HotelFilterpojo {

  private int filterId;

  private String filterTitle;

  /**
   * Getter method.
   *
   * @return Gets the value of filterId and returns filterId.
   */
  public int getFilterId() {
    return filterId;
  }

  /**
   * Sets the filterId. You can use getFilterId() to get the value of filterId.
   */
  public void setFilterId(int filterId) {
    this.filterId = filterId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of filterTitle and returns filterTitle.
   */
  public String getFilterTitle() {
    return filterTitle;
  }

  /**
   * Sets the filterTitle. You can use getFilterTitle() to get the value of filterTitle.
   */
  public void setFilterTitle(String filterTitle) {
    this.filterTitle = filterTitle;
  }
}
