package com.nlk.note.ui.model.addtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingConversion;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.nlk.note.R;
import com.nlk.note.databinding.ActivityAddBinding;
import com.nlk.note.db.WorkCode;
import com.nlk.note.ui.state.MainViewModel;

public class AddActivity extends AppCompatActivity {
    private AddTaskViewModel addTaskViewModel;
    private ActivityAddBinding addBin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBin = DataBindingUtil.setContentView(this, R.layout.activity_add);
        addTaskViewModel = new AddTaskViewModel();
        addTaskViewModel.moduleType.set(1);
        addTaskViewModel.ideaContent.set("aa");
        addBin.setView(addTaskViewModel);
        Log.d("aab",addTaskViewModel.moduleType.get()+"-"+addTaskViewModel.ideaContent);
//        addBin.tvIdea.setSelected(true);
//        addBin.tvSchedule.setSelected(false);
//        addBin.tvSkill.setSelected(true);

//        addTaskViewModel.getAddTask().observe(getLifecycle(), viewMatterBean -> {
//            addTaskViewModel.moduleType.set(viewMatterBean.getModuleType());
//
//        });

    }


    public class ClickProxy {
        //切换类型
        public void changeType(){

        }

        //切换日期
        public void changeDate(){

        }

        //完成
        public void complete(){

        }

        //关闭
        public void close(){

        }


    }



}