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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.world_resources_app.R;

import org.json.JSONException;
import org.json.JSONObject;


public class ReportUser extends AppCompatActivity {

    Button reportButton;
    EditText reportedPerson;
    String processedUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //if user presses report
        reportedPerson = findViewById(R.id.reportEdit);
        reportButton = findViewById(R.id.sendreport_button);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processedUser = reportedPerson.getText().toString(); //acquire post texts

                if (TextUtils.isEmpty(processedUser)) { //if post is empty/invalid
                    Toast.makeText(getApplicationContext(), "Please enter a user", Toast.LENGTH_SHORT).show();
                    return;
                } else { //send post if post is valid
                    report(processedUser);
                    finish();
                }

            }
        });

    }

    private void report(String reportedPerson) {

        RequestQueue queue = Volley.newRequestQueue(this);

        String URL = "http://10.0.2.2:3000/reportUser";//"http://192.168.1.119:3000/report";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", reportedPerson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //backend
                Toast.makeText(ReportUser.this, "Success " + response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(ReportUser.this, "Please try again. " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(req);
    }



}
