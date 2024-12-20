package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonMetadataTest {

    @Test
    public void testConstructorAndGetters() {
        // Create a PokemonMetadata instance with Example1 data
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);

        // Check that the constructor correctly initializes the attribute values
        assertEquals(0, pokemon.getIndex());
        assertEquals("Bulbizarre", pokemon.getName());
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
    }

    @Test
    public void testGetIndex() {
        // Create a PokemonMetadata instance with Example1 data
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        // Check that the getIndex() method returns the correct value
        assertEquals(0, pokemon.getIndex());
    }

    @Test
    public void testGetName() {
        // Create a PokemonMetadata instance with Example1 data
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        // Check that the getName() method returns the correct value
        assertEquals("Bulbizarre", pokemon.getName());
    }

    @Test
    public void testGetAttack() {
        // Create a PokemonMetadata instance with Example1 data
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        // Check that the getAttack() method returns the correct value
        assertEquals(126, pokemon.getAttack());
    }

    @Test
    public void testGetDefense() {
        // Create a PokemonMetadata instance with Example1 data
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        // Check that the getDefense() method returns the correct value
        assertEquals(126, pokemon.getDefense());
    }

    @Test
    public void testGetStamina() {
        // Create a PokemonMetadata instance with Example1 data
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        // Check that the getStamina() method returns the correct value
        assertEquals(90, pokemon.getStamina());
    }
}
