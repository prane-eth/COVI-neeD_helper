package com.example.covineedhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    Button cowinTracker, requestItems, appDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer player = MediaPlayer.create(
                MainActivity.this, R.raw.guitar);
        player.start();

        bindViews();

        cowinTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this,
                            CowinTrackerActivity.class);
                    startActivity(intent);
                } catch (Exception e)   {
                    showToast(e.toString());
                }
            }
        });
        requestItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this,
                            ItemRequestActivity.class);
                    startActivity(intent);
                } catch (Exception e)   {
                    showToast(e.toString());
                }
            }
        });
        appDetails.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v)  {
                try {
                    Intent intent = new Intent(MainActivity.this,
                            AppDetailsActivity.class);
                    startActivity(intent);
                } catch (Exception e)   {
                    showToast(e.toString());
                }
            }
        });
    }
    void showToast(String txt)    {
        Toast.makeText(MainActivity.this, txt,
                Toast.LENGTH_SHORT).show();
    }
    void bindViews()    {
        cowinTracker = findViewById(R.id.cowinTracker);
        requestItems = findViewById(R.id.requestItems);
        appDetails = findViewById(R.id.appDetails);
    }
}