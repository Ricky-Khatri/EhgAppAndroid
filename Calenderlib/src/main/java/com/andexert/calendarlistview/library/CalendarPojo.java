/*
 *  Created by Emaar Hospitality Group on 26/10/18 12:00 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 26/10/18 12:00 PM
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

package com.andexert.calendarlistview.library;

public class CalendarPojo {

  private static CalendarPojo calendarInstance;

  private int selectedDate;

  /**
   * Getter method.
   *
   * @return Gets the value of intance and returns intance.
   */
  public static CalendarPojo getInstance() {

    if (calendarInstance == null) {
      calendarInstance = new CalendarPojo();
    }
    return calendarInstance;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of selectedDate and returns selectedDate.
   */
  int getSelectedDate() {
    return selectedDate;
  }

  /**
   * Sets the selectedDate. You can use getSelectedDate() to get the value of selectedDate.
   */
  public void setSelectedDate(int selectedDate) {
    this.selectedDate = selectedDate;
  }
}
