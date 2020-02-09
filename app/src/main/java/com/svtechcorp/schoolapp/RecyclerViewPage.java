package com.svtechcorp.schoolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPage extends AppCompatActivity {

    private static final String URL_DATA="https://simplifiedcoding.net/demos/marvel/";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<recyclerListItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_page);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
       /* for (int i=0;i<=10;i++)
        {
            recyclerListItem listItem = new recyclerListItem(
              "Heading "+(i+1),
                    "Lorem Isjdnjkd sdjkd sjdksd"
            );
            listItems.add(listItem);
        }*/
       loadrecylerViewData();
       /* adapter = new recyclerMyAdapter(listItems,this);
        recyclerView.setAdapter(adapter);*/
    }
    private void loadrecylerViewData()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loding Data....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                   // JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        recyclerListItem item = new recyclerListItem(
                            jsonObject.getString("name"),
                                jsonObject.getString("realname")
                        );
                        listItems.add(item);
                    }
                    adapter = new recyclerMyAdapter(listItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
