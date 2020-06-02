package com.example.codeexp.ui.fragment.enterprise.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.R;

import me.yokeyword.fragmentation.SupportFragment;

public class EnterpriseMineFragment extends SupportFragment {

    public static EnterpriseMineFragment newInstance(){
        Bundle args = new Bundle();
        EnterpriseMineFragment fragment = new EnterpriseMineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enterprise_mine, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
}
