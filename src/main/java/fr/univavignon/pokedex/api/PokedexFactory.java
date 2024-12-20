package fr.univavignon.pokedex.api;

/**
 * PokedexFactory class that implements the IPokedexFactory interface.
 * It is responsible for creating new Pokedex instances.
 */
public class PokedexFactory implements IPokedexFactory {

    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        // Creates and returns a new Pokedex instance using the provided metadataProvider and pokemonFactory
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}
