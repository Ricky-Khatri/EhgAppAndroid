
package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.SerializedName;

public class Package {

  @SerializedName("Confidential")
  private Boolean confidential;
  @SerializedName("Discount")
  private Long discount;
  @SerializedName("IsAvailable")
  private Boolean isAvailable;
  @SerializedName("PackageCatagory")
  private String packageCatagory;
  @SerializedName("PackageCode")
  private String packageCode;
  @SerializedName("PackageDesc")
  private String packageDesc;
  @SerializedName("PackageName")
  private String packageName;
  @SerializedName("Rate")
  private Long rate;
  @SerializedName("SortOrder")
  private Long sortOrder;

  public Boolean getConfidential() {
    return confidential;
  }

  public void setConfidential(Boolean confidential) {
    this.confidential = confidential;
  }

  public Long getDiscount() {
    return discount;
  }

  public void setDiscount(Long discount) {
    this.discount = discount;
  }

  public Boolean getIsAvailable() {
    return isAvailable;
  }

  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public String getPackageCatagory() {
    return packageCatagory;
  }

  public void setPackageCatagory(String packageCatagory) {
    this.packageCatagory = packageCatagory;
  }

  public String getPackageCode() {
    return packageCode;
  }

  public void setPackageCode(String packageCode) {
    this.packageCode = packageCode;
  }

  public String getPackageDesc() {
    return packageDesc;
  }

  public void setPackageDesc(String packageDesc) {
    this.packageDesc = packageDesc;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public Long getRate() {
    return rate;
  }

  public void setRate(Long rate) {
    this.rate = rate;
  }

  public Long getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(Long sortOrder) {
    this.sortOrder = sortOrder;
  }

}
