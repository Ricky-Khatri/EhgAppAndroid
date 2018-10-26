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
 * MerchandisedPromoData.
 */
public class MerchandisedPromoData {

  @SerializedName("PromotionId")
  private String promotionId;
  @SerializedName("SaleText")
  private String saleText;

  /**
   * Getter method.
   *
   * @return Gets the value of promotionId and returns promotionId.
   */
  public String getPromotionId() {
    return promotionId;
  }

  /**
   * Sets the promotionId. You can use getPromotionId() to get the value of promotionId.
   */
  public void setPromotionId(String promotionId) {
    this.promotionId = promotionId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of saleText and returns saleText.
   */
  public String getSaleText() {
    return saleText;
  }

  /**
   * Sets the saleText. You can use getSaleText() to get the value of saleText.
   */
  public void setSaleText(String saleText) {
    this.saleText = saleText;
  }
}
