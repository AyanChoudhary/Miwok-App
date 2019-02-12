package com.example.ayan.miwokapp;

public class PhraseList {

    private String mDefaultTranslation;
    /** Miwok translation for the word */
    private String mMiwokTranslation;

    private int mSound;
    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     */
    public PhraseList(String defaultTranslation, String miwokTranslation, int accompSound){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSound = accompSound;
    }

    public PhraseList(String defaultTranslation, String miwokTranslation){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSound = R.raw.family_father;
    }
    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmSound() { return mSound; }
}

