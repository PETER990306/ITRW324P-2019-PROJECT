package com.itrw324.lights_on;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private Button btnReg;
    private EditText passw,confpassw,produk,email,cellphone;
    Database usersdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnReg = findViewById(R.id.btnRegister);
        passw  = findViewById(R.id.txtRegPassword);
        confpassw = findViewById(R.id.txtConfirmPassword);
        produk = findViewById(R.id.txtProduct);
        email = findViewById(R.id.txtEmail);
        cellphone = findViewById(R.id.txtCellphone);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String produkt = produk.getText().toString();

                    String pass = passw.getText().toString();
                    String cell = passw.getText().toString();
                    String confpass = confpassw.getText().toString();
                    String mail = email.getText().toString();

                    if(produkt.equals("")||mail.equals("")||pass.equals("")||confpass.equals("")||cell.equals("")){
                        Toast.makeText(getBaseContext(),"Complete all the fields",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(pass.equals(confpass)){
                            Boolean checkemail = usersdb.emailVerification(mail);
                            if(checkemail==true){
                                Boolean insert = usersdb.addUser(produkt,mail,cell,pass);
                                if(insert==true){
                                    Toast.makeText(getBaseContext(),"Registered successfully",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getBaseContext(),"Email already registered",Toast.LENGTH_SHORT).show();
                            }
                        }

                        Toast.makeText(getBaseContext(),"Incorrect confirm password",Toast.LENGTH_SHORT).show();
                    }

                    /*if(passw.getText().toString().equals(confpassw.getText().toString())){
                        Toast.makeText(getBaseContext(),"Registration success",Toast.LENGTH_SHORT).show();
                        openHomeActivity();
                    }
                    else {
                        Toast.makeText(getBaseContext(),"Incorrect confirm password",Toast.LENGTH_SHORT).show();
                    }*/

            }
        });

    }

    public void openHomeActivity(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
