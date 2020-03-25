package com.example.ekathapro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences=getSharedPreferences("Memlogin",MODE_PRIVATE);
        String string=sharedPreferences.getString("member",null);
        if(string!=null) {
            Intent inton = new Intent(getApplicationContext(), Presihome.class);
            startActivity(inton);
        }
        SharedPreferences sharedPreferences1=getSharedPreferences("unitpresi",MODE_PRIVATE);
        String presi=sharedPreferences1.getString("unitnum",null);
        if(presi!=null) {
            Intent inton = new Intent(getApplicationContext(), Presihome.class);
            startActivity(inton);
        }

        b1=(Button)findViewById(R.id.unitpres);
        b2=(Button)findViewById(R.id.mem);
        b4=(Button)findViewById(R.id.cds);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ob3=new Intent(getApplicationContext(),Cdslog.class);
                startActivity(ob3);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ob1=new Intent(getApplicationContext(),Memlog.class);
                startActivity(ob1);

            }


        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ob=new Intent(getApplicationContext(),Unitpresilog.class);
                startActivity(ob);
            }
        });
    }
}
