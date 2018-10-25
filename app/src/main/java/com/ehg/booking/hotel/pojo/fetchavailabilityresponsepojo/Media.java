
package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Media {

  @SerializedName("PropertyDetailImages")
  private List<PropertyDetailImage> propertyDetailImages;
  @SerializedName("PropertyMainImages")
  private List<PropertyMainImage> propertyMainImages;
  @SerializedName("PropertyMainImgTag")
  private PropertyMainImgTag propertyMainImgTag;

  public List<PropertyDetailImage> getPropertyDetailImages() {
    return propertyDetailImages;
  }

  public void setPropertyDetailImages(List<PropertyDetailImage> propertyDetailImages) {
    this.propertyDetailImages = propertyDetailImages;
  }

  public List<PropertyMainImage> getPropertyMainImages() {
    return propertyMainImages;
  }

  public void setPropertyMainImages(List<PropertyMainImage> propertyMainImages) {
    this.propertyMainImages = propertyMainImages;
  }

  public PropertyMainImgTag getPropertyMainImgTag() {
    return propertyMainImgTag;
  }

  public void setPropertyMainImgTag(PropertyMainImgTag propertyMainImgTag) {
    this.propertyMainImgTag = propertyMainImgTag;
  }

}
