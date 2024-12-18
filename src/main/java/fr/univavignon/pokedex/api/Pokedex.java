package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Pokedex class that implements the IPokedex interface.
 * It stores and manages a collection of Pokemon.
 */
public class Pokedex implements IPokedex {

    private final List<Pokemon> pokemons; // List to store the pokemons
    private final IPokemonMetadataProvider metadataProvider; // Metadata provider
    private final IPokemonFactory pokemonFactory; // Pokemon factory

    // Constructor that accepts metadataProvider and pokemonFactory
    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.pokemons = new ArrayList<>();
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public int size() {
        return pokemons.size(); // Returns the number of pokemons in the pokedex
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        // Adds the pokemon to the list and returns the index of the newly added pokemon
        pokemons.add(pokemon);
        return pokemons.indexOf(pokemon);
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        // Returns the pokemon identified by the given id
        if (id >= 0 && id < pokemons.size()) {
            return pokemons.get(id);
        } else {
            throw new PokedexException("Invalid ID: " + id); // Throw exception if the ID is invalid
        }
    }

    @Override
    public List<Pokemon> getPokemons() {
        return pokemons; // Returns a list of all pokemons
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> sortedPokemons = new ArrayList<>(pokemons); // Create a copy of the pokemons list
        sortedPokemons.sort(order); // Sort the list using the provided comparator
        return sortedPokemons; // Return the sorted list
    }

    @Override
    public Pokemon createPokemon(int attack, int defense, int stamina, int cp, int hp) {
        // Uses the pokemonFactory to create a new Pokemon
        return pokemonFactory.createPokemon(attack, defense, stamina, cp, hp);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int id) throws PokedexException {
        // Fetches Pokemon metadata from metadataProvider
        return metadataProvider.getPokemonMetadata(id);
    }
}
