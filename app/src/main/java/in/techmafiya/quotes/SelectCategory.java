package in.techmafiya.quotes;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelectCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        SharedPreferences.Editor editor = getSharedPreferences("splash", MODE_PRIVATE).edit();
        editor.putInt("home", 1);
        editor.commit();


    }
}
