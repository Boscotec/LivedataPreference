package com.boscotec.livedatapreference.adapters;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.Set;

public final class StringSetAdapter implements Adapter<Set<String>> {
  public static final StringSetAdapter INSTANCE = new StringSetAdapter();

  @Override public Set<String> get(@NonNull String key, @NonNull SharedPreferences preferences) {
    Set<String> value = preferences.getStringSet(key, null);
    assert value != null; // Not called unless key is present.
    return Collections.unmodifiableSet(value);
  }

  @Override public void set(@NonNull String key, @NonNull Set<String> value, @NonNull SharedPreferences.Editor editor) {
    editor.putStringSet(key, value);
  }
}
