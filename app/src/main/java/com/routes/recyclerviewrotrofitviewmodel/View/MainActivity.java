package com.routes.recyclerviewrotrofitviewmodel.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.routes.recyclerviewrotrofitviewmodel.Model.Hero;
import com.routes.recyclerviewrotrofitviewmodel.Class.HeroesAdapter;
import com.routes.recyclerviewrotrofitviewmodel.Model.HeroesViewModel;
import com.routes.recyclerviewrotrofitviewmodel.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    HeroesAdapter adapter;

    List<Hero> heroList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        final HeroesViewModel model = ViewModelProviders.of(this).get(HeroesViewModel.class);

        model.getHeroes().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> heroList) {
                adapter = new HeroesAdapter(MainActivity.this, heroList);
                recyclerView.setAdapter(adapter);



                // OnItemClickListener on Item
                adapter.setOnItemClickListener(new HeroesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        Toast.makeText(MainActivity.this,
                                     "Real Name: " + model.getHeroes().getValue().get(position).getRealname()
                                        + " ,Team: " + model.getHeroes().getValue().get(position).getTeam()
                                        + " ,Firstappearance: " + model.getHeroes().getValue().get(position).getFirstappearance()
                                        + " ,Createdby: " + model.getHeroes().getValue().get(position).getCreatedby()
                                        + " ,Publisher: " + model.getHeroes().getValue().get(position).getPublisher()
                                , Toast.LENGTH_SHORT).show();
                        
                    }
                });
            }
        });




    }





}
