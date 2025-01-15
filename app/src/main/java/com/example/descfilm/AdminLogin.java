package com.example.descfilm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(),AdminMovieList.class));
            finish();
        }
    }

    private void method() {
        username.setError(null);
        password.setError(null);
        View focus = null;
        boolean cancel = false;

        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(user)) {
            username.setError("Username is required!");
            focus = username;
            cancel = true;
        } else if (!user.equals("admin")) {
            username.setError("Invalid username credentials!");
            focus = username;
            cancel = true;
        }

        if (TextUtils.isEmpty(pass)) {
            password.setError("Password is required!");
            focus = password;
            cancel = true;
        } else if (!pass.equals("admin")) {
            password.setError("Invalid password credentials!");
            focus = password;
            cancel = true;
        }

        if (cancel) focus.requestFocus();
        else loggedin();
    }

    private void loggedin() {
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(),AdminMovieList.class));
        finish();
    }

}