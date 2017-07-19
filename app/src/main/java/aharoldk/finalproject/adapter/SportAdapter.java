package aharoldk.finalproject.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aharoldk.finalproject.R;
import aharoldk.finalproject.clases.Fighters;
import aharoldk.finalproject.viewholder.SportViewHolder;

/**
 * Created by alwandy on 11/07/17.
 */

public class SportAdapter extends RecyclerView.Adapter<SportViewHolder> {

    private Fighters[] fighterses;
    private Activity activity;

    public SportAdapter(Fighters[] fighterses, Activity activity) {
        this.fighterses = fighterses;
        this.activity = activity;
    }

    @Override
    public SportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sport, parent, false);

        return new SportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SportViewHolder holder, int position) {
        holder.bind(fighterses[position], activity);
    }

    @Override
    public int getItemCount() {
        return fighterses.length;
    }
}
