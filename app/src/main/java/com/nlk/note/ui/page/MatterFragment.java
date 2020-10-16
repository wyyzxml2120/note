package com.nlk.note.ui.page;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nlk.baseframe.ui.page.BaseFragment;
import com.nlk.jyweather.JYLocation;
import com.nlk.jyweather.JYWeather;
import com.nlk.note.MainActivity;
import com.nlk.note.R;
import com.nlk.note.data.EbsViewData;
import com.nlk.note.databinding.FragmentMatterBinding;
import com.nlk.note.databinding.FragmentViewBinding;
import com.nlk.note.db.ThemeCode;
import com.nlk.note.db.WorkCode;
import com.nlk.note.ui.page.adapter.ThemeAdapter;
import com.nlk.note.ui.page.adapter.WorkAdapter;
import com.nlk.note.ui.state.ChangeThemeViewModel;
import com.nlk.note.ui.state.MatterViewModel;

import org.greenrobot.eventbus.EventBus;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MatterFragment extends BaseFragment {

    private FragmentMatterBinding fMatterBin;
    private MatterViewModel matterViewModel;
    private PopupWindow popupWindow;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matterViewModel = new MatterViewModel(requireActivity().getApplication());
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
        //EventBus.getDefault().register(this);

//        JYWeather myWeather = new JYWeather(this.getActivity(),"b9b5a812047544d7be3b2c24f65e23b7");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String weather = myWeather.getWeather();
//                Log.d("weather",weather);
//            }
//        }).start();


        //获取当前日期
        long a = System.currentTimeMillis();
        //根据时间戳还原日期
        Date date = new Date(a);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String aa = format.format(date);
        Log.d("aaa",a+"---"+aa);

        boolean is = isSameDay(a,a+25200000,TimeZone.getDefault());
        if (is){
            Log.d("aaa","aaa");
        }else {
            Log.d("aaa","bbb");
        }


        init();


        matterViewModel.getViewMatter().observe(getViewLifecycleOwner(), viewMatterBean -> {
            matterViewModel.addContent.set(viewMatterBean.getAddContent());
            matterViewModel.addColumnShow.set(viewMatterBean.isAddColumnShow());
            matterViewModel.addIconShow.set(viewMatterBean.isAddIconShow());
            matterViewModel.addType.set(viewMatterBean.getAddType());
        });


    }

    public boolean isSameDay(long millis1, long millis2, TimeZone timeZone) {
        long interval = millis1 - millis2;
        return interval < 86400000 && interval > -86400000 && millis2Days(millis1, timeZone) == millis2Days(millis2, timeZone);
    }

    private long millis2Days(long millis, TimeZone timeZone) {
        return (((long) timeZone.getOffset(millis)) + millis) / 86400000;
    }

    public class ClickProxy {
        public void clickAddIcon(){
            matterViewModel.addIconShow.set(false);
            matterViewModel.addColumnShow.set(true);
        }
//        public void clickAddIcon(){
//            matterViewModel.addIconShow.set(false);
//
//            //弹起popupwindow
//            if (popupWindow != null && popupWindow.isShowing()) {
//                return;
//            }
//            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View vPopupWindow = inflater.inflate(R.layout.popup_addmatter, null, false);//引入弹窗布局
//            popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
//            //设置背景透明
//            // 设置背景颜色变暗
//            WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//            lp.alpha = 0.7f;//调节透明度
//            getActivity().getWindow().setAttributes(lp);
//            //dismiss时恢复原样
//            popupWindow.setOnDismissListener(() -> {
//                WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
//                lp1.alpha = 1f;
//                getActivity().getWindow().setAttributes(lp1);
//            });
//
//            popupWindow.setFocusable(true);
//            popupWindow.setTouchable(true);
//            popupWindow.setOutsideTouchable(false);
//
//            //兼容android6.0以下点击外部区域不消失的问题
//
//            //设置进出动画
//            popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
//
//            //引入依附的布局
//            View parentView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
//            //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
//            popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
//        }

        public void addType(int type){
            matterViewModel.addType.set(type);
        }

        public void addData(){
            if (matterViewModel.addType.get() == 0 || TextUtils.isEmpty(matterViewModel.addContent.get())){
                Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
            }else {
                matterViewModel.addColumnShow.set(false);
                matterViewModel.addIconShow.set(true);
                //添加数据
                int typeIcon = R.drawable.action_no;
                switch (matterViewModel.addType.get()){
                    case 1:
                        typeIcon = R.drawable.action_happy;
                        break;
                    case 2:
                        typeIcon = R.drawable.action_learn;
                        break;
                    case 3:
                        typeIcon = R.drawable.action_life;
                        break;
                    case 4:
                        typeIcon = R.drawable.action_sport;
                        break;
                    case 5:
                        typeIcon = R.drawable.action_work;
                        break;
                }
                WorkCode workCode = new WorkCode(matterViewModel.addType.get(),typeIcon,matterViewModel.addContent.get(),System.currentTimeMillis(),0,false);
                new Thread() {
                    @Override
                    public void run() {
                        matterViewModel.insertWork(workCode);
                    }
                }.start();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //EventBus.getDefault().unregister(this);
    }

    private void init(){
        //加载待做列表
        matterViewModel.getWorks()
                .observe(getViewLifecycleOwner(),workCodes -> {
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                    fMatterBin.recycler.setLayoutManager(layoutManager);
                    List<WorkCode> list =  matterViewModel.getWorks().getValue();
                    WorkAdapter workAdapter = new WorkAdapter(list);
                    fMatterBin.recycler.setAdapter(workAdapter);
                });

        //加载时间

        //加载天气

    }


}