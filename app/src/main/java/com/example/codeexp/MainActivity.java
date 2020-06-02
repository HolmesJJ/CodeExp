package com.example.codeexp;

import android.os.Bundle;

import com.example.codeexp.base.BaseActivity;
import com.example.codeexp.databinding.ActivityMainBinding;
import com.example.codeexp.ui.fragment.MainFragment;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {

        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<MainViewModel> getViewModelClazz() {

        return MainViewModel.class;
    }

    @Override
    public void initData() {

        super.initData();

        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
