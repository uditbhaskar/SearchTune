package com.example.searchtune.services.repository.databaseRepository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.searchtune.services.model.Root;
import com.example.searchtune.services.repository.roomDatabase.RootDatabase;


public class DatabaseRepository {

    public MutableLiveData<Root> rootDatabaseData = new MutableLiveData<>();

    public MutableLiveData<Root> loadFromDatabase(Context context, String searchQuery) {
        RootDatabase rootDatabaseInstance = RootDatabase.getInstance(context);
        rootDatabaseData.setValue(rootDatabaseInstance.tuneRootDao().getRoot(searchQuery));


        return rootDatabaseData;

    }
}
