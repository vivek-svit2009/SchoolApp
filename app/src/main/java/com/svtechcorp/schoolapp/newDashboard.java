package com.svtechcorp.schoolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class newDashboard extends AppCompatActivity {
    ViewPager viewPager;
    swipeAdapter adapter;
    List<swipeModel> models;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dashboard);
        models = new ArrayList<>();
        models.add(new swipeModel(R.drawable.brochure, "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        models.add(new swipeModel(R.drawable.sticker, "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
        models.add(new swipeModel(R.drawable.poster, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
        models.add(new swipeModel(R.drawable.namecard, "Namecard", "Business cards are cards bearing business information about a company or individual."));

        adapter = new swipeAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(70, 0, 70, 0);


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

    }
}
