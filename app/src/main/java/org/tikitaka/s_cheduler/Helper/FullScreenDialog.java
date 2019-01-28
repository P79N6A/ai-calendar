package org.tikitaka.s_cheduler.Helper;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.tikitaka.s_cheduler.R;

public class FullScreenDialog extends DialogFragment implements View.OnClickListener {

    private Callback callback;

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
        View view = inflater.inflate(R.layout.dialog_new_schedule, container, false);
        ImageButton close = view.findViewById(R.id.fullscreen_dialog_close);
        TextView save = view.findViewById(R.id.fullscreen_dialog_save);

        close.setOnClickListener(this);
        save.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.fullscreen_dialog_close:
                dismiss();
                break;

            case R.id.fullscreen_dialog_save:
                callback.onActionClick("일정 저장 완료");
                dismiss();
                break;

        }

    }

    public interface Callback {

        void onActionClick(String name);

    }

}
