/*
 *  Created by Emaar Hospitality Group on 25/9/18 11:39 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 25/9/18 11:39 AM
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

package com.ehg.ubyemaar.pojo;

/**
 * Pojo for user preferences.
 */
public class UserPreferencesPojo {

  private String preferenceName;
  private String preferenceId;

  private boolean isSelected;

  /**
   * Getter method.
   *
   * @return Gets the value of preferenceId and returns preferenceId.
   */
  public String getPreferenceId() {
    return preferenceId;
  }

  /**
   * Sets the preferenceId. You can use getPreferenceId() to get the value of preferenceId.
   */
  public void setPreferenceId(String preferenceId) {
    this.preferenceId = preferenceId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of preferenceName and returns preferenceName.
   */
  public String getPreferenceName() {
    return preferenceName;
  }

  /**
   * Sets the preferenceName. You can use getPreferenceName() to get the value of preferenceName.
   */
  public void setPreferenceName(String preferenceName) {
    this.preferenceName = preferenceName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of isSelected and returns isSelected.
   */
  public boolean isSelected() {
    return isSelected;
  }

  /**
   * Sets the isSelected. You can use getSelected() to get the value of isSelected.
   */
  public void setSelected(boolean selected) {
    isSelected = selected;
  }
}
