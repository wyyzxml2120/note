package com.nlk.note.data;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.nlk.note.db.DBHelper;
import com.nlk.note.db.ThemeCode;
import com.nlk.note.db.ThemeDao;
import com.nlk.note.db.WorkCode;
import com.nlk.note.db.WorkDao;


import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NoteRepository {
    private ThemeDao themeDao;
    private WorkDao workDao;
    private ExecutorService executorService;

    public NoteRepository(Context context) {
        DBHelper dbTheme = DBHelper.getDatabase(context);
        themeDao = dbTheme.ThemeDao();
        workDao = dbTheme.WorkDao();

        int process = Runtime.getRuntime().availableProcessors();
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
        executorService = new ThreadPoolExecutor(process,
                process * 2,
                1, TimeUnit.SECONDS,
                taskQueue);
    }

    //主题相关
    public LiveData<List<ThemeCode>> getThemes(){
        return themeDao.getThemes();
    }

    public void insertTheme(ThemeCode...theme) {themeDao.insert(theme); }


    //事项相关
    public LiveData<List<WorkCode>> getScheduleWorks(){
        return workDao.getScheduleWorks();
    }

    public LiveData<List<WorkCode>> getIdeaWorks(){
        return workDao.getIdeaWorks();
    }

    public LiveData<List<WorkCode>> getSkillWorks(){
        return workDao.getSkillWorks();
    }

    public void insertWork(WorkCode...work) {
        executorService.execute(() ->workDao.insert(work));
    }

    public void updateSkillTime(long time, int id) {executorService.execute(() ->workDao.updateSkillTime(time,id));}
}
