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

/**
 * Door.
 */
public class Door {

  @SerializedName("door_bell")
  private Boolean doorBell;
  @SerializedName("door_camera")
  private Boolean doorCamera;
  @SerializedName("door_camera_details")
  private DoorCameraDetails doorCameraDetails;
  @SerializedName("door_lock")
  private Boolean doorLock;
  @SerializedName("door_status")
  private Boolean doorStatus;

  /**
   * Getter method.
   *
   * @return Gets the value of doorBell and returns doorBell.
   */
  public Boolean getDoorBell() {
    return doorBell;
  }

  /**
   * Sets the doorBell. You can use getDoorBell() to get the value of doorBell.
   */
  public void setDoorBell(Boolean doorBell) {
    this.doorBell = doorBell;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of doorCamera and returns doorCamera.
   */
  public Boolean getDoorCamera() {
    return doorCamera;
  }

  /**
   * Sets the doorCamera. You can use getDoorCamera() to get the value of doorCamera.
   */
  public void setDoorCamera(Boolean doorCamera) {
    this.doorCamera = doorCamera;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of doorCameraDetails and returns doorCameraDetails.
   */
  public DoorCameraDetails getDoorCameraDetails() {
    return doorCameraDetails;
  }

  /**
   * Sets the doorCameraDetails. You can use getDoorCameraDetails() to get the value of
   * doorCameraDetails.
   */
  public void setDoorCameraDetails(DoorCameraDetails doorCameraDetails) {
    this.doorCameraDetails = doorCameraDetails;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of doorLock and returns doorLock.
   */
  public Boolean getDoorLock() {
    return doorLock;
  }

  /**
   * Sets the doorLock. You can use getDoorLock() to get the value of doorLock.
   */
  public void setDoorLock(Boolean doorLock) {
    this.doorLock = doorLock;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of doorStatus and returns doorStatus.
   */
  public Boolean getDoorStatus() {
    return doorStatus;
  }

  /**
   * Sets the doorStatus. You can use getDoorStatus() to get the value of doorStatus.
   */
  public void setDoorStatus(Boolean doorStatus) {
    this.doorStatus = doorStatus;
  }
}
