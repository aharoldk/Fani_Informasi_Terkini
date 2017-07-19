package aharoldk.finalproject.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aharoldk.finalproject.R;
import aharoldk.finalproject.clases.Products;
import aharoldk.finalproject.viewholder.HealthViewHolder;

/**
 * Created by alwandy on 11/07/17.
 */

public class HealthAdapter extends RecyclerView.Adapter<HealthViewHolder> {

    private Products[] productses;
    private Activity activity;

    public HealthAdapter(Products[] productses, Activity activity) {
        this.productses = productses;
        this.activity = activity;
    }

    @Override
    public HealthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_health, parent, false);

        return new HealthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HealthViewHolder holder, int position) {
        holder.bind(productses[position], activity);
    }

    @Override
    public int getItemCount() {
        return productses.length;
    }
}
