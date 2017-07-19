package aharoldk.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import aharoldk.finalproject.clases.Newses;
import aharoldk.finalproject.viewholder.NewsViewHolder;

/**
 * Created by aharoldk on 05/07/17.
 */

class AdminActivity extends AppCompatActivity{
    private RecyclerView rvmain;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("news");

        firebaseAuth = FirebaseAuth.getInstance();


        rvmain = (RecyclerView) findViewById(R.id.rvmain);

        rvmain.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Newses, NewsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Newses, NewsViewHolder>(
                Newses.class,
                R.layout.row_main,
                NewsViewHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(final NewsViewHolder viewHolder, final Newses model, int position) {
                final String post_key = getRef(position).getKey();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDate(model.getCurrentDate());
                viewHolder.setImage(model.getImage());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent detail = new Intent(AdminActivity.this, DetailAdminActivity.class);
                        detail.putExtra("news", post_key);
                        startActivity(detail);
                    }
                });
            }
        };

        rvmain.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.nav_logout){
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        startActivity(new Intent(AdminActivity.this, MainActivity.class));
        finish();
    }
}
