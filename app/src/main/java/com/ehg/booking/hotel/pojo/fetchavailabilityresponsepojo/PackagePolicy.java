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

package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.SerializedName;

/**
 * PackagePolicy.
 */
public class PackagePolicy {

  @SerializedName("Code")
  private String code;
  @SerializedName("PkgPolicyDesc")
  private String pkgPolicyDesc;
  @SerializedName("PkgPolicyTitle")
  private String pkgPolicyTitle;

  /**
   * Getter method.
   *
   * @return Gets the value of code and returns code.
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets the code. You can use getCode() to get the value of code.
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of pkgPolicyDesc and returns pkgPolicyDesc.
   */
  public String getPkgPolicyDesc() {
    return pkgPolicyDesc;
  }

  /**
   * Sets the pkgPolicyDesc. You can use getPkgPolicyDesc() to get the value of pkgPolicyDesc.
   */
  public void setPkgPolicyDesc(String pkgPolicyDesc) {
    this.pkgPolicyDesc = pkgPolicyDesc;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of pkgPolicyTitle and returns pkgPolicyTitle.
   */
  public String getPkgPolicyTitle() {
    return pkgPolicyTitle;
  }

  /**
   * Sets the pkgPolicyTitle. You can use getPkgPolicyTitle() to get the value of pkgPolicyTitle.
   */
  public void setPkgPolicyTitle(String pkgPolicyTitle) {
    this.pkgPolicyTitle = pkgPolicyTitle;
  }
}
