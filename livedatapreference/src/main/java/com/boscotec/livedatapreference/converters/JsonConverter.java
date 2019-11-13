package com.boscotec.livedatapreference.converters;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import java.lang.reflect.Type;

public class JsonConverter<T> implements Converter<T> {
    private Gson gson;
    private Type type;
   // private Class<T> tClass;

//    public JsonConverter(Gson gson, Class<T> tClass){
//        this.gson = gson;
//        this.tClass = tClass;
//    }

    public JsonConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @NonNull
    @Override
    public T deserialize(@NonNull String serialized) {
        return gson.fromJson(serialized, type);
        //return gson.fromJson(serialized, tClass);
    }

    @NonNull
    @Override
    public String serialize(@NonNull T value) {
        return gson.toJson(value);
    }
}