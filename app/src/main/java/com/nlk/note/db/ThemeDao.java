package com.nlk.note.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

import io.reactivex.Single;

@Dao
public interface ThemeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ThemeCode... codes);

    @Query("delete from ThemeCode")
    int deleteAllTheme();

    @Query("select count(*) from ThemeCode")
    int getThemeCount();

    @Query("SELECT * FROM ThemeCode WHERE Type == :type")
    Single<List<ThemeCode>> getThemeCode(int type);

    @Query("SELECT * FROM ThemeCode order by id asc")
    LiveData<List<ThemeCode>> getThemes();
//
//    @Query("select Name from DictCode WHERE Type == :type and Code==:code")
//    String getDictName(String type, String code);
//
//    @Query("SELECT * FROM DictCode WHERE Code LIKE :code || '%' AND Type == :type")
//    Single<List<DictCode>> getSchoolCode(String type, String code);

}