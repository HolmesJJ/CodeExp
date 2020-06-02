package com.example.codeexp.config;

import com.example.codeexp.constants.SpUtilKeyConstants;
import com.example.codeexp.constants.SpUtilValueConstants;
import com.example.codeexp.util.SpUtils;

public class Config {

    public static final String SETTING_CONFIG = "SettingConfig";
    public static int sLoginMode;

    private static SpUtils sSp = SpUtils.getInstance(SETTING_CONFIG);

    public Config() {
    }

    public static void setLoginMode(int loginMode) {
        sSp.put(SpUtilKeyConstants.LOGIN_MODE, loginMode);
        sLoginMode = loginMode;
    }

    public static void resetConfig() {
        SpUtils.getInstance(SETTING_CONFIG).clear();
        loadConfig();
    }

    public static void loadConfig() {
        sLoginMode = sSp.getInt(SpUtilKeyConstants.LOGIN_MODE, SpUtilValueConstants.LOGIN_MODE_ENTERPRISE);
    }

    static {
        loadConfig();
    }
}
