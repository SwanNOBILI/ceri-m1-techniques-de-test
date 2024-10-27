package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Comparator;
import java.util.List;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

public class test_IPokedex {
    // Declaration of mocks and necessary objects
    private IPokedex pokedex;
    private PokemonLoader pokemonLoader;

    @Before
    public void setUp() {
        // Creating the mock of the IPokedex interface
        pokedex = mock(IPokedex.class);
        
        // Creating some fake Pokemon objects
        pokemonLoader = new PokemonLoader("src/ressources/pokemons.csv");
    }

    @Test
    public void testSize() throws PokedexException{
        // Iterate through the entire list of Pokemons located in pokemonLoader
        for(int i = 0; i < pokemonLoader.getAllPokemons().size(); i ++){
            // Add one Pokemon
            pokedex.addPokemon(pokemonLoader.getOnePokemon(i));

            // Simulate the total size of the pokedex according to the size of the Pokemon list (incremental size)
            when(pokedex.size()).thenReturn(i+1);

            // Verify that the size of the pokedex matches the number of loaded Pokemon
            assertEquals(i+1, pokedex.size());
        }
    }
    
    @Test
    public void testAddPokemon() throws PokedexException{
        // Iterate through the entire list of Pokemons located in pokemonLoader
        for(int i = 0; i < pokemonLoader.getAllPokemons().size(); i ++){
            // Simulate adding the i-th Pokemon to the pokedex (i represents its position in the list)
            when(pokedex.addPokemon(pokemonLoader.getOnePokemon(i))).thenReturn(pokemonLoader.getOnePokemon(i).getIndex());

            // Add a Pokemon to the pokedex and verify the returned index
            assertEquals(pokemonLoader.getOnePokemon(i).getIndex(), pokedex.addPokemon(pokemonLoader.getOnePokemon(i)));
        }
    }
    
    @Test
    public void testGetPokemon() throws PokedexException{
        for(int i = 0; i < pokemonLoader.getAllPokemons().size(); i ++){
            // Simulate retrieving the i-th Pokemon by its id
            when(pokedex.getPokemon(i)).thenReturn(pokemonLoader.getOnePokemon(i));

            // Retrieve the Pokemon with ID i and verify the result
            assertEquals(pokemonLoader.getOnePokemon(i), pokedex.getPokemon(i));
        }
    }
    
    @Test
    public void testGetPokemonException() throws PokedexException {
        // Simulate an exception if the ID is not valid
        when(pokedex.getPokemon(-1)).thenThrow(new PokedexException("Invalid ID"));

        PokedexException e1 = null;
        PokedexException e2 = null;
        try {
            pokedex.getPokemon(-1);
        } catch (PokedexException e) {
            e1 = e;
        }
        try {
            pokemonLoader.getOnePokemon(999);
        } catch (PokedexException e) {
            e2 = e;
        }
        assertNotNull(e1); assertNotNull(e2);
        assertEquals(e1.getMessage(), e2.getMessage());
    }
    
    @Test
    public void testGetPokemons() throws PokedexException {
        // Simulate the return of the list of Pokemons
        when(pokedex.getPokemons()).thenReturn(pokemonLoader.getAllPokemons());

        // Retrieve the sorted list (by Index) of Pokemons
        List<Pokemon> res = pokedex.getPokemons();
        
        // Verify if the size of the pokemonLoader list is the same as "res"
        assertEquals(pokemonLoader.getAllPokemons().size(), res.size());
        for(int i = 0; i < res.size(); i ++){
            // Verify that "res" indeed contains the Pokemon defined by its Index i
            assertTrue(res.contains(pokemonLoader.getOnePokemon(i)));
        }

        // Verify that the getPokemons method was called once (when "res" was created)
        verify(pokedex, times(1)).getPokemons();
    }
    
    public static boolean isGetter(Method method) {
        if (method.getName().startsWith("get") && method.getParameterCount() == 0){
            return true;
        }else{
            return false;
        }
    }
    @Test
    @SuppressWarnings("unchecked")
    public void testGetPokemonsWithOrder() throws Exception {
        List<Method> getters = new ArrayList<>();
        List<Pokemon> pokemons_list = pokemonLoader.getAllPokemons();
        Method[] methods_Metadata = PokemonMetadata.class.getDeclaredMethods();
        Method[] methods_data = Pokemon.class.getDeclaredMethods();
    
        // Iterate through all methods to retrieve the getters
        for (Method method : methods_Metadata) {
            if (isGetter(method)) {
                getters.add(method);
            }
        }
        for (Method method : methods_data) {
            if (isGetter(method)) {
                getters.add(method);
            }
        }

        
        for (Method getter : getters) {
            // Primary comparator based on the current getter
            Comparator<Pokemon> primaryComparator = Comparator.comparing(pokemon -> {
                try {
                    // Dynamically call the getter
                    return (Comparable<Object>) getter.invoke(pokemon);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            
    
            // Secondary comparator to break ties (for example by index)
            Comparator<Pokemon> combinedComparator = primaryComparator.thenComparing(Pokemon::getIndex);
    
            // Simulate the Pokemon list originally sorted by index
            pokemons_list.sort(combinedComparator);
            when(pokedex.getPokemons(combinedComparator)).thenReturn(new ArrayList<>(pokemons_list));
    
            // Retrieve the list sorted by the comparator
            List<Pokemon> res = pokedex.getPokemons(combinedComparator);
    
            // Reverse the list to simulate disorder
            Collections.reverse(res);
    
            // Verify that the reversed list is different from the initially sorted list
            assertNotEquals(res, pokemons_list);
    
            // Sort the reversed list with the combined comparator
            res.sort(combinedComparator);
    
            // Verify that the sorted list is identical to the initially sorted list
            assertEquals(res, pokemons_list);
    
            // Verify that the getPokemons(Comparator) method was called once
            verify(pokedex, times(1)).getPokemons(combinedComparator);
        }
    }
            
}