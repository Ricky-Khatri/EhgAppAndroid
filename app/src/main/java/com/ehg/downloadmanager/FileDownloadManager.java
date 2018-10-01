/*
 *  Created by Emaar Hospitality Group on 1/10/18 6:41 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 1/10/18 6:28 PM
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

package com.ehg.downloadmanager;

import static com.ehg.downloadmanager.FileDownloadUtil.EHG_APP_ROOT_FOLDER;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.webkit.URLUtil;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * This class allows to download any file from url and store it in device storage.
 */
public class FileDownloadManager {

  private static FileDownloadManagerResponse fileDownloadManagerResponse;
  private static DownloadProgressUpdate progressUpdater;
  private Context context;

  private String finalProgress = "";

  /**
   * Constructor.
   *
   * @param context context of activity
   */
  public FileDownloadManager(Context context) {
    this.context = context;
  }

  /**
   * This class holds file download code stuff.
   */
  public class DownloadFile extends AsyncTask<String, Void, Boolean> {

    String fileName;
    String filePath;

    /**
     * All background operations will be handled in background thread.
     *
     * @param params parameters
     * @return boolean value
     */
    @Override
    protected Boolean doInBackground(String... params) {
      fileName = params[0];
      String url = params[1];
      filePath = params[2];
      return new FileDownloader().downloadFile(url, getFile(fileName, filePath));
    }

    /**
     * Called after background operation completed to modify ui thread.
     *
     * @param status boolean value
     */
    @Override
    protected void onPostExecute(Boolean status) {
      super.onPostExecute(status);

      if (status) {

        if (progressUpdater != null) {
          progressUpdater.onProgressUpdate(finalProgress);
        }

        if (fileDownloadManagerResponse != null) {

          if (filePath.equalsIgnoreCase("")) {

            fileDownloadManagerResponse.isFileDownloaded(true, fileName);

          } else {

            fileDownloadManagerResponse.isFileDownloaded(true, filePath);

          }
        }
      } else {
        if (fileDownloadManagerResponse != null) {
          fileDownloadManagerResponse.isFileDownloaded(false, fileName);
        }
      }
    }

    /**
     * Called before background operation started.
     */
    @Override
    protected void onPreExecute() {
      super.onPreExecute();

    }
  }

  /**
   * FileDownload class holds actual file downloading code stuff using Http calls.
   */
  public class FileDownloader {

    private static final int MEGABYTE = 1024 * 1024;

    /**
     * Called to downlodd file.
     * @param fileUrl file url
     * @param directory directory
     * @return boolean value
     */
    synchronized boolean downloadFile(String fileUrl, File directory) {
      try {

        URL url = new URL(fileUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //urlConnection.setRequestMethod("GET");
        //urlConnection.setDoOutput(true);
        urlConnection.setConnectTimeout(500000);
        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();
        //InputStream inputStream = new BufferedInputStream(url.openStream());

        FileOutputStream fileOutputStream = new FileOutputStream(directory, false);
        int totalSize = urlConnection.getContentLength();

        byte[] buffer = new byte[MEGABYTE];
        int bufferLength = 0;
        int count = 0;
        long total = 0;
        while ((bufferLength = inputStream.read(buffer)) > 0) {
          fileOutputStream.write(buffer, 0, bufferLength);

          ++count;

          total += bufferLength;

          finalProgress = "" + (int) ((total * 100) / totalSize);
        }

        fileOutputStream.close();
        fileOutputStream.flush();
        //  output.close();
        inputStream.close();
        return true;
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        if (fileDownloadManagerResponse != null) {
          fileDownloadManagerResponse.isFileDownloaded(false, "");
        }
        return false;
        // Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
      } catch (MalformedURLException e) {
        e.printStackTrace();
        if (fileDownloadManagerResponse != null) {
          fileDownloadManagerResponse.isFileDownloaded(false, "");
        }
        return false;
        //Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
      } catch (IOException e) {
        e.printStackTrace();
        if (fileDownloadManagerResponse != null) {
          fileDownloadManagerResponse.isFileDownloaded(false, "ioexception");
        }
        return false;
        //Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
      } catch (Exception s) {
        s.printStackTrace();
        if (fileDownloadManagerResponse != null) {
          fileDownloadManagerResponse.isFileDownloaded(false, "");
        }
        return false;
      }
    }
  }

  /**
   * Returns file or directory object.
   *
   * @param fileName fileName
   * @param filePath filePath
   * @return file object
   */
  public File getFile(String fileName, String filePath) {

    File file = null;
    String root = "";

    boolean success = true;
    root = Environment.getExternalStorageDirectory() + EHG_APP_ROOT_FOLDER;

    if (filePath.equalsIgnoreCase("")) {

      if (isExternalStorageAvailable()) {

        success = new File(root).mkdirs();
        file = new File(root, fileName);

        if (success) {
          // Do something on success
          //  Log.i("success", "success " + success);
        } else {
          // Do something else on failure
          // Log.i("success", "success " + success);
        }
      } else {
        file = new File(context.getFilesDir(), fileName);
      }

    } else {

      if (isExternalStorageAvailable()) {

        String subRoot = String.format("%s/%s", root, fileName);
        success = new File(subRoot).mkdirs();
        file = new File(subRoot, filePath);

        if (success) {

          Log.d("chk", "chk");
          //       // Do something on success
          //           Log.i("success", "success " + success);
        } else {
          // Do something else on failure
          //     Log.i("success", "success " + success);
        }

      } else {
        file = new File(context.getFilesDir(), filePath);
      }

    }
    //  Log.i("file----","file "+file.getAbsolutePath());

    return file;
  }

  /**
   * this method is used to fetch json from file and parse to class object ..... this method is
   * genric type method in this method we are passing dynamic class object to parse and save
   * dynamically from any classe....................Saket
   */
  public <T> T parseJson(String filename, String file, final T object, String key) {

    T dto = null;

    try {

      FileInputStream stream = new FileInputStream(getFile(filename, file));
      String jsonStr = null;
      try {
        FileChannel fc = stream.getChannel();
        MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

        String json = Charset.defaultCharset().decode(bb).toString();

        dto = (T) new Gson().fromJson(json, object.getClass());

      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        stream.close();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return dto;
  }

  /**
   * This method will return json string from .json file
   *
   * @param filePath filepath
   */
  public String getJsonString(String filePath) {

    String settingJson = "";

    try {
      FileInputStream stream = new FileInputStream(new File(filePath));
      String jsonStr = null;
      try {
        FileChannel fc = stream.getChannel();
        MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

        settingJson = Charset.defaultCharset().decode(bb).toString();

        Log.e("json================>", settingJson);

        //  val=true;
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        stream.close();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return settingJson;
  }


  /**
   * Check if external storage available or not.
   *
   * @return boolean value
   */
  private boolean isExternalStorageAvailable() {

    String state = Environment.getExternalStorageState();
    boolean externalStorageAvailable = false;
    boolean externalStorageWriteable = false;

    if (Environment.MEDIA_MOUNTED.equals(state)) {
      // We can read and write the media
      externalStorageAvailable = externalStorageWriteable = true;
    } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
      // We can only read the media
      externalStorageAvailable = true;
      externalStorageWriteable = false;
    } else {
      // Something else is wrong. It may be one of many other states, but
      // all we need
      // to know is we can neither read nor write
      externalStorageAvailable = externalStorageWriteable = false;
    }

    return externalStorageAvailable
        && externalStorageWriteable;
  }

  /**
   * FileDownloadManagerResponse Interface.
   */
  public interface FileDownloadManagerResponse {

    void isFileDownloaded(boolean isFileDownloaded, String fileName);
  }

  /**
   * DownloadProgressUpdate Interface.
   */
  public interface DownloadProgressUpdate {

    void onProgressUpdate(String progress);
  }

  /**
   * Called to set fileDownloadManagerResponseListener.
   * @param downloadManagerResponse object
   */
  public static void setFileDownloadResponseListener(FileDownloadManagerResponse
      downloadManagerResponse) {

    fileDownloadManagerResponse = downloadManagerResponse;
  }

  /**
   * Called to update download progress.
   *
   * @param progressUpdate object
   */
  public static void updateProgress(DownloadProgressUpdate progressUpdate) {

    progressUpdater = progressUpdate;
  }

  /**
   * Called to get file name from url.
   *
   * @param localUrl passed url
   * @return fileName
   */
  public String getfileNameFromUrl(String localUrl) {

    String url = "";
    try {
      url = URLUtil.guessFileName(localUrl, null, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return url;
  }
}