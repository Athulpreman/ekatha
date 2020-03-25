package com.example.ekathapro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;

public class Updatedeletemem extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    EditText plc,mob,user,user2;
    Button btnSer,btnDel,btnUpd;
    DatabaseReference reference,reference1;
    Memb memb;
    Spinner wa,no;
    String ward,unt,us;
    TextView t1,t2,t3;
    String unitpla1,mob1,user1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedeletemem);

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
        btnSer=(Button)findViewById( R.id.search );
        btnDel=(Button)findViewById( R.id.delete );
        btnUpd=(Button)findViewById( R.id.update );
        plc=(EditText)findViewById( R.id.plce );
        mob=(EditText)findViewById( R.id.mob);
        t1=(TextView) findViewById( R.id.p );
        t2=(TextView) findViewById( R.id.m );
        t3=(TextView) findViewById( R.id.u );
        wa=(Spinner) findViewById( R.id.ward);
        no=(Spinner) findViewById( R.id.unitno);

        user=(EditText)findViewById( R.id.user1);
        user2=(EditText)findViewById( R.id.usrnam );

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
            public void onClick(View view)
            {
                Intent ob=new Intent(getApplicationContext(),Presihome.class);
                startActivity(ob);
            }
        });


        btnSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ward=wa.getSelectedItem().toString().trim();
                unt=no.getSelectedItem().toString().trim();
                us=user.getText().toString().trim();

                reference= FirebaseDatabase.getInstance().getReference().child(ward).child(unt).child("Member");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            Memb memb=dataSnapshot1.getValue(Memb.class);
                            if (memb.muser.equals(us))
                            {

                                plc.setText(memb.mplace);
                                mob.setText( memb.mmobile);
                                user2.setText( memb.muser);

                                t1.setVisibility(View.VISIBLE);
                                t2.setVisibility(View.VISIBLE);
                                t3.setVisibility(View.VISIBLE);
                                plc.setVisibility(View.VISIBLE);
                                mob.setVisibility(View.VISIBLE);
                                user2.setVisibility(View.VISIBLE);
                                btnDel.setVisibility(View.VISIBLE);
                                btnUpd.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        btnUpd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ward=wa.getSelectedItem().toString().trim();
                unt=no.getSelectedItem().toString().trim();
                us=user.getText().toString().trim();

                unitpla1=plc.getText().toString().trim();
                mob1=mob.getText().toString().trim();
                user1=user2.getText().toString().trim();


                if (unitpla1.equals(""))
                {
                    plc.setError("cannot be empty");
                }
                else if (mob1.equals(""))
                {
                    mob.setError("cannot be empty");
                }
                else if (user1.equals(""))
                {
                    user2.setError("cannot be empty");
                }
                else
                {
                    reference1= FirebaseDatabase.getInstance().getReference().child(ward).child(unt).child("Member").child(us);
                    Query query=reference1.orderByChild("status").equalTo("true");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            dataSnapshot.getRef().child("mplace").setValue(unitpla1);
                            dataSnapshot.getRef().child("mmobile").setValue(mob1);
                            dataSnapshot.getRef().child("muser").setValue(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Updatedeletemem.this, "Updated", Toast.LENGTH_SHORT).show();


                                    plc.setText("");
                                    mob.setText("");
                                    user2.setText("");
                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        } );

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ward=wa.getSelectedItem().toString().trim();
                unt=no.getSelectedItem().toString().trim();

                us=user.getText().toString().trim();

                reference1= FirebaseDatabase.getInstance().getReference().child(ward).child(unt).child("Member");
                Query query=reference1.orderByChild("uuser").equalTo("us");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().removeValue();
                        Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();


                        plc.setText("");
                        mob.setText("");
                        user2.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



    }
}
