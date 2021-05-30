package com.nlk.note.ui.page.matter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nlk.note.R;
import com.nlk.note.databinding.FragmentIdeaBinding;
import com.nlk.note.databinding.FragmentScheduleBinding;
import com.nlk.note.db.WorkCode;
import com.nlk.note.ui.model.changeTheme.WorkAdapter;
import com.nlk.note.ui.page.MatterFragment;
import com.nlk.note.ui.state.MatterViewModel;
import com.nlk.note.ui.state.ScheduleViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class ScheduleFragment extends Fragment {
    private FragmentScheduleBinding fScheduleBin;
    private ScheduleViewModel scheduleViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scheduleViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fScheduleBin =  DataBindingUtil.inflate(inflater, R.layout.fragment_schedule,container,false);
        fScheduleBin.setClick(new ScheduleFragment.ClickProxy());
        return fScheduleBin.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fScheduleBin.setClick(new ScheduleFragment.ClickProxy());
        fScheduleBin.setView(scheduleViewModel);

        //加载待做列表
        scheduleViewModel.getScheduleWorks()
                .observe(getViewLifecycleOwner(),workCodes -> {
                    List<WorkCode> aa = new ArrayList<>();
                    for (int i=0;i<20;i++){
                        WorkCode workCode = new WorkCode(1,"","wwww"+i,333,System.currentTimeMillis(),0,false,0);
                        aa.add(workCode);
                    }

                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                    fScheduleBin.recycler.setLayoutManager(layoutManager);
                    WorkAdapter workAdapter = new WorkAdapter(aa);
                    fScheduleBin.recycler.setAdapter(workAdapter);
                });

    }

    //是否是同一天
    public boolean isSameDay(long millis1, long millis2, TimeZone timeZone) {
        long interval = millis1 - millis2;
        return interval < 86400000 && interval > -86400000 && millis2Days(millis1, timeZone) == millis2Days(millis2, timeZone);
    }

    private long millis2Days(long millis, TimeZone timeZone) {
        return (((long) timeZone.getOffset(millis)) + millis) / 86400000;
    }

    public class ClickProxy {

    }
}
