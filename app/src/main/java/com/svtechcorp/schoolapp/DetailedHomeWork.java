package com.svtechcorp.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class DetailedHomeWork extends AppCompatActivity {
    TextView Class,Subject,LastDate,Description;
    ConnectionClass connectionClass;
WebView webview;
ProgressBar progressbar;
    String classs,subject,lastDate,htmlFromServer,isAttachement,isPdf,pdfurl,Url;
    // End Declaring layout button, edit texts
String message;
    // Declaring connection variables
    Connection con;
   DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_home_work);
        connectionClass = new ConnectionClass();

        Bundle bundle = getIntent().getExtras();
         message = bundle.getString("message");
       // TextView txtView = (TextView) findViewById(R.id.txtview);
        //txtView.setText(message);

     /*  ImageView i = (ImageView)findViewById(R.id.imgView11);


       Picasso.with(this).load("https://scoopak.com/wp-content/uploads/2013/06/free-hd-natural-wallpapers-download-for-pc.jpg").into(i);
        i.getLayoutParams().height = 400;

        i.getLayoutParams().width = 400;
        i.setPadding(10,10,10,10);

        i.setScaleType(ImageView.ScaleType.FIT_XY);*/

       /* ImageView imageView = new ImageView(this);
        imageView.setMaxHeight(50);
        imageView.setMaxWidth(100);

        Picasso.with(this).load("https://scoopak.com/wp-content/uploads/2013/06/free-hd-natural-wallpapers-download-for-pc.jpg").into(imageView);
        LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.linlay);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );


        relativeLayout.addView(imageView, layoutParams);*/


        CheckLogin checkLogin = new CheckLogin();
        checkLogin.execute("");

    }
    public class CheckLogin extends AsyncTask<String,String,String>
    {

        String z = "";
        Boolean isSuccess = false;
        ProgressDialog progress;
        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(DetailedHomeWork.this, "Synchronising",
                    "Listview Loading! Please Wait...", true);
            // progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r)
        {
            // progressBar.setVisibility(View.GONE);
            progress.dismiss();
            Toast.makeText(DetailedHomeWork.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Class = (TextView) findViewById(R.id.txtClass);
                Subject = (TextView) findViewById(R.id.txtSubject);
                LastDate = (TextView) findViewById(R.id.txtlastDate);
                Description = (TextView) findViewById(R.id.txt2);

                Description.setText(Html.fromHtml(htmlFromServer).toString());
                Class.setText(classs);
                Subject.setText(subject);
                LastDate.setText(lastDate);
                if(isAttachement.equals("True"))
                {
                    ImageView i = (ImageView)findViewById(R.id.imgView11);


                    Picasso.with(DetailedHomeWork.this).load(Url).into(i);
                    i.getLayoutParams().height = 1000;

                    i.getLayoutParams().width = 900;
                    i.setPadding(10,10,10,10);

                    i.setScaleType(ImageView.ScaleType.FIT_XY);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.laylinbb);

                    //set the properties for button
                    Button btnTag = new Button(DetailedHomeWork.this);
                    btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    btnTag.setText("Download IMAGE");
                   


                    //add button to the layout
                    layout.addView(btnTag);

                    btnTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(Url);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            Long reference = downloadManager.enqueue(request);
                        }
                    });
                }
                if(isPdf.equals("True"))
                {
                    LinearLayout layout = (LinearLayout) findViewById(R.id.laylinbb);

                    //set the properties for button
                    Button btnTag = new Button(DetailedHomeWork.this);
                    btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    btnTag.setText("Download PDF");


                    //add button to the layout
                    layout.addView(btnTag);
                    btnTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(pdfurl);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            Long reference = downloadManager.enqueue(request);
                            
                        }
                    });

                }



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
                    String query = "select * from HomeWork where Id='"+message+"'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs!=null)
                    {

                        while (rs.next()) {
                            htmlFromServer = rs.getString("Description");
                            classs = rs.getString("Class");
                            subject = rs.getString("Subject");
                            lastDate = rs.getString("LastDate");
                            isAttachement = rs.getString("IsAttachement");
                            Url = rs.getString("Url");
                            isPdf = rs.getString("IsPdf");
                            pdfurl = rs.getString("PdfUrl");
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
