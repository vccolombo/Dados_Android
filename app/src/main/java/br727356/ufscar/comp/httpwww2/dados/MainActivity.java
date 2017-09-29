package br727356.ufscar.comp.httpwww2.dados;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private Vibrator mVibrator;

    private ImageView dado1;
    private ImageView dado2;

    private int players;
    private int counter;

    private boolean mariana;

    private float x1, x2;
    private float y1, y2;
    private static final float swapdistance = 150;

    int[] p = {R.drawable.dado1, R.drawable.dado2, R.drawable.dado3, R.drawable.dado4, R.drawable.dado5, R.drawable.dado6};


    //Quero finalizar a activity só se o how_many deu certo
    //Quero salvar algo por all programa
    //Quero um textview clicavel?
    //Quero uma main activity que recebe um intent, mas saiba quando nao vem nenhum intent e trate isso
    //Quero um about que esconda o modo mariana
    //extends appcompatactivity?
    //copiar um objeto para outro?


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String message = intent.getStringExtra(HowManyPlayers.EXTRA_MESSAGE);
        String modo_mariana_intent = intent.getStringExtra(HowManyPlayers.MARIANA);

        players = Integer.valueOf(message);
        counter = 0;
        mariana = false;
        if(Integer.valueOf(modo_mariana_intent) == 1)
        {
            showMarianaStatus("Modo Mariana ATIVADO");
            mariana = true;
        }

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
                handleShakeEvent();
            }


        });


    }

    private void handleShakeEvent() {
        Random gerador = new Random();
        //Log.d("MainActivity", String.valueOf(p[gerador.nextInt(6)]));
        //Log.d("MainActivity", String.valueOf(p[gerador.nextInt(6)]));

        dado1.setImageResource(p[gerador.nextInt(6)]);
        dado2.setImageResource(p[gerador.nextInt(6)]);


        if((++counter % players) == 1 && mariana) //Mariana checker
        {
            dado1.setImageResource(p[5]);
            dado2.setImageResource(p[5]);
        }


        mVibrator.vibrate(500);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                if(Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)) > swapdistance)
                {
                    handleShakeEvent();
                }
                break;
        }

        return super.onTouchEvent(event);


    }
    public void showPopup(View v) {
        Log.d("MainActivity", "Inflar");

        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());


        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.modo_mariana)
                {
                    Log.d("MainActivity", "Modo Mariana");
                    if(mariana)
                    {
                        showMarianaStatus("Modo Mariana desativado");

                        mariana = false;
                    }
                    else
                    {

                        Intent intent = new Intent(MainActivity.this, HowManyPlayers.class);
                        startActivity(intent);
                    }

                    return true;
                }
                return false;
            }
        });
    }

    public void showMarianaStatus(String s)
    {
        Context context = getApplicationContext();
        CharSequence text = s;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }



}
