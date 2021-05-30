package com.nlk.note.ui.state;

import androidx.lifecycle.LiveData;

import com.nlk.baseframe.ui.page.BaseViewModel;
import com.nlk.note.NoteContext;
import com.nlk.note.data.NoteRepository;
import com.nlk.note.db.WorkCode;

import java.util.List;

public class ScheduleViewModel extends BaseViewModel {
    private LiveData<List<WorkCode>> scheduleWorks;
    public ScheduleViewModel() {
        NoteRepository noteRepository = new NoteRepository(NoteContext.getInstance());
        scheduleWorks = noteRepository.getScheduleWorks();
    }

    public LiveData<List<WorkCode>> getScheduleWorks() {
        return scheduleWorks;
    }
}
