package projects.jatin.logoquiz2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import projects.jatin.logoquiz2.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(Splash.this,PlayOnline.class);
                startActivity(intent);
                finish();

            }
        },1500);
    }

}
