/*
 *  Created by Emaar Hospitality Group on 25/8/18 6:13 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 25/8/18 6:12 PM
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.glide.slider.library.R.id;
import com.glide.slider.library.R.layout;
import com.glide.slider.library.SliderTypes.BaseSliderView;

/**
 * This class creates custom view for Sliding layout by extending BaseSliderView class.
 * From GlideSlider lib.
 */
public class TextSliderView extends BaseSliderView {

  /**
   * TextSliderView constructor.
   * @param context activity context
   */
  public TextSliderView(Context context) {
    super(context);
  }

  /**
   * Returns complete view.
   * @return view
   */
  public View getView() {
    View v = LayoutInflater.from(this.getContext()).inflate(layout.render_type_text,
        null);
    ImageView target = v.findViewById(id.glide_slider_image);
    target.setScaleType(ImageView.ScaleType.FIT_XY);
    TextView description = v.findViewById(id.description);
    description.setText(this.getDescription());
    this.bindEventAndShow(v, target);
    return v;
  }
}
