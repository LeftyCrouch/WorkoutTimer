package com.crouch.workouttimer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.crouch.workouttimer.CrTimer;
import com.crouch.workouttimer.R;

import java.text.SimpleDateFormat;

import com.crouch.workouttimer.models.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private Button mStartButton;
    private Button mStopButton;
    private Button mPauseButton;
    private GridLayout mGridLayout;
    private TextView mTimerTextView;
    private MainViewModel mViewModel;
    private SimpleDateFormat mTimerFormat;
    private Runnable runTimer;

    private CrTimer mTimer;

    public MainActivity() {
        mTimerFormat = new SimpleDateFormat("HH:mm:ss.SS");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set View Model
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mTimer = new CrTimer();

        // set up layout components
        mStartButton = findViewById(R.id.start_button);
        mStopButton = findViewById(R.id.stop_button);
        mPauseButton = findViewById(R.id.pause_button);
        mGridLayout = findViewById(R.id.grid_layout);
        mTimerTextView = findViewById(R.id.timer);

        // set listeners
        setStartButtonListeners();
        setPauseButtonListeners();
        setStopButtonListeners();

        // set observers
        //mViewModel.getTimerValue().observe(this, timeDifference -> {
        //    updateTimer(timeDifference);
        //});

        Handler handler = new Handler();
        handler.post(runTimer = new Runnable() {
            @Override
            public void run() {
                updateTimer();

                handler.postDelayed(runTimer, 1);
            }
        });
    }

    private void setStartButtonListeners() {
        mStartButton.setOnClickListener(v -> onStartButton());
    }

    private void setPauseButtonListeners() {
        mPauseButton.setOnClickListener(v -> onPauseButton());
    }

    private void setStopButtonListeners() {
        mStopButton.setOnClickListener(v -> onStopButton());
    }

    private void onStartButton() {
        mStartButton.setVisibility(View.INVISIBLE);
        mGridLayout.setVisibility(View.VISIBLE);

        startTimer();
    }

    private void onPauseButton() {
        mTimer.pauseTimer();
        mStartButton.setVisibility(View.VISIBLE);
        mGridLayout.setVisibility(View.GONE);
    }

    private void onStopButton() {
        mStartButton.setVisibility(View.VISIBLE);
        mGridLayout.setVisibility(View.GONE);
    }

    private void startTimer() {
        mTimer.startTimer();
    }

    private void updateTimer() {
        String timerText;
        long timerValue = mTimer.getTimeValue();
        if (timerValue > 0) {
            timerText = mTimerFormat.format(timerValue);
        } else {
            timerText = "00:00:00.00";
        }
        mTimerTextView.setText(timerText);
    }
}
