package shtainyky.com.calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import shtainyky.com.calculator.adapter.TabsFragmentAdapter;

public class DisplayCalculator extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_calculator);
        initToolbar();
        initTabs();
    }

    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);

    }

    private void initTabs() {
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(DisplayCalculator.this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tablayout = (TabLayout)findViewById(R.id.tablayout);
        tablayout.setupWithViewPager(viewPager);
    }
}
