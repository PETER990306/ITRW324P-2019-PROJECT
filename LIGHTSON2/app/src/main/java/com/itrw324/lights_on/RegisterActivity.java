package com.itrw324.lights_on;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {
    Button btnReg;
    EditText productidText,emailText,nameText,cellphoneText,passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        productidText = findViewById(R.id.txtProduct);
        emailText  = findViewById(R.id.txtRegEmail);
        nameText = findViewById(R.id.txtCellphone);
        //cellphoneText = findViewById(R.id.txtProduct);
        passwordText = findViewById(R.id.txtRegPassword);

        btnReg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String productidtext = productidText.getText().toString();
                String email = emailText.getText().toString();
                String name = nameText.getText().toString();
                String password = passwordText.getText().toString();
                String type ="reg";
                BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
                backgroundTask.execute(type,productidtext,email,name,password);
            }


        });

    }

    public void openHomeActivity(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }


}
