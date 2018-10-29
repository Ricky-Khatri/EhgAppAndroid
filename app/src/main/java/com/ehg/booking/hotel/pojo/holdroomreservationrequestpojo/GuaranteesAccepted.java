/*
 *  Created by Emaar Hospitality Group on 17/10/18 6:00 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 17/10/18 6:00 PM
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

package com.ehg.booking.hotel.pojo.holdroomreservationrequestpojo;

import com.google.gson.annotations.SerializedName;

/**
 * GuaranteesAccepted.
 */
public class GuaranteesAccepted {

  @SerializedName("AlternatePayment")
  private AlternatePayment alternatePayment;
  @SerializedName("PaymentCard")
  private PaymentCard paymentCard;
  @SerializedName("PaymentMethodId")
  private String paymentMethodId;
  @SerializedName("UseExistingCreditCard")
  private String useExistingCreditCard;
  @SerializedName("VariantId")
  private Long variantId;
  @SerializedName("VendorKey")
  private String vendorKey;

  /**
   * Getter method.
   *
   * @return Gets the value of alternatePayment and returns alternatePayment.
   */
  public AlternatePayment getAlternatePayment() {
    return alternatePayment;
  }

  /**
   * Sets the alternatePayment. You can use getAlternatePayment() to get the value of
   * alternatePayment.
   */
  public void setAlternatePayment(
      AlternatePayment alternatePayment) {
    this.alternatePayment = alternatePayment;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of paymentCard and returns paymentCard.
   */
  public PaymentCard getPaymentCard() {
    return paymentCard;
  }

  /**
   * Sets the paymentCard. You can use getPaymentCard() to get the value of paymentCard.
   */
  public void setPaymentCard(
      PaymentCard paymentCard) {
    this.paymentCard = paymentCard;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of paymentMethodId and returns paymentMethodId.
   */
  public String getPaymentMethodId() {
    return paymentMethodId;
  }

  /**
   * Sets the paymentMethodId. You can use getPaymentMethodId() to get the value of paymentMethodId.
   */
  public void setPaymentMethodId(String paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of useExistingCreditCard and returns useExistingCreditCard.
   */
  public String getUseExistingCreditCard() {
    return useExistingCreditCard;
  }

  /**
   * Sets the useExistingCreditCard. You can use getUseExistingCreditCard() to get the value of
   * useExistingCreditCard.
   */
  public void setUseExistingCreditCard(String useExistingCreditCard) {
    this.useExistingCreditCard = useExistingCreditCard;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of variantId and returns variantId.
   */
  public Long getVariantId() {
    return variantId;
  }

  /**
   * Sets the variantId. You can use getVariantId() to get the value of variantId.
   */
  public void setVariantId(Long variantId) {
    this.variantId = variantId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of vendorKey and returns vendorKey.
   */
  public String getVendorKey() {
    return vendorKey;
  }

  /**
   * Sets the vendorKey. You can use getVendorKey() to get the value of vendorKey.
   */
  public void setVendorKey(String vendorKey) {
    this.vendorKey = vendorKey;
  }
}
