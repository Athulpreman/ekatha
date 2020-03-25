package com.example.ekathapro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapterpresiapproval extends RecyclerView.Adapter<Adapterpresiapproval.PresiViewHolder>
{
    private ValueEventListener mCtx;
    private ArrayList<Unitpres> unitpres;
    Context context;
    DatabaseReference reference;

    public Adapterpresiapproval(Context context, ArrayList<Unitpres> itemList)
    {
        this.context = context;
        unitpres = itemList;
    }



    @NonNull
    @Override
    public PresiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from((Context) context);
        View view=layoutInflater.inflate(R.layout.presirequestcardview,null);
        return new PresiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PresiViewHolder holder, final int position)
    {
        holder.t1.setText(unitpres.get(position).getUno());
        holder.t2.setText(unitpres.get(position).getUna());
        holder.t3.setText(unitpres.get(position).getUus());
        holder.t4.setText(unitpres.get(position).getUpl());
        holder.t5.setText(unitpres.get(position).getUwa());
        holder.t6.setText(unitpres.get(position).getUmo());

        reference= FirebaseDatabase.getInstance().getReference().child(unitpres.get(position).getUwa());


        holder.button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Query query=reference.orderByChild("uno").equalTo(unitpres.get(position).getUno());
                query.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot:dataSnapshot.getChildren())
                        {
                            snapshot.getRef().child("status").setValue(true);
                            Toast.makeText(context, unitpres.get(position).getUno()+" Has accepted", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(context, "Error....!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return unitpres.size();
    }

    class PresiViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3,t4,t5,t6;
        Button button;

        public PresiViewHolder(@NonNull View presiView) {
            super(presiView);
            t1=(TextView)presiView.findViewById(R.id.unitno);
            t2=(TextView)presiView.findViewById(R.id.unitnam);
            t3=(TextView)presiView.findViewById(R.id.username);
            t4=(TextView)presiView.findViewById(R.id.plce);
            t5=(TextView)presiView.findViewById(R.id.ward);
            t6=(TextView)presiView.findViewById(R.id.mob);
            button=(Button)presiView.findViewById(R.id.appro);

        }
    }
}


