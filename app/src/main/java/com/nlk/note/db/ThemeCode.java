package com.nlk.note.db;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(indices = {@Index("Type")})
public class ThemeCode {
    @PrimaryKey(autoGenerate = true)
    private int id;          //自增id
    private int Type;     //主题类型   1为图片主题  2为纯色主题
    private String Path;     //主题资源路径 若为纯色则存储色号


    public ThemeCode(){}
    public ThemeCode(int type,String path) {
        this.Type = type;
        this.Path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}