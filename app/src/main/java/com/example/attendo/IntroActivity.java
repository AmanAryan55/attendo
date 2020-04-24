package com.example.attendo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class IntroActivity extends AppCompatActivity {

    private int TIMER=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);
        getSupportActionBar().hide();

        Intro obj=new Intro();
        obj.start();
    }

    public class Intro extends Thread
    {
        public void run()
        {
            try
            {
                sleep(1000*TIMER);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            Intent intent=new Intent(IntroActivity.this,MainActivity.class);
            startActivity(intent);
            IntroActivity.this.finish();
        }
    }
}
