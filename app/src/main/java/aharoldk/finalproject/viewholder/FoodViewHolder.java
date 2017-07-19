package aharoldk.finalproject.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import aharoldk.finalproject.R;
import aharoldk.finalproject.clases.APIResponse;
import aharoldk.finalproject.clases.Recipes;


/**
 * Created by alwandy on 10/07/17.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder {
    private TextView titleFood;
    private TextView publisherFood;
    private TextView rateFood;

    private ImageView imgFood;

    public FoodViewHolder(View itemView) {
        super(itemView);

        titleFood = (TextView) itemView.findViewById(R.id.titleFood);
        publisherFood = (TextView) itemView.findViewById(R.id.publisherFood);
        rateFood = (TextView) itemView.findViewById(R.id.rateFood);
        imgFood = (ImageView) itemView.findViewById(R.id.imgFood);
    }


    public void bind(final Recipes recipes) {
        Glide.with(itemView.getContext())
                .load(recipes.getImageUrl())
                .into(imgFood);

        titleFood.setText(recipes.getTitle());
        publisherFood.setText(recipes.getPublisher());
        rateFood.setText(recipes.getRate(recipes.getSocial_rank()));


    }
}
