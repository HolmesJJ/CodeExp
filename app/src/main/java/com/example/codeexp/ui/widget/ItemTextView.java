package com.example.codeexp.ui.widget;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.codeexp.R;
import com.example.codeexp.listener.OnMultiClickListener;
import com.example.codeexp.util.ContextUtils;
import com.example.codeexp.util.MeasureUtils;
import com.example.codeexp.util.ToastUtils;

public class ItemTextView extends RelativeLayout {

    private TextView mTvLeft;
    private TextView mTvRight;
    private ImageView mIvRight;
    private View mTopLine;
    private View mBottomLine;
    private RelativeLayout mRLItem;
    private ItemClickListener mItemClickListener;

    public ItemTextView(Context context) {

        super(context);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.layout_item_edit, this);
        mTvLeft = findViewById(R.id.tv_left);
        mTvRight = findViewById(R.id.tv_right);
        mIvRight = findViewById(R.id.iv_right);
        mTopLine = findViewById(R.id.top_line);
        mBottomLine = findViewById(R.id.bottom_line);
        mRLItem = findViewById(R.id.rl_item);
        mRLItem.setOnClickListener(new OnMultiClickListener() {

            @Override
            public void onMultiClick(View v) {

                if (mItemClickListener != null) {
                    mItemClickListener.click();
                }
            }
        });
        mRLItem.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                onClickCopy(mTvRight);
                return true;
            }
        });
    }

    /**
     * 点击复制到剪贴板
     *
     * @param view
     */
    private void onClickCopy(TextView view) {

        if (view == null) {
            return;
        }
        ClipboardManager cm = (ClipboardManager) ContextUtils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(view.getText());
        ToastUtils.showShort(R.string.copy_success);
    }

    public ItemTextView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(context);
    }

    /**
     * 设置左边文本
     *
     * @param resId 文本资源id
     *
     * @return 当前对象
     */
    public ItemTextView setLeftText(int resId) {

        if (mTvLeft != null) {
            mTvLeft.setText(resId);
        }
        return this;
    }

    /**
     * 设置左边文本
     *
     * @param text String类型文本
     *
     * @return 当前对象
     */
    public ItemTextView setLeftText(String text) {

        if (mTvLeft != null) {
            mTvLeft.setText(text);
        }
        return this;
    }

    /**
     * 获取右边文本
     */
    public String getRightText() {

        if (mTvRight != null) {
            return mTvRight.getText().toString();
        }
        return "";
    }

    /**
     * 设置右边文本
     *
     * @param resId 文本资源id
     *
     * @return 当前对象
     */
    public ItemTextView setRightText(int resId) {

        if (mTvRight != null) {
            mTvRight.setText(resId);
        }
        return this;
    }

    /**
     * 设置右边文本
     *
     * @param text String类型文本
     *
     * @return 当前对象
     */
    public ItemTextView setRightText(String text) {

        if (mTvRight != null) {
            mTvRight.setText(text);
        }
        return this;
    }

    /**
     * 设置右边图片
     *
     * @param resId 图片资源id
     *
     * @return 当前对象
     */
    public ItemTextView setRightImage(int resId) {

        if (mIvRight != null) {
            mIvRight.setImageResource(resId);
        }
        return this;
    }

    /**
     * 设置右边图片
     *
     * @param bitmap bitmap类型图片
     *
     * @return 当前对象
     */
    public ItemTextView setRightImage(Bitmap bitmap) {

        if (mIvRight != null) {
            mIvRight.setImageBitmap(bitmap);
        }
        return this;
    }

    /**
     * 设置右边图片
     *
     * @param drawable drawable类型tup
     *
     * @return 当前对象
     */
    public ItemTextView setRightImage(Drawable drawable) {

        if (mIvRight != null) {
            mIvRight.setImageDrawable(drawable);
        }
        return this;
    }

    /**
     * 设置右边图标是否可见
     *
     * @param isVisible true可见，false不可见
     *
     * @return 当前对象
     */
    public ItemTextView setRightImageVisible(boolean isVisible) {

        if (mIvRight != null) {
            mIvRight.setVisibility(isVisible ? VISIBLE : GONE);
        }
        return this;
    }

    /**
     * 设置顶部线条左边距
     *
     * @param marginLeft 左边距值,dp值
     *
     * @return 当前对象
     */
    public ItemTextView setTopLineMarginLeft(int marginLeft) {

        if (mTopLine != null) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mTopLine.getLayoutParams();
            lp.setMargins(MeasureUtils.dip2px(getContext(), marginLeft), lp.topMargin, lp.rightMargin, lp.bottomMargin);
            mTopLine.setLayoutParams(lp);
        }
        return this;
    }

    /**
     * 设置底部线条左边距
     *
     * @param marginLeft 左边距值
     *
     * @return 当前对象
     */
    public ItemTextView setBottomLineMarginLeft(int marginLeft) {

        if (mBottomLine != null) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mBottomLine.getLayoutParams();
            lp.setMargins(marginLeft, lp.topMargin, lp.rightMargin, lp.bottomMargin);
            mBottomLine.setLayoutParams(lp);
        }
        return this;
    }

    /**
     * 设置顶部线条是否可见
     *
     * @param isVisible 可见true，不可见false
     *
     * @return 当前对象
     */
    public ItemTextView setTopLineVisible(boolean isVisible) {

        if (mTopLine != null) {
            mTopLine.setVisibility(isVisible ? VISIBLE : GONE);
        }
        return this;
    }

    /**
     * 设置底部线条是否可见
     *
     * @param isVisible 可见true，不可见false
     *
     * @return 当前对象
     */
    public ItemTextView setBottomLineVisible(boolean isVisible) {

        if (mBottomLine != null) {
            mBottomLine.setVisibility(isVisible ? VISIBLE : GONE);
        }
        return this;
    }

    /**
     * 设置左边文本的颜色
     *
     * @param color 颜色
     *
     * @return 当前对象
     */
    public ItemTextView setLeftTextColor(int color) {

        if (mTvLeft != null) {
            mTvLeft.setTextColor(color);
        }
        return this;
    }

    /**
     * 设置右边文本颜色
     *
     * @param color 颜色
     *
     * @return 当前对象
     */
    public ItemTextView setRightTextColor(int color) {

        if (mTvRight != null) {
            mTvRight.setTextColor(color);
        }
        return this;
    }

    /**
     * 设置点击事件监听
     *
     * @param clickListener 监听器
     *
     * @return 当前对象
     */
    public ItemTextView setItemClickListener(ItemClickListener clickListener) {

        this.mItemClickListener = clickListener;
        return this;
    }

    public interface ItemClickListener {

        void click();
    }
}
