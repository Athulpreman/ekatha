package com.example.ekathapro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Memregi extends AppCompatActivity {
    EditText e2,e4,e5,e6,e7;
    Button b1,b2;
    Spinner e3,e8;
    Memb memb;
    String mna,mpl,mwa,mmo,mus,mpa,mre,muninum;
    DatabaseReference databaseReference,reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memregi);

        e2=(EditText)findViewById(R.id.mplce);
        e3=(Spinner)findViewById(R.id.mward);
        e4=(EditText)findViewById(R.id.mmob);
        e5=(EditText)findViewById(R.id.musername);
        e6=(EditText)findViewById(R.id.mpass);
        e7=(EditText)findViewById(R.id.mrepass);

        e8=(Spinner)findViewById(R.id.munitnum);

        b1=(Button)findViewById(R.id.mregis);
        b2=(Button)findViewById(R.id.msignin);
        memb=new Memb();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mpl=e2.getText().toString().trim();
                mwa=e3.getSelectedItem().toString().trim();
                mmo=e4.getText().toString().trim();
                mus=e5.getText().toString().trim();
                mpa=e6.getText().toString().trim();
                mre=e7.getText().toString().trim();
                muninum=e8.getSelectedItem().toString().trim();

                if(mpl.equals(""))
                {
                    e2.setError("Place is required");
                    e2.requestFocus();
                }
                else if(mmo.equals(""))
                {
                    e4.setError("Mobilenumber is required");
                    e4.requestFocus();
                }
                else if(mmo.length()<10||mmo.length()>10)
                {
                    e4.setError("Enter a valid mobile number");
                }
                else if(mus.equals(""))
                {
                    e5.setError("Username is required");
                    e5.requestFocus();
                }
                else if(mpa.equals(""))
                {
                    e6.setError("Password is required");
                    e6.requestFocus();
                }
                else if(mre.equals(""))
                {
                    e7.setError("RetypePassword is required");
                    e7.requestFocus();
                }
                else if(!mpa.equals(mre)) {
                    e7.setError("Password does not match");
                    e7.requestFocus();
                }
                else {

                    memb.setMplace(mpl);
                    memb.setMward(mwa);
                    memb.setMmobile(mmo);
                    memb.setMuser(mus);
                    memb.setMpassw(mpa);
                    memb.setMunitnum(muninum);

                    databaseReference= FirebaseDatabase.getInstance().getReference().child(mwa).child(muninum).child("Member").child(mus);
                    reference=FirebaseDatabase.getInstance().getReference().child(mwa).child(muninum);
                    final Query query=databaseReference.orderByChild("mname").equalTo(mus);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull final DataSnapshot dataSnapshot1)
                        {
                            Query query1=reference.orderByChild("umo").getRef();
                            query1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                {
                                    if (dataSnapshot.exists())
                                    {
                                        if (dataSnapshot1.exists())
                                        {
                                            e5.setError("user already exist");
                                            e5.requestFocus();
                                            Toast.makeText(getApplicationContext(),"unit member already Registered Try Sign in",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            databaseReference=FirebaseDatabase.getInstance().getReference().child(mwa).child(muninum).child("Member").child(mus);
                                            databaseReference.setValue(memb).addOnCompleteListener(new OnCompleteListener<Void>()
                                            {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task)
                                                {

                                                    Toast.makeText(getApplicationContext(),"Successfully registered Wait for the approval",Toast.LENGTH_SHORT).show();

                                                    e2.setText("");
                                                    e4.setText("");
                                                    e5.setText("");
                                                    e6.setText("");
                                                    e7.setText("");

                                                    Intent ob=new Intent(getApplicationContext(),Memafterregi.class);
                                                    startActivity(ob);
                                                }
                                            });
                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(Memregi.this, "No Unit President registerd", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ob=new Intent(getApplicationContext(),Memlog.class);
                startActivity(ob);
            }
        });

    }
}
