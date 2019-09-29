package com.itrw324.lights_on;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity
{
    EditText editText;
    private static final String DB_URL = "jdbc:mysql://196.252.195.183/itrw324";
    private static final String USER = "root";
    private static final String PASS = "Kh@mbul3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText = (EditText) findViewById(R.id.txtProduct);
    }

    public void btnConn(View view)
    {
        Send objSend = new Send();
        objSend.execute("");
    }

    private class Send extends AsyncTask<String,String,String>
    {
        String msg ="";
        String text = editText.getText().toString();

        @Override
        protected String doInBackground(String...strings)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

                if (conn == null)
                {
                    msg ="connection goes wrong";
                }
                else {
                  String query = "INSERT INTO users(product_id,email,cellphone,password,confirm password) VALUES ('"+text+"')";
                  Statement stmt = conn.createStatement();
                  stmt.executeUpdate(query);
                  msg = "Inserting success";
                }
                conn.close();
            }
            catch (Exception e)
            {
                msg="connection goes wrong";
                e.printStackTrace();
            }
            return  msg;
        }


    }
}
