package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonMetadataTest {

    @Test
    public void testConstructorAndGetters() {
        // Créer une instance de PokemonMetadata avec les données de l'Example1
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);

        // Vérifier que les valeurs des attributs sont bien définies par le constructeur
        assertEquals(0, pokemon.getIndex());
        assertEquals("Bulbizarre", pokemon.getName());
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
    }

    @Test
    public void testGetIndex() {
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        assertEquals(0, pokemon.getIndex());
    }

    @Test
    public void testGetName() {
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        assertEquals("Bulbizarre", pokemon.getName());
    }

    @Test
    public void testGetAttack() {
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        assertEquals(126, pokemon.getAttack());
    }

    @Test
    public void testGetDefense() {
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        assertEquals(126, pokemon.getDefense());
    }

    @Test
    public void testGetStamina() {
        PokemonMetadata pokemon = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        assertEquals(90, pokemon.getStamina());
    }
}