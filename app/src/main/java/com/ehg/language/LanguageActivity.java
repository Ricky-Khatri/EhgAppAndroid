/*
 *  Created by Emaar Hospitality Group on 25/8/18 11:05 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 24/8/18 6:02 PM
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ehg.language;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.home.BaseActivity;
import com.ehg.language.adapter.LanguageListAdapter;
import com.ehg.language.adapter.LanguageListAdapter.OnItemClickListener;
import com.ehg.language.pojo.LanguagePojo;
import com.ehg.networkrequest.HttpClientRequest;
import com.ehg.networkrequest.HttpClientRequest.ApiResponseListener;
import com.ehg.utilities.AppUtil;
import com.ehg.utilities.JsonParserUtil;
import com.ehg.utilities.LanguageUtil;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class shows list of languages supported by app and allows user to choose any one as app
 * language.
 */
public class LanguageActivity extends BaseActivity implements
    OnItemClickListener, OnClickListener, ApiResponseListener {

  private static final String GET_LANGUAGE_LIST_METHOD = "getLanguageList";

  private RecyclerView recyclerViewLanguageList;

  private ArrayList<LanguagePojo> languageList;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_language);

    initView();
  }

  /**
   * Method init's view components.
   */
  private void initView() {

    TextView textViewHeaderTitle = findViewById(R.id.textview_header_title);
    textViewHeaderTitle.setText(R.string.selectlanguage_title);

    findViewById(R.id.imageview_header_back).setOnClickListener(this);

    recyclerViewLanguageList = findViewById(R.id.recyclerview_language_list);

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerViewLanguageList.setLayoutManager(layoutManager);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
        recyclerViewLanguageList.getContext(),
        LinearLayoutManager.VERTICAL);
    recyclerViewLanguageList.addItemDecoration(dividerItemDecoration);

    setLanguageListAdapter();

    setBackArrowRtl((AppCompatImageView) findViewById(R.id.imageview_header_back));
  }

  /**
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();
    setBackArrowRtl((AppCompatImageView) findViewById(R.id.imageview_header_back));
  }

  /**
   * Called to update back arrow rtl icons.
   *
   * @param appCompatImageView imageview object
   */
  @Override
  public void setBackArrowRtl(AppCompatImageView appCompatImageView) {
    super.setBackArrowRtl(appCompatImageView);
  }

  /**
   * Method creates language list and sets to language list adapter.
   */
  private void setLanguageListAdapter() {
    //Initialize language list
    languageList = new ArrayList<>();

    //Read available languages from Language Json file
    String languageJson = JsonParserUtil.getInstance(this).
        getStringValue(JsonParserUtil.LANGUAGE_JSON, "");

    if (!TextUtils.isEmpty(languageJson)) {
      try {
        JSONObject jsonObject = new JSONObject(languageJson);
        JSONArray jsonArray = jsonObject.optJSONArray("enabledlanguages");

        if (jsonArray != null && jsonArray.length() > 0) {
          for (int index = 0; index < jsonArray.length(); index++) {
            JSONObject langObject = jsonArray.optJSONObject(index);
            LanguagePojo languagePojo = new LanguagePojo();
            languagePojo.setLanguageName(langObject.getString("display_name"));
            languagePojo.setLanguageCode(langObject.getString("language_code"));
            languageList.add(languagePojo);
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    /*LanguagePojo languagePojo = new LanguagePojo();
    languagePojo.setLanguageCode("en");
    languagePojo.setLanguageName("English");
    languageList.add(languagePojo);
    languagePojo = new LanguagePojo();
    languagePojo.setLanguageCode("ar");
    languagePojo.setLanguageName("عربى");
    languageList.add(languagePojo);
    languagePojo = new LanguagePojo();
    languagePojo.setLanguageCode("zh");
    languagePojo.setLanguageName("中文");
    languageList.add(languagePojo);
    languagePojo = new LanguagePojo();
    languagePojo.setLanguageCode("es");
    languagePojo.setLanguageName("Español");
    languageList.add(languagePojo);*/

    //Set list to adapter
    LanguageListAdapter languageListAdapter =
        new LanguageListAdapter(this, languageList, this);
    recyclerViewLanguageList.setAdapter(languageListAdapter);
  }

  /**
   * OnItemClick callback method.
   *
   * @param position position of clicked row
   */
  @Override
  public void onItemClick(int position) {
    if (languageList != null && languageList.size() > 0) {
      String selectedLanguageCode = languageList.get(position).getLanguageCode();

      //Set app locale
      LanguageUtil.setLocale(this, selectedLanguageCode);
    }
    AppUtil.finishActivityWithAnimation(this);
  }

  /**
   * Called when any view clicked.
   *
   * @param view view
   */
  @Override
  public void onClick(View view) {

    //AppUtil.clickAnimation(view);

    switch (view.getId()) {
      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      default:
        break;
    }
  }

  //****************************** API CALLING STUFF ******************************************

  /**
   * Method calls getLanguageList api and get list of languages supported by app.
   */
  private void getLanguageList() {
    if (AppUtil.isNetworkAvailable(this)) {
      new HttpClientRequest().setApiResponseListner(this);
      /*JSONObject jsonObject = new JSONObject();
      try {
        jsonObject.put("guest_id", shared.getGuestId());
        jsonObject.put("device_token", shared.getNotificationToken());
        jsonObject.put("device_id", Settings.Secure.getString(getContentResolver(),
            Settings.Secure.ANDROID_ID));
        jsonObject.put("device_type", "android");
      } catch (JSONException e) {
        e.printStackTrace();
      }

      StringEntity entity = null;
      try {
        entity = new StringEntity(jsonObject.toString());
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      new HttpClientRequest(this, shared.getFcmApi() +
          ConstatLib.PUSH_NOTIFICATION_REGISTRATION, entity,
          "application/vnd.digivalet.v1+json",
          GET_LANGUAGE_LIST_METHOD,true).makeHttpRequest();*/
    }
  }

  /**
   * Called when response received from api call.
   *
   * @param responseVal response
   * @param requestMethod request method name
   */
  @Override
  public void onSuccessResponse(String responseVal, String requestMethod) {
    if (requestMethod.equalsIgnoreCase(GET_LANGUAGE_LIST_METHOD)) {
      //TODO: Parse api response
    }
  }

  /**
   * Called on failure api response.
   *
   * @param errorMessage error string
   */
  @Override
  public void onFailureResponse(String errorMessage) {
    AppUtil.showAlertDialog(this, errorMessage, false,
        getResources().getString(R.string.dialog_errortitle), true, null);
  }

  /**
   * OnKeyDown callback will be called when phone back key pressed.
   *
   * @param keyCode keycode
   * @param event event
   * @return return boolean value
   */
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      AppUtil.finishActivityWithAnimation(this);
    }
    return super.onKeyDown(keyCode, event);
  }
}
