package com.example.customcalendarjava.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.customcalendarjava.Callback;
import com.example.customcalendarjava.calendar.dataType.CustomDate;
import com.example.customcalendarjava.R;

import java.util.ArrayList;

import static com.example.customcalendarjava.Util.pxToDp;
import static com.example.customcalendarjava.base.BaseApplication.getActualDeviceHeight;
import static com.example.customcalendarjava.base.BaseApplication.getDpi;
import static com.example.customcalendarjava.base.BaseApplication.getMetricsY;
import static com.example.customcalendarjava.base.BaseApplication.getNavigationBarHeight;
import static com.example.customcalendarjava.base.BaseApplication.getStatusBarHeight;
import static com.example.customcalendarjava.calendar.CustomCalendar.NEXT_MONTH;
import static com.example.customcalendarjava.calendar.CustomCalendar.PREV_MONTH;


public class CustomCalendarAdapter extends BaseAdapter {

    private ConstraintLayout body;
    private TextView day;
    private View top, middle, bot;

    private ArrayList<CustomDate> data = new ArrayList<>();

    public CustomCalendarAdapter() {
        for (int i = 0; i < 42; i++) {
            this.data.add(new CustomDate(null, -1, -1, -1));
        }
        notifyDataSetChanged();
    }

    public CustomCalendarAdapter(ArrayList<CustomDate> data) {
        this.data = data;
    }

    public void changeData(ArrayList<CustomDate> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_calendar_item_day, parent, false);
        }
        int totalVerticalSpacingSize = (int) (parent.getResources().getDimension(R.dimen.calendar_item_spacing) * 5);
        convertView.getLayoutParams().height = pxToDp(((getActualDeviceHeight() - totalVerticalSpacingSize) / 6));
        CustomDate posData = data.get(position);

        body = convertView.findViewById(R.id.body);
        day = convertView.findViewById(R.id.day);

        if (posData.getDay() < 0) {
            day.setText("");
        } else {
            day.setText(posData.getDay() + "");
        }

        if (posData.getState() != null) {
            if (posData.getState().equals(PREV_MONTH) || posData.getState().equals(NEXT_MONTH)) {
                day.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.calendar_out_day_color));
            } else {
                day.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.black));
            }
        }
        return convertView;
    }

}
