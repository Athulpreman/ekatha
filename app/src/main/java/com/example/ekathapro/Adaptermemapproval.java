package com.example.ekathapro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Adaptermemapproval extends RecyclerView.Adapter<Adaptermemapproval.MembViewHolder>
{
    private ValueEventListener mCtx;
    private ArrayList<Memb> memb;
    Context context;
    DatabaseReference reference,reference2;

    Adaptermemapproval(Context context, ArrayList<Memb> itemList)
    {
        this.context = context;
        memb= itemList;
    }

    @NonNull
    @Override
    public MembViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from((Context) context);
        View view=layoutInflater.inflate(R.layout.memberrequestcardview,null);
        return new MembViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MembViewHolder holder, final int position)
    {
        holder.t1.setText(memb.get(position).getMunitnum());
        holder.t3.setText(memb.get(position).getMuser());
        holder.t4.setText(memb.get(position).getMplace());
        holder.t5.setText(memb.get(position).getMward());
        holder.t6.setText(memb.get(position).getMmobile());

        reference= FirebaseDatabase.getInstance().getReference().child(memb.get(position).getMward()).child(memb.get(position).getMunitnum());


        holder.button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                reference2=FirebaseDatabase.getInstance().getReference().child(memb.get(position).getMward()).child(memb.get(position).getMunitnum()).child("Member");
                Query query=reference2.orderByChild("mmobile").equalTo(memb.get(position).getMmobile());
                query.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot:dataSnapshot.getChildren())
                        {
                            snapshot.getRef().child("status").setValue(true);
                            Toast.makeText(context, memb.get(position).getMunitnum()+" Has accepted", Toast.LENGTH_SHORT).show();
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
        return memb.size();
    }

    class MembViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t3,t4,t5,t6;
        Button button;

        public MembViewHolder(@NonNull View memView) {
            super(memView);
            t1=(TextView)memView.findViewById(R.id.unitno);
            t3=(TextView)memView.findViewById(R.id.username);
            t4=(TextView)memView.findViewById(R.id.plce);
            t5=(TextView)memView.findViewById(R.id.ward);
            t6=(TextView)memView.findViewById(R.id.mob);
            button=(Button)memView.findViewById(R.id.appro);

        }
    }
}



