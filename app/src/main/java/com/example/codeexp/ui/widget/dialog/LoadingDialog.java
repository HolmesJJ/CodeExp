package com.example.codeexp.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.codeexp.R;
import com.example.codeexp.ui.widget.LoadingView;

public class LoadingDialog extends Dialog {

    private TextView mTvTips;
    private Context mContext;
    private LoadingView mLoadingView;

    public LoadingDialog(@NonNull Context context) {

        super(context, R.style.Theme_Dialog);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.dialog_loading, null);
        mLoadingView = contentView.findViewById(R.id.loading_view);
        setOnDismissListener(dialog -> mLoadingView.stop());
        setContentView(contentView);
    }

    public void showLoading() {

        super.show();
        mLoadingView.start();
    }
    public void dismissLoading(){
        super.dismiss();
        if (mLoadingView != null){
            mLoadingView.stop();
        }

    }

}
