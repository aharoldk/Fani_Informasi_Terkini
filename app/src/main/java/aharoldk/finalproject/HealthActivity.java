package aharoldk.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import aharoldk.finalproject.adapter.HealthAdapter;
import aharoldk.finalproject.adapter.SportAdapter;
import aharoldk.finalproject.clases.Fighters;
import aharoldk.finalproject.clases.Products;

/**
 * Created by aharoldk on 25/06/17.
 */

public class HealthActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    private RecyclerView rvmain;
    private Products[] products;
    private HealthAdapter healthAdapter;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvmain = (RecyclerView) findViewById(R.id.rvmain);

        loadData();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                Intent homeActivity = new Intent(getApplicationContext(), MainActivity.class);
                Intent sportActivity =  new Intent(getApplicationContext(), SportActivity.class);
                Intent healthActivity =  new Intent(getApplicationContext(), HealthActivity.class);
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);

                if (id == R.id.nav_home) {
                    startActivity(homeActivity);
                    overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
                } else if (id == R.id.nav_food) {
                    finish();
                    startActivity(getIntent());
                    overridePendingTransition(R.anim.trans_in_activity, R.anim.trans_out_activity);
                } else if (id == R.id.nav_health) {
                    startActivity(healthActivity);
                    overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
                } else if (id == R.id.nav_sport) {
                    startActivity(sportActivity);
                    overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
                } else if (id == R.id.nav_login){
                    startActivity(loginActivity);
                    overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
                }

                return true;
            }
        });
    }

    private void loadData() {
        String URL = "http://makeup-api.herokuapp.com/api/v1/products.json?brand=covergirl&product_type=lipstick";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response", response);

                        try {
                            products = gson.fromJson(response, Products[].class);

                            healthAdapter = new HealthAdapter(products, HealthActivity.this);
                            rvmain.setLayoutManager(new LinearLayoutManager(HealthActivity.this));
                            rvmain.setAdapter(healthAdapter);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );

        requestQueue.add(stringRequest);
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
