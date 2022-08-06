package com.example.world_resources_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilePage extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email,quizScore;

    private Button exitButton;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        HashMap<String,String> map = new HashMap<>();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        quizScore = findViewById(R.id.quizScore);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null){
            String personName = "Name:\n" + acct.getGivenName() + " " + acct.getFamilyName();
            String personEmail = "                   Email:\n" + acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
            map.put("email","4");
        }

        Call<ScoreResult> call2 = retrofitInterface.getQuiz(map);

        call2.enqueue(new Callback<ScoreResult>() {
            @Override
            public void onResponse(Call<ScoreResult> call, Response<ScoreResult> response) {
                if(response.code() == 200) {
                    ScoreResult result = response.body();
                    String scoreString = "Best Quiz Score: " + result.getScore();
                    quizScore.setText(scoreString);

                } else if (response.code() == 404) {
                    String scoreString = "Have not taken quiz";
                    quizScore.setText(scoreString);
                    Toast.makeText(ProfilePage.this, "Could not retrieve quiz score.",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ScoreResult> call, Throwable t) {
                Toast.makeText(ProfilePage.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exitIntent = new Intent(ProfilePage.this, MainActivity.class);
                startActivity(exitIntent);
            }
        });

    }
}