package com.example.codeexp.util;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class KeyboardUtils {
    public KeyboardUtils() {
    }

    public static void closeKeyboard(@NonNull Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity)context;
            View v = activity.getCurrentFocus();
            if (v != null && v.getWindowToken() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService("input_method");
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }

    }

    public static void openKeyboard(@NonNull Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity)context;
            View v = activity.getCurrentFocus();
            if (v != null && v.getWindowToken() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService("input_method");
                inputMethodManager.showSoftInput(v, 0);
            }
        }

    }

    public static boolean isShouldHideInput(@NonNull View v, @NonNull MotionEvent event) {
        if (v != null && event != null && v instanceof EditText) {
            int[] leftTop = new int[]{0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return event.getX() <= (float)left || event.getX() >= (float)right || event.getY() <= (float)top || event.getY() >= (float)bottom;
        } else {
            return false;
        }
    }
}
