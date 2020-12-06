package com.nlk.note.ui.model.changeTheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;

import com.nlk.note.R;
import com.nlk.note.databinding.ActivityChangeThemeBinding;
import com.nlk.note.db.ThemeCode;

public class ChangeThemeActivity extends AppCompatActivity {
    private ChangeThemeViewModel changeThemeViewModel;
    private ActivityChangeThemeBinding AChangeThemeBin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AChangeThemeBin = DataBindingUtil.setContentView(this, R.layout.activity_change_theme);
        changeThemeViewModel = new ChangeThemeViewModel(getApplication());
    }

    public class ClickProxy {

    }

    private void insertData(){
        ThemeCode themeCode1 = new ThemeCode(1,R.drawable.theme_color_419991,"半夏");
        ThemeCode themeCode2 = new ThemeCode(2, Color.parseColor("#00BFFF"),"立春");
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
//        changeThemeViewModel.getThemes()
//                .observe(getLifecycle(),themeCodes -> {
//                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//                    AChangeThemeBin.recycler.setLayoutManager(layoutManager);
//                    List<ThemeCode> list =  changeThemeViewModel.getThemes().getValue();
//                    com.nlk.note.ui.page.adapter.ThemeAdapter themeAdapter = new ThemeAdapter(list);
//                    AChangeThemeBin.recycler.setAdapter(themeAdapter);
//                });

    }

}