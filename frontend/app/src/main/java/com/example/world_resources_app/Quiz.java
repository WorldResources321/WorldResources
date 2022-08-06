package com.example.world_resources_app;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
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

public class Quiz extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA;
    Button ansB;
    Button ansC;
    Button ansD;
    Button submitBtn;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    String email;

    final static String TAG = "Quiz";

    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    int score = 0;
    int totalQuestion = QuestionsAnswersUtil.question.length;
    int currentQuestionIndex = 0;
    public String selectedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Retrofit retrofit;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null){
            email = acct.getEmail();
        }

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        String questionText = "Total questions: " + totalQuestion;
        totalQuestionsTextView.setText(questionText);

        loadNewQuestion();
    }

    private void loadNewQuestion() {

        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionsAnswersUtil.question[currentQuestionIndex]);
        ansA.setText(QuestionsAnswersUtil.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionsAnswersUtil.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionsAnswersUtil.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionsAnswersUtil.choices[currentQuestionIndex][3]);

    }

    private void finishQuiz() {

        HashMap<String,String> map = new HashMap<>();

        map.put("email","4");
        map.put("score", "" + score);
        Call<Void> call = retrofitInterface.storeQuiz(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200) {
                    Toast.makeText(Quiz.this, "New high score!", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400){
                    Toast.makeText(Quiz.this, "Well done!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Quiz.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        String message = "Score is " + score + " out of " + totalQuestion;
        new AlertDialog.Builder(this)
                .setTitle("Results")
                .setMessage(message)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
        String questionText = "Total questions: " + totalQuestion;

    }

    @Override
    public void onClick(View v) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if(clickedButton.getId()==R.id.submit_btn) {
            if(selectedAnswer != QuestionsAnswersUtil.choices[currentQuestionIndex][0]
                    && selectedAnswer != QuestionsAnswersUtil.choices[currentQuestionIndex][1]
                    && selectedAnswer != QuestionsAnswersUtil.choices[currentQuestionIndex][2]
                    && selectedAnswer != QuestionsAnswersUtil.choices[currentQuestionIndex][3]) {
                Toast.makeText(this, "Please select an answer.", Toast.LENGTH_LONG).show();
            }
            else {
                if (selectedAnswer.equals(QuestionsAnswersUtil.correctAnswers[currentQuestionIndex])) {
                    score++;
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }

        } else {

            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }
}