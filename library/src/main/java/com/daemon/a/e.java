package com.daemon.a;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class e extends Presentation {
    public e(Context context, Display display) {
        super(context, display);
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new TextView(getContext()));
    }
}