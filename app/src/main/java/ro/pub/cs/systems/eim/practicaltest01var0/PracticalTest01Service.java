package ro.pub.cs.systems.eim.practicaltest01var0;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PracticalTest01Service extends Service {
    private ProcessingThread thread = null;

    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String firstNumber = intent.getStringExtra("firstNumber");
        String secondNumber = intent.getStringExtra("secondNumber");
//        thread = new ProcessingThread(this, firstNumber, secondNumber);
//        thread.start();
        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        thread.stopThread();
    }
}
