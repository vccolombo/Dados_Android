package br727356.ufscar.comp.httpwww2.dados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class HowManyPlayers extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "br727356.ufscar.comp.httpwww2.dados";
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
        String s = mEdit.getText().toString();
        if(s.isEmpty() || Integer.valueOf(s) == 0);
        else {
            Intent intent = new Intent();
            String jogadores = mEdit.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, jogadores);
            this.setResult(RESULT_OK, intent);
            finish();
        }
    }
}
