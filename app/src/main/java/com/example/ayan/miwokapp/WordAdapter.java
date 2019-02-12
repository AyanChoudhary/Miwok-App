package com.example.ayan.miwokapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.ayan.miwokapp.R;
import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    int mResourceColor = 0;
    public WordAdapter(Context context, ArrayList<Word> words, int color_name){
        super(context,0, words);

        mResourceColor = color_name;
    }
    public WordAdapter(Context context, ArrayList<Word> words) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
    }

    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final Word currentWord = getItem(position);

        /** Handles playback of all the sound files */

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView defaultText = (TextView) listItemView.findViewById(R.id.text_1);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        defaultText.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView miwokText = (TextView) listItemView.findViewById(R.id.text_2);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        miwokText.setText(currentWord.getDefaultTranslation());

        ImageView accompImage = (ImageView) listItemView.findViewById(R.id.image_1);

        accompImage.setImageResource(currentWord.getmImage());

        FrameLayout linearLayout = listItemView.findViewById(R.id.list_item_layout);
        int color = ContextCompat.getColor(getContext(), mResourceColor);
        linearLayout.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}