
```
@Override
    public void onDaemonDead() {
        Log.d(TAG, "on daemon dead!");
        if (startServiceByAmsBinder()) {

            int pid = Process.myPid();
            Log.d(TAG, "mPid: " + mPid + " current pid: " + pid);
            Daemon.context.startInstrumentation(mApp,null,null);
            android.os.Process.killProcess(mPid);
        }
    }
```

# 2步集成使用
1、 打开library的AndroidManifest.xml找 如下位置：
```<instrumentation
        android:name="com.daemon.DInstrumentation"
        android:targetPackage="com.daemonLibrary.demo"
        android:targetProcesses="com.daemonLibrary.demo,com.daemonLibrary.demo:service" />
<application>
```
将包名替换成自己的包名

2、在app的Application中添加启动代码，并实现配置接口和回调接口
 ```
 override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //DaemonLog.d("Application onCrearte")
        Daemon.startWork(this, DaemonConfigurationImplement(this), DaemonCallbackImplement())
}
```


