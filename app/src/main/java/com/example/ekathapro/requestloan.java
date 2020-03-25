package com.example.ekathapro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Toast;

public class requestloan extends AppCompatActivity {

    EditText date,name,amount;
    String sd,sn,sa;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    Button reqloan;
    ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestloan);

        date=(EditText)findViewById(R.id.date);
        name=(EditText)findViewById(R.id.name);
        amount=(EditText)findViewById(R.id.amount1);
        b1=(Button)findViewById(R.id.memberlist);
        b2=(Button)findViewById(R.id.requestloan);
        b3=(Button)findViewById(R.id.viewattendance);
        b4=(Button)findViewById(R.id.viewthrift);
        b5=(Button)findViewById(R.id.viewexpense);
        b6=(Button)findViewById(R.id.loandetails);
        b7=(Button)findViewById(R.id.paymentinfo);
        b8=(Button)findViewById(R.id.complaints);
        b9=(Button)findViewById(R.id.privacy);


        reqloan=(Button)findViewById(R.id.loanreg);
        reqloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               sd=date.getText().toString();
               sn=name.getText().toString();
               sa=amount.getText().toString();
                if(sd.isEmpty())
                {
                    date.setError("enter date");
                    date.requestFocus();
                }
                else if(sn.isEmpty())
                {
                    name.setError("enter name");
                    name.requestFocus();
                }
                else if(sa.isEmpty())
                {
                    amount.setError("enter amount");
                    amount.requestFocus();
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),memlogged.class);
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
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),privacy.class);
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

}
