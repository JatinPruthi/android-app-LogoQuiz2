package projects.jatin.logoquiz2.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import projects.jatin.logoquiz2.R;

public class PlayOnline extends AppCompatActivity {


    public Button btnSubmit;

    public GridView gridViewAnswer,gridViewSuggest;

    public ImageView imgViewQuestion;

    public TextView textScore,showScore;

    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_online);


        gridViewAnswer = (GridView)findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView)findViewById(R.id.gridViewSuggest);
        imgViewQuestion = (ImageView)findViewById(R.id.imgLogo);
        textScore= (TextView) findViewById(R.id.textScore);
        showScore= (TextView) findViewById(R.id.showScore);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);


        final String pics[]={"facebook","whatsapp","mcdonalds","bmw"};

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==pics.length)
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(PlayOnline.this);
                    builder.setTitle("FINISH");
                    builder.setMessage("GAME COMPLETED");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(PlayOnline.this, "Well Done!", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(PlayOnline.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setCancelable(false);
                    builder.show();
                }
                else {
                    Glide.with(PlayOnline.this).load("http://logo.clearbit.com/" + pics[i] + ".com").into(imgViewQuestion);
                    i++;
                    
                }
            }
        });



    }
}
