package com.boscotec.livedatapreference.adapters;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public final class StringAdapter implements Adapter<String> {
  public static final StringAdapter INSTANCE = new StringAdapter();

  @Override public String get(@NonNull String key, @NonNull SharedPreferences preferences) {
    String value = preferences.getString(key, null);
    assert value != null; // Not called unless key is present.
    return value;
  }

  @Override public void set(@NonNull String key, @NonNull String value, @NonNull SharedPreferences.Editor editor) {
    editor.putString(key, value);
  }
}
