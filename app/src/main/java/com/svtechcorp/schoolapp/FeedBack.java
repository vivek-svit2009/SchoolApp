package com.svtechcorp.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FeedBack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
      final RatingBar mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        final TextView mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        final EditText mFeedback = (EditText) findViewById(R.id.etFeedback);
        Button mSendFeedback = (Button) findViewById(R.id.btnSubmit);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating())
                {
                    case 1:
                        mRatingScale.setText("Very Bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need Some Improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I Love It");
                        break;
                    default:
                        mRatingScale.setText("");

                }
            }
        });
        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mFeedback.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Fill in feedback text box",Toast.LENGTH_LONG).show();
                }
                else
                    {
                        mFeedback.setText("");
                        mRatingBar.setRating(0);
                        Toast.makeText(getApplicationContext(),"Thank You for sharing your feedback",Toast.LENGTH_LONG).show();
                    }
            }
        });

    }
}
