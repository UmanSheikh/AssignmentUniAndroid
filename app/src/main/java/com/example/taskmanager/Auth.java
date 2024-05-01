package com.example.taskmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Auth extends AppCompatActivity {


    public static final String FILENAME = "credentials";
    FragmentManager manager;
    Fragment loginFrag, signupFrag;
    View loginFragView, signupFragView;
    TextView tvLogin, tvSignup;
    // hooks for signup
    TextInputEditText etUsernameS, etPasswordS, etCPasswordS;
    Button btnSignup, btnCancelS;
    // hooks of login
    TextInputEditText etUsernameL, etPasswordL;
    Button btnLogin, btnCancelL;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_auth);
        init();

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View v) {
                manager.beginTransaction()
                        .hide(loginFrag)
                        .show(signupFrag)
                        .commit();
            }

        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction()
                        .show(loginFrag)
                        .hide(signupFrag)
                        .commit();
            }
        });


        btnCancelL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCancelS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsernameS.getText().toString().trim();
                String password = etPasswordS.getText().toString();
                String cPassword = etCPasswordS.getText().toString();

                if(username.isEmpty() || password.isEmpty() || cPassword.isEmpty())
                {
                    Toast.makeText(Auth.this, "Something is missing", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password.equals(cPassword))
                    {
                        SharedPreferences sPref = getSharedPreferences(FILENAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sPref.edit();

                        editor.putString("key_username", username);
                        editor.putString("key_password", password);

                        editor.apply();
                        Toast.makeText(Auth.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        manager.beginTransaction()
                                .show(loginFrag)
                                .hide(signupFrag)
                                .commit();

                    }
                    else
                    {
                        Toast.makeText(Auth.this, "Password mis-matched", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsernameL.getText().toString().trim();
                String password = etPasswordL.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(Auth.this, "Something is missing", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SharedPreferences sPref = getSharedPreferences(FILENAME, MODE_PRIVATE);
                    String fUsername = sPref.getString("key_username", "");
                    String fPassword = sPref.getString("key_password", "");

                    if(fUsername.equals(username) && fPassword.equals(password))
                    {
                        SharedPreferences.Editor editor = sPref.edit();
                        editor.putBoolean("isLogin", true);
                        editor.apply();



                        startActivity(new Intent(Auth.this, MainActivity.class));
                        finish();
                    }
                }
            }
        });




    }

    private void init() {

        // checking is user is already logged in
        SharedPreferences sPref = getSharedPreferences(FILENAME, MODE_PRIVATE);
        boolean flag = sPref.getBoolean("isLogin", false);
        if(flag)
        {
            startActivity(new Intent(Auth.this, MainActivity.class));
            finish();
        }

        manager = getSupportFragmentManager();
        loginFrag = manager.findFragmentById(R.id.loginfrag);
        signupFrag = manager.findFragmentById(R.id.signupfrag);
        loginFragView = loginFrag.getView();
        signupFragView = signupFrag.getView();

        assert signupFragView != null;
        tvLogin = signupFragView.findViewById(R.id.tvLoginS);


        // signup frag hooks
        tvSignup = loginFragView.findViewById(R.id.tvSignup);
        etUsernameS = signupFragView.findViewById(R.id.etUsernameS);
        etPasswordS = signupFragView.findViewById(R.id.etPasswordS);
        etCPasswordS = signupFragView.findViewById(R.id.etCPasswordS);
        btnSignup = signupFragView.findViewById(R.id.btnSignupS);
        btnCancelS = signupFragView.findViewById(R.id.btnCancelS);

        // login hooks
        etUsernameL = loginFragView.findViewById(R.id.etUsername);
        etPasswordL = loginFragView.findViewById(R.id.etPassword);
        btnLogin = loginFragView.findViewById(R.id.btnLogin);
        btnCancelL = loginFragView.findViewById(R.id.btnCancel);

        manager.beginTransaction()
                .hide(signupFrag)
                .commit();
    }
}