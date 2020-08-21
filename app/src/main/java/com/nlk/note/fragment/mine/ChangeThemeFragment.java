package com.nlk.note.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nlk.baseframe.ui.page.BaseFragment;
import com.nlk.note.R;
import com.nlk.note.databinding.FragmentChangeThemeBinding;
import com.nlk.note.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class ChangeThemeFragment extends BaseFragment {

    private List<ThemeBean> list = new ArrayList<>();
//    private RecyclerView recyclerView;
//    private ThemeAdapter themeAdapter;

    private FragmentChangeThemeBinding fChangeThemeBin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void init(){
        ThemeBean themeBean = new ThemeBean();
        themeBean.setType(1);
        themeBean.setPath(R.drawable.theme_color_419991);
        list.add(themeBean);

        ThemeBean themeBean1 = new ThemeBean();
        themeBean1.setType(1);
        themeBean1.setPath(R.drawable.theme_color_419991);
        //themeBean1.setPath("#FFB6C1");
        list.add(themeBean1);

        ThemeBean themeBean2 = new ThemeBean();
        themeBean2.setType(1);
        themeBean2.setPath(R.drawable.theme_color_419991);
        //themeBean2.setPath("#FF00FF");
        list.add(themeBean2);

        ThemeBean themeBean3 = new ThemeBean();
        themeBean3.setType(1);
        themeBean3.setPath(R.drawable.theme_color_419991);
        //themeBean3.setPath("#9400D3");
        list.add(themeBean3);

        ThemeBean themeBean4 = new ThemeBean();
        themeBean4.setType(1);
        themeBean4.setPath(Color.parseColor("#00BFFF"));
        //themeBean4.setPath("#00BFFF");
        list.add(themeBean4);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        fChangeThemeBin.recycler.setLayoutManager(layoutManager);
        ThemeAdapter themeAdapter = new ThemeAdapter(list);
        fChangeThemeBin.recycler.setAdapter(themeAdapter);

    }

}
