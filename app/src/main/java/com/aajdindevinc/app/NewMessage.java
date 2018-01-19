package com.aajdindevinc.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Struct;
import java.util.ArrayList;

public class NewMessage extends AppCompatActivity {
    Button check;
    Button not;
    EditText broj;
    EditText poruka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TinyDB database = new TinyDB(this);
        setContentView(R.layout.activity_new_message);
        getSupportActionBar().hide();

        broj = findViewById(R.id.editText);
        poruka = findViewById(R.id.editText2);
        check = findViewById(R.id.check);
        not = findViewById(R.id.not);
        final Intent intent = new Intent(this, MainActivity.class);
        final Intent intent1 = new Intent(this, MainActivity.class);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int j = database.getInt("id");
                    database.putString("broj" + j, broj.getText().toString());
                    database.putString("poruka" + j, poruka.getText().toString());
                    database.putInt("id", j + 1);
                    Toast.makeText(getApplicationContext(), R.string.success4, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), R.string.error3,Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
            }
        });
        not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });
    }
}
