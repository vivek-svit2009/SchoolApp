package com.svtechcorp.schoolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.svtechcorp.schoolapp.databinding.NavHeaderBinding;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {
    Button buttonProfile,buttonEducation,btnprofile;
    ImageView img;
    NavHeaderBinding binding;
    SharedPreferences sp1,sp;
    String name,email;
    private DrawerLayout drawer;
    ViewPager viewPager;
    swipeAdapter adapter;
    List<swipeModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board2);
      //  binding = DataBindingUtil.setContentView(DashBoard.this, R.layout.nav_header);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header, navigationView, false);
        navigationView.addHeaderView(headerView);


        TextView tn = (TextView) headerView.findViewById(R.id.txtName);
        TextView te = (TextView) headerView.findViewById(R.id.txtEmail);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        sp1 = getSharedPreferences("IdClass",MODE_PRIVATE);
        name = sp1.getString("Name","");
        email = sp1.getString("Email","");

        //Toast.makeText(DashBoard.this, name, Toast.LENGTH_SHORT).show();
        //Toast.makeText(DashBoard.this, email, Toast.LENGTH_SHORT).show();
       // binding.txtName.setText(name);
        //binding.txtEmail.setText(email);
        te.setText(email);
        tn.setText(name);

        drawer = findViewById(R.id.drawer_layout);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_logout:
                        sp.edit().clear().commit();
                        sp1.edit().clear().commit();
                        Intent intt = new Intent(DashBoard.this,MainActivity.class);
                        startActivity(intt);
                        finish();

                        Toast.makeText(DashBoard.this,"LoggedOut",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_message:
                        Toast.makeText(DashBoard.this,"Message Clicked",Toast.LENGTH_SHORT).show();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        models = new ArrayList<>();
        models.add(new swipeModel(R.drawable.brochure, "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        models.add(new swipeModel(R.drawable.sticker, "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
        models.add(new swipeModel(R.drawable.poster, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
        models.add(new swipeModel(R.drawable.namecard, "Namecard", "Business cards are cards bearing business information about a company or individual."));

        adapter = new swipeAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(70, 0, 70, 0);
        ImageView abc14 = (ImageView) findViewById(R.id.abc14);
        abc14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this,HomeWork.class);
                startActivity(intent);
            }
        });
        ImageView abc21 = (ImageView) findViewById(R.id.abc21);
        abc21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this,FeedBack.class);
                startActivity(intent);
            }
        });
       /* viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
      /*  img = (ImageView) findViewById(R.id.menuopen);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });*/


    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


}
