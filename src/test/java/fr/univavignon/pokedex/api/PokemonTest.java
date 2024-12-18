package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {

    @Test
    public void testConstructorAndGetters() {
        // Create a Pokemon instance with Example1 data
        Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);

        // Check that the constructor initializes the attributes correctly
        assertEquals(0, pokemon.getIndex());
        assertEquals("Bulbizarre", pokemon.getName());
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
        assertEquals(613, pokemon.getCp());
        assertEquals(64, pokemon.getHp());
        assertEquals(4000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());
        assertEquals(56.0, pokemon.getIv());
    }

    @Test
    public void testGetCp() {
        // Create a Pokemon instance with Example1 data
        Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        // Check that the cp getter returns the correct value
        assertEquals(613, pokemon.getCp());
    }

    @Test
    public void testGetHp() {
        // Create a Pokemon instance with Example1 data
        Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        // Check that the hp getter returns the correct value
        assertEquals(64, pokemon.getHp());
    }

    @Test
    public void testGetDust() {
        // Create a Pokemon instance with Example1 data
        Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        // Check that the dust getter returns the correct value
        assertEquals(4000, pokemon.getDust());
    }

    @Test
    public void testGetCandy() {
        // Create a Pokemon instance with Example1 data
        Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        // Check that the candy getter returns the correct value
        assertEquals(4, pokemon.getCandy());
    }

    @Test
    public void testGetIv() {
        // Create a Pokemon instance with Example1 data
        Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        // Check that the iv getter returns the correct value
        assertEquals(56.0, pokemon.getIv());
    }
}