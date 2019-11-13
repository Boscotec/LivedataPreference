package com.boscotec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.boscotec.livedatapreference.SharedPreferenceLiveData;

public class MainActivity extends AppCompatActivity {

    private SharedPreferenceHelper preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            preferences = new SharedPreferenceHelper(getSecuredSharedPreference());
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        preferences = new SharedPreferenceHelper(PreferenceManager.getDefaultSharedPreferences(this));
        preferences.setBoolean(false);
        boolean isLoggedIn = preferences.isBoolean();
        LiveData<Boolean> isLoggedInLive = preferences.isBooleanLiveData();
    }


    private SharedPreferences getSecuredSharedPreference() throws Exception {
        String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
        return EncryptedSharedPreferences.create("secret_shared_prefs", masterKeyAlias, this,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
    }
}
