package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class test_IPokemonTrainerFactory {
    private IPokemonTrainerFactory pokemonTrainerFactory;
    private IPokedexFactory pokedexFactory;
    private PokemonTrainer pokemonTrainer;

    private String pokemonTrainer_name;
    private Team pokemonTrainer_team;

    @Before
    public void setUp(){
        // Creating a pokemonTrainer object
        pokemonTrainer_name = "Wolf"; pokemonTrainer_team = Team.VALOR;
        pokemonTrainer = new PokemonTrainer(pokemonTrainer_name, pokemonTrainer_team, mock(IPokedex.class));

        // Mocking the pokedexFactory
        pokedexFactory = mock(IPokedexFactory.class);

        // Mocking the pokemonTrainerFactory
        pokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
    }

    @Test
    public void testCreateTrainer(){
        // Defining the behavior of createTrainer
        when(pokemonTrainerFactory.createTrainer(pokemonTrainer_name, pokemonTrainer_team, pokedexFactory)).thenReturn(pokemonTrainer);

        PokemonTrainer createdPokemonTrainer = pokemonTrainerFactory.createTrainer("Wolf", Team.VALOR, pokedexFactory);
        assertEquals("PokemonMetadata should be the mock instance", pokemonTrainer, createdPokemonTrainer);
    }
}
