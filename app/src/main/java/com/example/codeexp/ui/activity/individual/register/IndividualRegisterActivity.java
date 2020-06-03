package com.example.codeexp.ui.activity.individual.register;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.codeexp.BR;
import com.example.codeexp.MainActivity;
import com.example.codeexp.R;
import com.example.codeexp.backend.authentication.Authenticator;
import com.example.codeexp.backend.authentication.FIRAuthenticator;
import com.example.codeexp.backend.authentication.SignUpAuthDelegate;
import com.example.codeexp.backend.model.EnterpriseProfile;
import com.example.codeexp.backend.model.IndividualProfile;
import com.example.codeexp.backend.model.ProgramState;
import com.example.codeexp.base.BaseActivity;
import com.example.codeexp.databinding.ActivityIndividualRegisterBinding;
import com.example.codeexp.listener.OnMultiClickListener;
import com.example.codeexp.ui.activity.enterprise.register.EnterpriseRegisterActivity;
import com.example.codeexp.ui.viewmodel.individual.register.IndividualRegisterViewModel;
import com.example.codeexp.ui.widget.dialog.RadioSelectDialog;
import com.example.codeexp.ui.widget.dialog.TextInputDialog;
import com.example.codeexp.util.ContextUtils;
import com.example.codeexp.util.ListenerUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IndividualRegisterActivity extends BaseActivity<ActivityIndividualRegisterBinding, IndividualRegisterViewModel> implements SignUpAuthDelegate {
    Authenticator auth = FIRAuthenticator.getSingleton();

    String name, contact, email, bankAccount, enterprise, staffNumber;

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

        auth.setSignUpAuthDelegate(this);
        ListenerUtils.setOnClickListener(mBinding.tvLogout, new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                //TODO: any additional data checks before submitting?
                name = mBinding.name.getRightText();
                contact = mBinding.contact.getRightText();
                bankAccount = mBinding.bankAccount.getRightText();
                enterprise = mBinding.enterprise.getRightText();
                staffNumber = mBinding.staffNumber.getRightText();
                email = "test@example.com"; //TODO: add email
                String password = "123456"; //TODO: add password
                auth.signUp(email, password);
            }
        });
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

    private void startMainActivity() {
        Intent intent = new Intent(IndividualRegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void signUpDidSucceed() {
        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show();
        ProgramState.getSingleton().currentProfile = new IndividualProfile(
                email, name, "", name, "Description", LocalDateTime.now(), LocalDateTime.now(), bankAccount, new EnterpriseProfile(null, enterprise, null, null, null, null, null, null, 0), staffNumber, null, false
        );
        startMainActivity();
    }

    @Override
    public void signUpDidFail() {
        Toast.makeText(this, "Signup Failed, Please try again", Toast.LENGTH_LONG).show();
    }
}
