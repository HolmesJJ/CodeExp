package com.example.codeexp.ui.fragment.individual.acceptedjob;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.databinding.FragmentIndividualAcceptedJobBinding;
import com.example.codeexp.listener.OnMultiClickListener;
import com.example.codeexp.ui.viewmodel.individual.acceptedjob.IndividualAcceptedJobViewModel;
import com.example.codeexp.util.ListenerUtils;

public class IndividualAcceptedJobFragment extends BaseFragment<FragmentIndividualAcceptedJobBinding, IndividualAcceptedJobViewModel> {

    public static IndividualAcceptedJobFragment newInstance(){
        Bundle args = new Bundle();
        IndividualAcceptedJobFragment fragment = new IndividualAcceptedJobFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.fragment_individual_accepted_job;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<IndividualAcceptedJobViewModel> getViewModelClazz() {

        return IndividualAcceptedJobViewModel.class;
    }

    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(_mActivity.getString(R.string.mine));
        mBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher);
        mBinding.tvName.setText("NTU PTE. LTD.");

        mBinding.staffNumber.setLeftText(R.string.staff_number).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.position.setLeftText(R.string.position).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.workplace.setLeftText(R.string.workplace).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.jobNature.setLeftText(R.string.job_nature).setBottomLineVisible(true).setRightImage(android.R.color.transparent);

        mBinding.startDate.setLeftText(R.string.start_date).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.endDate.setLeftText(R.string.end_date).setBottomLineVisible(true).setRightImage(android.R.color.transparent);

        mBinding.salary.setLeftText(R.string.salary).setBottomLineVisible(true).setRightImage(android.R.color.transparent);

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
