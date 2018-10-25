
package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchCriteria {

  @SerializedName("Hotels")
  private List<Hotel> hotels;
  @SerializedName("PackageCategories")
  private List<String> packageCategories;
  @SerializedName("RatePlans")
  private List<RatePlan> ratePlans;
  @SerializedName("StarRatings")
  private List<String> starRatings;

  public List<Hotel> getHotels() {
    return hotels;
  }

  public void setHotels(List<Hotel> hotels) {
    this.hotels = hotels;
  }

  public List<String> getPackageCategories() {
    return packageCategories;
  }

  public void setPackageCategories(List<String> packageCategories) {
    this.packageCategories = packageCategories;
  }

  public List<RatePlan> getRatePlans() {
    return ratePlans;
  }

  public void setRatePlans(List<RatePlan> ratePlans) {
    this.ratePlans = ratePlans;
  }

  public List<String> getStarRatings() {
    return starRatings;
  }

  public void setStarRatings(List<String> starRatings) {
    this.starRatings = starRatings;
  }

}
