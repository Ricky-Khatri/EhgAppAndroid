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
 * CancellationPolicy class.
 */
public class CancellationPolicy {

  @SerializedName("CancellationDeadlines")
  private List<CancellationDeadline> cancellationDeadlines;
  @SerializedName("PolicyDescription")
  private PolicyDescription policyDescription;

  /**
   * Getter method.
   *
   * @return Gets the value of cancellationDeadlines and returns cancellationDeadlines.
   */
  public List<CancellationDeadline> getCancellationDeadlines() {
    return cancellationDeadlines;
  }

  /**
   * Sets the cancellationDeadlines. You can use getCancellationDeadlines() to get the value of
   * cancellationDeadlines.
   */
  public void setCancellationDeadlines(
      List<CancellationDeadline> cancellationDeadlines) {
    this.cancellationDeadlines = cancellationDeadlines;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of policyDescription and returns policyDescription.
   */
  public PolicyDescription getPolicyDescription() {
    return policyDescription;
  }

  /**
   * Sets the policyDescription. You can use getPolicyDescription() to get the value of
   * policyDescription.
   */
  public void setPolicyDescription(
      PolicyDescription policyDescription) {
    this.policyDescription = policyDescription;
  }
}
