package com.nlk.note.db;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity(indices = {@Index("id")})
public class WorkCode {
    @PrimaryKey(autoGenerate = true)
    private int id;//自增id
    private int typeclassa;//本事务的大分类 吃 行 学
    private int typeclassb;//本事务的小分类 学中的 学股票 学编程
    private String work;//具体要做的事务
    private Time time;//当前日期
    private boolean iscomplate;//是否完成

    public WorkCode(int typeclassa,int typeclassb,String work,Time time,boolean iscomplate) {
        this.typeclassa = typeclassa;
        this.typeclassb = typeclassb;
        this.work = work;
        this.time = time;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isIscomplate() {
        return iscomplate;
    }

    public void setIscomplate(boolean iscomplate) {
        this.iscomplate = iscomplate;
    }
}
