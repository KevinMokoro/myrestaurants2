package com.moringaschool.myrestaurants2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mFindRestaurantsButton;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationEditText = (EditText)findViewById(R.id.locationEditText);
        mFindRestaurantsButton = (Button)findViewById(R.id.findRestaurantsButton);
        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_LONG).show();
                String location = mLocationEditText.getText().toString();
               // Toast.makeText(MainActivity.this, location, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);


            }
        });

    }
}