package com.itrw324.lights_on;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

   Switch lightswitch;
   Button btnstream,btnactivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lightswitch = findViewById(R.id.swtchLight);


       lightswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               if(isChecked){
                   Toast.makeText(getBaseContext(),"Light turned on",Toast.LENGTH_SHORT).show();

                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           try
                           {
                               ConnectionEstablishment("ON");
                           } catch (UnknownHostException e) {
                               e.printStackTrace();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                   }).start();

               }
               else{
                   Toast.makeText(getBaseContext(),"Light turned off",Toast.LENGTH_SHORT).show();

                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           try
                           {
                               ConnectionEstablishment("Off");
                           } catch (UnknownHostException e) {
                               e.printStackTrace();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                   }).start();

               }
           }
       });

       btnstream = findViewById(R.id.btnStreaming);
       btnstream.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openStreamingActivity();
           }
       });

        btnstream = findViewById(R.id.btnActivity);
        btnstream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });


    }


    public void openMainActivity(){
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void openStreamingActivity(){
        Intent intent = new Intent(this,StreamingActivity.class);
        startActivity(intent);
    }


    public void ConnectionEstablishment(String str) throws IOException
    {
        Socket socket;
        int Port=8080;// change this when you get RPI
        String Server_add="196.252.254.120";// change this when you get RPI
        PrintWriter printwriter;

        socket = new Socket(Server_add,Port);
        printwriter = new PrintWriter(socket.getOutputStream());
        printwriter.write(str);
        printwriter.flush();
        printwriter.close();
        socket.close();

    }
}
