package projects.jatin.logoquiz2.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by Jateen on 26-05-2017.
 */
public class OfflineGridViewAnswerAdapter extends BaseAdapter {

    private char[] answerCharacter;
    private Context context;

    public OfflineGridViewAnswerAdapter(char[] answerCharacter, Context context) {
        this.answerCharacter = answerCharacter;
        this.context = context;
    }

    @Override
    public int getCount() {
        return answerCharacter.length;
    }

    @Override
    public Object getItem(int position) {
        return answerCharacter[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if(convertView == null)
        {
            //Create new button
            button = new Button(context);
            button.setLayoutParams(new GridView.LayoutParams(85,85));
            button.setPadding(8,8,8,8);
            button.setBackgroundColor(Color.DKGRAY);
            button.setTextColor(Color.CYAN);
            button.setText(String.valueOf(answerCharacter[position]));
        }
        else
            button=(Button)convertView;
        return button;
    }
}
