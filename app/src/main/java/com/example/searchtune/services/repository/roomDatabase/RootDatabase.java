package com.example.searchtune.services.repository.roomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.searchtune.services.model.Root;

@Database(entities = Root.class,exportSchema = false,version = 1)

public abstract class RootDatabase extends RoomDatabase {
    private static final String DB_NAME = "root_db";
    private static RootDatabase instance;

    public static synchronized RootDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), RootDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract TuneRootDao tuneRootDao();
}
