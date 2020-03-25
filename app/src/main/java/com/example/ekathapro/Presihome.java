package com.example.ekathapro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Presihome extends AppCompatActivity {
Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;


    DatabaseReference refee;
    RecyclerView recyclerView;
    Adaptermemapproval adaptermemapproval;
    ArrayList<Memb> list;
    String ward,unitnum;
    List<Memb> itemList;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presihome);

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
                Intent ob4=new Intent(getApplicationContext(),Approveloanrequest.class);
                startActivity(ob4);
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
            public void onClick(View view) {
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

            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("unitpresi",MODE_PRIVATE);
        ward=sharedPreferences.getString("ward",null);
        unitnum=sharedPreferences.getString("unitnum",null);


        recyclerView=(RecyclerView)findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Memb>();
        b1=(Button)findViewById(R.id.updelmem);
        b2=(Button)findViewById(R.id.attandance);





        refee = FirebaseDatabase.getInstance().getReference().child(ward).child(unitnum).child("Member");
        refee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {

                    for (DataSnapshot studentatasnapshot : dataSnapshot.getChildren())
                    {
                        Memb memb = studentatasnapshot.getValue(Memb.class);
                        if (memb.status.equals(false))
                        {
                            list.add(memb);
                        }
                    }
                    adaptermemapproval = new Adaptermemapproval(Presihome.this,list);
                    recyclerView.setAdapter(adaptermemapproval);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"something wnt wrong",Toast.LENGTH_LONG).show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten=new Intent(getApplicationContext(),Updatedeletemem.class);
                startActivity(inten);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten=new Intent(getApplicationContext(),Updatedeletemem.class);
                startActivity(inten);
            }
        });


    }
}