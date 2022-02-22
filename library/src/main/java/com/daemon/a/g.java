package com.daemon.a;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Build;
import android.view.Display;

/* loaded from: classes2.dex */
public class g implements Runnable {
    public Context a;
    public VirtualDisplay b;
    public DisplayManager c;
    public Display d;
    public e f11692e;

    public g(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                if (this.c == null) {
                    this.c = (DisplayManager) this.a.getSystemService(Context.DISPLAY_SERVICE);
                }
                if (this.c != null) {
                    if (this.b == null) {
                        DisplayManager displayManager = this.c;
                        this.b = displayManager.createVirtualDisplay("hm" + g.class.getName(), 10, 10, 10, null, 0);
                    }
                    if (this.d == null) {
                        this.d = this.b.getDisplay();
                    }
                    if (this.f11692e == null) {
                        this.f11692e = new e(this.a, this.d);
                    }
                    this.f11692e.show();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}