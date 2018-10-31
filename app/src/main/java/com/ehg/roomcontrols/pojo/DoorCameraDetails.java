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

import com.google.gson.annotations.SerializedName;


public class DoorCameraDetails {

  @SerializedName("camera_url")
  private String cameraUrl;
  @SerializedName("video_aspect_ratio")
  private String videoAspectRatio;
  @SerializedName("video_protocol")
  private String videoProtocol;

  /**
   * Getter method.
   *
   * @return Gets the value of cameraUrl and returns cameraUrl.
   */
  public String getCameraUrl() {
    return cameraUrl;
  }

  /**
   * Sets the cameraUrl. You can use getCameraUrl() to get the value of cameraUrl.
   */
  public void setCameraUrl(String cameraUrl) {
    this.cameraUrl = cameraUrl;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of videoAspectRatio and returns videoAspectRatio.
   */
  public String getVideoAspectRatio() {
    return videoAspectRatio;
  }

  /**
   * Sets the videoAspectRatio. You can use getVideoAspectRatio() to get the value of
   * videoAspectRatio.
   */
  public void setVideoAspectRatio(String videoAspectRatio) {
    this.videoAspectRatio = videoAspectRatio;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of videoProtocol and returns videoProtocol.
   */
  public String getVideoProtocol() {
    return videoProtocol;
  }

  /**
   * Sets the videoProtocol. You can use getVideoProtocol() to get the value of videoProtocol.
   */
  public void setVideoProtocol(String videoProtocol) {
    this.videoProtocol = videoProtocol;
  }
}
