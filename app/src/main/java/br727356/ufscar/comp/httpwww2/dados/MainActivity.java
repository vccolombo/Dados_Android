package br727356.ufscar.comp.httpwww2.dados;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private Vibrator mVibrator;

    private ImageView dado1;
    private ImageView dado2;

    int[] p = {R.drawable.dado1, R.drawable.dado2, R.drawable.dado3, R.drawable.dado4, R.drawable.dado5, R.drawable.dado6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        dado1 = (ImageView) findViewById(R.id.dado1);
        dado2 = (ImageView) findViewById(R.id.dado2);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                handleShakeEvent(count);
            }


        });
    }

    private void handleShakeEvent(int count) {
        Random gerador = new Random();
        //Log.d("MainActivity", String.valueOf(p[gerador.nextInt(6)]));
        //Log.d("MainActivity", String.valueOf(p[gerador.nextInt(6)]));

        dado1.setImageResource(p[gerador.nextInt(6)]);
        dado2.setImageResource(p[gerador.nextInt(6)]);
        mVibrator.vibrate(500);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
