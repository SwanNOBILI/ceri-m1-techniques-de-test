package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Comparator;

public class PokedexTest {

    // Mocks for the dependencies of Pokedex: metadata provider and pokemon factory
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;
    private Pokedex pokedex;

    // Sample Pokemon objects used in the tests
    private Pokemon pokemon1;
    private Pokemon pokemon2;

    @BeforeEach
    public void setUp() {
        // Initializing the mocks and the Pokedex instance before each test
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);

        // Creating the Pokedex instance with the mocked dependencies
        pokedex = new Pokedex(metadataProviderMock, pokemonFactoryMock);

        // Initializing the sample Pokemon objects
        pokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        pokemon2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.00);
    }

    // Test for the initial size of the Pokedex (should be 0)
    @Test
    public void testSize() {
        // The Pokedex should be empty initially
        assertEquals(0, pokedex.size(), "The Pokedex should be empty at the start.");
        
        // Adding one Pokemon and checking if the size increases to 1
        pokedex.addPokemon(pokemon1);
        assertEquals(1, pokedex.size(), "The Pokedex should contain 1 Pokemon after adding one.");
    }

    // Test for adding a Pokemon to the Pokedex and checking the index
    @Test
    public void testAddPokemon() {
        // Adding the first Pokemon and verifying the index returned
        int index = pokedex.addPokemon(pokemon1);
        assertEquals(0, index, "The index of the first added Pokemon should be 0.");
        
        // Verifying the size of the Pokedex after adding the Pokemon
        assertEquals(1, pokedex.size(), "The Pokedex should contain 1 Pokemon.");
    }

    // Test for getting a Pokemon by valid ID
    @Test
    public void testGetPokemonValidId() throws PokedexException {
        // Adding the Pokemon to the Pokedex
        pokedex.addPokemon(pokemon1);
        
        // Retrieving the Pokemon by ID (should return the same Pokemon)
        Pokemon retrievedPokemon = pokedex.getPokemon(0);
        assertEquals(pokemon1, retrievedPokemon, "The retrieved Pokemon should be the same as the added one.");
    }

    // Test for getting a Pokemon by an invalid ID (should throw exception)
    @Test
    public void testGetPokemonInvalidId() {
        // Adding a Pokemon to the Pokedex
        pokedex.addPokemon(pokemon1);
        
        // Trying to retrieve a Pokemon with an invalid ID (should throw PokedexException)
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(1), "A PokedexException should be thrown for an invalid ID.");
    }

    // Test for getting all Pokemon from the Pokedex
    @Test
    public void testGetPokemons() {
        // Adding two Pokemon to the Pokedex
        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);
        
        // Retrieving all Pokemon and verifying the size and contents
        List<Pokemon> allPokemons = pokedex.getPokemons();
        assertEquals(2, allPokemons.size(), "The Pokedex should contain 2 Pokemon.");
        assertTrue(allPokemons.contains(pokemon1), "The Pokedex should contain Bulbizarre.");
        assertTrue(allPokemons.contains(pokemon2), "The Pokedex should contain Aquali.");
    }

    // Test for getting all Pokemon sorted by CP (Combat Power)
    @Test
    public void testGetPokemonsSorted() {
        // Adding two Pokemon to the Pokedex
        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);

        // Retrieving and sorting the Pokemon by CP
        List<Pokemon> sortedPokemons = pokedex.getPokemons(Comparator.comparingInt(Pokemon::getCp));
        
        // Verifying the order after sorting by CP
        assertEquals(pokemon1, sortedPokemons.get(0), "The first sorted Pokemon should be Bulbizarre.");
        assertEquals(pokemon2, sortedPokemons.get(1), "The second sorted Pokemon should be Aquali.");
    }

    // Test for creating a new Pokemon using the mock Pokemon factory
    @Test
    public void testCreatePokemon() {
        // Defining the stats for the Pokemon to be created
        int attack = 100, defense = 100, stamina = 100, cp = 500, hp = 50;
        
        // Mocking the PokemonFactory's createPokemon method to return pokemon1
        when(pokemonFactoryMock.createPokemon(attack, defense, stamina, cp, hp)).thenReturn(pokemon1);

        // Creating a Pokemon using the Pokedex (which calls the mock PokemonFactory)
        Pokemon createdPokemon = pokedex.createPokemon(attack, defense, stamina, cp, hp);
        
        // Verifying that the created Pokemon is not null and matches the mocked Pokemon
        assertNotNull(createdPokemon, "The created Pokemon should not be null.");
        assertEquals(pokemon1, createdPokemon, "The created Pokemon should be the same as the one returned by the mock.");
    }

    // Test for getting Pokemon metadata by valid ID
    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        int id = 0;
    
        // Définir les métadonnées du Pokémon
        PokemonMetadata metadata = new PokemonMetadata(id, "Bulbizarre", 126, 126, 90);
        
        // Mocking le comportement du fournisseur de métadonnées pour renvoyer les métadonnées
        when(metadataProviderMock.getPokemonMetadata(id)).thenReturn(metadata);
    
        // Ajouter un Pokémon avant de récupérer ses métadonnées
        pokedex.addPokemon(pokemon1);
    
        // Récupérer les métadonnées du Pokémon en utilisant le Pokedex (qui appelle le mock metadata provider)
        PokemonMetadata retrievedMetadata = pokedex.getPokemonMetadata(id);
        
        // Vérifier que les métadonnées récupérées correspondent aux métadonnées simulées
        assertEquals(metadata, retrievedMetadata, "Les métadonnées récupérées doivent correspondre aux métadonnées simulées.");
    }    

    // Test for getting Pokemon metadata by invalid ID (should throw exception)
    @Test
    public void testGetPokemonMetadataInvalidId() {
        int id = -1;
        
        // Trying to get metadata with an invalid ID (should throw PokedexException)
        assertThrows(PokedexException.class, () -> pokedex.getPokemonMetadata(id),
                     "A PokedexException should be thrown for an invalid ID.");
    }
}
