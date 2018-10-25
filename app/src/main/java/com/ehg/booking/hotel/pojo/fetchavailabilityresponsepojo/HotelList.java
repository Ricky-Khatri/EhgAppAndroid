
package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HotelList {

  @SerializedName("Address")
  private Address address;
  @SerializedName("Amenity")
  private List<Amenity> amenity;
  @SerializedName("CurrentCurrency")
  private CurrentCurrency currentCurrency;
  @SerializedName("DefaultRateInfo")
  private DefaultRateInfo defaultRateInfo;
  @SerializedName("Description")
  private String description;
  @SerializedName("Discount")
  private Long discount;
  @SerializedName("HotelCode")
  private Long hotelCode;
  @SerializedName("HotelName")
  private String hotelName;
  @SerializedName("IsAvailable")
  private Boolean isAvailable;
  @SerializedName("Media")
  private Media media;
  @SerializedName("Packages")
  private List<Package> packages;
  @SerializedName("Position")
  private Position position;
  @SerializedName("Rate")
  private Long rate;
  @SerializedName("RatePlans")
  private List<RatePlan> ratePlans;
  @SerializedName("SortOrder")
  private Long sortOrder;
  @SerializedName("StarRating")
  private String starRating;

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<Amenity> getAmenity() {
    return amenity;
  }

  public void setAmenity(List<Amenity> amenity) {
    this.amenity = amenity;
  }

  public CurrentCurrency getCurrentCurrency() {
    return currentCurrency;
  }

  public void setCurrentCurrency(CurrentCurrency currentCurrency) {
    this.currentCurrency = currentCurrency;
  }

  public DefaultRateInfo getDefaultRateInfo() {
    return defaultRateInfo;
  }

  public void setDefaultRateInfo(DefaultRateInfo defaultRateInfo) {
    this.defaultRateInfo = defaultRateInfo;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getDiscount() {
    return discount;
  }

  public void setDiscount(Long discount) {
    this.discount = discount;
  }

  public Long getHotelCode() {
    return hotelCode;
  }

  public void setHotelCode(Long hotelCode) {
    this.hotelCode = hotelCode;
  }

  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  public Boolean getIsAvailable() {
    return isAvailable;
  }

  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public Media getMedia() {
    return media;
  }

  public void setMedia(Media media) {
    this.media = media;
  }

  public List<Package> getPackages() {
    return packages;
  }

  public void setPackages(List<Package> packages) {
    this.packages = packages;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Long getRate() {
    return rate;
  }

  public void setRate(Long rate) {
    this.rate = rate;
  }

  public List<RatePlan> getRatePlans() {
    return ratePlans;
  }

  public void setRatePlans(List<RatePlan> ratePlans) {
    this.ratePlans = ratePlans;
  }

  public Long getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(Long sortOrder) {
    this.sortOrder = sortOrder;
  }

  public String getStarRating() {
    return starRating;
  }

  public void setStarRating(String starRating) {
    this.starRating = starRating;
  }

}
