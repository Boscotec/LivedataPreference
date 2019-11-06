package com.boscotec.livedatapreference.adapters;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public final class IntegerAdapter implements Adapter<Integer> {
  public static final IntegerAdapter INSTANCE = new IntegerAdapter();

  @Override public Integer get(@NonNull String key, @NonNull SharedPreferences preferences) {
    return preferences.getInt(key, 0);
  }

  @Override public void set(@NonNull String key, @NonNull Integer value, @NonNull SharedPreferences.Editor editor) {
    editor.putInt(key, value);
  }
}
