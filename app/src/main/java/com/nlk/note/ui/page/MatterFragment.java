package com.nlk.note.ui.page;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.nlk.baseframe.ui.page.BaseFragment;
import com.nlk.jyweather.JYWeather;
import com.nlk.note.R;
import com.nlk.note.databinding.FragmentMatterBinding;
import com.nlk.note.db.WorkCode;

import com.nlk.note.ui.model.addtask.AddTaskViewModel;
import com.nlk.note.ui.model.changeTheme.WorkAdapter;
import com.nlk.note.ui.page.matter.IdeaFragment;
import com.nlk.note.ui.page.matter.ScheduleFragment;
import com.nlk.note.ui.page.matter.SkillFragment;
import com.nlk.note.ui.state.MatterViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MatterFragment extends BaseFragment {

    private FragmentMatterBinding fMatterBin;
    private MatterViewModel matterViewModel;


    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matterViewModel = new ViewModelProvider(this).get(MatterViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fMatterBin =  DataBindingUtil.inflate(inflater, R.layout.fragment_matter,container,false);
        return fMatterBin.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fMatterBin.setClick(new ClickProxy());
        fMatterBin.setView(matterViewModel);

        init();

    }



    public class ClickProxy {
        public void clickAddIcon(){
            //跳到具体界面
            nav().navigate(R.id.action_MatterFragment_to_AddActivity);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void init(){
        //获取当前天气
//        JYWeather myWeather = new JYWeather(this.getActivity(),"b9b5a812047544d7be3b2c24f65e23b7");
//        new Thread(() -> {
//            String weather = myWeather.getWeather();
//            matterViewModel.nowWeather.set(weather);
//            Log.d("weather",weather);
//        }).start();

        fMatterBin.mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mFragments.add(new ScheduleFragment());
        mFragments.add(new IdeaFragment());
        mFragments.add(new SkillFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getParentFragmentManager(), mFragments, getTabTitle());
        fMatterBin.mViewPager.setAdapter(adapter);
        fMatterBin.mTableLayout.setupWithViewPager(fMatterBin.mViewPager);
    }

    private List<String> getTabTitle() {
        return Arrays.asList("日程", "创意", "技能");
    }


}