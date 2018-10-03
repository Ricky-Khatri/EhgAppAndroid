/*
 *  Created by Emaar Hospitality Group on 29/9/18 6:38 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 29/9/18 6:38 PM
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

package com.ehg.search.pojo;

import java.util.ArrayList;
import java.util.List;

public class SearchGroupPojo {

  private ArrayList<SearchGroupPojo> groupArray = new ArrayList<>();
  private List<SearchChildPojo> childArray = new ArrayList<>();

  private String parentName = "";
  private int imageName;

  /**
   * Getter method.
   *
   * @return Gets the value of parentName and returns parentName.
   */
  public String getParentName() {
    return parentName;
  }

  /**
   * Sets the parentName.
   * You can use getParentName() to get the value of parentName.
   */
  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of imageName and returns imageName.
   */
  public int getImageName() {
    return imageName;
  }

  /**
   * Sets the imageName.
   * You can use getImageName() to get the value of imageName.
   */
  public void setImageName(int imageName) {
    this.imageName = imageName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of groupArray and returns groupArray.
   */
  public ArrayList<SearchGroupPojo> getGroupArray() {
    return groupArray;
  }

  /**
   * Sets the groupArray.
   * You can use getGroupArray() to get the value of groupArray.
   * @param groupArray
   */
  public void setGroupArray(ArrayList<SearchGroupPojo> groupArray) {
    this.groupArray = groupArray;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of childArray and returns childArray.
   */
  public List<SearchChildPojo> getChildArray() {
    return childArray;
  }

  /**
   * Sets the childArray.
   * You can use getChildArray() to get the value of childArray.
   */
  public void setChildArray(List<SearchChildPojo> childArray) {
    this.childArray = childArray;
  }
}
