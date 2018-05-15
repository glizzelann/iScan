package com.example.asus.iscan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ProgressBar progressBar;
        progressBar = (ProgressBar) findViewById(R.id.load);

        Thread myThread= new Thread() {
            @Override
            public void run() {
                try{
                    sleep(3000);
                    Intent intent = new Intent(getApplication(), Iscan.class);
                    startActivity(intent);
                    finish();
                }

                catch(
                        InterruptedException e
                        )

                {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
