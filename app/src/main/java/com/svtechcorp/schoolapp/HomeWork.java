package com.svtechcorp.schoolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeWork extends AppCompatActivity {
    ArrayList<DataModel> dataModels;
    ListView listView;
    ConnectionClass connectionClass;
    int Id;
    String Clas;
    SharedPreferences sp1;
    // End Declaring layout button, edit texts
        ImageView imgdt;
        Calendar mCurrentDate;
        int day,month,year;
    // Declaring connection variables
    Connection con;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);

        listView=(ListView)findViewById(R.id.list);
        connectionClass = new ConnectionClass();
        sp1 = getSharedPreferences("IdClass",MODE_PRIVATE);
        Id = sp1.getInt("Id",0);
        Clas = sp1.getString("Class","1");

        Toast.makeText(HomeWork.this, Clas, Toast.LENGTH_SHORT).show();



        dataModels= new ArrayList<>();

        /*dataModels.add(new DataModel("Apple Pie", "Android 1.0", "1","September 23, 2008"));
        dataModels.add(new DataModel("Banana Bread", "Android 1.1", "2","February 9, 2009"));

        dataModels.add(new DataModel("Marshmallow", "Android 6.0", "23","October 5, 2015"));*/
        CheckLogin checkLogin = new CheckLogin();
        checkLogin.execute("");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataModel dataModel= dataModels.get(position);

              //  Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();
                Intent intent = new Intent(HomeWork.this, DetailedHomeWork.class);
                intent.putExtra("message",Integer.toString(dataModel.getId()));
                startActivity(intent);
            }
        });


        mCurrentDate = Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        imgdt = (ImageView) findViewById(R.id.imgdt);
        imgdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HomeWork.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                    i1=i1+1;
                        String cunDate = i2+"/"+i1+"/"+i;
                       // Toast.makeText(HomeWork.this,i1,Toast.LENGTH_SHORT).show();
                        //Toast.makeText(HomeWork.this,i,Toast.LENGTH_SHORT).show();
                        String z = "";
                        Boolean isSuccess = false;

                        try
                        {
                            con = connectionClass.CONN();       // Connect to database
                            if (con == null)
                            {
                                z = "Check Your Internet Access!";
                            }
                            else
                            {
                                // Change below query according to your own database.
                                String query = "select Id,Class,Description,IsAttachement,LastDate,Url,Date,Subject from HomeWork where Class='"+Clas+"' and Date = '"+cunDate+"'";
                                Statement stmt = con.createStatement();
                                ResultSet rs = stmt.executeQuery(query);
                                if(rs!=null)
                                {
                                    dataModels.clear();
                                    while (rs.next())
                                    {
                                        try {

                                            dataModels.add(new DataModel(rs.getInt("Id"), rs.getString("Class"), rs.getString("Description"),rs.getString("LastDate"),rs.getString("IsAttachement"),rs.getString("Url"),rs.getString("Date"),rs.getString("Subject")));
                                        } catch (Exception ex) {
                                            isSuccess = false;
                                            z = ex.getMessage();
                                        }


                                    }
                                    isSuccess=true;
                                    con.close();
                                }
                                else
                                {
                                    z = "No data Found";
                                    isSuccess=false;
                                }

                            }
                        }
                        catch (Exception ex)
                        {
                            isSuccess = false;
                            z = ex.getMessage();
                        }
                        if(isSuccess)
                        {
                            //adapter= new CustomAdapter(dataModels,getApplicationContext());

                            listView.setAdapter(adapter);
                        }








                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });




    }
    public class CheckLogin extends AsyncTask<String,String,String>
    {

        String z = "";
        Boolean isSuccess = false;
        ProgressDialog progress;
        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(HomeWork.this, "Synchronising",
                    "Listview Loading! Please Wait...", true);
            // progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r)
        {
            // progressBar.setVisibility(View.GONE);
            progress.dismiss();
            Toast.makeText(HomeWork.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
               adapter= new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);
            }
        }

        @Override
        protected String doInBackground(String... params)
        {

                try
                {
                    con = connectionClass.CONN();       // Connect to database
                    if (con == null)
                    {
                        z = "Check Your Internet Access!";
                    }
                    else
                    {
                        // Change below query according to your own database.
                        String query = "select Id,Class,Description,IsAttachement,LastDate,Url,Date,Subject from HomeWork where Class='"+Clas+"'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs!=null)
                        {
                            while (rs.next())
                            {
                                try {

                                    dataModels.add(new DataModel(rs.getInt("Id"), rs.getString("Class"), rs.getString("Description"),rs.getString("LastDate"),rs.getString("IsAttachement"),rs.getString("Url"),rs.getString("Date"),rs.getString("Subject")));
                                } catch (Exception ex) {
                                    isSuccess = false;
                                    z = ex.getMessage();
                                }


                            }
                            isSuccess=true;
                            con.close();
                        }
                        else
                            {
                                z = "No data Found";
                                isSuccess=false;
                            }

                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }

            return z;
        }
    }

}
