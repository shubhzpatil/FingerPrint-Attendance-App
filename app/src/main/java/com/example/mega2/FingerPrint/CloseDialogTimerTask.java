package com.example.mega2.FingerPrint;

import android.support.v7.app.AlertDialog;

import java.util.TimerTask;


public class CloseDialogTimerTask extends TimerTask {
    private AlertDialog ad;

    public CloseDialogTimerTask(AlertDialog ad)
    {
        this.ad=ad;
    }
    @Override
    public void run() {
        if(ad.isShowing())
        {
            ad.dismiss();
        }

    }
}
