package com.example.ekathapro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Memlog extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    String userna,passna,ward,unit;
    Spinner s1,s2;
    Memb mem;
    DatabaseReference refer;
    int f=0;
    int i,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memlog);

        e1=(EditText)findViewById(R.id.musername);
        e2=(EditText)findViewById(R.id.mpass);
        b1=(Button)findViewById(R.id.mbtlogin);
        b2=(Button)findViewById(R.id.mregi);

        mem=new Memb();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userna=e1.getText().toString().trim();
                passna=e2.getText().toString().trim();
                if(userna.isEmpty())
                {
                    e1.setError("enter username");
                    e1.requestFocus();
                }
                else if(passna.isEmpty())
                {
                    e2.setError("enter password");
                    e2.requestFocus();
                }
                else
                {
                    for (i=1;i<20;i++)
                    {
                        for (j=1;j<20;j++)
                        {
                            refer= FirebaseDatabase.getInstance().getReference().child(String.valueOf(i)).child(String.valueOf(j)).child("Member");

                            Query query=refer.orderByChild("muser").equalTo(userna);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                                    {
                                        mem=snapshot.getValue(Memb.class);
                                        String pass=mem.mpassw;
                                        if(pass.equals(passna)&&userna.equalsIgnoreCase(mem.muser))
                                        {

                                            Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();

                                            SharedPreferences.Editor editor=getSharedPreferences("Memlogin",MODE_PRIVATE).edit();
                                            editor.putString("member",userna);
                                            editor.putString("ward", mem.mward);
                                            editor.putString("unitnum", mem.munitnum);
                                            editor.commit();

                                            Intent inte = new Intent(getApplicationContext(), memlogged.class);
                                            startActivity(inte);

                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(), "Incorrect password or username", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ob=new Intent(getApplicationContext(),Memregi.class);
                startActivity(ob);
            }
        });

    }
}
