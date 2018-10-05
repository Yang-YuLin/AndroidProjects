package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Yangyulin on 2018/10/5.
 */
public class TimePickerFragment extends DialogFragment{

    public static final String EXTRA_HOUR = "com.bignerdranch.android.criminalintent.hour";
    public static final String EXTRA_MINUTE = "com.bignerdranch.android.criminalintent.minute";

    private static final String ARG_HOUR = "hour";
    private static final String ARG_MINUTE = "minute";

    private TimePicker mTimePicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hour = (int) getArguments().getSerializable(ARG_HOUR);
        int minute = (int) getArguments().getSerializable(ARG_MINUTE);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time,null);

        mTimePicker = v.findViewById(R.id.dialog_time_picker);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        }else{
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.time_picker_title).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hour,minute;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    hour = mTimePicker.getHour();
                    minute = mTimePicker.getMinute();
                }else{
                    hour = mTimePicker.getCurrentHour();
                    minute = mTimePicker.getCurrentMinute();
                }
                sendResult(Activity.RESULT_OK,hour,minute);
            }
        }).create();
    }

    private void sendResult(int resultCode,int hour,int minute){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_HOUR,hour);
        intent.putExtra(EXTRA_MINUTE,minute);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }

    public static TimePickerFragment newInstance(int hour,int minute){
        Bundle args = new Bundle();
        args.putSerializable(ARG_HOUR,hour);
        args.putSerializable(ARG_MINUTE,minute);

        TimePickerFragment fragment =new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
