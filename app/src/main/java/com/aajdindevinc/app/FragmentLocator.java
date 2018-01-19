package com.aajdindevinc.app;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.ColorUtils;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class FragmentLocator extends Fragment {
    TextView gpsmsg;
    ImageView image;
    String wifiime = null;
    int from = Color.rgb(255,255,255);
    int to = Color.rgb(255,159,0);

    public FragmentLocator() {
        //Konstruktor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_locator, container, false);
        gpsmsg = view.findViewById(R.id.gps_msg);
        image = view.findViewById(R.id.imageView);
        final ConstraintLayout layout = view.findViewById(R.id.frame);

        final int pozadina1 = R.drawable.notconn_ba;
        final int pozadina2 = R.drawable.full;

            WifiManager manager = (WifiManager) FragmentLocator.this.getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = manager.getConnectionInfo();
            wifiime = info.getSSID();
//          Toast.makeText(getContext(), wifiime, Toast.LENGTH_SHORT).show();
            System.out.println(wifiime);

                    if (wifiime.equals("\"AndroidWifi\"")) {
                        image.setImageResource(pozadina2);
                        //Toast.makeText(getContext(), "BLABLALBALBAL", Toast.LENGTH_SHORT).show();
                    }
                    try {
                        wifiime = info.getSSID();
                     //   Toast.makeText(getContext(), wifiime, Toast.LENGTH_SHORT).show();
                        System.out.println(wifiime);
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "WiFi je iskljucen", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    if (wifiime.equals("\"AndroidWifi\"")) {
                        image.setImageResource(pozadina2);
                   //     Toast.makeText(getContext(), "BLABLALBALBAL", Toast.LENGTH_SHORT).show();
                    } else {
                        image.setBackgroundResource(pozadina1);
                        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), from, to, from);
                        colorAnimation.setRepeatCount(ValueAnimator.INFINITE);
                        colorAnimation.setDuration(1000);
                        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animator) {
                                layout.setBackgroundColor((int) animator.getAnimatedValue());
                            }
                        });
                        colorAnimation.start();
                    }

        gpsmsg.setText(R.string.gps_succes);
        Drawable slika = getContext().getResources().getDrawable(R.drawable.gps_succ);
        gpsmsg.setCompoundDrawablesWithIntrinsicBounds(slika, null, null, null);

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), from, to);
        //colorAnimation.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimation.setDuration(1000);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                layout.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
            colorAnimation.start();
            return view;
    }
    }


