package com.example.codeexp.ui.fragment.enterprise.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.databinding.FragmentEnterpriseMineBinding;
import com.example.codeexp.listener.OnMultiClickListener;
import com.example.codeexp.ui.viewmodel.enterprise.mine.EnterpriseMineViewModel;
import com.example.codeexp.util.ListenerUtils;

public class EnterpriseMineFragment extends BaseFragment<FragmentEnterpriseMineBinding, EnterpriseMineViewModel> {

    public static EnterpriseMineFragment newInstance(){
        Bundle args = new Bundle();
        EnterpriseMineFragment fragment = new EnterpriseMineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.fragment_enterprise_mine;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<EnterpriseMineViewModel> getViewModelClazz() {

        return EnterpriseMineViewModel.class;
    }

    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(_mActivity.getString(R.string.mine));
        mBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher);
        mBinding.tvName.setText("NUS PTE. LTD.");

        mBinding.type.setLeftText(R.string.type).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.numberOfEmployees.setLeftText(R.string.number_of_employees).setBottomLineVisible(true).setRightImage(android.R.color.transparent);

        ListenerUtils.setOnClickListener(mBinding.tvLogout, new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {

            }
        });

        ListenerUtils.setOnClickListener(mBinding.tvExit, new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {

            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
}
