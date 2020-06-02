package com.example.codeexp.constants;

import android.os.Environment;

import java.io.File;

public class Constants {

    public static final String APP_ID = "7yYUEQpa7qBN1V4iMFkKguZ8AqnazEyGRkTeHxj834gp";
    public static final String SDK_KEY = "5YvNyy6Zgh2CGp2xnBXA3nUgaj7KE7pdKzVjRT27PL87";

    public static final String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String EMPLOYEES_INFO_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.pathSeparator + "EmployeesInfo";

    public Constants() {
    }
}
