package com.boscotec.livedatapreference.converters;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

/**
 * Converts instances of {@code T} to be stored and retrieved as Strings in {@link
 * SharedPreferences}.
 */
public interface Converter<T> {
    /**
     * Deserialize to an instance of {@code T}. The input is retrieved from {@link
     * SharedPreferences#getString(String, String)}.
     */
    @NonNull
    T deserialize(@NonNull String serialized);

    /**
     * Serialize the {@code value} to a String. The result will be used with {@link
     * SharedPreferences.Editor#putString(String, String)}.
     */
    @NonNull String serialize(@NonNull T value);
}
