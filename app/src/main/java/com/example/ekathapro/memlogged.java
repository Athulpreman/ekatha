package com.example.ekathapro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class memlogged extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    DatabaseReference refee;
    RecyclerView recyclerView;
    MemberAdapter adapter;
    ArrayList<Memb> list;
    String ward,unitno;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memlogged);

        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Memb>();

        SharedPreferences sharedPreferences=getSharedPreferences("Memlogin",MODE_PRIVATE);
        ward=sharedPreferences.getString("ward",null);
        unitno=sharedPreferences.getString("unitnum",null);

        Toast.makeText(this, ward, Toast.LENGTH_SHORT).show();

        b1=(Button)findViewById(R.id.memberlist);
        b2=(Button)findViewById(R.id.requestloan);
        b3=(Button)findViewById(R.id.viewattendance);
        b4=(Button)findViewById(R.id.viewthrift);
        b5=(Button)findViewById(R.id.viewexpense);
        b6=(Button)findViewById(R.id.loandetails);
        b7=(Button)findViewById(R.id.paymentinfo);
        b8=(Button)findViewById(R.id.complaints);
        b9=(Button)findViewById(R.id.privacy);

        refee= FirebaseDatabase.getInstance().getReference().child(ward).child(unitno).child("Member");
        refee.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot studentDatasnapshot : dataSnapshot.getChildren())
                {
                    Memb memb = studentDatasnapshot.getValue(Memb.class);
                    list.add(memb);
                }
                adapter = new MemberAdapter(memlogged.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(getApplicationContext(),"something wnt wrong",Toast.LENGTH_LONG).show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
