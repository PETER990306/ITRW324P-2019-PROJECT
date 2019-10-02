package com.itrw324.lights_on;



import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Switch kitchenswitch,bedroom1switch,bathroomswitch,diningswitch,bedroom2switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        kitchenswitch = findViewById(R.id.swtchkitchen);
        diningswitch = findViewById(R.id.switchdining);
        bedroom1switch = findViewById(R.id.switchbedroom1);
        bathroomswitch = findViewById(R.id.switchbathroom);
        bedroom2switch = findViewById(R.id.switchbedroom2);

       kitchenswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               if(isChecked){
                   Toast.makeText(getBaseContext(),"Light turned on",Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(getBaseContext(),"Light turned off",Toast.LENGTH_SHORT).show();
               }
           }
       });

        diningswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getBaseContext(),"Light turned on",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(),"Light turned off",Toast.LENGTH_SHORT).show();
                }
            }
        });


        bedroom1switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getBaseContext(),"Light turned on",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(),"Light turned off",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bedroom2switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getBaseContext(),"Light turned on",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(),"Light turned off",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
   
   /* public void ConnectionEstablishment(String str) throws IOException
    {
        Socket socket;
        int Port=2009;// temporary port to be changed later
        String Server_add="192.168.1.5"; //temporary ip change as soon as lerato brings the RPI
        PrintWriter printwriter;

        socket = new Socket(Server_add,Port);
        printwriter = new PrintWriter(socket.getOutputStream());
        printwriter.write(str);
        printwriter.flush();
        printwriter.close();
        socket.close();

    }*/
}
