package aharoldk.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailAdminActivity extends AppCompatActivity {

    private ImageView img;
    private TextView title, datetime, usernameDetail, descDetail;

    private String post_key;

    private DatabaseReference databaseReference;

    private String post_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_admin);

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
                post_status = (String) dataSnapshot.child("status").getValue();

                title.setText(post_title);
                datetime.setText(post_date);
                descDetail.setText(post_desc);
                usernameDetail.setText(post_name);

                Glide.with(DetailAdminActivity.this)
                        .load(post_image)
                        .into(img);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_detail_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.disableMenu:
                DatabaseReference current_user =  databaseReference.child(post_key);

                if(post_status.equals("disable")){
                    current_user.child("status").setValue("enable");

                } else if (post_status.equals("enable")){
                    current_user.child("status").setValue("disable");
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DetailAdminActivity.this.finish();
    }
}
