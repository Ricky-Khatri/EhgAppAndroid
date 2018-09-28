/*
 *  Created by Emaar Hospitality Group on 13/8/18 11:12 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 13/8/18 11:12 AM
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

package com.ehg.settings;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.apppreferences.SharedPreferenceUtils;
import com.ehg.home.BaseActivity;
import com.ehg.language.LanguageActivity;
import com.ehg.signinsignup.SignInSignupActivity;
import com.ehg.utilities.AppUtil;

/**
 * This class allows users to do local app settings.
 */
public class SettingsActivity extends BaseActivity implements OnClickListener {

  private TextView textViewUserState;

  private AppCompatImageView imageViewPreference;
  private AppCompatImageView imageViewSupport;
  private AppCompatImageView imageViewPrivacyPolicy;
  private AppCompatImageView imageViewTermsAndCondition;
  private AppCompatImageView imageViewLanguage;
  private AppCompatImageView imageViewUserState;

  /**
   * Called when activity created first.
   *
   * @param savedInstanceState bundle object
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_settings);

    initView();
  }

  /**
   * Init's view components of this screen.
   */
  private void initView() {

    TextView textViewTitle = findViewById(R.id.textview_header_title);
    textViewTitle.setText(getResources().getString(R.string.settings_title));

    //Set OnClickListener
    findViewById(R.id.imageview_header_back).setOnClickListener(this);
    findViewById(R.id.linearlayout_setting_preferences).setOnClickListener(this);
    findViewById(R.id.linearlayout_settings_support).setOnClickListener(this);
    findViewById(R.id.linearlayout_settings_privacypolicy).setOnClickListener(this);
    findViewById(R.id.linearlayout_settings_termandconditions).setOnClickListener(this);
    findViewById(R.id.linearlayout_settings_language).setOnClickListener(this);
    findViewById(R.id.linearlayout_settings_userstate).setOnClickListener(this);

    textViewUserState = findViewById(R.id.textview_settings_userstate);

    if (SharedPreferenceUtils.getInstance(this)
        .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")
        .equalsIgnoreCase("")) {

      textViewUserState.setText(getResources().getString(R.string.settings_sign_in_sign_up));

    } else {

      textViewUserState.setText(getResources().getString(R.string.settings_signuot));
    }

    imageViewPreference = findViewById(R.id.imageview_setting_preference);
    imageViewSupport = findViewById(R.id.imageview_setting_support);
    imageViewPrivacyPolicy = findViewById(R.id.imageview_setting_privacypolicy);
    imageViewTermsAndCondition = findViewById(R.id.imageview_setting_termsandcondition);
    imageViewLanguage = findViewById(R.id.imageview_setting_language);
    imageViewUserState = findViewById(R.id.imageview_setting_userstate);
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
   * Called when activity resumed.
   */
  @Override
  protected void onResume() {
    super.onResume();

    try {
      setBackArrowRtl((AppCompatImageView) findViewById(R.id.imageview_header_back));
      if (SharedPreferenceUtils.getInstance(this).getStringValue(
          SharedPreferenceUtils.APP_LANGUAGE, "").equalsIgnoreCase("ar")) {
        imageViewPreference.setImageResource(R.drawable.ic_back_arrow_white);
        imageViewSupport.setImageResource(R.drawable.ic_back_arrow_white);
        imageViewPrivacyPolicy.setImageResource(R.drawable.ic_back_arrow_white);
        imageViewTermsAndCondition.setImageResource(R.drawable.ic_back_arrow_white);
        imageViewLanguage.setImageResource(R.drawable.ic_back_arrow_white);
        imageViewUserState.setImageResource(R.drawable.ic_back_arrow_white);
      } else {
        imageViewPreference.setImageResource(R.drawable.all_arrowpointtoright);
        imageViewSupport.setImageResource(R.drawable.all_arrowpointtoright);
        imageViewPrivacyPolicy.setImageResource(R.drawable.all_arrowpointtoright);
        imageViewTermsAndCondition.setImageResource(R.drawable.all_arrowpointtoright);
        imageViewLanguage.setImageResource(R.drawable.all_arrowpointtoright);
        imageViewUserState.setImageResource(R.drawable.all_arrowpointtoright);
      }
    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Called when user click on any view attached OnClickListener.
   *
   * @param view clicked view object
   */
  @Override
  public void onClick(View view) {

    Intent intent;

    switch (view.getId()) {

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
        break;

      case R.id.linearlayout_setting_preferences:
        AppUtil.showAlertDialog(this, "Function not implemented.",
            false, "", true, null);
        break;

      case R.id.linearlayout_settings_privacypolicy:
        AppUtil.loadWebView(this,
            getResources().getString(R.string.settings_privacy_policy),
            AppUtil.PRIVACY_POLICY_URL, false);
        break;

      case R.id.linearlayout_settings_termandconditions:
        AppUtil.loadWebView(this,
            getResources().getString(R.string.settings_termsandconditions),
            AppUtil.TERMS_AND_CONDITIONS_URL, false);
        break;

      case R.id.linearlayout_settings_support:
        AppUtil.loadWebView(this,
            getResources().getString(R.string.settings_support),
            AppUtil.SUPPORT_URL, false);
        break;

      case R.id.linearlayout_settings_language:
        intent = new Intent(this, LanguageActivity.class);
        AppUtil.startActivityWithAnimation(this, intent, false);
        break;

      case R.id.linearlayout_settings_userstate:
        if (SharedPreferenceUtils.getInstance(this)
            .getStringValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "")
            .equalsIgnoreCase("")) {

          intent = new Intent(this, SignInSignupActivity.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
          AppUtil.startActivityWithAnimation(this, intent, true);

        } else {
          showSignoutDialog(this);
        }

        break;

      default:
        break;
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

  /**
   * This method will use to show Sign out dialog and allows user to Sign-out from app.
   *
   * @param appCompatActivity calling class object
   */
  private void showSignoutDialog(final AppCompatActivity appCompatActivity) {

    try {

      // We need to get the instance of the LayoutInflater
      final Dialog dialog = new Dialog(appCompatActivity);
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      dialog.setCancelable(true);
      dialog.setContentView(R.layout.view_alertdialog);
      dialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogAnimation;

      TextView textViewTitle = dialog.findViewById(R.id.textview_alertdialog_title);
      TextView textViewAlertMessage = dialog.findViewById(R.id.textview_alertdialog_message);
      textViewTitle.setText(getResources().getString(R.string.all_confirmtitle));

      textViewAlertMessage.setText(getResources().getString(R.string.setting_signoutmessage));

      Button buttonCancel = dialog.findViewById(R.id.button_alertdialog_cancel);
      buttonCancel.setText(getResources().getString(R.string.all_no));
      buttonCancel.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          dialog.dismiss();
        }
      });

      Button buttonOk = dialog.findViewById(R.id.button_alertdialog_ok);
      buttonOk.setText(getResources().getString(R.string.all_yes));
      buttonOk.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {

          dialog.dismiss();

          //Clear preference and redirect to Sign-in/Sign-up activity
          SharedPreferenceUtils.getInstance(appCompatActivity)
              .setValue(SharedPreferenceUtils.LOYALTY_MEMBER_ID, "");
          Intent intent = new Intent(appCompatActivity, SignInSignupActivity.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
          AppUtil.startActivityWithAnimation(appCompatActivity, intent, true);
        }
      });

      dialog.show();

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (RuntimeException rte) {
      rte.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}