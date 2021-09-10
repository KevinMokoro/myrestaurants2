package com.moringaschool.myrestaurants2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.myrestaurants2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   // private Button mFindRestaurantsButton;
    @BindView(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
   // private EditText mLocationEditText;
    @BindView(R.id.locationEditText) EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // mLocationEditText = (EditText)findViewById(R.id.locationEditText);
        // mFindRestaurantsButton = (Button)findViewById(R.id.findRestaurantsButton);
        ButterKnife.bind(this);
        mFindRestaurantsButton.setOnClickListener(this);
    }

            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_LONG).show();
                if (v == mFindRestaurantsButton) {
                    String location = mLocationEditText.getText().toString();
                    // Toast.makeText(MainActivity.this, location, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);

                }
            }



}