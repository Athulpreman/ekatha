package com.example.ekathapro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Acceptingmem extends AppCompatActivity {
    DatabaseReference refee;
    RecyclerView recyclerView;
    Adaptermemapproval adaptermemapproval;
    ArrayList<Memb> list;
    String ward,unitnum;

    List<Memb> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptingmem);
        recyclerView=(RecyclerView)findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Memb>();



            refee = FirebaseDatabase.getInstance().getReference().child(ward).child(unitnum).child("Member");
            refee.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                    {
                        list.clear();
                        for (DataSnapshot studentatasnapshot : dataSnapshot.getChildren())
                        {
                            Memb memb = studentatasnapshot.getValue(Memb.class);
                          if(memb.status.equals(false))
                          {
                              list.add(memb);
                          }
                        }
                        adaptermemapproval = new Adaptermemapproval(Acceptingmem.this,list);
                        recyclerView.setAdapter(adaptermemapproval);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),"something wnt wrong",Toast.LENGTH_LONG).show();
                }
            });

    }
}
