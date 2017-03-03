package com.example.tanmay.backendtest;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    Button sendRequestButtonobj,sendRequestButtonstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        textView=(TextView)findViewById(R.id.view);
        sendRequestButtonobj=(Button)findViewById(R.id.send_request_json_object);
        sendRequestButtonstr=(Button)findViewById(R.id.send_request_string);
        sendRequestButtonstr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://hackadg.herokuapp.com/user/create";
                StringRequest stringRequest=new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Error Came(String)");
                    }
                }){
                    @Override
                    protected Map<String,String> getParams()
                    {
                        Map<String,String> params=new HashMap<>();
                        params.put("name","Tanmay Jha");
                        params.put("regno","15BCE0618");
                        params.put("phone","9790048621");
                        params.put("email","tanmay.jha1@gmail.com");
                        params.put("hackerrank","132");
                        return params;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
            }
        });

        sendRequestButtonobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://hackadg.herokuapp.com/user/create";
                JsonObjectRequest jsonPostRequest =new JsonObjectRequest(Request.Method.PUT, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String data = "Name is" + response.getString("name");
                            data.concat("\n");
                            data.concat("Registration no:" + response.getInt("regno"));
                            data.concat("\n");
                            data.concat("Email" + response.getString("email"));
                            data.concat("\n");
                            data.concat("Team" + response.getString("team"));
                            data.concat("\n");
                            data.concat("Hacker Rank" + response.getString("hackerrank"));
                            textView.setText(response.toString());
                        } catch (Exception error) {
                            error.printStackTrace();
                            textView.setText("Error occured");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        textView.setText("Error occured");
                    }
                }){
                    @Override
                    protected Map<String,String> getParams()
                    {
                        Map<String,String> params=new HashMap<>();
                        params.put("name","Tanmay Jha");
                        params.put("regno","15BCE0618");
                        params.put("phone","9790048621");
                        params.put("email","tanmay.jha1@gmail.com");
                        params.put("hackerrank","132");
                        return params;
                    }
                };

                Volley.newRequestQueue(getApplicationContext()).add(jsonPostRequest);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
