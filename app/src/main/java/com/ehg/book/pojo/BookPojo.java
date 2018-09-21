/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:43 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 19/9/18 1:50 PM
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

package com.ehg.book.pojo;

/**
 * Pojo class for BookListFragment.
 */
public class BookPojo {

  private String title;
  private String imageUrl;

  /**
   * Getter method.
   *
   * @return Gets the value of title and returns title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title. You can use getTitle() to get the value of title.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of imageUrl and returns imageUrl.
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Sets the imageUrl. You can use getImageUrl() to get the value of imageUrl.
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
