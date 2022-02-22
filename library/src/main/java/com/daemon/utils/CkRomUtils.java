package com.daemon.utils;

import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Keep;

import java.lang.reflect.Method;

@Keep
/* loaded from: classes.dex */
public final class CkRomUtils {
    public static final int ROM_HUAWEI = 2;
    public static final int ROM_MI = 1;
    public static final int ROM_OPPO = 3;
    public static final int ROM_UNKOWN = 0;
    public static final int ROM_VIVO = 4;
    public static int detectedROM;

    static {
        detectROM();
    }

    public static void detectROM() {
        try {
            Class.forName("miui.os.Build");
            detectedROM = 1;
        } catch (Exception unused) {
            if (getProp("ro.vivo.os.build.display.id").toLowerCase().contains("funtouch") || getPhoneType().equals(AgooConstants.MESSAGE_SYSTEM_SOURCE_VIVO) || "iqoo".equalsIgnoreCase(Build.BRAND)) {
                detectedROM = 4;
            } else if (!TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().contains(AgooConstants.MESSAGE_SYSTEM_SOURCE_OPPO)) {
                detectedROM = 3;
            } else if (isHUAWEI() || isHonor()) {
                detectedROM = 2;
            } else {
                detectedROM = 0;
            }
        }
    }

    @Keep
    public static int getCurrentROM() {
        return detectedROM;
    }

    public static String getPhoneType() {
        String str = Build.MANUFACTURER;
        return (str == null || str.length() <= 0) ? "" : str.toLowerCase();
    }

    public static String getProp(String str) {
        Method method;
        Method method2;
        if (Build.VERSION.SDK_INT < 26) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class, String.class);
                declaredMethod.setAccessible(true);
                return (String) declaredMethod.invoke(null, str, "");
            } catch (Exception unused) {
                return "";
            }
        } else {
            try {
                method = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            } catch (Exception e) {
                e.printStackTrace();
                method = null;
            }
            method.setAccessible(true);
            try {
                method2 = (Method) method.invoke(Class.forName("android.os.SystemProperties"), "get", new Class[]{String.class, String.class});
            } catch (Exception e2) {
                e2.printStackTrace();
                method2 = null;
            }
            method2.setAccessible(true);
            try {
                return (String) method2.invoke(null, str, "");
            } catch (Exception e3) {
                e3.printStackTrace();
                return "";
            }
        }
    }

    public static boolean isHUAWEI() {
        if (TextUtils.isEmpty(Build.BRAND) || !Build.BRAND.toLowerCase().startsWith("huawei")) {
            return !TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith("huawei");
        }
        return true;
    }

    public static boolean isHonor() {
        if (TextUtils.isEmpty(Build.BRAND) || !Build.BRAND.toLowerCase().startsWith("honor")) {
            return !TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith("honor");
        }
        return true;
    }
}