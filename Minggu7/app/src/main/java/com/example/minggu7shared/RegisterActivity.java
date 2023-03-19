package com.example.minggu7shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText username, pass, confirm;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.txt_reg);
        pass = findViewById(R.id.txt_passReg);
        confirm = findViewById(R.id.txt_confirmPass);
        btn = findViewById(R.id.button_regRegister);
        btn.setOnClickListener((v)->{
            if (pass.getText().toString().equals(confirm.getText().toString())){
                SharedPreferences pref = getSharedPreferences("daftar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("username", username.getText().toString());
                editor.putString("pass", pass.getText().toString());
                editor.commit();
                editor.apply();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}