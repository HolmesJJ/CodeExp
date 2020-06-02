package com.example.codeexp.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import com.example.codeexp.R;
import com.example.codeexp.callback.IBaseActivity;
import com.example.codeexp.ui.widget.dialog.LoadingDialog;
import com.example.codeexp.util.ActivityUtils;
import com.example.codeexp.util.AppManagerUtils;
import com.example.codeexp.util.ContextUtils;
import com.example.codeexp.util.KeyboardUtils;
import com.example.codeexp.util.ToastUtils;

import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends
        SupportActivity implements IBaseActivity, EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {

    /**
     * 第二次点击退出时间
     */
    private static final long MILLISECONDS = 2000;
    protected Context mContext;
    protected V mBinding;
    protected VM mViewModel;
    protected int mLanguage;
    /**
     * 第一次点击的时间
     */
    private long mClickTime;
    private LoadingDialog mLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mContext = this;

        mLoading = new LoadingDialog(this);

        initParam();

        initViewDataBinding(savedInstanceState);

        initViewObservable();

        initData();
    }

    @Override
    protected void onDestroy() {

        mViewModel = null;
        mBinding.unbind();
        mBinding = null;
        cancelLoading();
        super.onDestroy();
    }

    private void cancelLoading() {

        if (mLoading != null) {
            mLoading.cancel();
        }
    }

    @Override
    public Resources getResources() {

        Resources resources = super.getResources();
        if (resources != null) {
            Configuration newConfig = resources.getConfiguration();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            newConfig.fontScale = 1;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Context configurationContext = createConfigurationContext(newConfig);
                resources = configurationContext.getResources();
                displayMetrics.scaledDensity = displayMetrics.density * newConfig.fontScale;
            } else {
                resources.updateConfiguration(newConfig, displayMetrics);
            }
        }
        return resources;
    }

    @Override
    public void initParam() {

    }

    /**
     * 注入绑定.
     */
    private void initViewDataBinding(Bundle savedInstanceState) {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android
        // .databinding包
        mViewModel = ViewModelProviders.of(this).get(getViewModelClazz());
        mBinding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        refreshLayout();
        getLifecycle().addObserver(mViewModel);
    }

    /**
     * 默认是否隐藏工具栏
     *
     * @return true-隐藏，false-不隐藏
     */
    protected boolean defaultHideSystemBar() {

        return true;
    }

    /**
     * 获取ViewModel的Class对象.
     *
     * @return 继承BaseViewModel的ViewModel的Class对象
     */
    public abstract Class<VM> getViewModelClazz();

    /**
     * 初始化根布局.
     *
     * @param savedInstanceState bundle
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(Bundle savedInstanceState);

    /**
     * 刷新布局.
     */
    public void refreshLayout() {

        if (mViewModel != null) {
            mBinding.setVariable(initVariableId(), mViewModel);
        }
    }

    /**
     * 初始化ViewModel的id.
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }

    public void showLoading() {

        if (mLoading != null) {
            mLoading.showLoading();
        }
    }

    public void showLoading(boolean canCancel) {

        if (mLoading != null) {
            mLoading.setCanceledOnTouchOutside(canCancel);
            mLoading.showLoading();
        }
    }

    public void stopLoading() {

        if (mLoading != null) {
            //            mLoading.dismiss();
            mLoading.dismissLoading();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (KeyboardUtils.isShouldHideInput(v, ev)) {
                KeyboardUtils.closeKeyboard(mContext);
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        // 有权限都被准许后调用的接口
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // 有权限被拒绝后调用该接口
        new AppSettingsDialog.Builder(this).setTitle(R.string.need_permissions_str)
                .setRationale(getString(R.string.permissions_denied_content_str)).build().show();
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        // 开启申请权限时会调用该方法
    }

    @Override
    public void onRationaleDenied(int requestCode) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {

            if (!onHasPermissions()) {
                exitApp();
            } else {
                onPermissionSuccessCallbackFromSetting();
            }
        }
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void backPressedExit() {

        if (Math.abs(System.currentTimeMillis() - mClickTime) > MILLISECONDS) {
            ToastUtils.showShortSafe(R.string.exit_app_str);
            mClickTime = System.currentTimeMillis();
        } else {
            exitApp();
        }
    }

    public Resources getResourcesSafety() {

        return ContextUtils.getContext().getResources();
    }

    /**
     * 判断申请的权限是否都被允许
     */
    protected boolean onHasPermissions() {

        return true;
    }

    public void exitApp() {

        ActivityUtils.appExitDelayed(2000);
        this.finish();
    }

    /**
     * 当冲设置界面回调程序中且授权成功时回调该方法
     */
    protected void onPermissionSuccessCallbackFromSetting() {

    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {

        mContext.startActivity(new Intent(mContext, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {

        Intent intent = new Intent(mContext, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }
}
