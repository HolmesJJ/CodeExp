package com.example.codeexp.ui.fragment.enterprise.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.adapter.enterprise.home.EnterpriseHomeAdapter;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.callback.IOnItemClickListener;
import com.example.codeexp.databinding.FragmentEnterpriseHomeBinding;
import com.example.codeexp.entity.enterprise.home.EnterpriseHome;
import com.example.codeexp.ui.fragment.MainFragment;
import com.example.codeexp.ui.fragment.enterprise.detail.EnterpriseDetailFragment;
import com.example.codeexp.ui.viewmodel.enterprise.home.EnterpriseHomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseHomeFragment extends BaseFragment<FragmentEnterpriseHomeBinding, EnterpriseHomeViewModel> {

    private EnterpriseHomeAdapter mAdapter;

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

        mAdapter = new EnterpriseHomeAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mBinding.rvContainer.setLayoutManager(manager);
        mBinding.rvContainer.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new IOnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                // 因为启动的NewsDetailFragment是MainFragment的兄弟Fragment,所以需要MainFragment.start()
                // 也可以像使用getParentFragment()的方式,拿到父Fragment来操作 或者使用 EventBusActivityScope
                ((MainFragment) getParentFragment()).startBrotherFragment(EnterpriseDetailFragment.newInstance(mAdapter.getItem(position).getId()));
            }
        });

        // Init Datas
        List<EnterpriseHome> enterpriseHomeList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            EnterpriseHome enterpriseHome = new EnterpriseHome(i, "A" + i, "A" + i, "A" + i, i, "A" + i, "A" + i);
            enterpriseHomeList.add(enterpriseHome);
        }

        mAdapter.setDatas(enterpriseHomeList);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
    }
}
