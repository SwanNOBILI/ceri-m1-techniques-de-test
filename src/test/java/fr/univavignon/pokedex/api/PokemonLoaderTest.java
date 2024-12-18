package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class PokemonLoaderTest {

    private static final String VALID_FILE_PATH = "src/ressources/pokemons.csv";            // Path to the CSV file containing valid Pokémon data
    private static final String INVALID_FILE_PATH = "src/ressources/invalid_pokemons.csv";  // Path to the CSV file containing invalid Pokémon data
    
    private PokemonLoader pokemonLoader;

    // This method sets up the test environment by creating a PokemonLoader instance with a valid file
    @BeforeEach
    public void setUp() {
        // Initialize the test with a valid file path
        pokemonLoader = new PokemonLoader(VALID_FILE_PATH);
    }

    // Test case for checking if the column length is exactly 10
    @Test
    public void testColumnLengthIsTen() {
        // Check if the valid file contains Pokémon data with exactly 10 columns
        List<Pokemon> pokemons = pokemonLoader.getAllPokemons();
        assertFalse(pokemons.isEmpty(), "Pokemon list should not be empty.");
        // Checking if the first Pokémon object has all attributes, assuming the constructor was used correctly
        Pokemon pokemon = pokemons.get(0);
        
        // Verifying the attributes of the first Pokémon
        assertNotNull(pokemon.getCp(), "Cp should not be null.");
        assertNotNull(pokemon.getHp(), "Hp should not be null.");
        assertNotNull(pokemon.getDust(), "Dust should not be null.");
        assertNotNull(pokemon.getCandy(), "Candy should not be null.");
        assertNotNull(pokemon.getIv(), "IV should not be null.");
    }

    // Test case for checking if the column length is not 10
    @Test
    public void testColumnLengthIsNotTen() {
        // Create a PokémonLoader instance with an invalid file where lines have less than 10 columns
        PokemonLoader invalidLoader = new PokemonLoader(INVALID_FILE_PATH);
        List<Pokemon> pokemons = invalidLoader.getAllPokemons();
        // Verify that the Pokémon list is empty, as no lines should match the "columns.length == 10" condition
        assertTrue(pokemons.isEmpty(), "Pokémon list should be empty when the column length is not 10.");
    }

    // Test case for verifying that an IOException is properly caught
    @Test
    public void testRuntimeExceptionCaught() {
        // Test the part of the code that catches RuntimeException
        try {
            // Attempt to load a non-existent file to trigger an RuntimeException
            new PokemonLoader("src/resources/non_existent_file.csv");
            fail("Expected IOException to be caught.");
        } catch (RuntimeException e) {
            // Verify that the caught exception is of type RuntimeException
            assertTrue(e instanceof RuntimeException, "RuntimeException should be caught.");
        }
    }

}
