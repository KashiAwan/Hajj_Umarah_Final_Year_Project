package com.example.hajj_umarah_final_year_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread td = new Thread() {
            public void run()
            {
                try{
                    sleep(3000);

                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }finally {
                    Intent it= new Intent(Splash.this,MainActivity.class);
                    startActivity(it);

                }
            }
        };td.start();
    }
}
