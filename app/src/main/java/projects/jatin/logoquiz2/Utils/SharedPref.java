package projects.jatin.logoquiz2.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jateen on 29-08-2017.
 */

public class SharedPref {

    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    int score;
    String name;
    String date;
    private String pattern = "dd-MM-yyyy";

    public SharedPref(Context context) {
        this.context = context;
        preferences=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editor=preferences.edit();
    }

    public SharedPref(int score, String name, String date) {
        this.score = score;
        this.name = name;
        this.date = date;
    }

    public int getScore() {

       return preferences.getInt("hiscore", Integer.parseInt(null));
    }

    public void setScore(int score) {
        editor.putInt("hiscore",score);
        editor.apply();
    }

    public String getName() {

        return preferences.getString("name",null);
    }

    public void setName(String name) {
        editor.putString("name", name);
        editor.apply();
    }

    public String getDate() {
        return preferences.getString("date",null);
    }

    public void setDate(String date) {
        editor.putString("date", new SimpleDateFormat(pattern).format(new Date()));
        editor.apply();
    }

    public void clear()
    {
        editor.clear();
        editor.apply();
    }
}
