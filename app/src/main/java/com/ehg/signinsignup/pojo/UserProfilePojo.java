/*
 *  Created by Emaar Hospitality Group on 28/9/18 1:05 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 28/9/18 1:05 PM
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

/**
 * Pojo for UserProfile.
 */
public class UserProfilePojo {

  private boolean status;
  private String message;
  public Data dataObject;
  private float responseTag;

  /**
   * Getter method.
   *
   * @return Gets the value of status and returns status.
   */
  public boolean isStatus() {
    return status;
  }

  /**
   * Sets the status. You can use getStatus() to get the value of status.
   */
  public void setStatus(boolean status) {
    this.status = status;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of message and returns message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message. You can use getMessage() to get the value of message.
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of DataObject and returns DataObject.
   */
  public Data getDataObject() {
    return dataObject;
  }

  /**
   * Sets the DataObject. You can use getDataObject() to get the value of DataObject.
   */
  public void setDataObject(Data dataObject) {
    dataObject = dataObject;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of responseTag and returns responseTag.
   */
  public float getResponseTag() {
    return responseTag;
  }

  /**
   * Sets the responseTag. You can use getResponseTag() to get the value of responseTag.
   */
  public void setResponseTag(float responseTag) {
    this.responseTag = responseTag;
  }
}
