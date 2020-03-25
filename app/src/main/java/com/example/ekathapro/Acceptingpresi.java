package com.example.ekathapro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class  Acceptingpresi extends AppCompatActivity {
    DatabaseReference refee;
    RecyclerView recyclerView;
    Adapterpresiapproval adapterpresiapproval;
    ArrayList<Unitpres> list;

    List<Unitpres> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptingpresi);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Unitpres>();

        for(int i=1;i<20;i++){

            refee= FirebaseDatabase.getInstance().getReference().child(String.valueOf(i));
            refee.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if (dataSnapshot.exists())
                    {

                        for (DataSnapshot studentDatasnapshot : dataSnapshot.getChildren())
                        {
                            Unitpres unitpres = studentDatasnapshot.getValue(Unitpres.class);
                            if(unitpres.status.equals(false))
                            {
                                list.add(unitpres);
                            }
                        }
                        adapterpresiapproval = new Adapterpresiapproval(Acceptingpresi.this,list);
                        recyclerView.setAdapter(adapterpresiapproval);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),"something wnt wrong",Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}
