package com.nlk.note.ui.model.changeTheme;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.nlk.baseframe.ui.page.BaseViewModel;
import com.nlk.note.data.NoteRepository;
import com.nlk.note.db.ThemeCode;

import java.util.List;

public class ChangeThemeViewModel extends BaseViewModel {
    private LiveData<List<ThemeCode>> themes;
    private NoteRepository noteRepository;

    public ChangeThemeViewModel(@NonNull Application application) {
        //super(application);
        noteRepository = new NoteRepository(application);
        themes = noteRepository.getThemes();
    }

    public LiveData<List<ThemeCode>> getThemes() {
        return themes;
    }

    public void insertTitle(ThemeCode...themeCode) {
        noteRepository.insertTheme(themeCode);
    }

}