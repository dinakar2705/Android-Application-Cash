package com.example.dinakarmc.cash;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    Database mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mydb = new Database(this);
        register();
    }


    private static EditText name, username, email, password, confpass;
    private static Button register;


    public void register() {

        name = (EditText) findViewById(R.id.Name);
        username = (EditText) findViewById(R.id.UsernameReg);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.PasswordReg);
        confpass = (EditText) findViewById(R.id.ConfPass);
        register = (Button) findViewById(R.id.Regbut);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Cursor res = mydb.getUsername(username.getText().toString());
                Cursor eres = mydb.getEmail(email.getText().toString());

       /*         if (res.getCount() != 0 ) {

                    Toast.makeText(Registration.this, "Username taken", Toast.LENGTH_SHORT).show();
                }
                else if ( eres.getCount() != 0){
                    Toast.makeText(Registration.this, "Email is incorrect!", Toast.LENGTH_SHORT).show();
                }
*/
                     if (name.getText().length() < 1)
                        name.setError("Please enter a valid name");

                    else if (username.getText().length() < 1) {
                        username.setError("Please enter a valid username");
                    } else if (email.getText().length() < 1) {
                        email.setError("Please enter a valid email");
                    } else if (password.getText().length() < 1) {
                        password.setError("Please enter a valid password");
                    } else if (confpass.getText().length() < 1) {
                        confpass.setError("Please enter a valid password");

                    } else if (!(password.getText().toString()).equals(confpass.getText().toString())) {
                        confpass.setError("Passwords do not match");

                    } else if (res.moveToFirst() == true) {
                        Toast.makeText(Registration.this, "Username taken, Please choose another username", Toast.LENGTH_LONG).show();

                    } else if (eres.moveToFirst() == true) {
                        Toast.makeText(Registration.this, "Duplicate Email, Please check your email ID", Toast.LENGTH_LONG).show();

                    } else {
                        input();

                    }


            }


        });

    }

    public void input() {

        boolean isInserted;
        //int b = 0;
        //String a = Integer.toString(b);
        String a = String.valueOf(0);
        isInserted = mydb.insertData(name.getText().toString(), username.getText().toString(), email.getText().toString(), password.getText().toString(), a);

        if (isInserted == true) {

            Toast.makeText(Registration.this, "Inserted", Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(Registration.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
        }

    }
}