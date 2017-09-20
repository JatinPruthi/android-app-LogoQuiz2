package projects.jatin.logoquiz2.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import es.dmoral.toasty.Toasty;
import projects.jatin.logoquiz2.Adapter.OfflineGridViewAnswerAdapter;
import projects.jatin.logoquiz2.Adapter.OfflineGridViewSuggestAdapter;
import projects.jatin.logoquiz2.Common.Common;
import projects.jatin.logoquiz2.R;

public class PlayOffline extends AppCompatActivity {
    public List<String> suggestSource = new ArrayList<>();

    public OfflineGridViewAnswerAdapter answerAdapter;
    public OfflineGridViewSuggestAdapter suggestAdapter;

    public Button btnSubmit;

    public GridView gridViewAnswer,gridViewSuggest;

    public ImageView imgViewQuestion;

    public TextView textScore,showScore;

    AlertDialog dialog;

//  final Dialog dialog = new Dialog(MainActivity.this);

    public int score=0;

    int[] image_list= {

            R.drawable.android, R.drawable.apple, R.drawable.blogger, R.drawable.deviantart,
            R.drawable.digg, R.drawable.dropbox, R.drawable.evernote, R.drawable.facebook,
            R.drawable.flickr, R.drawable.google, R.drawable.googleplus,R.drawable.hyves,
            R.drawable.instagram, R.drawable.linkedin, R.drawable.myspace, R.drawable.picasa,
            R.drawable.pinterest, R.drawable.reddit, R.drawable.rss, R.drawable.skype,
            R.drawable.soundcloud,R.drawable.stumbleupon, R.drawable.twitter,R.drawable.vimeo,
            R.drawable.whatsapp,R.drawable.wordpress, R.drawable.yahoo,R.drawable.youtube
    };

    public char[] answer;

    String correct_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_offline);
//        imgViewQuestion = (ImageView)findViewById(R.id.imgLogo);

        initView();
       /* AlertDialog.Builder builder;
        View dialog_view= getLayoutInflater().inflate(R.layout.intro,null);
        Button play= (Button) dialog_view.findViewById(R.id.intro_play);
        Button exit= (Button) dialog_view.findViewById(R.id.intro_exit);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(PlayOffline.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(PlayOffline.this);
        }
        builder.setCancelable(false);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initView();
                dialog.dismiss();

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        builder.setView(dialog_view);
        dialog=builder.create();
        dialog.show();*/
        /*builder.setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        initView();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        finish();
                        System.exit(0);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        //Init View
        */



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

   /* public void openDialog() {

        dialog.setContentView(R.layout.intro);
        dialog.setTitle("WELCOME!");
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }*/

    private void initView() {

        gridViewAnswer = (GridView)findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView)findViewById(R.id.gridViewSuggest);
        imgViewQuestion = (ImageView)findViewById(R.id.imgLogo);
        textScore= (TextView) findViewById(R.id.textScore);
        showScore= (TextView) findViewById(R.id.showScore);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        //Add SetupList Here
        setupList();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="";

                for(int i = 0; i< Common.user_submit_answer.length; i++)
                    result+=String.valueOf(Common.user_submit_answer[i]);
                if(result.equals(correct_answer))
                {
                    Toasty.success(getApplicationContext(),"    Good Job! \n"+result+" is the correct answer!",Toast.LENGTH_SHORT).show();

                    //Reset
                    Common.count = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];

                    //Set Adapter
                    OfflineGridViewAnswerAdapter answerAdapter = new OfflineGridViewAnswerAdapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    OfflineGridViewSuggestAdapter suggestAdapter = new OfflineGridViewSuggestAdapter(suggestSource,getApplicationContext(),PlayOffline.this);
                    gridViewSuggest.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                    setupList();
                }
                else
                {
                    Vibrator vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    vibrator.vibrate(300);
                    Toasty.error(PlayOffline.this, "Wrong Answer!\n   TRY AGAIN!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void setupList() {
        //Random logo
        Random random = new Random();
        int imageSelected = image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);

        correct_answer = getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);

        answer = correct_answer.toCharArray();

        Common.user_submit_answer = new char[answer.length];

        //Add Answer character to List
        suggestSource.clear();
        for(char item:answer)
        {
            //Add logo name to list
            suggestSource.add(String.valueOf(item));
        }

        //Random add some character to list
        for(int i = answer.length;i<answer.length*2;i++)
            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);

        //Sort random
        Collections.shuffle(suggestSource);

        //Set for GridView
        answerAdapter = new OfflineGridViewAnswerAdapter(setupNullList(),this);
        suggestAdapter = new OfflineGridViewSuggestAdapter(suggestSource,this,this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);


    }

    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for(int i=0;i<answer.length;i++)
            result[i]=' ';
        return result;
    }


    public void info(View view) {

        AlertDialog.Builder builder;
        View dialog_view= getLayoutInflater().inflate(R.layout.info,null);
        Button okay= (Button) dialog_view.findViewById(R.id.info_ok);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder = new AlertDialog.Builder(PlayOffline.this, android.R.style.Theme_Material_Dialog_Alert);
//        } else {
            builder = new AlertDialog.Builder(PlayOffline.this);
//        }
        builder.setCancelable(false);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        builder.setView(dialog_view);
        dialog=builder.create();
        dialog.show();


    }
}
