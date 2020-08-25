package com.nlk.note.ui.state;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nlk.note.data.NoteRepository;
import com.nlk.note.db.ThemeCode;

import java.util.List;

public class ChangeThemeViewModel extends AndroidViewModel {
    private LiveData<List<ThemeCode>> themes;
    private NoteRepository noteRepository;

    public ChangeThemeViewModel(@NonNull Application application) {
        super(application);
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