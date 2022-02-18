package com.daemon.process;

import com.daemon.DaemonLog;

/* package */class NativeLoader {

    static {
        try {
            System.loadLibrary("daemon");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public native void doDaemon(String indicatorSelfPath, String indicatorDaemonPath, String observerSelfPath, String observerDaemonPath);

    public void onDaemonDead() {
        DaemonLog.d("onDaemonDead.call");

        IProcess.Fetcher.fetchStrategy().onDaemonDead();
    }
}
