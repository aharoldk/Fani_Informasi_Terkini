package aharoldk.finalproject.viewholder;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import aharoldk.finalproject.R;
import aharoldk.finalproject.clases.Products;

/**
 * Created by alwandy on 11/07/17.
 */

public class HealthViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgHealth;

    private TextView titleHealth;
    private TextView brandHeatlh;
    private TextView priceHealth;
    private TextView deskHealth;
    private TextView ratingHealth;

    public HealthViewHolder(View itemView) {
        super(itemView);

        imgHealth = itemView.findViewById(R.id.imgHealth);

        titleHealth = itemView.findViewById(R.id.titleHealth);
        brandHeatlh = itemView.findViewById(R.id.brandHeatlh);
        priceHealth = itemView.findViewById(R.id.priceHealth);
        deskHealth = itemView.findViewById(R.id.deskHealth);
        ratingHealth = itemView.findViewById(R.id.ratingHealth);

    }

    public void bind(final Products productse, final Activity activity) {

        Glide.with(itemView.getContext())
                .load(productse.getImage_link())
                .into(imgHealth);

        titleHealth.setText(productse.getName());
        brandHeatlh.setText(productse.getBrand());
        priceHealth.setText(productse.getPrice());
        deskHealth.setText(productse.getDescription());
        ratingHealth.setText(productse.getRating());
    }
}
