package com.example.codeexp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.codeexp.base.BaseActivity;
import com.example.codeexp.config.Config;
import com.example.codeexp.constants.SpUtilValueConstants;
import com.example.codeexp.databinding.ActivityLoginBinding;
import com.example.codeexp.listener.OnMultiClickListener;
import com.example.codeexp.ui.activity.enterprise.register.EnterpriseRegisterActivity;
import com.example.codeexp.ui.activity.individual.register.IndividualRegisterActivity;
import com.example.codeexp.util.ContextUtils;
import com.example.codeexp.util.ListenerUtils;
import com.example.codeexp.util.PermissionsUtils;

import pub.devrel.easypermissions.AfterPermissionGranted;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    private final static int REC_PERMISSION = 100;
    private String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA
    };

    @Override
    public int initContentView(Bundle savedInstanceState) {

        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<LoginViewModel> getViewModelClazz() {

        return LoginViewModel.class;
    }

    @AfterPermissionGranted(REC_PERMISSION)
    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(ContextUtils.getContext().getString(R.string.login));

        PermissionsUtils.doSomeThingWithPermission(this, () -> {
            if (mViewModel != null) {
                mViewModel.initData();
            }
        }, PERMISSIONS, REC_PERMISSION, R.string.rationale_init);

        checkType();
        setListener();
    }

    private void checkType() {
        switch (Config.sLoginMode) {
            case SpUtilValueConstants.LOGIN_MODE_ENTERPRISE:
                mBinding.rgType.check(R.id.rb_enterprise);
                break;
            case SpUtilValueConstants.LOGIN_MODE_INDIVIDUAL:
                mBinding.rgType.check(R.id.rb_individual);
                break;
            default:
                break;
        }
    }

    private void setListener() {

        mBinding.rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_enterprise:
                        Config.setLoginMode(SpUtilValueConstants.LOGIN_MODE_ENTERPRISE);
                        break;
                    case R.id.rb_individual:
                        Config.setLoginMode(SpUtilValueConstants.LOGIN_MODE_INDIVIDUAL);
                        break;
                    default:
                        break;
                }
            }
        });

        ListenerUtils.setOnClickListener(mBinding.btnLogin, new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startMainActivity();
            }
        });

        ListenerUtils.setOnClickListener(mBinding.btnEnterpriseRegister, new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startEnterpriseRegisterActivity();
            }
        });

        ListenerUtils.setOnClickListener(mBinding.btnIndividualRegister, new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startIndividualRegisterActivity();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void startEnterpriseRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, EnterpriseRegisterActivity.class);
        startActivity(intent);
    }

    private void startIndividualRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, IndividualRegisterActivity.class);
        startActivity(intent);
    }
}
