package com.example.dinakarmc.cash;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.IDNA;
import android.icu.text.IDNA.Info;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Database mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Register();
        LoggedIn();
        mydb = new Database(this);
    }


    private static EditText username, password;
    private static Button register, login;

    public void Register() {

        register = (Button) findViewById(R.id.Register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("com.example.dinakarmc.cash.Registration");
                startActivity(intent);

            }
        });

    }

    public void LoggedIn() {

        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);
        login = (Button) findViewById(R.id.Login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.getText().length() < 1 )
                    username.setError("Please enter a valid username");
                    else if (password.getText().length() < 1) {
                        password.setError("Please enter a valid password");
                        }

                else {
                        Cursor res = mydb.getUsername(username.getText().toString());
                            if(res.getCount()==0){
                                Toast.makeText(MainActivity.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                                 } else {
                                    res.moveToFirst();
                                    String name = res.getString(0);
                                    String urname = res.getString(1);
                                    String pwd = res.getString(3);
                                    String bal = res.getString(4);

                                        if(urname.equals(username.getText().toString()) && (pwd.equals(password.getText().toString()))) {

                                            Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent("com.example.dinakarmc.cash.Logged");
                                            intent.putExtra("got", name);
                                            intent.putExtra("del", urname);
                                            intent.putExtra("bal", bal);
                                            startActivity(intent);



                                        }else {

                                            Toast.makeText(MainActivity.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                                                }
                                        }
                    }

            }
        });

    }

}