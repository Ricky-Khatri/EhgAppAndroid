/*
 *  Created by Emaar Hospitality Group on 13/8/18 11:12 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 13/8/18 11:12 AM
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

package com.ehg.signinsignup.pojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Data class for UserProfilePojo.
 */
public class Data {

  @SerializedName("detail")
  private List<Detail> detail;

  /**
   * Getter method.
   *
   * @return Gets the value of detail and returns detail.
   */
  public List<Detail> getDetail() {
    return detail;
  }

  /**
   * Sets the detail. You can use getDetail() to get the value of detail.
   */
  public void setDetail(List<Detail> detail) {
    this.detail = detail;
  }
}
