package com.example.codeexp.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import com.example.codeexp.callback.IBaseActivity;
import com.example.codeexp.util.ContextUtils;

import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends
        SupportFragment implements IBaseActivity {

    protected V mBinding;
    protected VM mViewModel;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(getViewModelClazz());
        getLifecycle().addObserver(mViewModel);
        initParam();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, initContentView(inflater, container,
                savedInstanceState), container, false);
        refreshLayout();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        initViewObservable();

        initData();
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {

        getLifecycle().removeObserver(mViewModel);
        mViewModel = null;
        mBinding.unbind();
        super.onDestroy();
    }

    @Override
    public void onDetach() {

        mContext = null;
        super.onDetach();
    }

    /**
     * 初始化根布局
     *
     * @param inflater           inflater
     * @param container          父容器
     * @param savedInstanceState bundle
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                                        @Nullable Bundle savedInstanceState);

    /**
     * 刷新布局
     */
    public void refreshLayout() {

        if (mViewModel != null) {
            mBinding.setVariable(initVariableId(), mViewModel);
        }
    }

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    /**
     * 获取ViewModel的Class对象.
     *
     * @return 继承BaseViewModel的ViewModel的Class对象
     */
    public abstract Class<VM> getViewModelClazz();

    @Override
    public void initParam() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }

    public Resources getResourcesSafety() {

        return ContextUtils.getContext().getResources();
    }

    protected void finishActivity() {

        Activity activity = getActivity();
        if (null != activity) {
            activity.finish();
        }
    }

}
