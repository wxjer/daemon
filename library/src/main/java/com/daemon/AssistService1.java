package com.daemon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.daemon.utils.ServiceHolder;

public class AssistService1 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceHolder.getInstance().bindService(this, DaemonService.class, null);
    }

}
