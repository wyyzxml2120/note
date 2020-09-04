package com.nlk.note.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface WorkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WorkCode... codes);

    @Query("delete from WorkCode")
    int deleteAllWork();

    @Query("select count(*) from WorkCode")
    int getWorkCount();

//    @Query("SELECT * FROM WorkCode WHERE Type == :type")
//    Single<List<WorkCode>> getWorkCode(int type);

    @Query("SELECT * FROM WorkCode order by id asc")
    LiveData<List<WorkCode>> getWorks();
//
//    @Query("select Name from DictCode WHERE Type == :type and Code==:code")
//    String getDictName(String type, String code);
//
//    @Query("SELECT * FROM DictCode WHERE Code LIKE :code || '%' AND Type == :type")
//    Single<List<DictCode>> getSchoolCode(String type, String code);

}