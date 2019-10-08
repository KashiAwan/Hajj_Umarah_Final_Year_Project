package com.example.hajj_umarah_final_year_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem Homee,Timee,Weatherr,Qibla,Currencyy,Mina,Settingss,Ziarattt;
    ViewPager viewPager;
    PagerControl pagerControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayoutt);
        Homee=findViewById(R.id.Homee);
        Timee=findViewById(R.id.Timee);
        Weatherr=findViewById(R.id.Weatherr);
        Qibla=findViewById(R.id.Qibla);
        Currencyy=findViewById(R.id.Currencyy);
        Mina=findViewById(R.id.Mina);
        Settingss=findViewById(R.id.Settingss);

        viewPager=findViewById(R.id.Viewpagerr);

        pagerControl = new PagerControl(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerControl);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
