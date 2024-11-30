package com.example.practicaapimia.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaapimia.R;
import com.example.practicaapimia.adapters.PokemonAdapter;
import com.example.practicaapimia.viewmodel.PokemonViewModel;

public class MainActivity extends AppCompatActivity {

    private PokemonViewModel viewModel;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PokemonAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        viewModel.getPokemonList().observe(this, pokemonList -> {
            if (pokemonList != null) {
                adapter.setPokemonList(pokemonList);
            }
        });

        viewModel.fetchPokemonList(20, 0);
    }
}
