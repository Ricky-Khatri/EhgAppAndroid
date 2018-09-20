package com.ehg.home.fragment;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ehg.R;
import com.ehg.home.HomeActivity;
import com.ehg.signinsignup.SignInSignupActivity;
import com.ehg.signinsignup.SignInSignupActivity.FragmentAdapter;
import com.ehg.signinsignup.fragment.SignUpFragment;
import com.ehg.signinsignup.fragment.SigninFragment;
import java.util.Objects;

public class ReservationsFragment extends BaseFragment {

  private Context context;

  /**
   * Called when fragment created.
   * @param savedInstanceState bundle object
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * Called to inflate fragment view.
   * @param inflater LayoutInflater
   * @param container ViewGroup
   * @param savedInstanceState Bundle
   * @return View
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_reservations, container, false);

    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      ((HomeActivity) Objects.requireNonNull(getActivity()))
          .updateToolbarTitle(getResources().getString(R.string.reservations_title));
    }

    return view;
  }

  /**
   * Called to instantiate view components of fragment.
   * @param view View
   * @param savedInstanceState Bundle
   */
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      ((HomeActivity) Objects.requireNonNull(getActivity()))
          .updateToolbarTitle(getResources().getString(R.string.reservations_title));
    }

    this.context = getActivity();
    initView(view);
  }

  /**
   * Called to attach activity context to fragment.
   * @param context activity context
   */
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  /**
   * Init's view components of this screen.
   * @param view view
   */
  private void initView(View view) {

    //Init tab layout
    TabLayout tabLayout = view.findViewById(R.id.tab_layout_all);
    final ViewPager viewPager = view.findViewById(R.id.viewpager_all_fragment_viewpager);

    //Create tabs
    tabLayout.addTab(tabLayout.newTab().setText(
        getResources().getString(R.string.reservation_upcomingtab)));
    tabLayout.addTab(tabLayout.newTab().setText(
        getResources().getString(R.string.reservation_pasttab)));
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    //Set viewpager fragment adapter
    FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager());
    viewPager.setAdapter(fragmentAdapter);

    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab layoutTab) {

        viewPager.setCurrentItem(layoutTab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab layoutTab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab layoutTab) {

      }
    });
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
          return new UpcomingReservationFragment();
        case 1:
          return new PastReservationFragment();
        default:
          return null;
      }
    }
  }
}
