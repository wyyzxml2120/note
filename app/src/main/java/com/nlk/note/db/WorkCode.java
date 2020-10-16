package com.nlk.note.db;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity(indices = {@Index("id")})
public class WorkCode {
    @PrimaryKey(autoGenerate = true)
    private int id;//自增id
    private int typeclassa;//本事务的大分类 1娱乐 2学习 3生活 4运动 5工作
    private int typeclassb;//本事务的分类对应的图标
    private String work;//具体要做的事务
    private long time;//当前时间戳
    private long timecomplete;//完成时的时间戳
    private boolean iscomplate;//是否完成

    public WorkCode(int typeclassa,int typeclassb,String work,long time,long timecomplete,boolean iscomplate) {
        this.typeclassa = typeclassa;
        this.typeclassb = typeclassb;
        this.work = work;
        this.time = time;
        this.timecomplete = timecomplete;
        this.iscomplate = iscomplate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeclassa() {
        return typeclassa;
    }

    public void setTypeclassa(int typeclassa) {
        this.typeclassa = typeclassa;
    }

    public int getTypeclassb() {
        return typeclassb;
    }

    public void setTypeclassb(int typeclassb) {
        this.typeclassb = typeclassb;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isIscomplate() {
        return iscomplate;
    }

    public void setIscomplate(boolean iscomplate) {
        this.iscomplate = iscomplate;
    }

    public long getTimecomplete() {
        return timecomplete;
    }

    public void setTimecomplete(long timecomplete) {
        this.timecomplete = timecomplete;
    }
}
