package com.boscotec.livedatapreference;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.boscotec.livedatapreference.adapters.Adapter;

public final class RealPreference<T> extends LiveData<T> implements Preference<T> {
  private final OnSharedPreferenceChangeListener preferenceChangeListener;
  @NonNull
  private final SharedPreferences preferences;
  @NonNull
  private final String key;
  private final T defaultValue;
  private final Adapter<T> adapter;

  public RealPreference(@NonNull SharedPreferences sharedPrefs, @NonNull final String key,
                        T defValue, Adapter<T> adapter) {
    super();
    checkParameterIsNotNull(sharedPrefs, "sharedPrefs");
    checkParameterIsNotNull(key, "key");
    this.preferences = sharedPrefs;
    this.key = key;
    this.defaultValue = defValue;
    this.adapter = adapter;
    this.preferenceChangeListener = new OnSharedPreferenceChangeListener() {
      public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (areEqual(key, RealPreference.this.key())) {
          RealPreference.this.setValue(RealPreference.this.getValueFromPreferences(key));
        }
      }
    };
  }

  private static void checkNotNull(Object object, String message) {
    if (object == null) {
      throw new NullPointerException(message);
    }
  }

  private static void checkParameterIsNotNull(Object value, String paramName) {
    if (value == null) {
      throw new NullPointerException(paramName);
    }
  }

  private static boolean areEqual(Object first, Object second) {
    return first == null ? second == null : first.equals(second);
  }

  @Override
  protected void onActive() {
    super.onActive();
    this.setValue(this.getValueFromPreferences(this.key));
    this.preferences.registerOnSharedPreferenceChangeListener(this.preferenceChangeListener);
  }

  @Override
  protected void onInactive() {
    this.preferences.unregisterOnSharedPreferenceChangeListener(this.preferenceChangeListener);
    super.onInactive();
  }

  private synchronized T getValueFromPreferences(@NonNull String key){
    if(!preferences.contains(key)){
      return defaultValue;
    }
    return adapter.get(key, preferences);
  }

  @NonNull
  @Override
  public String key() {
    return key;
  }

  @NonNull
  @Override
  public T defaultValue() {
    return defaultValue;
  }

  @NonNull
  public final SharedPreferences preferences() {
    return preferences;
  }

  @NonNull
  @Override
  public synchronized T get() {
    if(!preferences.contains(key)){
      return defaultValue;
    }
    return adapter.get(key, preferences);
  }

  @NonNull
  @Override
  public synchronized LiveData<T> getAsLiveData() {
    return this;
  }

  @Override
  public void set(@NonNull T value) {
    checkNotNull(value, "value == null");
    SharedPreferences.Editor editor = preferences.edit();
    adapter.set(key, value, editor);
    editor.apply();
  }

  @Override
  public boolean isSet() {
    return preferences.contains(key);
  }

  @Override
  public void delete() {
    preferences.edit().remove(key).apply();
  }
}
