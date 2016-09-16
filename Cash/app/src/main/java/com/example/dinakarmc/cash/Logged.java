package com.example.dinakarmc.cash;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Logged extends AppCompatActivity {

    int i;
    Database mydb;
    private static TextView welcome, amount;
    private static Button delete, deposit, transfer;
    String q , a ;
    int b;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);
        mydb = new Database(this);
        wel();
        delete();
        deposit();
        Transfer();

    }


    public void wel(){

        welcome = (TextView) findViewById(R.id.Welcome);
        amount = (TextView) findViewById(R.id.Amount);
        String s = getIntent().getStringExtra("got"); //retrieve name
        welcome.setText("Welcome " + s +",");
        a = getIntent().getStringExtra("bal");    //retrieve balance
        //b = Integer.parseInt(a);
        //if(a.equals(null))
          //  amount.setText("70");
        //else
            amount.setText("$"+a);


    }

    public void delete(){
        delete = (Button)findViewById(R.id.Delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q = getIntent().getStringExtra("del");    //retrieve username
                AlertDialog.Builder alt = new AlertDialog.Builder(Logged.this);
                alt.setMessage("Do you want to delete your account?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                i = mydb.deleteUsername(q);
                                if(i > 0)
                                    Toast.makeText(Logged.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog alert = alt.create();
                alert.setTitle("Delete account");
                alert.show();




            }
        });

    }

    public void deposit(){

        deposit = (Button)findViewById(R.id.deposit);

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =  new Intent("com.example.dinakarmc.cash.Deposit");
                q = getIntent().getStringExtra("del");
                a = getIntent().getStringExtra("bal");
                intent.putExtra("bal", a);
                intent.putExtra("urname", q);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Transfer(){

        transfer = (Button)findViewById(R.id.Tran);
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("com.example.dinakarmc.cash.Transfer");
                q = getIntent().getStringExtra("del");
                a = getIntent().getStringExtra("bal");
                intent.putExtra("bal", a);
                intent.putExtra("urname", q);
                startActivity(intent);


            }
        });

    }

}
