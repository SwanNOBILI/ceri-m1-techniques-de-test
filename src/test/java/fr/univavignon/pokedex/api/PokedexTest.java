package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokedexTest {

    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;
    private Pokedex pokedex;
    private Pokemon pokemon1;

    @BeforeEach
    public void setUp() {
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        pokedex = new Pokedex(metadataProviderMock, pokemonFactoryMock);
        pokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
    }

    // Test for getting a Pokemon by valid ID
    @Test
    public void testGetPokemonValidId() throws PokedexException {
        pokedex.addPokemon(pokemon1);
        
        // Verify that a valid ID (0) returns the correct Pokemon
        Pokemon retrievedPokemon = pokedex.getPokemon(0);
        assertEquals(pokemon1, retrievedPokemon, "The retrieved Pokemon should be the same as the added one.");
    }

    // Test for getting a Pokemon by an invalid ID
    @Test
    public void testGetPokemonInvalidId() {
        pokedex.addPokemon(pokemon1);
        
        // Invalid ID (1) should throw an exception
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(1), "A PokedexException should be thrown for an invalid ID.");
    }

    // Test for getting Pokemon metadata by valid ID
    @Test
    public void testGetPokemonMetadataValidId() throws PokedexException {
        int id = 0;
        
        // Define metadata for the Pokemon
        PokemonMetadata metadata = new PokemonMetadata(id, "Bulbizarre", 126, 126, 90);
        when(metadataProviderMock.getPokemonMetadata(id)).thenReturn(metadata);

        // Add the Pokemon to the Pokedex
        pokedex.addPokemon(pokemon1);

        // Retrieve the Pokemon metadata by ID and verify it
        PokemonMetadata retrievedMetadata = pokedex.getPokemonMetadata(id);
        assertEquals(metadata, retrievedMetadata, "The retrieved metadata should match the mocked metadata.");
    }

    // Test for getting Pokemon metadata by invalid ID
    @Test
    public void testGetPokemonMetadataInvalidId() {
        int id = -1;

        // Invalid ID should throw an exception
        assertThrows(PokedexException.class, () -> pokedex.getPokemonMetadata(id),
                     "A PokedexException should be thrown for an invalid ID.");
    }
}
