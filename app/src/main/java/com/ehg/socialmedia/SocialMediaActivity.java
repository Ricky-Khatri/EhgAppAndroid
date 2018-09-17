/*
 *  Created by Emaar Hospitality Group on 16/8/18 1:15 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 16/8/18 1:15 PM
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

package com.ehg.socialmedia;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;

/**
 * This class provides social media sharing options to user.
 */
public class SocialMediaActivity extends BaseActivity implements OnClickListener {

  private static final int ANIMATION_DURATION = 500;

  private static final int DELAY_DURATION = 400;

  /**
   * Called when activity created.
   * @param savedInstanceState bundle
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_social_media);

    initView();
  }

  /**
   * Init's view component of this screen.
   */
  private void initView() {

    TextView textViewTitle = findViewById(R.id.textview_header_title);
    textViewTitle.setText(getResources().getString(R.string.share));

    final ImageView imageViewFaceBook = findViewById(R.id.imageview_social_media_facebook);
    final ImageView imageViewTwitter = findViewById(R.id.imageview_social_media_twitter);
    final ImageView imageViewInstagram = findViewById(R.id.imageview_social_media_instagram);

    //set OnClickListener
    imageViewFaceBook.setOnClickListener(this);
    imageViewTwitter.setOnClickListener(this);
    imageViewInstagram.setOnClickListener(this);
    findViewById(R.id.imageview_header_back).setOnClickListener(this);

    //Start button animation after 600ms delay
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        animateViews(imageViewFaceBook,"translationY",-230f);
        animateViews(imageViewInstagram,"translationY",230f);
      }
    },DELAY_DURATION);
  }

  /**
   * Animates view on screen.
   * @param view requested view to animate
   * @param translationDirection translation direction of animation
   * @param value translation value
   */
  private void animateViews(View view, String translationDirection, float value) {
    ObjectAnimator animation = ObjectAnimator.ofFloat(view, translationDirection, value);
    animation.setDuration(ANIMATION_DURATION);
    animation.start();
  }

  /**
   * onClick callback method.
   *
   * @param view view
   */
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.imageview_social_media_facebook:
        SocialMediaUtil.sharePostOnFacebook(this, "Hi EHG");
        break;

      case R.id.imageview_social_media_twitter:
        SocialMediaUtil.sharePostOnTwitter(this, "Hi EHG", "");
        break;

      case R.id.imageview_social_media_instagram:
        SocialMediaUtil.sharePostOnInstagram(this, "", "title", "message");
        break;

      case R.id.imageview_header_back:
        AppUtil.finishActivityWithAnimation(this);
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
}
