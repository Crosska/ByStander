package com.crosska.bystander;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ChaptersActivity extends AppCompatActivity {

    EditText commandLine;
    CardView commandApplyButton;
    CardView ch1Cardview;
    CardView ch2Cardview;
    CardView ch3Cardview;
    CardView terminalCardview;
    CardView commandCardview;
    TextView terminal;
    ScrollView terminalScroller;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    MediaPlayer player_sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_chapters);
        player_sounds = MediaPlayer.create(this, R.raw.chapter_selected_sound);
        player_sounds.setLooping(false);
        player_sounds.setVolume(20, 20);
        commandLine = findViewById(R.id.command_edittext);
        commandApplyButton = findViewById(R.id.command_apply_button);
        terminal = findViewById(R.id.terminal_textview);
        terminalScroller = findViewById(R.id.terminal_scrollview);
        ch1Cardview = findViewById(R.id.chapter_1_cardview);
        ch2Cardview = findViewById(R.id.chapter_2_cardview);
        ch3Cardview = findViewById(R.id.chapter_3_cardview);
        terminalCardview = findViewById(R.id.terminal_cardview);
        commandCardview = findViewById(R.id.command_cardview);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ch1_popup);
        ch1Cardview.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ch2_popup);
        ch2Cardview.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ch3_popup);
        ch3Cardview.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_appear);
        terminalCardview.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_translation);
        commandCardview.startAnimation(animation);

       /* settings = getApplicationContext().getSharedPreferences("system_data", 0);
        editor = settings.edit();*/

        commandApplyButton.setOnClickListener(view -> {
            if (commandLine.getText().toString().equals("")) {
                commandApplyButton.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.apply_command_button_error));
            } else {
                commandApplyButton.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.apply_command_button_success));
                String terminal_text;
                String command = commandLine.getText().toString(); // Получение команды пользователя

                try {
                    String[] command_divided = command.split(" "); // Разделение команды по пробелу

                    terminal_text = terminal.getText().toString() + getString(R.string.user_prefix) + command; // Вывод команды пользователя
                    terminal.setText(terminal_text);

                    if (command_divided[0].equals("")) {
                        commandApplyButton.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.apply_command_button_error)); // Негативная анимация кнопки
                    } else if (command_divided[0].equals(getString(R.string.switch_terminal_help))) {
                        terminal_text = terminal.getText().toString() + getString(R.string.help_command); // Вывод справки команд
                        terminal.setText(terminal_text);
                    } else if (command_divided[0].equals(getString(R.string.switch_terminal_run))) {

                        if (command_divided[1].equals(getString(R.string.stage3_mutual_aid))) {
                            Toast.makeText(this, "Chapter 1", Toast.LENGTH_SHORT).show();
                            player_sounds.start();
                        } else if (command_divided[1].equals(getString(R.string.stage2_first_contact))) {
                            Toast.makeText(this, "Chapter 2", Toast.LENGTH_SHORT).show();
                            player_sounds.start();
                        } else if (command_divided[1].equals(getString(R.string.stage1_booting_up))) {
                            Toast.makeText(this, "Chapter 3", Toast.LENGTH_SHORT).show();
                            player_sounds.start();
                        } else {
                            terminal_text = terminal.getText().toString() + getString(R.string.app_mes_wrong_command); // Если вторая часть команды не подходит
                            terminal.setText(terminal_text);
                        }

                    } else if (command_divided[0].equals(getString(R.string.switch_terminal_clear))) {

                        if (command_divided[1].equals(getString(R.string.stage3_mutual_aid))) {
                            Toast.makeText(this, "Delete Chapter 1", Toast.LENGTH_SHORT).show();
                        } else if (command_divided[1].equals(getString(R.string.stage2_first_contact))) {
                            Toast.makeText(this, "Delete Chapter 2", Toast.LENGTH_SHORT).show();
                        } else if (command_divided[1].equals(getString(R.string.stage1_booting_up))) {
                            Toast.makeText(this, "Delete Chapter 3", Toast.LENGTH_SHORT).show();
                        } else {
                            terminal_text = terminal.getText().toString() + getString(R.string.app_mes_wrong_command); // Если вторая часть команды не подходит
                            terminal.setText(terminal_text);
                        }

                    } else {
                        terminal_text = terminal.getText().toString() + getString(R.string.app_mes_wrong_command); // Если первая часть команды не подходит
                        terminal.setText(terminal_text);
                    }
                } catch (Exception ex) {
                    terminal_text = terminal.getText().toString() + getString(R.string.app_mes_wrong_command); // Если много пробелов
                    terminal.setText(terminal_text);
                }

                commandLine.setText("");
                terminalScroller.post(() -> terminalScroller.fullScroll(terminalScroller.FOCUS_DOWN));

            }
        });

    }

    @Override
    protected void onResume() {
       /* boolean intented = settings.getBoolean("intenteted", true);
        if (!intented) { // Если приложение разворачивается
            startService(new Intent(this, AmbientMediaPlayer.class));
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
            stopService(new Intent(this, AmbientMediaPlayer.class));
        }*/
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        /*editor.putBoolean("intented", true);
        editor.apply();*/
        super.onDestroy();
    }

}