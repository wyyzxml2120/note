package com.nlk.note.ui.model.addtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.nlk.note.R;
import com.nlk.note.data.bean.AddTaskBean;
import com.nlk.note.databinding.ActivityAddBinding;
import com.nlk.note.db.WorkCode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    private AddTaskViewModel addTaskViewModel;
    private ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        addTaskViewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);

        binding.setView(addTaskViewModel);
        binding.setLifecycleOwner(this);
        binding.setClick(new ClickProxy());
        init();


        //当数据发生改变时就会呼叫该函数
//        addTaskViewModel.getLdAddTask().observe(this, new Observer<AddTaskBean>() {
//            @Override
//            public void onChanged(AddTaskBean addTaskBean) {
//                Log.d("abc","abc");
//            }
//        });

    }

    //初始化值
    private void init(){
        //获取当前时间
        SimpleDateFormat formatter = new SimpleDateFormat   ("yyyy-MM-dd HH:mm", Locale.CHINA);
        Date curDate =  new Date(System.currentTimeMillis());
        addTaskViewModel.skillTime.set(formatter.format(curDate));

    }

    public class ClickProxy {
        //切换类型
        public void changeType(int type){
            addTaskViewModel.moduleType.set(type);
        }

        //切换日期
        public void changeDate(int type){
            addTaskViewModel.scheduleTime.set(type);
        }

        //完成
        public void complete(){
            Log.d("mytest","aa"+addTaskViewModel.moduleType.get());
            WorkCode workCode;
            //查看当前类型，然后校验数据
            switch (addTaskViewModel.moduleType.get()){
                case 1:
                    if (TextUtils.isEmpty(addTaskViewModel.scheduleContent.get())){
                        Toast.makeText(AddActivity.this, "当前日程为空，是否退出?", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //插入数据
                    workCode = new WorkCode(addTaskViewModel.moduleType.get(),"",addTaskViewModel.scheduleContent.get(),addTaskViewModel.scheduleTime.get(),System.currentTimeMillis(),0,false,0);
                    addTaskViewModel.addTask(workCode);
                    break;
                case 2:
                    if (TextUtils.isEmpty(addTaskViewModel.ideaContent.get())){
                        Toast.makeText(AddActivity.this, "当前想法为空，是否退出?", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    workCode = new WorkCode(addTaskViewModel.moduleType.get(),"",addTaskViewModel.ideaContent.get(),1,System.currentTimeMillis(),0,false,0);
                    addTaskViewModel.addTask(workCode);
                    break;
                case 3:
                    if (TextUtils.isEmpty(addTaskViewModel.skillTitle.get()) || TextUtils.isEmpty(addTaskViewModel.skillContent.get())){
                        Toast.makeText(AddActivity.this, "当前内容为空，是否退出?", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    workCode = new WorkCode(addTaskViewModel.moduleType.get(),addTaskViewModel.skillTitle.get(),addTaskViewModel.skillContent.get(),1,System.currentTimeMillis(),0,false,0);
                    addTaskViewModel.addTask(workCode);
                    break;
            }
        }

        //关闭
        public void close(){
            //清除数据 关闭页面
        }

    }






}