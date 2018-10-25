
package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.Expose;

public class ValidationError {

  @Expose
  private String errorMessage;
  @Expose
  private Long errorNumber;

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public Long getErrorNumber() {
    return errorNumber;
  }

  public void setErrorNumber(Long errorNumber) {
    this.errorNumber = errorNumber;
  }

}
