package fr.univavignon.pokedex.api;

import java.util.Random;

/**
 * Factory class that implements the IPokemonFactory interface.
 * It creates Pokemon instances by computing their IVs.
 */
public class PokemonFactory implements IPokemonFactory {

    private static final Random RANDOM = new Random(); // Random generator for IVs

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        // Generate random IVs for attack, defense, and stamina (assuming values between 0 and 15)
        int attack = RANDOM.nextInt(16);    // Random value between 0 and 15
        int defense = RANDOM.nextInt(16);   // Random value between 0 and 15
        int stamina = RANDOM.nextInt(16);   // Random value between 0 and 15

        // Calculate IV (as a simple average of the three stats for this example)
        double iv = (attack + defense + stamina) / 45.0; // IV is a percentage (0-1)

        // Create a new Pokemon instance with the generated values
        return new Pokemon(index, "Pokemon_" + index, attack, defense, stamina, cp, hp, dust, candy, iv);
    }
}
