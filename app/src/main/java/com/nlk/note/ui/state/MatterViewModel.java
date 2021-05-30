package com.nlk.note.ui.state;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;

import com.nlk.baseframe.ui.page.BaseViewModel;
import com.nlk.jyweather.JYWeather;
import com.nlk.note.NoteContext;
import com.nlk.note.data.NoteRepository;
import com.nlk.note.db.WorkCode;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MatterViewModel extends BaseViewModel {

    public final ObservableField<String> nowDate;//日期
    public final ObservableField<String> nowWeather;//天气

    public MatterViewModel() {

        //获取当前日期
        nowDate = new ObservableField<>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        switch (mWay) {
            case "1":
                mWay = "周日";
                break;
            case "2":
                mWay = "周一";
                break;
            case "3":
                mWay = "周二";
                break;
            case "4":
                mWay = "周三";
                break;
            case "5":
                mWay = "周四";
                break;
            case "6":
                mWay = "周五";
                break;
            case "7":
                mWay = "周六";
                break;
        }
        nowDate.set(String.valueOf(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + "  " + mWay);

        //天气实例化
        nowWeather = new ObservableField<>();
        nowWeather.set("晴");
    }

}