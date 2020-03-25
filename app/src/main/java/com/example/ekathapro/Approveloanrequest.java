package com.example.ekathapro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Approveloanrequest extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approveloanrequest);

        b1=(Button)findViewById(R.id.memberrequest);
        b2=(Button)findViewById(R.id.updelmem);
        b3=(Button)findViewById(R.id.attandance);
        b4=(Button)findViewById(R.id.viewattendance);
        b5=(Button)findViewById(R.id.loanReq);
        b6=(Button)findViewById(R.id.borrowlist);
        b7=(Button)findViewById(R.id.expenses);
        b8=(Button)findViewById(R.id.paymentinfo);
        b9=(Button)findViewById(R.id.complaints);
        b10=(Button)findViewById(R.id.privacy);

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob9=new Intent(getApplicationContext(),Unitpresiprivacy.class);
                startActivity(ob9);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob8=new Intent(getApplicationContext(),Viewcomplaints.class);
                startActivity(ob8);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob7=new Intent(getApplicationContext(),Report.class);
                startActivity(ob7);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob6=new Intent(getApplicationContext(),Expenses.class);
                startActivity(ob6);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob5=new Intent(getApplicationContext(),Borrowerlist.class);
                startActivity(ob5);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob3=new Intent(getApplicationContext(),Unitthrift.class);
                startActivity(ob3);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent ob2=new Intent(getApplicationContext(),Takeattendence.class);
                startActivity(ob2);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob1=new Intent(getApplicationContext(),Updatedeletemem.class);
                startActivity(ob1);


            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob=new Intent(getApplicationContext(),Presihome.class);
                startActivity(ob);
            }
        });

    }
}
