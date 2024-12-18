package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides the metadata for a given Pokemon index.
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    // A map to store the metadata for each Pokemon, indexed by Pokemon index
    private final Map<Integer, PokemonMetadata> pokemonMetadataMap;

    /**
     * Default constructor that initializes the metadata map.
     */
    public PokemonMetadataProvider() {
        this.pokemonMetadataMap = new HashMap<>();
        loadPokemonMetadata(); // Populate the metadata map (this can be done from a file or database)
    }

    /**
     * Loads the metadata for all Pokemon. This method should load the data from
     * a source (e.g., a CSV file, database, etc.), but for simplicity, we add some
     * static data here.
     */
    private void loadPokemonMetadata() {
        // Example of loading metadata - ideally, this data would come from a file or database
        pokemonMetadataMap.put(1, new PokemonMetadata(1, "Bulbasaur", 49, 49, 45)); // Example data
        pokemonMetadataMap.put(2, new PokemonMetadata(2, "Ivysaur", 62, 63, 60)); // Example data
        pokemonMetadataMap.put(3, new PokemonMetadata(3, "Venusaur", 82, 83, 80)); // Example data
        // Add more Pokémon metadata as needed
    }

    /**
     * Retrieves the metadata for a Pokemon by index.
     * 
     * @param index Index of the Pokemon to retrieve metadata for.
     * @return Metadata of the Pokemon.
     * @throws PokedexException If the given index is invalid.
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        PokemonMetadata metadata = pokemonMetadataMap.get(index);
        if (metadata == null) {
            throw new PokedexException("Invalid Pokemon index: " + index);
        }
        return metadata;
    }
}
