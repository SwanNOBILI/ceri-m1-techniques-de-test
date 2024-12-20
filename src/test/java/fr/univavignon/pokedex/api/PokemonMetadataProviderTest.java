package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PokemonMetadataProvider.
 */
public class PokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() {
        // Initialize the PokemonMetadataProvider before each test
        metadataProvider = new PokemonMetadataProvider();
    }

    @Test
    public void testGetPokemonMetadata_ValidIndex() {
        // Test valid indices
        try {
            // Test for Bulbasaur (index 1)
            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(1);
            assertNotNull(metadata, "Metadata for Pokemon with index 1 should not be null.");
            assertEquals(1, metadata.getIndex(), "The index of the Pokemon should be 1.");
            assertEquals("Bulbasaur", metadata.getName(), "The name of the Pokemon should be Bulbasaur.");
            assertEquals(49, metadata.getAttack(), "The attack value of Bulbasaur should be 49.");
            assertEquals(49, metadata.getDefense(), "The defense value of Bulbasaur should be 49.");
            assertEquals(45, metadata.getStamina(), "The stamina value of Bulbasaur should be 45.");

            // Test for Ivysaur (index 2)
            metadata = metadataProvider.getPokemonMetadata(2);
            assertNotNull(metadata, "Metadata for Pokemon with index 2 should not be null.");
            assertEquals(2, metadata.getIndex(), "The index of the Pokemon should be 2.");
            assertEquals("Ivysaur", metadata.getName(), "The name of the Pokemon should be Ivysaur.");
            assertEquals(62, metadata.getAttack(), "The attack value of Ivysaur should be 62.");
            assertEquals(63, metadata.getDefense(), "The defense value of Ivysaur should be 63.");
            assertEquals(60, metadata.getStamina(), "The stamina value of Ivysaur should be 60.");

            // Test for Venusaur (index 3)
            metadata = metadataProvider.getPokemonMetadata(3);
            assertNotNull(metadata, "Metadata for Pokemon with index 3 should not be null.");
            assertEquals(3, metadata.getIndex(), "The index of the Pokemon should be 3.");
            assertEquals("Venusaur", metadata.getName(), "The name of the Pokemon should be Venusaur.");
            assertEquals(82, metadata.getAttack(), "The attack value of Venusaur should be 82.");
            assertEquals(83, metadata.getDefense(), "The defense value of Venusaur should be 83.");
            assertEquals(80, metadata.getStamina(), "The stamina value of Venusaur should be 80.");

        } catch (PokedexException e) {
            fail("No exception should be thrown for valid indices: " + e.getMessage());
        }
    }

    @Test
    public void testGetPokemonMetadata_InvalidIndex() {
        // Test invalid index
        PokedexException thrown = assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(999); // Non-existing index
        }, "PokedexException should be thrown for invalid index");

        assertEquals("Invalid Pokemon index: 999", thrown.getMessage(), "The exception message should match the expected message.");
    }
}
