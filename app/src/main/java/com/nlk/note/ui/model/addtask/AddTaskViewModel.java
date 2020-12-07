package com.nlk.note.ui.model.addtask;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nlk.baseframe.ui.page.BaseViewModel;
import com.nlk.note.data.NoteRepository;
import com.nlk.note.data.bean.AddTaskBean;
import com.nlk.note.data.bean.ViewMatterBean;
import com.nlk.note.db.ThemeCode;

import java.util.List;

public class AddTaskViewModel extends BaseViewModel {
    public MutableLiveData<AddTaskBean> ldAddTask = new MutableLiveData<>();

    public final ObservableInt moduleIsShow = new ObservableInt();//1日程模块 2想法模块 3技能模块

    public final ObservableField<String> skillTitle = new ObservableField<>();//技能标题
    public final ObservableField<String> skillContent = new ObservableField<>();//技能内容

    public final ObservableField<String> ideaContent = new ObservableField<>();//想法内容

    public final ObservableField<String> scheduleContent = new ObservableField<>();//日程内容
    public final ObservableInt scheduleTime = new ObservableInt();//1 2 3 4代表四种日期

    public AddTaskViewModel(@NonNull Application application) {
        AddTaskBean addTaskBean = new AddTaskBean();
        addTaskBean.setModuleIsShow(1);
        addTaskBean.setScheduleTime(1);
        addTaskBean.setIdeaContent("");
        addTaskBean.setScheduleContent("");
        addTaskBean.setSkillContent("");
        addTaskBean.setSkillTitle("");

        ldAddTask.postValue(addTaskBean);
    }

    public LiveData<AddTaskBean> getAddTask(){return ldAddTask;}

}