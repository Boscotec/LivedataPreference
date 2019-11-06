package com.boscotec.livedatapreference.converters;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonConverter<T> implements Converter<T> {
  private Gson gson;
  private Type type;

  public JsonConverter(Gson gson, Type type){
    this.gson = gson;
    this.type = type;
  }

  @NonNull
  @Override
  public T deserialize(@NonNull String serialized) {
    return gson.fromJson(serialized, type);
  }

  @NonNull
  @Override
  public String serialize(@NonNull T value) {
    return gson.toJson(value);
  }
}
