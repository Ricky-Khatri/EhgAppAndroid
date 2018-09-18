package com.ehg.home.fragment;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.ehg.R;
import com.ehg.home.HomeActivity;
import java.util.Objects;

/**
 * Fragment class.
 */
public class BookFragment extends BaseFragment {

  private int fragCount;
  private FragmentNavigation fragmentNavigation;

  public static BookFragment newInstance(int instance) {
    Bundle args = new Bundle();
    args.putInt(ARGS_INSTANCE, instance);
    BookFragment fragment = new BookFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_book, container, false);

    ButterKnife.bind(this, view);

    Bundle args = getArguments();
    if (args != null) {
      fragCount = args.getInt(ARGS_INSTANCE);
    }

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      ((HomeActivity) Objects.requireNonNull(getActivity()))
          .updateToolbarTitle(getResources().getString(R.string.book_title));
    }
  }
}
