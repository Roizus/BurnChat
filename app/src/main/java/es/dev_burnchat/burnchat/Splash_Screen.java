package es.dev_burnchat.burnchat;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Jose on 29/1/16.
 */

public class Splash_Screen extends Activity {

    private MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        sound = MediaPlayer.create(this, R.raw.sonido);
        sound.setLooping(true);
        sound.start();
        openApp(true);
    }


    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sound.isPlaying()) {
            sound.stop();
            sound.release();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        sound.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        sound.pause();

    }

}