package com.example.codeexp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;

public class TouchEventTransferEditText extends AppCompatEditText {

    public TouchEventTransferEditText(Context context) {

        super(context);
    }

    public TouchEventTransferEditText(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        super.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        super.dispatchTouchEvent(event);
        return true;
    }
}
