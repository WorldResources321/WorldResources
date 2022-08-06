package com.example.world_resources_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.world_resources_app.forum.ForumManagement;
import com.example.world_resources_app.news.NewsManagement;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    final static String TAG = "MainActivity";

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private final int RC_SIGN_IN = 1000;
    public boolean signedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button mapsButton;
        Button signInButton;
         Button quizButton;
         Button forumButton;
         Button newsButton;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        forumButton = findViewById(R.id.forum_button);
        forumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(acct != null) {
                    Intent forumIntent = new Intent(MainActivity.this, ForumManagement.class);
                    startActivity(forumIntent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please sign in to use this feature", Toast.LENGTH_LONG).show();
                }
            }
        });

        newsButton = findViewById(R.id.news_button);
        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsIntent = new Intent(MainActivity.this, NewsManagement.class);
                startActivity(newsIntent);
            }
        });

        mapsButton = findViewById(R.id.maps_button);
        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Trying to open Google Maps");
                Intent mapsIntent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(mapsIntent);
            }
        });

        quizButton = findViewById(R.id.quiz_button);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signedIn) {
                    Intent quizIntent = new Intent(MainActivity.this, Quiz.class);
                    startActivity(quizIntent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please sign in to use this feature", Toast.LENGTH_LONG).show();
                }
            }
        });

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        signedIn = true;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToProfile();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void navigateToProfile() {
        finish();
        Intent profileIntent = new Intent(MainActivity.this,ProfilePage.class);
        startActivity(profileIntent);
    }

}