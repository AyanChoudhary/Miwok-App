package com.example.ayan.miwokapp;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhraseFragment extends ListFragment {
    PhraseAdapter adapter;
    MediaPlayer mMediaPlayer;
    AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mAudioChangeFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK && mMediaPlayer != null){

                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }

            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN && mMediaPlayer != null){
                mMediaPlayer.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS && mMediaPlayer != null){
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };


    public PhraseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<PhraseList> words = new ArrayList<>();
        words.add(new PhraseList("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new PhraseList("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new PhraseList("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new PhraseList("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new PhraseList("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new PhraseList("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new PhraseList("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new PhraseList("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new PhraseList("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new PhraseList("Come here.", "әnni'nem", R.raw.phrase_come_here));
        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        adapter = new PhraseAdapter(getActivity(), words);
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        getListView().findViewById(R.id.list);
        setListAdapter(adapter);
        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhraseList phraseList = words.get(position);

                int result = mAudioManager.requestAudioFocus(mAudioChangeFocusListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(getActivity(), phraseList.getmSound());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioChangeFocusListener);

        }
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }
}
