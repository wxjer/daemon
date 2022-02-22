package com.daemon.utils;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.PowerManager;
import android.util.Base64;
import androidx.annotation.RequiresApi;


@RequiresApi(api = 21)
/* loaded from: classes2.dex */
public class mcrz extends JobService {
    public static int a = 80886;
    public static int b = 80887;
    public static int c = 80888;

    public static void a(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            jobScheduler.cancel(a);
        }
    }

    public static boolean b(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(c, new ComponentName(context.getPackageName(), mcrz.class.getName()));
        builder.setMinimumLatency(1000L);
        if (Build.VERSION.SDK_INT >= 24) {
            builder.setTriggerContentMaxDelay(60000L);
        }
        return (jobScheduler == null || jobScheduler.schedule(builder.build()) == JobScheduler.RESULT_FAILURE) ? false : true;
    }

    public void mc_lfx() {
        for (int i2 = 0; i2 < 57; i2++) {
        }
    }

    public void mc_lgf() {
        for (int i2 = 0; i2 < 34; i2++) {
        }
        mc_lfx();
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        PowerManager powerManager;
        int jobId = jobParameters.getJobId();
        if (jobId == c) {
            startService(new Intent(this, mcry.class));
            return false;
        }
        PersistableBundle extras = jobParameters.getExtras();
        String string = "intent";
        boolean z = true;
        if (z) {
            byte[] decode = Base64.decode(string, 2);
            Parcel obtain = Parcel.obtain();
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            Intent intent = new Intent();
            intent.readFromParcel(obtain);
            obtain.recycle();
            if (jobId == b) {
                CkActivityStarter.startService(intent);
            } else {
                CkActivityStarter.startActivity(intent);
            }
        }
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public static void a(Context context, Intent intent) {
        try {
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder builder = new JobInfo.Builder(a, new ComponentName(context.getPackageName(), mcrz.class.getName()));
            builder.setMinimumLatency(0L);
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setTriggerContentMaxDelay(60000L);
            }
            Parcel obtain = Parcel.obtain();
            intent.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            String encodeToString = Base64.encodeToString(marshall, 2);
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("intent", encodeToString);
            persistableBundle.putLong("start_time", System.currentTimeMillis());
            builder.setExtras(persistableBundle);
            if (jobScheduler != null && jobScheduler.schedule(builder.build()) == JobScheduler.RESULT_FAILURE) {
                CkActivityStarter.startActivity(intent);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(Context context, Intent intent) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(b, new ComponentName(context.getPackageName(), mcrz.class.getName()));
        builder.setMinimumLatency(0L);
        if (Build.VERSION.SDK_INT >= 24) {
            builder.setTriggerContentMaxDelay(60000L);
        }
        Parcel obtain = Parcel.obtain();
        intent.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        String encodeToString = Base64.encodeToString(marshall, 2);
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("intent", encodeToString);
        persistableBundle.putLong("start_time", System.currentTimeMillis());
        builder.setExtras(persistableBundle);
        if (jobScheduler != null && jobScheduler.schedule(builder.build()) == JobScheduler.RESULT_FAILURE) {
            CkActivityStarter.startActivity(intent);
        }
    }
}