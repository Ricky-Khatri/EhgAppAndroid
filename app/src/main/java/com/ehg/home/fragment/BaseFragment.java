/**
 * Copyright (C) 2018 Emaar Hospitality Group Project. All rights reserved. Licensed under the
 * Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.ehg.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * This is a base class for all the fragment class.
 */

public class BaseFragment extends Fragment {

  public static final String ARGS_INSTANCE = "com.ehg.baseFragment";

  private FragmentNavigation fragmentNavigation;

  /**
   * Called when fragment created.
   * @param savedInstanceState bundle object
   */
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  /**
   * Called after onCreate() and attached activity context to fragment.
   * @param context activity context
   */
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof FragmentNavigation) {
      fragmentNavigation = (FragmentNavigation) context;
    }
  }

  /**
   * FragmentNavigation interface.
   */
  public interface FragmentNavigation {

    void pushFragment(Fragment fragment);
  }
}
