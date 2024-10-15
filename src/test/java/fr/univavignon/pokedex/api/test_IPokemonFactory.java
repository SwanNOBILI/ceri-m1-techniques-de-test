package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class test_IPokemonFactory {

    private IPokemonFactory pokemonFactory;
    private Pokemon pokemon;

    @Before
    public void setUp() {
        // Creating the Pokemon object
        pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);

        // Mocking the IPokemonFactory
        pokemonFactory = mock(IPokemonFactory.class);
    }

    @Test
    public void testCreatePokemon() {
        // Defining the behavior of createPokemon
        when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(pokemon);

        // Call the method and assert the expected result
        Pokemon createdPokemon = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        assertEquals("Pokemon should be the mock instance", pokemon, createdPokemon);
    }
}
