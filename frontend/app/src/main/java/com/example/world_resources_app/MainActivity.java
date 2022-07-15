package com.example.world_resources_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.world_resources_app.forum.ForumManagement;
import com.example.world_resources_app.news.NewsManagement;

import android.content.Intent;
import android.os.Bundle;
import java.net.UnknownHostException;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button forumButton, newsButton;

    private Button mapsButton;
    private Button signInButton;
    private Button quizButton;

    final static String TAG = "MainActivity";

    private String textLatLong;

    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 1;
    public boolean signedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forumButton = findViewById(R.id.forum_button);
        forumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForumManagement.class);
                startActivity(intent);
            }
        });

        newsButton = findViewById(R.id.news_button);
        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsManagement.class);
                startActivity(intent);
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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        signedIn = true;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            try {
                updateUI(account);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            try {
                updateUI(null);
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        try {
            updateUI(account);
            signedIn = true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void updateUI(GoogleSignInAccount account) throws UnknownHostException {
        if (account == null) {
            Log.d(TAG, "There is no user signed in!");
        }
        else{
            Intent profileIntent = new Intent(MainActivity.this, ProfilePage.class);
            startActivity(profileIntent);
        }
    }

}