package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PokemonTrainerFactory.
 */
public class PokemonTrainerFactoryTest {

    private IPokemonTrainerFactory trainerFactory;
    private IPokedexFactory pokedexFactory;

    @BeforeEach
    public void setUp() {
        // Initialize the trainer factory and pokedex factory before each test
        trainerFactory = new PokemonTrainerFactory();
        pokedexFactory = new PokedexFactory();
    }

    @Test
    public void testCreateTrainer() {
        // Define the trainer's details
        String trainerName = "Ash";
        Team trainerTeam = Team.VALOR;

        // Create the trainer using the factory
        PokemonTrainer trainer = trainerFactory.createTrainer(trainerName, trainerTeam, pokedexFactory);

        // Verify that the trainer is created successfully
        assertNotNull(trainer, "Trainer should not be null.");
        assertEquals(trainerName, trainer.getName(), "The trainer's name should match.");
        assertEquals(trainerTeam, trainer.getTeam(), "The trainer's team should match.");
        
        // Verify that the trainer has a valid Pokedex
        assertNotNull(trainer.getPokedex(), "The trainer's Pokedex should not be null.");
        assertEquals(0, trainer.getPokedex().size(), "The trainer's Pokedex should initially be empty.");
    }

    @Test
    public void testCreateTrainer_WithDifferentTeam() {
        // Test with a different team
        String trainerName = "Misty";
        Team trainerTeam = Team.MYSTIC;

        // Create the trainer using the factory
        PokemonTrainer trainer = trainerFactory.createTrainer(trainerName, trainerTeam, pokedexFactory);

        // Verify that the trainer's team is correct
        assertNotNull(trainer, "Trainer should not be null.");
        assertEquals(trainerName, trainer.getName(), "The trainer's name should match.");
        assertEquals(trainerTeam, trainer.getTeam(), "The trainer's team should match.");
    }
}
