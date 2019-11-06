package com.boscotec.livedatapreference.adapters;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public final class BooleanAdapter implements Adapter<Boolean> {
  public static final BooleanAdapter INSTANCE = new BooleanAdapter();

  @Override public Boolean get(@NonNull String key, @NonNull SharedPreferences preferences) {
    return preferences.getBoolean(key, false);
  }

  @Override public void set(@NonNull String key, @NonNull Boolean value, @NonNull SharedPreferences.Editor editor) {
    editor.putBoolean(key, value);
  }
}
