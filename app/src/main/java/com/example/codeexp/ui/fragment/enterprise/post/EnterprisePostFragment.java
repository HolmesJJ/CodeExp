package com.example.codeexp.ui.fragment.enterprise.post;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.Nullable;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.databinding.FragmentEnterprisePostBinding;
import com.example.codeexp.ui.viewmodel.enterprise.post.EnterprisePostViewModel;
import com.example.codeexp.ui.widget.dialog.InputDialog;
import com.example.codeexp.ui.widget.dialog.RadioSelectDialog;
import com.example.codeexp.ui.widget.dialog.TextInputDialog;

import java.util.ArrayList;
import java.util.Calendar;
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
                        this::updateTargetDialog
                );
        mBinding.position.setLeftText(R.string.position).setBottomLineVisible(false)
                .setItemClickListener(
                        this::updatePositionDialog
                );
        mBinding.numberOfEmployees.setLeftText(R.string.number_of_employees).setBottomLineVisible(false)
                .setItemClickListener(
                        this::updateNumberOfEmployeeDialog
                );
        mBinding.workplace.setLeftText(R.string.workplace).setBottomLineVisible(false)
                .setItemClickListener(
                        this::updateWorkPlaceDialog
                );
        mBinding.jobNature.setLeftText(R.string.job_nature).setBottomLineVisible(false)
                .setItemClickListener(
                        this::updateJobNstureDialog
                );
        mBinding.expirationDate.setLeftText(R.string.expiration_date).setBottomLineVisible(true)
                .setItemClickListener(
                        this::updateExpirationDateDialog
                );

        mBinding.startDate.setLeftText(R.string.start_date).setBottomLineVisible(false)
                .setItemClickListener(
                        this::updateStartDateDialog
                );
        mBinding.endDate.setLeftText(R.string.end_date).setBottomLineVisible(true)
                .setItemClickListener(
                        this::updateEndDateDialog);

        mBinding.minOfSalary.setLeftText(R.string.min_of_salary).setBottomLineVisible(false)
                .setItemClickListener(
                        this::updateMinOfSalaryDialog);
        mBinding.maxOfSalary.setLeftText(R.string.max_of_salary).setBottomLineVisible(true)
                .setItemClickListener(
                        this::updateMaxOfSalaryDialog
                );

        mBinding.totalAmount.setLeftText(R.string.total_amount).setBottomLineVisible(true).setRightImage(android.R.color.transparent);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
    }

    private void updateTargetDialog() {

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

    private void updatePositionDialog() {

        List<RadioSelectDialog.Item> items = new ArrayList<>();
        items.add(new RadioSelectDialog.Item(0, true, "Waiter"));
        items.add(new RadioSelectDialog.Item(1, false, "Seller"));
        items.add(new RadioSelectDialog.Item(2, false, "Warehouse Manager"));

        RadioSelectDialog radioSelectDialog =
                new RadioSelectDialog(mContext).setDialogTitle(R.string
                        .position).setItemList(items)
                        .setSelectCallback(new RadioSelectDialog.SelectCallback() {

                            @Override
                            public void cancel() {

                            }

                            @Override
                            public void select(RadioSelectDialog.Item item) {

                                mBinding.position.setRightText(item.description);
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

    private void updateNumberOfEmployeeDialog() {
        InputDialog inputDialog =
                new InputDialog(mContext).setDialogTitle(R.string.number_of_employees)
                        .setRemark(R.string.range_1_1000).setScore(1).setMinScore(1).setMaxScore(1000).setUnit("")
                        .setInputSelectListener(new InputDialog.InputSelectListener() {

                            @Override
                            public void confirm(int score) {

                                mBinding.numberOfEmployees.setRightText(String.valueOf(score));
                            }

                            @Override
                            public void cancel() {

                            }
                        });
        inputDialog.show();
    }

    private void updateJobNstureDialog() {

        List<RadioSelectDialog.Item> items = new ArrayList<>();
        items.add(new RadioSelectDialog.Item(0, true, getString(R.string.part_time)));
        items.add(new RadioSelectDialog.Item(1, false, getString(R.string.full_time)));

        RadioSelectDialog radioSelectDialog =
                new RadioSelectDialog(mContext).setDialogTitle(R.string
                        .job_nature).setItemList(items)
                        .setSelectCallback(new RadioSelectDialog.SelectCallback() {

                            @Override
                            public void cancel() {

                            }

                            @Override
                            public void select(RadioSelectDialog.Item item) {

                                mBinding.jobNature.setRightText(item.description);
                            }
                        });
        radioSelectDialog.show();
    }

    private void updateMinOfSalaryDialog() {
        InputDialog inputDialog =
                new InputDialog(mContext).setDialogTitle(R.string.min_of_salary)
                        .setRemark(R.string.range_1_10000).setScore(1).setMinScore(1).setMaxScore(10000).setUnit("(S$)")
                        .setInputSelectListener(new InputDialog.InputSelectListener() {

                            @Override
                            public void confirm(int score) {

                                mBinding.minOfSalary.setRightText(String.valueOf(score));
                            }

                            @Override
                            public void cancel() {

                            }
                        });
        inputDialog.show();
    }

    private void updateMaxOfSalaryDialog() {
        InputDialog inputDialog =
                new InputDialog(mContext).setDialogTitle(R.string.min_of_salary)
                        .setRemark(R.string.range_1_10000).setScore(1).setMinScore(1).setMaxScore(10000).setUnit("(S$)")
                        .setInputSelectListener(new InputDialog.InputSelectListener() {

                            @Override
                            public void confirm(int score) {

                                mBinding.minOfSalary.setRightText(String.valueOf(score));
                            }

                            @Override
                            public void cancel() {

                            }
                        });
        inputDialog.show();
    }

    private void updateExpirationDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                _mActivity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mBinding.expirationDate.setRightText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateStartDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                _mActivity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mBinding.startDate.setRightText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateEndDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                _mActivity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mBinding.endDate.setRightText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}
