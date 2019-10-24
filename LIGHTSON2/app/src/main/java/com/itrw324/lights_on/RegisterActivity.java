package com.itrw324.lights_on;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
    private Button btnReg;
    private EditText passw,confpassw,produk,email,cellphone;
    Database usersdb;


    EditText productidText,emailText,nameText,cellphoneText,passwordText;
    Button register,login;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnReg = findViewById(R.id.btnRegister);
        passwordText = findViewById(R.id.txtRegPassword);
        nameText = findViewById(R.id.txtConfirmPassword);
        productidText = findViewById(R.id.txtProduct);
        emailText = findViewById(R.id.txtEmail);
        cellphoneText = findViewById(R.id.txtCellphone);


        connectionClass = new ConnectionClass();

        progressDialog = new ProgressDialog(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Doregister doregister = new Doregister();
                doregister.execute("");
            }
        });
    }


    public class Doregister extends AsyncTask<String,String,String>
    {


        String productstr =productidText.getText().toString();
        String emailstr =emailText.getText().toString();
        String namestr =nameText.getText().toString();
        String cellphonestr =cellphoneText.getText().toString();
        String passstr =passwordText.getText().toString();
        String z="";
        boolean isSuccess=false;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            if(productstr.trim().equals("")|| emailstr.trim().equals("") || namestr.trim().equals("")||  cellphonestr.trim().equals("")||  passstr.trim().equals(""))
                z = "Please enter all fields....";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query="insert into demoregister values('"+namestr+"','"+emailstr+"','"+passstr+"')";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                        z = "Register successfull";
                        isSuccess=true;


                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


           /* if(isSuccess) {
                startActivity(new Intent(LoginActivity.this,Main2Activity.class));

            }*/


            progressDialog.hide();
        }
    }
}
