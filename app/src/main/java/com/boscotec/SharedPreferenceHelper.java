package com.boscotec;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.boscotec.livedatapreference.SharedPreferenceLiveData;
import com.boscotec.livedatapreference.converters.JsonConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceHelper {

    private Gson gson;
    private SharedPreferenceLiveData sharedPreferences;

    public SharedPreferenceHelper(SharedPreferences preferences, Gson gson) {
        if (sharedPreferences == null) {
            sharedPreferences = SharedPreferenceLiveData.create(preferences);
        }
        this.gson = gson;
    }

    public void setString(String value) { sharedPreferences.getString("string_key").set(value); }

    public String getString() { return sharedPreferences.getString("string_key", "").get(); }

    public LiveData<String> getStringLiveData() { return sharedPreferences.getString("string_key", "").getAsLiveData(); }

    public void setBoolean(boolean value) { sharedPreferences.getBoolean("boolean_key").set(value); }

    public boolean isBoolean() {
        return sharedPreferences.getBoolean("boolean_key", false).get();
    }

    public LiveData<Boolean> isBooleanLiveData() { return sharedPreferences.getBoolean("boolean_key", false).getAsLiveData(); }


    public void setInteger(int value) {
        sharedPreferences.getInteger("integer_key").set(value);
    }

    public int getInteger() {
        return sharedPreferences.getInteger("integer_key", 0).get();
    }

    public LiveData<Integer> getIntegerLiveData() {
        return sharedPreferences.getInteger("integer_key", 0).getAsLiveData();
    }

    public void setPojo(Pojo value) {
        Type type = new TypeToken<Pojo>() {}.getType();
        sharedPreferences.getObject("pojo_key", new Pojo(), new JsonConverter<>(gson, type)).set(value);
    }

    public Pojo getPojo() {
        Type type = new TypeToken<Pojo>() {
        }.getType();
        return sharedPreferences.getObject("pojo_key",
                new Pojo(), new JsonConverter<Pojo>(gson, type)).get();
    }

    public LiveData<Pojo> getPojoLiveData() {
        Type type = new TypeToken<Pojo>() {
        }.getType();
        return sharedPreferences.getObject("pojo_key",
                new Pojo(), new JsonConverter<Pojo>(gson, type)).getAsLiveData();
    }


    public List<Pojo> getPojoList() {
        Type type = new TypeToken<ArrayList<Pojo>>() {
        }.getType();
        return sharedPreferences.getObject("pojo_list_key",
                new ArrayList<Pojo>(), new JsonConverter<List<Pojo>>(gson, type)).get();
    }

    public LiveData<List<Pojo>> getPojoListLiveData() {
        Type type = new TypeToken<ArrayList<Pojo>>() {
        }.getType();
        return sharedPreferences.getObject("pojo_list_key", new ArrayList<Pojo>(),
                new JsonConverter<List<Pojo>>(gson, type)).getAsLiveData();
    }

}
