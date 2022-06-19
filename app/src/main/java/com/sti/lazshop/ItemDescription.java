package com.sti.lazshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carteasy.v1.lib.Carteasy;
public class ItemDescription extends AppCompatActivity {


    Carteasy cs = new Carteasy(); // TODO: Use an application-wide reference later.

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
        Button addButton = findViewById(R.id.Add_Button);

        itemName.setText(name);
        itemPrice.setText(price);
        imageView.setImageResource(image);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cs.add(name, "Name", name);
                cs.add(name, "Price", price);
                cs.add(name, "ProductImage", image);
                cs.commit(getApplicationContext());

                Toast.makeText(ItemDescription.this, "Item added to cart!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}