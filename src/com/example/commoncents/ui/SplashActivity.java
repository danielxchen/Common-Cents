package com.example.commoncents.ui;

import com.example.commoncents.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

/**
 * SplashActivity.
 * @author Daniel
 *
 */
public class SplashActivity extends Activity {

    /**
    * timer.
    */
    private static int TIME_OUT = 2500;

    /**
     * MediaPlayer.
     */
    private MediaPlayer mp;

    @Override
    protected final void onCreate(final Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);

                finish();
            }
        }, TIME_OUT);

        mp = MediaPlayer.create(getBaseContext(),
        R.drawable.everything_is_awesome);
        mp.start();
    }

    /**
     * onPause().
     */
    protected final void onPause() {
        super.onPause();
        mp.release();
        finish();
    }
}