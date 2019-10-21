package com.itrw324.lights_on;



import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Switch lightswitch,bedroom1switch,bathroomswitch,diningswitch,bedroom2switch;

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
