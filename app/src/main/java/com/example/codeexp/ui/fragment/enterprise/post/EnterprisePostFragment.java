package com.example.codeexp.ui.fragment.enterprise.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.databinding.FragmentEnterprisePostBinding;
import com.example.codeexp.ui.viewmodel.enterprise.EnterprisePostViewModel;
import com.example.codeexp.ui.widget.dialog.RadioSelectDialog;
import com.example.codeexp.ui.widget.dialog.TextInputDialog;

import java.util.ArrayList;
import java.util.List;

public class EnterprisePostFragment extends BaseFragment<FragmentEnterprisePostBinding, EnterprisePostViewModel> {

    public static EnterprisePostFragment newInstance(){

        Bundle args = new Bundle();
        EnterprisePostFragment fragment = new EnterprisePostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.fragment_enterprise_post;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<EnterprisePostViewModel> getViewModelClazz() {

        return EnterprisePostViewModel.class;
    }

    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(_mActivity.getString(R.string.post));
        mBinding.target.setLeftText(R.string.target).setRightText(R.string.enterprise).setBottomLineVisible(false)
                .setItemClickListener(
                        this::showTargetDialog
                );
        mBinding.position.setLeftText(R.string.position).setBottomLineVisible(false)
                .setItemClickListener(() -> {

                });
        mBinding.numberOfEmployee.setLeftText(R.string.number_of_employees).setBottomLineVisible(false)
                .setItemClickListener(() -> {

                });
        mBinding.workplace.setLeftText(R.string.workplace).setBottomLineVisible(false)
                .setItemClickListener(
                        this::updateWorkPlaceDialog
                );
        mBinding.jobNature.setLeftText(R.string.job_nature).setBottomLineVisible(false)
                .setItemClickListener(() -> {

                });
        mBinding.expirationDate.setLeftText(R.string.expiration_date).setBottomLineVisible(true)
                .setItemClickListener(() -> {

                });

        mBinding.startDate.setLeftText(R.string.start_date).setBottomLineVisible(false)
                .setItemClickListener(() -> {

                });
        mBinding.endDate.setLeftText(R.string.end_date).setBottomLineVisible(true)
                .setItemClickListener(() -> {

                });

        mBinding.minOfSalary.setLeftText(R.string.min_of_salary).setBottomLineVisible(false)
                .setItemClickListener(() -> {

                });
        mBinding.maxOfSalary.setLeftText(R.string.max_of_salary).setBottomLineVisible(true)
                .setItemClickListener(() -> {

                });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
    }

    private void showTargetDialog() {

        List<RadioSelectDialog.Item> items = new ArrayList<>();
        items.add(new RadioSelectDialog.Item(0, true, getString(R.string.enterprise)));
        items.add(new RadioSelectDialog.Item(1, false, getString(R.string.individual)));

        RadioSelectDialog radioSelectDialog =
                new RadioSelectDialog(mContext).setDialogTitle(R.string
                        .target).setItemList(items)
                        .setSelectCallback(new RadioSelectDialog.SelectCallback() {

                            @Override
                            public void cancel() {

                            }

                            @Override
                            public void select(RadioSelectDialog.Item item) {

                                mBinding.target.setRightText(item.description);
                            }
                        });
        radioSelectDialog.show();
    }

    private void updateWorkPlaceDialog() {
        TextInputDialog inputTextDialog = new TextInputDialog(_mActivity);
        inputTextDialog.show();
        inputTextDialog.setDialogTitle(R.string.workplace)
                .setMaxLength(100)
                .setDialogEventListener(new TextInputDialog.DialogEventListener() {

                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm(String text) {
                        mBinding.workplace.setRightText(text);
                    }
                });

    }
}
