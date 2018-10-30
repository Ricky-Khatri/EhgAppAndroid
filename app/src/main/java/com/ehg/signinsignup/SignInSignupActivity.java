/*
 * Created by Emaar Hospitality Group on 8/8/18 11:41 AM
 * Copyright (C) 2018.  All rights reserved.
 * Last modified 8/8/18 11:41 AM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ehg.signinsignup;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.signinsignup.fragment.SignUpFragment;
import com.ehg.signinsignup.fragment.SigninFragment;
import com.ehg.utilities.AppUtil;

/**
 * This class shows Login options to users like: Login Signup Skip or explore app as guest user.
 */
public class SignInSignupActivity extends BaseActivity {

  private  OnCountryCodeChangeListener onCountryCodeChangeListener;

  private SigninFragment signinFragment;
  private SignUpFragment signUpFragment;

  /**
   * Called when activity created.
   *
   * @param savedInstanceState bundle
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signin_signup);

    initView();
  }

  /**
   * Init's view components of this screen.
   */
  private void initView() {

    try {
      signinFragment = new SigninFragment();
      signUpFragment = new SignUpFragment();
      //Init tab layout
      TabLayout tabLayout = findViewById(R.id.tab_layout_all);
      final ViewPager viewPager = findViewById(R.id.viewpager_all_fragment_viewpager);

      //Create tabs
      tabLayout.addTab(tabLayout.newTab().setText(
          getResources().getString(R.string.all_signin)));
      tabLayout.addTab(tabLayout.newTab().setText(
          getResources().getString(R.string.signupfragment_signup)));
      tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

      //Set viewpager fragment adapter
      FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
      viewPager.setAdapter(fragmentAdapter);

      viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

      tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab layoutTab) {
          AppUtil.hideKeyboard(SignInSignupActivity.this,viewPager);
          viewPager.setCurrentItem(layoutTab.getPosition());
          if (onCountryCodeChangeListener != null) {
            onCountryCodeChangeListener.onCountryCodeChanged();
          }
          if (signinFragment != null) {
            signinFragment.clearFields();
          }
          if (signUpFragment != null) {
            signUpFragment.clearFields();
          }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab layoutTab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab layoutTab) {
        }
      });

    } catch (NullPointerException n) {
      n.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
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
   *
   * @param appCompatImageView imageview object
   */
  @Override
  public void setBackArrowRtl(AppCompatImageView appCompatImageView) {
    super.setBackArrowRtl(appCompatImageView);
  }

  /**
   * This class initializes viewpager fragment adapter.
   */
  public class FragmentAdapter extends FragmentStatePagerAdapter {

    /**
     * Constructor.
     *
     * @param fragmentManager para
     */
    public FragmentAdapter(FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
      return 2;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return signinFragment != null ? signinFragment :  new SigninFragment();
        case 1:
          return signUpFragment != null ? signUpFragment : new SignUpFragment();
        default:
          return null;
      }
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
   * Called to set countryCodeChangeListener object.
   * @param onCountryCodeChangeListener object
   */
  public void setOnCountryCodeChangeListener(OnCountryCodeChangeListener onCountryCodeChangeListener) {
    this.onCountryCodeChangeListener = onCountryCodeChangeListener;
  }
  /**
   * Interface for CountryCodeChange.
   */
  public interface OnCountryCodeChangeListener {
    void onCountryCodeChanged();
  }
}
