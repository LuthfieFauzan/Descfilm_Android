package com.example.descfilm;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

public class About extends AppCompatActivity {
    EditText name, email, message;
    Button submitmessage;
    DatabaseReference reference;
    Message msg = new Message();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        String frameMap =
            "<html>" +
                "<body>" +
                    "<iframe width=\"335\" height=\"210\"" +
                    " src=\"https://www.google.com/maps/embed/v1/place?key=AIzaSyDdxPuSDIqfKBnj6GXm577fmgaRHlj_ZOA&q=CEP+CCIT+FTUI\"" +
                    " frameborder=\"0\"></iframe>" +
                "</body>" +
            "</html>";

        WebView displayMap = findViewById(R.id.mapView);
        displayMap.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayMap.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayMap.loadData(frameMap, "text/html", "utf-8");

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        message = findViewById(R.id.message);

        submitmessage = findViewById(R.id.submitmessage);
        submitmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName = name.getText().toString();
                String sEmail = email.getText().toString();
                String sMessage = message.getText().toString();

                reference = FirebaseDatabase.getInstance().getReference();
                msg.setName(name.getText().toString());
                msg.setEmail(email.getText().toString());
                msg.setMessage(message.getText().toString());

                if (!sName.isEmpty() && !sEmail.isEmpty() && !sMessage.isEmpty()) {
                    reference.child("message").push().setValue(msg);
                    name.setText("");
                    email.setText("");
                    message.setText("");
                    Toast.makeText(getApplicationContext(),
                            "Thanks for your message on this application!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please input all required fields before continue!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}