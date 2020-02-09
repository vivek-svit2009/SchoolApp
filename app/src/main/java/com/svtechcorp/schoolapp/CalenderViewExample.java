package com.svtechcorp.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;


public class CalenderViewExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view_example);
        MaterialCalendarView calendarView = (MaterialCalendarView) findViewById(R.id.calenderView);

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                String mon =""+(date.getMonth()+1)+date.getYear();
                Toast.makeText(getApplicationContext(),mon,Toast.LENGTH_LONG).show();
            }
        });


    }
}
