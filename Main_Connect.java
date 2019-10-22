package com.itrw324.lights_on;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.*;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private Button btnEnter;
    private TextView lblReg;
    Socket myAppSocket = null;
    public static String wifiModuleIP = "";
    public static int wifiModulePort = 0;
    public static String ipAddress = "";
    public static String CMD = "0";

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnEnter = findViewById(R.id.btnLogin);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIPandPort();
                CMD = "LogIn";
                Socket_AsyncTask cmd_Login_servo = new Socket_AsyncTask();
                cmd_Login_servo.execute();
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
        btnEnter.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                getIPandPort();
                CMD = "LogIn";
                Socket_AsyncTask cmd_Login_servo = new Socket_AsyncTask();
                cmd_Login_servo.execute();

            }


        });

    }

    public void getIPandPort() {
        String iPandPort = "Raspberry Pi ipAddress";
        Log.d("MYTEST", "IP String: " + iPandPort);
        //String temp[] = iPandPort.split(":");
        wifiModuleIP = "192.168.137.191";
        wifiModulePort = 27247;
        Log.d("MY TEST", "IP:" + wifiModuleIP);
        Log.d("MY TEST", "PORT:" + wifiModulePort);

    }

    public class Socket_AsyncTask extends AsyncTask<Void, Void, Void> {
        Socket socket;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                InetAddress inetAddress = InetAddress.getByName(LoginActivity.wifiModuleIP);
                socket = new java.net.Socket(inetAddress, LoginActivity.wifiModulePort);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeBytes(CMD);
                dataOutputStream.close();
                socket.close();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException
                    e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    public void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    @Override
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
        new LoginActivity.Create_Part().execute();
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
            //String String_email = editTextEmail.getText().toString();
            String String_email = args[0];
            ////String String_cellphone = args[1];
            //String String_password = editTextCellphone.getText().toString();
            //String String_conPassword = editTextCellphone.getText().toString();

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("Email", String_email));
            /*params.add(new BasicNameValuePair("Cellphone", String_cellphone));
            params.add(new BasicNameValuePair("Password", Int_Part));
            params.add(new BasicNameValuePair("Confirm Password", Int_Part));*/
            JSONObject json = jsonParser.makeHttpRequest("192.168.137.191ss/db_create.php", "POST", params);

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

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }

    }
}
