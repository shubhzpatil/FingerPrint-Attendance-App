package com.example.mega2.FingerPrint;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
 class MyCustomToast
    {
        static  void makeToast_mfs(Context context, String message)
        {
            Toast toast=new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,Gravity.START,300);
            View appear=LayoutInflater.from(context).inflate(R.layout.toast,null);
            TextView textView=(TextView)appear.findViewById(R.id.textViewt);
            textView.setText(message);
            toast.setView(appear);
            toast.show();

        }
    }


