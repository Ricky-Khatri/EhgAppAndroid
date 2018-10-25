
package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.SerializedName;

public class FetchRoomAvailabilityResponsePojo {

  @SerializedName("Data")
  private Data data;
  @SerializedName("Message")
  private String message;
  @SerializedName("ResponseTag")
  private Long responseTag;
  @SerializedName("Status")
  private Boolean status;

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Long getResponseTag() {
    return responseTag;
  }

  public void setResponseTag(Long responseTag) {
    this.responseTag = responseTag;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

}
