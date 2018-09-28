/*
 *  Created by Emaar Hospitality Group on 21/9/18 4:20 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 21/9/18 4:20 PM
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

package com.ehg.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

/**
 * This class is used to show web url's in webview container.
 */
public class WebviewActivity extends BaseActivity {

  /**
   * The browser.
   */
  private WebView browser;

  private String url = "";

  /**
   * Called when activity created.
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);

    initView();
  }

  /**
   * This method init's view and load url in web view.
   */
  private void initView() {
    try {

      if (getIntent().getStringExtra("url") != null) {
        url = getIntent().getStringExtra("url");
      } else {
        url = "https://www.emaar.com/en/";
      }

      if (getIntent() != null && getIntent().getStringExtra("title") != null) {
        TextView titleTv = findViewById(R.id.textview_header_title);
        titleTv.setText(getIntent().getStringExtra("title"));
      }

      //Load url
      loadWebview();

      AppCompatImageView appCompatImageViewBackArrow = findViewById(R.id.imageview_header_back);
      appCompatImageViewBackArrow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          AppUtil.finishActivityWithAnimation(WebviewActivity.this);
        }
      });

      setBackArrowRtl(appCompatImageViewBackArrow);

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception n) {
      n.printStackTrace();
    }
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
   * @param appCompatImageView imageview object
   */
  @Override
  public void setBackArrowRtl(AppCompatImageView appCompatImageView) {
    super.setBackArrowRtl(appCompatImageView);
  }

  /**
   * Open and load url in web view.
   */
  public void loadWebview() {
    browser = findViewById(R.id.webview);
    browser.setWebViewClient(new MyBrowser());
    browser.getSettings().setLoadWithOverviewMode(true);
    browser.getSettings().setUseWideViewPort(true);
    browser.getSettings().setLoadsImagesAutomatically(true);
    browser.getSettings().setBuiltInZoomControls(true);
    browser.getSettings().setPluginState(PluginState.ON);
    browser.getSettings().setJavaScriptEnabled(true);
    browser.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
    browser.setScrollbarFadingEnabled(false);
    browser.getSettings().setUseWideViewPort(true);
    browser.getSettings().setLoadWithOverviewMode(true);
    browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    if (url != null && !url.equalsIgnoreCase("")) {
      browser.loadUrl(url);
    }
  }

  /**
   * The Class MyBrowser.
   */
  private class MyBrowser extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

      view.loadUrl(url);

      return true;
    }

    /**
     * The progress dialog.
     */
    //Show loader on url load
    public void onLoadResource(WebView view, String url) {
            /*if (mProgressBar != null) {
                loadingFinished = false;
                // in standard case YourActivity.this
                mProgressBar.show();
            }*/
    }

    /**
     * Called when error received while loading web page.
     * @param view view
     * @param errorCode integer code
     * @param description description
     * @param failingUrl failed url
     */
    @Override
    public void onReceivedError(WebView view, int errorCode, String description,
        String failingUrl) {
      AppUtil.dismissLoadingIndicator(WebviewActivity.this);
    }

    /**
     * Called when web page loading started.
     * @param view webview object
     * @param url url to load
     * @param bitmap bitmap object
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap bitmap) {

      AppUtil.showLoadingIndicator(WebviewActivity.this);

      super.onPageStarted(view, url, bitmap);
    }

    /**
     * Called on web page loading finished.
     * @param view webview object
     * @param url url loaded
     */
    public void onPageFinished(WebView view, String url) {
      AppUtil.dismissLoadingIndicator(WebviewActivity.this);
    }
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
