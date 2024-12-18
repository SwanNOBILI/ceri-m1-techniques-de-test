package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PokemonTrainerTest {

    @Test
    public void testConstructorAndGetters() {
        // Create a mock IPokedex object
        IPokedex pokedex = mock(IPokedex.class);

        // Create a Team object
        Team team = Team.VALOR;

        // Create a PokemonTrainer instance using the constructor
        PokemonTrainer trainer = new PokemonTrainer("Ash", team, pokedex);

        // Verify that the attributes are correctly set by the constructor
        assertEquals("Ash", trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertEquals(pokedex, trainer.getPokedex());
    }

    @Test
    public void testGetName() {
        // Create a mock IPokedex object
        IPokedex pokedex = mock(IPokedex.class);

        // Create a Team object
        Team team = Team.VALOR;

        // Create a PokemonTrainer instance
        PokemonTrainer trainer = new PokemonTrainer("Ash", team, pokedex);

        // Verify the name of the trainer
        assertEquals("Ash", trainer.getName());
    }

    @Test
    public void testGetTeam() {
        // Create a mock IPokedex object
        IPokedex pokedex = mock(IPokedex.class);

        // Create a Team object
        Team team = Team.VALOR;

        // Create a PokemonTrainer instance
        PokemonTrainer trainer = new PokemonTrainer("Ash", team, pokedex);

        // Verify the team of the trainer
        assertEquals(Team.VALOR, trainer.getTeam());
    }

    @Test
    public void testGetPokedex() {
        // Create a mock IPokedex object
        IPokedex pokedex = mock(IPokedex.class);

        // Create a Team object
        Team team = Team.VALOR;

        // Create a PokemonTrainer instance
        PokemonTrainer trainer = new PokemonTrainer("Ash", team, pokedex);

        // Verify the pokedex of the trainer
        assertEquals(pokedex, trainer.getPokedex());
    }
}