package com.svtechcorp.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.svtechcorp.schoolapp.ConnectionClass;

public class MainActivity extends AppCompatActivity {
    RelativeLayout rellay1, rellay2;
    Button btnlogin;
    int Id;
    String Clas,name,email;

    EditText username,password;
    ProgressBar progressBar;
     ConnectionClass connectionClass;
    SharedPreferences sp,sp1;
    // End Declaring layout button, edit texts

    // Declaring connection variables
    Connection con;
    String un,pass,db,ip;
    //End Declaring connection variables

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        connectionClass = new ConnectionClass();
        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
        btnlogin = (Button) findViewById(R.id.btnlogin);
        username = (EditText) findViewById(R.id.usernamee);
        password = (EditText) findViewById(R.id.passworde);
       // progressBar = (ProgressBar) findViewById(R.id.progressBar);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        sp1 = getSharedPreferences("IdClass",MODE_PRIVATE);

                if(sp.contains("logge")){
                    Intent intent = new Intent(MainActivity.this,DashBoard.class);
                    startActivity(intent);
                }




        // Setting up the function when button login is clicked
       btnlogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CheckLogin checkLogin = new CheckLogin();
                checkLogin.execute("");
            }
        });
        //End Setting up the function when button login is clicked
    }

    public class CheckLogin extends AsyncTask<String,String,String>
    {
        ProgressDialog progress;
        String usernam = username.getText().toString();
        String passwordd = password.getText().toString();
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(MainActivity.this, "Loggin You",
                    "Checking ! Please Wait...", true);
           // progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r)
        {
            progress.dismiss();
           // progressBar.setVisibility(View.GONE);

            Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();

            if(isSuccess)
            {
                sp.edit().putBoolean("logge",true).apply();

                sp1.edit().putInt("Id",Id).apply();
                sp1.edit().putString("Class",Clas).apply();
                sp1.edit().putString("Name",name).apply();
                sp1.edit().putString("Email",email).apply();

                Intent intent = new Intent(MainActivity.this,DashBoard.class);

                startActivity(intent);
            }
        }

        @Override
        protected String doInBackground(String... params)
        {

            if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                z = "Please enter Username and Password";
            else
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
                        StringBuilder str = new StringBuilder(passwordd.toString());
                        str.insert(2,"/");
                        str.insert(5,"/");
                        // Change below query according to your own database.
                        String query = "select * from StudentData where Mobile= '" + usernam.toString() + "' and DOB = '"+ str.toString() +"'  ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            Id = rs.getInt("Id");
                            Clas = rs.getString("Class");
                            name = rs.getString("Name");
                            email = rs.getString("Email");

                            z = "Login Successfully";
                            isSuccess=true;
                            con.close();
                        }
                        else
                        {
                            z = "Invalid Credentials!";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
        }
    }




}
