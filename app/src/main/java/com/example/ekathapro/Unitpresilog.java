package com.example.ekathapro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Unitpresilog extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    DatabaseReference reference;
    Unitpres unitpres;
    String use,pas;
    int i,m=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unitpresilog);
        e1=(EditText)findViewById(R.id.username);
        e2=(EditText)findViewById(R.id.pass);
        b1=(Button)findViewById(R.id.btlogin);
        b2=(Button)findViewById(R.id.regi);
        unitpres=new Unitpres();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                use=e1.getText().toString();
                pas=e2.getText().toString();

                for (i=1;i<19;i++)
                {
                    reference= FirebaseDatabase.getInstance().getReference().child(String.valueOf(i));
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (dataSnapshot.exists())
                            {
                                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                                {
                                    unitpres=snapshot.getValue(Unitpres.class);
                                    if (use.equals(unitpres.uus))
                                    {   m=0;
                                        if (unitpres.upa.equals(pas))
                                        {
                                            if (unitpres.status.equals(false))
                                            {
                                                Toast.makeText(Unitpresilog.this, "Account is not accepted by CDS precident", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(Unitpresilog.this,"Sucess" , Toast.LENGTH_SHORT).show();

                                                SharedPreferences.Editor editor=getSharedPreferences("unitpresi",MODE_PRIVATE).edit();
                                                editor.putString("ward", unitpres.uwa);
                                                editor.putString("unitnum", unitpres.uno);
                                                editor.commit();

                                                Intent intent=new Intent(getApplicationContext(),Presihome.class);
                                                startActivity(intent);
                                            }
                                        }
                                        else
                                        {
                                            Toast.makeText(Unitpresilog.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (m==1)
                {
                    Toast.makeText(Unitpresilog.this, "No user Found", Toast.LENGTH_SHORT).show();
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ob=new Intent(getApplicationContext(),Unitpresregi.class);
                startActivity(ob);

            }
        });
    }
}
