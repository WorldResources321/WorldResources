package com.example.world_resources_app.forum;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.world_resources_app.R;

import java.util.HashMap;
import java.util.Map;

public class WritePost extends AppCompatActivity {

    String processedContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writepost);
        
        Button cancelButton;
        Button postButton;
        EditText postContent;
        String postAuthor = "insert name"; //call getAuthorName from User module

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

                if (TextUtils.isEmpty(processedContent)) { //if post is empty/invalid
                    Toast.makeText(getApplicationContext(), "Please write something before posting", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (processedContent.length() > 500) {
                    Toast.makeText(getApplicationContext(), "Please write 500 characters or less", Toast.LENGTH_SHORT).show();
                }
                else { //send post if post is valid
                    makePost(processedContent, postAuthor);
                    //Toast.makeText(getApplicationContext(), "Posting...", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }

    private void makePost(String content, String author) {
        String key = "122333";

        RequestQueue queue = Volley.newRequestQueue(WritePost.this);

        String url = "http://10.0.2.2:3000/posttoforum";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(WritePost.this, "Success " + response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(WritePost.this,"Please try again. " + error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
              Map<String,String> params = new HashMap<String, String>();
              params.put("content", content);
              params.put("author", author);
              params.put("token", key);
              return params;
            }
        };

        queue.add(request);
    }

}
