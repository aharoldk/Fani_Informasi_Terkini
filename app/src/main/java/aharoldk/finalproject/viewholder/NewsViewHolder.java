package aharoldk.finalproject.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import aharoldk.finalproject.R;

/**
 * Created by alwandy on 12/07/17.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public NewsViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setTitle(String title){
        TextView postTitle = mView.findViewById(R.id.title);
        postTitle.setText(title);
    }

    public void setDate(String date){
        TextView postDate = mView.findViewById(R.id.datetime);
        postDate.setText(date);
    }

    public void setImage(String image){
        ImageView postImage = mView.findViewById(R.id.img);
        Glide.with(itemView.getContext())
                .load(image)
                .into(postImage);
    }

}
