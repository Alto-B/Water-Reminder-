package com.sample.waterreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ChooseDateActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    private TextView mTimePicker;
    private Spinner waterChoice, repeatReminder;
    private Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);

        mTimePicker = findViewById(R.id.reminder_time_picker);
        waterChoice = findViewById(R.id.water_type_spinner);
        repeatReminder = findViewById(R.id.repeat_date_Spinner);
        savebtn = findViewById(R.id.save_reminder);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.waterOptions,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterRepeat = ArrayAdapter.createFromResource(this,R.array.repeatOptions,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterRepeat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        repeatReminder.setAdapter(adapterRepeat);
        repeatReminder.setOnItemSelectedListener(this);

        waterChoice.setAdapter(adapter);
        waterChoice.setOnItemSelectedListener(this);


        mTimePicker.setOnClickListener(v -> {
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "Time Picker");
        });

        savebtn.setOnClickListener(v -> {
            Calendar d = Calendar.getInstance();

            if(waterChoice.getSelectedItem().toString().equals("Drink Water")){
                startAlarmDrink(d);
            }else{
                startAlarmBoil(d);
            }

            startActivity(new Intent(ChooseDateActivity.this,MainActivity.class));
        });


    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String am_pm = "";

        Calendar datetime = Calendar.getInstance();

        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        datetime.set(Calendar.MINUTE, minute);
        datetime.set(Calendar.SECOND,0);

        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";

        mTimePicker.setText( String.format("%02d:%02d",hourOfDay,minute) + am_pm);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void startAlarmDrink(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(ChooseDateActivity.this, AlertDrinkWater.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ChooseDateActivity.this,1,intent,0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

    }

    public void startAlarmBoil(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(ChooseDateActivity.this, AlertBoilWater.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ChooseDateActivity.this,2,intent,0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

    }
}
