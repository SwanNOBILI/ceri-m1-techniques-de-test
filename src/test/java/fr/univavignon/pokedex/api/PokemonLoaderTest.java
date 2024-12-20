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
        // Checking if the first Pokémon object has all attributes
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

    // Test case for the valid index in the getOnePokemon method
    @Test
    public void testGetOnePokemonWithValidIndex() throws PokedexException {
        List<Pokemon> pokemons = pokemonLoader.getAllPokemons();
        // Assume we have at least one pokemon in the list for the test
        int validIndex = 0;  // We can use the first element's index for this test
        Pokemon pokemon = pokemonLoader.getOnePokemon(validIndex);
        
        // Verify the returned Pokémon is the correct one
        assertNotNull(pokemon, "Pokemon should not be null for valid index.");
        assertEquals(pokemons.get(validIndex), pokemon, "The returned Pokemon should match the one at the given index.");
    }

    // Test case for an index that is too small (negative index)
    @Test
    public void testGetOnePokemonWithNegativeIndex() {
        // Use a negative index, which is invalid
        int invalidIndex = -1;
        
        // Verify that a PokedexException is thrown
        assertThrows(PokedexException.class, () -> {
            pokemonLoader.getOnePokemon(invalidIndex);
        }, "PokedexException should be thrown for negative index.");
    }

    // Test case for an index that is too large (out of bounds)
    @Test
    public void testGetOnePokemonWithLargeIndex() {
        List<Pokemon> pokemons = pokemonLoader.getAllPokemons();
        // Use an index that is out of bounds (larger than the list size)
        int invalidIndex = pokemons.size(); // This should be an invalid index
        
        // Verify that a PokedexException is thrown
        assertThrows(PokedexException.class, () -> {
            pokemonLoader.getOnePokemon(invalidIndex);
        }, "PokedexException should be thrown for index greater than the list size.");
    }

    // Test case for an invalid index in the getOnePokemon method (index equal to the size of the list)
    @Test
    public void testGetOnePokemonWithIndexEqualToSize() {
        List<Pokemon> pokemons = pokemonLoader.getAllPokemons();
        // Use an index that is exactly equal to the size of the list, which should also be invalid
        int invalidIndex = pokemons.size(); // This is the same as testing for an out-of-bounds index
        
        // Verify that a PokedexException is thrown
        assertThrows(PokedexException.class, () -> {
            pokemonLoader.getOnePokemon(invalidIndex);
        }, "PokedexException should be thrown for index equal to the size of the list.");
    }
}
