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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Unitpresregi extends AppCompatActivity {
    EditText e2,e3,e5,e6,e7,e8;
    Button b1,b2;
    Spinner e4,e1;
    Unitpres unitpres;
    String no,na,pl,wa,mo,us,pa,re,n,n1,p,w,m,u,p1,p2;
    DatabaseReference databaseReference,ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unitpresregi);

        e1=(Spinner) findViewById(R.id.unitnumb);

        e2=(EditText)findViewById(R.id.unitnam);
        e3=(EditText)findViewById(R.id.plce);
        e4=(Spinner)findViewById(R.id.ward);
        e5=(EditText)findViewById(R.id.mob);
        e6=(EditText)findViewById(R.id.username);
        e7=(EditText)findViewById(R.id.pass);
        e8=(EditText)findViewById(R.id.repass);
        b1=(Button)findViewById(R.id.regis);
        b2=(Button)findViewById(R.id.signin);

        unitpres=new Unitpres();



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no=e1.getSelectedItem().toString().trim();
                na=e2.getText().toString().trim();
                pl=e3.getText().toString().trim();
                wa=e4.getSelectedItem().toString().trim();
                mo=e5.getText().toString().trim();
                us=e6.getText().toString().trim();
                pa=e7.getText().toString().trim();
                re=e8.getText().toString().trim();


                if(na.equals(""))
                {
                    e2.setError("Unitname is required");
                    e2.requestFocus();
                }
                else if(pl.equals(""))
                {
                    e3.setError("Place is required");
                    e3.requestFocus();
                }
                else if(mo.equals(""))
                {
                    e5.setError("Mobilenumber is required");
                    e5.requestFocus();
                }
                else if(us.equals(""))
                {
                    e6.setError("Username is required");
                    e6.requestFocus();
                }
                else if(pa.equals(""))
                {
                    e7.setError("Password is required");
                    e7.requestFocus();
                }
                else if(re.equals(""))
                {
                    e8.setError("RetypePassword is required");
                    e8.requestFocus();
                }
                else if(!pa.equals(re)) {
                    e8.setError("Password does not match");
                    e8.requestFocus();
                }
                else {
                    databaseReference=FirebaseDatabase.getInstance().getReference().child(wa);

                    final Query query=databaseReference.orderByChild("uno").equalTo(no);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists())
                            {
                                Toast.makeText(getApplicationContext(),"unit already Registered Try Sign in",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                ref=databaseReference.orderByChild("uno").equalTo(no).getRef();
                                unitpres.setUno(no);
                                unitpres.setUna(na);
                                unitpres.setUpl(pl);
                                unitpres.setUwa(wa);
                                unitpres.setUmo(mo);
                                unitpres.setUus(us);
                                unitpres.setUpa(pa);


                                databaseReference=FirebaseDatabase.getInstance().getReference().child(wa).child(no);
                                databaseReference.setValue(unitpres).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        Toast.makeText(getApplicationContext(), "Sucessfull", Toast.LENGTH_SHORT).show();

                                        e2.setText("");
                                        e3.setText("");
                                        e5.setText("");
                                        e6.setText("");
                                        e7.setText("");
                                        e8.setText("");

                                        Intent intent=new Intent(getApplicationContext(),Presiiafterregi.class);
                                        startActivity(intent);


                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ob=new Intent(getApplicationContext(),Unitpresilog.class);
                startActivity(ob);
            }
        });
    }
}
