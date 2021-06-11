package com.example.covineedhelper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.*;

public class DisplayActivity extends AppCompatActivity {
    ImageView backBtn;
    TextView textView;
    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        backBtn = findViewById(R.id.backBtn);
        textView = findViewById(R.id.textView);

        txt = (String) getIntent().getStringExtra("txt");
        if (txt.length() > 1)
            textView.setText(txt);
        textView.setMovementMethod(new ScrollingMovementMethod());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}