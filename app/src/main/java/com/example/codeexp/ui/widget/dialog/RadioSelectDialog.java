package com.example.codeexp.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.codeexp.R;
import com.example.codeexp.listener.OnMultiClickListener;

import java.util.ArrayList;
import java.util.List;

public class RadioSelectDialog extends BaseDialog {

    private List<Item> mItemList;
    private SelectCallback mSelectCallback;
    private ListView mLvContent;
    private TextView mTvTitle;
    private TextView mTvCancel;
    private RadioSelectAdapter mRadioSelectAdapter;

    private String mTitle;

    public RadioSelectDialog(@NonNull Context context) {

        super(context);
        mItemList = new ArrayList<>();
        mRadioSelectAdapter = new RadioSelectAdapter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.createDialogView(savedInstanceState, R.layout.dialog_radio_select);
    }

    @Override
    protected void initView() {

        mLvContent = findViewById(R.id.lv_content);
        mTvTitle = findViewById(R.id.tv_title);
        mTvCancel = findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(new OnMultiClickListener() {

            @Override
            public void onMultiClick(View v) {

                if (mSelectCallback != null) {
                    mSelectCallback.cancel();
                }
                dismiss();
            }
        });
        mLvContent.setAdapter(mRadioSelectAdapter);
        mTvTitle.setText(mTitle);
    }


    public RadioSelectDialog setDialogTitle(String title) {

        mTitle = title;
        return this;
    }

    public RadioSelectDialog setDialogTitle(int resId) {

        mTitle = mContext.getString(resId);
        return this;
    }

    /**
     * 设置可供选择的列表
     *
     * @param itemList 列表
     *
     * @return 当前对象
     */
    public RadioSelectDialog setItemList(List<Item> itemList) {

        this.mItemList.clear();
        this.mItemList.addAll(itemList);
        return this;
    }

    public RadioSelectDialog setSelectCallback(SelectCallback callback) {

        this.mSelectCallback = callback;
        return this;
    }

    public interface SelectCallback {

        /**
         * 选择后的回调
         *
         * @param item 选择项的具体信息
         */
        void select(Item item);

        /**
         * 点击取消的回调
         */
        void cancel();
    }

    public static class Item {

        public int id;
        public String description;
        private boolean isSelect;

        public Item(int id, boolean isSelect, String description) {

            this.id = id;
            this.isSelect = isSelect;
            this.description = description;
        }
    }

    public class RadioSelectAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return mItemList.size();
        }

        @Override
        public Object getItem(int position) {

            return mItemList.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;
            ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_radio_select, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.mLlItem = view.findViewById(R.id.ll_item);
                viewHolder.mRbSelect = view.findViewById(R.id.rb_select);
                viewHolder.mTvDescription = view.findViewById(R.id.tv_description);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.mTvDescription.setText(mItemList.get(position).description);
            viewHolder.mRbSelect.setChecked(mItemList.get(position).isSelect);
            viewHolder.mLlItem.setOnClickListener(new OnMultiClickListener() {

                @Override
                public void onMultiClick(View v) {

                    if (mSelectCallback != null) {
                        mSelectCallback.select(mItemList.get(position));
                    }
                    dismiss();
                }
            });
            return view;
        }

        class ViewHolder {

            LinearLayout mLlItem;
            RadioButton mRbSelect;
            TextView mTvDescription;
        }
    }
}
