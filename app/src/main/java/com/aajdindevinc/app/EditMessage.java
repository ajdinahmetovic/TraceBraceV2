package com.aajdindevinc.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class EditMessage extends AppCompatActivity {

    EditText broj;
    EditText poruka;
    Button confirm;
    Button not;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = new Intent(this, MainActivity.class);

        final LinearLayout layout = (LinearLayout) findViewById(R.id.relativ);

        setContentView(R.layout.activity_edit_message);
        getSupportActionBar().hide();

        final TinyDB database = new TinyDB(this);
        broj = findViewById(R.id.editText3);
        poruka = findViewById(R.id.editText4);
        confirm = findViewById(R.id.check1);
        not = findViewById(R.id.not1);
        delete = findViewById(R.id.delete);

        Intent i = getIntent();
        final Bundle bundle = i.getExtras();

        broj.setText(database.getString("broj"+bundle.get("id")));
        poruka.setText(database.getString("poruka"+bundle.get("id")));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    database.putString("broj" + bundle.get("id"), broj.getText().toString());
                    database.putString("poruka" + bundle.get("id"), poruka.getText().toString());
                    Toast.makeText(getApplicationContext(), R.string.succes1, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), R.string.error1,Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);

            }
        });

        not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                Toast.makeText(getApplicationContext(), R.string.succes2, Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int var = (int) bundle.get("id");

                    database.remove("broj" + bundle.get("id"));
                    database.remove("poruka" + bundle.get("id"));
                    database.putInt("remove",(int) bundle.get("id"));
                    ArrayList<Integer> remove  = new ArrayList<>();
                    remove = database.getListInt("remover");
                    remove.add(var);
                    database.putListInt("remover",remove);
                    Toast.makeText(getApplicationContext(), R.string.succes3,Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), R.string.error2,Toast.LENGTH_SHORT).show();

                }
                startActivity(intent);
            }
        });
    }
}
