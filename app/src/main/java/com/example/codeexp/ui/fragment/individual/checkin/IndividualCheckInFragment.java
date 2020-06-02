package com.example.codeexp.ui.fragment.individual.checkin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.databinding.FragmentIndividualCheckInBinding;
import com.example.codeexp.listener.OnMultiClickListener;
import com.example.codeexp.ui.fragment.MainFragment;
import com.example.codeexp.ui.fragment.individual.faceidentify.IndividualFaceIdentifyFragment;
import com.example.codeexp.ui.viewmodel.individual.checkin.IndividualCheckInViewModel;
import com.example.codeexp.util.ListenerUtils;

import me.yokeyword.fragmentation.SupportFragment;

public class IndividualCheckInFragment extends BaseFragment<FragmentIndividualCheckInBinding, IndividualCheckInViewModel> {

    public static IndividualCheckInFragment newInstance(){
        Bundle args = new Bundle();
        IndividualCheckInFragment fragment = new IndividualCheckInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.fragment_individual_check_in;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<IndividualCheckInViewModel> getViewModelClazz() {

        return IndividualCheckInViewModel.class;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(_mActivity.getString(R.string.check_in_out));

        ListenerUtils.setOnClickListener(mBinding.btnCheckIn, new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                // 因为启动的NewsDetailFragment是MainFragment的兄弟Fragment,所以需要MainFragment.start()
                // 也可以像使用getParentFragment()的方式,拿到父Fragment来操作 或者使用 EventBusActivityScope
                ((MainFragment) getParentFragment()).startBrotherFragment(IndividualFaceIdentifyFragment.newInstance(true));
            }
        });

        ListenerUtils.setOnClickListener(mBinding.btnCheckOut, new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                // 因为启动的NewsDetailFragment是MainFragment的兄弟Fragment,所以需要MainFragment.start()
                // 也可以像使用getParentFragment()的方式,拿到父Fragment来操作 或者使用 EventBusActivityScope
                ((MainFragment) getParentFragment()).startBrotherFragment(IndividualFaceIdentifyFragment.newInstance(false));
            }
        });
    }
}
