package com.nlk.note.service;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;


import com.nlk.note.MainActivity;
import com.nlk.note.NoteContext;
import com.nlk.note.R;
import com.nlk.note.ui.state.ShareViewModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ForegroundService extends Service {

    public static boolean serviceIsLive = false;
    public int NOTIFICATION_ID = 1;

    private Handler timeHandler;
    private NotificationCompat.Builder builder;
    private RemoteViews remoteViews;
    private static int duration = 0;
    private NotificationManager manager;
    private notificationReceiver notificationReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("changeTask");
        notificationReceiver = new notificationReceiver();
        registerReceiver(notificationReceiver,intentFilter);
    }



    private void createNotification(String title){
        // 唯一的通知通道的id.
        String notificationChannelId = "notification_channel_id_01";
        builder = new NotificationCompat.Builder(this, notificationChannelId);

        // Android8.0以上的系统，新建消息通道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //用户可见的通道名称
            String channelName = "Foreground Service Notification";
            //通道的重要程度
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(notificationChannelId, channelName, importance);
            notificationChannel.setDescription("Channel description");
            manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
        }

        // 自定义通知栏样式用到RemoteViews ，第一个参数固定写法，第二个布局id
        remoteViews = new RemoteViews(this.getPackageName(), R.layout.notification_skill);
        remoteViews.setTextViewText(R.id.tvName,title);

        builder.setContent(remoteViews);
        builder.setOngoing(true);
        builder.setSmallIcon(R.drawable.head);
        //通知标题
        //设定启动的内容
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        //创建通知
        startForeground(NOTIFICATION_ID, builder.build());

        timeHandler = new Handler();

        timeHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                remoteViews.setTextViewText(R.id.tvTime,getTime());
                manager.notify(NOTIFICATION_ID, builder.build());
                // 再次调用此Runnable对象
                if (serviceIsLive){
                    timeHandler.postDelayed(this, 6000);
                }
            }
        },0);

    }

    private String getTime(){
        duration = duration + 1;
        return String.valueOf(duration);
    }

    public static int getDuration(){
        return duration;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 标记服务启动
        ForegroundService.serviceIsLive = true;

        String title = intent.getAction();
        createNotification(title);
        return super.onStartCommand(intent, flags, startId);
    }

    public class notificationReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context,Intent intent){
            duration = 0;
            remoteViews.setTextViewText(R.id.tvName,intent.getStringExtra("name"));
            remoteViews.setTextViewText(R.id.tvTime,"0");
            manager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    @Override
    public void onDestroy() {
        // 标记服务关闭
        ForegroundService.serviceIsLive = false;
        // 移除通知
        stopForeground(true);
        unregisterReceiver(notificationReceiver);
        super.onDestroy();
    }

}