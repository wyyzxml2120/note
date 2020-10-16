package com.nlk.note.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {ThemeCode.class,WorkCode.class}, version = 1, exportSchema = false)
public abstract class DBHelper extends RoomDatabase {
    private static volatile DBHelper INSTANCE;

    public static DBHelper getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (DBHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DBHelper.class, "timenote")
//                            .fallbackToDestructiveMigration()   //迁移数据库方式1：清空数据库,重新创建数据库表,
//                            //.addMigrations(MIGRATION_1_2)     //迁移数据库方式2：不清空数据库,就需要实现Migration
//                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {
    }

    public abstract ThemeDao ThemeDao();

    public abstract WorkDao WorkDao();


    //迁移补丁
//    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            //增加一个表字段
//            //database.execSQL("ALTER TABLE department ADD COLUMN phone_num TEXT");
//        }
//    };
}
