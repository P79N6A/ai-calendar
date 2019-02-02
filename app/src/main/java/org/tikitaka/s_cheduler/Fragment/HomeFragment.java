package org.tikitaka.s_cheduler.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import org.tikitaka.s_cheduler.AddActivity;
import org.tikitaka.s_cheduler.AddNoteActivity;
import org.tikitaka.s_cheduler.MainActivity;
import org.tikitaka.s_cheduler.MyEventDay;
import org.tikitaka.s_cheduler.NotePreviewActivity;
import org.tikitaka.s_cheduler.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import com.applandeo.materialcalendarview.CalendarUtils;


public class HomeFragment extends Fragment {
    View v;
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;

    private CalendarView mCalendarView;
    private List<EventDay> mEventDays = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home, container, false);

        mCalendarView = (CalendarView)v.findViewById(R.id.calendarView);

        FloatingActionButton floatingActionButton = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        Calendar calendar = Calendar.getInstance();
        mEventDays.add(new EventDay(calendar, R.drawable.sample_icon));


       mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                previewNote(eventDay);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_NOTE && resultCode == getActivity().RESULT_OK) {
            MyEventDay myEventDay = data.getParcelableExtra(RESULT);
            try {
                mCalendarView.setDate(myEventDay.getCalendar());
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }
            mEventDays.add(myEventDay);
            mCalendarView.setEvents(mEventDays);
        }
    }

    private void addNote() {
        Intent intent = new Intent(getActivity(), AddNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE);
    }

    private void previewNote(EventDay eventDay) {
        Intent intent = new Intent(getActivity(),NotePreviewActivity.class);
        if(eventDay instanceof MyEventDay){
            intent.putExtra(EVENT, (MyEventDay)eventDay);
        }
        startActivity(intent);
    }
}