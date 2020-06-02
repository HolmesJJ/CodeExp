package com.example.codeexp.ui.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.codeexp.R;
import com.example.codeexp.listener.OnMultiClickListener;

public class TextInputDialog extends BaseDialog {
    private TextView mTvTitle;
    private RelativeLayout mRlInputContainer;
    private EditText mEtInput;
    private TextView mTvWordCount;
    private TextView mTvCancel;
    private TextView mTvConfirm;

    private int mMaxLength;
    private DialogEventListener mDialogEventListener;

    public TextInputDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.createDialogView(savedInstanceState, R.layout.dialog_text_input);
        Window window = getWindow();
        if (window != null) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams
                    .FLAG_ALT_FOCUSABLE_IM);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager
                    .LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }

    }

    @Override
    protected void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mRlInputContainer = findViewById(R.id.rl_input_container);
        mEtInput = findViewById(R.id.et_input);
        mTvWordCount = findViewById(R.id.tv_word_count);
        mTvCancel = findViewById(R.id.tv_cancel);
        mTvConfirm = findViewById(R.id.tv_confirm);
        setInputListener();
        setConfirmListener();
        setCancelListener();
    }

    public TextInputDialog setDialogTitle(String title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
        return this;
    }

    public TextInputDialog setDialogTitle(int resId) {
        if (mTvTitle != null) {
            mTvTitle.setText(resId);
        }
        return this;
    }

    public TextInputDialog setEditTextHint(String hint) {
        if (mEtInput != null) {
            mEtInput.setHint(hint);
        }
        return this;
    }

    public TextInputDialog setEditTextHint(int resId) {
        if (mEtInput != null) {
            mEtInput.setHint(resId);
        }
        return this;
    }

    public TextInputDialog setEditText(String text) {
        if (mEtInput != null) {
            mEtInput.setText(text);
            mEtInput.setSelection(mEtInput.getText().length());
        }
        return this;
    }

    public TextInputDialog setEditText(int resId) {
        if (mEtInput != null) {
            mEtInput.setText(resId);
            mEtInput.setSelection(mEtInput.getText().length());
        }
        return this;
    }

    public TextInputDialog setDialogEventListener(DialogEventListener listener) {
        mDialogEventListener = listener;
        return this;
    }

    public TextInputDialog setMaxLength(int maxLength) {
        mMaxLength = maxLength;
        if (mTvWordCount != null) {
            String text = mEtInput.getText().toString();
            mTvWordCount.setText(text.length() + "/" + maxLength);
            if (text.length() > mMaxLength) {
                mTvWordCount.setTextColor(Color.parseColor("#F82A1F"));
                mRlInputContainer.setBackgroundResource(R.drawable.bg_rounded_rectangle_2dp_warning);
            } else {
                mTvWordCount.setTextColor(Color.parseColor("#CCCCCC"));
                mRlInputContainer.setBackgroundResource(R.drawable.bg_rounded_rectangle_2dp);
            }
        }
        return this;
    }


    private void setConfirmListener() {
        mTvConfirm.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                String text = mEtInput.getText().toString();
                if (text.length() > mMaxLength) {
                    return;
                }
                if (mDialogEventListener != null) {
                    mDialogEventListener.confirm(text);
                }
                dismiss();
            }
        });
    }

    private void setCancelListener() {
        mTvCancel.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (mDialogEventListener != null) {
                    mDialogEventListener.cancel();
                }
                dismiss();
            }
        });
    }

    private void setInputListener() {
        mEtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                mTvWordCount.setText(text.length() + "/" + mMaxLength);
                if (text.length() > mMaxLength) {
                    mTvWordCount.setTextColor(Color.parseColor("#F82A1F"));
                    mRlInputContainer.setBackgroundResource(R.drawable.bg_rounded_rectangle_2dp_warning);
                } else {
                    mTvWordCount.setTextColor(Color.parseColor("#CCCCCC"));
                    mRlInputContainer.setBackgroundResource(R.drawable.bg_rounded_rectangle_2dp);
                }
            }
        });
    }

    public interface DialogEventListener {

        /**
         * 点击确定按钮后的回调
         */
        void cancel();

        /**
         * 点击取消按钮后的回调
         */
        void confirm(String text);
    }
}
