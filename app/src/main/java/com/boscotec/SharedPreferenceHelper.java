package com.boscotec;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.boscotec.livedatapreference.SharedPreferenceLiveData;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceHelper {

    private SharedPreferenceLiveData sharedPreferences;

    public SharedPreferenceHelper(SharedPreferences preferences) {
        if (sharedPreferences == null) {
            sharedPreferences = SharedPreferenceLiveData.create(preferences);
        }
    }

    public void setBoolean(boolean value) {
        sharedPreferences.putBoolean("boolean_key", value);
    }

    public boolean isBoolean() {
        return sharedPreferences.getBoolean("boolean_key", false);
    }

    public LiveData<Boolean> isBooleanLiveData() {
        return sharedPreferences.getBooleanLiveData("boolean_key", false);
    }

    public void setString(String value) { sharedPreferences.putString("string_key", value); }

    public String getString() { return sharedPreferences.getString("string_key", ""); }

    public LiveData<String> getStringLiveData() { return sharedPreferences.getStringLiveData("string_key", ""); }

    public void setInteger(int value) {
        sharedPreferences.putInteger("integer_key", value);
    }

    public int getInteger() {
        return sharedPreferences.getInteger("integer_key", 0);
    }

    public LiveData<Integer> getIntegerLiveData() {
        return sharedPreferences.getIntegerLiveData("integer_key", 0);
    }

    public void setPojo(Pojo value) {
        sharedPreferences.putObject("pojo_key", value);
    }

    public Pojo getPojo() {
        return sharedPreferences.getObject("pojo_key", new Pojo(), new TypeToken<Pojo>() {}.getType());
    }

    public LiveData<Pojo> getPojoLiveData() {
        return sharedPreferences.getObjectLiveData("pojo_key", new Pojo(), new TypeToken<Pojo>() {}.getType());
    }

    public void setPojoList(List<Pojo> value) {
        sharedPreferences.putObject("pojo_list_key", value);
    }

    public ArrayList<Pojo> getPojoList() {
        return sharedPreferences.getObject("pojo_list_key", new ArrayList<Pojo>(), new TypeToken<List<Pojo>>() {}.getType());
    }

    public LiveData<ArrayList<Pojo>> getPojoListLiveData() {
        return sharedPreferences.getObjectLiveData("pojo_list_key", new ArrayList<Pojo>(), new TypeToken<List<Pojo>>() {}.getType());
    }

}