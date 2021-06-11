package com.example.covineedhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class ItemRequestActivity extends AppCompatActivity {
    ListView listView;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_request);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter(this));
    }

    private class MyAdapter extends BaseAdapter {
        Context mycontext1;
        String[] namesArr = {"Oxygen Concentrator", "Oxygen cylinder",
                "Blood - Group A+", "Blood - Group B-", "Paracetamol", "Vitamin tablets"};
        String[] priceArr = {"₹ 49", "₹ 39", "₹ 44", "₹ 41", "₹ 10 (Including GST)", "₹ 12 (Excluding GST)"};
        public MyAdapter(ItemRequestActivity ma) { mycontext1 = ma; }
        @Override
        public int getCount() { return namesArr.length; }
        @Override
        public Object getItem(int position) { return null; }
        @Override
        public long getItemId(int position) { return 0; }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(mycontext1).inflate(
                            R.layout.my_list_item, parent, false);
            TextView textView1 = view.findViewById(R.id.textView);
            textView1.setText(namesArr[position]);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setContentView(R.layout.item_details);
                    TextView textView1 = findViewById(R.id.textView);
                    TextView price = findViewById(R.id.price);
                    textView1.setText(namesArr[position]);
                    price.setText(priceArr[position]);

                    ImageView backBtn1;
                    backBtn1 = findViewById(R.id.backBtn);
                    backBtn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }
            } );
            return view;
        }
    }
}