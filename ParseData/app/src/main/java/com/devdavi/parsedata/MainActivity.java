package com.devdavi.parsedata;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
//    private RequestQueue requestQueue;
    String url = "https://www.google.com";
    String apiUrl = "https://jsonplaceholder.typicode.com/todos";
    String getApiUrl = "https://jsonplaceholder.typicode.com/todos/1";
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        requestQueue = Volley.newRequestQueue(this);
        queue = MySingleton.getInstance(this.getApplicationContext())
                .getRequestQueue();

        TextView textView = findViewById(R.id.textView);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getApiUrl, null,
                response -> {
                    Log.d("JsonObject", "RESPONSE: "+ response.toString());
                    try {
                        textView.setText(response.getString("title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Log.d("ObjectError", "Have an error!!!!");
        });
        queue.add(jsonObjectRequest);


        getJsonArrayRequested();

        getString();
    }

    private void getJsonArrayRequested() {
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Log.d("JsonTitle", "RESPONSE: " + jsonObject.getString("completed"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, error -> {
            Log.d("JSONError", "FAIL TO GET INFO!");
        });

        queue.add(jsonRequest);
    }

    private void getString() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // display the contents of our url

            Log.d("Response", "RESPONSE: " + response.substring(0, 500));
        }, error -> {
            Log.d("Error", "FAIL TO GET INFO!");
        });
        queue.add(stringRequest);
    }
}