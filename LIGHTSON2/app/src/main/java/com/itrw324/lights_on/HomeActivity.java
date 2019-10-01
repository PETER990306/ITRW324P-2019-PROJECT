package com.itrw324.lights_on;



import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Switch kitchenswitch,bedroomswitch,bathroomswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        kitchenswitch = findViewById(R.id.swtchkitchen);

        kitchenswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            ConnectionEstablishment("Light bulb turned on");
                        }
                        catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
   
    public void ConnectionEstablishment(String str) throws IOException
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

    }
}
