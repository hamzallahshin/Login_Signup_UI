package com.hamza.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    Button loginButton, createAccountButton;
    TextInputLayout username, password;

    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.GoButton);
        createAccountButton = findViewById(R.id.createAccountButton);
        username = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private boolean UserNameValidate() {
        String value = username.getEditText().getText().toString();
        if (value.isEmpty()) {
            username.setError("Username can not be empty ");
            username.setErrorEnabled(false);
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private boolean PasswordValidate() {
        String value = password.getEditText().getText().toString();
        if (value.isEmpty()) {
            password.setError("Password can not be empty ");
            password.setErrorEnabled(false);

            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }

    public void LoginUser(View view) {

        Toast.makeText(this, "hiiii", Toast.LENGTH_SHORT).show();
        if (!UserNameValidate() | !PasswordValidate()){
            return;
        }
        else{

            isUser();
        }

    }

    private void isUser() {

        FirebaseDatabase.getInstance().getReference();
           final String userEnteredUsername = username.getEditText().getText().toString().trim();
           final String userEnteredPassword = password.getEditText().getText().toString().trim();

           Query checkUser = reference.child("users").child("username").equalTo(userEnteredUsername);
           checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {

                   if (snapshot.exists()) {
                       username.setError(null);
                       username.setErrorEnabled(false);
                       String passwordFromDB=snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                       Toast.makeText(LoginActivity.this, "Error occur here ", Toast.LENGTH_SHORT).show();
                       if (passwordFromDB!=null && equals(userEnteredPassword)) {
                           password.setError(null);
                           password.setErrorEnabled(false);
                           Toast.makeText(LoginActivity.this, "Good Job", Toast.LENGTH_SHORT).show();


                           //      String userNameDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                           //    String emailNameDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                           //  String PhoneNoDB = snapshot.child(userEnteredUsername).child("PhoneNo ").getValue(String.class);

//                           //Intent intent = new Intent(getApplicationContext(), UserProfile.class);
//                           intent.putExtra("name", userNameDB);
//                           intent.putExtra("password",userEnteredPassword);
//                           intent.putExtra("email", emailNameDB);
//                           intent.putExtra("Phone", PhoneNoDB);
//
//                           startActivity(intent);
                           }

                       else
                           password.setError("Wrong Password");
                           password.requestFocus();
                   }
                   else {
                       username.setError("NO such User Exist");
                       username.requestFocus();
                   }
                       return;
                   }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
       }
}