package com.nlk.note.ui.page;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nlk.baseframe.ui.page.BaseFragment;
import com.nlk.note.R;
import com.nlk.note.databinding.FragmentChangeThemeBinding;
import com.nlk.note.db.ThemeCode;
import com.nlk.note.ui.page.adapter.ThemeAdapter;
import com.nlk.note.ui.state.ChangeThemeViewModel;

import java.util.List;
import java.util.Objects;

import io.reactivex.schedulers.Schedulers;


public class ChangeThemeFragment extends BaseFragment {

    private ChangeThemeViewModel changeThemeViewModel;

    private FragmentChangeThemeBinding fChangeThemeBin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeThemeViewModel = new ChangeThemeViewModel(requireActivity().getApplication());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        fChangeThemeBin =  DataBindingUtil.inflate(inflater, R.layout.fragment_change_theme,container,false);
        return fChangeThemeBin.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    public class ClickProxy {

    }

    private void insertData(){
        ThemeCode themeCode1 = new ThemeCode(1,R.drawable.theme_color_419991,"半夏");
        ThemeCode themeCode2 = new ThemeCode(2,Color.parseColor("#00BFFF"),"立春");
        ThemeCode themeCode3 = new ThemeCode(2,Color.parseColor("#2B333E"),"三伏");
        ThemeCode themeCode4 = new ThemeCode(2,Color.parseColor("#15559A"),"谷雨");
        ThemeCode themeCode5 = new ThemeCode(2,Color.parseColor("#1BA784"),"惊蛰");
        ThemeCode themeCode6 = new ThemeCode(2,Color.parseColor("#FED71A"),"春分");

        new Thread() {
            @Override
            public void run() {
                changeThemeViewModel.insertTitle(themeCode1,themeCode2,themeCode3,themeCode4,themeCode5,themeCode6);
            }
        }.start();
    }

    private void init(){


        changeThemeViewModel.getThemes()
                .observe(getViewLifecycleOwner(),themeCodes -> {
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                    fChangeThemeBin.recycler.setLayoutManager(layoutManager);
                    List<ThemeCode> list =  changeThemeViewModel.getThemes().getValue();
                    ThemeAdapter themeAdapter = new ThemeAdapter(list);
                    fChangeThemeBin.recycler.setAdapter(themeAdapter);
                });

    }

}
