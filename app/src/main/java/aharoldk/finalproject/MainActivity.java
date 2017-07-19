package aharoldk.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import aharoldk.finalproject.clases.Newses;
import aharoldk.finalproject.viewholder.NewsViewHolder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvmain;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    private FirebaseAuth firebaseAuth;
    private Query query, queryDate;
    private FirebaseAuth.AuthStateListener authStateListener;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("news");

        query = databaseReference.orderByChild("status").equalTo("enable");

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Intent loginIntent = new Intent(MainActivity.this, UserActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);

                    finish();
                }
            }
        };

        rvmain = (RecyclerView) findViewById(R.id.rvmain);

        rvmain.setLayoutManager(new LinearLayoutManager(this));


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

            int id = item.getItemId();

            Intent foodActivity = new Intent(getApplicationContext(), FoodActivity.class);
            Intent sportActivity =  new Intent(getApplicationContext(), SportActivity.class);
            Intent healthActivity =  new Intent(getApplicationContext(), HealthActivity.class);
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);

            if (id == R.id.nav_home) {
                startActivity(getIntent());
                overridePendingTransition(R.anim.trans_in_activity, R.anim.trans_out_activity);
                finish();

            } else if (id == R.id.nav_food) {
                startActivity(foodActivity);
                overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
                finish();

            } else if (id == R.id.nav_health) {
                startActivity(healthActivity);
                overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
                finish();

            } else if (id == R.id.nav_sport) {
                startActivity(sportActivity);
                overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
                finish();

            } else if (id == R.id.nav_login){
                startActivity(loginActivity);
                overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
                finish();
            }

            return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(authStateListener);

        FirebaseRecyclerAdapter<Newses, NewsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Newses, NewsViewHolder>(
                Newses.class,
                R.layout.row_main,
                NewsViewHolder.class,
                query
        ) {
            @Override
            protected void populateViewHolder(final NewsViewHolder viewHolder, final Newses model, final int position) {


                final String post_key = getRef(position).getKey();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDate(model.getCurrentDate());
                viewHolder.setImage(model.getImage());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent detail = new Intent(MainActivity.this, DetailActivity.class);
                        detail.putExtra("news", post_key);
                        startActivity(detail);
                    }
                });
            }
        };

        rvmain.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
