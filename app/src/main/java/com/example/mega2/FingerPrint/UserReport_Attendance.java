package com.example.mega2.FingerPrint;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserReport_Attendance extends AppCompatActivity {
    Db_Helper pdba;
    ArrayList arrayList = new ArrayList();
    RecyclerView recyclerView;
    EditText editText;
    Report_adapter adapter1;
    TextView date;
    Button rep;
    MyPojo p1=new MyPojo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_user_report);
        rep=(Button)findViewById( R.id.button5 );

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.requestFocus();
        editText= (EditText) findViewById(R.id.search_bar1);
        date = (TextView) findViewById(R.id.textView6);
        String DateStr=new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
        date.setText(DateStr);

        String s1=p1.getUserid();
        String s2=p1.getName();
        String s3=p1.getSync();

        final   String str1="Roll No            "+"Name          "+DateStr+"\n"+
                s1+s2+s3;
        rep.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                createPdf( "Attendance Report", str1 );
            }
        } );
        try {
            pdba = new Db_Helper(this);
            arrayList = pdba.getAllData_report();
            adapter1 = new Report_adapter(this, arrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter1);
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {  }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                UserReport_Attendance.this.adapter1.getFilter().filter(charSequence);
            }
            @Override
            public void afterTextChanged(Editable editable) {  }
        });
        assert getSupportActionBar()!=null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf(String title, String description) {
        PdfDocument document = new PdfDocument();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder( 300, 600, 1 ).create();
        PdfDocument.Page page = document.startPage( pageInfo );

        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor( Color.BLACK );
        canvas.drawText( title, 20, 40, paint );
        canvas.drawText( description, 20, 60, paint );
        document.finishPage( page );
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/mypdf/";
        File file = new File( directory_path );
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path + title + ".pdf";
        File filePath = new File( targetPdf );
        try {
            document.writeTo( new FileOutputStream( filePath ) );
            Toast.makeText( this, "Done", Toast.LENGTH_LONG ).show();
        } catch (IOException e) {
            Log.e( "main", "error " + e.toString() );
            Toast.makeText( this, "Something wrong: " + e.toString(),
                    Toast.LENGTH_LONG ).show();
        }
        document.close();
    }
    @Override
    public void onBackPressed() {
        Intent i =new Intent( UserReport_Attendance.this, Navigator.class);
        startActivity( i );
        super.onBackPressed();
    }
}

