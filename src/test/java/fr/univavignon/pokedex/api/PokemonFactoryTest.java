package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PokemonFactory.
 */
public class PokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        // Initialize the PokemonFactory before each test
        pokemonFactory = new PokemonFactory();
    }

    @Test
    public void testCreatePokemon() {
        // Given sample values for creating a Pokemon
        int index = 1;
        int cp = 1000;
        int hp = 150;
        int dust = 2000;
        int candy = 10;

        // Create the Pokemon using the factory
        Pokemon pokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Verify that the Pokemon is not null
        assertNotNull(pokemon, "The created Pokemon should not be null.");

        // Verify that the Pokemon index is correctly set
        assertEquals(index, pokemon.getIndex(), "The Pokemon index should match the input index.");

        // Verify that the Pokemon CP is correctly set
        assertEquals(cp, pokemon.getCp(), "The Pokemon CP should match the input CP.");

        // Verify that the Pokemon HP is correctly set
        assertEquals(hp, pokemon.getHp(), "The Pokemon HP should match the input HP.");

        // Verify that the Pokemon dust and candy are correctly set
        assertEquals(dust, pokemon.getDust(), "The Pokemon dust should match the input dust.");
        assertEquals(candy, pokemon.getCandy(), "The Pokemon candy should match the input candy.");

        // Verify that the Pokemon IV (attack, defense, stamina) is between 0 and 15
        assertTrue(pokemon.getAttack() >= 0 && pokemon.getAttack() <= 15, "The attack IV should be between 0 and 15.");
        assertTrue(pokemon.getDefense() >= 0 && pokemon.getDefense() <= 15, "The defense IV should be between 0 and 15.");
        assertTrue(pokemon.getStamina() >= 0 && pokemon.getStamina() <= 15, "The stamina IV should be between 0 and 15.");

        // Verify that the IV percentage is between 0 and 1
        double ivPercentage = pokemon.getIv();
        assertTrue(ivPercentage >= 0 && ivPercentage <= 1, "The IV percentage should be between 0 and 1.");
    }
}
