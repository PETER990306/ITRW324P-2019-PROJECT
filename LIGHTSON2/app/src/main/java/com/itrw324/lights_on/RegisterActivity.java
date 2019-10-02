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
    private EditText passw,confpassw,produk,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnReg = findViewById(R.id.btnRegister);
        passw  = findViewById(R.id.txtRegPassword);
        confpassw = findViewById(R.id.txtConfirmPassword);
        produk = findViewById(R.id.txtProduct);
        email = findViewById(R.id.txtEmail);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(passw.getText().toString().equals(confpassw.getText().toString())){
                        Toast.makeText(getBaseContext(),"Registration success",Toast.LENGTH_SHORT).show();
                        openHomeActivity();
                    }
                    else {
                        Toast.makeText(getBaseContext(),"Incorrect confirm password",Toast.LENGTH_SHORT).show();
                    }

            }
        });

    }

    public void openHomeActivity(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
