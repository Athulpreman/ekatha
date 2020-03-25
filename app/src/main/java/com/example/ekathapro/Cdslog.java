package com.example.ekathapro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cdslog extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    String cuse,cpas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdslog);
        e1=(EditText)findViewById(R.id.cusername);
        e2=(EditText)findViewById(R.id.cpass);
        b1=(Button)findViewById(R.id.cbtlogin);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cuse=e1.getText().toString().trim();
                cpas=e2.getText().toString().trim();
                if(cuse.equals("ekatha") && cpas.equals("12345"))
                {
                    Toast.makeText(getApplicationContext(),"Succesfully Login",Toast.LENGTH_SHORT).show();
                    Intent ob=new Intent(getApplicationContext(),Cdshome.class);
                    startActivity(ob);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"INCORRECT USERNAME OR PASSWORD",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
