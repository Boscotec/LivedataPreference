package com.boscotec.livedatapreference;

import android.content.SharedPreferences;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.boscotec.livedatapreference.adapters.BooleanAdapter;
import com.boscotec.livedatapreference.adapters.ConverterAdapter;
import com.boscotec.livedatapreference.adapters.EnumAdapter;
import com.boscotec.livedatapreference.adapters.FloatAdapter;
import com.boscotec.livedatapreference.adapters.IntegerAdapter;
import com.boscotec.livedatapreference.adapters.LongAdapter;
import com.boscotec.livedatapreference.adapters.StringAdapter;
import com.boscotec.livedatapreference.adapters.StringSetAdapter;
import com.boscotec.livedatapreference.converters.Converter;
import com.boscotec.livedatapreference.converters.JsonConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

import static android.os.Build.VERSION_CODES.HONEYCOMB;
import static androidx.core.util.Preconditions.checkNotNull;

public final class SharedPreferenceLiveData {
    private static final Float DEFAULT_FLOAT = 0f;
    private static final Integer DEFAULT_INTEGER = 0;
    private static final Boolean DEFAULT_BOOLEAN = false;
    private static final Long DEFAULT_LONG = 0L;
    private static final String DEFAULT_STRING = "";
    private static final Set<String> DEFAULT_STRING_SET = Collections.emptySet();
    private Gson gson;

    /**
     * Create an instance of {@link SharedPreferenceLiveData} for {@code preferences}.
     */
    @CheckResult
    @NonNull
    public static SharedPreferenceLiveData create(@NonNull SharedPreferences preferences) {
        checkNotNull(preferences, "preferences == null");
        return new SharedPreferenceLiveData(preferences);
    }

    private final SharedPreferences preferences;

    public SharedPreferences getPreferences() {
        return preferences;
    }

    protected SharedPreferenceLiveData(final SharedPreferences preferences) {
        this.preferences = preferences;
        this.gson = new Gson();
    }

    public void putBoolean(@NonNull String key, @NonNull Boolean value) {
        preBoolean(key, DEFAULT_BOOLEAN).set(value);
    }

    @CheckResult
    @NonNull
    public Boolean getBoolean(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getBoolean(key, DEFAULT_BOOLEAN);
    }

    @CheckResult
    @NonNull
    public Boolean getBoolean(@NonNull String key, @NonNull Boolean defaultValue) {
        return preBoolean(key, defaultValue).get();
    }

    @CheckResult
    @NonNull
    public LiveData<Boolean> getBooleanLiveData(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getBooleanLiveData(key, DEFAULT_BOOLEAN);
    }

    @CheckResult
    @NonNull
    public LiveData<Boolean> getBooleanLiveData(@NonNull String key, @NonNull Boolean defaultValue) {
        return preBoolean(key, defaultValue).getAsLiveData();
    }

    /**
     * Create a boolean preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    private Preference<Boolean> preBoolean(@NonNull String key, @NonNull Boolean defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, BooleanAdapter.INSTANCE);
    }

    public void putFloat(@NonNull String key, Float value) {
        preFloat(key, DEFAULT_FLOAT).set(value);
    }

    @CheckResult
    @NonNull
    public Float getFloat(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getFloat(key, DEFAULT_FLOAT);
    }

    @CheckResult
    @NonNull
    public Float getFloat(@NonNull String key, @NonNull Float defaultValue) {
        return preFloat(key, defaultValue).get();
    }

    @CheckResult
    @NonNull
    public LiveData<Float> getFloatLiveData(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getFloatLiveData(key, DEFAULT_FLOAT);
    }

    @CheckResult
    @NonNull
    public LiveData<Float> getFloatLiveData(@NonNull String key, @NonNull Float defaultValue) {
        return preFloat(key, defaultValue).getAsLiveData();
    }

    /**
     * Create a float preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    private Preference<Float> preFloat(@NonNull String key, @NonNull Float defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, FloatAdapter.INSTANCE);
    }

    public void putInteger(@NonNull String key, Integer value) {
        preInteger(key, DEFAULT_INTEGER).set(value);
    }

    public Integer getInteger(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getInteger(key, DEFAULT_INTEGER);
    }

    @CheckResult
    @NonNull
    public Integer getInteger(@NonNull String key, @NonNull Integer defaultValue) {
        return preInteger(key, defaultValue).get();
    }

    @CheckResult
    @NonNull
    public LiveData<Integer> getIntegerLiveData(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getIntegerLiveData(key, DEFAULT_INTEGER);
    }

    @CheckResult
    @NonNull
    public LiveData<Integer> getIntegerLiveData(@NonNull String key, @NonNull Integer defaultValue) {
        return preInteger(key, defaultValue).getAsLiveData();
    }

    /**
     * Create an integer preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    private Preference<Integer> preInteger(@NonNull String key, @NonNull Integer defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, IntegerAdapter.INSTANCE);
    }

    public void putLong(@NonNull String key, Long value) {
        preLong(key, DEFAULT_LONG).set(value);
    }

    @CheckResult
    @NonNull
    public Long getLong(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getLong(key, DEFAULT_LONG);
    }

    @CheckResult
    @NonNull
    public Long getLong(@NonNull String key, @NonNull Long defaultValue) {
        return preLong(key, defaultValue).get();
    }

    @CheckResult
    @NonNull
    public LiveData<Long> getLongLiveData(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getLongLiveData(key, DEFAULT_LONG);
    }

    @CheckResult
    @NonNull
    public LiveData<Long> getLongLiveData(@NonNull String key, @NonNull Long defaultValue) {
        return preLong(key, defaultValue).getAsLiveData();
    }

    /**
     * Create a long preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    private Preference<Long> preLong(@NonNull String key, @NonNull Long defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, LongAdapter.INSTANCE);
    }


    public void putString(@NonNull String key, String value) {
        preString(key, DEFAULT_STRING).set(value);
    }

    @CheckResult
    @NonNull
    public String getString(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getString(key, DEFAULT_STRING);
    }

    @CheckResult
    @NonNull
    public String getString(@NonNull String key, @NonNull String defaultValue) {
        //noinspection UnnecessaryBoxing
        return preString(key, defaultValue).get();
    }

    @CheckResult
    @NonNull
    public LiveData<String> getStringLiveData(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getStringLiveData(key, DEFAULT_STRING);
    }


    @CheckResult
    @NonNull
    public LiveData<String> getStringLiveData(@NonNull String key, @NonNull String defaultValue) {
        return preString(key, defaultValue).getAsLiveData();
    }

    /**
     * Create a string preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    private Preference<String> preString(@NonNull String key, @NonNull String defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, StringAdapter.INSTANCE);
    }

    public void putStringSet(@NonNull String key, Set<String> value) {
        preStringSet(key, DEFAULT_STRING_SET).set(value);
    }

    @RequiresApi(HONEYCOMB)
    @CheckResult
    @NonNull
    public Set<String> getStringSet(@NonNull String key) {
        return getStringSet(key, DEFAULT_STRING_SET);
    }

    @RequiresApi(HONEYCOMB)
    @CheckResult
    @NonNull
    public Set<String> getStringSet(@NonNull String key, @NonNull Set<String> defaultValue) {
       return preStringSet(key, defaultValue).get();
    }

    @RequiresApi(HONEYCOMB)
    @CheckResult
    @NonNull
    public LiveData<Set<String>> getStringSetLiveData(@NonNull String key) {
        return getStringSetLiveData(key, DEFAULT_STRING_SET);
    }

    @RequiresApi(HONEYCOMB)
    @CheckResult
    @NonNull
    public LiveData<Set<String>> getStringSetLiveData(@NonNull String key, @NonNull Set<String> defaultValue) {
       return preStringSet(key, defaultValue).getAsLiveData();
    }

    /**
     * Create a string set preference for {@code key} with a default of {@code defaultValue}.
     */
    @RequiresApi(HONEYCOMB)
    @CheckResult
    @NonNull
    private Preference<Set<String>> preStringSet(@NonNull String key, @NonNull Set<String> defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, StringSetAdapter.INSTANCE);
    }

    public <T> void putObject(@NonNull String key, @NonNull T value) {
        //preObject(key, value, new JsonConverter<T>(gson, new TypeToken<T>() {}.getType())).set(value);
        preObject(key, value, new JsonConverter<T>(gson, null)).set(value);
    }

    @CheckResult
    @NonNull
    public <T> T getObject(@NonNull String key, @NonNull T defaultValue, @NonNull Type type) {
        checkNotNull(type, "type == null");
        //return preObject(key, defaultValue, new JsonConverter<T>(gson, new TypeToken<T>() {}.getType())).get();
        //return preObject(key, defaultValue, new JsonConverter<T>(gson, defaultValue.getClass())).get();
        return preObject(key, defaultValue, new JsonConverter<T>(gson, type)).get();
    }

    @CheckResult
    @NonNull
    public <T> LiveData<T> getObjectLiveData(@NonNull String key, @NonNull T defaultValue, @NonNull Type type) {
        checkNotNull(type, "type == null");
        //return preObject(key, defaultValue, new JsonConverter<T>(gson, new TypeToken<T>() {}.getType())).getAsLiveData();
        //return preObject(key, defaultValue, new JsonConverter<T>(gson, defaultValue.getClass())).get();
        return preObject(key, defaultValue, new JsonConverter<T>(gson, type)).getAsLiveData();
    }

    /**
     * Create a preference for type {@code T} for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    private <T> Preference<T> preObject(@NonNull String key, @NonNull T defaultValue, @NonNull Converter<T> converter) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        checkNotNull(converter, "converter == null");
        return new RealPreference<>(preferences, key, defaultValue, new ConverterAdapter<>(converter));
    }

    private <T extends Enum<T>> void putEnum(@NonNull String key, @NonNull T value, @NonNull Class<T> enumClass) {
        preEnum(key, null, enumClass).set(value);
    }

    /**
     * Create an enum preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    private <T extends Enum<T>> Preference<T> preEnum(@NonNull String key, @NonNull T defaultValue, @NonNull Class<T> enumClass) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        checkNotNull(enumClass, "enumClass == null");
        return new RealPreference<>(preferences, key, defaultValue, new EnumAdapter<>(enumClass));
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

}
