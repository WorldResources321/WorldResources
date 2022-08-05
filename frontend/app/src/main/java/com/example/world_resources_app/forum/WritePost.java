package com.example.world_resources_app.forum;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.world_resources_app.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class WritePost extends AppCompatActivity {

    String processedContent;
    String postAuthor = "unknown author";

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writepost);

        Button cancelButton;
        Button postButton;
        EditText postContent;

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        //if user presses cancel, return to forum fragment
        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //if user presses post, check
        postContent = findViewById(R.id.editPost);
        postButton = findViewById(R.id.sendpost_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processedContent = postContent.getText().toString(); //acquire post texts
                //get user's email edit
                if(acct != null){
                    //postAuthor = acct.getEmail();
                    postAuthor = "user1@gmail.com"; //to edit
                }
                else {
                    postAuthor = "user1@gmail.com";
                }

                if (TextUtils.isEmpty(processedContent)) { //if post is empty/invalid
                    Toast.makeText(getApplicationContext(), "Please write something before posting", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (processedContent.length() > 500) {
                    Toast.makeText(getApplicationContext(),  "Please write 500 characters or less", Toast.LENGTH_SHORT).show();
                }
                else { //send post if post is valid
                    makePost(processedContent, postAuthor);
                    Toast.makeText(getApplicationContext(), "Posting...", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }

    private void makePost(String content, String author) {
        String key = "122333";
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://10.0.2.2:3000/postToForum"; //"http://192.168.1.119:3000/postToForum";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("content", content);
            jsonBody.put("author", author);
            jsonBody.put("key", key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //backend
                Toast.makeText(WritePost.this, "Success " + response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(WritePost.this,"Please try again. " + error.toString(),Toast.LENGTH_LONG).show();
          /*      if (error instanceof NetworkError) {
                } else if (error instanceof ServerError) {
                } else if (error instanceof AuthFailureError) {
                } else if (error instanceof ParseError) {
                } else if (error instanceof NoConnectionError) {
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(),
                            "Oops. Timeout error!",
                            Toast.LENGTH_LONG).show();
                } */
            }
        });
/*
        req.setRetryPolicy(new DefaultRetryPolicy(
           10000,
           DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
           DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )); */
        queue.add(req);

    }



}
