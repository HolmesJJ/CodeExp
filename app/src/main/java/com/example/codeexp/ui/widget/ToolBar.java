package com.example.codeexp.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.codeexp.R;
import com.example.codeexp.listener.OnMultiClickListener;

public class ToolBar extends RelativeLayout {

    private ImageButton mIbLeft;
    private TextView mTvTitle;
    private ImageButton mIbRight;
    private ToolBarClickListener mToolBarClickListener;

    public ToolBar(Context context) {

        super(context);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this);
        mIbLeft = findViewById(R.id.ib_left);
        mTvTitle = findViewById(R.id.tv_title);
        mIbRight = findViewById(R.id.ib_right);
        mIbLeft.setOnClickListener(new OnMultiClickListener() {

            @Override
            public void onMultiClick(View v) {

                if (mToolBarClickListener != null) {
                    mToolBarClickListener.leftIconClick();
                }
            }
        });
        mIbRight.setOnClickListener(new OnMultiClickListener() {

            @Override
            public void onMultiClick(View v) {

                if (mToolBarClickListener != null) {
                    mToolBarClickListener.rightIconClick();
                }
            }
        });
    }

    public ToolBar(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(context);
    }

    /**
     * 设置左边Icon图片
     *
     * @param resId 图片资源id
     *
     * @return 当前对象
     */
    public ToolBar setLeftImage(int resId) {

        if (mIbLeft != null) {
            mIbLeft.setImageResource(resId);
        }
        return this;
    }

    /**
     * 设置左边Icon图片
     *
     * @param bitmap bitmap类型的图片
     *
     * @return 当前对象
     */
    public ToolBar setLeftImage(Bitmap bitmap) {

        if (mIbLeft != null) {
            mIbLeft.setImageBitmap(bitmap);
        }
        return this;
    }

    /**
     * 设置左边Icon图片
     *
     * @param drawable drawable类型的图片
     *
     * @return 当前对象
     */
    public ToolBar setLeftImage(Drawable drawable) {

        if (mIbLeft != null) {
            mIbLeft.setImageDrawable(drawable);
        }
        return this;
    }

    /**
     * 设置标题文字
     *
     * @param title String类型的文本
     *
     * @return 当前对象
     */
    public ToolBar setTitle(String title) {

        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
        return this;
    }

    /**
     * 设置标题文字
     *
     * @param resId 文本资源id
     *
     * @return 当前对象
     */
    public ToolBar setTitle(int resId) {

        if (mTvTitle != null) {
            mTvTitle.setText(resId);
        }
        return this;
    }

    /**
     * 设置右边Icon图片
     *
     * @param resId 图片资源id
     *
     * @return 当前对象
     */
    public ToolBar setRightImage(int resId) {

        if (mIbRight != null) {
            mIbRight.setImageResource(resId);
        }
        return this;
    }

    /**
     * 设置右边Icon图片
     *
     * @param bitmap bitmap类型的图片
     *
     * @return 当前对象
     */
    public ToolBar setRightImage(Bitmap bitmap) {

        if (mIbRight != null) {
            mIbRight.setImageBitmap(bitmap);
        }
        return this;
    }

    /**
     * 设置右边Icon图片
     *
     * @param drawable drawable类型的图片
     *
     * @return 当前对象
     */
    public ToolBar setRightImage(Drawable drawable) {

        if (mIbRight != null) {
            mIbRight.setImageDrawable(drawable);
        }
        return this;
    }

    public ToolBar setToolBarClickListener(ToolBarClickListener clickListener) {

        this.mToolBarClickListener = clickListener;
        return this;
    }


    public interface ToolBarClickListener {

        /**
         * 左边按钮点击回调
         */
        void leftIconClick();

        /**
         * 右边按钮点击回调
         */
        void rightIconClick();
    }

}
