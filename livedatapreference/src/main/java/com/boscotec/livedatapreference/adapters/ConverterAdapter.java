package com.boscotec.livedatapreference.adapters;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.boscotec.livedatapreference.converters.Converter;

import static androidx.core.util.Preconditions.checkNotNull;

public final class ConverterAdapter<T> implements Adapter<T> {
    private final Converter<T> converter;

    public ConverterAdapter(Converter<T> converter) {
        this.converter = converter;
    }

    @Override public T get(@NonNull String key, @NonNull SharedPreferences preferences) {
        String serialized = preferences.getString(key, null);
        assert serialized != null; // Not called unless key is present.
        T value = converter.deserialize(serialized);
        checkNotNull(value, "Deserialized value must not be null from string: " + serialized);
        return value;
    }

    @Override
    public void set(@NonNull String key, @NonNull T value, @NonNull SharedPreferences.Editor editor) {
        String serialized = converter.serialize(value);
        checkNotNull(serialized, "Serialized string must not be null from value: " + value);
        editor.putString(key, serialized);
    }

}
