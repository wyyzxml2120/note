package com.nlk.note.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.nlk.note.db.DBHelper;
import com.nlk.note.db.ThemeCode;
import com.nlk.note.db.ThemeDao;
import com.nlk.note.db.WorkCode;
import com.nlk.note.db.WorkDao;


import java.util.List;

public class NoteRepository {
    private ThemeDao themeDao;
    private WorkDao workDao;

    public NoteRepository(Application application) {
        DBHelper dbTheme = DBHelper.getDatabase(application);
        themeDao = dbTheme.ThemeDao();
        workDao = dbTheme.WorkDao();
    }

    //主题相关
    public LiveData<List<ThemeCode>> getThemes(){
        return themeDao.getThemes();
    }

    public void insertTheme(ThemeCode...theme) {themeDao.insert(theme); }


    //事项相关
    public LiveData<List<WorkCode>> getWorks(){
        return workDao.getWorks();
    }

    public void insertWork(WorkCode...work) {workDao.insert(work); }
}
