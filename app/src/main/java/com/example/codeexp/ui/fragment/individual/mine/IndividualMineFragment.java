package com.example.codeexp.ui.fragment.individual.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.databinding.FragmentIndividualMineBinding;
import com.example.codeexp.listener.OnMultiClickListener;
import com.example.codeexp.ui.fragment.MainFragment;
import com.example.codeexp.ui.fragment.individual.acceptedjob.IndividualAcceptedJobFragment;
import com.example.codeexp.ui.viewmodel.individual.mine.IndividualMineViewModel;
import com.example.codeexp.util.ListenerUtils;

import me.yokeyword.fragmentation.SupportFragment;

public class IndividualMineFragment extends BaseFragment<FragmentIndividualMineBinding, IndividualMineViewModel> {

    public static IndividualMineFragment newInstance(){
        Bundle args = new Bundle();
        IndividualMineFragment fragment = new IndividualMineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.fragment_individual_mine;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<IndividualMineViewModel> getViewModelClazz() {

        return IndividualMineViewModel.class;
    }

    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(_mActivity.getString(R.string.mine));
        mBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher);
        mBinding.tvName.setText("Johnson");

        mBinding.contact.setLeftText(R.string.contact).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.email.setLeftText(R.string.email).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.bankAccount.setLeftText(R.string.bank_account).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.enterprise.setLeftText(R.string.enterprise).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.staffNumber.setLeftText(R.string.staff_number).setBottomLineVisible(true).setRightImage(android.R.color.transparent);
        mBinding.acceptedJobDetail.setLeftText(R.string.accepted_job_detail).setBottomLineVisible(true)
                .setItemClickListener(() -> {
                    // 因为启动的NewsDetailFragment是MainFragment的兄弟Fragment,所以需要MainFragment.start()
                    // 也可以像使用getParentFragment()的方式,拿到父Fragment来操作 或者使用 EventBusActivityScope
                    ((MainFragment) getParentFragment()).startBrotherFragment(IndividualAcceptedJobFragment.newInstance());
                });

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
