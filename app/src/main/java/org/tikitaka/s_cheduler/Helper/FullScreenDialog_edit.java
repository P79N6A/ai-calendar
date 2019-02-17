package org.tikitaka.s_cheduler.Helper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.tikitaka.s_cheduler.R;

import java.util.Calendar;

public class FullScreenDialog_edit extends DialogFragment implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Callback callback;
    private EditText titleInput;
    private EditText locationInput;
    private EditText emailInput;
    private EditText urlInput;
    private EditText memoInput;
    private SharedPreferences prefs;
    private int day,month,year,hour,minute;
    private int day_x,month_x,year_x,hour_x,minute_x;

    static public FullScreenDialog_edit newInstance() {
        return new FullScreenDialog_edit();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_new_schedule_edit, container, false);
        ImageButton close = v.findViewById(R.id.fullscreen_dialog_close);
        TextView save = v.findViewById(R.id.fullscreen_dialog_save);

        close.setOnClickListener(this);
        save.setOnClickListener(this);

        prefs=getActivity().getSharedPreferences("MY DATA", Activity.MODE_PRIVATE);
        String title=prefs.getString("My_Title","일정 제목");
        String location=prefs.getString("My_Location","일정 장소를 입력세요");
        String email=prefs.getString("My_Email","email@tikitaka.com");
        String url=prefs.getString("My_URL","https://tikitatka.com");
        String memo=prefs.getString("My_Memo","메모를 입력해주세요");
        //int tel=prefs.getInt("My_TEL",0);

        titleInput=(EditText) v.findViewById(R.id.titleInput);
        locationInput=(EditText) v.findViewById(R.id.locationInput);
        emailInput=(EditText) v.findViewById(R.id.emailInput);
        urlInput=(EditText) v.findViewById(R.id.urlInput);
        memoInput=(EditText) v.findViewById(R.id.memoInput);
       //telInput=(EditText) v.findViewById(R.id.telInput);

        titleInput.setText(title);
        locationInput.setText(location);
        emailInput.setText(email);
        urlInput.setText(url);
        memoInput.setText(memo);
        //telImput.setText(tel+"");


        Calendar c=Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),this,year,month,day);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),this,hour,minute,true);
        //timePickerDialog.show();

        TextView startDate = (TextView) v.findViewById(R.id.startDate);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        TextView endDate = (TextView) v.findViewById(R.id.endDate);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.fullscreen_dialog_close:
                dismiss();
                break;

            case R.id.fullscreen_dialog_save:
                saveData(v);
                //callback.onActionClick("일정 저장 완료");
                dismiss();
                break;

        }

    }

    private void saveData(View view) {
        String title=titleInput.getText().toString();
        String location=locationInput.getText().toString();
        String email=emailInput.getText().toString();
        String url=urlInput.getText().toString();
        String memo=memoInput.getText().toString();
        //int tel=Integer.parseInt(telInput.getText().toString());

        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("My_Title",title);
        editor.putString("My_Location",location);
        editor.putString("MMy_Email",email);
        editor.putString("My_URL",url);
        editor.putString("My_Memo",memo);
        //editor.putInt("My_TEL",tel);
        editor.apply();

        DialogFragment dialog = FullScreenDialog.newInstance();
        dialog.show(getFragmentManager(), "");

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        year_x=year;
        month_x=month+1;
        day_x=dayOfMonth;
        Calendar c=Calendar.getInstance();
        hour=c.get(Calendar.HOUR);
        minute=c.get(Calendar.MINUTE);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour_x=hourOfDay;
        minute_x=minute;
        Toast.makeText(this.getContext(),"year"+year_x+"\n"
                +"month"+month_x+"\n"+"day"+day_x+"\n"+"hour"+hour_x+"\n"+"hour"+minute_x+"\n",Toast.LENGTH_SHORT).show();
    }

    public interface Callback {

        void onActionClick(String name);

    }

}
