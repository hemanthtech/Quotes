package in.techmafiya.quotes.Adapter;

import android.app.Activity;
import android.content.ClipData;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.techmafiya.quotes.R;
import in.techmafiya.quotes.model.Quotes;

/**
 * Created by ABCD on 21-Aug-16.
 */
public class QuoteAdapter extends ArrayAdapter<Quotes> {

    private static final String TAG = "Quotes";
    private final Activity activity;
    public long temp;
    public ViewHolder holder;
    List<Quotes> quotesList = new ArrayList<Quotes>();
    Random ran = new Random();
    int x = ran.nextInt(3) + 1;
    //    int x =1;
    int Pos;

    public QuoteAdapter(Activity activity,
                        List<Quotes> quotesList) {
        super(activity, R.layout.quote_item_list, quotesList);
        this.activity = activity;
        this.quotesList = quotesList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.quote_item_list, null, true);
            holder = new ViewHolder();
            holder.quoteTextView = (TextView) view.findViewById(R.id.quote);
            holder.authorTextView = (TextView) view.findViewById(R.id.author);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        if (quotesList.get(position).getColor() > 0) {
            x = quotesList.get(position).getColor();
        }
        quotesList.get(position).setColor(x);
        switch (x) {
            case 1: {
                holder.quoteTextView.setBackgroundResource(R.color.Blue);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.Blue));
                x++;

            }
            break;
            case 2: {

                holder.quoteTextView.setBackgroundResource(R.color.red);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.red));
                x++;
            }
            break;
            case 3: {
                holder.quoteTextView.setBackgroundResource(R.color.DarkGreen);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.DarkGreen));
                x++;
            }
            break;
            case 4: {

                holder.quoteTextView.setBackgroundResource(R.color.BluishGrey);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.BluishGrey));
                x++;

            }
            break;
            case 5: {
                holder.quoteTextView.setBackgroundResource(R.color.purple);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.purple));


                x++;
            }
            break;
            case 6: {
                holder.quoteTextView.setBackgroundResource(R.color.pink);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.pink));

                x++;
            }
            break;

            case 7: {
                holder.quoteTextView.setBackgroundResource(R.color.LightYellow);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.LightYellow));
                x++;

            }
            break;

            case 8: {
                holder.quoteTextView.setBackgroundResource(R.color.Brown);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.Brown));
                x++;
            }

            break;
            case 9: {
                holder.quoteTextView.setBackgroundResource(R.color.redDark);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.redDark));
                x++;
            }
            break;
            case 10: {
                holder.quoteTextView.setBackgroundResource(R.color.BlackRedish);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.BlackRedish));
                x++;
            }
            break;
            case 11: {
                holder.quoteTextView.setBackgroundResource(R.color.DarkBlue);
                holder.quoteTextView.setTextColor(ContextCompat.getColor(activity, R.color.white));
                holder.authorTextView.setTextColor(ContextCompat.getColor(activity, R.color.DarkBlue));
                x = 1;
            }
            break;
        }

        font();

        holder.quoteTextView.setText(quotesList.get(position).getQuote());
        holder.authorTextView.setText(quotesList.get(position).getAuthor());

        return view;
    }

    public void font() {
        int x = ran.nextInt(15) + 0;

        switch (x) {
            case 1: {
                Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Exo-Regular.otf");
                holder.quoteTextView.setTypeface(custom_font);
            }
            break;
            case 2: {
                Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/KaushanScript-Regular.otf");
                holder.quoteTextView.setTypeface(custom_font);
            }
            break;
            case 3: {
                Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Muli-Light.ttf");
                holder.quoteTextView.setTypeface(custom_font);
            }
            break;
            case 4: {
                Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Sofia-Regular.otf");
                holder.quoteTextView.setTypeface(custom_font);
            }
            break;
            case 5: {
                Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Pacifico.ttf");
                holder.quoteTextView.setTypeface(custom_font);
            }
            break;
        }
    }

    static class ViewHolder {

        TextView quoteTextView;
        TextView authorTextView;

    }
}
