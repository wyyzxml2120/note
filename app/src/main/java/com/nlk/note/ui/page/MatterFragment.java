package com.nlk.note.ui.page;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.nlk.baseframe.ui.page.BaseFragment;
import com.nlk.jyweather.JYLocation;
import com.nlk.jyweather.JYWeather;
import com.nlk.note.R;
import com.nlk.note.databinding.FragmentMatterBinding;
import com.nlk.note.databinding.FragmentViewBinding;

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

        JYWeather myWeather = new JYWeather(this.getActivity(),"b9b5a812047544d7be3b2c24f65e23b7");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String weather = myWeather.getWeather();
                Log.d("weather",weather);
            }
        }).start();

    }

    public class ClickProxy {

    }
}