package com.sti.lazshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        String name = getIntent().getStringExtra("NAME");
        String price = getIntent().getStringExtra("ITEM_PRICE");
        int image = getIntent().getIntExtra("IMAGE",0);

        TextView itemName = findViewById(R.id.textView4);
        TextView itemPrice = findViewById(R.id.textView3);
        ImageView imageView = findViewById(R.id.imageView2);

        itemName.setText(name);
        itemPrice.setText(price);
        imageView.setImageResource(image);

    }
}