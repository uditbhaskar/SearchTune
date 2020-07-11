package com.example.searchtune.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.searchtune.services.model.Root;

import com.example.searchtune.services.repository.apiRepository.APIRepository;
import com.example.searchtune.services.repository.databaseRepository.DatabaseRepository;


public class TunesInfoViewModel extends ViewModel {
    public LiveData<Root> songsInfo;
    public LiveData<Root> songsInfoFromDatabase;

    private APIRepository apiRepositoryInstance;
    private DatabaseRepository databaseRepositoryInstance;


    public TunesInfoViewModel() {
        apiRepositoryInstance = new APIRepository();
        databaseRepositoryInstance = new DatabaseRepository();
    }


    public void setTunesInfo(String searchQuery, Context context) {
        songsInfo = apiRepositoryInstance.loadTunesInfo(searchQuery, context);
    }

    public void setDatabaseQuery(String databaseQuery, Context context) {
        songsInfoFromDatabase = databaseRepositoryInstance.loadFromDatabase(context, databaseQuery.trim().toLowerCase());
    }


}
