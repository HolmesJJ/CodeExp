package com.example.codeexp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.R;
import com.example.codeexp.config.Config;
import com.example.codeexp.constants.SpUtilValueConstants;
import com.example.codeexp.ui.fragment.enterprise.home.EnterpriseHomeFragment;
import com.example.codeexp.ui.fragment.enterprise.mine.EnterpriseMineFragment;
import com.example.codeexp.ui.fragment.enterprise.post.EnterprisePostFragment;
import com.example.codeexp.ui.fragment.individual.checkin.IndividualCheckInFragment;
import com.example.codeexp.ui.fragment.individual.home.IndividualHomeFragment;
import com.example.codeexp.ui.fragment.individual.mine.IndividualMineFragment;
import com.example.codeexp.ui.widget.BottomBar;
import com.example.codeexp.ui.widget.BottomBarTab;

import me.yokeyword.fragmentation.SupportFragment;

public class MainFragment extends SupportFragment {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    private SupportFragment[] mFragments = new SupportFragment[3];
    private BottomBar mBottomBar;

    private int prePrePosition;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SupportFragment firstFragment = findFragment(EnterpriseHomeFragment.class);
        if (firstFragment == null) {

            if (Config.sLoginMode == SpUtilValueConstants.LOGIN_MODE_ENTERPRISE) {

                mFragments[FIRST] = EnterpriseHomeFragment.newInstance();
                mFragments[SECOND] = EnterprisePostFragment.newInstance();
                mFragments[THIRD] = EnterpriseMineFragment.newInstance();
            } else {

                mFragments[FIRST] = IndividualHomeFragment.newInstance();
                mFragments[SECOND] = IndividualCheckInFragment.newInstance();
                mFragments[THIRD] = IndividualMineFragment.newInstance();
            }

            loadMultipleRootFragment(R.id.fl_main_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]
            );

        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;

            if (Config.sLoginMode == SpUtilValueConstants.LOGIN_MODE_ENTERPRISE) {

                mFragments[SECOND] = findFragment(EnterprisePostFragment.class);
                mFragments[THIRD] = findFragment(EnterpriseMineFragment.class);
            } else {

                mFragments[SECOND] = findFragment(IndividualCheckInFragment.class);
                mFragments[THIRD] = findFragment(IndividualMineFragment.class);
            }
        }
    }

    private void initView(View view) {
        mBottomBar = (BottomBar) view.findViewById(R.id.bottomBar);

        if (Config.sLoginMode == SpUtilValueConstants.LOGIN_MODE_ENTERPRISE) {

            mBottomBar.addItem(new BottomBarTab(_mActivity, R.drawable.ic_home, getString(R.string.home)))
                    .addItem(new BottomBarTab(_mActivity, R.drawable.ic_post, getString(R.string.post)))
                    .addItem(new BottomBarTab(_mActivity, R.drawable.ic_mine, getString(R.string.mine)));
        } else {

            mBottomBar.addItem(new BottomBarTab(_mActivity, R.drawable.ic_home, getString(R.string.home)))
                    .addItem(new BottomBarTab(_mActivity, R.drawable.ic_checkin, getString(R.string.check_in_out)))
                    .addItem(new BottomBarTab(_mActivity, R.drawable.ic_mine, getString(R.string.mine)));
        }

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position],mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }

    // 点击返回键后回到第一个fragment
    @Override
    public boolean onBackPressedSupport() {
        if(mBottomBar.getCurrentItemPosition() == 0) {
            return false;
        }
        else {
            mBottomBar.setCurrentItem(0);
            return true;
        }
    }
}
