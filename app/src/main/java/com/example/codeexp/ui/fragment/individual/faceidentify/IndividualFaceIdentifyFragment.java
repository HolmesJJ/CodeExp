package com.example.codeexp.ui.fragment.individual.faceidentify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.databinding.FragmentIndividualFaceIdentifyBinding;
import com.example.codeexp.ui.viewmodel.individual.faceidentify.IndividualFaceIdentifyViewModel;

public class IndividualFaceIdentifyFragment extends BaseFragment<FragmentIndividualFaceIdentifyBinding, IndividualFaceIdentifyViewModel> {

    private static final String IS_CHECK_IN = "is_check_in";

    private boolean isCheckIn = true;

    public static IndividualFaceIdentifyFragment newInstance(boolean isCheckIn) {

        Bundle args = new Bundle();
        args.putBoolean(IS_CHECK_IN, isCheckIn);
        IndividualFaceIdentifyFragment fragment = new IndividualFaceIdentifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            isCheckIn = bundle.getBoolean(IS_CHECK_IN);
        }
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.fragment_individual_face_identify;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<IndividualFaceIdentifyViewModel> getViewModelClazz() {

        return IndividualFaceIdentifyViewModel.class;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void initData() {

        super.initData();

        if (isCheckIn) {
            mBinding.toolbar.setTitle(_mActivity.getString(R.string.check_in));
        } else {
            mBinding.toolbar.setTitle(_mActivity.getString(R.string.check_out));
        }
    }
}
