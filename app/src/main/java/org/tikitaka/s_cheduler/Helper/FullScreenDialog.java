package org.tikitaka.s_cheduler.Helper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.tikitaka.s_cheduler.R;

import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;

public class FullScreenDialog extends DialogFragment implements View.OnClickListener {

    private Callback callback;
    private int day,month,year,hour,minute;
    private int day_x,month_x,year_x,hour_x,minute_x;

    static public FullScreenDialog newInstance() {
        return new FullScreenDialog();
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
        View v = inflater.inflate(R.layout.dialog_new_schedule, container, false);
        ImageButton close = v.findViewById(R.id.fullscreen_dialog_close);
        TextView edit = v.findViewById(R.id.fullscreen_dialog_edit);

        close.setOnClickListener(this);
        edit.setOnClickListener(this);

        SharedPreferences prefs = getActivity().getSharedPreferences("MY DATA", Activity.MODE_PRIVATE);

        String title=prefs.getString("My_Title","일정 제목");
        String location=prefs.getString("My_Location","일정 장소를 입력하세요");
        String email=prefs.getString("My_Email","e-mail");
        String url=prefs.getString("My_URL","URL");
        String memo=prefs.getString("My_Memo","MEMO");
        int tel=prefs.getInt("My_TEL",0);

        ((TextView) v.findViewById(R.id.titleLabel)).setText(title);
        ((TextView) v.findViewById(R.id.locationLabel)).setText(location);
        ((TextView) v.findViewById(R.id.emailLabel)).setText(email);
        ((TextView) v.findViewById(R.id.urlLabel)).setText(url);
        ((TextView) v.findViewById(R.id.memoLabel)).setText(memo);
        ((TextView) v.findViewById(R.id.telLabel)).setText(tel+"");

        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.fullscreen_dialog_close:
                dismiss();
                break;

            case R.id.fullscreen_dialog_edit:
                dismiss();
                showEdit(v);
                break;
        }

    }

    public interface Callback {

        void onActionClick(String name);

    }

    public void showEdit(View view){
        DialogFragment dialog = FullScreenDialog_edit.newInstance();
        dialog.show(getFragmentManager(), "");
    }

}
