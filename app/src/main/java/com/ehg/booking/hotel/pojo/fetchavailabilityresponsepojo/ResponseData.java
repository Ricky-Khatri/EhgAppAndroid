
package com.ehg.booking.hotel.pojo.fetchavailabilityresponsepojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseData {

  @SerializedName("ChainCode")
  private String chainCode;
  @SerializedName("HotelList")
  private List<HotelList> hotelList;
  @SerializedName("LanguageCode")
  private String languageCode;
  @SerializedName("MoreDataEchoToken")
  private Long moreDataEchoToken;
  @SerializedName("SearchCriteria")
  private SearchCriteria searchCriteria;

  public String getChainCode() {
    return chainCode;
  }

  public void setChainCode(String chainCode) {
    this.chainCode = chainCode;
  }

  public List<HotelList> getHotelList() {
    return hotelList;
  }

  public void setHotelList(List<HotelList> hotelList) {
    this.hotelList = hotelList;
  }

  public String getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  public Long getMoreDataEchoToken() {
    return moreDataEchoToken;
  }

  public void setMoreDataEchoToken(Long moreDataEchoToken) {
    this.moreDataEchoToken = moreDataEchoToken;
  }

  public SearchCriteria getSearchCriteria() {
    return searchCriteria;
  }

  public void setSearchCriteria(SearchCriteria searchCriteria) {
    this.searchCriteria = searchCriteria;
  }

}
