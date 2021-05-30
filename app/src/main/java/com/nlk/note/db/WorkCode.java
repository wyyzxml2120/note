package com.nlk.note.db;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity(indices = {@Index("id")})
public class WorkCode {
    @PrimaryKey(autoGenerate = true)
    private int id;//自增id
    private int type;//本事务的大分类 1日程 2想法 3技艺
    private String title;//标题 只有技艺有
    private String content;//都有
    private int scheduleTime;//日程选择的四种时间
    private long time;//当前时间戳
    private long timecomplete;//完成时的时间戳
    private boolean iscomplate;//是否完成
    private long skillTime;//技艺的总时间

    public WorkCode(int type,String title,String content,int scheduleTime,long time,long timecomplete,boolean iscomplate,long skillTime) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.scheduleTime = scheduleTime;
        this.time = time;
        this.timecomplete = timecomplete;
        this.iscomplate = iscomplate;
        this.skillTime = skillTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(int scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTimecomplete() {
        return timecomplete;
    }

    public void setTimecomplete(long timecomplete) {
        this.timecomplete = timecomplete;
    }

    public boolean isIscomplate() {
        return iscomplate;
    }

    public void setIscomplate(boolean iscomplate) {
        this.iscomplate = iscomplate;
    }

    public long getSkillTime() {
        return skillTime;
    }

    public void setSkillTime(long skillTime) {
        this.skillTime = skillTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
