/*
 *  Created by Emaar Hospitality Group on 10/8/18 5:47 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 10/8/18 5:47 PM
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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurtainItem {

  @SerializedName("display_name")
  private String displayName;
  @Expose
  private String id;
  @SerializedName("image_url")
  private String imageUrl;
  @Expose
  private Long order;
  @SerializedName("stop_feature")
  private Boolean stopFeature;

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Long getOrder() {
    return order;
  }

  public void setOrder(Long order) {
    this.order = order;
  }

  public Boolean getStopFeature() {
    return stopFeature;
  }

  public void setStopFeature(Boolean stopFeature) {
    this.stopFeature = stopFeature;
  }

}
