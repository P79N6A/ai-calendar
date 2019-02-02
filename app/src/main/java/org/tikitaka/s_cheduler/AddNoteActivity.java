package org.tikitaka.s_cheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.tikitaka.s_cheduler.Fragment.HomeFragment;

import com.applandeo.materialcalendarview.CalendarView;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);

        final CalendarView datePicker = (CalendarView) findViewById(R.id.datePicker);
        Button button = (Button) findViewById(R.id.addNoteButton);
        final EditText noteEditText = (EditText) findViewById(R.id.noteEditText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();

                MyEventDay myEventDay = new MyEventDay(datePicker.getSelectedDate(), R.drawable.ic_today, noteEditText.getText().toString());

                returnIntent.putExtra(HomeFragment.RESULT, myEventDay);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}