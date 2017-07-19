package aharoldk.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import aharoldk.finalproject.clases.Newses;

/**
 * Created by aharoldk on 26/06/17.
 */

public class DetailActivity extends AppCompatActivity {
    private ImageView img;
    private TextView title, datetime, usernameDetail, descDetail;

    private String post_key;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("news");

        databaseReference.keepSynced(true);

        post_key = getIntent().getExtras().getString("news");

        img = (ImageView) findViewById(R.id.imgDetail);
        title = (TextView) findViewById(R.id.titleDetail);
        datetime = (TextView) findViewById(R.id.datetimeDetail);
        usernameDetail = (TextView) findViewById(R.id.usernameDetail);
        descDetail = (TextView) findViewById(R.id.descDetail);


        databaseReference.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String post_title = (String) dataSnapshot.child("title").getValue();
                String post_date = (String) dataSnapshot.child("currentDate").getValue();
                String post_desc = (String) dataSnapshot.child("desc").getValue();
                String post_name = (String) dataSnapshot.child("name").getValue();
                String post_image = (String) dataSnapshot.child("image").getValue();
                String post_uid = (String) dataSnapshot.child("user_id").getValue();

                title.setText(post_title);
                datetime.setText(post_date);
                descDetail.setText(post_desc);
                usernameDetail.setText(post_name);

                Glide.with(DetailActivity.this)
                        .load(post_image)
                        .into(img);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DetailActivity.this.finish();
    }
}
