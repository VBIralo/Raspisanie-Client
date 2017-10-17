package com.vbiralo.raspisanie;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class MyPreferenceActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this, "После смены сайта, пожалуйста, перезайдите в приложение", Toast.LENGTH_LONG).show();
        addPreferencesFromResource(R.xml.settings);
    }


}

