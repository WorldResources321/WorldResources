package com.example.world_resources_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class ProfilePage extends AppCompatActivity {

    TextView userInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        userInfoTextView = findViewById(R.id.user_info);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //String userName = "Michael Perkins";
        if(account != null) {
            String userName = account.getDisplayName();
            String userInfo = "User Name: " + userName;
            userInfoTextView.setText(userInfo);
        }
        else {
            userInfoTextView.setText("");
        }
    }
}