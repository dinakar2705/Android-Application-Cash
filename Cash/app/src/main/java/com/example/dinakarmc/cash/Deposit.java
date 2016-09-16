package com.example.dinakarmc.cash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Deposit extends AppCompatActivity {

    String a;
    int bal, no;
    Database mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        mydb = new Database(this);
        deposit();
    }

    private static EditText amt;
    private static Button deposit;


    public void deposit(){

        amt = (EditText)findViewById(R.id.depamt);
        deposit = (Button)findViewById(R.id.depositb);


        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(amt.getText().length() < 1 ) {
                    amt.setError("Please enter a valid amount");
                } else {
                    bal = Integer.parseInt(getIntent().getStringExtra("bal"));
                    String urname = getIntent().getStringExtra("urname");
                    no = Integer.parseInt(amt.getText().toString());
                    int sum = bal + no;

                    String result = Integer.toString(sum);

                    boolean updated = mydb.UpdateBal(result, urname);

                    if (updated == true) {
                        Toast.makeText(Deposit.this, "Updated Balance, Please login again.", Toast.LENGTH_LONG).show();
                        finish();
                    } else
                        Toast.makeText(Deposit.this, "Balance not updated", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}
