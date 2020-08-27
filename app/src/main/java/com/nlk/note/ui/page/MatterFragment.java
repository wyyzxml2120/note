package com.nlk.note.ui.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.nlk.baseframe.ui.page.BaseFragment;
import com.nlk.note.R;
import com.nlk.note.databinding.FragmentMatterBinding;
import com.nlk.note.databinding.FragmentViewBinding;

public class MatterFragment extends BaseFragment {

    private FragmentMatterBinding fMatterBin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fMatterBin =  DataBindingUtil.inflate(inflater, R.layout.fragment_matter,container,false);
        fMatterBin.setClick(new MatterFragment.ClickProxy());
        return fMatterBin.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class ClickProxy {

    }
}