package com.example.ekathapro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

public class privacy extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    Button logoutmem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        b1=(Button)findViewById(R.id.memberlist);
        b2=(Button)findViewById(R.id.requestloan);
        b3=(Button)findViewById(R.id.viewattendance);
        b4=(Button)findViewById(R.id.viewthrift);
        b5=(Button)findViewById(R.id.viewexpense);
        b6=(Button)findViewById(R.id.loandetails);
        b7=(Button)findViewById(R.id.paymentinfo);
        b8=(Button)findViewById(R.id.complaints);
        b9=(Button)findViewById(R.id.privacy);

        logoutmem=(Button)findViewById(R.id.logout2);

        logoutmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor=getSharedPreferences("Memlogin",MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();

                Intent intenn=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intenn);
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),memlogged.class);
                startActivity(inten);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),requestloan.class);
                startActivity(inten);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),viewattendance.class);
                startActivity(inten);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),viewthrift.class);
                startActivity(inten);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),viewexpense.class);
                startActivity(inten);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),loandetails.class);
                startActivity(inten);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),paymentinfo.class);
                startActivity(inten);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),complaints.class);
                startActivity(inten);
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
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.horizondal);

        int x, y;
        x = b9.getLeft();
        y = b9.getTop();
        hsv.scrollTo(x, y);
    }
}
