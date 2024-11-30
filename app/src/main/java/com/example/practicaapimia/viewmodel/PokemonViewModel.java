package com.example.practicaapimia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.practicaapimia.models.Pokemon;
import com.example.practicaapimia.models.PokemonList;
import com.example.practicaapimia.pokeapi.PokeApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonViewModel extends ViewModel {
    private final MutableLiveData<List<Pokemon>> pokemonListLiveData = new MutableLiveData<>();
    private final PokeApiService pokeApiService;

    public PokemonViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PokeApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokeApiService = retrofit.create(PokeApiService.class);
    }

    public LiveData<List<Pokemon>> getPokemonList() {
        return pokemonListLiveData;
    }

    public void fetchPokemonList(int limit, int offset) {
        pokeApiService.getPokemonList(limit, offset).enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pokemonListLiveData.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                // Manejar el error
            }
        });
    }
}
