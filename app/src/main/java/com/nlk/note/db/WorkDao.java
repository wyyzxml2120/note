package com.nlk.note.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("update WorkCode set skillTime =:value + skillTime where Id =:id")
    void updateSkillTime(long value, int id);

    @Query("SELECT * FROM WorkCode order by id asc")
    LiveData<List<WorkCode>> getWorks();

    @Query("select * from WorkCode WHERE type == 1 order by scheduleTime asc" )
    LiveData<List<WorkCode>> getScheduleWorks();

    @Query("select * from WorkCode WHERE type == 2" )
    LiveData<List<WorkCode>> getIdeaWorks();

    @Query("select * from WorkCode WHERE type == 3" )
    LiveData<List<WorkCode>> getSkillWorks();

//
//    @Query("SELECT * FROM DictCode WHERE Code LIKE :code || '%' AND Type == :type")
//    Single<List<DictCode>> getSchoolCode(String type, String code);

}