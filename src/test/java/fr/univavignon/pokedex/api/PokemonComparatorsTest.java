package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class PokemonComparatorsTest {

    @Test
    public void testNameComparator() {
        // Create two Pokemon instances for comparison
        Pokemon pokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon pokemon2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);

        // Use the NAME comparator to compare Pokemon by name
        int result = PokemonComparators.NAME.compare(pokemon1, pokemon2);

        // Assert that "Aquali" should come before "Bulbizarre"
        assertTrue(result > 0, "Aquali should come before Bulbizarre");
    }

    @Test
    public void testIndexComparator() {
        // Create two Pokemon instances for comparison
        Pokemon pokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon pokemon2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);

        // Use the INDEX comparator to compare Pokemon by index
        int result = PokemonComparators.INDEX.compare(pokemon1, pokemon2);

        // Assert that Pokemon with index 0 should come before Pokemon with index 133
        assertTrue(result < 0, "Pokemon with index 0 should come before Pokemon with index 133");
    }

    @Test
    public void testCpComparator() {
        // Create two Pokemon instances for comparison
        Pokemon pokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon pokemon2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);

        // Use the CP comparator to compare Pokemon by combat points (CP)
        int result = PokemonComparators.CP.compare(pokemon1, pokemon2);

        // Assert that Pokemon with lower CP should come before Pokemon with higher CP
        assertTrue(result < 0, "Pokemon with lower CP should come before Pokemon with higher CP");
    }

    @Test
    public void testMultipleComparators() {
        // Create a list of Pokemon
        Pokemon pokemon1 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);
        Pokemon pokemon2 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon pokemon3 = new Pokemon(1, "Pikachu", 112, 85, 60, 500, 35, 2000, 4, 85.0);

        // Use an ArrayList to allow modifications
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pokemon1); pokemons.add(pokemon2); pokemons.add(pokemon3);

        // Sort by name, index, then CP
        pokemons.sort(PokemonComparators.NAME.thenComparing(PokemonComparators.INDEX).thenComparing(PokemonComparators.CP));

        // Verify the sorting order
        assertEquals("Aquali", pokemons.get(0).getName());
        assertEquals("Bulbizarre", pokemons.get(1).getName());
        assertEquals("Pikachu", pokemons.get(2).getName());
    }

}
