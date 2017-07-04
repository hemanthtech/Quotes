package in.techmafiya.quotes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;

import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import in.techmafiya.quotes.Adapter.QuoteAdapter;
import in.techmafiya.quotes.FirebaseInfo.FirebaseInfo;
import in.techmafiya.quotes.loading.CatLoadingView;
import in.techmafiya.quotes.model.Quotes;


public class Home extends AppCompatActivity implements View.OnClickListener, BottomSheetListener {

    private static final String TAG = "HOME";
    public QuoteAdapter adapter;
    public boolean chk1;
    ListView listView;
    CatLoadingView mView;
    RelativeLayout r;
    ArrayList<Quotes> QuoteList = new ArrayList<Quotes>();
    int a = 1, pos;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton2;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FullScreencall();

        if (chk1 == false) {
            chk1 = true;
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();
        initUIELements();
        updatedFirebaseCustomers();

    }

    private void initUIELements() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);  //Toolbar
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        Typeface custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/Exo-Bold.otf");
        mTitle.setTypeface(custom_font);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#77bdc3c7")));


//        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#25bdc3c7")));
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu); //Fab menu
        materialDesignFAM.setClosedOnTouchOutside(true);

        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2); //Fab Button
//        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        listView = (ListView) findViewById(R.id.Quotes_List_View);  //list view
        if (listView.getCount() < 1) {
            mView = new CatLoadingView();  //cat when data to be load
            mView.show(getSupportFragmentManager(), "");
            mView.setCancelable(false);
        }
        r = (RelativeLayout) findViewById(R.id.xy);
        Timer timer = new Timer();
        timer.schedule(new back(), 0, 4000);


        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                materialDesignFAM.close(true);

                Intent intent = new Intent(Home.this, PostQuote.class);
                startActivity(intent);
            }
        });
//        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu third item clicked
//
//            }
//        });


        adapter = new QuoteAdapter(this, QuoteList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;

                new BottomSheet.Builder(Home.this)
                        .setSheet(R.menu.grid_menu)
                        .grid()
                        .setTitle("\" " + QuoteList.get(position).getQuote() + " \" ")
                        .setListener(Home.this)
                        .show();
            }

        });
    }

    private void updatedFirebaseCustomers() {
        FirebaseDatabase.getInstance().getReference(FirebaseInfo.Quote_List).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Quotes quote = dataSnapshot.getValue(Quotes.class);
                if (listView.getCount() >= 1)
                    dismiss();
                if (quote.getLikes() <= 5) {
                    adapter.insert(quote, 0);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Quotes q = dataSnapshot.getValue(Quotes.class);
                int pos = adapter.getPosition(q);

                Log.d(TAG, "dataChanged: " + "Position : " + pos + dataSnapshot.getValue(Quotes.class));
                if (pos != -1) {

                    adapter.remove(q);
                    if (q.getLikes() < 5) {
                        adapter.insert(q, pos);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d("Quotes", "canceled: " + databaseError);
            }
        });
    }

    @Override
    public void onSheetShown(@NonNull BottomSheet bottomSheet) {
        Log.v(TAG, "onSheetShown");
    }

    @Override
    public void onSheetItemSelected(@NonNull BottomSheet bottomSheet, MenuItem item) {

        if (item.getItemId() == R.id.share) {
            Log.v(TAG, " " + QuoteList.get(pos).getQuote());

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            String temp = QuoteList.get(pos).getQuote() + "\n\n -" + QuoteList.get(pos).getAuthor() + "\n\n quotes.techmafiya.in";
            sendIntent.putExtra(Intent.EXTRA_TEXT, temp);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } else if (item.getItemId() == R.id.copy) {

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            String temp = QuoteList.get(pos).getQuote() + "\n\n -" + QuoteList.get(pos).getAuthor() + "\n\n quotes.techmafiya.in";
            ClipData clip = ClipData.newPlainText("Quotes ", temp);
            clipboard.setPrimaryClip(clip);
            {
            }
        } else if (item.getItemId() == R.id.report) {

            int likes = QuoteList.get(pos).getLikes();
            likes++;
            mDatabase.child(FirebaseInfo.Quote_List).child(QuoteList.get(pos).getId()).child(FirebaseInfo.Like).setValue(likes);
//            adapter.remove(QuoteList.get(pos));
        }
    }

    @Override
    public void onSheetDismissed(@NonNull BottomSheet bottomSheet, @BottomSheetListener.DismissEvent int dismissEvent) {

        Log.v(TAG, "onSheetDismissed " + dismissEvent);

        switch (dismissEvent) {
            case BottomSheetListener.DISMISS_EVENT_BUTTON_POSITIVE:
                Toast.makeText(this, "Positive Button Clicked", Toast.LENGTH_SHORT).show();
                break;

            case BottomSheetListener.DISMISS_EVENT_BUTTON_NEGATIVE:
                Toast.makeText(this, "Negative Button Clicked", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    protected void onResume() {
        FullScreencall();
        if (listView.getCount() >= 1)
            dismiss();
        chk1 = false;
        super.onResume();

    }

    @Override
    protected void onPause() {
        FullScreencall();
        super.onPause();
    }

    @Override
    public void onClick(View v) {


    }

    public void dismiss() {
        mView.dismiss();
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

    @Override
    public void onBackPressed() {
        finish();
        Log.d("back", "exitmodel");
        super.onBackPressed();
    }

    class back extends TimerTask {


        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (a == 1) {
                        r.setBackgroundResource(R.drawable.trans);
                    } else if (a == 2) {
                        r.setBackgroundResource(R.drawable.trans2);
                    } else if (a == 3) {
                        r.setBackgroundResource(R.drawable.trans3);
                    } else if (a == 4) {
                        r.setBackgroundResource(R.drawable.trans4);
                    } else if (a == 5) {
                        r.setBackgroundResource(R.drawable.trans5);
                    } else if (a == 6) {
                        r.setBackgroundResource(R.drawable.trans6);
                        a = 0;
                    }

                    TransitionDrawable trans = (TransitionDrawable) r.getBackground();
                    trans.startTransition(4000);
                    a++;
//stuff that updates ui

                }
            });

        }
    }
}


