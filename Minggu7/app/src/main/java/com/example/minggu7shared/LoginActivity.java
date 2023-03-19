package com.example.minggu7shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText mViewUser, mViewPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewUser = findViewById(R.id.txt_user);
        mViewPass = findViewById(R.id.txt_passLog);

        mViewPass.setOnEditorActionListener((v, actionId, event)->{
            if (actionId == EditorInfo.IME_ACTION_DONE ||actionId == EditorInfo.IME_NULL){
                razia();
                return true;
            }
            return false;
        });
        findViewById(R.id.button_login).setOnClickListener((v)->{
            razia();
        });
        findViewById(R.id.button_register).setOnClickListener((v)->{
            startActivity(new Intent(getBaseContext(),RegisterActivity.class));
        });
    }

    protected void onStart(){
        super.onStart();
        if (Preferences.getKeyStatusLSedangLogin(getBaseContext())) {
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }
    }
    private void razia(){
        mViewUser.setError(null);
        mViewPass.setError(null);
        View fokus = null;
        boolean cancel = false;

        String user = mViewUser.getText().toString();
        String password = mViewPass.getText().toString();

        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        } else if (!cekUser(user)) {
            mViewUser.setError("Username Not Found!");
            fokus = mViewUser;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)){
            mViewPass.setError("This field is required");
            fokus = mViewPass;
            cancel = true;
        } else if (!cekPass(password)) {
            mViewPass.setError("Password is incorrect!");
            fokus = mViewPass;
            cancel = true;
        }
        if (cancel) fokus.requestFocus();
        else masuk();
    }

    private void masuk(){
        Preferences.setKeyUsernameSedangLogin(getBaseContext(), mViewUser.getText().toString());
        Preferences.setKeyStatusSedangLogin(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }
    private boolean cekPass(String password){
        SharedPreferences pref = getSharedPreferences("daftar", Context.MODE_PRIVATE);
        if (pref.getString("pass","").equals(password)){
            return true;
        }
        return false;
    }
    private Boolean cekUser(String user){
        SharedPreferences pref = getSharedPreferences("daftar", Context.MODE_PRIVATE);
        if(pref.getString("username", "").equals(user)){
            return true;
        }
        return false;
    }
}