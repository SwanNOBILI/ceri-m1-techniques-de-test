package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class test_IPokemonMetadataProvider {
    private IPokemonMetadataProvider pokemonMetadataProvider;
    private PokemonMetadata pokemonMetadata;

    @Before
    public void setUp(){
        // Creating a pokemonMetadata object
        pokemonMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);

        // Mocking the pokemonMetadataProvider
        pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        // Defining the behavior of getPokemonMetadata
        when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(pokemonMetadata);

        PokemonMetadata createdPokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(0);
        assertEquals("PokemonMetadata should be the mock instance", pokemonMetadata, createdPokemonMetadata);
    }

    @Test(expected = PokedexException.class)
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        // Define behavior for invalid index
        when(pokemonMetadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid index"));

        // Call the method with an invalid index and expect an exception
        pokemonMetadataProvider.getPokemonMetadata(-1);
    }
}
