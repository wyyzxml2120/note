package com.nlk.note.ui.page;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nlk.baseframe.ui.page.BaseFragment;
import com.nlk.note.R;
import com.nlk.note.data.bean.ThemeBean;
import com.nlk.note.databinding.FragmentChangeThemeBinding;
import com.nlk.note.db.ThemeCode;
import com.nlk.note.ui.page.adapter.ThemeAdapter;
import com.nlk.note.ui.state.ChangeThemeViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChangeThemeFragment extends BaseFragment {

    private List<ThemeBean> list = new ArrayList<>();

    private ChangeThemeViewModel changeThemeViewModel;

    private FragmentChangeThemeBinding fChangeThemeBin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeThemeViewModel = getFragmentViewModel(ChangeThemeViewModel.class);
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
        //recyclerView = view.findViewById(R.id.recycler);
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
        ThemeBean themeBean = new ThemeBean();
        themeBean.setType(1);
        themeBean.setTip("半夏");
        themeBean.setPath(R.drawable.theme_color_419991);
        list.add(themeBean);

        ThemeBean themeBean1 = new ThemeBean();
        themeBean1.setType(1);
        themeBean1.setTip("立春");
        themeBean1.setPath(R.drawable.theme_color_419991);
        //themeBean1.setPath("#FFB6C1");
        list.add(themeBean1);

        ThemeBean themeBean2 = new ThemeBean();
        themeBean2.setType(1);
        themeBean2.setTip("三伏");
        themeBean2.setPath(R.drawable.theme_color_419991);
        //themeBean2.setPath("#FF00FF");
        list.add(themeBean2);

        ThemeBean themeBean3 = new ThemeBean();
        themeBean3.setType(1);
        themeBean3.setTip("谷雨");
        themeBean3.setPath(R.drawable.theme_color_419991);
        //themeBean3.setPath("#9400D3");
        list.add(themeBean3);

        ThemeBean themeBean4 = new ThemeBean();
        themeBean4.setType(1);
        themeBean4.setTip("惊蛰");
        themeBean4.setPath(Color.parseColor("#00BFFF"));
        //themeBean4.setPath("#00BFFF");
        list.add(themeBean4);



        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        fChangeThemeBin.recycler.setLayoutManager(layoutManager);
        ThemeAdapter themeAdapter = new ThemeAdapter(changeThemeViewModel.getThemes().getValue());
        fChangeThemeBin.recycler.setAdapter(themeAdapter);

    }

}
