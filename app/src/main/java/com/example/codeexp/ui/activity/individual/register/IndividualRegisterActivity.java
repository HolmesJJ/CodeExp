package com.example.codeexp.ui.activity.individual.register;

import android.os.Bundle;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseActivity;
import com.example.codeexp.databinding.ActivityIndividualRegisterBinding;
import com.example.codeexp.ui.viewmodel.individual.register.IndividualRegisterViewModel;
import com.example.codeexp.ui.widget.dialog.RadioSelectDialog;
import com.example.codeexp.ui.widget.dialog.TextInputDialog;
import com.example.codeexp.util.ContextUtils;

import java.util.ArrayList;
import java.util.List;

public class IndividualRegisterActivity extends BaseActivity<ActivityIndividualRegisterBinding, IndividualRegisterViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {

        return R.layout.activity_individual_register;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<IndividualRegisterViewModel> getViewModelClazz() {

        return IndividualRegisterViewModel.class;
    }

    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(ContextUtils.getContext().getString(R.string.individual_register));
        mBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher);

        mBinding.name.setLeftText(R.string.name).setBottomLineVisible(false)
                .setItemClickListener(
                        this::showUpdateNameDialog
                );
        mBinding.contact.setLeftText(R.string.contact).setBottomLineVisible(false)
                .setItemClickListener(
                        this::showUpdateContactDialog
                );
        mBinding.email.setLeftText(R.string.email).setBottomLineVisible(false)
                .setItemClickListener(
                        this::showUpdateEmailDialog
                );
        mBinding.bankAccount.setLeftText(R.string.bank_account).setBottomLineVisible(false)
                .setItemClickListener(
                        this::showUpdateBankAccountDialog
                );
        mBinding.enterprise.setLeftText(R.string.enterprise).setBottomLineVisible(false)
                .setItemClickListener(
                        this::showUpdateEnterpriseDialog
                );
        mBinding.staffNumber.setLeftText(R.string.staff_number).setBottomLineVisible(true)
                .setItemClickListener(
                        this::showUpdateStaffNumberDialog
                );
    }

    private void showUpdateNameDialog() {
        TextInputDialog inputTextDialog = new TextInputDialog(mContext);
        inputTextDialog.show();
        inputTextDialog.setDialogTitle(R.string.name)
                .setMaxLength(50)
                .setDialogEventListener(new TextInputDialog.DialogEventListener() {

                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm(String text) {
                        mBinding.name.setRightText(text);
                    }
                });
    }

    private void showUpdateContactDialog() {
        TextInputDialog inputTextDialog = new TextInputDialog(mContext);
        inputTextDialog.show();
        inputTextDialog.setDialogTitle(R.string.contact)
                .setMaxLength(8)
                .setDialogEventListener(new TextInputDialog.DialogEventListener() {

                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm(String text) {
                        mBinding.contact.setRightText(text);
                    }
                });
    }

    private void showUpdateEmailDialog() {
        TextInputDialog inputTextDialog = new TextInputDialog(mContext);
        inputTextDialog.show();
        inputTextDialog.setDialogTitle(R.string.email)
                .setMaxLength(50)
                .setDialogEventListener(new TextInputDialog.DialogEventListener() {

                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm(String text) {
                        mBinding.email.setRightText(text);
                    }
                });
    }

    private void showUpdateBankAccountDialog() {
        TextInputDialog inputTextDialog = new TextInputDialog(mContext);
        inputTextDialog.show();
        inputTextDialog.setDialogTitle(R.string.bank_account)
                .setMaxLength(50)
                .setDialogEventListener(new TextInputDialog.DialogEventListener() {

                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm(String text) {
                        mBinding.bankAccount.setRightText(text);
                    }
                });
    }

    private void showUpdateEnterpriseDialog() {
        List<RadioSelectDialog.Item> items = new ArrayList<>();
        items.add(new RadioSelectDialog.Item(0, true, "NUS PTE. LTD."));
        items.add(new RadioSelectDialog.Item(1, false, "NTU PTE. LTD."));
        items.add(new RadioSelectDialog.Item(2, false, "SMU PTE. LTD."));
        items.add(new RadioSelectDialog.Item(3, false, "SIT PTE. LTD."));
        items.add(new RadioSelectDialog.Item(4, false, "SUTD PTE. LTD."));

        RadioSelectDialog radioSelectDialog =
                new RadioSelectDialog(mContext).setDialogTitle(R.string
                        .enterprise).setItemList(items)
                        .setSelectCallback(new RadioSelectDialog.SelectCallback() {

                            @Override
                            public void cancel() {

                            }

                            @Override
                            public void select(RadioSelectDialog.Item item) {

                                mBinding.enterprise.setRightText(item.description);
                            }
                        });
        radioSelectDialog.show();
    }

    private void showUpdateStaffNumberDialog() {
        TextInputDialog inputTextDialog = new TextInputDialog(mContext);
        inputTextDialog.show();
        inputTextDialog.setDialogTitle(R.string.staff_number)
                .setMaxLength(10)
                .setDialogEventListener(new TextInputDialog.DialogEventListener() {

                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm(String text) {
                        mBinding.staffNumber.setRightText(text);
                    }
                });
    }
}
