/*
 *  Created by Emaar Hospitality Group on 13/8/18 11:12 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 13/8/18 11:12 AM
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

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Detail class for UserProfilePojo.
 */
public class Detail {

  @SerializedName("addressLine1")
  private String addressLine1;
  @SerializedName("addressLine2")
  private String addressLine2;
  @SerializedName("birthDate")
  private String birthDate;
  @SerializedName("cardNumber")
  private Long cardNumber;
  @SerializedName("city")
  private String city;
  @SerializedName("country")
  private String country;
  @SerializedName("currentBalance")
  private Double currentBalance;
  @SerializedName("currentPoints")
  private Long currentPoints;
  @SerializedName("emailId")
  private String emailId;
  @SerializedName("firstName")
  private String firstName;
  @SerializedName("gender")
  private String gender;
  @SerializedName("lastName")
  private String lastName;
  @SerializedName("loyaltyMemberId")
  private Long loyaltyMemberId;
  @SerializedName("mobileNumber")
  private String mobileNumber;
  @SerializedName("postalCode")
  private String postalCode;
  @SerializedName("region")
  private String region;
  @SerializedName("responseCode")
  private Long responseCode;
  @SerializedName("suffix")
  private String suffix;
  @SerializedName("tierLevel")
  private String tierLevel;
  @SerializedName("validationErrors")
  private List<ValidationError> validationErrors;

  /**
   * Getter method.
   *
   * @return Gets the value of addressLine1 and returns addressLine1.
   */
  public String getAddressLine1() {
    return addressLine1;
  }

  /**
   * Sets the addressLine1. You can use getAddressLine1() to get the value of addressLine1.
   */
  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of addressLine2 and returns addressLine2.
   */
  public String getAddressLine2() {
    return addressLine2;
  }

  /**
   * Sets the addressLine2. You can use getAddressLine2() to get the value of addressLine2.
   */
  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of birthDate and returns birthDate.
   */
  public String getBirthDate() {
    return birthDate;
  }

  /**
   * Sets the birthDate. You can use getBirthDate() to get the value of birthDate.
   */
  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of cardNumber and returns cardNumber.
   */
  public Long getCardNumber() {
    return cardNumber;
  }

  /**
   * Sets the cardNumber. You can use getCardNumber() to get the value of cardNumber.
   */
  public void setCardNumber(Long cardNumber) {
    this.cardNumber = cardNumber;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of city and returns city.
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city. You can use getCity() to get the value of city.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of country and returns country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the country. You can use getCountry() to get the value of country.
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of currentBalance and returns currentBalance.
   */
  public Double getCurrentBalance() {
    return currentBalance;
  }

  /**
   * Sets the currentBalance. You can use getCurrentBalance() to get the value of currentBalance.
   */
  public void setCurrentBalance(Double currentBalance) {
    this.currentBalance = currentBalance;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of currentPoints and returns currentPoints.
   */
  public Long getCurrentPoints() {
    return currentPoints;
  }

  /**
   * Sets the currentPoints. You can use getCurrentPoints() to get the value of currentPoints.
   */
  public void setCurrentPoints(Long currentPoints) {
    this.currentPoints = currentPoints;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of emailId and returns emailId.
   */
  public String getEmailId() {
    return emailId;
  }

  /**
   * Sets the emailId. You can use getEmailId() to get the value of emailId.
   */
  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of firstName and returns firstName.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the firstName. You can use getFirstName() to get the value of firstName.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of gender and returns gender.
   */
  public String getGender() {
    return gender;
  }

  /**
   * Sets the gender. You can use getGender() to get the value of gender.
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of lastName and returns lastName.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the lastName. You can use getLastName() to get the value of lastName.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of loyaltyMemberId and returns loyaltyMemberId.
   */
  public Long getLoyaltyMemberId() {
    return loyaltyMemberId;
  }

  /**
   * Sets the loyaltyMemberId. You can use getLoyaltyMemberId() to get the value of loyaltyMemberId.
   */
  public void setLoyaltyMemberId(Long loyaltyMemberId) {
    this.loyaltyMemberId = loyaltyMemberId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of mobileNumber and returns mobileNumber.
   */
  public String getMobileNumber() {
    return mobileNumber;
  }

  /**
   * Sets the mobileNumber. You can use getMobileNumber() to get the value of mobileNumber.
   */
  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of postalCode and returns postalCode.
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * Sets the postalCode. You can use getPostalCode() to get the value of postalCode.
   */
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of region and returns region.
   */
  public String getRegion() {
    return region;
  }

  /**
   * Sets the region. You can use getRegion() to get the value of region.
   */
  public void setRegion(String region) {
    this.region = region;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of responseCode and returns responseCode.
   */
  public Long getResponseCode() {
    return responseCode;
  }

  /**
   * Sets the responseCode. You can use getResponseCode() to get the value of responseCode.
   */
  public void setResponseCode(Long responseCode) {
    this.responseCode = responseCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of suffix and returns suffix.
   */
  public String getSuffix() {
    return suffix;
  }

  /**
   * Sets the suffix. You can use getSuffix() to get the value of suffix.
   */
  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of tierLevel and returns tierLevel.
   */
  public String getTierLevel() {
    return tierLevel;
  }

  /**
   * Sets the tierLevel. You can use getTierLevel() to get the value of tierLevel.
   */
  public void setTierLevel(String tierLevel) {
    this.tierLevel = tierLevel;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of validationErrors and returns validationErrors.
   */
  public List<ValidationError> getValidationErrors() {
    return validationErrors;
  }

  /**
   * Sets the validationErrors. You can use getValidationErrors() to get the value of
   * validationErrors.
   */
  public void setValidationErrors(
      List<ValidationError> validationErrors) {
    this.validationErrors = validationErrors;
  }
}
