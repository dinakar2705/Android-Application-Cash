package com.example.dinakarmc.cash;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Transfer extends AppCompatActivity {

    Database mydb;
    int sendbal, recbal, sendout, recout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        mydb = new Database(this);
        Transfer();

    }

    private static EditText amt, Rusername;
    private static Button Transfer;

    public void Transfer(){

        amt = (EditText)findViewById(R.id.amt);
        Rusername = (EditText) findViewById(R.id.Rusername);

        Transfer = (Button)findViewById(R.id.TransferB);

        Transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Rusername.getText().length() < 1) {
                    Rusername.setError("Please enter a valid username");
                } else if (amt.getText().length() < 1) {
                    amt.setError("Please enter a valid amount");
                } else {
                    sendbal = Integer.parseInt(getIntent().getStringExtra("bal")); // sender's balance
                    String Suname = getIntent().getStringExtra("urname");

                    Cursor res = mydb.getBal(Rusername.getText().toString());
                    res.moveToFirst();
                    String Runame = res.getString(1);
                    String recbalance = res.getString(4);
                    recbal = Integer.parseInt(recbalance);                      //receiver's balance
                    int amount = Integer.parseInt(amt.getText().toString());    //convert amount to int
                    recout = amount + recbal;

                    if (amount > sendbal)

                        Toast.makeText(Transfer.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();

                    else {
                        sendout = sendbal - amount;


                        String senderlost = Integer.toString(sendout);
                        String recgained = Integer.toString(recout);

                        boolean up1 = mydb.UpdateBal(senderlost, Suname);
                        boolean up2 = mydb.UpdateBal(recgained, Runame);

                        if (up1 == true) {
                            Toast.makeText(Transfer.this, "Amount is sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Transfer.this, "Amount is not sent", Toast.LENGTH_SHORT).show();
                        }

                        if (up2 == true) {
                            Toast.makeText(Transfer.this, "Receiver received the amount", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Transfer.this, "Receiver didnt receive the amount", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

        });

    }
}
