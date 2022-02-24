package com.hamza.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextInputLayout username,email,password,phoneNO;
    Button alreadytextButtton , goButton;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alreadytextButtton = findViewById(R.id.alreadyAccountButton);
        goButton = findViewById(R.id.GoButton);
        username = findViewById(R.id.usernameText);
        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        phoneNO = findViewById(R.id.phoneText);


        alreadytextButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

        private boolean UserNameValidate() {
            String value = username.getEditText().getText().toString();
            if (value.isEmpty()) {
                username.setError("Username can not be empty ");
                return false;
            } else {
                username.setError(null);
                return true;
            }
        }
        private boolean PasswordValidate(){
            String value = password.getEditText().getText().toString();
            if (value.isEmpty()) {
                password.setError("Username can not be empty ");
                return false;
            } else {
                password.setError(null);
                return true;
            }

        }
        private boolean EmailValidate(){
        String value = email.getEditText().getText().toString();
        if (value.isEmpty()) {
            email.setError("Email error");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }
        private boolean PhoneNoValidate(){
        String value = phoneNO.getEditText().getText().toString();
        if (value.isEmpty()) {
            phoneNO.setError("Username can not be empty ");
            return false;
        } else {
            phoneNO.setError(null);
            return true;
        }

    }


    public void RegisterUsers(View view) {
        if( !UserNameValidate() | !PasswordValidate() | !PhoneNoValidate() |!EmailValidate() ){
            return;
        }
        //database reference code
        reference = FirebaseDatabase.getInstance().getReference("users");
        rootnode = FirebaseDatabase.getInstance();

        //getting all the values
        String getusername = username.getEditText().getText().toString();
        String getuserEmail = email.getEditText().getText().toString();
        String getUserPassword = password.getEditText().getText().toString();
        String getUserPhone = phoneNO.getEditText().getText().toString();


        usersclass usersclass = new usersclass(getusername, getuserEmail, getUserPassword,getUserPhone);

        reference.child(getUserPhone).setValue(usersclass);



        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);


    }
}