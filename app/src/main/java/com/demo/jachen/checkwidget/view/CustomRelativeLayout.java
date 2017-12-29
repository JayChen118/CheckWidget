package com.demo.jachen.checkwidget.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by jachen on 12/14/2017.
 */

public class CustomRelativeLayout extends RelativeLayout {
    private static final String TAG = "CustomRelativeLayout";

    public CustomRelativeLayout(Context context) {
        super(context);
        Log.d(TAG, "CustomRelativeLayout() called with: context = [" + context + "]");
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "CustomRelativeLayout() called with: context = [" + context + "], attrs = [" + attrs + "]");
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "CustomRelativeLayout() called with: context = [" + context + "], attrs = [" + attrs + "], defStyleAttr = [" + defStyleAttr + "]");
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Log.d(TAG, "onSaveInstanceState() called");

        Bundle bundle = new Bundle();
//        bundle.putParcelable("super", super.onSaveInstanceState());

        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Log.d(TAG, "onRestoreInstanceState() called with: state = [" + state + "]");
        super.onRestoreInstanceState(state);
    }
}
