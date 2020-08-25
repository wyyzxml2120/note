package com.nlk.note.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.nlk.note.db.DBHelper;
import com.nlk.note.db.ThemeCode;
import com.nlk.note.db.ThemeDao;


import java.util.List;

public class NoteRepository {
    private ThemeDao themeDao;
    private LiveData<List<ThemeCode>> themes;

    public NoteRepository(Application application) {
        DBHelper dbTheme = DBHelper.getDatabase(application);
        themeDao = dbTheme.ThemeDao();
        themes = themeDao.getThemes();
    }

    public LiveData<List<ThemeCode>> getThemes(){
        return themes;
    }

    public void insertTheme(ThemeCode...theme) {themeDao.insert(theme); }
}
