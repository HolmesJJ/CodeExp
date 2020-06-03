package com.example.codeexp.ui.activity.enterprise.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.codeexp.BR;
import com.example.codeexp.LoginActivity;
import com.example.codeexp.MainActivity;
import com.example.codeexp.R;
import com.example.codeexp.backend.authentication.AuthNotifierDelegate;
import com.example.codeexp.backend.authentication.Authenticator;
import com.example.codeexp.backend.authentication.FIRAuthenticator;
import com.example.codeexp.backend.authentication.SignUpAuthDelegate;
import com.example.codeexp.base.BaseActivity;
import com.example.codeexp.databinding.ActivityEnterpriseRegisterBinding;
import com.example.codeexp.ui.viewmodel.enterprise.register.EnterpriseRegisterViewModel;
import com.example.codeexp.ui.widget.dialog.InputDialog;
import com.example.codeexp.ui.widget.dialog.RadioSelectDialog;
import com.example.codeexp.ui.widget.dialog.TextInputDialog;
import com.example.codeexp.util.ContextUtils;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseRegisterActivity extends BaseActivity<ActivityEnterpriseRegisterBinding, EnterpriseRegisterViewModel> implements SignUpAuthDelegate, AuthNotifierDelegate {
    Authenticator auth = FIRAuthenticator.getSingleton();

    @Override
    public int initContentView(Bundle savedInstanceState) {

        return R.layout.activity_enterprise_register;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<EnterpriseRegisterViewModel> getViewModelClazz() {

        return EnterpriseRegisterViewModel.class;
    }

    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(ContextUtils.getContext().getString(R.string.enterprise_register));
        mBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher);

        mBinding.name.setLeftText(R.string.name).setBottomLineVisible(false)
                .setItemClickListener(
                        this::showUpdateNameDialog
                );
        mBinding.type.setLeftText(R.string.type).setBottomLineVisible(false)
                .setItemClickListener(
                        this::showUpdateTypeDialog
                );
        mBinding.numberOfEmployees.setLeftText(R.string.number_of_employees).setBottomLineVisible(true)
                .setItemClickListener(
                        this::showUpdateNumberOfEmployeesDialog
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

    private void showUpdateTypeDialog() {
        List<RadioSelectDialog.Item> items = new ArrayList<>();
        items.add(new RadioSelectDialog.Item(0, true, getString(R.string.retail)));
        items.add(new RadioSelectDialog.Item(1, false, getString(R.string.catering)));
        items.add(new RadioSelectDialog.Item(2, false, getString(R.string.manufacturing)));
        items.add(new RadioSelectDialog.Item(3, false, getString(R.string.service)));

        RadioSelectDialog radioSelectDialog =
                new RadioSelectDialog(mContext).setDialogTitle(R.string
                        .type).setItemList(items)
                        .setSelectCallback(new RadioSelectDialog.SelectCallback() {

                            @Override
                            public void cancel() {

                            }

                            @Override
                            public void select(RadioSelectDialog.Item item) {

                                mBinding.type.setRightText(item.description);
                            }
                        });
        radioSelectDialog.show();
    }

    private void showUpdateNumberOfEmployeesDialog() {
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

    private void startMainActivity() {
        Intent intent = new Intent(EnterpriseRegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void signUpDidSucceed() {
        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show();
        startMainActivity();
    }

    @Override
    public void signUpDidFail() {
        Toast.makeText(this, "Signup Failed, Please try again", Toast.LENGTH_LONG).show();
    }

    @Override
    public void authDidSucceedAndLoadProfile() {
        startMainActivity();
    }
}
