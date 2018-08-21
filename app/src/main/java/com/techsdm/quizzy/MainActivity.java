package com.techsdm.quizzy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techsdm.quizzy.Model.User;

public class MainActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    EditText nameText,passwordText,emailText;
    Button signupButton;
    DatabaseReference dbUsers;
    android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.app_bar);
        toolbar.setTitle("Quizzy");
        setSupportActionBar(toolbar);

        signupButton=(Button)findViewById(R.id.btn_signup);
        nameText=(EditText)findViewById(R.id.input_name);
        passwordText=(EditText)findViewById(R.id.input_password);
        emailText=(EditText)findViewById(R.id.input_email);
        firebaseDatabase=FirebaseDatabase.getInstance();
        dbUsers=firebaseDatabase.getReference("Users");
    }
    public void sign_up_button(View view)
    {
        final User user=new User(nameText.getText().toString().trim(),emailText.getText().toString().trim(),passwordText.getText().toString().trim());
        dbUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user.getName()).exists())
                {
                    Toast.makeText(getApplicationContext(),"User Exisgts Already",Toast.LENGTH_SHORT).show();
                }
                else if(!(dataSnapshot.child(user.getName()).exists()))
                {
                    dbUsers.child(user.getName()).setValue(user);
                    Toast.makeText(getApplicationContext(),"User Registered",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),CategoryList.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void login_text_click(View view)
    {
        //final User user=new User(nameText.getText().toString().trim(),emailText.getText().toString().trim(),passwordText.getText().toString().trim());
        dbUsers=firebaseDatabase.getReference("Users");
        dbUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            final User user=new User();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(nameText.getText().toString().trim()).exists())
                {
                    User user_test=dataSnapshot.child(nameText.getText().toString().trim()).getValue(User.class);
                    if(user_test.email.equals(emailText.getText().toString().trim()))
                    {
                        if(user_test.password.equals(passwordText.getText().toString().trim()))
                        {
                            Toast.makeText(getApplicationContext(),"Login Done",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),CategoryList.class);
                            startActivity(intent);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
