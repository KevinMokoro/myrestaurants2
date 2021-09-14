package com.moringaschool.myrestaurants2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.myrestaurants2.R;
import com.moringaschool.myrestaurants2.adapters.RestaurantListAdapter;
import com.moringaschool.myrestaurants2.models.Business;
import com.moringaschool.myrestaurants2.models.YelpBusinessesSearchResponse;
import com.moringaschool.myrestaurants2.network.YelpApi;
import com.moringaschool.myrestaurants2.network.YelpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantListActivity extends AppCompatActivity {
    //private TextView mLocationTextView;
    //private ListView mListView;
    //@BindView(R.id.locationTextView) TextView mLocationTextView;
 //   @BindView(R.id.listView) ListView mListView; ---due to recycler view they are in the adapter
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressbar;

    private RestaurantListAdapter mAdapter;

    public List<Business> restaurants;
  //  private String[] restaurants = new String[] {"Mi Mero Mole", "Mother's Bistro",
   //         "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
  //          "Slappy Cakes", "Equinox", "Miss Delta's", "Andina",
   //         "Lardo", "Portland City Grill", "Fat Head's Brewery",
   //         "Chipotle", "Subway"};
   // private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast", "Mexican"};


    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your internet connection and try again." );
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Try agin later.");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar() {
        mProgressbar.setVisibility(View.GONE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
      //  mLocationTextView = (TextView)findViewById(R.id.locationTextView);
       // mListView = (ListView) findViewById(R.id.listView);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
   //     mLocationTextView.setText("Here are all restaurants near: " + location);

   //     MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines);
    //    mListView.setAdapter(adapter);

  //      mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
   //         @Override
      //      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         //       String restaurant = ((TextView)view).getText().toString();
        //        Toast.makeText(RestaurantActivity.this, restaurant, Toast.LENGTH_LONG).show();
       //     }
      //  });

        YelpApi client = YelpClient.getClient();
        Call<YelpBusinessesSearchResponse> call = client.getRestaurants(location, "restaurants");
        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                hideProgressbar();
                if (response.isSuccessful()){
                    restaurants = response.body().getBusinesses();
                    mAdapter = new RestaurantListAdapter(RestaurantListActivity.this, restaurants);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RestaurantListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showRestaurants();
             //       MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(RestaurantActivity.this, android.R.layout.simple_list_item_1, restaurants, categories);
             //       mListView.setAdapter(adapter);
                } else{
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                hideProgressbar();
                showFailureMessage();
            }
        });




    }
}




 //   hideProgressbar();
//                if (response.isSuccessful()){
 //                       List<Business> restaurantsList = response.body().getBusinesses();
  //      String[] restaurants = new String[restaurantsList.size()];
  //      String[] categories = new String[restaurantsList.size()];

 //       for (int i = 0; i < restaurants.length; i++) {
 //       restaurants[i] = restaurantsList.get(i).getName();
  //      }
 //       for (int i = 0; i < categories.length; i++) {
  //      Category category = restaurantsList.get(i).getCategories().get(0);
  //      categories[i] = category.getTitle();
  //      }

   //     MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(RestaurantActivity.this, android.R.layout.simple_list_item_1, restaurants, categories);
   //     mListView.setAdapter(adapter);

    //    showRestaurants();
   //     } else{
   //     showUnsuccessfulMessage();
   //     }
    //    }
