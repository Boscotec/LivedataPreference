package com.boscotec.livedatapreference;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public interface Preference<T> {
  /** The key for which this preference will store and retrieve values. */
  @NonNull String key();

  /** The value used if none is stored. */
  @NonNull T defaultValue();

  /**
   * Retrieve the current value for this preference. Returns {@link #defaultValue()} if no value is
   * set.
   */
  @NonNull T get();

  /**
   * Retrieve the current value for this preference. Returns {@link #defaultValue()} if no value is
   * set. as a live data
   */
  @NonNull
  LiveData<T> getAsLiveData();

  /**
   * Change this preference's stored value to {@code value}.
   */
  void set(@NonNull T value);

  /** Returns true if this preference has a stored value. */
  boolean isSet();

  /** Delete the stored value for this preference, if any. */
  void delete();

}
