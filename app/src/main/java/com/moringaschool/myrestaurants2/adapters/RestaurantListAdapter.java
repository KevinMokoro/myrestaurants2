package com.moringaschool.myrestaurants2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.myrestaurants2.R;
import com.moringaschool.myrestaurants2.models.Business;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {
    private List<Business> mRestaurants;
    private Context mContext;

    public RestaurantListAdapter( Context context, List<Business> restaurants) {
        mContext = context;
        mRestaurants = restaurants;
    }

    @Override
    public RestaurantListAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item,parent,false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantListAdapter.RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(mRestaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.restaurantImageView) ImageView mRestaurantImageView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.restaurantNameTextView) TextView mRestaurantNameTextView;

        private Context mContext;

        public RestaurantViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        public void bindRestaurant(Business restaurant) {
            Picasso.get().load(restaurant.getImageUrl()).into(mRestaurantImageView);
            mRestaurantNameTextView.setText(restaurant.getName());
            mCategoryTextView.setText(restaurant.getCategories().get(0).getTitle());
            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        }
    }

}