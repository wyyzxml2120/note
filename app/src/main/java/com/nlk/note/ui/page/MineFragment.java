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
import com.nlk.note.databinding.FragmentMineBinding;

public class MineFragment extends BaseFragment {

    private FragmentMineBinding fMineBin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        fMineBin =  DataBindingUtil.inflate(inflater, R.layout.fragment_mine,container,false);
        fMineBin.setClick(new ClickProxy());
        return fMineBin.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class ClickProxy {

        public void changeTheme(){
            nav().navigate(R.id.action_MineFragment_to_ChangeThemeActivity);
        }
    }
}
