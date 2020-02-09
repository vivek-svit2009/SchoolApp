package com.svtechcorp.schoolapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    // Declaring Server ip, username, database name and password
    String ip = "148.72.232.167";
    String  db = "DashboardApp";
    String  un = "dashboard";
    String pass = "dashboard@123";
    // Declaring Server ip, username, database name and password
    @SuppressLint("NewApi")
    public Connection CONN()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://"+ip+"/"+db+";instance=SQLEXPRESS;user="+un+";password="+pass+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
