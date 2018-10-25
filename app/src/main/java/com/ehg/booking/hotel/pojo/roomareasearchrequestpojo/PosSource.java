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

package com.ehg.booking.hotel.pojo.roomareasearchrequestpojo;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/**
 * PosSource class.
 */
public class PosSource {

  @Expose
  private List<RequestorId> requestorIds = new ArrayList<>();

  /**
   * Getter method.
   *
   * @return Gets the value of requestorIds and returns requestorIds.
   */
  public List<RequestorId> getRequestorIds() {
    return requestorIds;
  }

  /**
   * Sets the requestorIds. You can use getRequestorIds() to get the value of requestorIds.
   */
  public void setRequestorIds(
      List<RequestorId> requestorIds) {
    this.requestorIds = requestorIds;
  }
}
