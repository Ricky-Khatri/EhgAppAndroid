/*
 *  Created by Emaar Hospitality Group on 28/9/18 1:08 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 28/9/18 1:08 PM
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

import java.util.ArrayList;

/**
 * Data class of UserProfile Pojo class.
 */
public class Data {

  public ArrayList<Object> detail = new ArrayList<Object>();

  /**
   * Getter method.
   *
   * @return Gets the value of detail and returns detail.
   */
  public ArrayList<Object> getDetail() {
    return detail;
  }

  /**
   * Sets the detail. You can use getDetail() to get the value of detail.
   */
  public void setDetail(ArrayList<Object> detail) {
    this.detail = detail;
  }
}