package com.example.practicaapimia.pokeapi;

import com.example.practicaapimia.models.PokemonList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApiService {
    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Call<PokemonList> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);
}
