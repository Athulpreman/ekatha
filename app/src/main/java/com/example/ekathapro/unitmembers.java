package com.example.ekathapro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.security.acl.Owner;
import java.util.ArrayList;

public class unitmembers extends AppCompatActivity {

    Spinner s1,s2;
    String wardn,unitn;
    DatabaseReference refee;
    RecyclerView recyclerView;
    MemberAdapter adapter;
    ArrayList<Memb> list;
    Button seemem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unitmembers);

        s1=(Spinner)findViewById(R.id.mward2);
        s2=(Spinner)findViewById(R.id.munit2);

        seemem=(Button)findViewById(R.id.seemembers);

        recyclerView=(RecyclerView)findViewById(R.id.recycle2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Memb>();


        seemem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.clear();

                wardn=s1.getSelectedItem().toString();
                unitn=s2.getSelectedItem().toString();

                refee= FirebaseDatabase.getInstance().getReference().child(wardn).child(unitn).child("Member");
                refee.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot studentDatasnapshot : dataSnapshot.getChildren())
                        {
                            Memb member = studentDatasnapshot.getValue(Memb.class);
                            if (member.status.equals(true))
                            {
                                list.add(member);
                            }
                        }
                        adapter = new MemberAdapter(unitmembers.this,list);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(getApplicationContext(),"something wnt wrong",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}
