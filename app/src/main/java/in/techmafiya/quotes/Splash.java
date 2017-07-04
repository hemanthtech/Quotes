package in.techmafiya.quotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

import in.techmafiya.quotes.OnBoard.OnBoard;


public class Splash extends AppCompatActivity {

    protected boolean _active = true;
    protected int _splashTime = 3000; // time to display the splash screen in ms
    TextView quote, slogan;
    int a = 1;
    RelativeLayout r1;
    ImageView im1;
    int home;
    SharedPreferences prefs1;
    ProgressBar pg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FullScreencall();

        prefs1 = getSharedPreferences("quotes", MODE_PRIVATE);

        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        quote = (TextView) findViewById(R.id.quote);
        slogan = (TextView) findViewById(R.id.slogan);
        r1 = (RelativeLayout) findViewById(R.id.r1);
        Timer timer = new Timer();
        timer.schedule(new back(), 0, 1000);
        Typeface custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/Exo-Regular.otf");
        quote.setTypeface(custom_font);
        slogan.setTypeface(custom_font);
        im1 = (ImageView) findViewById(R.id.image);
        pg1 = (ProgressBar) findViewById(R.id.pg);
        fristrun();


        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                            if (waited > _splashTime - 200) {
                                pg1.setVisibility(View.GONE);
                            }
                        }
                    }
                } catch (Exception e) {

                } finally {
                    {
                        startActivity(new Intent(Splash.this,
                                OnBoard.class));
                    }

                    finish();
                }
            }

        };
        splashTread.start();
    }

    public void fristrun() {
        if (prefs1.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs

            prefs1.edit().putBoolean("firstrun", false).commit();
        }
    }

    public void FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    class back extends TimerTask {


        public void run() {


            runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    if (a == 1) {
                        r1.setBackgroundResource(R.drawable.transsplash3);
                    } else if (a == 2) {
                        r1.setBackgroundResource(R.drawable.transsplash4);
                    } else if (a == 3) {
                        r1.setBackgroundResource(R.drawable.transsplash5);
                    } else if (a == 4) {
                        r1.setBackgroundResource(R.drawable.transsplash6);

                    } else if (a == 5) {
                        r1.setBackgroundResource(R.drawable.transsplash1);

                    } else if (a == 6) {
                        r1.setBackgroundResource(R.drawable.transsplash6);

                    } else if (a == 7) {
                        r1.setBackgroundResource(R.drawable.transsplash7);
                    } else if (a == 8) {
                        r1.setBackgroundResource(R.drawable.transsplash8);

                    } else if (a == 9) {
                        r1.setBackgroundResource(R.drawable.transsplash9);
                        a = 0;
                    }

                    TransitionDrawable trans = (TransitionDrawable) r1.getBackground();
                    trans.startTransition(1000);
                    a++;
//stuff that updates ui


                }
            });

        }
    }


}
