package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class test_IPokemonMetadataProvider {
    private IPokemonMetadataProvider pokemonMetadataProvider;
    private PokemonLoader pokemonLoader;

    @Before
    public void setUp(){
        // Mocking the pokemonMetadataProvider
        pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);

        pokemonLoader = new PokemonLoader("src/ressources/pokemons.csv");
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        for(int i = 0; i < pokemonLoader.getAllPokemons().size(); i ++){
            // Creating a Pokemon instance
            Pokemon p = pokemonLoader.getOnePokemon(i);
            PokemonMetadata m = new PokemonMetadata(p.getIndex(), p.getName(), p.getAttack(), p.getDefense(), p.getStamina());

            // Defining the behavior of getPokemonMetadata
            when(pokemonMetadataProvider.getPokemonMetadata(p.getIndex())).thenReturn(m);

            PokemonMetadata createdPokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(p.getIndex());
            assertEquals("PokemonMetadata should be the mock instance", m, createdPokemonMetadata);
        }
    }

    @Test(expected = PokedexException.class)
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        // Define behavior for invalid index
        when(pokemonMetadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid ID"));

        // Check if the PokedexException from IPokemonMetadataProvider is the same as PokemonLoader
        assertEquals(pokemonMetadataProvider.getPokemonMetadata(-1), pokemonLoader.getOnePokemon(-1));
    }
}
