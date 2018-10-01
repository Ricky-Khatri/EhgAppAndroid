/*
 *  Created by Emaar Hospitality Group on 1/10/18 6:42 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 1/10/18 6:42 PM
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

import android.os.Environment;

/**
 * Util class for FileDownloadManager.
 */
public class FileDownloadUtil {

  //EHG App root folder name
  public static final String EHG_APP_ROOT_FOLDER = "/.EHGApp";

  //File download base url
  public static final String FILE_DOWNLOAD_BASE_URL = "https://demo.digivalet.com/offlineApp/emaar/json/";

  //Constants
  public static final String LANGUAGE_FILE_NAME = "language.json";
  public static final String LANGUAGE_FOLDER_NAME = "LanguageJson";

  /**
   * Returns root folder path.
   * @return folder path
   */
  public static String getRootFolderPath() {
    return Environment.getExternalStorageDirectory() + EHG_APP_ROOT_FOLDER;
  }
}
