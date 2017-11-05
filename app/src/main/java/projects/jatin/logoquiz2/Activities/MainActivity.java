package projects.jatin.logoquiz2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.jatin.logoquiz2.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_offline)
    Button btn_offline;

    @BindView(R.id.btn_online)
    Button btn_online;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this)

        btn_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PlayOnline.class);
                startActivity(intent);
            }
        });

        btn_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,PlayOffline.class);
                startActivity(intent);
            }
        });
    }
}
