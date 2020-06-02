package com.example.searchtune.services.repository.roomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.searchtune.services.model.Root;
import com.example.searchtune.services.repository.typeConverter.TypeConverter;

import java.util.List;

@Dao
public interface TuneRootDao {

    @TypeConverters(TypeConverter.class)

    @Query("Select * from root where `query` LIKE :searchQuery")
    Root getRoot(String searchQuery);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoot(Root root);

    @Update
    void updateRoot(Root root);

}
