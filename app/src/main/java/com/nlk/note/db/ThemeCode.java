package com.nlk.note.db;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(indices = {@Index("id")})
public class ThemeCode {
    @PrimaryKey(autoGenerate = true)
    private int id;
    //自增id
    private int type;
    //主题类型   1为图片主题  2为纯色主题
    private int path;
    //主题资源路径 若为纯色则存储色号
    private String tip;

    public ThemeCode(int type,int path,String tip) {
        this.type = type;
        this.path = path;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}