/*
 *  Created by Emaar Hospitality Group on 14/9/18 6:34 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 14/9/18 6:03 PM
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

package com.ehg.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.ehg.splash.adapter.SplashPagerAdapter;

/**
 * This class is using to create a Carousel effects on imageview and we are creating custom view.
 */
public class CarouselLayout extends LinearLayout {

  private float scale = SplashPagerAdapter.BIG_SCALE;

  /**
   * This is a parameterize constructor to initialise variables.
   *
   * @param context - calling class context.
   * @param attrs - Attributes which will using in a layout
   */
  public CarouselLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * Parameterize constructor.
   * @param context context of activity
   */
  public CarouselLayout(Context context) {
    super(context);
  }

  /**
   * This method will call to initialise the value of scale.
   */
  public void setScaleBoth(float scale) {
    this.scale = scale;
    this.invalidate();
  }

  /**
   * This method will call when images is going to Draw and
   * in this class we are Scaling the size of canvas.
   *
   * @param canvas - its object of canvas.
   */
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int w = this.getWidth();
    int h = this.getHeight();
    canvas.scale(scale, scale, w / 2, h / 2);
  }
}
