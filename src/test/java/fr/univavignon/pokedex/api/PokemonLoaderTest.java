package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonLoaderTest {

    private File tempFile; // Temporary file for testing purposes

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file in the src/resources directory
        File resourceDir = new File("src/resources");
        if (!resourceDir.exists()) {
            resourceDir.mkdirs(); // Create the directory if it doesn't exist
        }

        tempFile = new File(resourceDir, "pokemons_test.csv");
        try (FileWriter writer = new FileWriter(tempFile)) {
            // Write sample Pokemon data to the temporary file
            writer.write("0,Bulbasaur,45,49,49,45,65,65,69,7\n");
            writer.write("1,Charmander,39,52,43,65,60,50,85,6\n");
        }
    }

    @AfterEach
    public void tearDown() {
        // Delete the temporary file after each test
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testGetAllPokemons() {
        // Test the getAllPokemons method
        PokemonLoader loader = new PokemonLoader(tempFile.getAbsolutePath());
        List<Pokemon> pokemons = loader.getAllPokemons();

        assertEquals(2, pokemons.size(), "The number of Pokemon should be 2.");
        assertEquals("Bulbasaur", pokemons.get(0).getName(), "The first Pokemon should be Bulbasaur.");
        assertEquals("Charmander", pokemons.get(1).getName(), "The second Pokemon should be Charmander.");
    }

    @Test
    public void testGetOnePokemon_ValidIndex() throws PokedexException {
        // Test retrieving a Pokemon with a valid index
        PokemonLoader loader = new PokemonLoader(tempFile.getAbsolutePath());
        Pokemon pokemon = loader.getOnePokemon(0);

        assertNotNull(pokemon, "The retrieved Pokemon should not be null.");
        assertEquals("Bulbasaur", pokemon.getName(), "The retrieved Pokemon should be Bulbasaur.");
    }

    @Test
    public void testGetOnePokemon_InvalidIndex() {
        // Test retrieving a Pokemon with an invalid index
        PokemonLoader loader = new PokemonLoader(tempFile.getAbsolutePath());

        Exception exception = assertThrows(PokedexException.class, () -> loader.getOnePokemon(10));
        assertEquals("Invalid ID", exception.getMessage(), "The exception message should be 'Invalid ID'.");
    }

    @Test
    public void testEmptyFile() throws IOException {
        // Test the behavior when the file is empty
        File emptyFile = new File("src/resources/pokemons_empty.csv");
        try (FileWriter writer = new FileWriter(emptyFile)) {
            // Create an empty file
        }

        PokemonLoader loader = new PokemonLoader(emptyFile.getAbsolutePath());
        List<Pokemon> pokemons = loader.getAllPokemons();

        assertTrue(pokemons.isEmpty(), "The Pokemon list should be empty.");
        emptyFile.delete(); // Clean up manually
    }
}
