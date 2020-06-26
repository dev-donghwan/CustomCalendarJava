package com.example.customcalendarjava.calendar;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;


import com.example.customcalendarjava.Callback;
import com.example.customcalendarjava.calendar.dataType.CustomDate;
import com.example.customcalendarjava.R;
import com.example.customcalendarjava.calendar.dataType.CustomToday;
import com.example.customcalendarjava.databinding.CustomCalendarBinding;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.customcalendarjava.calendar.CustomCalendarUtil.MonthMax;
import static com.example.customcalendarjava.calendar.CustomCalendarUtil.monthStartCheck;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.getInstance;

public class CustomCalendar extends ConstraintLayout {

    public static final String PREV_MONTH = "PREV_MONTH";
    public static final String THIS_MONTH = "THIS_MONTH";
    public static final String NEXT_MONTH = "NEXT_MONTH";

    private final int CALENDAR_MAX_COUNT = 42;

    private int PREV_MONTH_MAX;

    private CustomCalendarBinding binding;

    private Calendar cal;

    private CustomCalendarAdapter calendarAdapter;

    private int thisYear, thisMonth, prevMonthDaySize, oldPos = -1;

    private ArrayList<CustomDate> dayArray = new ArrayList<>();

    public CustomCalendar(Context context) {
        super(context);
        init();
    }

    public CustomCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.custom_calendar, this, false);

        cal = getInstance(); // Calendar.getInstance

        calendarAdapter = new CustomCalendarAdapter();

        binding.calendar.setAdapter(calendarAdapter);

        binding.calendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomDate posData = dayArray.get(position);
                final int pos;
                if (posData.getState().equals(PREV_MONTH) || posData.getState().equals(NEXT_MONTH)) {
                    pos = monthStartCheck(posData.getYear(), posData.getMonth()) + (posData.getDay() - 1);
                } else {
                    //current
                    pos = position;
                }
                Log.d("동환", "state : " + posData.getState() + ", month : " + posData.getMonth() + ", pos : " + pos);

                if (oldPos != -1) {
                    binding.calendar.getChildAt(oldPos).setBackground(ContextCompat.getDrawable(getContext(), R.color.calendar_background_color));
                }
                oldPos = position;

                if (posData.getState().equals(PREV_MONTH)) {
                    prevMonth(new Callback() {
                        @Override
                        public Void call() {
                            binding.calendar.performItemClick(binding.calendar.getChildAt(pos), pos, binding.calendar.getAdapter().getItemId(pos));
                            return null;
                        }
                    });

                } else if (posData.getState().equals(NEXT_MONTH)) {
                    nextMonth(new Callback() {
                        @Override
                        public Void call() {
                            binding.calendar.performItemClick(binding.calendar.getChildAt(pos), pos, binding.calendar.getAdapter().getItemId(pos));
                            return null;
                        }
                    });
                } else {
                    //posData.getState().equals(THIS_MONTH);
                    Log.d("동환", thisYear + "년" + thisMonth + "월" + posData.getDay() + "일");
                    binding.calendar.getChildAt(pos).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.item_calendar_selector));
                }
            }
        });

        binding.next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonth(new Callback() {
                    @Override
                    public Void call() {
                        int pos = monthStartCheck(thisYear, thisMonth);
                        binding.calendar.performItemClick(binding.calendar.getChildAt(pos), pos, binding.calendar.getAdapter().getItemId(pos));
                        return null;
                    }
                });
            }
        });

        binding.prev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                prevMonth(new Callback() {
                    @Override
                    public Void call() {
                        int pos = monthStartCheck(thisYear, thisMonth);
                        binding.calendar.performItemClick(binding.calendar.getChildAt(pos), pos, binding.calendar.getAdapter().getItemId(pos));
                        return null;
                    }
                });
            }
        });

        addView(binding.getRoot());
    }

    public void setCalendarDate(int year, int month, Callback callback) {
        thisYear = year;
        thisMonth = month;

        int prevYear, prevMonth;
        int nextYear, nextMonth;

        if (thisMonth == 1) {
            prevYear = thisYear - 1;
            prevMonth = 12;
            nextYear = thisYear;
            nextMonth = thisMonth + 1;
        } else if (thisMonth == 12) {
            prevYear = thisYear;
            prevMonth = thisMonth - 1;
            nextYear = thisYear + 1;
            nextMonth = 1;
        } else {
            prevYear = thisYear;
            prevMonth = thisMonth - 1;
            nextYear = thisYear;
            nextMonth = thisMonth + 1;
        }

        dayArray.clear();
        binding.title.setText(year + "년 " + month + "월");

        cal.set(year, month - 1, 1);

        PREV_MONTH_MAX = MonthMax(year, month - 1);

        StringBuffer prevDate = new StringBuffer("");
        StringBuffer currentDate = new StringBuffer("");
        StringBuffer nextDate = new StringBuffer("");
        prevDate.append(prevYear).append((prevMonth < 10) ? "0" + prevMonth : prevMonth);
        currentDate.append(thisYear).append((thisMonth < 10) ? "0" + thisMonth : thisMonth);
        nextDate.append(nextYear).append((nextMonth < 10) ? "0" + nextMonth : nextMonth);

        prevMonthDaySize = cal.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < prevMonthDaySize; i++) {
            //DAY_OF_WEEK SUNDAY TO SATURDAY (1 to 7)
            int prevDay = PREV_MONTH_MAX + (i + 1) - prevMonthDaySize;
            String prevDayString = prevDay < 10 ? "0" + prevDay : String.valueOf(prevDay);
            dayArray.add(new CustomDate(PREV_MONTH, prevYear, prevMonth, prevDay));
        }

        for (int i = 0; i < cal.getActualMaximum(DAY_OF_MONTH); i++) {
            //getActualMinimum(DAY_OF_MONTH) == firstDay
            //getActualMaximum(DAY_OF_MONTH) == lastDay
            int currentDay = (i + 1);
            String currentDayString = currentDay < 10 ? "0" + currentDay : String.valueOf(currentDay);
            dayArray.add(new CustomDate(THIS_MONTH, thisYear, thisMonth, currentDay));
        }

        if (dayArray.size() < CALENDAR_MAX_COUNT) {
            int index = 0;
            for (int i = dayArray.size(); i < CALENDAR_MAX_COUNT; i++) {
                int nextDay = ++index;
                dayArray.add(new CustomDate(NEXT_MONTH, nextYear, nextMonth, nextDay));
            }
        }

        calendarAdapter.changeData(dayArray);
        callback.call();
    }

    public void clickToday() {
        setCalendarDate(new CustomToday().getYear(), new CustomToday().getMonth(), new Callback() {
            @Override
            public Void call() {
                int pos = monthStartCheck(new CustomToday().getYear(), new CustomToday().getMonth()) + new CustomToday().getDay() - 1;
                binding.calendar.performItemClick(binding.calendar.getChildAt(pos), pos, binding.calendar.getAdapter().getItemId(pos));
                return null;
            }
        });
    }

    private void nextMonth(Callback callback) {
        if (thisMonth < 12) {
            thisMonth++;
        } else {
            thisYear++;
            thisMonth = 1;
        }
        setCalendarDate(thisYear, thisMonth, callback);
    }

    private void prevMonth(Callback callback) {
        if (thisMonth > 1) {
            thisMonth--;
        } else {
            thisYear--;
            thisMonth = 12;
        }
        setCalendarDate(thisYear, thisMonth, callback);
    }
}
