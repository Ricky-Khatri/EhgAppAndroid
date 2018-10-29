
package com.ehg.booking.hotel.pojo.fetchservicesresponsepojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DailyRate {

  @SerializedName("AdultQuantity")
  private int adultQuantity;
  @SerializedName("AdultRate")
  private int adultRate;
  @SerializedName("AdultUnitPrice")
  private int adultUnitPrice;
  @SerializedName("ChildQuantity")
  private int childQuantity;
  @SerializedName("ChildRate")
  private int childRate;
  @SerializedName("ChildUnitPrice")
  private int childUnitPrice;
  @SerializedName("DiscountRate")
  private int discountRate;
  @SerializedName("Price")
  private List<Price> price;
  @SerializedName("Quantity")
  private int quantity;
  @SerializedName("ServiceDate")
  private String serviceDate;
  @SerializedName("ServiceOptionRefId")
  private String serviceOptionRefId;
  @SerializedName("TaxRate")
  private Double taxRate;

  /**
   * Getter method.
   *
   * @return Gets the value of adultQuantity and returns adultQuantity.
   */
  public int getAdultQuantity() {
    return adultQuantity;
  }

  /**
   * Sets the adultQuantity. You can use getAdultQuantity() to get the value of adultQuantity.
   */
  public void setAdultQuantity(int adultQuantity) {
    this.adultQuantity = adultQuantity;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of adultRate and returns adultRate.
   */
  public int getAdultRate() {
    return adultRate;
  }

  /**
   * Sets the adultRate. You can use getAdultRate() to get the value of adultRate.
   */
  public void setAdultRate(int adultRate) {
    this.adultRate = adultRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of adultUnitPrice and returns adultUnitPrice.
   */
  public int getAdultUnitPrice() {
    return adultUnitPrice;
  }

  /**
   * Sets the adultUnitPrice. You can use getAdultUnitPrice() to get the value of adultUnitPrice.
   */
  public void setAdultUnitPrice(int adultUnitPrice) {
    this.adultUnitPrice = adultUnitPrice;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of childQuantity and returns childQuantity.
   */
  public int getChildQuantity() {
    return childQuantity;
  }

  /**
   * Sets the childQuantity. You can use getChildQuantity() to get the value of childQuantity.
   */
  public void setChildQuantity(int childQuantity) {
    this.childQuantity = childQuantity;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of childRate and returns childRate.
   */
  public int getChildRate() {
    return childRate;
  }

  /**
   * Sets the childRate. You can use getChildRate() to get the value of childRate.
   */
  public void setChildRate(int childRate) {
    this.childRate = childRate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of childUnitPrice and returns childUnitPrice.
   */
  public int getChildUnitPrice() {
    return childUnitPrice;
  }

  /**
   * Sets the childUnitPrice. You can use getChildUnitPrice() to get the value of childUnitPrice.
   */
  public void setChildUnitPrice(int childUnitPrice) {
    this.childUnitPrice = childUnitPrice;
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
   * @return Gets the value of price and returns price.
   */
  public List<Price> getPrice() {
    return price;
  }

  /**
   * Sets the price. You can use getPrice() to get the value of price.
   */
  public void setPrice(List<Price> price) {
    this.price = price;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of quantity and returns quantity.
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity. You can use getQuantity() to get the value of quantity.
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of serviceDate and returns serviceDate.
   */
  public String getServiceDate() {
    return serviceDate;
  }

  /**
   * Sets the serviceDate. You can use getServiceDate() to get the value of serviceDate.
   */
  public void setServiceDate(String serviceDate) {
    this.serviceDate = serviceDate;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of serviceOptionRefId and returns serviceOptionRefId.
   */
  public String getServiceOptionRefId() {
    return serviceOptionRefId;
  }

  /**
   * Sets the serviceOptionRefId. You can use getServiceOptionRefId() to get the value of serviceOptionRefId.
   */
  public void setServiceOptionRefId(String serviceOptionRefId) {
    this.serviceOptionRefId = serviceOptionRefId;
  }

  /**
   * Getter method.
   *
   * @return Gets the value of taxRate and returns taxRate.
   */
  public Double getTaxRate() {
    return taxRate;
  }

  /**
   * Sets the taxRate. You can use getTaxRate() to get the value of taxRate.
   */
  public void setTaxRate(Double taxRate) {
    this.taxRate = taxRate;
  }
}
