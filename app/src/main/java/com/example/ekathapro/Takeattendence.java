package com.example.ekathapro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Takeattendence extends AppCompatActivity {
    Button submit;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;

    Adapterattendence adapterattendence;
    ArrayList<Memb> liste;
    String ward,unitnum;
    List<Memb> itemList;
    String currentDate;
    TextView date;
    String wardNo,unitNo;
    DatabaseReference refee,refattand;
    RecyclerView recyclerView;
    AttandanceClass attandanceClass;
    Memb memb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeattendence);

        submit=(Button)findViewById(R.id.submitt);
        date=(TextView)findViewById(R.id.dateDisplay);

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

        SharedPreferences sharedPreferences=getSharedPreferences("unitpresi",MODE_PRIVATE);
        wardNo=sharedPreferences.getString("ward",null);
        unitNo=sharedPreferences.getString("unitnum",null);

         Calendar c = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("yyyy,MM,dd");
         currentDate = df.format(c.getTime());

        date.setText(currentDate);

        memb=new Memb();
        attandanceClass=new AttandanceClass();

        recyclerView=(RecyclerView)findViewById(R.id.recyclevv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        liste=new ArrayList<Memb>();

        refee= FirebaseDatabase.getInstance().getReference().child(wardNo).child(unitNo).child("Member");
        refee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                liste.clear();
                for (DataSnapshot studentDatasnapshot : dataSnapshot.getChildren())
                {
                    memb = studentDatasnapshot.getValue(Memb.class);
                    if (memb.status.equals(true))
                    {
                        refattand=refee.child(memb.getMuser()).child("Attendance").child(currentDate);
                        refattand.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                if (!dataSnapshot.exists())
                                {
                                    liste.add(memb);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
                adapterattendence = new Adapterattendence(Takeattendence.this,liste);
                recyclerView.setAdapter(adapterattendence);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(getApplicationContext(),"something wnt wrong", Toast.LENGTH_LONG).show();
            }
        });
        if (liste.isEmpty())
        {
            submit.setVisibility(View.VISIBLE);
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                refee= FirebaseDatabase.getInstance().getReference().child(wardNo).child(unitNo).child("Member");
                refee.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        liste.clear();
                        for (DataSnapshot studentDatasnapshot : dataSnapshot.getChildren())
                        {
                            memb = studentDatasnapshot.getValue(Memb.class);
                            if (memb.status.equals(true))
                            {
                                refattand=refee.child(memb.getMuser()).child("Attendance");
                                refattand.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                    {
                                        for (DataSnapshot ds:dataSnapshot.getChildren())
                                        {
                                            liste.add(memb);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                        adapterattendence = new Adapterattendence(Takeattendence.this,liste);
                        recyclerView.setAdapter(adapterattendence);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(getApplicationContext(),"something wnt wrong", Toast.LENGTH_LONG).show();
                    }
                });
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
