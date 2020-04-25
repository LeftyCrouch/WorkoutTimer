package com.crouch.workouttimer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.crouch.workouttimer.R;

import java.text.SimpleDateFormat;

import models.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private Button mStartButton;
    private Button mStopButton;
    private Button mPauseButton;
    private GridLayout mGridLayout;
    private TextView mTimer;
    private MainViewModel mViewModel;
    private SimpleDateFormat mTimerFormat;

    public MainActivity() {
        mTimerFormat = new SimpleDateFormat("HH:mm:ss.SS");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set View Model
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // set up layout components
        mStartButton = findViewById(R.id.start_button);
        mStopButton = findViewById(R.id.stop_button);
        mPauseButton = findViewById(R.id.pause_button);
        mGridLayout = findViewById(R.id.grid_layout);
        mTimer = findViewById(R.id.timer);

        // set listeners
        setStartButtonListeners();
        setStopButtonListeners();

        // set observers
        mViewModel.getTimerValue().observe(this, timeDifference -> {
            updateTimer(timeDifference);
        });
    }

    private void setStartButtonListeners() {
        mStartButton.setOnClickListener(v -> onStartButton());
    }

    private void setStopButtonListeners() {
        mStopButton.setOnClickListener(v -> onStopButton());
    }

    private void onStartButton() {
        mStartButton.setVisibility(View.INVISIBLE);
        mGridLayout.setVisibility(View.VISIBLE);

        startTimer();
    }

    private void onStopButton() {
        mStartButton.setVisibility(View.VISIBLE);
        mGridLayout.setVisibility(View.GONE);
    }

    private void startTimer() {
        mViewModel.startTimer();
    }

    private void updateTimer(Long timeDifference) {
        Log.d("MainActivity", timeDifference.toString());
        String timerText = mTimerFormat.format(timeDifference);
        mTimer.setText(timerText);
    }
}
