package com.example.ekathapro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder>
{
    private ValueEventListener mCtx;
    private ArrayList<Memb> memb;
    Context context;
    DatabaseReference reference;

    MemberAdapter(Context context, ArrayList<Memb> itemList)
    {
        this.context = context;
        memb = itemList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater= LayoutInflater.from((Context) context);
        View view=layoutInflater.inflate(R.layout.membercardview,null);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MemberViewHolder holder, final int position)
    {
        holder.t1.setText(memb.get(position).getMuser());
        holder.t2.setText(memb.get(position).getMmobile());


        reference= FirebaseDatabase.getInstance().getReference().child("Member");



        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(v.getContext(),memlogged.class);
                intent.putExtra("shopID",memb.get(position).getMname());
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount()

    {
        return memb.size();
    }

    class MemberViewHolder extends RecyclerView.ViewHolder
    {
        EditText t1,t2;

        public MemberViewHolder(@NonNull View ownerView) {
            super(ownerView);
            t1=(EditText) ownerView.findViewById(R.id.membername);
            t2=(EditText) ownerView.findViewById(R.id.mobilenumber);
        }
    }
}
