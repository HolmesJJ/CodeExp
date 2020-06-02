package com.example.codeexp;

import android.app.Application;

import com.example.codeexp.util.ContextUtils;

import me.yokeyword.fragmentation.Fragmentation;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 建议在Application里初始化
        Fragmentation.builder()
                // BUBBLE显示悬浮球 ; SHAKE: 摇一摇唤出 ;  NONE：隐藏
                .stackViewMode(Fragmentation.SHAKE)
                .debug(BuildConfig.DEBUG)
                .install();

        ContextUtils.init(this);
    }
}
