package com.daemon.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Keep;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


import com.daemon.R;
import com.daemon.a.g;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


@Keep
/* loaded from: classes.dex */
public final class CkActivityStarter {
    public static final int NOTIFY_DI = 10101;
    public static Context mAppContext;
    public static Handler mHandler = new a();

    /* loaded from: classes.dex */
    public static class a extends Handler {
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            NotificationManager notificationManager;
            try {
                if (message.what == 101 && (notificationManager = (NotificationManager) CkActivityStarter.getContext().getSystemService(Context.NOTIFICATION_SERVICE)) != null) {
                    notificationManager.cancel("phone_guard_tg_1", CkActivityStarter.NOTIFY_DI);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @RequiresApi(api = 21)
    public static void activityStarted() {
        try {
            ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE)).cancel("phone_guard_tg_1", NOTIFY_DI);

        } catch (Exception unused) {
        }
    }

    @RequiresApi(api = 21)
    @SuppressLint({"MissingPermission"})
    public static void clearExistActivity(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningTaskInfo runningTaskInfo : activityManager.getRunningTasks(10)) {
                if (runningTaskInfo.baseActivity.getPackageName().equalsIgnoreCase(context.getPackageName())) {
                    activityManager.getAppTasks().clear();
                    return;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Keep
    public static void createChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= 26 && notificationManager.getNotificationChannel("popup_guard_channel_id") == null) {
            NotificationChannel notificationChannel = new NotificationChannel("popup_guard_channel_id", "ck", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("ck");
            notificationChannel.setLockscreenVisibility(-1);
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationChannel.setShowBadge(false);
            notificationChannel.setSound(null, null);
            notificationChannel.setBypassDnd(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Keep
    public static Notification createNotification(PendingIntent pendingIntent, String str) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mAppContext, str);
        builder.setSmallIcon(R.drawable.ic_stat_name);
        builder.setFullScreenIntent(pendingIntent, true);
        builder.setContentTitle("手机优化中");
        builder.setContentText("正在优化您的手机");
        builder.setAutoCancel(true);
        builder.setOngoing(false);
        return builder.build();
    }

    public static void fixVivoIntent(Intent intent) {
        Field field = getField(Intent.class, "mIsVivoWidget");
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(intent, true);
            } catch (Exception unused) {
            }
        }
    }

    public static Context getContext() {
        return mAppContext;
    }

    public static Field getField(Class<?> cls, String str) {
        if (cls == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredField", String.class);
                declaredMethod.setAccessible(true);
                return (Field) declaredMethod.invoke(cls, str);
            } catch (Exception unused) {
                Class<? super Object> superclass = (Class<? super Object>) cls.getSuperclass();// todo Class<? super Object> superclass = cls.getSuperclass();
                if (superclass == null) {
                    return null;
                }
                return getField(superclass, str);
            }
        } else {
            try {
                return cls.getDeclaredField(str);
            } catch (NoSuchFieldException unused2) {
                Class<? super Object> superclass2 = (Class<? super Object>) cls.getSuperclass();//Class<? super Object> superclass2 = cls.getSuperclass();
                if (superclass2 == null) {
                    return null;
                }
                return getField(superclass2, str);
            }
        }
    }

    public static void initialize(Context context) {
        mAppContext = context;
    }

    @SuppressLint({"MissingPermission"})
    public static void moveTaskToFront(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningTaskInfo runningTaskInfo : activityManager.getRunningTasks(3)) {
                if (runningTaskInfo.baseActivity.getPackageName().equalsIgnoreCase(context.getPackageName())) {
                    activityManager.moveTaskToFront(runningTaskInfo.id, ActivityManager.MOVE_TASK_NO_USER_ACTION);
                    return;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @RequiresApi(api = 21)
    public static void queueStartRequest(Intent intent) {
        //TODO
//        try {
//            startActivityInner(intent);
//            startActivity(intent);
//            startByAlarm(mAppContext, intent, 50);
//            int currentROM = CkRomUtils.getCurrentROM();
//            if (currentROM == 4 || currentROM == 3) {
//                mcrz.a(mAppContext, intent);
//            }
//        } catch (Throwable th) {
//            th.printStackTrace();
//        }
    }

    @RequiresApi(api = 21)
    public static void queueStartServiceRequest(Intent intent) {
        startService(intent);
        int currentROM = CkRomUtils.getCurrentROM();
        if (currentROM == 4 || currentROM == 3) {
           // mcrz.b(mAppContext, intent);
        }
    }

    public static void splashNotification(PendingIntent pendingIntent) {
        try {
            NotificationManager notificationManager = (NotificationManager) mAppContext.getSystemService(Context.NOTIFICATION_SERVICE);
            createChannel(notificationManager);
            if (notificationManager != null) {
                notificationManager.cancel("phone_guard_tg_1", NOTIFY_DI);
                notificationManager.notify("phone_guard_tg_1", NOTIFY_DI, createNotification(pendingIntent, "popup_guard_channel_id"));
                mHandler.sendEmptyMessageDelayed(101, 2000L);
            }
        } catch (Exception unused) {
        }
    }

//   todo  @Keep
//    public static native void startActivity(Intent intent);

    public static void startActivityInner(Intent intent) {
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mAppContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public static void startByAlarm(Context context, Intent intent, int i2) {
        try {
            PendingIntent activity = PendingIntent.getActivity(context, 10102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.set(0, System.currentTimeMillis() + i2, activity);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(1082130432);
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    @RequiresApi(api = 21)
    public static void startOut(Intent intent, int i2) {
        //TODO
//        if (i2 == 1) {
//            startActivity(intent);
//        } else if (i2 == 2) {
//            startByAlarm(mAppContext, intent, 50);
//        } else if (i2 != 3) {
//            startActivity(intent);
//            int currentROM = CkRomUtils.getCurrentROM();
//            if (currentROM == 4 || currentROM == 3) {
//                mcrz.a(mAppContext, intent);
//            }
//        } else {
//            int currentROM2 = CkRomUtils.getCurrentROM();
//            if (currentROM2 == 4 || currentROM2 == 3) {
//                mcrz.a(mAppContext, intent);
//            }
//        }
    }

    public static void startService(Intent intent) {
        PendingIntent service = Build.VERSION.SDK_INT >= 26 ? PendingIntent.getService(mAppContext, 201, intent, 0) : null;
        try {
            service.send();
        } catch (PendingIntent.CanceledException unused) {
            mAppContext.startService(intent);
        }
        splashNotification(service);
    }

    public static void virDisplay(Context context) {
        new Handler(Looper.getMainLooper()).postDelayed(new g(context), 1000L);
    }
}