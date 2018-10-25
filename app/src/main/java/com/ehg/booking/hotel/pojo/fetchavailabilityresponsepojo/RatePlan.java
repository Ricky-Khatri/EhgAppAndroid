
package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.SerializedName;

public class RatePlan {

  @SerializedName("CancellationPolicy")
  private CancellationPolicy cancellationPolicy;
  @SerializedName("Confidential")
  private Boolean confidential;
  @SerializedName("Discount")
  private Long discount;
  @SerializedName("ExternalCode")
  private String externalCode;
  @SerializedName("GuaranteePolicy")
  private GuaranteePolicy guaranteePolicy;
  @SerializedName("IsAvailable")
  private Boolean isAvailable;
  @SerializedName("Name")
  private String name;
  @SerializedName("Rate")
  private Long rate;
  @SerializedName("RatePlanCode")
  private String ratePlanCode;
  @SerializedName("RateplanDesc")
  private String rateplanDesc;
  @SerializedName("SortOrder")
  private Long sortOrder;

  public CancellationPolicy getCancellationPolicy() {
    return cancellationPolicy;
  }

  public void setCancellationPolicy(CancellationPolicy cancellationPolicy) {
    this.cancellationPolicy = cancellationPolicy;
  }

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

  public String getExternalCode() {
    return externalCode;
  }

  public void setExternalCode(String externalCode) {
    this.externalCode = externalCode;
  }

  public GuaranteePolicy getGuaranteePolicy() {
    return guaranteePolicy;
  }

  public void setGuaranteePolicy(GuaranteePolicy guaranteePolicy) {
    this.guaranteePolicy = guaranteePolicy;
  }

  public Boolean getIsAvailable() {
    return isAvailable;
  }

  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getRate() {
    return rate;
  }

  public void setRate(Long rate) {
    this.rate = rate;
  }

  public String getRatePlanCode() {
    return ratePlanCode;
  }

  public void setRatePlanCode(String ratePlanCode) {
    this.ratePlanCode = ratePlanCode;
  }

  public String getRateplanDesc() {
    return rateplanDesc;
  }

  public void setRateplanDesc(String rateplanDesc) {
    this.rateplanDesc = rateplanDesc;
  }

  public Long getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(Long sortOrder) {
    this.sortOrder = sortOrder;
  }

}
