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

package com.ehg.ubyemaar.pojo;

import com.google.gson.annotations.Expose;
import java.util.List;

/**
 * Detail pojo class.
 */
public class Detail {

    @Expose
    private List<Object> address;
    @Expose
    private String cardNumber;
    @Expose
    private String city;
    @Expose
    private Double currentBalance;
    @Expose
    private Double currentPoints;
    @Expose
    private String dob;
    @Expose
    private String emailId;
    @Expose
    private String firstName;
    @Expose
    private String gender;
    @Expose
    private String lastName;
    @Expose
    private Long loyaltyMemberId;
    @Expose
    private String mobileNumber;
    @Expose
    private String postalCode;
    @Expose
    private String prefix;
    @Expose
    private String region;
    @Expose
    private Long responseCode;
    @Expose
    private String responseMessage;
    @Expose
    private String tierLevel;

    /**
     * Getter method.
     *
     * @return Gets the value of address and returns address.
     */
    public List<Object> getAddress() {
        return address;
    }

    /**
     * Sets the address. You can use getAddress() to get the value of address.
     */
    public void setAddress(List<Object> address) {
        this.address = address;
    }

    /**
     * Getter method.
     *
     * @return Gets the value of cardNumber and returns cardNumber.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the cardNumber. You can use getCardNumber() to get the value of cardNumber.
     */
    public void setCardNumber(String cardNumber) {
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
    public Double getCurrentPoints() {
        return currentPoints;
    }

    /**
     * Sets the currentPoints. You can use getCurrentPoints() to get the value of currentPoints.
     */
    public void setCurrentPoints(Double currentPoints) {
        this.currentPoints = currentPoints;
    }

    /**
     * Getter method.
     *
     * @return Gets the value of dob and returns dob.
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets the dob. You can use getDob() to get the value of dob.
     */
    public void setDob(String dob) {
        this.dob = dob;
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
     * @return Gets the value of prefix and returns prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the prefix. You can use getPrefix() to get the value of prefix.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
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
     * @return Gets the value of responseMessage and returns responseMessage.
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Sets the responseMessage. You can use getResponseMessage() to get the value of responseMessage.
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
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
}
