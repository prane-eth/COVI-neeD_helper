package com.example.covineedhelper;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.*;
import java.util.*;

public class CowinTrackerActivity extends AppCompatActivity {
    EditText pinCodeET;
    Button changePin, trackNow, trackHistory;
    String pinCodeFile = "pincode.txt";
    String historyFile = "tracking_history.txt";
    String pinCode;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cowin_tracker);
        bindViews();

        pinCode = readPinCode();  // try to read pinCode from file
        if (pinCode.length() != 6)  // if not exists in file, ask user to enter
            showToast("Enter pin code");
        else  // add to editText
            pinCodeET.setText(pinCode);

        changePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinCode = pinCodeET.getText().toString();  // read from EditText
                if (pinCode.length() == 6) {
                    writePinCode(pinCode);  // write to File
                    showToast("Successfully stored details");
                }
                else
                    showToast("Pin code must have only 6 digits");
            }
        });

        trackNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinCode = pinCodeET.getText().toString();  // read from EditText
                if (pinCode.length() == 6) {
                    writePinCode(pinCode);  // write to File
                    showToast("Loading... Please wait");
                    String txt = trackVaccines(pinCode);
                    addHistory(txt);
                    displayText(txt);
                }
                else
                    showToast("Pin code must have only 6 digits");
            }
        });
        trackHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String history = readHistory();
                history = "This is the stored data: \n" + history;
                displayText(history);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    String trackVaccines(String pinCode)   {  // main function
        Date cal = Calendar.getInstance().getTime();
        SimpleDateFormat datetime_form = new SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.getDefault());
        SimpleDateFormat date_form = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = datetime_form.format(cal);

        int total = 0;
        // String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="
        // + pinCode + "&date=" + formattedDate;
        // String res = loadURL(url);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(cal);
        String date1 = date_form.format(cal1.getTime());

        cal1.add(Calendar.DAY_OF_MONTH, 1);
        String date2 = date_form.format(cal1.getTime());

        cal1.add(Calendar.DAY_OF_MONTH, 1);
        String date3 = date_form.format(cal1.getTime());

        return "Tracked on: " + formattedDate + "\n\n" +
                date1 + " - 0 \n" +
                date2 + " - 2 \n" +
                date3 + " - 1 \n" +
                "------------------------------------------\n";
    }
    String loadURL(String url)  {
        try {
            Scanner scanner = new Scanner(
                    new URL(url).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (Exception e) {
            showToast("Error: " + e.toString());
            return "";
        }
    }
    void displayText(String txt)    {
        // display text using intent & second activity
        try {
            Intent intent = new Intent(CowinTrackerActivity.this,
                    DisplayActivity.class);
            intent.putExtra("txt", txt);
            startActivity(intent);
        } catch (Exception e)   {
            showToast(e.toString());
        }
    }
    String readPinCode()    {
        try {
            FileInputStream fis = openFileInput(pinCodeFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String msg = br.readLine();
            return msg;
        } catch (Exception e) {
            return "";
        }
    }
    boolean writePinCode(String pinCode) {
        if (pinCode.length() != 6)
            return false;
        try {
            FileOutputStream fos = openFileOutput(pinCodeFile, MODE_APPEND);
            fos.write(pinCode.getBytes());
            return true;
        } catch (Exception e)   {
            return false;
        }
    }
    String readHistory()    {
        try {
            FileInputStream fis = openFileInput(historyFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String msg;
            StringBuilder sb = new StringBuilder();
            while ( (msg=br.readLine()) != null ) {
                sb.append(msg);
                sb.append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }
    boolean addHistory(String history)    {
        try {
            FileOutputStream fos = openFileOutput(historyFile, MODE_APPEND);
            fos.write(history.getBytes());
            fos.write((byte)'\n');  // new line
            showToast("Added tracking to history");
            return true;
        } catch (Exception e)   {
            return false;
        }
    }
    void bindViews()    {
        pinCodeET = findViewById(R.id.pinCode);
        changePin = findViewById(R.id.changePin);
        trackNow = findViewById(R.id.trackNow);
        trackHistory = findViewById(R.id.trackHistory);
        backBtn = findViewById(R.id.backBtn);
    }
    void showToast(String txt)    {
        Toast.makeText(CowinTrackerActivity.this, txt,
                Toast.LENGTH_SHORT).show();
    }
}