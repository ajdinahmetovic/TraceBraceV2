package com.aajdindevinc.app;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A.Ajdin
 */
public class BlankFragment extends Fragment {
//TEST
    String data [][] = {
           {"062737196","Pomozi mi Seade opasnosti sam, ovo je moja lokacija"},
           {"063212910","Pomozi mi Mama opasnosti sam, ovo je moja lokacija"},
           {"062597445","Pomozi mi Djede opasnosti sam, ovo je moja lokacija"},
           {"063250980","Pomozi mi Djeda Mraze opasnosti sam, ovo je moja lokacija"},
           {"062111111","Pomozi mi Hodza opasnosti sam, ovo je moja lokacija"}
   };
    public BlankFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TinyDB database = new TinyDB(getContext());

        final Intent intent = new Intent(getContext(), NewMessage.class);
        final Intent intent1 = new Intent (getContext(), EditMessage.class);
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        LinearLayout layout =  view.findViewById(R.id.relativ);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,20,0,10);

        LinearLayout.LayoutParams text2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams btn = new LinearLayout.LayoutParams(100,100);
        btn.gravity = Gravity.CENTER_HORIZONTAL;
        btn.topMargin = 30;
       //database.clear();
            for(int i = 0;i<database.getInt("id");i++){
                final int ident = i;
                    CardView card = new CardView(getContext());
                    card.setRadius(9);
                    card.isClickable();
                    card.setId(i);
                    card.setLayoutParams(params);
                    card.setContentPadding(15, 15, 15, 15);
                    card.setMaxCardElevation(15);
                    card.setCardElevation(15);
                    card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent1.putExtra("id", ident);
                            startActivity(intent1);
                        }
                    });
                    TextView broj = new TextView(getContext());
                    broj.setLayoutParams(params);
                    broj.setText(getString(R.string.za) + database.getString("broj" + i));
                    broj.setTextSize(20);
                    card.addView(broj);
                    TextView poruka = new TextView(getContext());
                    poruka.setText(database.getString("poruka" + i));
                    poruka.setTextSize(15);
                    poruka.setPadding(0, 75, 0, 0);
                    poruka.setLayoutParams(text2);
                    card.addView(poruka);
                    layout.addView(card);
        }
        ArrayList<Integer> remove = new ArrayList<>();
        remove = database.getListInt("remover");


      for(int i = 0;i<remove.size();i++){
          layout.removeView(layout.findViewById(remove.get(i)));
      }

        Button add = new Button (getContext());
        add.setBackgroundResource(R.drawable.clipart);
        add.setLayoutParams(btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                //Toast.makeText(getContext(), "Nova poruka", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        layout.addView(add);
        return view;
    }
}
