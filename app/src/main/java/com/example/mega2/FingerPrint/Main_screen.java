package com.example.mega2.FingerPrint;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Timer;

public class Main_screen extends AppCompatActivity {
    Db_Helper db_helper;
ImageButton ImageButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        this.getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        overridePendingTransition( R.anim.fade_in, R.anim.fade_out );
        setContentView( R.layout.activity_main );

        db_helper = new Db_Helper( this );
        ImageButton1 = (ImageButton) findViewById( R.id.imageButton );
        ImageButton1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent( getApplicationContext(), MFS100_final.class );
                    i.putExtra( "main", "main" );
                    i.setFlags( Intent.FLAG_ACTIVITY_NO_HISTORY );
                    startActivity( i );
                } catch (Exception e) {
                    Toast.makeText( getApplicationContext(), e.toString(), Toast.LENGTH_LONG ).show();
                }
            }
        } );
        Intent intent = getIntent();
        if (intent.hasExtra( "mainActivity_mfs100Test_dialog_ok" )) {
            MyPojo pojo = (MyPojo) intent.getExtras().get( "mainActivity_mfs100Test_dialog_ok" );
            assert pojo != null;
            String val1 = pojo.getUserid();
            String val2 = pojo.getName();
            String val3 = pojo.getHand();
            String val4 = pojo.getFinger();
            String val5 = pojo.getFile();
            launchDismissDlg( val1, val2, val3, val4, val5 );
        }
        if (getIntent().hasExtra( "mainActivity_mfs100Test_dialog" )) {
            String s = "NA";
            launchDismissDlg( s, s, s, s, s );
        }

    }

    private void launchDismissDlg(String val1, String val2, String val3, String val4, String val5) {
        View v = LayoutInflater.from( this ).inflate( R.layout.attendance_result, null );
        AlertDialog.Builder builder = new AlertDialog.Builder( Main_screen.this );
        builder.setView( v );
        AlertDialog dialog = builder.create();
        TextView id, name, hand, heading;
        ImageView img1, pic;
        LinearLayout linearLayout = (LinearLayout) v.findViewById( R.id.fial );
        RelativeLayout relativeLayout = (RelativeLayout) v.findViewById( R.id.rv1 );
        id = (TextView) v.findViewById( R.id.id1 );
        name = (TextView) v.findViewById( R.id.name );
        //heading = (TextView) v.findViewById( R.id.textView9 );
        try {
            if (val1.equals( "NA" )) {
                relativeLayout.setBackground( new ColorDrawable( Color.RED ) );
                Toast.makeText( this,"Finger Not Registered",Toast.LENGTH_SHORT ).show();
               // heading.setText( R.string.not_res );
                id.setText( R.string.invalid );
                id.setTextColor( Color.RED );
                id.setTextSize( 36 );
                id.setGravity( Gravity.CENTER_HORIZONTAL );
                name.setVisibility( View.INVISIBLE );
                linearLayout.setBackground( new ColorDrawable( Color.parseColor( "#EF9A9A" ) ) );

            } else {
                id.setText( String.format( "Roll No : %s", val1 ) );
                name.setText( String.format( "Name    : %s", val2 ) );

                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.BOTTOM;
                wmlp.x = 10;
                wmlp.y = 10;
                dialog.show();
            }
            Timer T = new Timer();
            T.schedule( new CloseDialogTimerTask( dialog ), 3000 );
        }
        catch (Exception e)
        {}
    }

    @Override
    public void onBackPressed() {
            Intent i =new Intent( Main_screen.this, Navigator.class);
            startActivity( i );
            super.onBackPressed();
        }
}
