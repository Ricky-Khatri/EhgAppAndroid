package com.ehg.home.fragment;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ehg.R;
import com.ehg.home.HomeActivity;
import java.util.Objects;

public class ReservationsFragment extends BaseFragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

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
}
