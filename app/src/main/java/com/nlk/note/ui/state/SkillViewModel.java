package com.nlk.note.ui.state;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nlk.baseframe.ui.page.BaseViewModel;
import com.nlk.note.NoteContext;
import com.nlk.note.data.NoteRepository;
import com.nlk.note.db.WorkCode;

import java.util.List;

public class SkillViewModel extends BaseViewModel {
    public LiveData<List<WorkCode>> skillWorks;
    private NoteRepository noteRepository;

    public SkillViewModel() {
        noteRepository = new NoteRepository(NoteContext.getInstance());
        skillWorks = noteRepository.getSkillWorks();
    }

    public LiveData<List<WorkCode>> getSkillWorks() {
        return skillWorks;
    }

    //更改技能的时间
    public void updateSkillTime(long time, int id){
        noteRepository.updateSkillTime(time,id);
    }
}
