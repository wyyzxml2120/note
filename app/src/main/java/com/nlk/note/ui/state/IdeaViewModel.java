package com.nlk.note.ui.state;

import androidx.lifecycle.LiveData;

import com.nlk.baseframe.ui.page.BaseViewModel;
import com.nlk.note.NoteContext;
import com.nlk.note.data.NoteRepository;
import com.nlk.note.db.WorkCode;

import java.util.List;

public class IdeaViewModel extends BaseViewModel {
    private LiveData<List<WorkCode>> ideaWorks;

    public IdeaViewModel() {
        NoteRepository noteRepository = new NoteRepository(NoteContext.getInstance());
        ideaWorks = noteRepository.getIdeaWorks();
    }

    public LiveData<List<WorkCode>> getIdeaWorks() {
        return ideaWorks;
    }

}
