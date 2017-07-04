package in.techmafiya.quotes;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import in.techmafiya.quotes.Adapter.QuoteAdapter;
import in.techmafiya.quotes.FirebaseInfo.FirebaseInfo;
import in.techmafiya.quotes.model.Quotes;

public class PostQuote extends AppCompatActivity {
    FloatingActionButton fab;
    EditText quote_EditText;
    EditText author_EditText;
    TextView title, q1, q2;
    //    EditText category_EditText;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_quote);
        initUIELements();


    }

    private void initUIELements() {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setTitle("New Quote");
        actionBar.setDisplayHomeAsUpEnabled(true);

        quote_EditText = (EditText) findViewById(R.id.quote);
        author_EditText = (EditText) findViewById(R.id.author);
        q1 = (TextView) findViewById(R.id.q1);
        q2 = (TextView) findViewById(R.id.q2);
        Typeface custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/Exo-Regular.otf");

        quote_EditText.setTypeface(custom_font);
        author_EditText.setTypeface(custom_font);
        q1.setTypeface(custom_font);
        q2.setTypeface(custom_font);
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#01bdc3c7")));
//        category_EditText = (EditText) findViewById(R.id.category);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quote_EditText.getText().toString().matches("")) {
                    quote_EditText.setBackgroundResource(R.drawable.rect2);
                } else if (author_EditText.getText().toString().matches("")) {
                    String key = mDatabase.child(FirebaseInfo.Quote_List).push().getKey();
                    String author = "anonymous \uD83D\uDE0E";
                    Quotes q = new Quotes(key, quote_EditText.getText().toString(), author, 0, 0, 0, null);
                    mDatabase.child(FirebaseInfo.Quote_List).child(key).setValue(q);
                    finish();
                } else {
                    String key = mDatabase.child(FirebaseInfo.Quote_List).push().getKey();
                    Quotes q = new Quotes(key, quote_EditText.getText().toString(), author_EditText.getText().toString(), 0, 0, 0, null);
                    mDatabase.child(FirebaseInfo.Quote_List).child(key).setValue(q);
                    finish();
                }

//                Intent intent = new Intent(PostQuote.this,PostQuote.class);
//                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
