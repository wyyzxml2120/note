package com.nlk.note.ui.page;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;

import com.nlk.baseframe.ui.page.BaseFragment;
import com.nlk.jyweather.JYLocation;
import com.nlk.jyweather.JYWeather;
import com.nlk.note.R;
import com.nlk.note.databinding.FragmentMatterBinding;
import com.nlk.note.databinding.FragmentViewBinding;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static android.content.Context.NOTIFICATION_SERVICE;

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

    }

    public boolean isSameDay(long millis1, long millis2, TimeZone timeZone) {
        long interval = millis1 - millis2;
        return interval < 86400000 && interval > -86400000 && millis2Days(millis1, timeZone) == millis2Days(millis2, timeZone);
    }

    private long millis2Days(long millis, TimeZone timeZone) {
        return (((long) timeZone.getOffset(millis)) + millis) / 86400000;
    }

    public class ClickProxy {
        public void test(){

        }


    }
}