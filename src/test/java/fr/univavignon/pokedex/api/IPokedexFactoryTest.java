package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;
    private IPokedex pokedex;

    @Before
    public void setUp() {
        // Mocking dependencies
        metadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);
        pokedex = mock(IPokedex.class);

        // Mocking the IPokedexFactory
        pokedexFactory = mock(IPokedexFactory.class);
    }

    @Test
    public void testCreatePokedex() {
        // Defining the behavior of createPokedex
        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);
        
        // Call the method and assert the expected result
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertEquals("Pokedex should be the mock instance", pokedex, createdPokedex);
    }
}