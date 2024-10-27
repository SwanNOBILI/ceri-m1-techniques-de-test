package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class test_IPokemonFactory {

    private IPokemonFactory pokemonFactory;
    private PokemonLoader pokemonLoader;

    @Before
    public void setUp() {
        // Mocking the IPokemonFactory
        pokemonFactory = mock(IPokemonFactory.class);

        pokemonLoader = new PokemonLoader("src/ressources/pokemons.csv");
    }

    @Test
    public void testCreatePokemon() throws PokedexException{
        for(int i = 0; i < pokemonLoader.getAllPokemons().size(); i ++){
            // Creating a Pokemon instance
            Pokemon p = pokemonLoader.getOnePokemon(i);
            
            // Defining the behavior of createPokemon
            when(pokemonFactory.createPokemon(p.getIndex(), p.getCp(), p.getHp(), p.getDust(), p.getCandy())).thenReturn(p);

            // Call the method and assert the expected result
            Pokemon createdPokemon = pokemonFactory.createPokemon(p.getIndex(), p.getCp(), p.getHp(), p.getDust(), p.getCandy());
            assertEquals("Pokemon should be the mock instance", p, createdPokemon);
        }
    }
}
