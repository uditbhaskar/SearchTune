package com.example.searchtune.services.model;

import androidx.annotation.NonNull;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.example.searchtune.services.repository.typeConverter.TypeConverter;


@Entity(tableName = "root")

public class Root {

    private int resultCount;

    @Ignore
    public Root() {

    }

    public Root(List<Results> results, String query) {
        this.results = results;
        this.query = query;
    }

    @TypeConverters(TypeConverter.class)
    private List<Results> results;

    @PrimaryKey
    @NonNull
    private String query;


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public int getResultCount() {
        return this.resultCount;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public List<Results> getResults() {
        return this.results;
    }

}
