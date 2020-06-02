package com.example.codeexp.ui.fragment.individual.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.databinding.FragmentIndividualDetailBinding;
import com.example.codeexp.ui.viewmodel.individual.detail.IndividualDetailViewModel;

public class IndividualDetailFragment extends BaseFragment<FragmentIndividualDetailBinding, IndividualDetailViewModel> {

    private static final String ENTERPRISE_ID = "enterprise_id";

    private int mId;

    public static IndividualDetailFragment newInstance(int id){

        Bundle args = new Bundle();
        args.putInt(ENTERPRISE_ID, id);
        IndividualDetailFragment fragment = new IndividualDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mId = bundle.getInt(ENTERPRISE_ID);
        }
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.fragment_individual_detail;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<IndividualDetailViewModel> getViewModelClazz() {

        return IndividualDetailViewModel.class;
    }

    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(_mActivity.getString(R.string.detail));
        mBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher);
        mBinding.tvName.setText(String.valueOf(mId));

        mBinding.target.setLeftText(R.string.target).setRightText(R.string.individual).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.position.setLeftText(R.string.position).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.numberOfEmployees.setLeftText(R.string.number_of_employees).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.workplace.setLeftText(R.string.workplace).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.jobNature.setLeftText(R.string.job_nature).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.expirationDate.setLeftText(R.string.expiration_date).setBottomLineVisible(true).setRightImage(android.R.color.transparent);

        mBinding.startDate.setLeftText(R.string.start_date).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.endDate.setLeftText(R.string.end_date).setBottomLineVisible(true).setRightImage(android.R.color.transparent);

        mBinding.minOfSalary.setLeftText(R.string.min_of_salary).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.maxOfSalary.setLeftText(R.string.max_of_salary).setBottomLineVisible(true).setRightImage(android.R.color.transparent);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
    }
}
