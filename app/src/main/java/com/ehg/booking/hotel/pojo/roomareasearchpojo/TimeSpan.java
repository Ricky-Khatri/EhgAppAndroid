
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

package com.ehg.booking.hotel.pojo.roomareasearchpojo;

import com.google.gson.annotations.SerializedName;

/**
 * TimeSpan class.
 */
public class TimeSpan {

  @SerializedName("End")
  private String end;
  @SerializedName("Start")
  private String start;

  /**
   * Getter method.
   *
   * @return Gets the value of end and returns end.
   */
  public String getEnd() {
    return end;
  }

  /**
   * Sets the end. You can use getEnd() to get the value of end.
   */
  public void setEnd(String end) {
    this.end = end;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of start and returns start.
   */
  public String getStart() {
    return start;
  }

  /**
   * Sets the start. You can use getStart() to get the value of start.
   */
  public void setStart(String start) {
    this.start = start;
  }
}
