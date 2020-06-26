package com.example.customcalendarjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.customcalendarjava.base.BaseActivity;
import com.example.customcalendarjava.calendar.dataType.CustomToday;
import com.example.customcalendarjava.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        binding.calendar.setCalendarDate(new CustomToday().getYear(), new CustomToday().getMonth(), new Callback() {
            @Override
            public Void call() {
                //비동기처리 해결방법을 찾기전까지 막아둔다.
                //binding.calendar.clickToday();
                return null;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_today:
                binding.calendar.clickToday();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
