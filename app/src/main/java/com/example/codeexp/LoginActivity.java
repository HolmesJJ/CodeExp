package com.example.codeexp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codeexp.config.Config;
import com.example.codeexp.constants.SpUtilValueConstants;
import com.example.codeexp.listener.OnMultiClickListener;

public class LoginActivity extends AppCompatActivity {

    private ProgressBar pbLogin;
    private RadioGroup rgType;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        checkType();
        setListener();
    }

    private void initView() {
        pbLogin = (ProgressBar) findViewById(R.id.pb_login);
        rgType = (RadioGroup) findViewById(R.id.rg_type);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    private void checkType() {
        switch (Config.sLoginMode) {
            case SpUtilValueConstants.LOGIN_MODE_ENTERPRISE:
                rgType.check(R.id.rb_enterprise);
                break;
            case SpUtilValueConstants.LOGIN_MODE_INDIVIDUAL:
                rgType.check(R.id.rb_individual);
                break;
            default:
                break;
        }
    }

    private void setListener() {
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        btnLogin.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startMainActivity();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
