/*
 *  Created by Emaar Hospitality Group on 16/10/18 12:24 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 16/10/18 12:24 PM
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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.NumberPicker;
import com.ehg.R;

/**
 * This class will show number picker dialog.
 */
public class NumberPickerDialog extends DialogFragment {

  private NumberPicker.OnValueChangeListener valueChangeListener;

  /**
   * Called when dialog created.
   * @param savedInstanceState bundle object
   * @return returns dialog
   */
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {

    final NumberPicker numberPicker = new NumberPicker(getActivity());

    numberPicker.setMinValue(1);
    numberPicker.setMaxValue(5);
    numberPicker.setValue(2);

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle("Select number of people");
    builder.setMessage("");

    builder.setPositiveButton(getActivity().getString(R.string.dialog_ok)
        , new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            valueChangeListener.onValueChange(numberPicker,
                numberPicker.getValue(), numberPicker.getValue());
          }
        });

    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        valueChangeListener.onValueChange(numberPicker,
            numberPicker.getValue(), numberPicker.getValue());
      }
    });

    builder.setView(numberPicker);
    return builder.create();
  }

  /**
   * Called to get valueChangeListener object.
   * @return
   */
  public NumberPicker.OnValueChangeListener getValueChangeListener() {
    return valueChangeListener;
  }

  /**
   * Called to set valueChangeListener
   * @param valueChangeListener
   */
  public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
    this.valueChangeListener = valueChangeListener;
  }
}