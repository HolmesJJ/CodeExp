package com.example.codeexp.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public abstract class BaseDialog extends AlertDialog {

    protected Context mContext;

    public BaseDialog(@NonNull Context context) {

        super(context);
        this.mContext = context;
    }

    protected void createDialogView(Bundle savedInstanceState, int layoutId) {

        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        initView();
    }

    protected abstract void initView();
}
