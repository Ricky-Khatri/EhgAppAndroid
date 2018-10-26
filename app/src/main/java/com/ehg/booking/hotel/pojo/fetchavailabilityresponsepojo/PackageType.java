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
import java.util.List;

/**
 * PackageType.
 */
public class PackageType {

  @SerializedName("AllowExtendedStay")
  private Boolean allowExtendedStay;
  @SerializedName("Available")
  private Boolean available;
  @SerializedName("CategoryCode")
  private String categoryCode;
  @SerializedName("Confidential")
  private Boolean confidential;
  @SerializedName("Description")
  private String description;
  @SerializedName("DiscountRate")
  private int discountRate;
  @SerializedName("ExtendedStayMessage")
  private String extendedStayMessage;
  @SerializedName("HideDailyRate")
  private Boolean hideDailyRate;
  @SerializedName("HideMaxMinNight")
  private Boolean hideMaxMinNight;
  @SerializedName("HotelPolicies")
  private List<HotelPolicy> hotelPolicies;
  @SerializedName("Inclusions")
  private List<Inclusions> inclusions;
  @SerializedName("LeadRate")
  private int leadRate;
  @SerializedName("MainImage")
  private MainImage mainImage;
  @SerializedName("MaxStay")
  private int maxStay;
  @SerializedName("Medias")
  private List<Media> medias;
  @SerializedName("MerchandisedPromoData")
  private MerchandisedPromoData merchandisedPromoData;
  @SerializedName("MinStay")
  private int minStay;
  @SerializedName("PackageCode")
  private String packageCode;
  @SerializedName("PackageEndDate")
  private String packageEndDate;
  @SerializedName("PackageName")
  private String packageName;
  @SerializedName("PackagePolicies")
  private List<PackagePolicy> packagePolicies;
  @SerializedName("PackageStartDate")
  private String packageStartDate;
  @SerializedName("SortOrder")
  private int sortOrder;

  /**
   * Getter method.
   *
   * @return Gets the value of allowExtendedStay and returns allowExtendedStay.
   */
  public Boolean getAllowExtendedStay() {
    return allowExtendedStay;
  }

  /**
   * Sets the allowExtendedStay. You can use getAllowExtendedStay() to get the value of
   * allowExtendedStay.
   */
  public void setAllowExtendedStay(Boolean allowExtendedStay) {
    this.allowExtendedStay = allowExtendedStay;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of available and returns available.
   */
  public Boolean getAvailable() {
    return available;
  }

  /**
   * Sets the available. You can use getAvailable() to get the value of available.
   */
  public void setAvailable(Boolean available) {
    this.available = available;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of categoryCode and returns categoryCode.
   */
  public String getCategoryCode() {
    return categoryCode;
  }

  /**
   * Sets the categoryCode. You can use getCategoryCode() to get the value of categoryCode.
   */
  public void setCategoryCode(String categoryCode) {
    this.categoryCode = categoryCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of confidential and returns confidential.
   */
  public Boolean getConfidential() {
    return confidential;
  }

  /**
   * Sets the confidential. You can use getConfidential() to get the value of confidential.
   */
  public void setConfidential(Boolean confidential) {
    this.confidential = confidential;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of description and returns description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description. You can use getDescription() to get the value of description.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of discountRate and returns discountRate.
   */
  public int getDiscountRate() {
    return discountRate;
  }

  /**
   * Sets the discountRate. You can use getDiscountRate() to get the value of discountRate.
   */
  public void setDiscountRate(int discountRate) {
    this.discountRate = discountRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of extendedStayMessage and returns extendedStayMessage.
   */
  public String getExtendedStayMessage() {
    return extendedStayMessage;
  }

  /**
   * Sets the extendedStayMessage. You can use getExtendedStayMessage() to get the value of
   * extendedStayMessage.
   */
  public void setExtendedStayMessage(String extendedStayMessage) {
    this.extendedStayMessage = extendedStayMessage;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of hideDailyRate and returns hideDailyRate.
   */
  public Boolean getHideDailyRate() {
    return hideDailyRate;
  }

  /**
   * Sets the hideDailyRate. You can use getHideDailyRate() to get the value of hideDailyRate.
   */
  public void setHideDailyRate(Boolean hideDailyRate) {
    this.hideDailyRate = hideDailyRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of hideMaxMinNight and returns hideMaxMinNight.
   */
  public Boolean getHideMaxMinNight() {
    return hideMaxMinNight;
  }

  /**
   * Sets the hideMaxMinNight. You can use getHideMaxMinNight() to get the value of hideMaxMinNight.
   */
  public void setHideMaxMinNight(Boolean hideMaxMinNight) {
    this.hideMaxMinNight = hideMaxMinNight;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of hotelPolicies and returns hotelPolicies.
   */
  public List<HotelPolicy> getHotelPolicies() {
    return hotelPolicies;
  }

  /**
   * Sets the hotelPolicies. You can use getHotelPolicies() to get the value of hotelPolicies.
   */
  public void setHotelPolicies(
      List<HotelPolicy> hotelPolicies) {
    this.hotelPolicies = hotelPolicies;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of inclusions and returns inclusions.
   */
  public List<Inclusions> getInclusions() {
    return inclusions;
  }

  /**
   * Sets the inclusions. You can use getInclusions() to get the value of inclusions.
   */
  public void setInclusions(
      List<Inclusions> inclusions) {
    this.inclusions = inclusions;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of leadRate and returns leadRate.
   */
  public int getLeadRate() {
    return leadRate;
  }

  /**
   * Sets the leadRate. You can use getLeadRate() to get the value of leadRate.
   */
  public void setLeadRate(int leadRate) {
    this.leadRate = leadRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of mainImage and returns mainImage.
   */
  public MainImage getMainImage() {
    return mainImage;
  }

  /**
   * Sets the mainImage. You can use getMainImage() to get the value of mainImage.
   */
  public void setMainImage(
      MainImage mainImage) {
    this.mainImage = mainImage;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of maxStay and returns maxStay.
   */
  public int getMaxStay() {
    return maxStay;
  }

  /**
   * Sets the maxStay. You can use getMaxStay() to get the value of maxStay.
   */
  public void setMaxStay(int maxStay) {
    this.maxStay = maxStay;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of medias and returns medias.
   */
  public List<Media> getMedias() {
    return medias;
  }

  /**
   * Sets the medias. You can use getMedias() to get the value of medias.
   */
  public void setMedias(
      List<Media> medias) {
    this.medias = medias;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of merchandisedPromoData and returns merchandisedPromoData.
   */
  public MerchandisedPromoData getMerchandisedPromoData() {
    return merchandisedPromoData;
  }

  /**
   * Sets the merchandisedPromoData. You can use getMerchandisedPromoData() to get the value of
   * merchandisedPromoData.
   */
  public void setMerchandisedPromoData(
      MerchandisedPromoData merchandisedPromoData) {
    this.merchandisedPromoData = merchandisedPromoData;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of minStay and returns minStay.
   */
  public int getMinStay() {
    return minStay;
  }

  /**
   * Sets the minStay. You can use getMinStay() to get the value of minStay.
   */
  public void setMinStay(int minStay) {
    this.minStay = minStay;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packageCode and returns packageCode.
   */
  public String getPackageCode() {
    return packageCode;
  }

  /**
   * Sets the packageCode. You can use getPackageCode() to get the value of packageCode.
   */
  public void setPackageCode(String packageCode) {
    this.packageCode = packageCode;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packageEndDate and returns packageEndDate.
   */
  public String getPackageEndDate() {
    return packageEndDate;
  }

  /**
   * Sets the packageEndDate. You can use getPackageEndDate() to get the value of packageEndDate.
   */
  public void setPackageEndDate(String packageEndDate) {
    this.packageEndDate = packageEndDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packageName and returns packageName.
   */
  public String getPackageName() {
    return packageName;
  }

  /**
   * Sets the packageName. You can use getPackageName() to get the value of packageName.
   */
  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packagePolicies and returns packagePolicies.
   */
  public List<PackagePolicy> getPackagePolicies() {
    return packagePolicies;
  }

  /**
   * Sets the packagePolicies. You can use getPackagePolicies() to get the value of packagePolicies.
   */
  public void setPackagePolicies(
      List<PackagePolicy> packagePolicies) {
    this.packagePolicies = packagePolicies;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of packageStartDate and returns packageStartDate.
   */
  public String getPackageStartDate() {
    return packageStartDate;
  }

  /**
   * Sets the packageStartDate. You can use getPackageStartDate() to get the value of
   * packageStartDate.
   */
  public void setPackageStartDate(String packageStartDate) {
    this.packageStartDate = packageStartDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of sortOrder and returns sortOrder.
   */
  public int getSortOrder() {
    return sortOrder;
  }

  /**
   * Sets the sortOrder. You can use getSortOrder() to get the value of sortOrder.
   */
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
}
