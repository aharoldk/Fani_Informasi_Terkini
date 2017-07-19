package aharoldk.finalproject.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import aharoldk.finalproject.R;
import aharoldk.finalproject.clases.Fighters;

/**
 * Created by alwandy on 11/07/17.
 */

public class SportViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageSport;

    private TextView nicknameSport;
    private TextView fullnameSport;
    private TextView classSport;
    private TextView matchSport;
    private TextView winsSport;
    private TextView drawsSport;
    private TextView losesSport;
    private TextView rankSport;
    private TextView poundRank;
    private TextView linkSport;


    public SportViewHolder(View itemView) {
        super(itemView);

        imageSport = (ImageView) itemView.findViewById(R.id.imageSport);

        nicknameSport = (TextView) itemView.findViewById(R.id.nicknameSport);
        fullnameSport = (TextView) itemView.findViewById(R.id.fullNameSport);
        classSport = (TextView) itemView.findViewById(R.id.classSport);
        matchSport = (TextView) itemView.findViewById(R.id.matchSport);
        winsSport = (TextView) itemView.findViewById(R.id.winsSport);
        drawsSport = (TextView) itemView.findViewById(R.id.drawsSport);
        losesSport = (TextView) itemView.findViewById(R.id.losesSport);
        rankSport = (TextView) itemView.findViewById(R.id.rankSport);
        poundRank = (TextView) itemView.findViewById(R.id.poundRank);
        linkSport = (TextView) itemView.findViewById(R.id.linkSport);

    }

    public void bind(final Fighters fighters, final Activity activity) {
        Glide.with(itemView.getContext())
                .load(fighters.getBelt_thumbnail())
                .into(imageSport);

        nicknameSport.setText(fighters.getNickname());
        fullnameSport.setText(fighters.getFullName());
        classSport.setText(fighters.getWeight_class());
        matchSport.setText(""+ fighters.getMatch());
        winsSport.setText(""+fighters.getWins());
        drawsSport.setText(""+fighters.getDraws());
        losesSport.setText(""+fighters.getLoses());
        rankSport.setText(fighters.getRank());
        poundRank.setText(fighters.getPound_for_pound_rank());
        linkSport.setText(fighters.getLink());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(fighters.getLink()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                itemView.getContext().startActivity(intent);
            }
        });


    }
}
