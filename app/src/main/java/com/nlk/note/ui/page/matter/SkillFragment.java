package com.nlk.note.ui.page.matter;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nlk.note.NoteContext;
import com.nlk.note.R;
import com.nlk.note.data.bean.SkillBean;
import com.nlk.note.databinding.FragmentScheduleBinding;
import com.nlk.note.databinding.FragmentSkillBinding;
import com.nlk.note.db.WorkCode;
import com.nlk.note.service.ForegroundService;
import com.nlk.note.ui.model.changeTheme.WorkAdapter;
import com.nlk.note.ui.state.ScheduleViewModel;
import com.nlk.note.ui.state.ShareViewModel;
import com.nlk.note.ui.state.SkillViewModel;
import com.nlk.note.utils.ParticleView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class SkillFragment extends Fragment {
    private FragmentSkillBinding fSkillBin;
    private SkillViewModel skillViewModel;
    private ShareViewModel shareViewModel;
    private Intent mForegroundService = new Intent(NoteContext.getInstance(), ForegroundService.class);
    private List<SkillBean> skillBeanList = new ArrayList<>();
    private SkillAdapter skillAdapter;

    private final int DELETE = 3;//删除数据
    private final int SHOWNOTIFICATION = 4;//显示通知栏
    private final int HIDENOTIFICATION = 5;//隐藏通知栏

    private int taskId = -1;//当前正在进行的任务id

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skillViewModel = new ViewModelProvider(this).get(SkillViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fSkillBin =  DataBindingUtil.inflate(inflater, R.layout.fragment_skill,container,false);
        fSkillBin.setClick(new SkillFragment.ClickProxy());
        return fSkillBin.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        fSkillBin.recycler.setLayoutManager(layoutManager);
        skillAdapter = new SkillAdapter(skillBeanList,serviceHandler);
        fSkillBin.recycler.setAdapter(skillAdapter);


        skillViewModel.getSkillWorks().observe(getViewLifecycleOwner(), new Observer<List<WorkCode>>() {
            @Override
            public void onChanged(List<WorkCode> workCodes) {
                Log.d("mytest",workCodes.size()+"--");
                skillBeanList.clear();
                for (int i=0;i<workCodes.size();i++){
                    SkillBean skillBean = new SkillBean();

                    skillBean.setTitle(workCodes.get(i).getTitle());
                    skillBean.setContent(workCodes.get(i).getContent());
                    skillBean.setDelete(false);
                    skillBean.setSkillTime(String.valueOf(Math.floor(workCodes.get(i).getSkillTime()/60)));
                    skillBean.setStartTime(getStartTime(workCodes.get(i).getTime()));
                    skillBean.setLevel(getLevel(workCodes.get(i).getSkillTime()));
                    skillBean.setId(workCodes.get(i).getId());
                    skillBeanList.add(skillBean);
                    Log.d("mytest",skillBean.getSkillTime()+"--");
                }
                skillAdapter.notifyDataSetChanged();

            }
        });

    }

    public class ClickProxy {

    }

    public final Handler serviceHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOWNOTIFICATION:
                    //判断当前是否有任务正在进行，关闭上一个任务的计时，并将任务名称进行变更
                    if (taskId != -1 && ForegroundService.serviceIsLive){
                        notifyNotificationService(msg.arg1,msg.obj.toString());
                    }else {
                        taskId = msg.arg1;
                        startNotificationService(msg.obj.toString());
                    }
                    break;
                case HIDENOTIFICATION:
                    taskId = msg.arg1;
                    stopNotificationService();
//                    if (msg.arg1 == taskId) {
//                        stopNotificationService();
//                    }
                    break;
                case DELETE:
                    final ParticleView particleAnimator = new ParticleView(getActivity(),3000);
                    particleAnimator.setOnAnimationListener(new ParticleView.OnAnimationListener() {
                        @Override
                        public void onAnimationStart(View view, Animator animation) {
                            view.setVisibility(View.INVISIBLE);
                        }
                        @Override
                        public void onAnimationEnd(View view,Animator animation) {
                            skillAdapter.notifyItemRemoved(msg.arg1);
                            // TODO: 2021/4/5 从数据库删除数据
                        }
                    });
                    particleAnimator.boom((View) msg.obj);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + msg.what);
            }
        }
    };

    public void startNotificationService(String title){
        //启动服务
        if (!ForegroundService.serviceIsLive) {
            mForegroundService.setAction(title);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NoteContext.getInstance().startForegroundService(mForegroundService);
            } else {
                NoteContext.getInstance().startService(mForegroundService);
            }
        } else {
            Toast.makeText(getActivity(), "前台服务正在运行中...", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopNotificationService(){
//        int duration = ForegroundService.getDuration();
        //更新到数据库
        Log.d("mytest",taskId+"");
        skillViewModel.updateSkillTime((long)10,taskId);
//        taskId = -1;
//        //停止服务
//        NoteContext.getInstance().stopService(mForegroundService);
    }

    private void notifyNotificationService(int nowId,String name){
        int duration = ForegroundService.getDuration();
        //更新到数据库
        skillViewModel.updateSkillTime((long)duration,taskId);
        //变更任务名称
        taskId = nowId;
        Intent intent = new Intent("changeTask");
        intent.putExtra("name",name);
        requireActivity().sendBroadcast(intent);
    }

    private String getStartTime(long time){
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(date);
    }

    private String getLevel(long time){
        String s = "";
        long hour = time/3600000;
        int hours = (int) Math.floor(hour);
        if (hours<10){
            s = "初窥门径";
        }else if(hours<100){
            s = "bbb";
        }else if(hours<1000){
            s = "ccc";
        }else {
            s = "ddd";
        }
        return s;
    }

}
