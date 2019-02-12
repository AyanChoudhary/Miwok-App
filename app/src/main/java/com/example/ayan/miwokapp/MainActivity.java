package com.example.ayan.miwokapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.ComponentName;
import android.widget.Button;

import com.example.ayan.miwokapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       TextView number = findViewById(R.id.numbers);

        View.OnClickListener numbers = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Numbers.class);
                startActivity(intent);
            }
        };
        number.setOnClickListener(numbers);

        TextView color = findViewById(R.id.colors);

        View.OnClickListener colors = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Colors.class);
                startActivity(intent);
            }
        };
        color.setOnClickListener(colors);

        TextView phrase = findViewById(R.id.phrases);

        View.OnClickListener phrases = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Phrases.class);
                startActivity(intent);
            }
        };
        phrase.setOnClickListener(phrases);

        TextView familyMember = findViewById(R.id.family);

        View.OnClickListener familyMembers = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FamilyMembers.class);
                startActivity(intent);
            }
        };
        familyMember.setOnClickListener(familyMembers);


    }
}
