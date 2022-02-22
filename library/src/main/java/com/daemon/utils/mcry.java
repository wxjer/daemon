package com.daemon.utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

/* loaded from: classes2.dex */
public class mcry extends Service {
    public void mc_xj() {
        for (int i2 = 0; i2 < 87; i2++) {
        }
    }

    public void mc_xm() {
        for (int i2 = 0; i2 < 98; i2++) {
        }
        mc_xx();
    }

    public void mc_xq() {
        for (int i2 = 0; i2 < 28; i2++) {
        }
    }

    public void mc_xx() {
        for (int i2 = 0; i2 < 9; i2++) {
        }
    }

    public void mc_xy() {
        for (int i2 = 0; i2 < 68; i2++) {
        }
    }

    public void mc_yc() {
        for (int i2 = 0; i2 < 90; i2++) {
        }
    }

    public void mc_yi() {
        mc_xm();
        for (int i2 = 0; i2 < 47; i2++) {
        }
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        stopSelf();
        System.exit(0);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        stopSelf();
        System.exit(0);
        return 2;
    }
}