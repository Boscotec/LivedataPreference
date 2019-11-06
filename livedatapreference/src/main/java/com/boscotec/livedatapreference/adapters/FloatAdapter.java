package com.boscotec.livedatapreference.adapters;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public final class FloatAdapter implements Adapter<Float> {
  public static final FloatAdapter INSTANCE = new FloatAdapter();

  @Override public Float get(@NonNull String key, @NonNull SharedPreferences preferences) {
    return preferences.getFloat(key, 0f);
  }

  @Override public void set(@NonNull String key, @NonNull Float value, @NonNull SharedPreferences.Editor editor) {
    editor.putFloat(key, value);
  }
}
