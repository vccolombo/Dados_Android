package br727356.ufscar.comp.httpwww2.dados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class HowManyPlayers extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "br727356.ufscar.comp.httpwww2.dados";
    public static final String MARIANA = "MARIANA_MODE_STATUS";
    private Button mButton;
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many_players);

        mButton = (Button)findViewById(R.id.Button);
        mEdit = (EditText) findViewById(R.id.number);


    }

    public void OpenMain(View view)
    {
        if(mEdit.getText().toString().isEmpty());
        else {
            Intent intent = new Intent(this, MainActivity.class);
            String jogadores = mEdit.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, jogadores);
            intent.putExtra(MARIANA, "1");
            startActivity(intent);
            finish();
        }
    }
}
