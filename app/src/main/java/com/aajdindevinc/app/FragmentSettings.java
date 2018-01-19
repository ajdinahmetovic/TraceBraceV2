package com.aajdindevinc.app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentSettings extends Fragment {

    CardView card;
    CardView insta;
    CardView fb;

    public FragmentSettings() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_settings, container, false);
        card = view.findViewById(R.id.about1);
        insta = view.findViewById(R.id.insta);
        fb = view.findViewById(R.id.fb);

        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://tracebrace.site"));

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/tracebrac"));
                try {
                    startActivity(followIntent);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Doslo je do greske, pokusajte kasnije",Toast.LENGTH_SHORT).show();
                }
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/trace.brace/"));
                startActivity(followIntent);

            }
        });




        return view;
    }

}
