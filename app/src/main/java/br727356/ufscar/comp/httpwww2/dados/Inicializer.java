package br727356.ufscar.comp.httpwww2.dados;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Inicializer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicializer);

        startMainActivity();
    }

    private void startMainActivity()
    {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Inicializer.this, MainActivity.class);
                intent.putExtra(HowManyPlayers.EXTRA_MESSAGE, "1");
                intent.putExtra(HowManyPlayers.MARIANA, "0");
                startActivity(intent);
                finish();
            }
        }, 100);
    }
}
