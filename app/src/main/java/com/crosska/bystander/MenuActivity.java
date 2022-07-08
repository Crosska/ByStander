package com.crosska.bystander;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.dynamicanimation.animation.FloatValueHolder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    private CardView eye_card;
    private CardView button_start;
    private CardView button_config;
    private CardView button_info;
    private CardView namecard;
    private TextView text_one;
    private TextView text_two;
    private LinearLayout name_app;
    private ImageView image_eye_inner;
    MediaPlayer player_sounds;
    Intent ambient;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);

        //startService(new Intent(MenuActivity.this, AmbientMediaPlayer.class));
        //data = (GlobalData) this.getApplication();
        setContentView(R.layout.activity_main_menu);
        player_sounds = MediaPlayer.create(this, R.raw.dev_sound);
        player_sounds.setLooping(false);
        name_app = findViewById(R.id.app_name);
        eye_card = findViewById(R.id.eye_card);
        button_start = findViewById(R.id.button_start);
        button_config = findViewById(R.id.button_config);
        button_info = findViewById(R.id.button_info);
        name_app.setVisibility(View.INVISIBLE);
        eye_card.setVisibility(View.INVISIBLE);
        button_start.setVisibility(View.INVISIBLE);
        button_config.setVisibility(View.INVISIBLE);
        button_info.setVisibility(View.INVISIBLE);
        image_eye_inner = findViewById(R.id.icon_eye_inner);
        namecard = findViewById(R.id.developer_cardview);
        text_one = findViewById(R.id.name_part_one);
        text_two = findViewById(R.id.name_part_two);
        text_one.setVisibility(View.INVISIBLE);
        text_two.setVisibility(View.INVISIBLE);

        /*settings = getApplicationContext().getSharedPreferences("system_data", 0);
        editor = settings.edit();
        editor.putBoolean("intented", true);
        editor.apply();
        //ambient = new Intent(this, AmbientMediaPlayer.class);*/

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.game_name_card_popup);
        namecard.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                player_sounds.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.name_part_one_popup);
                text_one.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.name_part_two_popup);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.game_name_card_hide);

                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                namecard.setVisibility(View.INVISIBLE);
                                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_appear);
                                name_app.startAnimation(animation);
                                eye_card.startAnimation(animation);
                                button_start.startAnimation(animation);
                                button_config.startAnimation(animation);
                                button_info.startAnimation(animation);
                                //startService(ambient);
                                animation.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_icon_blinking);
                                        image_eye_inner.startAnimation(animation);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                name_app.setVisibility(View.VISIBLE);
                                eye_card.setVisibility(View.VISIBLE);
                                button_start.setVisibility(View.VISIBLE);
                                button_config.setVisibility(View.VISIBLE);
                                button_info.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        namecard.startAnimation(animation);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                text_two.startAnimation(animation);
                text_one.setVisibility(View.VISIBLE);
                text_two.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*editor.putBoolean("intented", true);
                editor.apply();*/
                Intent intent = new Intent(MenuActivity.this, ChaptersActivity.class);
                startActivity(intent);
            }
        });

        button_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onResume() {
        /*boolean intented = settings.getBoolean("intenteted", false);
        if (!intented) { // Если приложение разворачивается
            startService(ambient);
        } else {
            editor.putBoolean("intented", false);
            editor.apply();
        }*/
        super.onResume();
    }

    @Override
    protected void onPause() {
        /*boolean intented = settings.getBoolean("intenteted", false);
        if (!intented) { // Если приложение сворачивается
            stopService(ambient);
        }*/
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        //stopService(ambient);
        super.onDestroy();
    }

}