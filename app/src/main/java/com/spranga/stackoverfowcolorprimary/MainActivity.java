package com.spranga.stackoverfowcolorprimary;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private ApplicationAPI applicationAPI;
    private Button aThemeButton;
    private Button bThemeButton;
    private Button cThemeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applicationAPI = ((ColorPrimaryApplication) getApplication()).getApplicationAPI();
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUIElements();
        toggleButtons();
        addListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeListeners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.theme_a_button:
                Log.d(TAG, "Theme A Button Pressed.");
                applicationAPI.getSharedPreferencesWrapper().saveCurrentThemeKey(getString(R.string.theme_a_key));
                break;
            case R.id.theme_b_button:
                Log.d(TAG, "Theme B Button Pressed.");
                applicationAPI.getSharedPreferencesWrapper().saveCurrentThemeKey(getString(R.string.theme_b_key));
                break;
            case R.id.theme_c_button:
                Log.d(TAG, "Theme C Button Pressed.");
                applicationAPI.getSharedPreferencesWrapper().saveCurrentThemeKey(getString(R.string.theme_c_key));
                break;
        }
        restartApp();
    }

    private void setTheme() {
        int theme;

        try {
            ActivityInfo activityInfo = this.getPackageManager().getActivityInfo(getComponentName(), 0);
            theme = activityInfo.theme;

            if (applicationAPI.getSharedPreferencesWrapper().getCurrentThemeKey().equalsIgnoreCase(getString(R.string.theme_a_key))) {
                if (theme == R.style.Theme_ThemeB
                        || theme == R.style.Theme_ThemeC) {
                    theme = R.style.Theme_ThemeA;
                }
            } else if (applicationAPI.getSharedPreferencesWrapper().getCurrentThemeKey().equalsIgnoreCase(getString(R.string.theme_b_key))) {
                if (theme == R.style.Theme_ThemeA
                        || theme == R.style.Theme_ThemeC) {
                    theme = R.style.Theme_ThemeB;
                }
            } else {
                if (theme == R.style.Theme_ThemeA
                        || theme == R.style.Theme_ThemeB) {
                    theme = R.style.Theme_ThemeC;
                }
            }
            setTheme(theme);
        } catch (PackageManager.NameNotFoundException ex) {
            Log.e(TAG, "PackageManager.NameNotFoundException " + ex);
        }
    }

    private void bindUIElements() {
        aThemeButton = (Button) findViewById(R.id.theme_a_button);
        bThemeButton = (Button) findViewById(R.id.theme_b_button);
        cThemeButton = (Button) findViewById(R.id.theme_c_button);
    }

    private void addListeners() {
        aThemeButton.setOnClickListener(this);
        bThemeButton.setOnClickListener(this);
        cThemeButton.setOnClickListener(this);
    }

    private void removeListeners() {
        aThemeButton.setOnClickListener(null);
        bThemeButton.setOnClickListener(null);
        cThemeButton.setOnClickListener(null);
    }

    private void toggleButtons() {
        if (applicationAPI.getSharedPreferencesWrapper().getCurrentThemeKey().equalsIgnoreCase(getString(R.string.theme_a_key))) {
            aThemeButton.setEnabled(false);
            bThemeButton.setEnabled(true);
            cThemeButton.setEnabled(true);
        } else if (applicationAPI.getSharedPreferencesWrapper().getCurrentThemeKey().equalsIgnoreCase(getString(R.string.theme_b_key))) {
            aThemeButton.setEnabled(true);
            bThemeButton.setEnabled(false);
            cThemeButton.setEnabled(true);
        } else {
            aThemeButton.setEnabled(true);
            bThemeButton.setEnabled(true);
            cThemeButton.setEnabled(false);
        }
    }

    private void restartApp() {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
        startActivity(intent);
    }
}
