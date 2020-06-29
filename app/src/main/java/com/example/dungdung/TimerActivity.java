package com.example.dungdung;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class TimerActivity extends Activity {
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    private long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer1);

        TextView bookName = findViewById(R.id.bookName);

        Intent secondIntent = getIntent();
        String bookNameText = secondIntent.getStringExtra("책 제목");

        bookName.setText(bookNameText);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());
    }
    public void startChronometer(View v) {
        if (!running) {
            Toast.makeText(TimerActivity.this, "타이머 시작!", Toast.LENGTH_SHORT).show();
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void pauseChronometer(View v) {
        if (running) {
            Toast.makeText(TimerActivity.this, "타이머 정지!", Toast.LENGTH_SHORT).show();
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
    public void resetChronometer(View v) {
        Toast.makeText(TimerActivity.this, "읽기를 종료합니다.", Toast.LENGTH_SHORT).show();
        time=SystemClock.elapsedRealtime() - chronometer.getBase();
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
        finish();
    }
}