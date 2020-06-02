package com.example.codeexp.ui.fragment.individual.checkin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.R;

import me.yokeyword.fragmentation.SupportFragment;

public class IndividualCheckInFragment extends SupportFragment {

    public static IndividualCheckInFragment newInstance(){
        Bundle args = new Bundle();
        IndividualCheckInFragment fragment = new IndividualCheckInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual_check_in, container, false);
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
