/*
 *  Created by Emaar Hospitality Group on 10/8/18 11:33 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 10/8/18 11:33 AM
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

package com.ehg.exceptionhandler;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.firebase.crash.FirebaseCrash;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Locale;

/**
 * This class contains util methods for logging exception.
 */

public class ExceptionHandlerUtil {

  private AppCompatActivity appCompatActivity;

  /**
   * Constructor.
   *
   * @param activity activity context
   */
  public ExceptionHandlerUtil(AppCompatActivity activity) {
    appCompatActivity = activity;
  }

  /**
   * This method checks for File system state.
   */
  private StatFs getStatFs() {
    File path = Environment.getDataDirectory();
    return new StatFs(path.getPath());
  }

  /**
   * This method checks for available internal memory.
   */
  @SuppressWarnings("deprecation")
  private long getAvailableInternalMemorySize(StatFs stat) {
    long blockSize = stat.getBlockSize();
    long availableBlocks = stat.getAvailableBlocks();
    return availableBlocks * blockSize;
  }

  /**
   * This method checks for total internal memory.
   */
  private long getTotalInternalMemorySize(StatFs stat) {
    long blockSize = stat.getBlockSize();
    long totalBlocks = stat.getBlockCount();
    return totalBlocks * blockSize;
  }

  /**
   * This method adds device information.
   */
  private void addInformation(StringBuilder message) {
    message.append("Locale: ").append(Locale.getDefault()).append('\n');
    try {
      PackageManager pm = appCompatActivity.getPackageManager();
      PackageInfo packageInfo;
      packageInfo = pm.getPackageInfo(appCompatActivity.getPackageName(), 0);
      message.append("Version: ").append(packageInfo.versionName).append('\n');
      message.append("Package: ").append(packageInfo.packageName).append('\n');
    } catch (Exception e) {
      Log.e("CustomExceptionHandler", "Error", e);
      message.append("Could not get Version information for ").append(
          appCompatActivity.getPackageName());
    }
    message.append("Phone Model: ").append(android.os.Build.MODEL)
        .append('\n');
    message.append("Android Version: ")
        .append(android.os.Build.VERSION.RELEASE).append('\n');
    message.append("Board: ").append(android.os.Build.BOARD).append('\n');
    message.append("Brand: ").append(android.os.Build.BRAND).append('\n');
    message.append("Device: ").append(android.os.Build.DEVICE).append('\n');
    message.append("Host: ").append(android.os.Build.HOST).append('\n');
    message.append("ID: ").append(android.os.Build.ID).append('\n');
    message.append("Model: ").append(android.os.Build.MODEL).append('\n');
    message.append("Product: ").append(android.os.Build.PRODUCT)
        .append('\n');
    message.append("Type: ").append(android.os.Build.TYPE).append('\n');
    StatFs stat = getStatFs();
    message.append("Total Internal memory: ")
        .append(getTotalInternalMemorySize(stat)).append('\n');
    message.append("Available Internal memory: ")
        .append(getAvailableInternalMemorySize(stat)).append('\n');
  }

  /**
   * Method collect crash data and send it to fire base server.
   */
  public void uncaughtException(Thread thread, Throwable throwable) {
    StringBuilder report = new StringBuilder();
    try {
      Date curDate = new Date();
      report.append("Error Report collected on : ")
          .append(curDate.toString()).append('\n').append('\n');
      report.append("Informations :").append('\n');
      addInformation(report);
      report.append('\n').append('\n');
      report.append("Stack:\n\n\n");
      final Writer result = new StringWriter();
      final PrintWriter printWriter = new PrintWriter(result);
      throwable.printStackTrace(printWriter);
      report.append(result.toString());
      printWriter.close();
      report.append('\n').append('\n').append('\n');
      report.append("**** End of current Report ***");

      /**
       * Report exception to Firebase
       */
      FirebaseCrash.report(throwable);

      //Exit app
      System.exit(2);

    } catch (Throwable ignore) {
      System.exit(2);
      Log.e("UncaughtException", ignore.toString());
    }
  }
}

