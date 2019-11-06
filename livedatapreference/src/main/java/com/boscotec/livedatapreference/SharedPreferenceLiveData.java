package com.boscotec.livedatapreference;

import android.content.SharedPreferences;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.boscotec.livedatapreference.adapters.BooleanAdapter;
import com.boscotec.livedatapreference.adapters.ConverterAdapter;
import com.boscotec.livedatapreference.adapters.EnumAdapter;
import com.boscotec.livedatapreference.adapters.FloatAdapter;
import com.boscotec.livedatapreference.adapters.IntegerAdapter;
import com.boscotec.livedatapreference.adapters.LongAdapter;
import com.boscotec.livedatapreference.adapters.StringAdapter;
import com.boscotec.livedatapreference.adapters.StringSetAdapter;
import com.boscotec.livedatapreference.converters.Converter;

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

    /**
     * Create an instance of {@link SharedPreferenceLiveData} for {@code preferences}.
     */
    @CheckResult
    @NonNull
    public static SharedPreferenceLiveData create(/*@NonNull*/ SharedPreferences preferences) {
        checkNotNull(preferences, "preferences == null");
        return new SharedPreferenceLiveData(preferences);
    }

    private final SharedPreferences preferences;

    private SharedPreferenceLiveData(final SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public SharedPreferences getPreferences(){
        return preferences;
    }

    /**
     * Create a boolean preference for {@code key}. Default is {@code false}.
     */
    @CheckResult
    @NonNull
    public Preference<Boolean> getBoolean(@NonNull String key) {
        return getBoolean(key, DEFAULT_BOOLEAN);
    }

    /**
     * Create a boolean preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    public Preference<Boolean> getBoolean(@NonNull String key, @NonNull Boolean defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, BooleanAdapter.INSTANCE);
    }

    /**
     * Create an enum preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    public <T extends Enum<T>> Preference<T> getEnum(@NonNull String key, @NonNull T defaultValue, @NonNull Class<T> enumClass) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        checkNotNull(enumClass, "enumClass == null");
        return new RealPreference<>(preferences, key, defaultValue, new EnumAdapter<>(enumClass));
    }

    /**
     * Create a float preference for {@code key}. Default is {@code 0}.
     */
    @CheckResult
    @NonNull
    public Preference<Float> getFloat(@NonNull String key) {
        return getFloat(key, DEFAULT_FLOAT);
    }

    /**
     * Create a float preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    public Preference<Float> getFloat(@NonNull String key, @NonNull Float defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, FloatAdapter.INSTANCE);
    }

    /**
     * Create an integer preference for {@code key}. Default is {@code 0}.
     */
    @CheckResult
    @NonNull
    public Preference<Integer> getInteger(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getInteger(key, DEFAULT_INTEGER);
    }

    /**
     * Create an integer preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    public Preference<Integer> getInteger(@NonNull String key, @NonNull Integer defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, IntegerAdapter.INSTANCE);
    }

    /**
     * Create a long preference for {@code key}. Default is {@code 0}.
     */
    @CheckResult
    @NonNull
    public Preference<Long> getLong(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getLong(key, DEFAULT_LONG);
    }

    /**
     * Create a long preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    public Preference<Long> getLong(@NonNull String key, @NonNull Long defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, LongAdapter.INSTANCE);
    }

    /**
     * Create a preference for type {@code T} for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    public <T> Preference<T> getObject(@NonNull String key, @NonNull T defaultValue, @NonNull Converter<T> converter) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        checkNotNull(converter, "converter == null");
        return new RealPreference<>(preferences, key, defaultValue, new ConverterAdapter<>(converter));
    }

    /**
     * Create a string preference for {@code key}. Default is {@code ""}.
     */
    @CheckResult
    @NonNull
    public Preference<String> getString(@NonNull String key) {
        return getString(key, DEFAULT_STRING);
    }

    /**
     * Create a string preference for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull
    public Preference<String> getString(@NonNull String key, @NonNull String defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, StringAdapter.INSTANCE);
    }

    /**
     * Create a string set preference for {@code key}. Default is an empty set. Note that returned set
     * value will always be unmodifiable.
     */
    @RequiresApi(HONEYCOMB)
    @CheckResult
    @NonNull
    public Preference<Set<String>> getStringSet(@NonNull String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * Create a string set preference for {@code key} with a default of {@code defaultValue}.
     */
    @RequiresApi(HONEYCOMB)
    @CheckResult
    @NonNull
    public Preference<Set<String>> getStringSet(@NonNull String key, @NonNull Set<String> defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new RealPreference<>(preferences, key, defaultValue, StringSetAdapter.INSTANCE);
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

}
