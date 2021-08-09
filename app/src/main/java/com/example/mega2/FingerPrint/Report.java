package com.example.mega2.FingerPrint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Report extends AppCompatActivity {

    Button rep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_report );

        rep=(Button)findViewById( R.id.button5 );
        final   String str1="Roll No            "+"Name          ";
        rep.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                createPdf( "Attendance Report", str1 );
            }
        } );
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
}
