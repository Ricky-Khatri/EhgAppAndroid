/*
 *  Created by Emaar Hospitality Group on 9/8/18 2:56 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 9/8/18 2:56 PM
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

package com.ehg.utilities;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

/**
 * This class sets glide image rendering configuration.
 */

public class GlideConfiguration implements GlideModule {

  /**
   * Called to apply image decode format for showing image quality.
   * @param context activity context
   * @param builder gliderBuilder
   */
  @Override
  public void applyOptions(Context context, GlideBuilder builder) {
    // Apply options to the builder here.
    builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
  }

  /**
   * Called to register glide components.
   * @param context activity context
   * @param glide glide object
   */
  @Override
  public void registerComponents(Context context, Glide glide) {
    // register ModelLoaders here.
  }
}
