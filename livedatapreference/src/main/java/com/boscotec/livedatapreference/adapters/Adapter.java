package com.boscotec.livedatapreference.adapters;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

/** Stores and retrieves instances of {@code T} in {@link SharedPreferences}. */
public interface Adapter<T> {
    /** Retrieve the value for {@code key} from {@code preferences}. */
    T get(@NonNull String key, @NonNull SharedPreferences preferences);

    /**
     * Store non-null {@code value} for {@code key} in {@code editor}.
     * <p>
     * Note: Implementations <b>must not</b> call {@code commit()} or {@code apply()} on
     * {@code editor}.
     */
    void set(@NonNull String key, @NonNull T value, @NonNull SharedPreferences.Editor editor);
}