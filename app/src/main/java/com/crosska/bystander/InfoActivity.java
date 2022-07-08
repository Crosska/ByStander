package com.crosska.bystander;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;

import java.util.Objects;

public class InfoActivity extends AppCompatActivity {

    CardView back_button;
    CardView github_button;
    CardView email_button;
    CardView logo_card;
    CardView devname_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        logo_card = findViewById(R.id.info_activity_logo_cardview);
        devname_card = findViewById(R.id.info_activity_devname_cardview);
        back_button = findViewById(R.id.info_activity_back_cardview);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        github_button = findViewById(R.id.info_activity_github_cardview);
        github_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Crosska"));
                startActivity(browserIntent);
            }
        });
        email_button = findViewById(R.id.info_activity_email_cardview);
        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"musical.vg@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "");
                email.putExtra(Intent.EXTRA_TEXT, "");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, getString(R.string.info_activity_mail_question)));
            }
        });
        devname_card.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.devname_card_popup));
        logo_card.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_card_popup));
        back_button.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_translation));
        github_button.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ch1_popup));
        email_button.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ch2_popup));
    }
}