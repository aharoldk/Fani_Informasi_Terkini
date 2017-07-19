package aharoldk.finalproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import aharoldk.finalproject.R;
import aharoldk.finalproject.clases.Recipes;
import aharoldk.finalproject.viewholder.FoodViewHolder;

/**
 * Created by alwandy on 10/07/17.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    private List<Recipes> recipes = new ArrayList<>();

    public FoodAdapter(List<Recipes> recipes) {
        this.recipes = recipes;
    }


    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_food, parent, false);

        return new FoodViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        holder.bind(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
