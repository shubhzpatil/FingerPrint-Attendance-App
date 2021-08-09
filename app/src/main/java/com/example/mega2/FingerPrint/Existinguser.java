package com.example.mega2.FingerPrint;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Existinguser extends AppCompatActivity {
   Db_Helper helper;
    RecyclerView recyclerView;
    EditText editText;
    ModifyAdapter MAdap;
    java.util.ArrayList ArrayList= new ArrayList();
    String value=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_existinguser);

        recyclerView = (RecyclerView) findViewById(R.id.recycler1);
        recyclerView.requestFocus();
        helper = new Db_Helper(Existinguser.this);
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        value = extras.getString("Report");
        ArrayList = helper.getAllData("");
        MAdap = new ModifyAdapter(this, ArrayList,value);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(MAdap);


        editText = (EditText) findViewById(R.id.search_bar11);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {  }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Existinguser.this.MAdap.getFilter().filter(charSequence);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        assert getSupportActionBar()!=null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onBackPressed() {
        Intent i =new Intent( Existinguser.this, Navigator.class);
        startActivity( i );
        super.onBackPressed();
    }

}