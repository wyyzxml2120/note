package com.nlk.note.ui.model.addtask;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nlk.baseframe.ui.page.BaseViewModel;
import com.nlk.note.NoteContext;
import com.nlk.note.data.NoteRepository;
import com.nlk.note.data.bean.AddTaskBean;
import com.nlk.note.db.DBHelper;
import com.nlk.note.db.WorkCode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTaskViewModel extends BaseViewModel {
    public ObservableInt moduleType;//1日程模块 2想法模块 3技能模块

    public final ObservableField<String> skillTitle;//技能标题
    public final ObservableField<String> skillContent;//技能内容
    public final ObservableField<String> skillTime;//技能设置时间

    public final ObservableField<String> ideaContent;//想法内容

    public final ObservableField<String> scheduleContent;//日程内容
    public final ObservableInt scheduleTime;//1 2 3 4代表四种日期

    private NoteRepository noteRepository;

    public AddTaskViewModel(){
        moduleType = new ObservableInt(1);
        skillTitle = new ObservableField<>();
        skillContent = new ObservableField<>();
        skillTime = new ObservableField<>();
        ideaContent = new ObservableField<>();
        scheduleContent = new ObservableField<>();
        scheduleTime = new ObservableInt(1);

        noteRepository = new NoteRepository(NoteContext.getInstance());
    }

    //添加任务
    public void addTask(WorkCode workCode){
        noteRepository.insertWork(workCode);
    }

}