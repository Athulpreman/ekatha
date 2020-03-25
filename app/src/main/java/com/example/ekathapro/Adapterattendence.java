package com.example.ekathapro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Adapterattendence extends RecyclerView.Adapter<Adapterattendence.OwnerViewHolder>
{
    private ValueEventListener mCtx;
    private ArrayList<Memb> membs;
    Context context;
    DatabaseReference reference;

    Adapterattendence(Context context, ArrayList<Memb> itemList)
    {
        this.context = context;
        membs = itemList;
    }

    @NonNull
    @Override
    public OwnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater= LayoutInflater.from((Context) context);
        View view=layoutInflater.inflate(R.layout.attendencemarkcardview,null);
        return new OwnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OwnerViewHolder holder, final int position)
    {
        holder.t1.setText(membs.get(position).getMuser());
        holder.r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Toast.makeText(context, "present", Toast.LENGTH_SHORT).show();

                    reference= FirebaseDatabase.getInstance().getReference().child(membs.get(position).getMward()).child(membs.get(position).getMunitnum()).child("Member").child(membs.get(position).getMuser()).child("Attendance").child(holder.currentDate);
                    holder.attandanceClass.Date=holder.currentDate;
                    holder.attandanceClass.present=true;
                    reference.setValue(holder.attandanceClass);
                }



        });
        holder.r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                holder.r2.setChecked(true);
                holder.r1.setChecked(false);
                Toast.makeText(context, "absent", Toast.LENGTH_SHORT).show();

                    reference= FirebaseDatabase.getInstance().getReference().child(membs.get(position).getMward()).child(membs.get(position).getMunitnum()).child("Member").child(membs.get(position).getMuser()).child("Attendance").child(holder.currentDate);
                    holder.attandanceClass.Date=holder.currentDate;
                    holder.attandanceClass.present=false;
                    reference.setValue(holder.attandanceClass);
                }

        });
    }

    @Override
    public int getItemCount()

    {
        return membs.size();
    }

    class OwnerViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1;
        RadioButton r1,r2;
        RadioGroup radioGroup;
        AttandanceClass attandanceClass;
        String currentDate;


        public OwnerViewHolder(@NonNull View ownerView) {
            super(ownerView);
            t1=(TextView) ownerView.findViewById(R.id.unitno);
            radioGroup=(RadioGroup)ownerView.findViewById(R.id.mpAttandence);
            r1=(RadioButton)ownerView.findViewById(R.id.male);
            r2=(RadioButton)ownerView.findViewById(R.id.female);
            attandanceClass=new AttandanceClass();

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy,MM,dd");
            currentDate = df.format(c.getTime());

        }
    }
}
