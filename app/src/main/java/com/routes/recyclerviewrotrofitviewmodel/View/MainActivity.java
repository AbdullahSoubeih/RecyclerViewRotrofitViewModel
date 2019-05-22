package com.routes.recyclerviewrotrofitviewmodel.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.routes.recyclerviewrotrofitviewmodel.Class.HeroesAdapterMultibleViews;
import com.routes.recyclerviewrotrofitviewmodel.Model.Hero;
import com.routes.recyclerviewrotrofitviewmodel.Class.HeroesAdapter;
import com.routes.recyclerviewrotrofitviewmodel.Model.HeroesViewModel;
import com.routes.recyclerviewrotrofitviewmodel.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private FloatingActionButton fab_recreate;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private HeroesAdapterMultibleViews adapter;

    //private List<Hero> heroList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fab_recreate = findViewById(R.id.fab_recreate);
        fab_recreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRecyclerViewData();
            }
        });

        mSwipeRefreshLayout = findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(MainActivity.this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, android.R.color.holo_green_dark, android.R.color.holo_orange_dark, android.R.color.holo_blue_dark);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                if(mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }

                // Fetching data from server
                loadRecyclerViewData();
            }
        });



        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





    }

    private void loadRecyclerViewData() {
        final HeroesViewModel model = ViewModelProviders.of(this).get(HeroesViewModel.class);

        model.getHeroes().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> heroList) {
                adapter = new HeroesAdapterMultibleViews(MainActivity.this, heroList);
                recyclerView.setAdapter(adapter);



                // OnItemClickListener on Item
                adapter.setOnItemClickListener(new HeroesAdapterMultibleViews.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        Toast.makeText(MainActivity.this,
                                "Name: " + model.getHeroes().getValue().get(position).getName()
                                        +" ,Real Name: " + model.getHeroes().getValue().get(position).getRealname()
                                        + " ,Team: " + model.getHeroes().getValue().get(position).getTeam()
                                        + " ,Firstappearance: " + model.getHeroes().getValue().get(position).getFirstappearance()
                                        + " ,Createdby: " + model.getHeroes().getValue().get(position).getCreatedby()
                                        + " ,Publisher: " + model.getHeroes().getValue().get(position).getPublisher()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Stopping swipe refresh
        mSwipeRefreshLayout.setRefreshing(false);

    }


    @Override
    public void onRefresh() {
        // Fetching data from server
        loadRecyclerViewData();
    }
}
