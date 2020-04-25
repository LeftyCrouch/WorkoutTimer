package models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    private MutableLiveData<LocalDateTime> mStartTime;
    private MutableLiveData<Long> mTimerValue;

    public MainViewModel() {
        mStartTime = new MutableLiveData<>();
        mTimerValue = new MutableLiveData<>();

        mStartTime.setValue(null);
        mTimerValue.setValue(new Long(0));
    }

    public MutableLiveData<Long> getTimerValue() {
        return mTimerValue;
    }

    public void startTimer() {
        LocalDateTime startTime = LocalDateTime.now();
        if (mStartTime.getValue() == null) {
            mStartTime.setValue(startTime);
        }
        Duration duration = Duration.between(mStartTime.getValue(), startTime);
        mTimerValue.setValue(duration.toMillis());
        Log.d(TAG, "CHRIS WAS HERE");
        Log.d(TAG, Long.toString(duration.toHours()));
        Log.d(TAG, Long.toString(duration.toMinutes()));
        Log.d(TAG, Long.toString(duration.getSeconds()));
        Log.d(TAG, Long.toString(duration.toMillis()));
    }
}
