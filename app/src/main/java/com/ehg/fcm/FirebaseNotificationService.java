/*
 *  Created by Emaar Hospitality Group on 10/8/18 11:08 AM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 10/8/18 11:08 AM
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

package com.ehg.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.ehg.R;
import com.ehg.home.HomeActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * This class handles incoming messages from FCM.
 */

public class FirebaseNotificationService extends FirebaseMessagingService {

  private static final String TAG = "MyFirebaseMsgService";

  private String notificationBody = "";

  /**
   * Called when new notification token received.
   * @param notificationToken string value.
   */
  @Override
  public void onNewToken(String notificationToken) {
    super.onNewToken(notificationToken);
    Log.e("NOTIFICATION_TOKEN: ",notificationToken);
  }

  /**
   * Called when message is received.
   *
   * @param remoteMessage
   * Object representing the message received from Firebase Cloud Messaging.
   */
  // [START receive_message]
  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {
    try {

      // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
      Log.e(TAG, "From: " + remoteMessage.getFrom());

      // Check if message contains a data payload.
      if (remoteMessage.getData().size() > 0) {
        Log.e(TAG, "Message data payload: " + remoteMessage.getData());
      }

      if (remoteMessage.getNotification() != null
          && remoteMessage.getNotification().getBody() != null) {

        notificationBody = remoteMessage.getNotification().getBody().toString();
        Log.e(TAG, "Message Notification Body: "
            + remoteMessage.getNotification().getBody().toString());

        // Check if message contains a notification payload.
      } else if (remoteMessage.getData() != null && remoteMessage.getData().size() > 0) {

        notificationBody = remoteMessage.getData().toString();
        Log.e(TAG, "Message Notification Body: " + remoteMessage.getData().toString());
      }

      sendNotification(notificationBody);

      // Also if you intend on generating your own notifications as a result of a received FCM
      // message, here is where that should be initiated. See sendNotification method below.
    } catch (NullPointerException j) {
      j.printStackTrace();
    } catch (Exception j) {
      j.printStackTrace();
    }
  }
  // [END receive_message]

  /**
   * Create and show a simple notification containing the received FCM message.
   *
   * @param messageBody FCM message body received.
   */
  private void sendNotification(String messageBody) {

    Intent intent = new Intent(this, HomeActivity.class);

    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivity(this,
        0 /* Request code */, intent,
        PendingIntent.FLAG_ONE_SHOT);

    String channelId = "000"/*getString(R.string.default_notification_channel_id)*/;
    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationCompat.Builder notificationBuilder =
        new NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.emaar_logo)
            /*.setContentTitle(getResources().getString(R.string.app_name))*/
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent);

    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      notificationBuilder.setSmallIcon(R.drawable.emaar_logo);
      notificationBuilder.setColor(getResources().getColor(R.color.white));
    } else {
      notificationBuilder.setSmallIcon(R.drawable.emaar_logo);
    }

    NotificationManager notificationManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    // Since android Oreo notification channel is needed.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(channelId,
          getResources().getString(R.string.app_name),
          NotificationManager.IMPORTANCE_DEFAULT);
      notificationManager.createNotificationChannel(channel);
    }

    notificationManager.notify((int) System.currentTimeMillis() / 4 /* ID of notification */,
        notificationBuilder.build());
  }
}