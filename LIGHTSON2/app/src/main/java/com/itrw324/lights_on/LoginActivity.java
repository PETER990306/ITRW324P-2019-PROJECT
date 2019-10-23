package com.itrw324.lights_on;

//import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;




public class LoginActivity extends AppCompatActivity {
    private Button btnEnter;//refers to the login button on the login page
    private TextView lblReg;//refers to the register textview or label on the login page

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();

    EditText emailText ;
    EditText passwordText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEnter = findViewById(R.id.btnLogin);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });

        lblReg = findViewById(R.id.lblRegister);
        lblReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });

        emailText = (EditText) findViewById(R.id.txtEmail);
        passwordText =(EditText) findViewById(R.id.txtPassword);
    }

    //this method opens the home page
    public void openHomeActivity(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    //this method opens the register page
    public void openRegisterActivity(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Send(View view) {
        new Create_Part().execute();
    }

    class Create_Part extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Sending part to the database...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


            @Override
            protected String doInBackground(String... args) {
                String String_email = emailText.getText().toString();
                String String_password = passwordText.getText().toString();

                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("Email", String_email));
                params.add(new BasicNameValuePair("password", String_password));

                JSONObject json = jsonParser.makeHttpRequest("192.168.137.69/db_create.php", "POST", params);

                try {
                    int success = json.getInt("success");

                    if (success == 1) {
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }


        protected void onPostExecute(String file_url){
            pDialog.dismiss();
        }

        public void Send(View view)
        {
            new Create_Part().execute();
        }
    }*/
}
