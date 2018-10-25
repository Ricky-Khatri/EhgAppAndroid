
package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.SerializedName;

public class GuaranteePolicy {

  @SerializedName("PolicyDescription")
  private String policyDescription;
  @SerializedName("PolicyType")
  private String policyType;

  public String getPolicyDescription() {
    return policyDescription;
  }

  public void setPolicyDescription(String policyDescription) {
    this.policyDescription = policyDescription;
  }

  public String getPolicyType() {
    return policyType;
  }

  public void setPolicyType(String policyType) {
    this.policyType = policyType;
  }

}
