package ro.pub.cs.systems.eim.practicaltest01var0;

import android.content.Context;
import android.content.Intent;

import java.util.Date;

public class ProcessingThread extends Thread{
    private boolean isRunning = true;
    private Context context = null;

    private double arithmeticMean;

    private double geometricMean;
    public ProcessingThread(Context context, String firstNumber, String secondNumber) {
        this.context = context;
        arithmeticMean = (double) (Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber)) / 2;
        geometricMean = Math.sqrt(Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber));
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        //intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
        intent.setAction("ro.pub.cs.systems.eim.practicaltest01.arithmeticmean");
        intent.putExtra("message",
                new Date(System.currentTimeMillis()) + " " + arithmeticMean + " " + geometricMean);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
