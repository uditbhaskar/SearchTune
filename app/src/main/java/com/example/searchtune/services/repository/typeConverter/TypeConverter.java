package com.example.searchtune.services.repository.typeConverter;

import com.example.searchtune.services.model.Results;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TypeConverter {
    Gson gson = new Gson();

    @androidx.room.TypeConverter
    public String getResultToString(List<Results> resultsList) {
        String resultString;
        resultString = gson.toJson(resultsList);
        return resultString;
    }

    @androidx.room.TypeConverter
    public List<Results> getStringToResult(String resultString){
        Type type = new TypeToken<List<Results>>(){}.getType();
        List<Results> resultsList;
        resultsList = gson.fromJson( resultString, type);
        return resultsList;
    }
}
