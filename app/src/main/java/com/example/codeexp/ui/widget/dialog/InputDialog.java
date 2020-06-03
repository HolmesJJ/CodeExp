package com.example.codeexp.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.codeexp.R;
import com.example.codeexp.listener.OnMultiClickListener;

public class InputDialog extends BaseDialog {

    private TextView mTvTitle;
    private TextView mTvRemark;
    private EditText mEtScore;
    private TextView mTvUnit;
    private TextView mTvCancel;
    private TextView mTvConfirm;
    private LinearLayout mLlInput;
    private InputSelectListener mInputSelectListener;
    private String mTitle, mRemark;
    private int mMinScore;
    private int mMaxScore;
    private int mScore;
    private String mUnit;

    public InputDialog(@NonNull Context context) {

        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.createDialogView(savedInstanceState, R.layout.dialog_input);
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
        mTvRemark = findViewById(R.id.tv_remark);
        mEtScore = findViewById(R.id.et_score);
        mTvUnit = findViewById(R.id.tv_unit);
        mTvCancel = findViewById(R.id.tv_cancel);
        mTvConfirm = findViewById(R.id.tv_confirm);
        mLlInput = findViewById(R.id.ll_input);
        mTvTitle.setText(mTitle);
        mTvRemark.setText(mRemark);
        mTvUnit.setText(mUnit);
        mEtScore.setText(String.valueOf(mScore));
        mEtScore.setSelection(String.valueOf(mScore).length());
        mEtScore.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                mLlInput.setBackgroundResource(R.drawable.bg_rounded_rectangle_2dp);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if ("".equals(s.toString())) {
                    return;
                }
                mScore = Integer.valueOf(s.toString());
                if (mScore < mMinScore || mScore > mMaxScore) {
                    mLlInput.setBackgroundResource(R.drawable.bg_rounded_rectangle_2dp_warning);
                }
            }
        });
        mTvConfirm.setOnClickListener(new OnMultiClickListener() {

            @Override
            public void onMultiClick(View v) {

                if (mScore < mMinScore || mScore > mMaxScore) {
                    return;
                }
                if (mInputSelectListener != null) {
                    mInputSelectListener.confirm(mScore);
                }
                dismiss();
            }
        });
        mTvCancel.setOnClickListener(new OnMultiClickListener() {

            @Override
            public void onMultiClick(View v) {

                if (mInputSelectListener != null) {
                    mInputSelectListener.cancel();
                }
                dismiss();
            }
        });
    }

    public InputDialog setDialogTitle(String title) {

        this.mTitle = title;
        return this;
    }

    public InputDialog setDialogTitle(int resId) {

        this.mTitle = mContext.getString(resId);
        return this;
    }

    public InputDialog setRemark(String remark) {

        this.mRemark = remark;
        return this;
    }

    public InputDialog setRemark(int resId) {

        this.mRemark = mContext.getString(resId);
        return this;
    }

    /**
     * 设置输入框的初始值
     *
     * @param score int
     *
     * @return 当前对象
     */
    public InputDialog setScore(int score) {

        this.mScore = score;
        return this;
    }

    public InputDialog setMinScore(int minScore) {

        this.mMinScore = minScore;
        return this;
    }

    public InputDialog setMaxScore(int maxScore) {

        this.mMaxScore = maxScore;
        return this;
    }

    public InputDialog setUnit(String unit) {

        this.mUnit = unit;
        return this;
    }

    public InputDialog setInputSelectListener(InputSelectListener listener) {

        this.mInputSelectListener = listener;
        return this;
    }


    public interface InputSelectListener {

        /**
         * 点确定按钮回调
         *
         * @param score 输入框的值
         */
        void confirm(int score);

        /**
         * 点取消按钮回调
         */
        void cancel();
    }
}
