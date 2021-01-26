package com.sample.waterreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ActivityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private WeekDateItem[] daysOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daysOfWeek = new WeekDateItem[7];

        addDates();
        buildRecyclerView();

    }

    public void addDates(){
        daysOfWeek[0] = new WeekDateItem("Monday");
        daysOfWeek[1] = new WeekDateItem("Tuesday");
        daysOfWeek[2] = new WeekDateItem("Wednesday");
        daysOfWeek[3] = new WeekDateItem("Thursday");
        daysOfWeek[4] = new WeekDateItem("Friday");
        daysOfWeek[5] = new WeekDateItem("Saturday");
        daysOfWeek[6] = new WeekDateItem("Sunday");
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.reminder_view);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ActivityAdapter(daysOfWeek);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter.setOnClickListener(new ActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(MainActivity.this,ChooseDateActivity.class));
                CustomIntent.customType(MainActivity.this,"left-to-right");
            }
        });

    }


}
