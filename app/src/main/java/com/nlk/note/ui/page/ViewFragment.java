package com.nlk.note.ui.page;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.nlk.baseframe.ui.page.BaseFragment;
import com.nlk.note.R;
import com.nlk.note.databinding.FragmentMineBinding;
import com.nlk.note.databinding.FragmentViewBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewFragment extends BaseFragment {
    private FragmentViewBinding fViewBin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fViewBin =  DataBindingUtil.inflate(inflater, R.layout.fragment_view,container,false);
        fViewBin.setClick(new ViewFragment.ClickProxy());
        return fViewBin.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
    }

    public class ClickProxy {

    }


    private void initData(){
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(20,""));
        strings.add(new PieEntry(40,""));
        strings.add(new PieEntry(10,""));
        strings.add(new PieEntry(30,""));

        PieDataSet dataSet = new PieDataSet(strings,"bb");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#419991"));
        colors.add(Color.parseColor("#3FBFFE"));
        colors.add(Color.parseColor("#EF6C00"));
        colors.add(Color.parseColor("#FFE793"));
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(false);

        Description description = new Description();
        description.setText("");
        fViewBin.picChart.setDescription(description);

        fViewBin.picChart.getDescription().setEnabled(false);
        fViewBin.picChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        fViewBin.picChart.getLegend().setEnabled(false);

        fViewBin.picChart.setTransparentCircleRadius(00f);
        fViewBin.picChart.setHoleRadius(70f);

        fViewBin.picChart.setCenterText("学习:240min");

        fViewBin.picChart.setClickable(false);

        fViewBin.picChart.setData(pieData);
        fViewBin.picChart.invalidate();
    }

}