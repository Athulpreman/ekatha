package com.example.ekathapro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Cdshome extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdshome);

        b1=(Button)findViewById(R.id.presireq);
        b2=(Button)findViewById(R.id.unitmembers);
        b3=(Button)findViewById(R.id.unitexpense);
        b4=(Button)findViewById(R.id.unitreport);
        b6=(Button)findViewById(R.id.adduppr);
        b5=(Button)findViewById(R.id.logout3);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob=new Intent(getApplicationContext(),Updatedeletepresi.class);
                startActivity(ob);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent ob=new Intent(getApplicationContext(),Acceptingpresi.class);
                startActivity(ob);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ob=new Intent(getApplicationContext(),unitmembers.class);
                startActivity(ob);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ob=new Intent(getApplicationContext(),unitexpenses.class);
                startActivity(ob);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ob=new Intent(getApplicationContext(),unitreport.class);
                startActivity(ob);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ob=new Intent(getApplicationContext(),Cdslog.class);
                startActivity(ob);
            }
        });

    }
    Toast backToast;
    long backpress;


    @Override
    public void onBackPressed()
    {
        if (backpress+2000>System.currentTimeMillis())
        {
            backToast.cancel();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else
        {
            backToast=Toast.makeText(getApplicationContext(), "Press again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backpress=System.currentTimeMillis();
    }
}
