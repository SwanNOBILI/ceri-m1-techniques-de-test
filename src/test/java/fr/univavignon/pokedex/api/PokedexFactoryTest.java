package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PokedexFactoryTest {

    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        // Initialize the factory and required dependencies before each test
        pokedexFactory = new PokedexFactory();
        metadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory();
    }

    @Test
    public void testCreatePokedex() {
        // Test creating a Pokedex instance using the PokedexFactory
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Verify that the created Pokedex is not null
        assertNotNull(pokedex, "The Pokedex should not be null.");
        
        // Verify that the created Pokedex is an instance of Pokedex
        assertTrue(pokedex instanceof Pokedex, "The created Pokedex should be an instance of the Pokedex class.");
    }
}
