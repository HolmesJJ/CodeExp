package com.example.codeexp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class TouchEventTransferRelativeLayout extends RelativeLayout {

    public TouchEventTransferRelativeLayout(Context context) {

        super(context);
    }

    public TouchEventTransferRelativeLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        super.dispatchTouchEvent(ev);
        super.onTouchEvent(ev);
        return true;
    }
}
