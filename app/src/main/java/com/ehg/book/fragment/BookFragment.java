/*
 *  Created by Emaar Hospitality Group on 21/9/18 3:39 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 19/9/18 2:00 PM
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

package com.ehg.book.fragment;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.home.HomeActivity;
import com.ehg.book.adapter.BookAdapter;
import com.ehg.home.fragment.BaseFragment;
import com.ehg.book.pojo.BookPojo;
import com.ehg.utilities.AppUtil;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Fragment class.
 */
public class BookFragment extends BaseFragment {

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

    View view = inflater.inflate(R.layout.fragment_book, container, false);

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
          .updateToolbarTitle(getResources().getString(R.string.book_title));
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
   * Method init's view component of this fragment.
   *
   * @param view view
   */
  private void initView(View view) {
    RecyclerView recyclerViewBookList = view
        .findViewById(R.id.recyclerview_book_list);
    recyclerViewBookList.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewBookList.setHasFixedSize(true);

    //Prepare data
    ArrayList<BookPojo> bookList = new ArrayList<>();
    BookPojo bookPojo = new BookPojo();
    bookPojo.setTitle("Book a Hotel");
    bookPojo.setImageUrl("");
    bookList.add(bookPojo);
    bookPojo = new BookPojo();
    bookPojo.setTitle("Book a Restaurant");
    bookPojo.setImageUrl("");
    bookList.add(bookPojo);
    bookPojo = new BookPojo();
    bookPojo.setTitle("Book a Spa Treatment");
    bookPojo.setImageUrl("");
    bookList.add(bookPojo);
    bookPojo = new BookPojo();
    bookPojo.setTitle("Book a Round of Golf");
    bookPojo.setImageUrl("");
    bookList.add(bookPojo);
    bookPojo = new BookPojo();
    bookPojo.setTitle("Book an Experience");
    bookPojo.setImageUrl("");
    bookList.add(bookPojo);

    //Set adapter
    DisplayMetrics displaymetrics = new DisplayMetrics();
    ((BaseActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    BookAdapter bookAdapter = new BookAdapter(context,
        AppUtil.getDeviceHeight((BaseActivity) context),bookList);
    recyclerViewBookList.setAdapter(bookAdapter);
  }
}
