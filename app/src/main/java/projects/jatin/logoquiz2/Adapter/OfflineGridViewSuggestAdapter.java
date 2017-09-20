package projects.jatin.logoquiz2.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.List;

import projects.jatin.logoquiz2.Activities.PlayOffline;
import projects.jatin.logoquiz2.Common.Common;

/**
 * Created by Jateen on 26-05-2017.
 */

public class OfflineGridViewSuggestAdapter extends BaseAdapter {

    private List<String> suggestSource;
    private Context context;
    public PlayOffline mainActivity;


    public OfflineGridViewSuggestAdapter(List<String> suggestSource, Context context, PlayOffline mainActivity) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        if(convertView == null)
        {
            if(suggestSource.get(position).equals("null"))
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
            }
            else
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(suggestSource.get(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //If correct answer contains character user selected
                        if(String.valueOf(mainActivity.answer).contains(suggestSource.get(position)))
                        {
                            char compare = suggestSource.get(position).charAt(0); // Get char

                            for(int i =0;i<mainActivity.answer.length;i++)
                            {
                                if(compare == mainActivity.answer[i])
                                    Common.user_submit_answer[i]=compare;
                            }

                            //Update UI
                            OfflineGridViewAnswerAdapter answerAdapter = new OfflineGridViewAnswerAdapter(Common.user_submit_answer,context);
                            mainActivity.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            //Remove from suggest source
                            mainActivity.suggestSource.set(position,"null");
                            mainActivity.suggestAdapter = new OfflineGridViewSuggestAdapter(mainActivity.suggestSource,context,mainActivity);
                            mainActivity.gridViewSuggest.setAdapter(mainActivity.suggestAdapter);
                            mainActivity.suggestAdapter.notifyDataSetChanged();

                            //Increase the score
                           mainActivity.score+=5;
                           mainActivity.showScore.setText(""+mainActivity.score);
                        }
                        else // else
                        {
                            //Remove from suggest source
                            mainActivity.suggestSource.set(position,"null");
                            mainActivity.suggestAdapter = new OfflineGridViewSuggestAdapter(mainActivity.suggestSource,context,mainActivity);
                            mainActivity.gridViewSuggest.setAdapter(mainActivity.suggestAdapter);
                            mainActivity.suggestAdapter.notifyDataSetChanged();


                            //Decrease the score
                           mainActivity.score-=2;
                           mainActivity.showScore.setText(""+mainActivity.score);

                        }
                    }
                });
            }
        }
        else
            button = (Button)convertView;
        return button;

    }
}
