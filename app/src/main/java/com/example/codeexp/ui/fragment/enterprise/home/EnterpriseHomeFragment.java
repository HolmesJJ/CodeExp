package com.example.codeexp.ui.fragment.enterprise.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.databinding.FragmentEnterpriseHomeBinding;
import com.example.codeexp.ui.viewmodel.enterprise.EnterpriseHomeViewModel;

public class EnterpriseHomeFragment extends BaseFragment<FragmentEnterpriseHomeBinding, EnterpriseHomeViewModel> {

    public static EnterpriseHomeFragment newInstance(){

        Bundle args = new Bundle();
        EnterpriseHomeFragment fragment = new EnterpriseHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.fragment_enterprise_home;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<EnterpriseHomeViewModel> getViewModelClazz() {

        return EnterpriseHomeViewModel.class;
    }

    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(_mActivity.getString(R.string.home));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
    }
}
