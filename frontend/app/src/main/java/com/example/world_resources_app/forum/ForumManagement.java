package com.example.world_resources_app.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.world_resources_app.R;

import java.util.ArrayList;
import java.util.List;


public class ForumManagement extends AppCompatActivity {

    final static String TAG = "MainActivity";


    //private SwipeRefreshLayout swipeRefreshLayout;
    
        //posts and authors
    private TextView p1;
    private TextView p2;
    private TextView p3;
    private TextView p4;
    private TextView p5;
    private TextView p6;
    private TextView p7;
    private TextView p8;
    private TextView p9;
    private TextView p10;
    private TextView a1;
    private TextView a2;
    private TextView a3;
    private TextView a4;
    private TextView a5;
    private TextView a6;
    private TextView a7;
    private TextView a8;
    private TextView a9;
    private TextView a10;
   
    //private Button makePost, report;

   // private List<String> forumContent = new ArrayList<String>();
   // private List<String> forumAuthors = new ArrayList<String>();
    private List<String> testForumContent = new ArrayList<String>();
    private List<String> testForumAuthors = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        setTitle("Forum");

        Button makePost;
        Button report;
        
        testForumAuthors.add("test author 1");
        testForumAuthors.add("test author 2");
        testForumContent.add("test content 1: manually inputted since frontend/backend connection is not working asldfj wpeorm sc dpowlwakerpo awfmksdmp akewrklkmmclakjdfp werkdckmalksjfdkaf dslj alsjfdopawer" +
                "werwewerwerio apwoipo [0awoerq alokd[q 234091 ;a[o0-3oa ap002l,k ;0o[plaw3a -=f0134l14 g-f[fg23lakdsfpb apoakwmreasfaoewr[ apewormasvklagpoaweraewrawer23423awewat");
        testForumContent.add("test content 2: asldfj wpeorm sc dpowlwakerpo awfmksdmp akewrklkmmclakjdfp werkdckmalksjfdkaf dslj alsjfdopawer" +
                "werwewerwerio apwoipo [0awoerq alokd[q 234091 ;a[o0-3oa ap002l,k ;0o[plaw3a -=f0134l14 g-f[fg23lakdsfpb apoakwmreasfaoewr[ apewormasvklagpoaweraewrawer23423awewat");

        getPosts();
        getAuthors();

        p1 = findViewById(R.id.text1);
        p2 = findViewById(R.id.text2);
        p3 = findViewById(R.id.text3);
        p4 = findViewById(R.id.text4);
        p5 = findViewById(R.id.text5);
        p6 = findViewById(R.id.text6);
        p7 = findViewById(R.id.text7);
        p8 = findViewById(R.id.text8);
        p9 = findViewById(R.id.text9);
        p10 = findViewById(R.id.text10);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        a6 = findViewById(R.id.a6);
        a7 = findViewById(R.id.a7);
        a8 = findViewById(R.id.a8);
        a9 = findViewById(R.id.a9);
        a10 = findViewById(R.id.a10);

        updateForum();

        makePost = findViewById(R.id.make_post_button);
        makePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumManagement.this, WritePost.class);
                startActivity(intent);
            }
        });

        report = findViewById(R.id.report_button);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumManagement.this, ReportUser.class);
                startActivity(intent);
            }
        });


    }

    private void getPosts() { //get most recent post
        RequestQueue queue = Volley.newRequestQueue(ForumManagement.this);

        String url = "http://10.0.2.2:3000/getposts";
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(ForumFragment.this, "Success " + response, Toast.LENGTH_LONG).show();
                        //forumContent = (Arrays.asList(response.split(",")));

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForumManagement.this,"Posts cannot be loaded. " + error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        queue.add(request);


    }

    private void getAuthors() { //get most recent post
        RequestQueue queue = Volley.newRequestQueue(ForumManagement.this);

        String url = "http://10.0.2.2:3000/getauthors";
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(ForumFragment.this, "Success " + response, Toast.LENGTH_LONG).show();
                        //forumAuthors = (Arrays.asList(response.split(",")));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ForumManagement.this,"Posts cannot be loaded. " + error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        );

        queue.add(request);
    }

    private void updateForum() {

/*
        if (forumContent.get(0) != null) {
            p1.setText(forumContent.get(0));
            a1.setText(forumAuthors.get(0));
        }
        
        if (forumContent.get(0) != null) {
            p2.setText(forumContent.get(1));
            a2.setText(forumAuthors.get(1));
        }
        if (forumContent.get(0) != null) {
            p3.setText(forumContent.get(2));
            a3.setText(forumAuthors.get(2));
        }
        if (forumContent.get(0) != null) {
            p4.setText(forumContent.get(3));
            a4.setText(forumAuthors.get(3));
        }
        if (forumContent.get(0) != null) {
            p5.setText(forumContent.get(4));
            a5.setText(forumAuthors.get(4));
        }
        if (forumContent.get(0) != null) {
            p6.setText(forumContent.get(5));
            a6.setText(forumAuthors.get(5));
        }
        if (forumContent.get(0) != null) {
            p7.setText(forumContent.get(6));
            a7.setText(forumAuthors.get(6));
        }
        if (forumContent.get(0) != null) {
            p8.setText(forumContent.get(7));
            a8.setText(forumAuthors.get(7));
        }
        if (forumContent.get(0) != null) {
            p9.setText(forumContent.get(8));
            a9.setText(forumAuthors.get(8));
        }
        if (forumContent.get(0) != null) {
            p10.setText(forumContent.get(9));
            a10.setText(forumAuthors.get(9));
        }
*/


        //to see how posts would look since backend is not working
        p1.setText(testForumContent.get(0));
        p2.setText(testForumContent.get(1));
        a1.setText(testForumAuthors.get(0));
        a2.setText(testForumAuthors.get(1));
        p3.setText(testForumContent.get(0));
        p4.setText(testForumContent.get(1));
        a3.setText(testForumAuthors.get(0));
        a4.setText(testForumAuthors.get(1));
        p5.setText(testForumContent.get(0));
        p6.setText(testForumContent.get(1));
        a5.setText(testForumAuthors.get(0));
        a6.setText(testForumAuthors.get(1));
        p7.setText(testForumContent.get(0));
        p8.setText(testForumContent.get(1));
        a7.setText(testForumAuthors.get(0));
        a8.setText(testForumAuthors.get(1));
        p9.setText(testForumContent.get(0));
        p10.setText(testForumContent.get(1));
        a9.setText(testForumAuthors.get(0));
        a10.setText(testForumAuthors.get(1));
        
        //Toast.makeText(ForumManagement.this, forumContent.get(0).toString(),Toast.LENGTH_LONG);
    }
 


}
