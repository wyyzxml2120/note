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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nlk.baseframe.ui.page.BaseFragment;
import com.nlk.note.R;
import com.nlk.note.databinding.FragmentMatterBinding;
import com.nlk.note.db.WorkCode;

import com.nlk.note.ui.model.changeTheme.WorkAdapter;
import com.nlk.note.ui.state.MatterViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
            //跳到具体界面
            nav().navigate(R.id.action_MatterFragment_to_AddActivity);

        }


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